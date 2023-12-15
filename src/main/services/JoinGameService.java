package services;

import chess.ChessGame;
import responses.JoinGameResponse;
import requests.*;
import models.*;
import dataAccess.*;

/**
 * The JoinGameService class provides a service to join a game based on the provided request and returns a JoinGameResponse.
 */
public class JoinGameService {

    /**
     * Joins a game based on the provided request and provides a response indicating the result.
     *
     * @param request The JoinGameRequest specifying the player's color and game identifier.
     * @return A JoinGameResponse indicating the result of the join game operation.
     */
    public JoinGameResponse joinGame(JoinGameRequest request, String authToken) {

        try {

            GameDAO gameDAO = new GameDAO();
            AuthDAO auth = new AuthDAO();
            JoinGameResponse join = new JoinGameResponse();
            Game game = new GameDAO().readGame(request.getGameID());
            if (game != null){
                if(request.getPlayerColor() != null){
                    AuthToken authtoken = auth.readToken(authToken);
                    //find authToken. if null, return unauthorized, else replace auth token with result of find, username
                    if(authtoken != null) {
                        if (request.getPlayerColor() == ChessGame.TeamColor.WHITE && game.getWhiteUsername() != null) {
                            return new JoinGameResponse("Error: already taken");
                        }
                        if (request.getPlayerColor() == ChessGame.TeamColor.BLACK && game.getBlackUsername() != null) {
                            return new JoinGameResponse("Error: already taken");
                        }
                        gameDAO.claimSpot(request.getGameID(), request.getPlayerColor(), authtoken.getUsername());
                    }
                    else{
                        return new JoinGameResponse("Error: unauthorized");
                    }
                }
                else{
                    AuthToken authToken1 = auth.readToken(authToken);
                    if(authToken1 == null){
                        return new JoinGameResponse("Error: unauthorized");
                    }
                    return new JoinGameResponse();

                }
            }
            else{
                return new JoinGameResponse("Error: bad request");
            }
            return join;
        }
        catch (Exception e){
            e.printStackTrace();
            return new JoinGameResponse(e.getMessage());
        }
    }
}
