package chess;

import java.util.*;

import java.util.ArrayList;
import java.util.Collection;

public class pawn extends myChessPiece {

    public pawn(myChessGame.TeamColor color) {
        super(color, PieceType.PAWN);
    }


    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        List<ChessMove> moveList = new ArrayList<>();

        int direction = (getTeamColor() == ChessGame.TeamColor.WHITE) ? 1 : -1;
        //White Team
        if (isWhite()) {
            if (myPosition.getRow() == 1) { //First turn
                var whiteMove1 = new myChessPosition(myPosition.getRow() + direction, myPosition.getColumn());
                var whiteMove2 = new myChessPosition(myPosition.getRow() + direction * 2, myPosition.getColumn());
                if(board.getPiece(whiteMove2) != null && board.getPiece(whiteMove2).getTeamColor() == ChessGame.TeamColor.BLACK){
                    moveList.add(new myChessMove(myPosition, whiteMove1));
                }
                if(board.getPiece(whiteMove2) == null && board.getPiece(whiteMove1) == null){
                    moveList.add(new myChessMove(myPosition, whiteMove1));
                    moveList.add(new myChessMove(myPosition, whiteMove2));
                }
            }
            //
            else {
                //Move forward one if square ahead isn't occupied
                var positionAhead1 = new myChessPosition(myPosition.getRow() + direction, myPosition.getColumn());
                if (board.getPiece(positionAhead1) == null && positionAhead1.getRow() < 7) {
                    moveList.add(new myChessMove(myPosition, positionAhead1));
                }
                //WHITE EDGE PROMOTION
                if (board.getPiece(positionAhead1) == null && positionAhead1.getRow() == 7) {
                    moveList.add(new myChessMove(myPosition, positionAhead1, PieceType.ROOK));
                    moveList.add(new myChessMove(myPosition, positionAhead1, PieceType.KNIGHT));
                    moveList.add(new myChessMove(myPosition, positionAhead1, PieceType.BISHOP));
                    moveList.add(new myChessMove(myPosition, positionAhead1, PieceType.QUEEN));
                }
                var positionDiagonalRight1 = new myChessPosition(myPosition.getRow() + direction, myPosition.getColumn() + 1);
                var positionDiagonalLeft1 = new myChessPosition(myPosition.getRow() + direction, myPosition.getColumn() - 1);
                var pieceRightDiagonal1 = board.getPiece(positionDiagonalRight1);
                var pieceLeftDiagonal1 = board.getPiece(positionDiagonalLeft1);
                //PROMOTE AND CAPTURE
                if (board.getPiece(positionDiagonalRight1) != null && pieceRightDiagonal1.getTeamColor() == ChessGame.TeamColor.BLACK && positionDiagonalRight1.getRow() == 7) {
                    moveList.add(new myChessMove(myPosition, positionDiagonalRight1, PieceType.QUEEN));
                    moveList.add(new myChessMove(myPosition, positionDiagonalRight1, PieceType.ROOK));
                    moveList.add(new myChessMove(myPosition, positionDiagonalRight1, PieceType.KNIGHT));
                    moveList.add(new myChessMove(myPosition, positionDiagonalRight1, PieceType.BISHOP));
                }
                //PROMOTE AND CAPTURE
                if (board.getPiece(positionDiagonalLeft1) != null && pieceLeftDiagonal1.getTeamColor() == ChessGame.TeamColor.BLACK && positionDiagonalLeft1.getRow() == 7) {
                    moveList.add(new myChessMove(myPosition, positionDiagonalLeft1, PieceType.KNIGHT));
                    moveList.add(new myChessMove(myPosition, positionDiagonalLeft1, PieceType.QUEEN));
                    moveList.add(new myChessMove(myPosition, positionDiagonalLeft1, PieceType.BISHOP));
                    moveList.add(new myChessMove(myPosition, positionDiagonalLeft1, PieceType.ROOK));
                }
            }
            var positionDiagonalRight1 = new myChessPosition(myPosition.getRow() + direction, myPosition.getColumn() + 1);
            var positionDiagonalLeft1 = new myChessPosition(myPosition.getRow() + direction, myPosition.getColumn() - 1);
            var pieceRightDiagonal1 = board.getPiece(positionDiagonalRight1);
            var pieceLeftDiagonal1 = board.getPiece(positionDiagonalLeft1);
            //Capture piece if diagonal right and color is opposite
            if (board.getPiece(positionDiagonalRight1) != null && pieceRightDiagonal1.getTeamColor() == ChessGame.TeamColor.BLACK && positionDiagonalRight1.getRow() < 7) {
                moveList.add(new myChessMove(myPosition, positionDiagonalRight1));
            }
            //Capture piece if diagonal left and color is opposite
            if (board.getPiece(positionDiagonalLeft1) != null && pieceLeftDiagonal1.getTeamColor() == ChessGame.TeamColor.BLACK && positionDiagonalLeft1.getRow() < 7) {
                moveList.add(new myChessMove(myPosition, positionDiagonalLeft1));
            }

        }
            //Black Team
        else {
            if (myPosition.getRow() == 6) { //First turn
                var move1 = new myChessPosition(myPosition.getRow() + direction, myPosition.getColumn());
                var move2 = new myChessPosition(myPosition.getRow() + direction * 2, myPosition.getColumn());
                if (board.getPiece(move2) != null && board.getPiece(move2).getTeamColor() == ChessGame.TeamColor.WHITE) {
                    moveList.add(new myChessMove(myPosition, move1));
                }
                if (board.getPiece(move2) == null && board.getPiece(move1) == null) {
                    moveList.add(new myChessMove(myPosition, move1));
                    moveList.add(new myChessMove(myPosition, move2));
                }
            } else {
                //Move forward one if square ahead isn't occupied
                var positionAhead = new myChessPosition(myPosition.getRow() + direction, myPosition.getColumn());
                if (board.getPiece(positionAhead) == null && positionAhead.getRow() > 0) {
                    moveList.add(new myChessMove(myPosition, positionAhead));
                }
                //BLACK EDGE PROMOTION
                if (board.getPiece(positionAhead) == null && positionAhead.getRow() == 0) {
                    moveList.add(new myChessMove(myPosition, positionAhead, PieceType.ROOK));
                    moveList.add(new myChessMove(myPosition, positionAhead, PieceType.KNIGHT));
                    moveList.add(new myChessMove(myPosition, positionAhead, PieceType.BISHOP));
                    moveList.add(new myChessMove(myPosition, positionAhead, PieceType.QUEEN));
                }
                var positionDiagonalRight = new myChessPosition(myPosition.getRow() + direction, myPosition.getColumn() + 1);
                var positionDiagonalLeft = new myChessPosition(myPosition.getRow() + direction, myPosition.getColumn() - 1);
                var pieceRightDiagonal = board.getPiece(positionDiagonalRight);
                var pieceLeftDiagonal = board.getPiece(positionDiagonalLeft);
                //Capture piece if diagonal right and color is opposite
                if (board.getPiece(positionDiagonalRight) != null && pieceRightDiagonal.getTeamColor() == ChessGame.TeamColor.WHITE && positionDiagonalRight.getRow() > 0) {
                    moveList.add(new myChessMove(myPosition, positionDiagonalRight));
                }
                //PROMOTE AND CAPTURE DIAGONAL RIGHT
                if (board.getPiece(positionDiagonalRight) != null && pieceRightDiagonal.getTeamColor() == ChessGame.TeamColor.WHITE && positionDiagonalRight.getRow() == 0) {
                    moveList.add(new myChessMove(myPosition, positionDiagonalRight, PieceType.ROOK));
                    moveList.add(new myChessMove(myPosition, positionDiagonalRight, PieceType.KNIGHT));
                    moveList.add(new myChessMove(myPosition, positionDiagonalRight, PieceType.BISHOP));
                    moveList.add(new myChessMove(myPosition, positionDiagonalRight, PieceType.QUEEN));
                }
                //Capture piece if diagonal left and color is opposite
                if (board.getPiece(positionDiagonalLeft) != null && pieceLeftDiagonal.getTeamColor() == ChessGame.TeamColor.WHITE && positionDiagonalLeft.getRow() > 0){
                    moveList.add(new myChessMove(myPosition, positionDiagonalLeft));
                }
                //PROMOTE AND CAPTURE DIAGONAL LEFT
                if (board.getPiece(positionDiagonalLeft) != null && pieceLeftDiagonal.getTeamColor() == ChessGame.TeamColor.WHITE && positionDiagonalLeft.getRow() == 0) {
                    moveList.add(new myChessMove(myPosition, positionDiagonalLeft, PieceType.ROOK));
                    moveList.add(new myChessMove(myPosition, positionDiagonalLeft, PieceType.KNIGHT));
                    moveList.add(new myChessMove(myPosition, positionDiagonalLeft, PieceType.BISHOP));
                    moveList.add(new myChessMove(myPosition, positionDiagonalLeft, PieceType.QUEEN));
                }
            }
        }
        return moveList;
    }

    private boolean isWhite() {
        return getTeamColor() == ChessGame.TeamColor.WHITE;
    }

}





