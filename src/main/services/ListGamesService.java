package services;

import responses.ListGamesResponse;
import dataAccess.*;

/**
 * The ListGamesService class provides a service to retrieve a list of games and returns a ListGamesResponse.
 */
public class ListGamesService {

    //body


    /**
     * Retrieves a list of games and provides a response indicating the result.
     *
     * @return A ListGamesResponse containing the list of games and additional information about the operation's result.
     */

    private GameDAO game = new GameDAO();
    private AuthDAO auth = new AuthDAO();
    public ListGamesResponse listGames(String authToken) {
        try{
            ListGamesResponse listGames = new ListGamesResponse();
            if(auth.readToken(authToken) != null){
                listGames.setGames(game.readGames());
                return listGames;
            }
            else{
                return new ListGamesResponse("Error: unauthorized");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return new ListGamesResponse(e.getMessage());
        }
    }

}
