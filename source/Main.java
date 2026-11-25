import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // GUI dibuat di Event Dispatch Thread
        SwingUtilities.invokeLater(POSFrame::new);
    }
}
