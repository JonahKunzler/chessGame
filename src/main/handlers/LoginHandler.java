package handlers;
import com.google.gson.Gson;
import requests.LoginRequest;
import services.LoginService;
import responses.LoginResponse;
import spark.Request;
import spark.Response;
import spark.*;

public class LoginHandler implements Route{


    @Override
    public Object handle(Request request, Response response) throws Exception {
        LoginRequest login = new Gson().fromJson(request.body(), LoginRequest.class);

        LoginService service = new LoginService();
        LoginResponse loginResponse = service.login(login);

        if(loginResponse.getMessage() == null){
            response.status(200);
        }

        else if(loginResponse.getMessage().contains("Error: unauthorized")){
            response.status(401);
        }

        else{
            response.status(500);
        }


        return new Gson().toJson(loginResponse);
    }
}
