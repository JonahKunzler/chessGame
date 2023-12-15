package userGameCommands;

import chess.ChessGame;
import webSocketMessages.UserGameCommand;

public class joinPlayerCommand extends UserGameCommand {

  public joinPlayerCommand(String authToken, ChessGame.TeamColor color, int gameID) {
    super(authToken, CommandType.JOIN_PLAYER);
    this.playerColor=color;
    this.gameID=gameID;
  }



  public int getGameID() {
    return gameID;
  }

  public void setGameID(int gameID) {
    this.gameID = gameID;
  }

  public ChessGame.TeamColor getColor() {
    return playerColor;
  }

  public void setColor(ChessGame.TeamColor color) {
    this.playerColor = color;
  }

  private ChessGame.TeamColor playerColor;
  private int gameID;


}
