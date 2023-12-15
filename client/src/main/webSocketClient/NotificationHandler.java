package webSocketClient;

import chess.myChessBoard;
import com.google.gson.Gson;
import serverMessages.errorMessage;
import ui.PrintChessBoard;
import webSocketMessages.ServerMessage;

import static webSocketMessages.ServerMessage.ServerMessageType.*;

public interface NotificationHandler {
  void notify(String message);

}