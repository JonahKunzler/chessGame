package dataAccess;

import chess.ChessGame;
import chess.myChessGame;
import com.google.gson.*;

import java.lang.reflect.Type;

public class ChessGameAdapter implements JsonDeserializer<ChessGame> {
  @Override
  public ChessGame deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    return jsonDeserializationContext.deserialize(jsonElement, myChessGame.class);
  }
}
