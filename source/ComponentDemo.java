import javax.swing.*;
import java.awt.*;

public class ComponentDemo extends JFrame {

    private final JTextField nameField = new JTextField(15);
    private final JComboBox<String> deptCombo = new JComboBox<>(
            new String[] {"Informatika", "Elektronika", "Mesin"}
    );
    private final JCheckBox activeCheck = new JCheckBox("Mahasiswa aktif", true);
    private final JRadioButton regulerRadio = new JRadioButton("Reguler", true);
    private final JRadioButton ekstensiRadio = new JRadioButton("Ekstensi");
    private final JTextArea outputArea = new JTextArea(6, 25);
    private final JButton showButton = new JButton("Tampilkan");

    public ComponentDemo() {
        setTitle("Swing Component Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Radio button dikelompokkan supaya hanya satu yang bisa dipilih
        ButtonGroup group = new ButtonGroup();
        group.add(regulerRadio);
        group.add(ekstensiRadio);

        outputArea.setEditable(false);

        // Panel form: GridLayout 2 kolom (label | input)
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 8, 6));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.add(new JLabel("Nama:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Jurusan:"));
        formPanel.add(deptCombo);
        formPanel.add(new JLabel("Status:"));
        formPanel.add(activeCheck);

        JPanel kelasPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        kelasPanel.add(regulerRadio);
        kelasPanel.add(ekstensiRadio);
        formPanel.add(new JLabel("Kelas:"));
        formPanel.add(kelasPanel);

        // Panel aksi: tombol di bawah
        JPanel actionPanel = new JPanel(new FlowLayout());
        actionPanel.add(showButton);

        // JFrame memakai BorderLayout, setiap panel punya layout sendiri
        setLayout(new BorderLayout());
        add(formPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        // Kode di dalam lambda baru jalan saat tombol diklik
        showButton.addActionListener(e -> showData());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void showData() {
        String nama = nameField.getText();
        String jurusan = (String) deptCombo.getSelectedItem();
        boolean aktif = activeCheck.isSelected();
        String kelas = regulerRadio.isSelected()
                ? "Reguler"
                : "Ekstensi";

        outputArea.setText(
                "Nama: " + nama +
                "\nJurusan: " + jurusan +
                "\nAktif: " + aktif +
                "\nKelas: " + kelas
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ComponentDemo::new);
    }
}
