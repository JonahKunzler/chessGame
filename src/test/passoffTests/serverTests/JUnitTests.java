package passoffTests.serverTests;
import chess.ChessGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import services.*;
import requests.*;
import responses.*;
import models.*;

import java.util.ArrayList;

public class JUnitTests {

    private static final User user = new User("Jonah", "Kunzler", "jonahk12@gmail.com");
    @BeforeEach
    public void reset(){
        new ClearApplicationService().clearApplication();
    }

    @Test
    @Order(1)
    public void PositiveRegisterGameTest(){
        RegisterRequest request = new RegisterRequest(user.getUsername(), user.getPassword(), user.getEmail());
        RegisterService service = new RegisterService();
        RegisterResponse response = service.register(request);

        Assertions.assertEquals("Jonah", response.getUsername());
        Assertions.assertNull(response.getMessage());
        Assertions.assertNotNull(response.getAuthToken());
    }

    @Test
    @Order(2)
    public void NegativeRegisterGameTest(){
        RegisterRequest request = new RegisterRequest(null, user.getPassword(), user.getEmail());
        RegisterService service = new RegisterService();
        RegisterResponse response = service.register(request);

        Assertions.assertNull(response.getAuthToken());
        Assertions.assertEquals("Error: bad request", response.getMessage());

    }

    @Test
    @Order(3)
    public void ClearTest(){
        ClearApplicationService clearService = new ClearApplicationService();
        ClearApplicationResponse clearResponse = clearService.clearApplication();

        Assertions.assertNull(clearResponse.getMessage());
    }

    @Test
    @Order(4)
    public void PositiveCreateGameTest(){
        Game game = new Game(500, null, null, "Stuff", null);
        RegisterRequest request = new RegisterRequest(user.getUsername(), user.getPassword(), user.getEmail());
        RegisterService service = new RegisterService();
        RegisterResponse response = service.register(request);

        CreateGameRequest createGameRequest = new CreateGameRequest("Game");
        CreateGameService createGameService = new CreateGameService();
        CreateGameResponse createGameResponse = createGameService.createGame(createGameRequest, response.getAuthToken());

        Assertions.assertNotNull(createGameRequest.getGameName());
        Assertions.assertNotNull(game.getGameID());
        Assertions.assertNull(createGameResponse.getMessage());
    }

    @Test
    @Order(5)
    public void NegativeCreateGameTest(){
        RegisterRequest request = new RegisterRequest(user.getUsername(), user.getEmail(), user.getEmail());
        RegisterService service = new RegisterService();
        RegisterResponse response = service.register(request);

        CreateGameRequest createGameRequest = new CreateGameRequest(null);
        CreateGameService createGameService = new CreateGameService();
        CreateGameResponse createGameResponse = createGameService.createGame(createGameRequest, response.getAuthToken());

        Assertions.assertEquals("Error: bad request", createGameResponse.getMessage());
        Assertions.assertNotNull(response.getAuthToken());
    }

    @Test
    @Order(6)
    public void PositiveJoinGameTest(){
        RegisterRequest request = new RegisterRequest(user.getUsername(), user.getEmail(), user.getEmail());
        RegisterService service = new RegisterService();
        RegisterResponse response = service.register(request);

        CreateGameRequest createRequest = new CreateGameRequest("Stuff");
        CreateGameService createService = new CreateGameService();
        CreateGameResponse createResponse = createService.createGame(createRequest, response.getAuthToken());


        JoinGameRequest requestJoin = new JoinGameRequest(ChessGame.TeamColor.WHITE, createResponse.getGameID());
        JoinGameRequest requestJoin1 = new JoinGameRequest(ChessGame.TeamColor.BLACK, createResponse.getGameID());
        JoinGameService serviceJoin = new JoinGameService();
        JoinGameResponse joinGameResponse = serviceJoin.joinGame(requestJoin, response.getAuthToken());
        JoinGameResponse joinGameResponse1 = serviceJoin.joinGame(requestJoin1, response.getAuthToken());

        Assertions.assertNull(joinGameResponse.getMessage());
        Assertions.assertNull(joinGameResponse1.getMessage());
    }

    @Test
    @Order(7)
    public void NegativeJoinGameTest(){
        RegisterRequest request = new RegisterRequest(user.getUsername(), user.getEmail(), user.getEmail());
        RegisterService service = new RegisterService();
        RegisterResponse response = service.register(request);

        CreateGameRequest createRequest = new CreateGameRequest("Stuff");
        CreateGameService createService = new CreateGameService();
        CreateGameResponse createResponse = createService.createGame(createRequest, response.getAuthToken());


        JoinGameRequest requestJoin = new JoinGameRequest(ChessGame.TeamColor.WHITE, createResponse.getGameID());
        JoinGameRequest requestJoin1 = new JoinGameRequest(ChessGame.TeamColor.WHITE, createResponse.getGameID());

        JoinGameRequest requestJoinBlack = new JoinGameRequest(ChessGame.TeamColor.BLACK, createResponse.getGameID());
        JoinGameRequest requestJoinBlack1 = new JoinGameRequest(ChessGame.TeamColor.BLACK, createResponse.getGameID());


        JoinGameService serviceJoin = new JoinGameService();
        JoinGameResponse joinGameResponse = serviceJoin.joinGame(requestJoin, response.getAuthToken());
        JoinGameResponse joinGameResponse1 = serviceJoin.joinGame(requestJoin1, response.getAuthToken());

        JoinGameResponse joinGameResponseBlack = serviceJoin.joinGame(requestJoinBlack, response.getAuthToken());
        JoinGameResponse joinGameResponseBlack1 = serviceJoin.joinGame(requestJoinBlack1, response.getAuthToken());


        Assertions.assertEquals("Error: already taken", joinGameResponse1.getMessage());
        Assertions.assertEquals("Error: already taken", joinGameResponseBlack1.getMessage());
    }

