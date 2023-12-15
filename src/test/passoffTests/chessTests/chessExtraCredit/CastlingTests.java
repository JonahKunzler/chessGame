package passoffTests.chessTests.chessExtraCredit;

import chess.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import passoffTests.TestFactory;

/**
 * Tests if the ChessGame implementation can handle Castling moves
 * Castling is a situational move the chess.king can make as it's first move. If one of the rooks has not yet moved
 * and there are no pieces between the chess.rook and the chess.king, and the path is "safe", the chess.king can castle. Castling is
 * performed by moving the chess.king 2 spaces towards the qualifying chess.rook, and the chess.rook "jumping" the chess.king to sit next
 * to the chess.king on the opposite side it was previously. A path is considered "safe" if 1: the chess.king is not in check
 * and 2: neither the space the chess.king moves past nor the space the chess.king ends up at can be reached by an opponents piece.
 */
public class CastlingTests {

    private ChessBoard board;
    private ChessPosition position;
    private ChessPiece king;

    @BeforeEach
    public void setup() {
        board = TestFactory.getNewBoard();
    }


    @Test
    @DisplayName("White Team Castle")
    public void castleWhite() {

        /*
        | | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		|R| | | |K| | |R|
         */

        //set up chess.king
        king = TestFactory.getNewPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING);
        position = TestFactory.getNewPosition(1, 5);
        board.addPiece(position, king);

