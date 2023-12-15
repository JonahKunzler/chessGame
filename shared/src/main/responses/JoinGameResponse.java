package responses;

/**
 * The JoinGameResponse class represents a response to a request to join a game, providing a message indicating the result.
 */
public class JoinGameResponse {


    /**
     * Constructs a JoinGameResponse with the provided message.
     *
     * @param message A message providing additional information about the result of the join game operation.
     */
    public JoinGameResponse(String message){
        this.message = message;
    }

    public JoinGameResponse() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * A message providing additional information about the result of the join game operation.
     */
    private String message;
}
