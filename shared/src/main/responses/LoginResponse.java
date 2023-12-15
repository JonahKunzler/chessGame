package responses;

/**
 * The LoginResponse class represents a response to a login request, providing a message, the username, and an authentication token.
 */
public class LoginResponse {

    /**
     * Constructs a LoginResponse with the provided message, username, and authentication token.
     *
     * @param message    A message providing additional information about the result of the login operation.
     * @param username   The username of the user who successfully logged in.
     * @param authToken  An authentication token generated upon successful login.
     */
    public LoginResponse(String message, String username, String authToken){
        this.message = message;
        this.username = username;
        this.authToken = authToken;
    }

    public LoginResponse(String username, String authToken){
        this.authToken = authToken;
        this.username = username;
    }
    public LoginResponse(String message){
        this.message = message;
    }

    public LoginResponse() {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    /**
     * A message providing additional information about the result of the login operation.
     */
    private String message;

    /**
     * The username of the user who successfully logged in.
     */
    private String username;

    /**
     * An authentication token generated upon successful login.
     */
    private String authToken;
}
