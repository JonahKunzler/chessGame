package handlers;
import com.google.gson.Gson;
import responses.ClearApplicationResponse;
import services.ClearApplicationService;
import spark.Request;
import spark.Response;
import spark.*;
import java.io.*;

public class ClearApplicationHandler implements Route{
    @Override
    public Object handle(Request request, Response response) throws IOException {


        ClearApplicationService service = new ClearApplicationService();
        ClearApplicationResponse clearResponse = service.clearApplication();


        if(clearResponse.getMessage() == null){
            response.status(200);
        }
        else{
            response.status(500);
        }


        return new Gson().toJson(clearResponse);
    }
}
