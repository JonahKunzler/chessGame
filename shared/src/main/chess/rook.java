package chess;

import java.util.Collection;
import java.util.*;

public class rook extends myChessPiece{

    private final int rowLength = 8;
    private final int columnLength = 8;


    public rook(myChessGame.TeamColor color) {
        super(color, PieceType.ROOK);
    }

    public Collection<ChessMove>pieceMoves(ChessBoard board, ChessPosition myPosition){
        List<ChessMove> moveList = new ArrayList<>();
                //WHITE TURN
        if(isWhite()){
            //Upwards Direction
            for (int i = myPosition.getRow() + 1; i < rowLength; i++){
                ChessPosition currentUpwards = new myChessPosition(i, myPosition.getColumn());
                var pieceAhead = board.getPiece(currentUpwards);
                if(board.getPiece(currentUpwards) == null && currentUpwards.getRow() <= 7){
                    moveList.add(new myChessMove(myPosition, currentUpwards));
                }
                else if(board.getPiece(currentUpwards) != null && pieceAhead.getTeamColor() == ChessGame.TeamColor.BLACK && currentUpwards.getRow() <= 7){
                    moveList.add(new myChessMove(myPosition, currentUpwards));
                    break;
                }
                else{
                    break;
                }
            }
            //Downwards Direction
            for (int i = myPosition.getRow() -1; i >= 0; i--){
                ChessPosition currentDownwards = new myChessPosition(i, myPosition.getColumn());
                var pieceBelow = board.getPiece(currentDownwards);
                if(board.getPiece(currentDownwards) == null && currentDownwards.getRow() >= 0){
                    moveList.add(new myChessMove(myPosition, currentDownwards));
                }
                else if(board.getPiece(currentDownwards) != null && pieceBelow.getTeamColor() == ChessGame.TeamColor.BLACK && currentDownwards.getRow() >= 0){
                    moveList.add(new myChessMove(myPosition, currentDownwards));
                    break;
                }
                else{
                    break;
                }
            }
            //Rightwards Direction
            for (int i = myPosition.getColumn() +1; i < columnLength; i++){
                ChessPosition currentRightwards = new myChessPosition(myPosition.getRow(), i);
                var pieceRight = board.getPiece(currentRightwards);
                if(board.getPiece(currentRightwards) == null && currentRightwards.getColumn() <= 7){
                    moveList.add(new myChessMove(myPosition, currentRightwards));
                }
                else if(board.getPiece(currentRightwards) != null && pieceRight.getTeamColor() == ChessGame.TeamColor.BLACK && currentRightwards.getColumn() <= 7){
                    moveList.add(new myChessMove(myPosition, currentRightwards));
                    break;
                }
                else{
                    break;
                }
            }
            //Leftwards Direction
            for (int i = myPosition.getColumn() -1; i >= 0; i--){
                ChessPosition currentLeftwards = new myChessPosition(myPosition.getRow(), i);
                var pieceLeft = board.getPiece(currentLeftwards);
                if(board.getPiece(currentLeftwards) == null && currentLeftwards.getColumn() >= 0){
                    moveList.add(new myChessMove(myPosition, currentLeftwards));
                }
                else if(board.getPiece(currentLeftwards) != null && pieceLeft.getTeamColor() == ChessGame.TeamColor.BLACK && currentLeftwards.getColumn() >= 0){
                    moveList.add(new myChessMove(myPosition, currentLeftwards));
                    break;
                }
                else{
                    break;
                }
            }
        }
        //BLACK TURN
        else {
            //Upwards direction
            for (int i = myPosition.getRow() + 1; i < rowLength; i++){
                ChessPosition currentUpwards2 = new myChessPosition(i, myPosition.getColumn());
                var pieceAhead2 = board.getPiece(currentUpwards2);
                if(board.getPiece(currentUpwards2) == null && currentUpwards2.getRow() <= 7){
                    moveList.add(new myChessMove(myPosition, currentUpwards2));
                }
                else if(board.getPiece(currentUpwards2) != null && pieceAhead2.getTeamColor() == ChessGame.TeamColor.WHITE && currentUpwards2.getRow() <= 7){
                    moveList.add(new myChessMove(myPosition, currentUpwards2));
                    break;
                }
                else{
                    break;
                }
            }
            //Downwards Direction
            for (int i = myPosition.getRow() -1; i >= 0; i--){
                ChessPosition currentDownwards2 = new myChessPosition(i, myPosition.getColumn());
                var pieceBelow2 = board.getPiece(currentDownwards2);
                if(board.getPiece(currentDownwards2) == null && currentDownwards2.getRow() >= 0){
                    moveList.add(new myChessMove(myPosition, currentDownwards2));
                }
                else if(board.getPiece(currentDownwards2) != null && pieceBelow2.getTeamColor() == ChessGame.TeamColor.WHITE && currentDownwards2.getRow() >= 0){
                    moveList.add(new myChessMove(myPosition, currentDownwards2));
                    break;
                }
                else{
                    break;
                }
            }
            //Rightwards Direction
            for (int i = myPosition.getColumn() +1; i < columnLength; i++){
                ChessPosition currentRightwards2 = new myChessPosition(myPosition.getRow(), i);
                var pieceRight2 = board.getPiece(currentRightwards2);
                if(board.getPiece(currentRightwards2) == null && currentRightwards2.getColumn() <= 7){
                    moveList.add(new myChessMove(myPosition, currentRightwards2));
                }
                else if(board.getPiece(currentRightwards2) != null && pieceRight2.getTeamColor() == ChessGame.TeamColor.WHITE && currentRightwards2.getColumn() <= 7){
                    moveList.add(new myChessMove(myPosition, currentRightwards2));
                    break;
                }
                else{
                    break;
                }
            }
            //Leftwards Direction
            for (int i = myPosition.getColumn() -1; i >= 0; i--){
                ChessPosition currentLeftwards2 = new myChessPosition(myPosition.getRow(), i);
                var pieceLeft2 = board.getPiece(currentLeftwards2);
                if(board.getPiece(currentLeftwards2) == null && currentLeftwards2.getColumn() >= 0){
                    moveList.add(new myChessMove(myPosition, currentLeftwards2));
                }
                else if(board.getPiece(currentLeftwards2) != null && pieceLeft2.getTeamColor() == ChessGame.TeamColor.WHITE && currentLeftwards2.getColumn() >= 0){
                    moveList.add(new myChessMove(myPosition, currentLeftwards2));
                    break;
                }
                else{
                    break;
                }
            }
        }
        return moveList;
    }

    private boolean isWhite() {
        return getTeamColor() == ChessGame.TeamColor.WHITE;
    }
}
