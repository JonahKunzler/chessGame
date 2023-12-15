package requests;

/**
 * The CreateGameRequest class represents a request to create a new game, providing the game name.
 */
public class CreateGameRequest {


    /**
     * Constructs a CreateGameRequest with the provided game name.
     *
     * @param gameName The name or title of the game to be created.
     */
    public CreateGameRequest(String gameName){
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    /**
     * The name or title of the game to be created.
     */
    private String gameName;
}
