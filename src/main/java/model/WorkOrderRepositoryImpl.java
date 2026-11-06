/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADVAN
 */
import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WorkOrderRepositoryImpl implements WorkOrderRepository {

    // buat wo
    @Override
    public void addWorkOrder(WorkOrder wo) {
        String sql = "INSERT INTO work_orders (id_wo, nama_pelanggan, nama_barang, biaya, status_progres, kode_qr) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, wo.getIdWo());
            pstmt.setString(2, wo.getNamaPelanggan());
            pstmt.setString(3, wo.getNamaBarang());
            pstmt.setDouble(4, wo.getBiaya());
            pstmt.setString(5, wo.getStatusProgres());
            pstmt.setString(6, wo.getKodeQr());

            pstmt.executeUpdate();
            System.out.println("Work Order berhasil ditambahkan ke database.");

        } catch (SQLException e) {
            System.out.println("Error tambah data: " + e.getMessage());
        }
    }

    @Override
    public List<WorkOrder> getAllWorkOrders() {
        List<WorkOrder> listWo = new ArrayList<>();
        String sql = "SELECT * FROM work_orders";

        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                WorkOrder wo = new WorkOrder(
                        rs.getString("id_wo"),
                        rs.getString("nama_pelanggan"),
                        rs.getString("nama_barang"),
                        rs.getDouble("biaya"),
                        rs.getString("status_progres"),
                        rs.getString("kode_qr")
                );
                listWo.add(wo);
            }
        } catch (SQLException e) {
            System.out.println("Error ambil data: " + e.getMessage());
        }
        return listWo;
    }

    // Implementasi FR-008: Mencari Work Order berdasarkan ID
    @Override
    public WorkOrder searchWorkOrder(String idWo) {
        String sql = "SELECT * FROM work_orders WHERE id_wo = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, idWo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new WorkOrder(
                            rs.getString("id_wo"),
                            rs.getString("nama_pelanggan"),
                            rs.getString("nama_barang"),
                            rs.getDouble("biaya"),
                            rs.getString("status_progres"),
                            rs.getString("kode_qr")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error cari data: " + e.getMessage());
        }
        return null;
    }

    // Implementasi FR-004 & FR-005: Memperbarui status progres pengerjaan (Update)
    @Override
    public void updateStatusProgress(String idWo, String statusBaru) {
        String sql = "UPDATE work_orders SET status_progres = ? WHERE id_wo = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, statusBaru);
            pstmt.setString(2, idWo);
            pstmt.executeUpdate();
            System.out.println("Status Work Order berhasil diupdate.");

        } catch (SQLException e) {
            System.out.println("Error update status: " + e.getMessage());
        }
    }
}