        //add both rooks
        board.addPiece(TestFactory.getNewPosition(1, 1),
                TestFactory.getNewPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));
        board.addPiece(TestFactory.getNewPosition(1, 8),
                TestFactory.getNewPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));

        //set up game
        ChessGame game = TestFactory.getNewGame();
        game.setBoard(board);
        game.setTeamTurn(ChessGame.TeamColor.WHITE);

        //check that with nothing in way, chess.king can castle
        ChessMove queenSide = TestFactory.getNewMove(TestFactory.getNewPosition(1, 5),
                        TestFactory.getNewPosition(1, 3), null);
        ChessMove kingSide = TestFactory.getNewMove(TestFactory.getNewPosition(1, 5),
                        TestFactory.getNewPosition(1, 7), null);

        Assertions.assertTrue(game.validMoves(position).contains(queenSide),
                "ChessGame validMoves did not contain valid chess.queen-side castle move");
        Assertions.assertTrue(game.validMoves(position).contains(kingSide),
                "ChessGame validMoves did not contain valid chess.king-side castle move");

        //chess.queen side castle works correctly
        Assertions.assertDoesNotThrow(() -> game.makeMove(queenSide));
        Assertions.assertNull(game.getBoard().getPiece(TestFactory.getNewPosition(1, 5)),
                "After castling move, a piece is still present in the chess.king's initial position");
        Assertions.assertNull(game.getBoard().getPiece(TestFactory.getNewPosition(1, 1)),
                "After castling move, a piece is still present in the chess.rook's initial position");

        ChessPiece foundKing = game.getBoard().getPiece(TestFactory.getNewPosition(1, 3));
        Assertions.assertNotNull(foundKing, "After castling move, no piece found at chess.king's new position");
        Assertions.assertEquals(ChessPiece.PieceType.KING, foundKing.getPieceType(),
                "Found piece at chess.king's position is not a chess.king");
        Assertions.assertEquals(ChessGame.TeamColor.WHITE, foundKing.getTeamColor(),
                "Found piece at chess.king's position is the wrong team color");

        ChessPiece foundRook = game.getBoard().getPiece(TestFactory.getNewPosition(1, 4));
        Assertions.assertNotNull(foundRook, "After castling move, no piece found at chess.rook's new position");
        Assertions.assertEquals(ChessPiece.PieceType.ROOK, foundRook.getPieceType(),
                "Found piece at chess.rook's position is not a chess.rook");
        Assertions.assertEquals(ChessGame.TeamColor.WHITE, foundRook.getTeamColor(),
                "Found piece at chess.rook's position is the wrong team color");


        //reset board
        board = TestFactory.getNewBoard();
        board.addPiece(TestFactory.getNewPosition(1, 5),
                TestFactory.getNewPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING));
        board.addPiece(TestFactory.getNewPosition(1, 1),
                TestFactory.getNewPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));
        board.addPiece(TestFactory.getNewPosition(1, 8),
                TestFactory.getNewPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));
        game.setBoard(board);
        game.setTeamTurn(ChessGame.TeamColor.WHITE);

        //chess.king side castle works correctly
        Assertions.assertDoesNotThrow(() -> game.makeMove(kingSide));
        Assertions.assertNull(game.getBoard().getPiece(TestFactory.getNewPosition(1, 5)),
                "After castling move, a piece is still present in the chess.king's initial position");
        Assertions.assertNull(game.getBoard().getPiece(TestFactory.getNewPosition(1, 8)),
                "After castling move, a piece is still present in the chess.rook's initial position");

        foundKing = game.getBoard().getPiece(TestFactory.getNewPosition(1, 7));
        Assertions.assertNotNull(foundKing, "After castling move, no piece found at chess.king's new position");
        Assertions.assertEquals(ChessPiece.PieceType.KING, foundKing.getPieceType(),
                "Found piece at chess.king's position is not a chess.king");
        Assertions.assertEquals(ChessGame.TeamColor.WHITE, foundKing.getTeamColor(),
                "Found piece at chess.king's position is the wrong team color");

        foundRook = game.getBoard().getPiece(TestFactory.getNewPosition(1, 6));
        Assertions.assertNotNull(foundRook, "After castling move, no piece found at chess.rook's new position");
        Assertions.assertEquals(ChessPiece.PieceType.ROOK, foundRook.getPieceType(),
                "Found piece at chess.rook's position is not a chess.rook");
        Assertions.assertEquals(ChessGame.TeamColor.WHITE, foundRook.getTeamColor(),
                "Found piece at chess.rook's position is the wrong team color");
    }


    @Test
    @DisplayName("Black Team Castle")
    public void castleBlack() {

        /*
        |r| | | |k| | |r|
		| |p| | | | | |q|
		| | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		|R| | | | | | | |
         */

        //set up chess.king
        king = TestFactory.getNewPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING);
        position = TestFactory.getNewPosition(8, 5);
        board.addPiece(position, king);

        //set up rooks
        board.addPiece(TestFactory.getNewPosition(8, 1),
                TestFactory.getNewPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK));
        board.addPiece(TestFactory.getNewPosition(8, 8),
                TestFactory.getNewPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK));

        //extra decoy pieces
        board.addPiece(TestFactory.getNewPosition(7, 2),
                TestFactory.getNewPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));
        board.addPiece(TestFactory.getNewPosition(1, 1),
                TestFactory.getNewPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));
        board.addPiece(TestFactory.getNewPosition(7, 8),
                TestFactory.getNewPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN));

        //set up game
        ChessGame game = TestFactory.getNewGame();
        game.setBoard(board);
        game.setTeamTurn(ChessGame.TeamColor.BLACK);

        //check that with nothing in way, chess.king can castle
        ChessMove queenSide = TestFactory.getNewMove(TestFactory.getNewPosition(8, 5),
                TestFactory.getNewPosition(8, 3), null);
        ChessMove kingSide = TestFactory.getNewMove(TestFactory.getNewPosition(8, 5),
                TestFactory.getNewPosition(8, 7), null);

        Assertions.assertTrue(game.validMoves(position).contains(queenSide),
                "ChessGame validMoves did not contain valid chess.queen-side castle move");
        Assertions.assertTrue(game.validMoves(position).contains(kingSide),
                "ChessGame validMoves did not contain valid chess.king-side castle move");

        //chess.queen side castle works correctly
        Assertions.assertDoesNotThrow(() -> game.makeMove(queenSide));
        Assertions.assertNull(game.getBoard().getPiece(TestFactory.getNewPosition(8, 5)),
                "After castling move, a piece is still present in the chess.king's initial position");
        Assertions.assertNull(game.getBoard().getPiece(TestFactory.getNewPosition(8, 1)),
                "After castling move, a piece is still present in the chess.rook's initial position");

        ChessPiece foundKing = game.getBoard().getPiece(TestFactory.getNewPosition(8, 3));
        Assertions.assertNotNull(foundKing, "After castling move, no piece found at chess.king's new position");
        Assertions.assertEquals(ChessPiece.PieceType.KING, foundKing.getPieceType(),
                "Found piece at chess.king's position is not a chess.king");
        Assertions.assertEquals(ChessGame.TeamColor.BLACK, foundKing.getTeamColor(),
                "Found piece at chess.king's position is the wrong team color");

        ChessPiece foundRook = game.getBoard().getPiece(TestFactory.getNewPosition(8, 4));
        Assertions.assertNotNull(foundRook, "After castling move, no piece found at chess.rook's new position");
        Assertions.assertEquals(ChessPiece.PieceType.ROOK, foundRook.getPieceType(),
                "Found piece at chess.rook's position is not a chess.rook");
        Assertions.assertEquals(ChessGame.TeamColor.BLACK, foundRook.getTeamColor(),
                "Found piece at chess.rook's position is the wrong team color");


        //reset board
        board = TestFactory.getNewBoard();
        board.addPiece(TestFactory.getNewPosition(8, 5),
                TestFactory.getNewPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING));
        board.addPiece(TestFactory.getNewPosition(8, 1),
                TestFactory.getNewPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK));
        board.addPiece(TestFactory.getNewPosition(8, 8),
                TestFactory.getNewPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK));
        board.addPiece(TestFactory.getNewPosition(7, 2),
                TestFactory.getNewPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));
        board.addPiece(TestFactory.getNewPosition(1, 1),
                TestFactory.getNewPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));
        board.addPiece(TestFactory.getNewPosition(7, 8),
                TestFactory.getNewPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN));
        game.setBoard(board);
        game.setTeamTurn(ChessGame.TeamColor.BLACK);

        //chess.king side castle works correctly
        Assertions.assertDoesNotThrow(() -> game.makeMove(kingSide));
        Assertions.assertNull(game.getBoard().getPiece(TestFactory.getNewPosition(8, 5)),
                "After castling move, a piece is still present in the chess.king's initial position");
        Assertions.assertNull(game.getBoard().getPiece(TestFactory.getNewPosition(8, 8)),
                "After castling move, a piece is still present in the chess.rook's initial position");

        foundKing = game.getBoard().getPiece(TestFactory.getNewPosition(8, 7));
        Assertions.assertNotNull(foundKing, "After castling move, no piece found at chess.king's new position");
        Assertions.assertEquals(ChessPiece.PieceType.KING, foundKing.getPieceType(),
                "Found piece at chess.king's position is not a chess.king");
        Assertions.assertEquals(ChessGame.TeamColor.BLACK, foundKing.getTeamColor(),
                "Found piece at chess.king's position is the wrong team color");

        foundRook = game.getBoard().getPiece(TestFactory.getNewPosition(8, 6));
        Assertions.assertNotNull(foundRook, "After castling move, no piece found at chess.rook's new position");
        Assertions.assertEquals(ChessPiece.PieceType.ROOK, foundRook.getPieceType(),
                "Found piece at chess.rook's position is not a chess.rook");
        Assertions.assertEquals(ChessGame.TeamColor.BLACK, foundRook.getTeamColor(),
                "Found piece at chess.rook's position is the wrong team color");
    }


    @Test
    @DisplayName("Cannot Castle Through Pieces")
    public void castlingBlockedByTeam() {
        /*
        | | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		|R| |B| |K| |Q|R|
         */

        //set up chess.king
        king = TestFactory.getNewPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING);
        position = TestFactory.getNewPosition(1, 5);
        board.addPiece(position, king);

        //set up rooks
        board.addPiece(TestFactory.getNewPosition(1, 1),
                TestFactory.getNewPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));
        board.addPiece(TestFactory.getNewPosition(1, 8),
                TestFactory.getNewPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));

        //own team pieces in way
        board.addPiece(TestFactory.getNewPosition(1, 3),
                TestFactory.getNewPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP));
        board.addPiece(TestFactory.getNewPosition(1, 7),
                TestFactory.getNewPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN));

        //set up game
        ChessGame game = TestFactory.getNewGame();
        game.setBoard(board);

        //make sure chess.king cannot castle
        Assertions.assertFalse(game.validMoves(position).contains(
                TestFactory.getNewMove(TestFactory.getNewPosition(1, 5),
                        TestFactory.getNewPosition(1, 3), null)),
                "ChessGame validMoves contained an invalid castling move");

        Assertions.assertFalse(game.validMoves(position).contains(
                TestFactory.getNewMove(TestFactory.getNewPosition(1, 5),
                        TestFactory.getNewPosition(1, 7), null)),
                "ChessGame validMoves contained an invalid castling move");
    }


    @Test
    @DisplayName("Cannot Castle in Check")
    public void castlingBlockedByEnemy() {

        /*
        |r| | |B|k| | |r|
		| | | | | | | | |
		| | | | | |R| | |
		| | | | | | | | |
		| | | | | | | | |
		| |K| | | | | | |
		| | | | | | | | |
		| | | | | | | | |
         */

        //set up chess.king
        king = TestFactory.getNewPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING);
        position = TestFactory.getNewPosition(8, 5);
        board.addPiece(position, king);

        //set up rooks
        board.addPiece(TestFactory.getNewPosition(8, 1),
                TestFactory.getNewPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK));
        board.addPiece(TestFactory.getNewPosition(8, 8),
                TestFactory.getNewPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK));

        //enemy piece in way
        board.addPiece(TestFactory.getNewPosition(8, 4),
                TestFactory.getNewPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP));

        //enemy threatening in between position
        board.addPiece(TestFactory.getNewPosition(6, 6),
                TestFactory.getNewPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));

        //decoy enemy chess.king
        board.addPiece(TestFactory.getNewPosition(3, 2),
                TestFactory.getNewPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING));

        //set up game
        ChessGame game = TestFactory.getNewGame();
        game.setBoard(board);

        //make sure chess.king cannot castle on either side
        Assertions.assertFalse(game.validMoves(position).contains(
                TestFactory.getNewMove(TestFactory.getNewPosition(8, 5),
                        TestFactory.getNewPosition(8, 3), null)),
                "ChessGame validMoves contained an invalid castling move");

        Assertions.assertFalse(game.validMoves(position).contains(
                TestFactory.getNewMove(TestFactory.getNewPosition(8, 5),
                        TestFactory.getNewPosition(8, 7), null)),
                "ChessGame validMoves contained an invalid castling move");
    }


    @Test
    @DisplayName("Cannot Castle After Moving")
    public void noCastleAfterMove() throws InvalidMoveException {

        /*
        | | | | | | | | |
		|p| | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		|R| | | |K| | |R|
         */

        //set up chess.king
        king = TestFactory.getNewPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING);
        position = TestFactory.getNewPosition(1, 5);
        board.addPiece(position, king);

        //add both rooks
        board.addPiece(TestFactory.getNewPosition(1, 1),
                TestFactory.getNewPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));
        board.addPiece(TestFactory.getNewPosition(1, 8),
                TestFactory.getNewPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));

        //enemy chess.pawn for filler moves
        board.addPiece(TestFactory.getNewPosition(7, 1),
                TestFactory.getNewPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));

        //set up game
        ChessGame game = TestFactory.getNewGame();
        game.setBoard(board);
        game.setTeamTurn(ChessGame.TeamColor.WHITE);

        //move left chess.rook
        game.makeMove(TestFactory.getNewMove(TestFactory.getNewPosition(1, 1),
                TestFactory.getNewPosition(1, 4), null));
        game.makeMove(TestFactory.getNewMove(TestFactory.getNewPosition(7, 1),
                TestFactory.getNewPosition(6, 1), null));
        /*
        | | | | | | | | |
		| | | | | | | | |
		|p| | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		| | | |R|K| | |R|
         */

        //move chess.rook back to starting spot
        game.makeMove(TestFactory.getNewMove(TestFactory.getNewPosition(1, 4),
                TestFactory.getNewPosition(1, 1), null));
        /*
        | | | | | | | | |
		| | | | | | | | |
		|p| | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		|R| | | |K| | |R|
         */

        //make sure chess.king can't castle towards moved chess.rook, but still can to unmoved chess.rook
        Assertions.assertFalse(game.validMoves(position).contains(
                TestFactory.getNewMove(TestFactory.getNewPosition(1, 5),
                        TestFactory.getNewPosition(1, 3), null)),
                "ChessGame validMoves contained an invalid castling move");

        Assertions.assertTrue(game.validMoves(position).contains(
                TestFactory.getNewMove(TestFactory.getNewPosition(1, 5),
                        TestFactory.getNewPosition(1, 7), null)),
                "ChessGame validMoves contained an invalid castling move");

        //move chess.king
        game.makeMove(TestFactory.getNewMove(TestFactory.getNewPosition(6, 1),
                TestFactory.getNewPosition(5, 1), null));
        game.makeMove(TestFactory.getNewMove(position, TestFactory.getNewPosition(1, 6), null));
        /*
        | | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		|p| | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		|R| | | | |K| |R|
         */

        //move chess.king back to starting position
        game.makeMove(TestFactory.getNewMove(TestFactory.getNewPosition(5, 1),
                TestFactory.getNewPosition(4, 1), null));
        game.makeMove(TestFactory.getNewMove(TestFactory.getNewPosition(1, 6), position, null));
        /*
        | | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		|p| | | | | | | |
		| | | | | | | | |
		| | | | | | | | |
		|R| | | |K| | |R|
         */

        //make sure chess.king can't castle anymore
        Assertions.assertFalse(game.validMoves(position).contains(
                TestFactory.getNewMove(TestFactory.getNewPosition(1, 5),
                        TestFactory.getNewPosition(1, 3), null)),
                "ChessGame validMoves contained an invalid castling move");

        Assertions.assertFalse(game.validMoves(position).contains(
                TestFactory.getNewMove(TestFactory.getNewPosition(1, 5),
                        TestFactory.getNewPosition(1, 7), null)),
                "ChessGame validMoves contained an invalid castling move");
    }

}
