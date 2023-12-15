package models;

import chess.ChessGame;

import java.util.Objects;

/**
 * The Game class represents a chess game with associated information and gameplay data.
 */
public class Game implements Comparable{

    /**
     * The unique identifier of the chess game.
     */
    private int gameID;

    /**
     * The username of the player with the white pieces in the chess game.
     */
    private String whiteUsername;

    /**
     * The username of the player with the black pieces in the chess game.
     */
    private String blackUsername;

    /**
     * The name or title of the chess game.
     */
    private String gameName;

    /**
     * The ChessGame object representing the state and moves of the chess game.
     */
    private ChessGame game;

    /**
     * Constructs a Game instance with the provided game identifier, player usernames, game name, and ChessGame object.
     *
     * @param gameID        The unique identifier of the chess game.
     * @param blackUsername The username of the player with the black pieces.
     * @param whiteUsername The username of the player with the white pieces.
     * @param gameName      The name or title of the chess game.
     * @param game          The ChessGame object representing the state and moves of the game.
     */
    public Game(int gameID, String blackUsername, String whiteUsername, String gameName, ChessGame game) {
        this.gameID = gameID;
        this.blackUsername = blackUsername;
        this.whiteUsername = whiteUsername;
        this.gameName = gameName;
        this.game = game;
    }

    public Game(){}

    public Game(String gameName) {
    }


    public int getGameID(){
        return gameID;
    }
    public void setGameID(int gameID){
        this.gameID = gameID;
    }
    public String getWhiteUsername(){
        return whiteUsername;
    }
    public void setWhiteUsername(String whiteUsername){
        this.whiteUsername = whiteUsername;
    }
    public String getBlackUsername(){
        return blackUsername;
    }
    public void setBlackUsername(String blackUsername){
        this.blackUsername = blackUsername;
    }
    public String getGameName(){
        return gameName;
    }
    public void setGameName(String gameName){
        this.gameName = gameName;
    }
    public ChessGame getChessGame(){
        return game;
    }
    public void setGame(ChessGame game){
        this.game = game;
    }
    public ChessGame getGame(){
        return game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return gameID == game.gameID && Objects.equals(whiteUsername, game.whiteUsername) && Objects.equals(blackUsername, game.blackUsername) && Objects.equals(gameName, game.gameName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameID, whiteUsername, blackUsername, gameName);
    }

    @Override
    public int compareTo(Object o) {
        return o.hashCode() - this.hashCode();
    }
}
