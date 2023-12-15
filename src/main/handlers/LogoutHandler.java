package handlers;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.*;
import responses.*;
import services.LogoutService;

public class LogoutHandler implements Route{
    @Override
    public Object handle(Request request, Response response) throws Exception {
        String authToken = request.headers("Authorization");

        LogoutService service = new LogoutService();
        LogoutResponse logoutResponse = service.logout(authToken);


        if(logoutResponse.getMessage() == null){
            response.status(200);
        }

        else if(logoutResponse.getMessage().contains("Error: unauthorized")){
            response.status(401);
        }

        else{
            response.status(500);
        }

        return new Gson().toJson(logoutResponse);
    }
}
