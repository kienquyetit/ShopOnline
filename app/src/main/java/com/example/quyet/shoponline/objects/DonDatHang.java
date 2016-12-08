package com.example.quyet.shoponline.objects;

/**
 * Created by Quyet on 10/7/2016.
 */

public class DonDatHang {
    private int id;
    private int id_hang;
    private int soluong;
    private String tenkhachhang;
    private String sodienthoai;
    private String diachikhachhang;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_hang() {
        return id_hang;
    }

    public void setId_hang(int id_hang) {
        this.id_hang = id_hang;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getDiachikhachhang() {
        return diachikhachhang;
    }

    public void setDiachikhachhang(String diachikhachhang) {
        this.diachikhachhang = diachikhachhang;
    }

    public DonDatHang() {
    }

    public DonDatHang(int id, int id_hang, int soluong, String tenkhachhang, String sodienthoai, String diachikhachhang) {
        this.id = id;
        this.id_hang = id_hang;
        this.soluong = soluong;
        this.tenkhachhang = tenkhachhang;
        this.sodienthoai = sodienthoai;
        this.diachikhachhang = diachikhachhang;
    }
}
