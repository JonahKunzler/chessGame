package server;

import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import serverMessages.notificationMessage;
import webSocketMessages.ServerMessage;

import javax.management.Notification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionManager {
  public final ConcurrentHashMap<Integer, Set<Connection>> connectionList = new ConcurrentHashMap<>();
  public void add(Integer gameID, String authToken, Session session) {
    Connection connection = new Connection(authToken, session);
    connectionList.computeIfAbsent(gameID, k -> new HashSet<>()).add(connection);
  }



  public void remove(String username) {
    for (Set<Connection> connections : connectionList.values()) {
      connections.removeIf(connection -> connection.getUsername().equals(username));
    }
  }
}


