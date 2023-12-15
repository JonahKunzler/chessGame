package responses;

/**
 * The ClearApplicationResponse class represents a response indicating the result of clearing application data.
 */
public class ClearApplicationResponse {

    /**
     * Constructs a ClearApplicationResponse with the provided message.
     *
     * @param message A message providing additional information about the result of clearing application data.
     */
    public ClearApplicationResponse(String message) {
        this.message = message;
    }

    public ClearApplicationResponse(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * A message providing additional information about the result of clearing application data.
     */
    private String message;
}
