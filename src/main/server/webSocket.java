package server;

import chess.*;
import com.google.gson.Gson;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import models.AuthToken;
import models.Game;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import serverMessages.errorMessage;
import serverMessages.loadGameMessage;
import serverMessages.notificationMessage;
import userGameCommands.*;
import webSocketMessages.ServerMessage;
import webSocketMessages.UserGameCommand;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Set;


@WebSocket
public class webSocket {
  private final ConnectionManager connections = new ConnectionManager();

  @OnWebSocketMessage
  public void onMessage(Session session, String message) throws IOException, DataAccessException {
    UserGameCommand gameCommand = new Gson().fromJson(message, UserGameCommand.class);
    joinPlayerCommand playerCommand = new Gson().fromJson(message, joinPlayerCommand.class);
    joinObserverCommand observerCommand = new Gson().fromJson(message, joinObserverCommand.class);
    makeMoveCommand moveCommand = new Gson().fromJson(message, makeMoveCommand.class);
    leaveCommand commandLeave = new Gson().fromJson(message, leaveCommand.class);
    resignCommand commandResign = new Gson().fromJson(message, resignCommand.class);
    try {
      switch (gameCommand.getCommandType()) {
        case JOIN_PLAYER -> joinPlayerHandler(playerCommand, session);
        case JOIN_OBSERVER -> joinObserverHandler(observerCommand, session);
        case MAKE_MOVE -> makeMoveHandler(moveCommand, session);
        case LEAVE -> leaveHandler(commandLeave, session);
        case RESIGN -> resignHandler(commandResign, session);
      }
    }
    catch (InvalidMoveException e) {
      throw new RuntimeException(e);
    }
  }

  private void joinPlayerHandler(joinPlayerCommand command, Session session) throws DataAccessException, IOException {
    AuthDAO auth = new AuthDAO();
    AuthToken token = auth.readToken(command.getAuthString());
    if(token == null){
      session.getRemote().sendString(new Gson().toJson(new errorMessage("<Error>")));
      return;
    }
    String username = token.getUsername();

    GameDAO gameDao = new GameDAO();
    int gameID = command.getGameID();
    Game myGame = gameDao.readGame(gameID);

    ChessGame.TeamColor color =  command.getColor();

    if(myGame == null){
      session.getRemote().sendString(new Gson().toJson(new errorMessage("<Error>")));
      return;
    }
    if((color == ChessGame.TeamColor.BLACK && (myGame.getBlackUsername() == null || !myGame.getBlackUsername().equals(username)))|| // set equal to user
            (color == ChessGame.TeamColor.WHITE && (myGame.getWhiteUsername() == null || !myGame.getWhiteUsername().equals(username)))){
        session.getRemote().sendString(new Gson().toJson(new errorMessage("<Error>")));
        return;
    }
    connections.add(gameID, command.getAuthString(), session);
    loadGame(session, myGame);

    Set<Connection> connectionList = connections.connectionList.get(gameID);
    for(Connection c: connectionList){
      if(!c.getUsername().equals(command.getAuthString())){
        c.getSession().getRemote().sendString(new Gson().toJson(new notificationMessage("<" + username + " joined as " + color + ">")));
      }
    }
  }
  private void loadGame(Session session, Game game) throws IOException {
    ServerMessage loadGame = new loadGameMessage(game);
    Gson gson = new Gson();
    String jsonMessage = gson.toJson(loadGame);
    session.getRemote().sendString(jsonMessage);
  }

  private void joinObserverHandler(joinObserverCommand command, Session session) throws DataAccessException, IOException {
    AuthDAO auth = new AuthDAO();
    AuthToken token = auth.readToken(command.getAuthString());
    if(token == null){
      session.getRemote().sendString(new Gson().toJson(new errorMessage("<Error>")));
      return;
    }
    String username = token.getUsername();

    GameDAO gameDao = new GameDAO();
    int gameID = command.getGameID();
    Game myGame = gameDao.readGame(gameID);

    if(myGame == null){
      session.getRemote().sendString(new Gson().toJson(new errorMessage("<Error>")));
      return;
    }

    connections.add(gameID, command.getAuthString(), session);
    loadGame(session, myGame);

    Set<Connection> connectionList = connections.connectionList.get(gameID);
    for(Connection c: connectionList){
      if(!c.getUsername().equals(command.getAuthString())){
        c.getSession().getRemote().sendString(new Gson().toJson(new notificationMessage("<" + username + " joined as observer>")));
      }
    }
  }

