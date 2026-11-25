import javax.swing.*;
import java.awt.*;

public class ComponentDemo extends JFrame {

    public ComponentDemo() {
        setTitle("Swing Component Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Percobaan Step 2: sementara diganti GridLayout(0, 2)
        // FlowLayout: komponen berurutan dari kiri ke kanan
        // GridLayout: setiap cell berukuran sama, 2 kolom
        setLayout(new GridLayout(0, 2));

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
