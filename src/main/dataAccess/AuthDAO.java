package dataAccess;

import models.AuthToken;

import java.sql.*;
import java.util.HashMap;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

/**
 * The AuthDAO class provides data access methods for managing authentication tokens in a data store.
 */
public class AuthDAO {

    public AuthDAO() {}

    private static HashMap<String, AuthToken> tokens = new HashMap<>();

    private Database myDatabase = new Database();


    public HashMap<String, AuthToken> getTokens() {
        return tokens;
    }


    /**
     * Creates a new authentication token in the data store.
     *
     * @throws DataAccessException if an error occurs during the token creation process.
     */

    public void createToken(String authToken, String username) throws DataAccessException {
        Connection conn = myDatabase.getConnection();
        try (var preparedStatement = conn.prepareStatement("INSERT INTO AuthToken (authToken, username) VALUES(?, ?)", RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, authToken);
            preparedStatement.setString(2, username);
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
     * Retrieves an authentication token from the data store based on its value.
     *
     *
     * @return The AuthToken object with the specified value, or null if not found.
     * @throws DataAccessException if an error occurs while retrieving the token.
     */

    public AuthToken readToken(String authToken) throws DataAccessException {
        Connection conn = myDatabase.getConnection();
        try (var preparedStatement = conn.prepareStatement("SELECT* FROM AuthToken WHERE authToken=?")) {
            preparedStatement.setString(1, authToken);
            try (var rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    authToken = rs.getString("authToken");
                    var username = rs.getString("username");
                    return new AuthToken(authToken, username);
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
     * Clears all authentication token data from the data store.
     *
     * @throws DataAccessException if an error occurs during the data clearing process.
     */
    public void clear(Connection conn) throws DataAccessException {
        try (var preparedStatement = conn.prepareStatement("DELETE FROM AuthToken")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error");
        }
    }

    /**
     * Deletes an authentication token from the data store based on its value.
     *
     * @param authToken The authentication token value to be deleted.
     * @throws DataAccessException if an error occurs while deleting the token.
     */

    public void delete(String authToken) throws DataAccessException {
        Connection conn = myDatabase.getConnection();
        try (var preparedStatement = conn.prepareStatement("DELETE FROM AuthToken WHERE authToken=?")) {
            preparedStatement.setString(1, authToken);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        finally{
            myDatabase.closeConnection(conn);
        }
    }
}
