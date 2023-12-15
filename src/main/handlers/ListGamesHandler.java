package handlers;
import com.google.gson.Gson;
import responses.ListGamesResponse;
import services.ListGamesService;
import spark.Request;
import spark.Response;
import spark.*;

public class ListGamesHandler implements Route{
    @Override
    public Object handle(Request request, Response response) throws Exception {
        String authToken = request.headers("Authorization");

        ListGamesService service = new ListGamesService();
        ListGamesResponse listGamesResponse = service.listGames(authToken);

        if(listGamesResponse.getMessage() == null){
            response.status(200);
        }

        else if(listGamesResponse.getMessage().contains("Error: unauthorized")){
            response.status(401);
        }

        else{
            response.status(500);
        }



        return new Gson().toJson(listGamesResponse);
    }
}
