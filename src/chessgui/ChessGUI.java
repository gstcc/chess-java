import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import pieces.*;
import boardLogic.ChessBoard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessGUI {

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private JPanel chessBoard;
    private ChessBoard gameBoard; // Added reference to your ChessBoard class
    private final JLabel message = new JLabel(
            "Chess Champ is ready to play!");
    private static final String COLS = "ABCDEFGH";

    private int selectedRow = -1;
    private int selectedCol = -1;

    ChessGUI(ChessBoard gameBoard) {
        this.gameBoard = gameBoard;
        initializeGui();
    }

    public ChessBoard getGameBoard() {
        return gameBoard;
    }

    public final void initializeGui() {
        // Set up the main GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        tools.add(new JButton("New")); // TODO - add functionality!
        tools.add(new JButton("Save")); // TODO - add functionality!
        tools.add(new JButton("Restore")); // TODO - add functionality!
        tools.addSeparator();
        tools.add(new JButton("Resign")); // TODO - add functionality!
        tools.addSeparator();
        tools.add(message);

        gui.add(new JLabel("?"), BorderLayout.LINE_START);

        chessBoard = new JPanel(new GridLayout(0, 9));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(chessBoard);

        // Create the chess board squares
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int row = 0; row < chessBoardSquares.length; row++) {
            for (int col = 0; col < chessBoardSquares[row].length; col++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                b.setIcon(new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB)));
                if ((col % 2 == 1 && row % 2 == 1) || (col % 2 == 0 && row % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.DARK_GRAY);
                }

                final int r = row;
                final int c = col;
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleSquareClick(r, c);
                    }
                });

                chessBoardSquares[row][col] = b;
            }
        }

        // Fill the chess board
        chessBoard.add(new JLabel(""));
        // Fill the top row
        for (int col = 0; col < 8; col++) {
            chessBoard.add(new JLabel(COLS.substring(col, col + 1), SwingConstants.CENTER));
        }
        // Fill the black non-pawn piece row
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                switch (col) {
                    case 0:
                        chessBoard.add(new JLabel("" + (row + 1), SwingConstants.CENTER));
                    default:
                        chessBoard.add(chessBoardSquares[row][col]);
                }
            }
        }
    }

    private void handleSquareClick(int row, int col) {
        if (selectedRow == -1 && selectedCol == -1) {
            ChessPiece selectedPiece = gameBoard.getBoard()[row][col];
            if (selectedPiece != null && selectedPiece.getColor().equals(gameBoard.getPlayerTurn())) {
                selectedRow = row;
                selectedCol = col;
            }
        } else {
            if (gameBoard.movePiece(selectedRow, selectedCol, row, col)) {
                updateChessBoardUI();
            }
            selectedRow = -1;
            selectedCol = -1;
        }
    }

    public final JComponent getChessBoard() {
        return chessBoard;
    }

    public final JComponent getGui() {
        return gui;
    }

    // Helper method to update the chessboard UI based on the ChessBoard state
    public void updateChessBoardUI() {
        ChessPiece[][] board = gameBoard.getBoard();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                ChessPiece piece = board[row][col];
                if (piece != null) {
                    String imagePath = "icons/" + piece.getColor() + piece.getSymbol() + ".png";
                    ImageIcon icon = new ImageIcon(imagePath);
                    chessBoardSquares[row][col].setIcon(icon);
                } else {
                    chessBoardSquares[row][col].setIcon(null);
                }
            }
        }
    }
}
