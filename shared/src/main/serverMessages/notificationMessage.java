package serverMessages;

import webSocketMessages.ServerMessage;

public class notificationMessage extends ServerMessage {



  public notificationMessage(String message) {
    super(ServerMessageType.NOTIFICATION);
    this.message=message;
  }


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message=message;
  }

  private String message;
}
