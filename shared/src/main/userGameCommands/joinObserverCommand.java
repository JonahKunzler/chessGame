package userGameCommands;

import webSocketMessages.UserGameCommand;

public class joinObserverCommand extends UserGameCommand {

  public joinObserverCommand(String authToken, int gameID) {
    super(authToken, CommandType.JOIN_OBSERVER);
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
