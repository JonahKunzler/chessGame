package chess;

import java.util.Objects;

public class myChessMove implements ChessMove {
    private myChessPosition startingPosition;
    private myChessPosition endingPosition;
    private ChessPiece.PieceType promotionPiece;


    public myChessMove(ChessPosition startingPosition, ChessPosition endingPosition){
        this.startingPosition =(myChessPosition) startingPosition;
        this.endingPosition =(myChessPosition) endingPosition;
    }
    //chess.myChessMove constructor for all three
    public myChessMove(ChessPosition startingPosition, ChessPosition endingPosition, ChessPiece.PieceType promotionPiece){
        this.startingPosition =(myChessPosition) startingPosition;
        this.endingPosition =(myChessPosition) endingPosition;
        this.promotionPiece = promotionPiece;
    }

    @Override
    public ChessPosition getStartPosition() {
        return startingPosition;
    }

    @Override
    public ChessPosition getEndPosition() {
        return endingPosition;
    }

    @Override
    public String toString(){
        return startingPosition.toString() + " " + endingPosition.toString();
    }

    @Override
    public ChessPiece.PieceType getPromotionPiece() {
        return promotionPiece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        myChessMove that = (myChessMove) o;
        return Objects.equals(startingPosition, that.startingPosition) && Objects.equals(endingPosition, that.endingPosition) && promotionPiece == that.promotionPiece;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startingPosition, endingPosition, promotionPiece);
    }
}
