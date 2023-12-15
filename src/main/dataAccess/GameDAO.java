package dataAccess;

import chess.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Game;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.lang.*;

import static java.sql.Statement.RETURN_GENERATED_KEYS;


/**
 * The GameDAO class provides data access methods for managing Game objects in a data store.
 */
public class GameDAO {

    /**
     * Constructs a new GameDAO instance.
     */
    public GameDAO(){}

    private static HashMap<Integer, Game> games = new HashMap<>();
    private static int gameid = 1;

    private Database myDatabase = new Database();


    /**
     * Creates a new Game in the data store.
     *
     *
     * @throws DataAccessException if an error occurs during the data creation process.
     */


    public int createGame(String gameName) throws DataAccessException {
        Connection conn = myDatabase.getConnection();
        try (var preparedStatement = conn.prepareStatement("INSERT INTO Game (gameName, game) VALUES(?, ?)", RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, gameName);
            preparedStatement.setString(2,  new Gson().toJson(new myChessGame()));

            preparedStatement.executeUpdate();
            var resultSet = preparedStatement.getGeneratedKeys();
            var ID = 0;
            if (resultSet.next()) {
                ID = resultSet.getInt(1);
            }
            return ID;
        }
        catch(SQLException e){
            throw new DataAccessException(e.getMessage());
        }
        finally{
            myDatabase.closeConnection(conn);
        }
    }

    /**
     * Retrieves a list of all Game objects from the data store.
     *
     * @return A List of Game objects, or null if no games are found.
     * @throws DataAccessException if an error occurs while retrieving the games.
     */

    public Collection<Game> readGames() throws DataAccessException {
        Collection<Game> gamelist  = new ArrayList<>();
        Connection conn = myDatabase.getConnection();
        try (var preparedStatement = conn.prepareStatement("SELECT* FROM Game")) {
            try (var rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    var gameID = rs.getInt("gameID");
                    var whiteUsername = rs.getString("whiteUsername");
                    var blackUsername = rs.getString("blackUsername");
                    var gameName = rs.getString("gameName");
                    var json = rs.getString("game");
                    var builder = new GsonBuilder();
                    builder.registerTypeAdapter(ChessBoard.class, new ChessBoardAdapter());
                    builder.registerTypeAdapter(ChessPiece.class, new ChessPieceAdapter());


                    var game = builder.create().fromJson(json, myChessGame.class);
                    Game newgame = new Game(gameID, blackUsername, whiteUsername, gameName, game);
                    gamelist.add(newgame);
                }
                return gamelist;
            }
        }
        catch(SQLException e){
            throw new DataAccessException(e.getMessage());
        }
        finally{
            myDatabase.closeConnection(conn);
        }
    }



    public Game readGame(int gameID) throws DataAccessException {
        Connection conn = myDatabase.getConnection();
        try (var preparedStatement = conn.prepareStatement("SELECT* FROM Game WHERE gameID=?")) {
            preparedStatement.setInt(1, gameID);
            try (var rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    gameID = rs.getInt("gameID");
                    var whiteUsername = rs.getString("whiteUsername");
                    var blackUsername = rs.getString("blackUsername");
                    var gameName = rs.getString("gameName");
                    var json = rs.getString("game");
                    var builder = new GsonBuilder().registerTypeAdapter(myChessPiece.class, new ChessPieceAdapter());
                    var game = builder.create().fromJson(json, myChessGame.class);
                    return new Game(gameID, blackUsername, whiteUsername, gameName, game);
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
     * Updates an existing Game in the data store.
     *
     * @param g The Game object to be updated.
     * @throws DataAccessException if an error occurs during the data update process.
     */

    /**
     * Clears all Game data from the data store.
     *
     * @throws DataAccessException if an error occurs during the data clearing process.
     */
    public void clear(Connection conn) throws DataAccessException {
        try (var preparedStatement = conn.prepareStatement("DELETE FROM Game")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }


    public void claimSpot(int gameID, ChessGame.TeamColor color, String username) throws DataAccessException {
        Connection conn = myDatabase.getConnection();
        var game = readGame(gameID);
        if(game.getBlackUsername() != null && color== ChessGame.TeamColor.BLACK){
            throw new DataAccessException("Error");
        }
        if(game.getWhiteUsername() != null && color == ChessGame.TeamColor.WHITE){
            throw new DataAccessException("Error");
        }
        if (color == ChessGame.TeamColor.WHITE) {
            try (var preparedStatement = conn.prepareStatement("UPDATE game SET whiteUsername = ? WHERE gameID = ?")) {
                preparedStatement.setInt(2, gameID);
                preparedStatement.setString(1, username);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new DataAccessException(e.getMessage());
            } finally {
                myDatabase.closeConnection(conn);
            }
        }
        else {
            try (var preparedStatement = conn.prepareStatement("UPDATE Game SET blackUsername=? WHERE gameID=?")) {
                preparedStatement.setInt(2, gameID);
                preparedStatement.setString(1, username);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new DataAccessException(e.getMessage());
            } finally {
                myDatabase.closeConnection(conn);
            }
        }
    }

    public void updateGame(Game game) throws DataAccessException {
        //sql update
        Connection conn = myDatabase.getConnection();
        try (var preparedStatement = conn.prepareStatement("UPDATE Game SET game =? WHERE gameID=?")) {
            preparedStatement.setInt(2, game.getGameID());
            preparedStatement.setString(1, new Gson().toJson(game.getGame()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        } finally {
            myDatabase.closeConnection(conn);
        }
    }


}
