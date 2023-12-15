package passoffTests;

import chess.*;

/**
 * Used for testing your code
 * Add in code using your classes for each method for each FIXME
 */
public class TestFactory {

    //Chess Functions
    //------------------------------------------------------------------------------------------------------------------
    public static ChessBoard getNewBoard(){
        return new myChessBoard();
    }

    public static ChessGame getNewGame(){

		return new myChessGame();
    }

    public static ChessPiece getNewPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type){
        if (type == ChessPiece.PieceType.PAWN) {
            return new pawn(pieceColor);
        }
        if (type == ChessPiece.PieceType.ROOK) {
            return new rook(pieceColor);
        }
        if (type == ChessPiece.PieceType.KNIGHT) {
            return new knight(pieceColor);
        }
        if (type == ChessPiece.PieceType.BISHOP) {
            return new bishop(pieceColor);
        }
        if (type == ChessPiece.PieceType.QUEEN) {
            return new queen(pieceColor);
        }
        if (type == ChessPiece.PieceType.KING) {
            return new king(pieceColor);
        }
        return null;
    }

    public static ChessPosition getNewPosition(Integer row, Integer col){
        // FIXME
		return new myChessPosition(row-1, col-1);
    }

    public static ChessMove getNewMove(ChessPosition startPosition, ChessPosition endPosition, ChessPiece.PieceType promotionPiece){
        // FIXME
		return new myChessMove(startPosition, endPosition, promotionPiece);
    }
    //------------------------------------------------------------------------------------------------------------------


    //Server API's
    //------------------------------------------------------------------------------------------------------------------
    public static String getServerPort(){
        return "8080";
    }
    //------------------------------------------------------------------------------------------------------------------


    //Websocket Tests
    //------------------------------------------------------------------------------------------------------------------
    public static Long getMessageTime(){
        /*
        Changing this will change how long tests will wait for the server.server to send messages.
        3000 Milliseconds (3 seconds) will be enough for most computers. Feel free to change as you see fit,
        just know increasing it can make tests take longer to run.
        (On the flip side, if you've got a good computer feel free to decrease it)
         */
        return 3000L; // 2 0's
    }
    //------------------------------------------------------------------------------------------------------------------
}
