package passoffTests.serverTests;
import models.*;
import dataAccess.*;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


public class UserDAOTests {

    private static UserDAO myUser;

    @BeforeEach
    public void setup() throws DataAccessException, SQLException {
        myUser = new UserDAO();
        Connection conn = new Database().getConnection();
        myUser.clear(conn);
        conn.close();
    }

    @Test
    public void creatingUserPositive(){
        User u =  new User("hellobuddy125", "JonahKunzler", "jonahk12@gmail.com");
        try{
            myUser.createUser(u.getUsername(), u.getPassword(), u.getEmail());
        }
        catch(DataAccessException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void creatingUserNegative(){
        User u =  new User("hellobuddy125", "JonahKunzler", "jonahk12@gmail.com");
        try{
            myUser.createUser(u.getUsername(), u.getPassword(), u.getEmail());
        }
        catch(DataAccessException e){
            throw new RuntimeException(e);
        }

        User sameUserandPass = new User("hellobuddy125", "KunzlerJonah", "kunzlerj9@gmail.com");
        assertThrows(DataAccessException.class, () -> {myUser.createUser(sameUserandPass.getUsername(), sameUserandPass.getPassword(), sameUserandPass.getEmail());});
   }


   @Test
   public void readUserPositive(){
       User u = new User("Jonah", "Kunzler", "jonahk12@gmail.com");
       try {
           myUser.createUser(u.getUsername(), u.getPassword(), u.getEmail());
           User user = myUser.readUser(u.getUsername());
           assertNotNull(user, "null");
           assertEquals(u.getUsername(), user.getUsername(), "Error");
           assertEquals(u.getPassword(), user.getPassword(), "Error");
           assertEquals(u.getEmail(), user.getEmail(), "Error");

       } catch (DataAccessException e) {
           throw new RuntimeException(e);
       }
   }

   @Test
   public void readUserNegative(){
       User u = new User("Jonah" , "Kunzler", "jonahk12@gmail.com");

       try {
           myUser.createUser(u.getUsername(), u.getPassword(), u.getEmail());
       } catch (DataAccessException e) {
           throw new RuntimeException(e);
       }
       User readUser = null;
       try {
           readUser = myUser.readUser(u.getUsername());
       } catch (DataAccessException e) {
           throw new RuntimeException(e);
       }
       assertEquals(u.getUsername(), readUser.getUsername());
   }

   @Test
    public void clear(){
       User u = new User("Jonah" , "Kunzler", "jonahk12@gmail.com");
       try {
           myUser.createUser(u.getUsername(), u.getPassword(), u.getEmail());
       } catch (DataAccessException e) {
           throw new RuntimeException(e);
       }
       try {
           Connection conn = new Database().getConnection();
           myUser.clear(conn);
           conn.close();
       } catch (DataAccessException | SQLException e) {
           throw new RuntimeException(e);
       }
       User readUser = null;
       try {
           readUser = myUser.readUser(u.getUsername());
       } catch (DataAccessException e) {
           throw new RuntimeException(e);
       }
       assertEquals(null, readUser);
   }
}
