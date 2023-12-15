package requests;

/**
 * The RegisterRequest class represents a request to register a new user, providing user information including username, password, and email.
 */
public class RegisterRequest {


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Constructs a RegisterRequest with the provided username, password, and email.
     *
     * @param username The desired username for the new user.
     * @param password The password chosen by the new user for their account.
     * @param email    The email address associated with the new user's account.
     */
    public RegisterRequest(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * The desired username for the new user.
     */
    private String username;

    /**
     * The password chosen by the new user for their account.
     */
    private String password;

    /**
     * The email address associated with the new user's account.
     */
    private String email;
}
