package handlers;
import com.google.gson.Gson;
import requests.RegisterRequest;
import spark.Request;
import spark.Response;
import spark.*;
import services.*;
import responses.*;


public class RegisterHandler implements Route{

    @Override
    public Object handle(Request request, Response response) throws Exception {
        RegisterRequest registerRequest = new Gson().fromJson(request.body(), RegisterRequest.class);

        RegisterService service = new RegisterService();
        RegisterResponse registerResponse = service.register(registerRequest);

        if(registerResponse.getMessage() == null){
            response.status(200);
        }

        else if(registerResponse.getMessage().contains("Error: bad request")){
            response.status(400);
        }

        else if(registerResponse.getMessage().contains("Error: already taken")){
            response.status(403);
        }

        else{
            response.status(500);
        }


        return new Gson().toJson(registerResponse);
    }
}
