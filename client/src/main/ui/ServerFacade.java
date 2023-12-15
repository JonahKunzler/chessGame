package ui;
import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessPiece;
import chess.myChessGame;
import com.google.gson.*;
import dataAccess.ChessBoardAdapter;
import dataAccess.ChessGameAdapter;
import dataAccess.ChessPieceAdapter;
import requests.CreateGameRequest;
import requests.JoinGameRequest;
import requests.LoginRequest;
import requests.RegisterRequest;
import responses.*;

import java.io.IOException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerFacade {

    private final String serverUrl = "http://localhost:8080";

    public RegisterResponse register(RegisterRequest request) throws IOException{
        URL url = new URL(serverUrl+"/user");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setReadTimeout(5000);
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        connection.connect();

        try (var outputStream = connection.getOutputStream()) {
            outputStream.write(new Gson().toJson(request).getBytes());
        }

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            RegisterResponse response = null;
            try (InputStream respBody = connection.getInputStream()) {
                InputStreamReader inputStreamReader = new InputStreamReader(respBody);
                response = new Gson().fromJson(inputStreamReader, RegisterResponse.class);
            }
            return response;
        }

        else {
            RegisterResponse response = null;
            try (InputStream respBody = connection.getErrorStream()) {
                InputStreamReader inputStreamReader = new InputStreamReader(respBody);
                response = new Gson().fromJson(inputStreamReader, RegisterResponse.class);
            }
            return response;
        }

    }

    public LoginResponse login(LoginRequest request) throws IOException{
        URL url = new URL(serverUrl+"/session");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setReadTimeout(5000);
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        connection.connect();

        try (var outputStream = connection.getOutputStream()) {
            outputStream.write(new Gson().toJson(request).getBytes());
        }

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            LoginResponse response = null;
            try (InputStream respBody = connection.getInputStream()) {
                InputStreamReader inputStreamReader = new InputStreamReader(respBody);
                response = new Gson().fromJson(inputStreamReader, LoginResponse.class);
            }
            return response;
        }

        else {
            LoginResponse response = null;
            try (InputStream respBody = connection.getErrorStream()) {
                InputStreamReader inputStreamReader = new InputStreamReader(respBody);
                response = new Gson().fromJson(inputStreamReader, LoginResponse.class);
            }
            return response;
        }
    }

    public LogoutResponse logout() throws IOException{
        URL url = new URL(serverUrl+"/session");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(5000);
        connection.setRequestMethod("DELETE");
        connection.addRequestProperty("Authorization", DataCache.token);
        connection.connect();

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            LogoutResponse response = null;
            try (InputStream respBody = connection.getInputStream()) {
                InputStreamReader inputStreamReader = new InputStreamReader(respBody);
                response = new Gson().fromJson(inputStreamReader, LogoutResponse.class);
            }
            return response;
        }

        else {
            LogoutResponse response = null;
            try (InputStream respBody = connection.getErrorStream()) {
                InputStreamReader inputStreamReader = new InputStreamReader(respBody);
                response = new Gson().fromJson(inputStreamReader, LogoutResponse.class);
            }
            return response;
        }
    }

    public ListGamesResponse listgames() throws IOException{
        URL url = new URL(serverUrl+"/game");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setReadTimeout(5000);
        connection.setRequestMethod("GET");
        connection.addRequestProperty("Authorization", DataCache.token);
        connection.connect();

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            ListGamesResponse response = null;
            try (InputStream respBody = connection.getInputStream()) {
                InputStreamReader inputStreamReader = new InputStreamReader(respBody);
                //Build gson
                GsonBuilder builder = new GsonBuilder();
                builder.registerTypeAdapter(ChessGame.class, new ChessGameAdapter());
                builder.registerTypeAdapter(ChessBoard.class, new ChessBoardAdapter());
                response = builder.create().fromJson(inputStreamReader, ListGamesResponse.class);
            }
            return response;
        }

        else {
            ListGamesResponse response = null;
            try (InputStream respBody = connection.getErrorStream()) {
                InputStreamReader inputStreamReader = new InputStreamReader(respBody);
                response = new Gson().fromJson(inputStreamReader, ListGamesResponse.class);
            }
            return response;
        }
    }

    public CreateGameResponse createGame(CreateGameRequest request) throws IOException{
        URL url = new URL(serverUrl+"/game");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setReadTimeout(5000);
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.addRequestProperty("Authorization", DataCache.token);
        connection.connect();

        try (var outputStream = connection.getOutputStream()) {
            outputStream.write(new Gson().toJson(request).getBytes());
        }

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            CreateGameResponse response = null;
            try (InputStream respBody = connection.getInputStream()) {
                InputStreamReader inputStreamReader = new InputStreamReader(respBody);
                response = new Gson().fromJson(inputStreamReader, CreateGameResponse.class);
            }
            return response;
        }

        else {
            CreateGameResponse response = null;
            try (InputStream respBody = connection.getErrorStream()) {
                InputStreamReader inputStreamReader = new InputStreamReader(respBody);
                response = new Gson().fromJson(inputStreamReader, CreateGameResponse.class);
            }
            return response;
        }
    }

    public JoinGameResponse joinGame(JoinGameRequest request) throws IOException{
        URL url = new URL(serverUrl+"/game");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setReadTimeout(5000);
        connection.setRequestMethod("PUT");
        connection.addRequestProperty("Authorization", DataCache.token);
        connection.setDoOutput(true);

        connection.connect();

        try (var outputStream = connection.getOutputStream()) {
            outputStream.write(new Gson().toJson(request).getBytes());
        }

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            JoinGameResponse response = null;
            try (InputStream respBody = connection.getInputStream()) {
                InputStreamReader inputStreamReader = new InputStreamReader(respBody);
                response = new Gson().fromJson(inputStreamReader, JoinGameResponse.class);
            }
            return response;
        }
        else {
            JoinGameResponse response = null;
            try (InputStream respBody = connection.getErrorStream()) {
                InputStreamReader inputStreamReader = new InputStreamReader(respBody);
                response = new Gson().fromJson(inputStreamReader, JoinGameResponse.class);
            }
            return response;
        }
    }
}