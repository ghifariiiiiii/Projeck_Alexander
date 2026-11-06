/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ADVAN
 */
package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class WorkOrderView extends JFrame {

    // Komponen Input Form
    private final JTextField txtIdWo;
    private final JTextField txtNamaPelanggan;
    private final JTextField txtNamaBarang;
    private final JTextField txtBiaya;
    private final JComboBox<String> cbStatusProgres;

    // Komponen Pencarian
    private final JTextField txtCariId;

    // Komponen Tombol Aksi
    private final JButton btnTambah;
    private final JButton btnUpdateStatus;
    private final JButton btnCari;
    private final JButton btnRefresh;
    private final JButton btnClear;

    // Komponen Tabel
    private final JTable tableWo;
    private final DefaultTableModel tableModel;

    public WorkOrderView() {
        setTitle("Sistem Informasi Work Order - PT Alexander Cipta Karya");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 550);
        setLocationRelativeTo(null); // Agar window muncul di tengah layar
        setLayout(new BorderLayout(10, 10));

        // 1. PANEL ATAS (Form Input & Tombol Aksi)
        JPanel panelUtamaAtas = new JPanel(new BorderLayout(5, 5));
        panelUtamaAtas.setBorder(BorderFactory.createTitledBorder("Form Pengelolaan Work Order"));

        // Panel Form menggunakan GridLayout (5 baris, 2 kolom)
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 8, 8));

        panelForm.add(new JLabel("  ID Work Order (Contoh: WO-001):"));
        txtIdWo = new JTextField();
        panelForm.add(txtIdWo);

        panelForm.add(new JLabel("  Nama Pelanggan:"));
        txtNamaPelanggan = new JTextField();
        panelForm.add(txtNamaPelanggan);

        panelForm.add(new JLabel("  Nama Barang / Pesanan:"));
        txtNamaBarang = new JTextField();
        panelForm.add(txtNamaBarang);

        panelForm.add(new JLabel("  Estimasi Biaya (Rp):"));
        txtBiaya = new JTextField();
        panelForm.add(txtBiaya);

        panelForm.add(new JLabel("  Status Progres Pengerjaan:"));
        String[] opsiStatus = {
            "Menunggu Konfirmasi",
            "Dalam Pengerjaan (Produksi)",
            "Quality Control",
            "Selesai / Siap Diambil"
        };
        cbStatusProgres = new JComboBox<>(opsiStatus);
        panelForm.add(cbStatusProgres);

        // Panel Tombol menggunakan FlowLayout
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnTambah = new JButton("Tambah WO Baru");
        btnUpdateStatus = new JButton("Update Status Progres");
        btnClear = new JButton("Clear Form");

        panelTombol.add(btnTambah);
        panelTombol.add(btnUpdateStatus);
        panelTombol.add(btnClear);

        panelUtamaAtas.add(panelForm, BorderLayout.CENTER);
        panelUtamaAtas.add(panelTombol, BorderLayout.SOUTH);

        // 2. PANEL TENGAH (Tabel Data Work Order)
        String[] kolomTabel = {"ID WO", "Nama Pelanggan", "Nama Barang", "Biaya (Rp)", "Status Progres", "Kode QR Tracking"};
        tableModel = new DefaultTableModel(kolomTabel, 0);
        tableWo = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableWo);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Daftar Work Order & Tracking"));

        // 3. PANEL BAWAH (Pencarian Work Order - FR-008)
        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelCari.setBorder(BorderFactory.createTitledBorder("Pencarian Data"));
        panelCari.add(new JLabel("Cari berdasarkan ID WO:"));
        txtCariId = new JTextField(15);
        btnCari = new JButton("Cari Work Order");
        btnRefresh = new JButton("Tampilkan Semua");

        panelCari.add(txtCariId);
        panelCari.add(btnCari);
        panelCari.add(btnRefresh);

        // Menambahkan ketiga panel utama ke dalam JFrame
        add(panelUtamaAtas, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelCari, BorderLayout.SOUTH);
    }

    // Getter untuk mengambil input dari pengguna
    public String getIdWoInput() {
        return txtIdWo.getText().trim();
    }

    public String getNamaPelangganInput() {
        return txtNamaPelanggan.getText().trim();
    }

    public String getNamaBarangInput() {
        return txtNamaBarang.getText().trim();
    }

    public String getBiayaInput() {
        return txtBiaya.getText().trim();
    }

    public String getStatusProgresInput() {
        return (String) cbStatusProgres.getSelectedItem();
    }

    public String getCariIdInput() {
        return txtCariId.getText().trim();
    }

    public JTable getTableWo() {
        return tableWo;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    // Setter untuk mengisi form saat baris tabel diklik atau mengosongkan form
    public void setFormFields(String id, String pelanggan, String barang, String biaya, String status) {
        txtIdWo.setText(id);
        txtNamaPelanggan.setText(pelanggan);
        txtNamaBarang.setText(barang);
        txtBiaya.setText(biaya);
        cbStatusProgres.setSelectedItem(status);
    }

    public void clearForm() {
        txtIdWo.setText("");
        txtNamaPelanggan.setText("");
        txtNamaBarang.setText("");
        txtBiaya.setText("");
        cbStatusProgres.setSelectedIndex(0);
        txtCariId.setText("");
        tableWo.clearSelection();
    }

    // Metode untuk mendaftarkan Event Listener dari Controller
    public void addTambahListener(ActionListener listener) {
        btnTambah.addActionListener(listener);
    }

    public void addUpdateListener(ActionListener listener) {
        btnUpdateStatus.addActionListener(listener);
    }

    public void addCariListener(ActionListener listener) {
        btnCari.addActionListener(listener);
    }

    public void addRefreshListener(ActionListener listener) {
        btnRefresh.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        btnClear.addActionListener(listener);
    }

    // Helper untuk menampilkan pesan popup (JOptionPane)
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
