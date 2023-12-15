package handlers;

import requests.CreateGameRequest;
import responses.CreateGameResponse;
import services.CreateGameService;
import spark.Request;
import spark.Response;
import spark.*;
import com.google.gson.*;


public class CreateGameHandler implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {
        CreateGameRequest creategame = new Gson().fromJson(request.body(), CreateGameRequest.class);

        String authToken = request.headers("Authorization");

        CreateGameService service = new CreateGameService();
        CreateGameResponse gameResponse = service.createGame(creategame, authToken);

        //success
        if(gameResponse.getMessage() == null){
            response.status(200);
        }
        //Error: bad request
        else if(gameResponse.getMessage().contains("Error: bad request")){
            response.status(400);
        }
        //Error: unauthorized
        else if(gameResponse.getMessage().contains("Error: unauthorized")){
            response.status(401);
        }
        //Error: description
        else{
            response.status(500);
        }

        return new Gson().toJson(gameResponse);

    }
}

//Get body
//Get auth token out of headers
//Call service
//return string
