package models;

/**
 * The AuthToken class represents an authentication token associated with a user.
 */
public class AuthToken {
    /**
     * The authentication token associated with the user.
     */
    private String authToken;

    /**
     * The username of the user to whom the token belongs.
     */
    private String username;

    /**
     * Constructs an AuthToken with the provided authentication token and username.
     *
     * @param authToken The authentication token associated with the user.
     * @param username The username of the user to whom the token belongs.
     */
    public AuthToken(String authToken, String username) {
        this.authToken = authToken;
        this.username = username;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
