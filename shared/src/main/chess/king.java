package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class king extends myChessPiece{
    public king(myChessGame.TeamColor color) {
        super(color, PieceType.KING);
    }

    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition){
        List<ChessMove> moveList = new ArrayList<>();
        //WHITE TEAM
        if(isWhite()){
            // 1 up
            var upPosition = new myChessPosition(myPosition.getRow()+1, myPosition.getColumn());
            var upPlace = board.getPiece(upPosition);
            if(upPlace == null){
                if(withinBoard(upPosition)) {
                    moveList.add(new myChessMove(myPosition, upPosition));
                }
            }
            if(upPlace != null && upPlace.getTeamColor() == ChessGame.TeamColor.BLACK){
                if(withinBoard(upPosition)) {
                    moveList.add(new myChessMove(myPosition, upPosition));
                }
            }
            // 1 down
            var downPosition = new myChessPosition(myPosition.getRow()-1, myPosition.getColumn());
            var downPlace = board.getPiece(downPosition);
            if(downPlace == null){
                if(withinBoard(downPosition)) {
                    moveList.add(new myChessMove(myPosition, downPosition));
                }
            }
            if(downPlace != null && downPlace.getTeamColor() == ChessGame.TeamColor.BLACK){
                if(withinBoard(downPosition)) {
                    moveList.add(new myChessMove(myPosition, downPosition));
                }
            }
            // 1 right
            var rightPosition = new myChessPosition(myPosition.getRow(), myPosition.getColumn()+1);
            var rightPlace = board.getPiece(rightPosition);
            if(rightPlace == null){
                if(withinBoard(rightPosition)) {
                    moveList.add(new myChessMove(myPosition, rightPosition));
                }
            }
            if(rightPlace != null && rightPlace.getTeamColor() == ChessGame.TeamColor.BLACK){
                if(withinBoard(rightPosition)) {
                    moveList.add(new myChessMove(myPosition, rightPosition));
                }
            }
            // 1 left
            var leftPosition = new myChessPosition(myPosition.getRow(), myPosition.getColumn()-1);
            var leftPlace = board.getPiece(leftPosition);
            if(leftPlace == null){
                if(withinBoard(leftPosition)) {
                    moveList.add(new myChessMove(myPosition, leftPosition));
                }
            }
            if(leftPlace != null && leftPlace.getTeamColor() == ChessGame.TeamColor.BLACK){
                if(withinBoard(leftPosition)) {
                    moveList.add(new myChessMove(myPosition, leftPosition));
                }
            }
            // 1 down, 1 left
            var downleftPosition = new myChessPosition(myPosition.getRow()-1, myPosition.getColumn()-1);
            var downleftPlace = board.getPiece(downleftPosition);
            if(downleftPlace == null){
                if(withinBoard(downleftPosition)) {
                    moveList.add(new myChessMove(myPosition, downleftPosition));
                }
            }
            if(downleftPlace != null && downleftPlace.getTeamColor() == ChessGame.TeamColor.BLACK){
                if(withinBoard(downleftPosition)) {
                    moveList.add(new myChessMove(myPosition, downleftPosition));
                }
            }
            // 1 down, 1 right
            var downrightPosition = new myChessPosition(myPosition.getRow()-1, myPosition.getColumn()+1);
            var downrightPlace = board.getPiece(downrightPosition);
            if(downrightPlace == null){
                if(withinBoard(downrightPosition)) {
                    moveList.add(new myChessMove(myPosition, downrightPosition));
                }
            }
            if(downrightPlace != null && downrightPlace.getTeamColor() == ChessGame.TeamColor.BLACK){
                if(withinBoard(downrightPosition)) {
                    moveList.add(new myChessMove(myPosition, downrightPosition));
                }
            }
            // 1 up, 1 right
            var uprightPosition = new myChessPosition(myPosition.getRow()+1, myPosition.getColumn()+1);
            var uprightPlace = board.getPiece(uprightPosition);
            if(uprightPlace == null){
                if(withinBoard(uprightPosition)) {
                    moveList.add(new myChessMove(myPosition, uprightPosition));
                }
            }
            if(uprightPlace != null && uprightPlace.getTeamColor() == ChessGame.TeamColor.BLACK){
                if(withinBoard(uprightPosition)) {
                    moveList.add(new myChessMove(myPosition, uprightPosition));
                }
            }
            // 1 up, 1 left
            var upleftPosition = new myChessPosition(myPosition.getRow()+1, myPosition.getColumn()-1);
            var upleftPlace = board.getPiece(upleftPosition);
            if(upleftPlace == null){
                if(withinBoard(upleftPosition)) {
                    moveList.add(new myChessMove(myPosition, upleftPosition));
                }
            }
            if(upleftPlace != null && upleftPlace.getTeamColor() == ChessGame.TeamColor.BLACK){
                if(withinBoard(upleftPosition)) {
                    moveList.add(new myChessMove(myPosition, upleftPosition));
                }
            }
        }
        //BLACK TEAM
        else {
            // 1 up
            var upPosition1 = new myChessPosition(myPosition.getRow()+1, myPosition.getColumn());
            var upPlace1 = board.getPiece(upPosition1);
            if(upPlace1 == null){
                if(withinBoard(upPosition1)) {
                    moveList.add(new myChessMove(myPosition, upPosition1));
                }
            }
            if(upPlace1 != null && upPlace1.getTeamColor() == ChessGame.TeamColor.WHITE){
                if(withinBoard(upPosition1)) {
                    moveList.add(new myChessMove(myPosition, upPosition1));
                }
            }
            // 1 down
            var downPosition1 = new myChessPosition(myPosition.getRow()-1, myPosition.getColumn());
            var downPlace1 = board.getPiece(downPosition1);
            if(downPlace1 == null){
                if(withinBoard(downPosition1)) {
                    moveList.add(new myChessMove(myPosition, downPosition1));
                }
            }
            if(downPlace1 != null && downPlace1.getTeamColor() == ChessGame.TeamColor.WHITE){
                if(withinBoard(downPosition1)) {
                    moveList.add(new myChessMove(myPosition, downPosition1));
                }
            }
            // 1 right
            var rightPosition1 = new myChessPosition(myPosition.getRow(), myPosition.getColumn()+1);
            var rightPlace1 = board.getPiece(rightPosition1);
            if(rightPlace1 == null){
                if(withinBoard(rightPosition1)) {
                    moveList.add(new myChessMove(myPosition, rightPosition1));
                }
            }
            if(rightPlace1 != null && rightPlace1.getTeamColor() == ChessGame.TeamColor.WHITE){
                if(withinBoard(rightPosition1)) {
                    moveList.add(new myChessMove(myPosition, rightPosition1));
                }
            }
            // 1 left
            var leftPosition1 = new myChessPosition(myPosition.getRow(), myPosition.getColumn()-1);
            var leftPlace1 = board.getPiece(leftPosition1);
            if(leftPlace1 == null){
                if(withinBoard(leftPosition1)) {
                    moveList.add(new myChessMove(myPosition, leftPosition1));
                }
            }
            if(leftPlace1 != null && leftPlace1.getTeamColor() == ChessGame.TeamColor.WHITE){
                if(withinBoard(leftPosition1)) {
                    moveList.add(new myChessMove(myPosition, leftPosition1));
                }
            }
            // 1 down, 1 left
            var downleftPosition1 = new myChessPosition(myPosition.getRow()-1, myPosition.getColumn()-1);
            var downleftPlace1 = board.getPiece(downleftPosition1);
            if(downleftPlace1 == null){
                if(withinBoard(downleftPosition1)) {
                    moveList.add(new myChessMove(myPosition, downleftPosition1));
                }
            }
            if(downleftPlace1 != null && downleftPlace1.getTeamColor() == ChessGame.TeamColor.WHITE){
                if(withinBoard(downleftPosition1)) {
                    moveList.add(new myChessMove(myPosition, downleftPosition1));
                }
            }
            // 1 down, 1 right
            var downrightPosition1 = new myChessPosition(myPosition.getRow()-1, myPosition.getColumn()+1);
            var downrightPlace1 = board.getPiece(downrightPosition1);
            if(downrightPlace1 == null){
                if(withinBoard(downrightPosition1)) {
                    moveList.add(new myChessMove(myPosition, downrightPosition1));
                }
            }
            if(downrightPlace1 != null && downrightPlace1.getTeamColor() == ChessGame.TeamColor.WHITE){
                if(withinBoard(downrightPosition1)) {
                    moveList.add(new myChessMove(myPosition, downrightPosition1));
                }
            }
            // 1 up, 1 right
            var uprightPosition1 = new myChessPosition(myPosition.getRow()+1, myPosition.getColumn()+1);
            var uprightPlace1 = board.getPiece(uprightPosition1);
            if(uprightPlace1 == null){
                if(withinBoard(uprightPosition1)) {
                    moveList.add(new myChessMove(myPosition, uprightPosition1));
                }
            }
            if(uprightPlace1 != null && uprightPlace1.getTeamColor() == ChessGame.TeamColor.WHITE){
                if(withinBoard(uprightPosition1)) {
                    moveList.add(new myChessMove(myPosition, uprightPosition1));
                }
            }
            // 1 up, 1 left
            var upleftPosition1 = new myChessPosition(myPosition.getRow()+1, myPosition.getColumn()-1);
            var upleftPlace1 = board.getPiece(upleftPosition1);
            if(upleftPlace1 == null){
                if(withinBoard(upleftPosition1)) {
                    moveList.add(new myChessMove(myPosition, upleftPosition1));
                }
            }
            if(upleftPlace1 != null && upleftPlace1.getTeamColor() == ChessGame.TeamColor.WHITE){
                if(withinBoard(upleftPosition1)) {
                    moveList.add(new myChessMove(myPosition, upleftPosition1));
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
