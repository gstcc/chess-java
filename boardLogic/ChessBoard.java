package boardLogic;

import pieces.*;

public class ChessBoard {
    private ChessPiece[][] board;
    private King[] kings;
    private Move move;
    String playerTurn;

    public ChessBoard() {
        this.board = new ChessPiece[8][8];
        this.kings = new King[2];
        this.kings[0] = new King("white", 0, 4);
        this.kings[1] = new King("black", 7, 4);
        initializeBoard();
        this.move = new Move(this);
        this.playerTurn = "white";
    }

    public King[] getKings() {
        return kings;
    }

    public ChessPiece[][] getBoard() {
        return board;
    }

    private void initializeBoard() {
    // Place pawns for both players
        for (int col = 0; col < 8; col++) {
            board[1][col] = new Pawn("white", 1, col);
            board[6][col] = new Pawn("black", 6, col);
        }

        // Place other pieces for both players
        // White pieces
        board[0][0] = new Rook("white", 0, 0);
        board[0][7] = new Rook("white", 0, 7);
        board[0][1] = new Knight("white", 0, 1);
        board[0][6] = new Knight("white", 0, 6);
        board[0][2] = new Bishop("white", 0, 2);
        board[0][5] = new Bishop("white", 0, 5);
        board[0][3] = new Queen("white", 0, 3);
        board[0][4] = kings[0];

        // Black pieces
        board[7][0] = new Rook("black", 7, 0);
        board[7][7] = new Rook("black", 7, 7);
        board[7][1] = new Knight("black", 7, 1);
        board[7][6] = new Knight("black", 7, 6);
        board[7][2] = new Bishop("black", 7, 2);
        board[7][5] = new Bishop("black", 7, 5);
        board[7][3] = new Queen("black", 7, 3);
        board[7][4] = kings[1];
    }

    public int getKingRow(String playerColor){
        return (kings[0].getColor() == playerColor) ? kings[0].getRow() : kings[1].getRow();
    }

    public int getKingCol(String playerColor){
        return (kings[0].getColor() == playerColor) ? kings[0].getCol() : kings[1].getCol();
    }

    private void swapTurn(){
        int row = (playerTurn == "white") ? 7 : 0;
        for (ChessPiece piece : board[row]) {
            if (piece != null && piece.getSymbol() == "P"){
                promotion(row, piece.getCol(), piece.getColor());
            }
        }
        if (playerTurn == "white"){playerTurn="black";}
        else {playerTurn="white";}
    }

    private void promotion(int row, int col, String playerColor){
        //Temporary
        board[row][col] = new Queen(playerColor, row, col);
    }

    public boolean movePiece(int currentRow, int currentCol, int newRow, int newCol) {
        if (move.isValidMove(currentRow, currentCol, newRow, newCol, playerTurn)){
            ChessPiece piece = board[currentRow][currentCol];
            //Move the piece to the new position
            board[currentRow][currentCol] = null;
            board[newRow][newCol] = piece;
            piece.setRow(newRow);
            piece.setCol(newCol);
            if (isCheck(playerTurn)){
                //Moved into check, don't allow and reset
                board[currentRow][currentCol] = piece;
                board[newRow][newCol] = null;
                piece.setRow(currentRow);
                piece.setCol(currentCol);
                System.out.println("Can't move when in check");
                return false;
            }
            swapTurn();
            return true;
        }
        return false;
    }

    public void printBoard() {

        
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ChessPiece piece = board[row][col];
                if (piece == null) {
                    System.out.print(". "); // Empty square
                } else {
                    System.out.print(piece.getSymbol() + " "); // Print the piece symbol
                }
            }
            System.out.println(); // Move to the next row
        }
    }

    public boolean isCheck(String playerColor) {
        int kingRow = getKingRow(playerColor);
        int kingCol = getKingCol(playerColor);

        // Iterate through the opponent's pieces
        for (ChessPiece[] row : board) {
            for (ChessPiece piece : row) {
                if (piece != null && !piece.getColor().equals(playerColor)) {
                    if (piece.isValidMove(kingRow, kingCol)) {
                        return true; // King is in check
                    }
                }
            }
        }

        return false; // King is not in check
    }
}
