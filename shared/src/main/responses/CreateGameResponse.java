package responses;

/**
 * The CreateGameResponse class represents a response to a request to create a new game, providing a message and the game's unique identifier.
 */
public class CreateGameResponse {


    /**
     * Constructs a CreateGameResponse with the provided message and game identifier.
     *
     * @param message A message providing additional information about the result of the create game operation.
     * @param gameID  The unique identifier assigned to the newly created game.
     */
    public CreateGameResponse(String message, int gameID){
        this.message = message;
        this.gameID = gameID;
    }

    public CreateGameResponse(int gameID) {
        this.gameID = gameID;
    }

    public CreateGameResponse(String message){
        this.message = message;
    }

    public CreateGameResponse(){}



    public String getMessage() {
        return message;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID){
        this.gameID = gameID;
    }

    public void setMessage(String message){
        this.message = message;
    }

    /**
     * A message providing additional information about the result of the create game operation.
     */
    private String message;



    /**
     * The unique identifier assigned to the newly created game.
     */
    private Integer gameID;
}
