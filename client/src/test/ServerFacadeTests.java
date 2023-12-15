import chess.ChessGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.CreateGameRequest;
import requests.JoinGameRequest;
import requests.LoginRequest;
import requests.RegisterRequest;
import responses.*;
import services.ClearApplicationService;
import ui.DataCache;
import ui.ServerFacade;

import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ServerFacadeTests {

  private ServerFacade serverFacade;


  @BeforeEach
  public void setup() {
    serverFacade = new ServerFacade();
  }

  @Test
  void positiveRegisterTest(){
    RegisterRequest request = new RegisterRequest(UUID.randomUUID().toString(), "u", "fhsndfi");
    try{
      RegisterResponse response = serverFacade.register(request);
      assertNull(response.getMessage());
      assertNotNull(response.getAuthToken());
    }
    catch(IOException e){
      System.out.println(e.getMessage());
    }
  }

  @Test
  void negativeRegisterTest(){
    RegisterRequest request = new RegisterRequest("J", "K", "jonahk12@gmail.com");
    try{
      RegisterResponse response = serverFacade.register(request);
      assertEquals(response.getMessage(), "Error: already taken");
    }
    catch(IOException e){
      System.out.println(e.getMessage());
    }
  }

  @Test
  void positiveLoginTest(){
    LoginRequest loginRequest = new LoginRequest("J", "K");
    try{
      LoginResponse response = serverFacade.login(loginRequest);
      assertNull(response.getMessage());
    }
    catch(IOException e){
      System.out.println(e.getMessage());
    }
  }

  @Test
  void negativeLoginTest(){
    LoginRequest loginRequest = new LoginRequest("bhdsufgiku", "tg6r4i7");
    try{
      LoginResponse response = serverFacade.login(loginRequest);
      assertEquals("Error: unauthorized", response.getMessage());
    }
    catch(IOException e){
      System.out.println(e.getMessage());
    }
  }

  @Test
  void positiveLogoutTest(){
    LoginRequest loginRequest = new LoginRequest("J", "K");
    try{
      LoginResponse response = serverFacade.login(loginRequest);
      assertNull(response.getMessage());
      DataCache.token = response.getAuthToken();
      LogoutResponse logoutResponse = serverFacade.logout();
      assertNull(logoutResponse.getMessage());
    }
    catch(IOException e){
      System.out.println(e.getMessage());
    }
  }

  @Test
  void negativeLogoutTest(){
    LoginRequest loginRequest = new LoginRequest("Sup", "Bro");
    try{
      LoginResponse response = serverFacade.login(loginRequest);
      assertNotNull(response.getMessage());
      DataCache.token = response.getAuthToken();
      LogoutResponse logoutResponse = serverFacade.logout();
      assertEquals("Error: unauthorized",logoutResponse.getMessage());
    }
    catch(IOException e){
      System.out.println(e.getMessage());
    }
  }

  @Test
  void positiveListGames(){
    LoginRequest request = new LoginRequest("J", "K");
    try{
      LoginResponse response = serverFacade.login(request);
      assertNull(response.getMessage());
      DataCache.token = response.getAuthToken();
      ListGamesResponse listGamesResponse = serverFacade.listgames();
      assertNull(listGamesResponse.getMessage());
    }
    catch(IOException e){
      System.out.println(e.getMessage());
    }
  }

  @Test
  void negativeListGames(){
    LoginRequest request = new LoginRequest("J", "K");
    try{
      LoginResponse response = serverFacade.login(request);
      assertNull(response.getMessage());
      DataCache.token = null;
      ListGamesResponse listGamesResponse = serverFacade.listgames();
      assertNotNull(listGamesResponse.getMessage());
    }
    catch(IOException e){
      System.out.println(e.getMessage());
    }
  }

  @Test
  void positiveCreateGame(){
//UUID for game name
    LoginRequest request = new LoginRequest("J", "K");
    CreateGameRequest createGameRequest = new CreateGameRequest(UUID.randomUUID().toString());
    try{
      LoginResponse response = serverFacade.login(request);
      assertNull(response.getMessage());
      DataCache.token = response.getAuthToken();
      CreateGameResponse createGameResponse = serverFacade.createGame(createGameRequest);
      assertNull(createGameResponse.getMessage());
    }
    catch(IOException e){
      System.out.println(e.getMessage());
    }
  }

  @Test
  void negativeCreateGame(){
    LoginRequest request = new LoginRequest("J", "K");
    CreateGameRequest createGameRequest = new CreateGameRequest(UUID.randomUUID().toString());
    try{
      LoginResponse response = serverFacade.login(request);
      assertNull(response.getMessage());
      DataCache.token = null;
      CreateGameResponse createGameResponse = serverFacade.createGame(createGameRequest);
      assertNotNull(createGameResponse.getMessage());
    }
    catch(IOException e){
      System.out.println(e.getMessage());
    }
  }

  @Test
  void positiveJoin(){
    LoginRequest request = new LoginRequest("J", "K");
    JoinGameRequest joinGameRequest = new JoinGameRequest(null, 672);
    try{
      LoginResponse response = serverFacade.login(request);
      assertNull(response.getMessage());
      DataCache.token = response.getAuthToken();
      JoinGameResponse joinGameResponse = serverFacade.joinGame(joinGameRequest);
      assertNull(joinGameResponse.getMessage());
    }
    catch(IOException e){
      System.out.println(e.getMessage());
    }
  }

  @Test
  void negativeJoin(){
    LoginRequest request = new LoginRequest("J", "K");
    JoinGameRequest joinGameRequest = new JoinGameRequest(ChessGame.TeamColor.WHITE, 672);
    try{
      LoginResponse response = serverFacade.login(request);
      assertNull(response.getMessage());
      DataCache.token = response.getAuthToken();
      JoinGameResponse joinGameResponse = serverFacade.joinGame(joinGameRequest);
      assertEquals("Error: already taken", joinGameResponse.getMessage());
    }
    catch(IOException e){
      System.out.println(e.getMessage());
    }
  }

}
