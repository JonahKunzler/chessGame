package responses;

import java.util.*;
import models.Game;

/**
 * The ListGamesResponse class represents a response to a request to retrieve a list of games, providing a message and a list of game objects.
 */
public class ListGamesResponse {


    /**
     * Constructs a ListGamesResponse with the provided message and a list of game objects.
     *
     * @param message A message providing additional information about the result of the list games operation.
     * @param games   A list of Game objects representing the games retrieved in response to the request.
     */
    public ListGamesResponse(String message, Collection<Game> games){
        this.message = message;
        this.games = games;
    }

    public ListGamesResponse() {}

    public ListGamesResponse(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * A message providing additional information about the result of the list games operation.
     */

    public Collection<Game> getGames() {
        return games;
    }

    public void setGames(Collection<Game> games) {
        this.games = games;
    }

    /**
     * A list of Game objects representing the games retrieved in response to the request.
     */
    private String message;
    private Collection<Game> games;
}
