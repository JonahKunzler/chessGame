package dataAccess;

import chess.*;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.*;

public class ChessPieceAdapter implements JsonDeserializer<ChessPiece> {
    @Override
    public ChessPiece deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String myPiece = jsonElement.getAsJsonObject().get("piece").getAsString();
        if(Objects.equals(myPiece, "ROOK")){
            return jsonDeserializationContext.deserialize(jsonElement, rook.class);
        }
        else if(Objects.equals(myPiece, "KING")){
            return jsonDeserializationContext.deserialize(jsonElement, king.class);
        }
        else if(Objects.equals(myPiece, "BISHOP")){
            return jsonDeserializationContext.deserialize(jsonElement, bishop.class);
        }
        else if(Objects.equals(myPiece, "QUEEN")){
            return jsonDeserializationContext.deserialize(jsonElement, queen.class);
        }
        else if(Objects.equals(myPiece, "KNIGHT")){
            return jsonDeserializationContext.deserialize(jsonElement, knight.class);
        }
        else if(Objects.equals(myPiece, "PAWN")){
            return jsonDeserializationContext.deserialize(jsonElement, pawn.class);
        }
        return null;
    }
}
