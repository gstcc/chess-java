package pieces;

public abstract class ChessPiece {
    private String color;
    private int row;
    private int col;

    public ChessPiece(String color, int row, int col) {
        this.color = color;
        this.row = row;
        this.col = col;
    }

    public abstract boolean isValidMove(int newRow, int newCol);

    public abstract String getSymbol();

    public void setCol(int col) {
        this.col = col;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public String getColor() {
        return color;
    }

    public int getRow() {
        return row;
    }

}
