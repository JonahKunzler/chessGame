package services;

import responses.LogoutResponse;
import dataAccess.*;

/**
 * The LogoutService class provides a service to handle user logout and returns a LogoutResponse.
 */
public class LogoutService {

    /**
     * Logs a user out and provides a response indicating the result.
     *
     * @return A LogoutResponse indicating the result of the logout operation, including additional information provided in the response message.
     */

    private AuthDAO auth = new AuthDAO();
    public LogoutResponse logout(String authToken) {
        try{
            if(auth.readToken(authToken) != null){
                auth.delete(authToken);
                return new LogoutResponse();
            }
            else{
                return new LogoutResponse("Error: unauthorized");
            }
        }
        catch(Exception e){
            return new LogoutResponse(e.getMessage());
        }
    }
}
