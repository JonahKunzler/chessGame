package models;

/**
 * The User class represents a user with associated user information.
 */
public class User {

    /**
     * The username of the user.
     */
    private String username;

    /**
     * The password associated with the user's account.
     */
    private String password;

    /**
     * The email address of the user.
     */
    private String email;

    /**
     * Constructs a User instance with the provided username, password, and email.
     *
     * @param username The username of the user.
     * @param password The password associated with the user's account.
     * @param email    The email address of the user.
     */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User() {}

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
