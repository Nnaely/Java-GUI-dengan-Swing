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

    private void initProductData() {
        // TODO: isi 5 Product sesuai tabel.
    }

    private void initComponents() {
        // TODO: buat menu, panel Produk, panel Keranjang,
        // summary, struk, dan tombol.
    }

    private void bindEvents() {
        // TODO: selection table + button events.
    }

    private void addSelectedProductToCart() { }
    private void refreshCartTable() { }
    private void updateSummary() { }
    private void checkout() { }
    private void printReceipt() { }
}
