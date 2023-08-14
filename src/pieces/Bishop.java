package pieces;

public class Bishop extends ChessPiece {
    public Bishop(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean isValidMove(int newRow, int newCol) {
        int currentRow = getRow();
        int currentCol = getCol();

        // Calculate the row and column differences
        int rowDiff = Math.abs(newRow - currentRow);
        int colDiff = Math.abs(newCol - currentCol);

        // Bishop moves diagonally (rowDiff should equal colDiff)
        return rowDiff == colDiff;
    }


    @Override
    public String getSymbol(){
        return "B";
    }
}
