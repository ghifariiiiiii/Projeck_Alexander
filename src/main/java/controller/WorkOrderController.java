/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author ADVAN
 */
import model.WorkOrder;
import model.WorkOrderRepository;
import view.WorkOrderView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class WorkOrderController {

    // Penerapan DIP: Bergantung pada abstraksi (Interface)
    private final WorkOrderRepository repository;
    private final WorkOrderView view;

    // Dependency Injection lewat konstruktor
    public WorkOrderController(WorkOrderRepository repository, WorkOrderView view) {
        this.repository = repository;
        this.view = view;

        // Muat data dari database ke tabel saat aplikasi pertama kali dibuka
        loadTableData();

        // Daftarkan semua Event Listener ke tombol-tombol di View
        this.view.addTambahListener(e -> tambahWorkOrder());
        this.view.addUpdateListener(e -> updateStatusWorkOrder());
        this.view.addCariListener(e -> cariWorkOrder());
        this.view.addRefreshListener(e -> loadTableData());
        this.view.addClearListener(e -> this.view.clearForm());
        this.view.addHapusListener(e -> hapusWorkOrder());

        // Event klik pada baris tabel agar datanya otomatis masuk ke form input
        this.view.getTableWo().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTableWo().getSelectedRow();
                if (row != -1) {
                    DefaultTableModel model = view.getTableModel();
                    view.setFormFields(
                            model.getValueAt(row, 0).toString(),
                            model.getValueAt(row, 1).toString(),
                            model.getValueAt(row, 2).toString(),
                            model.getValueAt(row, 3).toString(),
                            model.getValueAt(row, 4).toString()
                    );
                }
            }
        });
    }

    private void loadTableData() {
        DefaultTableModel tableModel = view.getTableModel();
        tableModel.setRowCount(0); // Kosongkan isi tabel sebelum diisi ulang

        List<WorkOrder> listWo = repository.getAllWorkOrders();
        for (WorkOrder wo : listWo) {
            tableModel.addRow(new Object[]{
                wo.getIdWo(),
                wo.getNamaPelanggan(),
                wo.getNamaBarang(),
                wo.getBiaya(),
                wo.getStatusProgres(),
                wo.getKodeQr()
            });
        }
    }

    private void tambahWorkOrder() {
        String id = view.getIdWoInput();
        String pelanggan = view.getNamaPelangganInput();
        String barang = view.getNamaBarangInput();
        String biayaStr = view.getBiayaInput();
        String status = view.getStatusProgresInput();

        // Validasi input kosong
        if (id.isEmpty() || pelanggan.isEmpty() || barang.isEmpty() || biayaStr.isEmpty()) {
            view.showMessage("Semua field harus diisi!");
            return;
        }

        try {
            double biaya = Double.parseDouble(biayaStr);
            // Generate kode QR otomatis
            String kodeQr = "QR-ACK-" + id.toUpperCase();

            WorkOrder woBaru = new WorkOrder(id, pelanggan, barang, biaya, status, kodeQr);
            repository.addWorkOrder(woBaru);

            view.showMessage("Work Order berhasil ditambahkan!\nKode QR: " + kodeQr);
            view.clearForm();
            loadTableData();
        } catch (NumberFormatException ex) {
            view.showMessage("Format biaya harus berupa angka!");
        }
    }

    private void updateStatusWorkOrder() {
        String id = view.getIdWoInput();
        String statusBaru = view.getStatusProgresInput();

        if (id.isEmpty()) {
            view.showMessage("Pilih data di tabel atau masukkan ID Work Order yang ingin diupdate!");
            return;
        }

        repository.updateStatusProgress(id, statusBaru);
        view.showMessage("Status progres untuk ID " + id + " berhasil diperbarui!");
        view.clearForm();
        loadTableData();
    }

    private void cariWorkOrder() {
        String idCari = view.getCariIdInput();
        if (idCari.isEmpty()) {
            view.showMessage("Masukkan ID Work Order yang ingin dicari!");
            return;
        }

        WorkOrder hasil = repository.searchWorkOrder(idCari);
        DefaultTableModel tableModel = view.getTableModel();
        tableModel.setRowCount(0);

        if (hasil != null) {
            tableModel.addRow(new Object[]{
                hasil.getIdWo(),
                hasil.getNamaPelanggan(),
                hasil.getNamaBarang(),
                hasil.getBiaya(),
                hasil.getStatusProgres(),
                hasil.getKodeQr()
            });
        } else {
            view.showMessage("Data Work Order dengan ID '" + idCari + "' tidak ditemukan.");
            loadTableData();
        }
    }

    private void hapusWorkOrder() {
        String id = view.getIdWoInput();

        if (id.isEmpty()) {
            view.showMessage("Pilih baris di tabel atau masukkan ID Work Order yang ingin dihapus!");
            return;
        }

        // Konfirmasi sebelum menghapus agar lebih aman
        int konfirmasi = javax.swing.JOptionPane.showConfirmDialog(
                view,
                "Apakah Anda yakin ingin menghapus Work Order ID " + id + "?",
                "Konfirmasi Hapus",
                javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (konfirmasi == javax.swing.JOptionPane.YES_OPTION) {
            repository.deleteWorkOrder(id);
            view.showMessage("Work Order ID " + id + " berhasil dihapus!");
            view.clearForm();
            loadTableData();
        }
    }
}
