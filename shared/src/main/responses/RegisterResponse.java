package responses;

/**
 * The RegisterResponse class represents a response to a user registration request, providing the username, an authentication token, and a message.
 */
public class RegisterResponse {

    /**
     * Constructs a RegisterResponse with the provided username, authentication token, and message.
     *
     * @param username   The username of the newly registered user.
     * @param authToken  An authentication token generated upon successful registration.
     * @param message    A message providing additional information about the result of the registration request.
     */
    public RegisterResponse(String username, String authToken, String message){
        this.username = username;
        this.authToken = authToken;
        this.message = message;
    }

    public RegisterResponse(String username, String authToken){
        this.username = username;
        this.authToken = authToken;
    }

    public RegisterResponse(String message) {
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    /**
     * The username of the newly registered user.
     */
    private String username;

    /**
     * An authentication token generated upon successful registration.
     */
    private String authToken;

    /**
     * A message providing additional information about the result of the registration request.
     */
    private String message;
}
