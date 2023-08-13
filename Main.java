import boardLogic.ChessBoard;

public class Main {
    public static void main(String[] args) {
        // Create a chessboard
        ChessBoard chessBoard = new ChessBoard();

        // Print the initial state of the board
        System.out.println("Initial Chessboard:");
        chessBoard.printBoard();
        System.out.println("---------------------------------------");

        chessBoard.movePiece(0, 1, 2, 0);
        chessBoard.printBoard();
        System.out.println("---------------------------------------");

        chessBoard.movePiece(6, 0, 5, 0);
        chessBoard.printBoard();
        System.out.println("---------------------------------------");

        chessBoard.movePiece(2, 0, 4, 1);
        chessBoard.printBoard();
        System.out.println("---------------------------------------");

        chessBoard.movePiece(5, 0, 4, 0);
        chessBoard.printBoard();
        System.out.println("---------------------------------------");

        chessBoard.movePiece(4, 1, 6, 2);
        chessBoard.printBoard();
        System.out.println("---------------------------------------");

        chessBoard.movePiece(4, 0, 3, 0);
        chessBoard.printBoard();
        System.out.println("---------------------------------------");

        chessBoard.movePiece(7, 3, 6, 2);
        chessBoard.printBoard();
        System.out.println("---------------------------------------");
    }
}