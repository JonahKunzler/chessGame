package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class knight extends myChessPiece{
    public knight(myChessGame.TeamColor color) {
        super(color, PieceType.KNIGHT);
    }

    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition){
        List<ChessMove> moveList = new ArrayList<>();
        //WHITE TEAM
        if(isWhite()){
            //Two Up, One Left
            var upwardOneLeftPosition = new myChessPosition(myPosition.getRow()+2, myPosition.getColumn()-1);
            var upWardOneLeft = board.getPiece(upwardOneLeftPosition);
            if(upWardOneLeft == null || upWardOneLeft.getTeamColor() == ChessGame.TeamColor.BLACK){
                if(withinBoard(upwardOneLeftPosition)) {
                    moveList.add(new myChessMove(myPosition, upwardOneLeftPosition));
                }
            }
            //Two Up, One Right
            var upwardOneRightPosition = new myChessPosition(myPosition.getRow()+2, myPosition.getColumn()+1);
            var upWardOneRight = board.getPiece(upwardOneRightPosition);
            if(upWardOneRight == null || upWardOneRight.getTeamColor() == ChessGame.TeamColor.BLACK){
                if(withinBoard(upwardOneRightPosition)) {
                    moveList.add(new myChessMove(myPosition, upwardOneRightPosition));
                }
            }
            //Two Down, One Right
            var downwardOneRightPosition = new myChessPosition(myPosition.getRow()-2, myPosition.getColumn()+1);
            var downwardOneRight = board.getPiece(downwardOneRightPosition);
            if(downwardOneRight == null || downwardOneRight.getTeamColor() == ChessGame.TeamColor.BLACK){
                if(withinBoard(downwardOneRightPosition)) {
                    moveList.add(new myChessMove(myPosition, downwardOneRightPosition));
                }
            }
            //Two Down, One Left
            var downwardOneLeftPosition = new myChessPosition(myPosition.getRow()-2, myPosition.getColumn()-1);
            var downwardOneLeft = board.getPiece(downwardOneLeftPosition);
            if(downwardOneLeft == null || downwardOneLeft.getTeamColor() == ChessGame.TeamColor.BLACK){
                if(withinBoard(downwardOneLeftPosition)) {
                    moveList.add(new myChessMove(myPosition, downwardOneLeftPosition));
                }
            }
            //Two Right, One Down
            var rightwardOneDownPosition = new myChessPosition(myPosition.getRow()-1, myPosition.getColumn()+2);
            var rightwardOneDown = board.getPiece(rightwardOneDownPosition);
            if(rightwardOneDown == null || rightwardOneDown.getTeamColor() == ChessGame.TeamColor.BLACK){
                if(withinBoard(rightwardOneDownPosition)) {
                    moveList.add(new myChessMove(myPosition, rightwardOneDownPosition));
                }
            }
            //Two Right, One Up
            var rightwardOneUpPosition = new myChessPosition(myPosition.getRow()+1, myPosition.getColumn()+2);
            var rightwardOneUp = board.getPiece(rightwardOneUpPosition);
            if(rightwardOneUp == null || rightwardOneUp.getTeamColor() == ChessGame.TeamColor.BLACK){
                if(withinBoard(rightwardOneUpPosition)) {
                    moveList.add(new myChessMove(myPosition, rightwardOneUpPosition));
                }
            }

            //Two Left, One Down
            var leftwardOneDownPosition = new myChessPosition(myPosition.getRow()-1, myPosition.getColumn()-2);
            var leftwardOneDown = board.getPiece(leftwardOneDownPosition);
            if(leftwardOneDown == null || leftwardOneDown.getTeamColor() == ChessGame.TeamColor.BLACK){
                if(withinBoard(leftwardOneDownPosition)) {
                    moveList.add(new myChessMove(myPosition, leftwardOneDownPosition));
                }
            }
            //Two Left, One Up
            var leftwardOneUpPosition = new myChessPosition(myPosition.getRow()+1, myPosition.getColumn()-2);
            var leftwardOneUp = board.getPiece(leftwardOneUpPosition);
            if(leftwardOneUp == null || leftwardOneUp.getTeamColor() == ChessGame.TeamColor.BLACK){
                if(withinBoard(leftwardOneUpPosition)) {
                    moveList.add(new myChessMove(myPosition, leftwardOneUpPosition));
                }
            }
            //Bottom-Right Corner
            if(myPosition.getColumn() == 7 && myPosition.getRow() == 0){
                moveList.add(new myChessMove(myPosition, upwardOneLeftPosition));
                moveList.add(new myChessMove(myPosition, leftwardOneUpPosition));
            }
            //Bottom-Left Corner
            if(myPosition.getColumn() == 0 && myPosition.getRow() == 0){
                moveList.add(new myChessMove(myPosition, upwardOneRightPosition));
                moveList.add(new myChessMove(myPosition, rightwardOneUpPosition));
            }
            //Top-Left Corner
            if(myPosition.getColumn() == 0 && myPosition.getRow() == 7){
                moveList.add(new myChessMove(myPosition, rightwardOneDownPosition));
                moveList.add(new myChessMove(myPosition, downwardOneRightPosition));
            }
            //Top-Right Corner
            if(myPosition.getColumn() == 7 && myPosition.getRow() == 7){
                moveList.add(new myChessMove(myPosition, downwardOneLeftPosition));
                moveList.add(new myChessMove(myPosition, leftwardOneDownPosition));
            }
        }
        //TEAM BLACK
        else {
            //Two Up, One Left
            var upwardOneLeftPosition1 = new myChessPosition(myPosition.getRow()+2, myPosition.getColumn()-1);
            var upWardOneLeft1 = board.getPiece(upwardOneLeftPosition1);
            if(upWardOneLeft1 == null || upWardOneLeft1.getTeamColor() == ChessGame.TeamColor.WHITE){
                if(withinBoard(upwardOneLeftPosition1)) {
                    moveList.add(new myChessMove(myPosition, upwardOneLeftPosition1));
                }
            }
            //Two Up, One Right
            var upwardOneRightPosition1 = new myChessPosition(myPosition.getRow()+2, myPosition.getColumn()+1);
            var upWardOneRight1 = board.getPiece(upwardOneRightPosition1);
            if(upWardOneRight1 == null || upWardOneRight1.getTeamColor() == ChessGame.TeamColor.WHITE){
                if(withinBoard(upwardOneRightPosition1)) {
                    moveList.add(new myChessMove(myPosition, upwardOneRightPosition1));
                }
            }
            //Two Down, One Right
            var downwardOneRightPosition1 = new myChessPosition(myPosition.getRow()-2, myPosition.getColumn()+1);
            var downwardOneRight1 = board.getPiece(downwardOneRightPosition1);
            if(downwardOneRight1 == null || downwardOneRight1.getTeamColor() == ChessGame.TeamColor.WHITE){
                if(withinBoard(downwardOneRightPosition1)) {
                    moveList.add(new myChessMove(myPosition, downwardOneRightPosition1));
                }
            }
            //Two Down, One Left
            var downwardOneLeftPosition1 = new myChessPosition(myPosition.getRow()-2, myPosition.getColumn()-1);
            var downwardOneLeft1 = board.getPiece(downwardOneLeftPosition1);
            if(downwardOneLeft1 == null || downwardOneLeft1.getTeamColor() == ChessGame.TeamColor.WHITE){
                if(withinBoard(downwardOneLeftPosition1)) {
                    moveList.add(new myChessMove(myPosition, downwardOneLeftPosition1));
                }
            }
            //Two Right, One Down
            var rightwardOneDownPosition1 = new myChessPosition(myPosition.getRow()-1, myPosition.getColumn()+2);
            var rightwardOneDown1 = board.getPiece(rightwardOneDownPosition1);
            if(rightwardOneDown1 == null || rightwardOneDown1.getTeamColor() == ChessGame.TeamColor.WHITE){
                if(withinBoard(rightwardOneDownPosition1)) {
                    moveList.add(new myChessMove(myPosition, rightwardOneDownPosition1));
                }
            }
            //Two Right, One Up
            var rightwardOneUpPosition1 = new myChessPosition(myPosition.getRow()+1, myPosition.getColumn()+2);
            var rightwardOneUp1 = board.getPiece(rightwardOneUpPosition1);
            if(rightwardOneUp1 == null || rightwardOneUp1.getTeamColor() == ChessGame.TeamColor.WHITE){
                if(withinBoard(rightwardOneUpPosition1)) {
                    moveList.add(new myChessMove(myPosition, rightwardOneUpPosition1));
                }
            }
            //Two Left, One Down
            var leftwardOneDownPosition1 = new myChessPosition(myPosition.getRow()-1, myPosition.getColumn()-2);
            var leftwardOneDown1 = board.getPiece(leftwardOneDownPosition1);
            if(leftwardOneDown1 == null || leftwardOneDown1.getTeamColor() == ChessGame.TeamColor.WHITE){
                if(withinBoard(leftwardOneDownPosition1)) {
                    moveList.add(new myChessMove(myPosition, leftwardOneDownPosition1));
                }
            }
            //Two Left, One Up
            var leftwardOneUpPosition1 = new myChessPosition(myPosition.getRow()+1, myPosition.getColumn()-2);
            var leftwardOneUp1 = board.getPiece(leftwardOneUpPosition1);
            if(leftwardOneUp1 == null || leftwardOneUp1.getTeamColor() == ChessGame.TeamColor.WHITE){
                if(withinBoard(leftwardOneUpPosition1)) {
                    moveList.add(new myChessMove(myPosition, leftwardOneUpPosition1));
                }
            }
            //Bottom-Right Corner
            if(myPosition.getColumn() == 7 && myPosition.getRow() == 0){
                moveList.add(new myChessMove(myPosition, upwardOneLeftPosition1));
                moveList.add(new myChessMove(myPosition, leftwardOneUpPosition1));
            }
            //Bottom-Left Corner
            if(myPosition.getColumn() == 0 && myPosition.getRow() == 0){
                moveList.add(new myChessMove(myPosition, upwardOneRightPosition1));
                moveList.add(new myChessMove(myPosition, rightwardOneUpPosition1));
            }
            //Top-Left Corner
            if(myPosition.getColumn() == 0 && myPosition.getRow() == 7){
                moveList.add(new myChessMove(myPosition, rightwardOneDownPosition1));
                moveList.add(new myChessMove(myPosition, downwardOneRightPosition1));
            }
            //Top-Right Corner
            if(myPosition.getColumn() == 7 && myPosition.getRow() == 7){
                moveList.add(new myChessMove(myPosition, downwardOneLeftPosition1));
                moveList.add(new myChessMove(myPosition, leftwardOneDownPosition1));
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
