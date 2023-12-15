package requests;

/**
 * The LoginRequest class represents a request to authenticate a user by providing their username and password.
 */
public class LoginRequest {


    /**
     * Constructs a LoginRequest with the provided username and password.
     *
     * @param username The username of the user attempting to log in.
     * @param password The password associated with the user's account.
     */
    public LoginRequest(String username, String password){
        this.username = username;
        this.password = password;
    }

    public LoginRequest() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * The username of the user attempting to log in.
     */
    private String username;

    /**
     * The password associated with the user's account.
     */
    private String password;
}
