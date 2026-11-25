import javax.swing.*;
import java.awt.*;

public class ComponentDemo extends JFrame {

    public ComponentDemo() {
        setTitle("Swing Component Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel form: label dan input disusun berurutan
        JPanel formPanel = new JPanel(new FlowLayout());
        formPanel.add(new JLabel("Nama:"));
        formPanel.add(new JTextField(15));

        // Panel aksi: tombol Simpan dan Batal
        JPanel actionPanel = new JPanel(new FlowLayout());
        actionPanel.add(new JButton("Simpan"));
        actionPanel.add(new JButton("Batal"));

        // JFrame memakai BorderLayout, setiap panel punya layout sendiri
        setLayout(new BorderLayout());
        add(formPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ComponentDemo::new);
    }
}
