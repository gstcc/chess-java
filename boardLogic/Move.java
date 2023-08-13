package boardLogic;
import pieces.ChessPiece;

public class Move {
    private ChessBoard chessBoard;

    public Move(ChessBoard board){
        this.chessBoard = board;
    }

    public boolean isValidMove(int currentRow, int currentCol, int newRow, int newCol, String playerColor){
        ChessPiece[][] board = chessBoard.getBoard();
        ChessPiece piece = board[currentRow][currentCol];

        if (piece == null) {
            System.out.println("There is no piece at the specified source position.");
            return false;
        }

        if (piece.getColor() != playerColor){
            System.out.println("Can't move an opponents piece");
            return false;
        }

        if (!piece.isValidMove(newRow, newCol)) {
            System.out.println("Invalid move for the selected piece.");
            return false;
        }

        ChessPiece destinationPiece = board[newRow][newCol];
        if (destinationPiece != null && destinationPiece.getColor().equals(piece.getColor())) {
            System.out.println("Cannot capture your own piece.");
            return false;
        }

        return true && !isPathBlocked(currentRow, currentCol, newRow, newCol);
    }

    private boolean isPathBlocked(int currentRow, int currentCol, int newRow, int newCol) {
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
                System.out.println("Cannot move over your own piece");
                return true; // Path is blocked
            }
            row += rowStep;
            col += colStep;
        }

        return false; // Path is not blocked
    }

}


