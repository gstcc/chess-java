package boardLogic;

import pieces.ChessPiece;
import pieces.Pawn;

public class Move {
    private ChessBoard chessBoard;
    private int[] prevMove;

    public Move(ChessBoard board){
        this.chessBoard = board;
        this.prevMove = new int[4];
    };
    

    public void setPrevMove(int prevRow, int prevCol, int newRow, int newCol){
        prevMove[0] = prevRow;
        prevMove[1] = prevCol;
        prevMove[2] = newRow;
        prevMove[3] = newCol;
    }

    public boolean isValidMove(int currentRow, int currentCol, int newRow, int newCol, String playerColor){
        ChessPiece[][] board = chessBoard.getBoard();
        ChessPiece piece = board[currentRow][currentCol];

        if (piece == null) {
            //System.out.println("There is no piece at the specified source position.");
            return false;
        }

        if (piece.getColor() != playerColor){
            //System.out.println("Can't move an opponents piece");
            return false;
        }

        ChessPiece destinationPiece = board[newRow][newCol];

        //Pawn capture
        if (piece.getSymbol().equals("P") && Math.abs(currentCol-newCol)==1){
            // Check for en passant
            if ((currentRow==prevMove[2]) && enPassantCapture(currentRow, currentCol, newRow, newCol)) {
                return true;
            }
            int direction = (piece.getColor().equals("white")) ? 1 : -1;
            return currentRow+direction==newRow && destinationPiece != null;
        }

        //Pawn can't move straight into another piece
        if (destinationPiece != null && piece.getSymbol().equals("P")){
            return false;
        }

        if (!piece.isValidMove(newRow, newCol)) {
            //System.out.println("Invalid move for the selected piece.");
            return false;
        }

        if (destinationPiece != null && destinationPiece.getColor().equals(piece.getColor())) {
            //System.out.println("Cannot capture your own piece.");
            return false;
        }
        

        return true && !isPathBlocked(currentRow, currentCol, newRow, newCol);
    }

    public boolean isPathBlocked(int currentRow, int currentCol, int newRow, int newCol) {
        ChessPiece[][] board = chessBoard.getBoard();
        if (board[currentRow][currentCol].getSymbol() == "N"){
            //Knights can jump over pieces so no need to check
            return false;
        }

        int rowStep = (newRow - currentRow == 0) ? 0 : (newRow - currentRow) / Math.abs(newRow - currentRow);
        int colStep = (newCol - currentCol == 0) ? 0 : (newCol - currentCol) / Math.abs(newCol - currentCol);

        int row = currentRow + rowStep;
        int col = currentCol + colStep;

        while (row != newRow || col != newCol) {
            if (board[row][col] != null) {
                //System.out.println("Cannot move over your own piece");
                return true; // Path is blocked
            }
            row += rowStep;
            col += colStep;
        }

        return false; // Path is not blocked
    }

    public boolean enPassantCapture(int currentRow, int currentCol, int newRow, int newCol) {
        ChessPiece[][] board = chessBoard.getBoard();
        ChessPiece prevPiece = board[prevMove[2]][prevMove[3]];
        // Check if the previous piece was a pawn, moved two squares, and is of the opposite color
        if (prevPiece.getSymbol().equals("P") &&
            prevPiece.getColor().equals(getOpponentColor(chessBoard.getPlayerTurn())) &&
            Math.abs(prevMove[2] - prevMove[0]) == 2) {
            System.out.println("got here");
            // Ensure white only captures downwards and black only captures upwards
            int direction = chessBoard.getPlayerTurn().equals("white") ? 1 : -1;
            int expectedRow = prevMove[2] + direction;

            if (newRow == expectedRow && Math.abs(currentCol - newCol) == 1 && newCol == prevMove[3]) {
                return true;
            }
        }

        return false;
    }

    
    // Helper function to get the opponent's color
    private String getOpponentColor(String playerColor) {
        return (playerColor.equals("white")) ? "black" : "white";
    }

    
}


