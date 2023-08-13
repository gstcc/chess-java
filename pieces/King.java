package pieces;

public class King extends ChessPiece {
    public King(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean isValidMove(int newRow, int newCol) {
        int currentRow = getRow();
        int currentCol = getCol();

        int rowDiff = Math.abs(newRow - currentRow);
        int colDiff = Math.abs(newCol - currentCol);

        // Kings move one square in any direction (vertically, horizontally, or diagonally)
        return rowDiff <= 1 && colDiff <= 1;
    }

    @Override
    public String getSymbol(){
        return "K";
    }
}
