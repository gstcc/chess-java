package pieces;

public class Rook extends ChessPiece {
    public Rook(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean isValidMove(int newRow, int newCol) {
        int currentRow = getRow();
        int currentCol = getCol();

        // Rook moves vertically or horizontally
        if (currentRow == newRow || currentCol == newCol) {
            return true;
        }

        return false;
    }

    @Override
    public String getSymbol(){
        return "R";
    }
}
