package pieces;

public class Pawn extends ChessPiece {
    public Pawn(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean isValidMove(int newRow, int newCol) {
        int currentRow = getRow();
        int currentCol = getCol();

        // Calculate the row and column differences
        int rowDiff = newRow - currentRow;
        int colDiff = Math.abs(newCol - currentCol);

        // White pawns move forward by 1 row, black pawns move backward by 1 row
        // They can move forward by 2 rows on their first move
        if (getColor().equals("white")) {
            return rowDiff == 1 || (rowDiff == 2 && currentRow == 1) && colDiff == 0;
        } else if (getColor().equals("black")) {
            return rowDiff == -1 || (rowDiff == -2 && currentRow == 6) && colDiff == 0;
        }

        return false;
    }

    @Override
    public String getSymbol(){
        return "P";
    }
}
