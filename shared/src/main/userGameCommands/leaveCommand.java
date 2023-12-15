package userGameCommands;

import webSocketMessages.UserGameCommand;

public class leaveCommand extends UserGameCommand {
  public leaveCommand(String authToken, int gameID) {
    super(authToken, CommandType.LEAVE);
    this.gameID=gameID;
  }

  public int getGameID() {
    return gameID;
  }

  public void setGameID(int gameID) {
    this.gameID=gameID;
  }

  private int gameID;
}
