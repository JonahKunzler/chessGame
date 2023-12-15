package ui;

import chess.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dataAccess.ChessGameAdapter;
import exception.ResponseException;
import models.Game;
import models.User;
import requests.CreateGameRequest;
import requests.JoinGameRequest;
import requests.LoginRequest;
import requests.RegisterRequest;
import responses.*;
import serverMessages.errorMessage;
import serverMessages.loadGameMessage;
import serverMessages.notificationMessage;
import webSocketClient.NotificationHandler;
import webSocketClient.WebSocketFacade;
import webSocketMessages.ServerMessage;

import javax.websocket.DeploymentException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;



public class main implements NotificationHandler {

    private static int gameID;
    private static ChessGame.TeamColor color = ChessGame.TeamColor.WHITE;
    private static myChessBoard myChessBoard=new myChessBoard(true);

    public static void main(String[] args) throws IOException, ResponseException, DeploymentException, URISyntaxException {
        ChessBoard myChessBoard = new myChessBoard();
        ServerFacade server = new ServerFacade();
        User u = new User();
        Scanner s = new Scanner(System.in);
        System.out.println("WELCOME TO THE GAME OF CHESS!\n");
        System.out.println("Here is a list of possible commands: ");
        System.out.println("\"register\" <Username><Password><Email> - in order to create account");
        System.out.println("\"quit\" - to exit the game");
        System.out.println("\"help\" - for list of possible commands");
        System.out.println("\"login\" <Username><Password> - to input registration information");
        while(true) {
            String input = s.nextLine();
            if (input.equals("help")) {
                System.out.println("\"register\" <Username><Password><Email> - in order to create account");
                System.out.println("\"quit\" - to exit the game");
                System.out.println("\"help\" - for list of possible commands");
                System.out.println("\"login\" <Username><Password> - to input registration information");
            }
            else if (input.equals("register")) {
                System.out.println("Create Username: ");
                String username = s.nextLine();
                System.out.println("Create Password: ");
                String password = s.nextLine();
                System.out.println("Enter Email: ");
                String email = s.nextLine();
                RegisterRequest request = new RegisterRequest(username, password, email);
                RegisterResponse r = server.register(request);
                if(r.getMessage() == null) {
                    DataCache.token = r.getAuthToken();
                    System.out.println("<Registration Complete>");
                    postlogin();
                }
                else{
                    System.out.println(r.getMessage());
                    System.out.println("Here is a list of possible commands: ");
                    System.out.println("\"register\" <Username><Password><Email> - in order to create account");
                    System.out.println("\"quit\" - to exit the game");
                    System.out.println("\"help\" - for list of possible commands");
                    System.out.println("\"login\" <Username><Password> - to input registration information");
                }
            }
            else if (input.equals("login")) {
                System.out.println("Enter Username: ");
                String username = s.nextLine();
                System.out.println("Enter Password: ");
                String password = s.nextLine();
                LoginRequest request = new LoginRequest(username, password);
                LoginResponse r = server.login(request);
                if(r.getMessage() == null){
                    DataCache.token = r.getAuthToken();
                    System.out.println("<Logged in>");
                    postlogin();
                }
                else{
                    System.out.println(r.getMessage());
                    System.out.println("Here is a list of possible commands: ");
                    System.out.println("\"register\" <Username><Password><Email> - in order to create account");
                    System.out.println("\"quit\" - to exit the game");
                    System.out.println("\"help\" - for list of possible commands");
                    System.out.println("\"login\" <Username><Password> - to input registration information");
                }
            }
            else if (input.equals("quit")){
                System.out.println("<Exited the Program>");
                break;
            }
            else{
                System.out.println("<Invalid Command> ");
                System.out.println("Here is a list of possible commands: ");
                System.out.println("\"register\" <Username><Password><Email> - in order to create account");
                System.out.println("\"quit\" - to exit the game");
                System.out.println("\"help\" - for list of possible commands");
                System.out.println("\"login\" <Username><Password> - to input registration information");
            }
        }
    }
    private static void postlogin() throws IOException, ResponseException, DeploymentException, URISyntaxException {
        ServerFacade server = new ServerFacade();
        myChessBoard chess = new myChessBoard(true);
        System.out.print("\n");
        System.out.println("\"create\" game - <Name>");
        System.out.println("\"list\" games");
        System.out.println("\"join\" Game - <ID> [<black>|<white>]");
        System.out.println("\"observe\" game - <ID>");
        System.out.println("\"logout\" - when finished");
        System.out.println("\"quit\" - to exit the game");
        System.out.println("\"help\" - for list of possible commands");
        Scanner s = new Scanner(System.in);
        String command = s.next();
        s.nextLine();
        switch(command){
            case "quit":
                System.out.println("<Exited the Program>");
                System.exit(0);
                break;
            case "help":
                postlogin();
                break;
            case "list":
                ListGamesResponse response = server.listgames();
                if(response.getMessage() == null){
                    System.out.println("List of Games:");
                    for(Game game: response.getGames()){
                        System.out.println(game.getGameName() + " - GameID: " + game.getGameID() + " White Username: " + game.getWhiteUsername() + " Black Username: " + game.getBlackUsername());
                    }
                    postlogin();
                    break;
                }
                else{
                    System.out.println(response.getMessage());
                    postlogin();
                    break;
                }

            case "join":
                boolean valid = false;
                while(!valid) {
                    System.out.println("Choose Team Color: \"black\" or \"white\"");
                    String myColor = s.nextLine();
                    if (myColor.equals("black")) {
                        color = ChessGame.TeamColor.BLACK;
                        valid = true;
                    }
                    if (myColor.equals("white")) {
                        color = ChessGame.TeamColor.WHITE;
                        valid = true;
                    }
                }
                int id;
                try {
                    System.out.println("Enter the Game ID:");
                    id=s.nextInt();
                    gameID = id;
                }
                catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid input.");
                    s.nextLine();
                    postlogin();
                    break;
                }
                JoinGameRequest request=new JoinGameRequest(color, id);
                //check response
                JoinGameResponse joinGameResponse=server.joinGame(request);
                if (joinGameResponse.getMessage() == null) {
                    System.out.println("<Joined Game>");
                    WebSocketFacade webSocketFacade = new WebSocketFacade("http://localhost:8080", notificationHandler);
                    webSocketFacade.joinPlayer(DataCache.token, color, id);
                    System.out.print("\n");
                    gamePlay();
                    break;
                }
                else {
                    System.out.println(joinGameResponse.getMessage());
                    postlogin();
                    break;
                }

            case "observe":
                int observeID;
                try {
                    System.out.println("Enter the Game ID:");
                    observeID=s.nextInt();
                }
                catch(java.util.InputMismatchException e){
                    System.out.println("Invalid input.");
                    s.nextLine();
                    postlogin();
                    break;
                }
                JoinGameRequest observeRequest = new JoinGameRequest(null, observeID);
                JoinGameResponse joinGameResponse1 = server.joinGame(observeRequest);
                if(joinGameResponse1.getMessage() == null) {
                    System.out.println("<Joined as Observer>");
                    color = ChessGame.TeamColor.WHITE;
                    WebSocketFacade webSocketFacade = new WebSocketFacade("http://localhost:8080", notificationHandler);
                    webSocketFacade.joinObserver(DataCache.token, observeID);
                    System.out.print("\n");
                    gamePlay();
                    break;
                }
                else{
                    System.out.println(joinGameResponse1.getMessage());
                    postlogin();
                    break;
                }
            case "create":
                System.out.println("Name of Game:");
                String gameName = s.nextLine();
                CreateGameRequest createGameRequest = new CreateGameRequest(gameName);
                CreateGameResponse r = server.createGame(createGameRequest);
                if(r.getMessage() == null) {
                    System.out.println("<Game Successfully Created>");
                    System.out.println("GameID: " + r.getGameID());
                    System.out.println();
                    postlogin();
                    break;
                }
                else{
                    System.out.println(r.getMessage());
                    postlogin();
                    break;
                }
            case "logout":
                LogoutResponse logoutResponse = server.logout();
                if(logoutResponse.getMessage() == null){
                    System.out.println("<Logged Out>");
                    System.out.print("\n");
                    System.out.println("Here is a list of possible commands: ");
                    System.out.println("\"register\" <Username><Password><Email> - in order to create account");
                    System.out.println("\"quit\" - to exit the game");
                    System.out.println("\"help\" - for list of possible commands");
                    System.out.println("\"login\" <Username><Password> - to input registration information");
                    break;
                }
                else{
                    System.out.println(logoutResponse.getMessage());
                    break;
                }
            default:
                System.out.println("<Invalid Command>");
                postlogin();
                break;
        }
    }

    private static void gamePlay() throws ResponseException, DeploymentException, IOException, URISyntaxException {
        System.out.print("\n");
        System.out.println("\"help\" - for list of possible commands");
        System.out.println("\"redraw\" chess board");
        System.out.println("\"leave\" game");
        System.out.println("\"make\" move");
        System.out.println("\"resign\" game");
        System.out.println("\"highlight\" legal moves");
        WebSocketFacade webSocketFacade = new WebSocketFacade("http://localhost:8080", notificationHandler);
        Scanner s = new Scanner(System.in);
        String command = s.next();
        s.nextLine();

        switch(command){
            case "help":
                gamePlay();
                break;
            case "redraw":
                PrintChessBoard.drawChessGame(myChessBoard, color);
                gamePlay();
                break;
            case "leave":
                webSocketFacade.leave(DataCache.token, gameID);
                postlogin();
                break;
            case "resign":
                webSocketFacade.resign(DataCache.token, gameID);
                postlogin();
                break;
            case "highlight":
                postlogin();
                break;
            case "make":
                String startPosition = "  ";
                if (startPosition.length() == 2) {
                    boolean validStart = false;
                    while (!validStart) {
                        System.out.println("Enter the starting position: (Ex. a1)");
                        startPosition = s.nextLine();
                        char startColumnLetter = startPosition.charAt(0);
                        int startColumnNumber = startColumnLetter - 'a';

                        int startRowNumber;
                        try {
                            startRowNumber = Character.getNumericValue(startPosition.charAt(1)) - 1;
                            switch(startColumnNumber){
                                case 0:
                                    startColumnNumber = 7;
                                    break;
                                case 1:
                                    startColumnNumber = 6;
                                    break;
                                case 2:
                                    startColumnNumber = 5;
                                    break;
                                case 3:
                                    startColumnNumber = 4;
                                    break;
                                case 4:
                                    startColumnNumber = 3;
                                    break;
                                case 5:
                                    startColumnNumber = 2;
                                    break;
                                case 6:
                                    startColumnNumber = 1;
                                    break;
                                case 7:
                                    startColumnNumber = 0;
                                    break;
                            }
                            if (startColumnNumber >= 0 && startColumnNumber <= 7 && startRowNumber >= 0 && startRowNumber <= 7) {
                                System.out.println("Enter the end position: (Ex. b3)");
                                String endPosition = s.nextLine();
                                if (endPosition.length() == 2) {
                                    char endColumnLetter = endPosition.charAt(0);
                                    int endColumnNumber = endColumnLetter - 'a';
                                    int endRowNumber;
                                    try {
                                        endRowNumber = Character.getNumericValue(endPosition.charAt(1)) -1;
                                        switch(endColumnNumber){
                                            case 0:
                                                endColumnNumber = 7;
                                                break;
                                            case 1:
                                                endColumnNumber = 6;
                                                break;
                                            case 2:
                                                endColumnNumber = 5;
                                                break;
                                            case 3:
                                                endColumnNumber = 4;
                                                break;
                                            case 4:
                                                endColumnNumber = 3;
                                                break;
                                            case 5:
                                                endColumnNumber = 2;
                                                break;
                                            case 6:
                                                endColumnNumber = 1;
                                                break;
                                            case 7:
                                                endColumnNumber = 0;
                                                break;
                                        }
                                        if (endColumnNumber >= 0 && endColumnNumber <= 7 && endRowNumber >= 0 && endRowNumber <= 7) {
                                            myChessPosition startingPosition = new myChessPosition(startRowNumber, startColumnNumber);
                                            myChessPosition endingPosition = new myChessPosition(endRowNumber, endColumnNumber);
                                            myChessMove move = new myChessMove(startingPosition, endingPosition);
                                            webSocketFacade.makeMove(DataCache.token, gameID, move);
                                        } else {
                                            System.out.println("Invalid end position. Column and row must be in range (a-h) and (1-8).");
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid end row number. Please enter a number (1-8).");
                                        gamePlay();
                                    }
                                } else {
                                    System.out.println("Invalid input for end position. Please enter a valid position. 1. (a-h) 2. (1-8)");
                                }

                                validStart = true;
                            } else {
                                System.out.println("Invalid starting position. Column and row must be in range (a-h) and (1-8).");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid starting row number. Please enter a number (1-8).");
                            gamePlay();
                        }
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid position. 1. (a-h) 2. (1-8)");
                }
                gamePlay();
                break;
            default:
                System.out.println("<Invalid Command>");
                gamePlay();
                break;
        }
    }

    private static NotificationHandler notificationHandler = new main();

    @Override
    public void notify(String message) {
        try{
            ServerMessage serverMessage = new Gson().fromJson(message, ServerMessage.class);
            switch(serverMessage.getServerMessageType()) {
                case ERROR:
                    errorMessage error_message=new Gson().fromJson(message, errorMessage.class);
                    System.out.println(error_message.getErrorMessage());
                    break;
                case NOTIFICATION:
                    notificationMessage notification_message=new Gson().fromJson(message, notificationMessage.class);
                    System.out.println(notification_message.getMessage());
                    break;
                case LOAD_GAME:
                    Gson gson=new GsonBuilder()
                        .registerTypeAdapter(ChessGame.class, new ChessGameAdapter())
                        .create();
                    loadGameMessage load_game=gson.fromJson(message, loadGameMessage.class);
                    myChessBoard =(chess.myChessBoard) load_game.getGame().getGame().getBoard();
                    PrintChessBoard.drawChessGame(myChessBoard, color);
                    System.out.print("\n");
                    break;
            }
        }
        catch(RuntimeException e){
            e.printStackTrace();
        }
    }

}
