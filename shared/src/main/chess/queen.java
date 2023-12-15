package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class queen extends myChessPiece {
    public queen(myChessGame.TeamColor color) {
        super(color, PieceType.QUEEN);
    }

    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        List<ChessMove> allMoves = new ArrayList<>();
        allMoves.addAll(new rook(getTeamColor()).pieceMoves(board, myPosition));
        allMoves.addAll(new bishop(getTeamColor()).pieceMoves(board, myPosition));
        return allMoves;
    }
}
