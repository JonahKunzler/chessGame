package serverMessages;

import webSocketMessages.ServerMessage;

public class errorMessage extends ServerMessage{

  public errorMessage(String errorMessage) {
    super(ServerMessageType.ERROR);
    this.errorMessage=errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage=errorMessage;
  }

  private String errorMessage;
}
