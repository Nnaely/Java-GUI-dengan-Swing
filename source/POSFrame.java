import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.List;

public class POSFrame extends JFrame {

    private final List<Product> products = new ArrayList<>();
    private final List<CartItem> cart = new ArrayList<>();

    private JTable productTable;
    private JTable cartTable;
    private JTextField qtyField;
    private JLabel selectedLabel;
    private JLabel totalLabel;
    private JLabel pointsLabel;
    private JTextArea receiptArea;

    private DefaultTableModel productModel;
    private DefaultTableModel cartModel;

    public POSFrame() {
        setTitle("POIN Off-Sales - Java Swing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initProductData();
        initComponents();
        bindEvents();

        setSize(1000, 650);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton addButton;
    private JButton checkoutButton;
    private JButton printButton;

    private void initProductData() {
        products.add(new Product("P001", "Air Mineral 600ml", 3000));
        products.add(new Product("P002", "Kopi Sachet", 5000));
        products.add(new Product("P003", "Roti isi", 8000));
        products.add(new Product("P004", "Snack Keripik", 6000));
        products.add(new Product("P005", "Minuman Botol", 12000));
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JSplitPane mainPanel = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                createProductPanel(),
                createCartPanel()
        );
        mainPanel.setResizeWeight(0.5);
        mainPanel.setDividerSize(8);
        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createProductPanel() {
        productModel = new DefaultTableModel(
                new Object[] {"ID", "Nama Produk", "Harga (Rp)"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Isi tabel diambil dari List<Product>
        for (Product p : products) {
            productModel.addRow(new Object[] {
                    p.getId(), p.getName(), p.getPrice()
            });
        }

        productTable = new JTable(productModel);
        productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productTable.getTableHeader().setReorderingAllowed(false);

        selectedLabel = new JLabel("Pilih produk dari tabel di kiri");

        qtyField = new JTextField("1", 5);
        addButton = new JButton("Add to Cart");

        JPanel qtyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        qtyPanel.add(new JLabel("Qty:"));
        qtyPanel.add(qtyField);
        qtyPanel.add(addButton);

        JPanel southPanel = new JPanel(new GridLayout(2, 1));
        southPanel.setBorder(BorderFactory.createEmptyBorder(6, 0, 0, 0));
        southPanel.add(selectedLabel);
        southPanel.add(qtyPanel);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Produk"));
        panel.add(new JScrollPane(productTable), BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createCartPanel() {
        cartModel = new DefaultTableModel(
                new Object[] {"ID", "Nama Produk", "Qty", "Harga", "Subtotal"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        cartTable = new JTable(cartModel);
        cartTable.getTableHeader().setReorderingAllowed(false);

        totalLabel = new JLabel("Total: Rp0");
        totalLabel.setFont(totalLabel.getFont().deriveFont(Font.BOLD, 16f));
        pointsLabel = new JLabel("Points: 0");
        pointsLabel.setFont(pointsLabel.getFont().deriveFont(Font.PLAIN, 14f));

        checkoutButton = new JButton("Checkout");
        printButton = new JButton("Cetak");

        // Summary: Total, Points, Checkout, Cetak disusun ke bawah
        JPanel summaryGrid = new JPanel(new GridLayout(0, 1, 0, 4));
        summaryGrid.add(totalLabel);
        summaryGrid.add(pointsLabel);
        summaryGrid.add(checkoutButton);
        summaryGrid.add(printButton);

        JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 10));
        summaryPanel.add(summaryGrid);

        receiptArea = new JTextArea(8, 30);
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        JPanel receiptPanel = new JPanel(new BorderLayout(0, 4));
        receiptPanel.setBorder(BorderFactory.createEmptyBorder(0, 8, 4, 8));
        receiptPanel.add(new JLabel("Struk:"), BorderLayout.NORTH);
        receiptPanel.add(new JScrollPane(receiptArea), BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(summaryPanel, BorderLayout.NORTH);
        southPanel.add(receiptPanel, BorderLayout.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Keranjang"));
        panel.add(new JScrollPane(cartTable), BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);
        return panel;
    }

    private void bindEvents() {
        // Step 9: baca row yang dipilih lalu tampilkan info produk
        productTable.getSelectionModel().addListSelectionListener(e -> {
            int row = productTable.getSelectedRow();
            if (row >= 0) {
                Product selected = products.get(row);
                selectedLabel.setText(
                        "Dipilih: " + selected.getId() +
                        " - " + selected.getName() +
                        " (Rp" + selected.getPrice() + ")"
                );
            }
        });

        addButton.addActionListener(
                e -> addSelectedProductToCart()
        );
    }

    private void addSelectedProductToCart() {
        int row = productTable.getSelectedRow();
        if (row < 0) {
            showWarning("Pilih produk terlebih dahulu dari tabel Produk.");
            return;
        }

        int qty;
        try {
            qty = Integer.parseInt(qtyField.getText().trim());
        } catch (NumberFormatException ex) {
            showWarning("Qty harus berupa angka bulat.");
            qtyField.requestFocus();
            return;
        }

        try {
            // Validasi qty <= 0 dilakukan oleh constructor CartItem
            cart.add(new CartItem(products.get(row), qty));
        } catch (IllegalArgumentException ex) {
            showWarning(ex.getMessage() + ".");
            qtyField.requestFocus();
            return;
        }

        refreshCartTable();
        updateSummary();
    }

    private void refreshCartTable() {
        // Tabel Keranjang selalu dibangun ulang dari List<CartItem>
        cartModel.setRowCount(0);
        for (CartItem item : cart) {
            Product p = item.getProduct();
            cartModel.addRow(new Object[] {
                    p.getId(), p.getName(), item.getQty(),
                    p.getPrice(), item.getSubtotal()
            });
        }
    }

    private void showWarning(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Input tidak valid",
                JOptionPane.WARNING_MESSAGE
        );
    }
    private int calculateTotal() {
        int total = 0;
        for (CartItem item : cart) {
            total += item.getSubtotal();
        }
        return total;
    }

    private int calculatePoints(int total) {
        // pembagian integer: Rp14.000 -> 14 points
        return total / 1000;
    }

    private void updateSummary() {
        int total = calculateTotal();
        totalLabel.setText("Total: Rp" + total);
        pointsLabel.setText("Points: " + calculatePoints(total));
    }
    private void checkout() { }
    private void printReceipt() { }
}
