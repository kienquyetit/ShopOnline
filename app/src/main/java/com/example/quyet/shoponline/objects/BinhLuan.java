package com.example.quyet.shoponline.objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Quyet on 10/14/2016.
 */

public class BinhLuan implements Parcelable{
    private int id;
    private int id_nguoi_dung;
    private String tennguoidung;
    private String linkanh;
    private int id_hang;
    private String noidung;

    public BinhLuan() {
    }

    public BinhLuan(String noidung, int id_hang, String linkanh, String tennguoidung, int id_nguoi_dung, int id) {
        this.noidung = noidung;
        this.id_hang = id_hang;
        this.linkanh = linkanh;
        this.tennguoidung = tennguoidung;
        this.id_nguoi_dung = id_nguoi_dung;
        this.id = id;
    }

    protected BinhLuan(Parcel in) {
        id = in.readInt();
        id_nguoi_dung = in.readInt();
        tennguoidung = in.readString();
        linkanh = in.readString();
        id_hang = in.readInt();
        noidung = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(id_nguoi_dung);
        dest.writeString(tennguoidung);
        dest.writeString(linkanh);
        dest.writeInt(id_hang);
        dest.writeString(noidung);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BinhLuan> CREATOR = new Creator<BinhLuan>() {
        @Override
        public BinhLuan createFromParcel(Parcel in) {
            return new BinhLuan(in);
        }

        @Override
        public BinhLuan[] newArray(int size) {
            return new BinhLuan[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_nguoi_dung() {
        return id_nguoi_dung;
    }

    public void setId_nguoi_dung(int id_nguoi_dung) {
        this.id_nguoi_dung = id_nguoi_dung;
    }

    public String getTennguoidung() {
        return tennguoidung;
    }

    public void setTennguoidung(String tennguoidung) {
        this.tennguoidung = tennguoidung;
    }

    public String getLinkanh() {
        return linkanh;
    }

    public void setLinkanh(String linkanh) {
        this.linkanh = linkanh;
    }

    public int getId_hang() {
        return id_hang;
    }

    public void setId_hang(int id_hang) {
        this.id_hang = id_hang;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }
}
