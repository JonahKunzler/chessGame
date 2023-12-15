package serverMessages;

import models.Game;
import webSocketMessages.ServerMessage;

public class loadGameMessage extends ServerMessage {

  public loadGameMessage(Game game) {
    super(ServerMessageType.LOAD_GAME);
    this.game=game;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game=game;
  }

  Game game;
}
