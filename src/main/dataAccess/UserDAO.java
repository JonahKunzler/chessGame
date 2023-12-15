package dataAccess;


import models.User;
import java.sql.Connection;
import java.sql.SQLException;


import static java.sql.Statement.RETURN_GENERATED_KEYS;

/**
 * The UserDAO class provides data access methods for managing user information in a data store.
 */
public class UserDAO {


    public UserDAO(){}

    private Database myDatabase = new Database();



    /**
     * Creates a new user in the data store.
     *
     *
     * @throws DataAccessException if an error occurs during the user creation process.
     */

    public void createUser(String username, String password, String email) throws DataAccessException {
        Connection conn = myDatabase.getConnection();
        try (var preparedStatement = conn.prepareStatement("INSERT INTO User (username, password, email) VALUES(?, ?, ?)", RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            throw new DataAccessException(e.getMessage());
        }
        finally{
            myDatabase.closeConnection(conn);
        }
    }

    /**
     * Retrieves a user from the data store based on their username.
     *
     *
     * @return The User object with the specified username, or null if the user is not found.
     * @throws DataAccessException if an error occurs while retrieving the user.
     */

    public User readUser(String username) throws DataAccessException {
        Connection conn = myDatabase.getConnection();
        try (var preparedStatement = conn.prepareStatement("SELECT* FROM User WHERE username=?")) {
            preparedStatement.setString(1, username);
            try (var rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    username = rs.getString("username");
                    var password = rs.getString("password");
                    var email = rs.getString("email");
                    return new User(username,password,email);
                }
                else{
                    return null;
                }
            }
        }
        catch(SQLException e){
            throw new DataAccessException(e.getMessage());
        }
        finally{
            myDatabase.closeConnection(conn);
        }
    }

    /**
     * Clears all user data from the data store.
     *
     * @throws DataAccessException if an error occurs during the data clearing process.
     */


    public void clear(Connection conn) throws DataAccessException {
        try (var preparedStatement = conn.prepareStatement("DELETE FROM User")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}
