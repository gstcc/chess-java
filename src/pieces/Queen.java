package pieces;

public class Queen extends ChessPiece {
    public Queen(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean isValidMove(int newRow, int newCol) {
        int currentRow = getRow();
        int currentCol = getCol();

        // Calculate the row and column differences
        int rowDiff = Math.abs(newRow - currentRow);
        int colDiff = Math.abs(newCol - currentCol);

        // Queen moves either vertically, horizontally, or diagonally
        return currentRow == newRow || currentCol == newCol || rowDiff == colDiff;
    }

    @Override
    public String getSymbol(){
        return "Q";
    }
}
