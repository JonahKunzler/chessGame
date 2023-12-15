package chess;

public class myChessBoard implements ChessBoard {

    private myChessPiece[][] chessPlaces;



    public myChessBoard(){
        chessPlaces = new myChessPiece[8][8];
//        resetBoard(); // Quick fix
    }

    public myChessBoard(boolean reset){
        chessPlaces = new myChessPiece[8][8];
        resetBoard(); // Quick fix
    }


    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */

    @Override
    public void addPiece(ChessPosition position, ChessPiece piece) {
            int row = position.getRow();
            int column = position.getColumn();
            if (withinBoard(position)){
                chessPlaces[row][column] = (myChessPiece) piece;
            }
    }

    private boolean withinBoard(ChessPosition position){
        return (position.getRow() >= 0 && position.getRow() < 8) && (position.getColumn() >= 0 && position.getColumn() < 8);
    }

    @Override
    public ChessPiece getPiece(ChessPosition position) {
        int row = position.getRow();
        int column = position.getColumn();
        if (withinBoard(position)){
            return chessPlaces[row][column];
        }
        return null;
    }

    @Override
    public void resetBoard() {
        //double for loop
        for (int row = 0; row <= 7; row++){
            for(int column = 0; column <= 7; column++){
                //ADDING PAWNS TO BOARD
                if(row == 6){
                    chessPlaces[row][column] = new pawn(ChessGame.TeamColor.BLACK);
                }
                else if(row == 1){
                    chessPlaces[row][column] = new pawn(ChessGame.TeamColor.WHITE);
                }
                //ADDING ROOKS TO BOARD
                else if(row == 7 && column == 0){
                    chessPlaces[row][column] = new rook(ChessGame.TeamColor.BLACK);
                }
                else if(row == 0 && column == 7){
                    chessPlaces[row][column] = new rook(ChessGame.TeamColor.WHITE);
                }
                else if(row == 7 && column == 7){
                    chessPlaces[row][column] = new rook(ChessGame.TeamColor.BLACK);
                }
                else if(row == 0 && column == 0){
                    chessPlaces[row][column] = new rook(ChessGame.TeamColor.WHITE);
                }
                //ADDING KINGS TO BOARD
                else if(row == 7 && column == 4){
                    chessPlaces[row][column] = new king(ChessGame.TeamColor.BLACK);
                }
                else if(row == 0 && column == 4){
                    chessPlaces[row][column] = new king(ChessGame.TeamColor.WHITE);
                }
                //ADDING QUEENS TO BOARD
                else if(row == 7 && column == 3){
                    chessPlaces[row][column] = new queen(ChessGame.TeamColor.BLACK);
                }
                else if(row == 0 && column == 3){
                    chessPlaces[row][column] = new queen(ChessGame.TeamColor.WHITE);
                }
                //ADDING BISHOPS TO BOARD
                else if(row == 7 && column == 2){
                    chessPlaces[row][column] = new bishop(ChessGame.TeamColor.BLACK);
                }
                else if(row == 7 && column == 5){
                    chessPlaces[row][column] = new bishop(ChessGame.TeamColor.BLACK);
                }
                else if(row == 0 && column == 2){
                    chessPlaces[row][column] = new bishop(ChessGame.TeamColor.WHITE);
                }
                else if(row == 0 && column == 5){
                    chessPlaces[row][column] = new bishop(ChessGame.TeamColor.WHITE);
                }
                //ADDING KNIGHTS TO BOARD
                else if(row == 7 && column == 1){
                    chessPlaces[row][column] = new knight(ChessGame.TeamColor.BLACK);
                }
                else if(row == 7 && column == 6){
                    chessPlaces[row][column] = new knight(ChessGame.TeamColor.BLACK);
                }
                else if(row == 0 && column == 1){
                    chessPlaces[row][column] = new knight(ChessGame.TeamColor.WHITE);
                }
                else if(row == 0 && column == 6){
                    chessPlaces[row][column] = new knight(ChessGame.TeamColor.WHITE);
                }

                else{
                    chessPlaces[row][column] = null;
                }
            }
        }
    }
}