  private void makeMoveHandler(makeMoveCommand command, Session session) throws DataAccessException, InvalidMoveException, IOException {
    AuthDAO auth = new AuthDAO();
    AuthToken token = auth.readToken(command.getAuthString());
    String username = token.getUsername();

    GameDAO gameDao = new GameDAO();
    int gameID = command.getGameID();
    Game myGame = gameDao.readGame(gameID);


    ChessGame chessGame=gameDao.readGame(gameID).getChessGame();
    if(chessGame.isInCheckmate(chessGame.getTeamTurn()) || chessGame.isInStalemate(chessGame.getTeamTurn()) || chessGame.gameOver()){
      session.getRemote().sendString(new Gson().toJson(new errorMessage("<Error>")));
      return;
    }
    System.out.println(command.getMove().toString());
    System.out.println(chessGame.validMoves(command.getMove().getStartPosition()));
    if (chessGame.validMoves(command.getMove().getStartPosition()).contains(command.getMove())) { //why returning false
      if (chessGame.getTeamTurn() == ChessGame.TeamColor.WHITE && myGame.getWhiteUsername().equals(username)) {
        chessGame.makeMove(command.getMove());
        myGame.setGame(chessGame);
        gameDao.updateGame(myGame);
        }
      else if (chessGame.getTeamTurn() == ChessGame.TeamColor.BLACK && myGame.getBlackUsername().equals(username)) {
        chessGame.makeMove(command.getMove());
        myGame.setGame(chessGame);
        gameDao.updateGame(myGame);
      }
      else {
        session.getRemote().sendString(new Gson().toJson(new errorMessage("<It isn't your turn>")));
        return;
      }
    }
    else {
      session.getRemote().sendString(new Gson().toJson(new errorMessage("<Invalid move>")));
      return;
    }

    Set<Connection> connectionList=connections.connectionList.get(gameID);
    for (Connection c : connectionList) {
      loadGame(c.getSession(), myGame);
      if (!c.getUsername().equals(command.getAuthString())) {
        c.getSession().getRemote().sendString(new Gson().toJson(new notificationMessage("<" + username + " made a move>")));
      }
    }
  }

  private void leaveHandler(leaveCommand command, Session session) throws DataAccessException, IOException {
    AuthDAO auth = new AuthDAO();
    AuthToken token = auth.readToken(command.getAuthString());
    String username = token.getUsername();

    int gameID = command.getGameID();

    GameDAO gameDao = new GameDAO();
    Game myGame = gameDao.readGame(gameID);

    if (myGame != null) {
      if (myGame.getWhiteUsername() != null && myGame.getWhiteUsername().equals(username)) {
        myGame.setWhiteUsername(null);
      } else if (myGame.getBlackUsername() != null && myGame.getBlackUsername().equals(username)) {
        myGame.setBlackUsername(null);
      }
      gameDao.updateGame(myGame);
      connections.remove(token.getAuthToken());

      Set<Connection> connectionList = connections.connectionList.get(gameID);
      for(Connection c: connectionList){
        if(!c.getUsername().equals(command.getAuthString())){
          c.getSession().getRemote().sendString(new Gson().toJson(new notificationMessage("<" + username + " left the game>")));
        }
      }
      session.close();
    }
  }

  private void resignHandler(resignCommand command, Session session) throws DataAccessException, IOException {
    AuthDAO auth=new AuthDAO();
    AuthToken token=auth.readToken(command.getAuthString());
    String username=token.getUsername();
    int gameID=command.getGameID();


    GameDAO gameDao=new GameDAO();
    Game myGame=gameDao.readGame(gameID);

    if (myGame.getGame().gameOver()) {
      session.getRemote().sendString(new Gson().toJson(new errorMessage("<Error>")));
      return;
    }
      if (!myGame.getBlackUsername().equals(username) && !myGame.getWhiteUsername().equals(username)) {
        session.getRemote().sendString(new Gson().toJson(new errorMessage("<Error>")));
        return;
      } else {
        myGame.getChessGame().resign();

        gameDao.updateGame(myGame);
        connections.remove(username);

        Set<Connection> connectionList=connections.connectionList.get(gameID);
        for (Connection c : connectionList) {
          c.getSession().getRemote().sendString(new Gson().toJson(new notificationMessage("<" + username + " has resigned>")));
        }
        if (session.isOpen()) {
          session.close();
        }
      }
    }
  }
