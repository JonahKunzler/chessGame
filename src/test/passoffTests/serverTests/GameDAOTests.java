package passoffTests.serverTests;
import chess.ChessGame;
import models.*;
import dataAccess.*;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class GameDAOTests {
    private static GameDAO myGame;

    @BeforeEach
    public void setup() throws DataAccessException, SQLException {
        myGame = new GameDAO();
        Connection conn = new Database().getConnection();
        myGame.clear(conn);
        conn.close();
    }

    @Test
    public void createGamePositive(){
        try{
            myGame.createGame("gameName");
        }
        catch(DataAccessException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void createGameNegative(){
        try {
            myGame.createGame("gameName");
            Connection conn = new Database().getConnection();
            myGame.clear(conn);
            conn.close();
            Game sameGame = new Game("gameName");
            assertThrows(DataAccessException.class, () -> myGame.createGame(sameGame.getGameName()));

        } catch (DataAccessException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void readGamesPositive() {
        try {
            myGame.createGame("gameName");
            myGame.createGame("gameName2");
            myGame.createGame("gameName3");

            Collection<Game> games = myGame.readGames();
            assertNotNull(games);
            assertFalse(games.isEmpty());

        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void readGamesNegative(){
        try {
            Connection conn = new Database().getConnection();
            myGame.clear(conn);
            conn.close();

            Collection<Game> games = myGame.readGames();
            assertNotNull(games);
            assertTrue(games.isEmpty());

        } catch (DataAccessException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void readSingleGamePositive(){
        try {
            Game readGame = myGame.readGame(myGame.createGame("gameName"));
            assertNotNull(readGame);

        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void readSingleGameNegative(){
        try {
            Connection conn = new Database().getConnection();
            myGame.clear(conn);
            conn.close();

            Game game = myGame.readGame(myGame.createGame("gameName"));
            assertNotNull(game);

        } catch (DataAccessException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void clearTest(){
        try {
            myGame.createGame("testing");
            Connection conn = new Database().getConnection();
            myGame.clear(conn);
            conn.close();
            Collection<Game> games = myGame.readGames();
            assertNotNull(games);
            assertTrue(games.isEmpty());
        } catch (DataAccessException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void claimSpotPositive(){
        try {
            int gameId = myGame.createGame("myGame");
            String username = "JonahKunzler";

            myGame.claimSpot(gameId, ChessGame.TeamColor.WHITE, username);

            Game game = myGame.readGame(gameId);
            assertNotNull(game);
            assertEquals(username, game.getWhiteUsername());

            myGame.claimSpot(gameId, ChessGame.TeamColor.BLACK, username);

            game = myGame.readGame(gameId);
            assertNotNull(game);
            assertEquals(username, game.getBlackUsername());

        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void claimSpotNegative() {
        try {
            int gameId = myGame.createGame("myGame");
            String username1 = "JonahKunzler";
            String username2 = "jKunzle2";

            myGame.claimSpot(gameId, ChessGame.TeamColor.WHITE, username1);

            assertThrows(DataAccessException.class, () -> myGame.claimSpot(gameId, ChessGame.TeamColor.WHITE, username2));

        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
