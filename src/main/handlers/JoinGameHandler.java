package handlers;
import com.google.gson.Gson;
import requests.JoinGameRequest;
import responses.JoinGameResponse;
import services.JoinGameService;
import spark.Request;
import spark.Response;
import spark.*;

public class JoinGameHandler implements Route{
    @Override
    public Object handle(Request request, Response response) throws Exception {

        JoinGameRequest joingame = new Gson().fromJson(request.body(), JoinGameRequest.class);

        String authToken = request.headers("Authorization");

        JoinGameService service = new JoinGameService();
        JoinGameResponse gameResponse = service.joinGame(joingame, authToken);

        if(gameResponse.getMessage() == null){
            response.status(200);
        }

        else if(gameResponse.getMessage().contains("Error: bad request")){
            response.status(400);
        }

        else if(gameResponse.getMessage().contains("Error: unauthorized")){
            response.status(401);
        }

        else if(gameResponse.getMessage().contains("Error: already taken")){
            response.status(403);
        }

        else{
            response.status(500);
        }

        return new Gson().toJson(gameResponse);
    }
}
