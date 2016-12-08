package com.example.quyet.shoponline.objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Quyet on 10/6/2016.
 */

public class SanPhamDaiDien implements Parcelable {
    private int id;
    private String ten;
    private  String giaca;
    private String linkanh;
    private  int soluotthich;
    private int soluotdanhgia;

    public SanPhamDaiDien(int soluotthich, int soluotdanhgia, String linkanh, String giaca, String ten, int id) {
        this.soluotthich = soluotthich;
        this.soluotdanhgia = soluotdanhgia;
        this.linkanh = linkanh;
        this.giaca = giaca;
        this.ten = ten;
        this.id = id;
    }

    public SanPhamDaiDien() {
    }

    protected SanPhamDaiDien(Parcel in) {
        id = in.readInt();
        ten = in.readString();
        giaca = in.readString();
        linkanh = in.readString();
        soluotthich = in.readInt();
        soluotdanhgia = in.readInt();
    }

    public static final Creator<SanPhamDaiDien> CREATOR = new Creator<SanPhamDaiDien>() {
        @Override
        public SanPhamDaiDien createFromParcel(Parcel in) {
            return new SanPhamDaiDien(in);
        }

        @Override
        public SanPhamDaiDien[] newArray(int size) {
            return new SanPhamDaiDien[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(ten);
        dest.writeString(giaca);
        dest.writeString(linkanh);
        dest.writeInt(soluotthich);
        dest.writeInt(soluotdanhgia);
    }
}
