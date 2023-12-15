package server;

import handlers.*;
import spark.Spark;

public class server {

    public static void main(String[] args) {
        new server().run();
    }

    private void run() {
        Spark.port(8080);
        Spark.externalStaticFileLocation("web");
        Spark.webSocket("/connect", webSocket.class);

        //Clear application
        Spark.delete("/db", new ClearApplicationHandler());

        //Register
        Spark.post("/user", new RegisterHandler());

        //Login
        Spark.post("/session", new LoginHandler());

        //Logout
        Spark.delete("/session", new LogoutHandler());

        //List games
        Spark.get("/game", new ListGamesHandler());

        //Create game
        Spark.post("/game", new CreateGameHandler());

        //Join game
        Spark.put("/game", new JoinGameHandler());
    }
}
