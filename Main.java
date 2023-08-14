import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import boardLogic.ChessBoard;

public class Main {

    public static void main(String[] args) {
        Runnable r = () -> {
            ChessGUI cb = new ChessGUI(new ChessBoard());
            cb.updateChessBoardUI(); // Update the GUI based on the ChessBoard state

            JFrame f = new JFrame("ChessChamp");
            f.add(cb.getGui());
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setLocationByPlatform(true);

            f.pack();
            f.setMinimumSize(f.getSize());
            f.setVisible(true);
        };
        SwingUtilities.invokeLater(r);
    }
    /*public static void main(String[] args) {
        // Create a chessboard
        ChessBoard chessBoard = new ChessBoard();

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

        chessBoard.movePiece(1, 5, 3, 5);
        chessBoard.printBoard();
        System.out.println("---------------------------------------");

        chessBoard.movePiece(4, 0, 3, 0);
        chessBoard.printBoard();
        System.out.println("---------------------------------------");

        chessBoard.movePiece(3, 5, 4, 5);
        chessBoard.printBoard();
        System.out.println("---------------------------------------");

        chessBoard.movePiece(3, 0, 2, 0);
        chessBoard.printBoard();
        System.out.println("---------------------------------------");

        chessBoard.movePiece(4, 5, 5, 5);
        chessBoard.printBoard();
        System.out.println("---------------------------------------");

        chessBoard.movePiece(2, 0, 1, 0);
        chessBoard.printBoard();
        System.out.println("---------------------------------------");

        chessBoard.movePiece(5, 5, 6, 5);
        chessBoard.printBoard();
        System.out.println("---------------------------------------");

        chessBoard.movePiece(1, 0, 0, 0);
        chessBoard.printBoard();
        System.out.println("---------------------------------------");
    }*/
}