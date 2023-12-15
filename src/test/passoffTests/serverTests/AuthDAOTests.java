package passoffTests.serverTests;
import models.*;
import dataAccess.*;
import org.junit.jupiter.api.*;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;


public class AuthDAOTests {

    private static AuthDAO myAuth;

    @BeforeEach
    public void setup() throws DataAccessException, SQLException {
        myAuth = new AuthDAO();
        Connection conn = new Database().getConnection();
        myAuth.clear(conn);
        conn.close();
    }

    @Test
    public void creatingAuthTokenPositive(){
        AuthToken myToken =  new AuthToken("hellobuddy125", "JonahKunzler");
        try{
            myAuth.createToken(myToken.getAuthToken(), myToken.getUsername());
        }
        catch(DataAccessException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void creatingAuthTokenNegative(){
        AuthToken myToken =  new AuthToken("hellobuddy125", "JonahKunzler");
        try{
            myAuth.createToken(myToken.getAuthToken(), myToken.getUsername());
        }
        catch(DataAccessException e){
            throw new RuntimeException(e);
        }

        AuthToken sameToken = new AuthToken("hellobuddy125", "KunzlerJonah");
        assertThrows(DataAccessException.class, () -> {myAuth.createToken(sameToken.getAuthToken(), sameToken.getUsername());});
    }

    @Test
    public void readAuthTokenPositive(){
        AuthToken myToken = new AuthToken("hellobuddy125", "JonahKunzler");

        try {
            myAuth.createToken(myToken.getAuthToken(), myToken.getUsername());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        AuthToken readToken = null;
        try {
            readToken = myAuth.readToken(myToken.getAuthToken());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        assertEquals(myToken.getAuthToken(), readToken.getAuthToken());
        assertEquals(myToken.getUsername(), readToken.getUsername());
    }

    @Test
    public void readAuthTokenNegative(){
        AuthToken myToken = new AuthToken("hellobuddy125", "JonahKunzler");
        AuthToken readToken = null;
        try {
            readToken = myAuth.readToken(myToken.getAuthToken());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }

        assertEquals(null, readToken);
    }

    @Test
    public void clearTest(){
        AuthToken myToken = new AuthToken("hellobuddy125", "JonahKunzler");
        try {
            myAuth.createToken(myToken.getAuthToken(), myToken.getUsername());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        try {
            Connection conn = new Database().getConnection();
            myAuth.clear(conn);
            conn.close();
        } catch (DataAccessException | SQLException e) {
            throw new RuntimeException(e);
        }
        AuthToken retrievedToken = null;
        try {
            retrievedToken = myAuth.readToken(myToken.getAuthToken());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        assertEquals(null, retrievedToken);
    }

    @Test
    public void deletePositive(){
        AuthToken myToken = new AuthToken("hellobuddy125", "JonahKunzler");

        try {
            myAuth.createToken(myToken.getAuthToken(), myToken.getUsername());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }

        try {
            myAuth.delete(myToken.getAuthToken());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }

        AuthToken readToken = null;
        try {
            readToken = myAuth.readToken(myToken.getAuthToken());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }

        assertEquals(null, readToken);
    }

    @Test
    public void deleteNegative() {
        AuthToken myToken = new AuthToken(null, null);
        assertDoesNotThrow(() -> {
            myAuth.delete(myToken.getAuthToken());
        });
    }
}
