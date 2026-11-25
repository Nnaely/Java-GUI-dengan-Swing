# Praktikum 11 PBO - Java GUI dengan Swing

Nama: Dyas Nakata Hilwan
NIM: 251511009
Kelas: D3 A

Isi repo (`source/`):

- `ComponentDemo.java`: latihan Praktikum I dan II. Window kecil dengan JLabel, JTextField, JComboBox, JCheckBox, JRadioButton, JTextArea, dan JButton. Tombol Tampilkan menampilkan isi form, dan nama kosong ditolak dengan JOptionPane.
- `Product.java`: data produk (id, name, price) dengan field private.
- `CartItem.java`: satu baris keranjang (product, qty) dan perhitungan `getSubtotal()`. Qty <= 0 ditolak lewat exception.
- `POSFrame.java`: aplikasi POS (subclass JFrame). Tabel Produk, Add to Cart, tabel Keranjang, total dan points, checkout, struk, print, serta menu File dan Help.
- `Main.java`: titik masuk aplikasi POS.

Semua tampilan disusun lewat kode dengan layout manager (BorderLayout, GridLayout, FlowLayout, JSplitPane), tanpa GUI Builder dan tanpa `setBounds()`.

Challenge opsional yang dikerjakan:

- Produk yang sama ditambahkan lagi digabung qty-nya dalam satu baris.
- Harga diformat dengan `NumberFormat.getCurrencyInstance` locale Indonesia (contoh `Rp14.000,00`).
- Tombol Cetak nonaktif sampai ada struk.
- Konfirmasi sebelum keluar (File > Exit maupun tombol X), shortcut Ctrl+Q, F1, dan Enter di field Qty.

## Cara menjalankan

Butuh JDK 17 atau lebih baru.

```powershell
cd source
javac -d bin *.java
java -cp bin Main
```

```powershell
cd source
javac -d bin *.java
java -cp bin ComponentDemo
```
