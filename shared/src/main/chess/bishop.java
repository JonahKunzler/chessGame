package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class bishop extends myChessPiece{
    public bishop(myChessGame.TeamColor color) {
        super(color, PieceType.BISHOP);
    }

    private final int rowLength = 8;

    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition){
        List<ChessMove> moveList = new ArrayList<>();
        //WHITE TEAM
        if(isWhite()){
            for(int i = 1; i < rowLength; i++) {
                //up diagonal right
                ChessPosition current1 = new myChessPosition(myPosition.getRow() + i, myPosition.getColumn() + i);
                var upDiagonalRight = board.getPiece(current1);
                if (board.getPiece(current1) == null) {
                    if (withinBoard(current1)) {
                        moveList.add(new myChessMove(myPosition, current1));
                    }
                } else if (board.getPiece(current1) != null && upDiagonalRight.getTeamColor() == ChessGame.TeamColor.BLACK) {
                    if (withinBoard(current1)) {
                        moveList.add(new myChessMove(myPosition, current1));
                        break;
                    }
                } else {
                    break;
                }
            }
                //up diagonal left
            for(int i = 1; i < rowLength; i++) {
                ChessPosition current2 = new myChessPosition(myPosition.getRow() + i, myPosition.getColumn() - i);
                var upDiagonalLeft = board.getPiece(current2);
                if (board.getPiece(current2) == null) {
                    if (withinBoard(current2)) {
                        moveList.add(new myChessMove(myPosition, current2));
                    }
                } else if (board.getPiece(current2) != null && upDiagonalLeft.getTeamColor() == ChessGame.TeamColor.BLACK) {
                    if (withinBoard(current2)) {
                        moveList.add(new myChessMove(myPosition, current2));
                        break;
                    }
                } else {
                    break;
                }
            }
                //down diagonal right
            for(int i = 1; i < rowLength; i++) {
                ChessPosition current3 = new myChessPosition(myPosition.getRow() - i, myPosition.getColumn() + i);
                var downDiagonalRight = board.getPiece(current3);
                if (board.getPiece(current3) == null) {
                    if (withinBoard(current3)) {
                        moveList.add(new myChessMove(myPosition, current3));
                    }
                } else if (board.getPiece(current3) != null && downDiagonalRight.getTeamColor() == ChessGame.TeamColor.BLACK) {
                    if (withinBoard(current3)) {
                        moveList.add(new myChessMove(myPosition, current3));
                        break;
                    }
                } else {
                    break;
                }
            }
                //down diagonal left
            for(int i = 1; i < rowLength; i++) {
                ChessPosition current4 = new myChessPosition(myPosition.getRow() - i, myPosition.getColumn() - i);
                var downDiagonalLeft = board.getPiece(current4);
                if (board.getPiece(current4) == null) {
                    if (withinBoard(current4)) {
                        moveList.add(new myChessMove(myPosition, current4));
                    }
                } else if (board.getPiece(current4) != null && downDiagonalLeft.getTeamColor() == ChessGame.TeamColor.BLACK) {
                    if (withinBoard(current4)) {
                        moveList.add(new myChessMove(myPosition, current4));
                        break;
                    }
                } else {
                    break;
                }
            }

        }
        //BLACK TURN
        else {
            for(int i = 1; i < rowLength; i++) {
                //up diagonal right
                ChessPosition blackCurrent1 = new myChessPosition(myPosition.getRow() + i, myPosition.getColumn() + i);
                var upDiagonalRight1 = board.getPiece(blackCurrent1);
                if (board.getPiece(blackCurrent1) == null) {
                    if (withinBoard(blackCurrent1)) {
                        moveList.add(new myChessMove(myPosition, blackCurrent1));
                    }
                } else if (board.getPiece(blackCurrent1) != null && upDiagonalRight1.getTeamColor() == ChessGame.TeamColor.WHITE) {
                    if (withinBoard(blackCurrent1)) {
                        moveList.add(new myChessMove(myPosition, blackCurrent1));
                        break;
                    }
                } else {
                    break;
                }
            }
                //up diagonal left
            for(int i = 1; i < rowLength; i++) {
                ChessPosition blackCurrent2 = new myChessPosition(myPosition.getRow() + i, myPosition.getColumn() - i);
                var upDiagonalLeft1 = board.getPiece(blackCurrent2);
                if (board.getPiece(blackCurrent2) == null) {
                    if (withinBoard(blackCurrent2)) {
                        moveList.add(new myChessMove(myPosition, blackCurrent2));
                    }
                } else if (board.getPiece(blackCurrent2) != null && upDiagonalLeft1.getTeamColor() == ChessGame.TeamColor.WHITE) {
                    if (withinBoard(blackCurrent2)) {
                        moveList.add(new myChessMove(myPosition, blackCurrent2));
                        break;
                    }
                } else {
                    break;
                }
            }
                //down diagonal right
            for(int i = 1; i < rowLength; i++) {
                ChessPosition blackCurrent3 = new myChessPosition(myPosition.getRow() - i, myPosition.getColumn() + i);
                var downDiagonalRight1 = board.getPiece(blackCurrent3);
                if (board.getPiece(blackCurrent3) == null) {
                    if (withinBoard(blackCurrent3)) {
                        moveList.add(new myChessMove(myPosition, blackCurrent3));
                    }
                } else if (board.getPiece(blackCurrent3) != null && downDiagonalRight1.getTeamColor() == ChessGame.TeamColor.WHITE) {
                    if (withinBoard(blackCurrent3)) {
                        moveList.add(new myChessMove(myPosition, blackCurrent3));
                        break;
                    }
                } else {
                    break;
                }
            }
                //down diagonal left
            for(int i = 1; i < rowLength; i++) {
                ChessPosition blackCurrent4 = new myChessPosition(myPosition.getRow() - i, myPosition.getColumn() - i);
                var downDiagonalLeft1 = board.getPiece(blackCurrent4);
                if (board.getPiece(blackCurrent4) == null) {
                    if (withinBoard(blackCurrent4)) {
                        moveList.add(new myChessMove(myPosition, blackCurrent4));
                    }
                } else if (board.getPiece(blackCurrent4) != null && downDiagonalLeft1.getTeamColor() == ChessGame.TeamColor.WHITE) {
                    if (withinBoard(blackCurrent4)) {
                        moveList.add(new myChessMove(myPosition, blackCurrent4));
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return moveList;
    }

    private boolean isWhite() {
        return getTeamColor() == ChessGame.TeamColor.WHITE;
    }

    private boolean withinBoard(ChessPosition position){
        return (position.getRow() >= 0 && position.getRow() < 8) && (position.getColumn() >= 0 && position.getColumn() < 8);
    }
}
