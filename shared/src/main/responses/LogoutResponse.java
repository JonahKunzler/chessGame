package responses;

/**
 * The LogoutResponse class represents a response to a user's logout action, providing a message indicating the result.
 */
public class LogoutResponse {

    /**
     * Constructs a LogoutResponse with the provided message.
     *
     * @param message A message providing additional information about the result of the logout action.
     */
    public LogoutResponse(String message){
        this.message = message;
    }

    public LogoutResponse(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * A message providing additional information about the result of the logout action.
     */
    private String message;
}
