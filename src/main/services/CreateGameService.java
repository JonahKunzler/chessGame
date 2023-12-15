package services;

import models.Game;
import responses.CreateGameResponse;
import requests.CreateGameRequest;
import dataAccess.*;

/**
 * The CreateGameService class provides a service to create a new game based on the provided request and returns a CreateGameResponse.
 */
public class CreateGameService {

    /**
     * Creates a new game based on the provided request and provides a response indicating the result.
     *
     * @param request The CreateGameRequest specifying the game name.
     * @return A CreateGameResponse indicating the result of the create game operation.
     */

    private AuthDAO auth = new AuthDAO();
    private GameDAO game = new GameDAO();


    public CreateGameResponse createGame(CreateGameRequest request, String authToken) {

        try {
            if(auth.readToken(authToken) == null) {
                return new CreateGameResponse("Error: unauthorized");
            }

            if(request.getGameName() == null){
                return new CreateGameResponse("Error: bad request");
            }

                Game g = new Game();
                g.setGameName(request.getGameName());

                int id = game.createGame(request.getGameName());
                g.setGameID(id);
                return new CreateGameResponse(id);

        }
        catch (DataAccessException e) {
            return new CreateGameResponse(e.getMessage());
        }
    }
}
