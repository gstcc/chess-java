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
}