package requests;

import chess.ChessGame;

/**
 * The JoinGameRequest class represents a request to join a specific game, specifying the player's color and the game's unique identifier.
 */
public class JoinGameRequest {

    /**
     * Constructs a JoinGameRequest with the provided player's color and game identifier.
     *
     * @param playerColor The color assigned to the player (e.g., "white" or "black") for the game.
     * @param gameID      The unique identifier of the game to join.
     */
    public JoinGameRequest(ChessGame.TeamColor playerColor, int gameID){
        this.playerColor = playerColor;
        this.gameID = gameID;
    }

    public JoinGameRequest() {}

    public ChessGame.TeamColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(ChessGame.TeamColor playerColor) {
        this.playerColor = playerColor;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    /**
     * The color assigned to the player (e.g., "white" or "black") for the game.
     */
    private ChessGame.TeamColor playerColor;

    /**
     * The unique identifier of the game to join.
     */
    private int gameID;
}
