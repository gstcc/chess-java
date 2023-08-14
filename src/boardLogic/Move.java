package boardLogic;
import pieces.ChessPiece;

public class Move {
    private ChessBoard chessBoard;
    private int[] prevMove;

    public Move(ChessBoard board){
        this.chessBoard = board;
        this.prevMove = new int[4];
    }

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
            System.out.println("There is no piece at the specified source position.");
            return false;
        }

        if (piece.getColor() != playerColor){
            System.out.println("Can't move an opponents piece");
            return false;
        }

        ChessPiece destinationPiece = board[newRow][newCol];

        if (Math.abs(newRow - currentRow) == 1 && Math.abs(newCol - currentCol) == 1 && piece.getSymbol().equals("P")) {
            if (destinationPiece != null && !destinationPiece.getColor().equals(piece.getColor())) {
                return true; // Valid diagonal capture
            }
        }

        // Check for en passant
        if (piece.getSymbol().equals("P") && enPassantCapture(currentRow, currentCol, newRow, newCol)) {
            return true;
        }

        if (!piece.isValidMove(newRow, newCol)) {
            System.out.println("Invalid move for the selected piece.");
            return false;
        }

        if (destinationPiece != null && destinationPiece.getColor().equals(piece.getColor())) {
            System.out.println("Cannot capture your own piece.");
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
                System.out.println("Cannot move over your own piece");
                return true; // Path is blocked
            }
            row += rowStep;
            col += colStep;
        }

        return false; // Path is not blocked
    }

    public boolean enPassantCapture(int currentRow, int currentCol, int newRow, int newCol) {
        if (Math.abs(currentCol - newCol) == 1 && Math.abs(newRow - prevMove[2])==1 && newCol == prevMove[3]) {
            ChessPiece[][] board = chessBoard.getBoard();
            ChessPiece prevPiece = board[prevMove[2]][prevMove[3]];
    
            // Check if the previous piece was a pawn, moved two squares, and is of the opposite color
            if (prevPiece != null && prevPiece.getColor().equals(getOpponentColor(chessBoard.getPlayerTurn()))
                    && prevPiece.getSymbol().equals("P") && Math.abs(prevMove[2] - prevMove[0]) == 2) {
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


