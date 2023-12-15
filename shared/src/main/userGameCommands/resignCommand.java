package userGameCommands;

import webSocketMessages.UserGameCommand;

public class resignCommand extends UserGameCommand {

  public resignCommand(String authToken, int gameID) {
    super(authToken, CommandType.RESIGN);
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
