package ui;

import chess.*;
import com.google.gson.internal.bind.util.ISO8601Utils;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import static ui.EscapeSequences.*;

//

public class PrintChessBoard {

  private static final String EMPTY = "   ";
  private static myChessBoard board;

  public PrintChessBoard(myChessBoard board){
    this.board = board;
    board.resetBoard();
  }

  public static void drawChessGame(myChessBoard board, ChessGame.TeamColor color){
    if(color == ChessGame.TeamColor.BLACK) {
      for (int i=0; i < 10; i++) {
        for (int j=0; j < 10; j++) {
          //Make Header Grey
          if (i == 0 || i == 9 || j == 0 || j == 9) {
            System.out.print(SET_BG_COLOR_DARK_GREY);
            if ((i == 0 && j > 0 && j < 9) || (i == 9 && j > 0 && j < 9)) { //print letters going across
              char letter=(char) ('h' - j + 1);
              System.out.print(" " + letter + " ");
            } else if ((j == 0 && i > 0 && i < 9) || (j == 9 && i > 0 && i < 9)) { //print numbers
              System.out.print(" " + (i) + " ");
            } else {
              System.out.print(EMPTY);
            }
          } else {
            myChessPosition chessPosition=new myChessPosition(i - 1, j - 1);
            ChessPiece piece=board.getPiece(chessPosition);
            if (piece == null) {
              if ((i + j) % 2 == 0) {
                System.out.print(SET_BG_COLOR_RED);
                System.out.print(EMPTY);
              } else {
                System.out.print(SET_BG_COLOR_LIGHT_GREY);
                System.out.print(EMPTY);
              }
            }
            //1-8 Adding Pieces to Board
            if (piece != null) {
              if ((i + j) % 2 == 0) {
                System.out.print(SET_BG_COLOR_RED);
              } else {
                System.out.print(SET_BG_COLOR_LIGHT_GREY);
              }
              if (piece == board.getPiece(chessPosition)) {
                System.out.print(getPieceType(board.getPiece(chessPosition)));
              }
            }
          }
        }
        System.out.print(RESET_BG_COLOR);
        System.out.print("\n");
      }
    }
    else{
      for (int i = 9; i >= 0; i--) {
        for(int j = 9; j >= 0; j--){
          //Make Header Grey
          if(i == 0 || i == 9 || j == 0 || j == 9){
            System.out.print(SET_BG_COLOR_DARK_GREY);
            if ((i == 0 && j > 0 && j < 9) || (i == 9 && j > 0 && j < 9)) { //print letters going across
              char letter = (char) ('h' - j + 1);
              System.out.print(" " + letter + " ");
            }
            else if ((j == 0 && i > 0 && i < 9) || (j == 9 && i > 0 && i < 9)) { //print numbers
              System.out.print(" " + (i) + " ");
            }
            else {
              System.out.print(EMPTY);
            }
          }
          else{
            myChessPosition chessPosition = new myChessPosition(i-1, j-1);
            ChessPiece piece = board.getPiece(chessPosition);
            if(piece == null) {
              if ((i + j) % 2 == 0) {
                System.out.print(SET_BG_COLOR_RED);
                System.out.print(EMPTY);
              } else {
                System.out.print(SET_BG_COLOR_LIGHT_GREY);
                System.out.print(EMPTY);
              }
            }
            //1-8 Adding Pieces to Board
            if(piece != null) {
              if ((i + j) % 2 == 0) {
                System.out.print(SET_BG_COLOR_RED);
              }
              else {
                System.out.print(SET_BG_COLOR_LIGHT_GREY);
              }
              if (piece == board.getPiece(chessPosition)) {
                System.out.print(getPieceType(board.getPiece(chessPosition)));
              }
            }
          }
        }
        System.out.print(RESET_BG_COLOR);
        System.out.print("\n");
      }
    }
  }



  private static String getPieceType(ChessPiece piece){
    if(piece.getTeamColor() == ChessGame.TeamColor.WHITE) {
      if (piece.getPieceType() == ChessPiece.PieceType.PAWN) {
        return BLACK_PAWN;
      }
      else if (piece.getPieceType() == ChessPiece.PieceType.ROOK) {
        return BLACK_ROOK;
      }
      else if (piece.getPieceType() == ChessPiece.PieceType.KING) {
        return BLACK_KING;
      }
      else if (piece.getPieceType() == ChessPiece.PieceType.QUEEN) {
        return BLACK_QUEEN;
      }
      else if (piece.getPieceType() == ChessPiece.PieceType.BISHOP) {
        return BLACK_BISHOP;
      }
      else if (piece.getPieceType() == ChessPiece.PieceType.KNIGHT) {
        return BLACK_KNIGHT;
      }
    }
    else{
      if (piece.getPieceType() == ChessPiece.PieceType.PAWN) {
        return WHITE_PAWN;
      }
      else if (piece.getPieceType() == ChessPiece.PieceType.ROOK) {
        return WHITE_ROOK;
      }
      else if (piece.getPieceType() == ChessPiece.PieceType.KING) {
        return WHITE_KING;
      }
      else if (piece.getPieceType() == ChessPiece.PieceType.QUEEN) {
        return WHITE_QUEEN;
      }
      else if (piece.getPieceType() == ChessPiece.PieceType.BISHOP) {
        return WHITE_BISHOP;
      }
      else if (piece.getPieceType() == ChessPiece.PieceType.KNIGHT) {
        return WHITE_KNIGHT;
      }
    }
    return null;
  }









}
