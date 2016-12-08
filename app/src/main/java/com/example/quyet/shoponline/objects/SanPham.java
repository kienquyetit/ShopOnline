package com.example.quyet.shoponline.objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Quyet on 10/2/2016.
 */

public class SanPham implements Parcelable{
    private int id;
    private String ten;
    private String danhmuc;
    private String thuonghieu;
    private String mausac;
    private String chatlieu;
    private String size;
    private String gioitthieu;
    private String giaca;
    private String linkanh;
    private int soluotthich;
    private int soluotdanhgia;

    protected SanPham(Parcel in) {
        id = in.readInt();
        ten = in.readString();
        danhmuc = in.readString();
        thuonghieu = in.readString();
        mausac = in.readString();
        chatlieu = in.readString();
        size = in.readString();
        gioitthieu = in.readString();
        giaca = in.readString();
        linkanh = in.readString();
        soluotthich = in.readInt();
        soluotdanhgia = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(ten);
        dest.writeString(danhmuc);
        dest.writeString(thuonghieu);
        dest.writeString(mausac);
        dest.writeString(chatlieu);
        dest.writeString(size);
        dest.writeString(gioitthieu);
        dest.writeString(giaca);
        dest.writeString(linkanh);
        dest.writeInt(soluotthich);
        dest.writeInt(soluotdanhgia);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SanPham> CREATOR = new Creator<SanPham>() {
        @Override
        public SanPham createFromParcel(Parcel in) {
            return new SanPham(in);
        }

        @Override
        public SanPham[] newArray(int size) {
            return new SanPham[size];
        }
    };

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDanhmuc() {
        return danhmuc;
    }

    public void setDanhmuc(String danhmuc) {
        this.danhmuc = danhmuc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThuonghieu() {
        return thuonghieu;
    }

    public void setThuonghieu(String thuonghieu) {
        this.thuonghieu = thuonghieu;
    }

    public String getMausac() {
        return mausac;
    }

    public void setMausac(String mausac) {
        this.mausac = mausac;
    }

    public String getChatlieu() {
        return chatlieu;
    }

    public void setChatlieu(String chatlieu) {
        this.chatlieu = chatlieu;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getGioitthieu() {
        return gioitthieu;
    }

    public void setGioitthieu(String gioitthieu) {
        this.gioitthieu = gioitthieu;
    }

    public String getGiaca() {
        return giaca;
    }

    public void setGiaca(String giaca) {
        this.giaca = giaca;
    }

    public String getLinkanh() {
        return linkanh;
    }

    public void setLinkanh(String linkanh) {
        this.linkanh = linkanh;
    }

    public int getSoluotthich() {
        return soluotthich;
    }

    public void setSoluotthich(int soluotthich) {
        this.soluotthich = soluotthich;
    }

    public int getSoluotdanhgia() {
        return soluotdanhgia;
    }

    public void setSoluotdanhgia(int soluotdanhgia) {
        this.soluotdanhgia = soluotdanhgia;
    }

    public SanPham() {
    }

    public SanPham(String danhmuc, String ten, int id, String thuonghieu, String chatlieu, String size, String mausac, String gioitthieu, String giaca, int soluotdanhgia, int soluotthich, String linkanh) {
        this.danhmuc = danhmuc;
        this.ten = ten;
        this.id = id;
        this.thuonghieu = thuonghieu;
        this.chatlieu = chatlieu;
        this.size = size;
        this.mausac = mausac;
        this.gioitthieu = gioitthieu;
        this.giaca = giaca;
        this.soluotdanhgia = soluotdanhgia;
        this.soluotthich = soluotthich;
        this.linkanh = linkanh;
    }


}
