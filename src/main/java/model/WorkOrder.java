/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADVAN
 */
public class WorkOrder {

    private String idWo;
    private String namaPelanggan;
    private String namaBarang;
    private double biaya;
    private String statusProgres;
    private String kodeQr;

    //deklarasi atribut' WO
    public WorkOrder(String idWo, String namaPelanggan, String namaBarang, double biaya,
            String statusProgres, String kodeQr) {
        this.idWo = idWo;
        this.namaPelanggan = namaPelanggan;
        this.namaBarang = namaBarang;
        this.biaya = biaya;
        this.statusProgres = statusProgres;
        this.kodeQr = kodeQr;
    }

    public String getIdWo() {
        return idWo;
    }

    public void setIdWo(String idWo) {
        this.idWo = idWo;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public double getBiaya() {
        return biaya;
    }

    public void setBiaya(double biaya) {
        this.biaya = biaya;
    }

    public String getStatusProgres() {
        return statusProgres;
    }

    public void setStatusProgres(String statusProgres) {
        this.statusProgres = statusProgres;
    }

    public String getKodeQr() {
        return kodeQr;
    }

    public void setKodeQr(String kodeQr) {
        this.kodeQr = kodeQr;
    }
}