    @Test
    @Order(8)
    public void PositiveListGamesTest(){
        RegisterRequest request = new RegisterRequest(user.getUsername(), user.getEmail(), user.getEmail());
        RegisterService service = new RegisterService();
        RegisterResponse response = service.register(request);

        CreateGameRequest createRequest = new CreateGameRequest("Stuff");
        CreateGameService createService = new CreateGameService();
        CreateGameResponse createResponse = createService.createGame(createRequest, response.getAuthToken());

        CreateGameRequest createRequest1 = new CreateGameRequest("Hello");
        CreateGameService createService1 = new CreateGameService();
        CreateGameResponse createResponse1 = createService1.createGame(createRequest1, response.getAuthToken());

        ListGamesService listGamesService = new ListGamesService();
        ListGamesResponse listGameResponse = listGamesService.listGames(response.getAuthToken());
        ArrayList<Game> list1 = new ArrayList<>(listGameResponse.getGames());
        ArrayList<Game> list2 = new ArrayList<>();

        Game game2 = new Game(2, null, null, "Stuff", null);
        Game game3 = new Game(3, null, null, "Hello", null);

        list2.add(game2);
        list2.add(game3);

        Assertions.assertNull(createResponse1.getMessage());
    }

    @Test
    @Order(9)
    public void NegativeListGamesTest(){
        // Register a user and obtain an authentication token
        RegisterRequest request = new RegisterRequest(user.getUsername(), user.getEmail(), user.getEmail());
        RegisterService service = new RegisterService();
        RegisterResponse response = service.register(request);

        CreateGameRequest createRequest = new CreateGameRequest("Stuff");
        CreateGameService createService = new CreateGameService();
        CreateGameResponse createResponse = createService.createGame(createRequest, response.getAuthToken());

        CreateGameRequest createRequest1 = new CreateGameRequest("Hello");
        CreateGameService createService1 = new CreateGameService();
        CreateGameResponse createResponse1 = createService1.createGame(createRequest1, response.getAuthToken());

        ListGamesService listGamesService = new ListGamesService();
        ListGamesResponse listGameResponse = listGamesService.listGames(response.getAuthToken()+1);


        Assertions.assertEquals("Error: unauthorized", listGameResponse.getMessage());
    }

    @Test
    @Order(10)
    public void PositiveLoginTest(){
        RegisterRequest request = new RegisterRequest(user.getUsername(), user.getPassword(), user.getEmail());
        RegisterService service = new RegisterService();
        RegisterResponse response = service.register(request);

        LoginRequest loginRequest = new LoginRequest(user.getUsername(), user.getPassword());
        LoginService loginService = new LoginService();
        LoginResponse loginResponse = loginService.login(loginRequest);

        Assertions.assertNull(loginResponse.getMessage());
    }

    @Test
    @Order(11)
    public void NegativeLoginTest(){
        RegisterRequest request = new RegisterRequest(null, user.getPassword(), user.getEmail());
        RegisterService service = new RegisterService();
        RegisterResponse response = service.register(request);


        LoginRequest loginRequest = new LoginRequest(user.getUsername(), user.getPassword());
        LoginService loginService = new LoginService();
        LoginResponse loginResponse = loginService.login(loginRequest);

        Assertions.assertEquals("Error: unauthorized", loginResponse.getMessage());
    }

    @Test
    @Order(12)
    public void PositiveLogoutTest(){
        RegisterRequest request = new RegisterRequest(user.getUsername(), user.getPassword(), user.getEmail());
        RegisterService service = new RegisterService();
        RegisterResponse response = service.register(request);

        LoginRequest loginRequest = new LoginRequest(user.getUsername(), user.getPassword());
        LoginService loginService = new LoginService();
        LoginResponse loginResponse = loginService.login(loginRequest);

        LogoutService logoutService = new LogoutService();
        LogoutResponse logoutResponse = logoutService.logout(response.getAuthToken());

        Assertions.assertNull(logoutResponse.getMessage());
    }

    @Test
    @Order(13)
    public void NegativeLogoutTest(){
        RegisterRequest request = new RegisterRequest(user.getUsername(), user.getPassword(), user.getEmail());
        RegisterService service = new RegisterService();
        RegisterResponse response = service.register(request);

        LoginRequest loginRequest = new LoginRequest(user.getUsername(), user.getPassword());
        LoginService loginService = new LoginService();
        LoginResponse loginResponse = loginService.login(loginRequest);

        LogoutService logoutService = new LogoutService();
        LogoutResponse logoutResponse = logoutService.logout(null);

        Assertions.assertEquals("Error: unauthorized", logoutResponse.getMessage());
    }





}
