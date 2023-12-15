package userGameCommands;

import chess.ChessMove;
import chess.myChessMove;
import webSocketMessages.UserGameCommand;

public class makeMoveCommand extends UserGameCommand {
  private int gameID;

  public makeMoveCommand(String authToken, int gameID, ChessMove move) {
    super(authToken, CommandType.MAKE_MOVE);
    this.gameID=gameID;
    this.move=(myChessMove) move;
  }

  public int getGameID() {
    return gameID;
  }

  public void setGameID(int gameID) {
    this.gameID=gameID;
  }

  public ChessMove getMove() {
    return move;
  }

  public void setMove(ChessMove move) {
    this.move=(myChessMove) move;
  }

  private myChessMove move;
}
