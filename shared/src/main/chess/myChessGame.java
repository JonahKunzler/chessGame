package chess;

import java.util.*;


import java.util.ArrayList;
import java.util.Collection;

public class myChessGame implements ChessGame {

    private myChessBoard board;
    private TeamColor team = TeamColor.WHITE;

    public void setOver(boolean isOver) {
        this.isOver=isOver;
    }

    private boolean isOver;


    public myChessGame(){
        this.board = new myChessBoard(true);
        isOver = false;

    }

    @Override
    public TeamColor getTeamTurn() {
        return team;
    }

    @Override
    public void setTeamTurn(TeamColor team) {
        this.team = team;
    }

    @Override
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        List<ChessMove> validMoves = new ArrayList<>();
        ChessPiece current = board.getPiece(startPosition);

        if (current == null) {
            return new ArrayList<>();
        }
        for (ChessMove move : current.pieceMoves(board, startPosition)) {
            ChessPiece tempStart = board.getPiece(move.getStartPosition());
            ChessPiece tempEnd = board.getPiece(move.getEndPosition());
            board.addPiece(move.getEndPosition(), tempStart);
            board.addPiece(move.getStartPosition(), null);

            if (!isInCheck(current.getTeamColor())) {
                validMoves.add(move);
            }
            board.addPiece(move.getStartPosition(), tempStart);
            board.addPiece(move.getEndPosition(), tempEnd);
        }
        return validMoves;
    }


    @Override
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPiece start = board.getPiece(move.getStartPosition());
        if(start == null){
            throw new InvalidMoveException("Empty ChessPiece");
        }

        if(board.getPiece(move.getStartPosition()).getTeamColor() != this.getTeamTurn()){
           throw new InvalidMoveException("Opposite team color");
        }

        Collection<ChessMove>legalMoves = validMoves(move.getStartPosition());
        if(legalMoves.contains(move)) {
            if(start != null) {
                if(board.getPiece(move.getStartPosition()).getTeamColor() == this.getTeamTurn()){
                if(move.getPromotionPiece() != null){
                  board.addPiece(move.getEndPosition(), getPromotionPieceType(board.getPiece(move.getStartPosition()).getTeamColor(), move.getPromotionPiece()));
                  board.addPiece(move.getStartPosition(), null);
                }
                else {
                    board.addPiece(move.getEndPosition(), start);
                    board.addPiece(move.getStartPosition(), null);
                }
                if (this.getTeamTurn() == TeamColor.WHITE) {
                    this.setTeamTurn(TeamColor.BLACK);
                } else {
                    this.setTeamTurn(TeamColor.WHITE);
                }
                }
            }
        }
        else{
            throw new InvalidMoveException("Invalid Move");
        }

    }

    private ChessPiece getPromotionPieceType(TeamColor color, ChessPiece.PieceType getPieceType) {
                return switch (getPieceType) {
                    case KING, PAWN -> null;
                    case QUEEN -> new queen(color);
                    case ROOK -> new rook(color);
                    case KNIGHT -> new knight(color);
                    case BISHOP -> new bishop(color);
                };
    }

    @Override
    public boolean isInCheck(TeamColor teamColor) {
        myChessPosition positionOfKing = null;

        for(int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                ChessPiece chesspiece = board.getPiece(new myChessPosition(row, column));
                if(chesspiece != null) {
                    if (chesspiece.getTeamColor() == teamColor) {
                        if (chesspiece.getPieceType() == ChessPiece.PieceType.KING) {
                            positionOfKing = new myChessPosition(row, column);
                            break;
                        }
                    }
                }
            }
        }
        if(positionOfKing == null){
            return false;
        }
        for(int row = 0; row < 8; row++){
            for(int column = 0; column < 8; column++){
                if((board.getPiece(new myChessPosition(row, column)) != null)){
                    if (board.getPiece(new myChessPosition(row, column)).getTeamColor() != teamColor) {
                        for (ChessMove move : board.getPiece(new myChessPosition(row, column)).pieceMoves(board, new myChessPosition(row, column))) {
                            if (move.getEndPosition().equals(positionOfKing)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean isInCheckmate(TeamColor teamColor) {
        myChessPosition positionOfKing = null;
        for(int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                ChessPiece chesspiece = board.getPiece(new myChessPosition(row, column));
                if(chesspiece != null) {
                    if (chesspiece.getTeamColor() == teamColor) {
                        if (chesspiece.getPieceType() == ChessPiece.PieceType.KING) {
                            positionOfKing = new myChessPosition(row, column);
                            break;
                        }
                    }
                }
            }
        }
        if(positionOfKing == null){
            return false;
        }

        if(!isInCheck(teamColor)){
            return false;
        }

        for(int row = 0; row < 8; row++){
            for(int column = 0; column < 8; column++){
                if((board.getPiece(new myChessPosition(row, column)) != null)){
                    if (board.getPiece(new myChessPosition(row, column)).getTeamColor() == teamColor) {
                        for(ChessMove move : board.getPiece(new myChessPosition(row, column)).pieceMoves(board, new myChessPosition(row, column))){
                            ChessPiece tempStart = board.getPiece(move.getStartPosition());
                            ChessPiece tempEnd = board.getPiece(move.getEndPosition());
                            board.addPiece(move.getEndPosition(), tempStart);
                            board.addPiece(move.getStartPosition(), null);


                            if(!isInCheck(teamColor)){
                                board.addPiece(move.getStartPosition(), tempStart);
                                board.addPiece(move.getEndPosition(), tempEnd);
                                return false;
                            }
                            board.addPiece(move.getStartPosition(), tempStart);
                            board.addPiece(move.getEndPosition(), tempEnd);
                        }
                    }
                }
            }
        }
        setOver(true);
        return true;
    }



    @Override
    public boolean isInStalemate(TeamColor teamColor) {
        for(int row = 0; row < 8; row++){
            for(int column = 0; column < 8; column++){
                if((board.getPiece(new myChessPosition(row, column)) != null)){
                    if (board.getPiece(new myChessPosition(row, column)).getTeamColor() == teamColor) {
                        for(ChessMove move : board.getPiece(new myChessPosition(row, column)).pieceMoves(board, new myChessPosition(row, column))){
                            Collection<ChessMove>legalMoves = validMoves(move.getStartPosition());
                            if(!isInCheck(teamColor)){
                                if(!legalMoves.isEmpty()){
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        setOver(true);
        return true;
    }

//    public boolean GameOver(ChessGame chessGame) {
//      return chessGame.isInCheckmate(chessGame.getTeamTurn()) || chessGame.isInStalemate(chessGame.getTeamTurn());
//    }

    @Override
    public void setBoard(ChessBoard board) {
        this.board = (myChessBoard) board;
    }

    @Override
    public ChessBoard getBoard() {
        return board;
    }

    @Override
    public void resign(){
        setOver(true);
    }

    @Override
    public boolean gameOver(){
        return isOver;
    }


    }

