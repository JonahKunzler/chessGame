package services;

import responses.LoginResponse;
import requests.LoginRequest;
import  dataAccess.*;
import models.*;
import java.sql.*;

import java.util.UUID;

/**
 * The LoginService class provides a service to authenticate a user by handling a login request and returns a LoginResponse.
 */
public class LoginService {


    private UserDAO user = new UserDAO();
    private AuthDAO auth = new AuthDAO();
    private Database myDatabase = new Database();
    private Connection conn;

    public LoginService() {
        try {
            Connection conn = myDatabase.getConnection();
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Authenticates a user based on the provided login request and provides a response indicating the result.
     *
     * @param request The LoginRequest specifying the user's username and password for authentication.
     * @return A LoginResponse indicating the result of the login operation, including the user's username and an authentication token.
     */
    public LoginResponse login(LoginRequest request) {
        try{
            if(request.getPassword() != null && request.getUsername() != null){
                //UserDAO find user
                User myUser = user.readUser(request.getUsername());
                    if (myUser != null && myUser.getPassword().equals(request.getPassword())) {
                        String authToken = UUID.randomUUID().toString();
                        auth.createToken(new AuthToken(authToken, request.getUsername()).getAuthToken(), request.getUsername());
                        return new LoginResponse(request.getUsername(), authToken);
                    }
                    else{
                        return new LoginResponse("Error: unauthorized");
                    }
            }
            else{
                return new LoginResponse();
            }
        }
        catch(Exception e){
            return new LoginResponse(e.getMessage());
        }
    }
}
