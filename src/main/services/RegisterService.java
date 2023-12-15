package services;

import responses.RegisterResponse;
import requests.RegisterRequest;
import dataAccess.*;
import models.*;


import java.util.UUID;

/**
 * The RegisterService class provides a service to handle user registration and returns a RegisterResponse.
 */
public class RegisterService {

    /**
     * Registers a new user based on the provided registration request and provides a response indicating the result.
     *
     * @param request The RegisterRequest specifying the user's desired username, password, and email for registration.
     * @return A RegisterResponse indicating the result of the registration operation, including the user's username, an authentication token, and an additional message.
     */
    private AuthDAO auth = new AuthDAO();
    private UserDAO user = new UserDAO();


    public RegisterResponse register(RegisterRequest request) {
        try {
            if(request.getEmail() == null || request.getPassword() == null || request.getUsername() == null){
                return new RegisterResponse("Error: bad request");
            }
            if(user.readUser(request.getUsername()) != null){
                return new RegisterResponse("Error: already taken");
            }
            else {
                User myUser = new User(request.getUsername(), request.getPassword(), request.getEmail());
                user.createUser(myUser.getUsername(), myUser.getPassword(), myUser.getEmail());
                String token = UUID.randomUUID().toString();
                auth.createToken(new AuthToken(token, request.getUsername()).getAuthToken(), request.getUsername());
                return new RegisterResponse(request.getUsername(), token);
            }
        }
        catch(Exception e){
            return new RegisterResponse(e.getMessage());
        }

    }

}
