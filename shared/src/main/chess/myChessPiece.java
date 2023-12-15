package chess;

import java.util.Collection;

public class myChessPiece implements ChessPiece {

    private final ChessGame.TeamColor color;
    private final PieceType piece;

    public myChessPiece(ChessGame.TeamColor color, PieceType piece){
        this.color = color;
        this.piece = piece;
    }
    @Override
    public ChessGame.TeamColor getTeamColor() {
        return color;
    }

    @Override
    public PieceType getPieceType() {
        return piece;
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        return null;
    }
}
