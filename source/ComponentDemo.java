import javax.swing.*;
import java.awt.*;

public class ComponentDemo extends JFrame {

    public ComponentDemo() {
        setTitle("Swing Component Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // FlowLayout menyusun komponen berurutan dari kiri ke kanan
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Nama:");
        JTextField nameField = new JTextField(15);
        JButton button = new JButton("Tampilkan");

        add(label);
        add(nameField);
        add(button);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ComponentDemo::new);
    }
}
