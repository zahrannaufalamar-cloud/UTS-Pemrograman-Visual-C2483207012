package uts_pemrograman_visual_kasir_kantin_kampus;

import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class UTS_Pemrograman_Visual_Kasir_Kantin_Kampus extends JFrame {

    private final JTextField txtNama = new JTextField();
    private final JTextField txtHarga = new JTextField();
    private final JTextField txtJumlah = new JTextField();
    private final JTextField txtPembayaran = new JTextField();

    private final JLabel lblTotal = new JLabel("Rp0");
    private final JLabel lblKembalian = new JLabel("Rp0");

    private final NumberFormat rupiah =
            NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    private long totalHarga = 0;
    private boolean totalSudahDihitung = false;

    public UTS_Pemrograman_Visual_Kasir_Kantin_Kampus() {
        setTitle("Kasir Kantin Kampus");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(620, 650);
        setMinimumSize(new Dimension(560, 600));
        setLocationRelativeTo(null);
        setResizable(true);

        buatTampilan();
    }

    private void buatTampilan() {
        JPanel root = new JPanel(new BorderLayout(0, 18));
        root.setBackground(new Color(245, 247, 251));
        root.setBorder(new EmptyBorder(24, 30, 24, 30));
        setContentPane(root);

        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(new Color(31, 78, 121));
        header.setBorder(new EmptyBorder(22, 24, 22, 24));

        JLabel judul = new JLabel("KASIR KANTIN KAMPUS");
        judul.setFont(new Font("SansSerif", Font.BOLD, 25));
        judul.setForeground(Color.WHITE);
        judul.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subjudul = new JLabel("Sistem pencatatan transaksi makanan dan minuman");
        subjudul.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subjudul.setForeground(new Color(225, 235, 245));
        subjudul.setAlignmentX(Component.LEFT_ALIGNMENT);

        header.add(judul);
        header.add(Box.createVerticalStrut(7));
        header.add(subjudul);
        root.add(header, BorderLayout.NORTH);

        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(Color.WHITE);
        content.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(225, 230, 237)),
                new EmptyBorder(22, 24, 22, 24)));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 5, 8, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;

        tambahLabel(content, c, "Nama Makanan / Minuman", 0);
        aturField(txtNama);
        tambahField(content, c, txtNama, 1);

        tambahLabel(content, c, "Harga Satuan (angka)", 2);
        aturField(txtHarga);
        tambahField(content, c, txtHarga, 3);

        tambahLabel(content, c, "Jumlah Pembelian", 4);
        aturField(txtJumlah);
        tambahField(content, c, txtJumlah, 5);

        tambahLabel(content, c, "Uang Pembayaran (angka)", 6);
        aturField(txtPembayaran);
        tambahField(content, c, txtPembayaran, 7);

        JLabel infoTotal = new JLabel("TOTAL HARGA");
        infoTotal.setFont(new Font("SansSerif", Font.BOLD, 12));
        infoTotal.setForeground(new Color(90, 100, 115));
        c.gridx = 0; c.gridy = 8; c.gridwidth = 1;
        content.add(infoTotal, c);

        lblTotal.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTotal.setForeground(new Color(31, 78, 121));
        c.gridx = 1; c.gridy = 8;
        content.add(lblTotal, c);

        JLabel infoKembali = new JLabel("UANG KEMBALIAN");
        infoKembali.setFont(new Font("SansSerif", Font.BOLD, 12));
        infoKembali.setForeground(new Color(90, 100, 115));
        c.gridx = 0; c.gridy = 9;
        content.add(infoKembali, c);

        lblKembalian.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblKembalian.setForeground(new Color(28, 140, 90));
        c.gridx = 1; c.gridy = 9;
        content.add(lblKembalian, c);

        JPanel tombol = new JPanel(new GridLayout(1, 3, 10, 0));
        tombol.setBackground(Color.white);

        JButton btnHitung = buatTombol("HITUNG", new Color(31, 105, 170));
        JButton btnBayar = buatTombol("BAYAR", new Color(28, 140, 90));
        JButton btnReset = buatTombol("RESET", new Color(105, 113, 125));
        
        btnHitung.setForeground(Color.BLACK);
        btnBayar.setForeground(Color.BLACK);
        btnReset.setForeground(Color.BLACK);

        btnHitung.addActionListener(e -> hitungTotal());
        btnBayar.addActionListener(e -> prosesPembayaran());
        btnReset.addActionListener(e -> resetForm());

        tombol.add(btnHitung);
        tombol.add(btnBayar);
        tombol.add(btnReset);

        c.gridx = 0; c.gridy = 10; c.gridwidth = 2;
        c.insets = new Insets(22, 5, 4, 5);
        content.add(tombol, c);

        root.add(content, BorderLayout.CENTER);

        JLabel footer = new JLabel("Aplikasi Kasir Kantin Kampus | Pemrograman Visual");
        footer.setHorizontalAlignment(SwingConstants.CENTER);
        footer.setForeground(new Color(115, 123, 135));
        footer.setFont(new Font("SansSerif", Font.PLAIN, 11));
        root.add(footer, BorderLayout.SOUTH);
    }

    private void tambahLabel(JPanel panel, GridBagConstraints c, String teks, int baris) {
        JLabel label = new JLabel(teks);
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        label.setForeground(new Color(55, 65, 80));
        c.gridx = 0; c.gridy = baris; c.gridwidth = 2;
        panel.add(label, c);
    }

    private void tambahField(JPanel panel, GridBagConstraints c, JTextField field, int baris) {
        c.gridx = 0; c.gridy = baris; c.gridwidth = 2;
        panel.add(field, c);
    }

    private void aturField(JTextField field) {
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(200, 38));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(205, 213, 223)),
                new EmptyBorder(7, 10, 7, 10)));
    }

    private JButton buatTombol(String teks, Color warna) {
        JButton button = new JButton(teks);
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setForeground(Color.WHITE);
        button.setBackground(warna);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(12, 10, 12, 10));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    private Long bacaAngkaPositif(String teks, String namaKolom, boolean bolehNol) {
        String nilai = teks.trim();
        if (nilai.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    namaKolom + " wajib diisi.", "Input Belum Lengkap",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }

        if (!nilai.matches("\\d+")) {
            JOptionPane.showMessageDialog(this,
                    namaKolom + " harus berupa angka bulat tanpa huruf atau simbol.",
                    "Input Tidak Valid", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        try {
            long angka = Long.parseLong(nilai);
            if (angka < 0 || (!bolehNol && angka == 0)) {
                JOptionPane.showMessageDialog(this,
                        namaKolom + (bolehNol ? " tidak boleh negatif." : " harus lebih dari nol."),
                        "Input Tidak Valid", JOptionPane.WARNING_MESSAGE);
                return null;
            }
            return angka;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    namaKolom + " terlalu besar untuk diproses.",
                    "Input Tidak Valid", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

    private boolean validasiNama() {
        if (txtNama.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nama makanan atau minuman wajib diisi.",
                    "Input Belum Lengkap", JOptionPane.WARNING_MESSAGE);
            txtNama.requestFocusInWindow();
            return false;
        }
        return true;
    }

    private boolean hitungTotal() {
        if (!validasiNama()) return false;

        Long harga = bacaAngkaPositif(txtHarga.getText(), "Harga satuan", true);
        if (harga == null) {
            txtHarga.requestFocusInWindow();
            return false;
        }

        Long jumlah = bacaAngkaPositif(txtJumlah.getText(), "Jumlah pembelian", false);
        if (jumlah == null) {
            txtJumlah.requestFocusInWindow();
            return false;
        }

        try {
            totalHarga = Math.multiplyExact(harga, jumlah);
        } catch (ArithmeticException ex) {
            JOptionPane.showMessageDialog(this,
                    "Hasil perhitungan terlalu besar.", "Kesalahan Perhitungan",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        lblTotal.setText(formatRupiah(totalHarga));
        lblKembalian.setText("Rp0");
        totalSudahDihitung = true;
        return true;
    }

    private void prosesPembayaran() {
        if (!totalSudahDihitung) {
            if (!hitungTotal()) return;
        }

        Long pembayaran = bacaAngkaPositif(txtPembayaran.getText(),
                "Uang pembayaran", true);
        if (pembayaran == null) {
            txtPembayaran.requestFocusInWindow();
            return;
        }

        if (pembayaran < totalHarga) {
            long kurang = totalHarga - pembayaran;
            lblKembalian.setText("Rp0");
            JOptionPane.showMessageDialog(this,
                    "Pembayaran kurang " + formatRupiah(kurang) + ".",
                    "Pembayaran Tidak Mencukupi", JOptionPane.WARNING_MESSAGE);
            txtPembayaran.requestFocusInWindow();
            return;
        }

        long kembalian = pembayaran - totalHarga;
        lblKembalian.setText(formatRupiah(kembalian));
        JOptionPane.showMessageDialog(this,
                "Pembayaran berhasil.\nTotal: " + formatRupiah(totalHarga)
                + "\nDibayar: " + formatRupiah(pembayaran)
                + "\nKembalian: " + formatRupiah(kembalian),
                "Transaksi Berhasil", JOptionPane.INFORMATION_MESSAGE);
    }

    private String formatRupiah(long angka) {
        return rupiah.format(angka).replace(",00", "");
    }

    private void resetForm() {
        txtNama.setText("");
        txtHarga.setText("");
        txtJumlah.setText("");
        txtPembayaran.setText("");
        lblTotal.setText("Rp0");
        lblKembalian.setText("Rp0");
        totalHarga = 0;
        totalSudahDihitung = false;
        txtNama.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
                // Tampilan default Java Swing digunakan jika Look and Feel gagal dimuat.
            }
            new UTS_Pemrograman_Visual_Kasir_Kantin_Kampus().setVisible(true);
        });
    }
}
