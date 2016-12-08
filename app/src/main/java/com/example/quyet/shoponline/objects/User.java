package com.example.quyet.shoponline.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Quyet on 10/7/2016.
 */

public class User implements Parcelable{
    private int id;
    private String taikhoan;
    private String matkhau;
    private String tennguoidung;
    private String gioitinh;
    private Date ngaysinh;
    private String sodienthoai;
    private String email;
    private String diachi;
    private String linkanh;

    protected User(Parcel in) {
        id = in.readInt();
        taikhoan = in.readString();
        matkhau = in.readString();
        tennguoidung = in.readString();
        gioitinh = in.readString();
        sodienthoai = in.readString();
        email = in.readString();
        diachi = in.readString();
        linkanh = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(taikhoan);
        dest.writeString(matkhau);
        dest.writeString(tennguoidung);
        dest.writeString(gioitinh);
        dest.writeString(sodienthoai);
        dest.writeString(email);
        dest.writeString(diachi);
        dest.writeString(linkanh);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getTennguoidung() {
        return tennguoidung;
    }

    public void setTennguoidung(String tennguoidung) {
        this.tennguoidung = tennguoidung;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getLinkanh() {
        return linkanh;
    }

    public void setLinkanh(String linkanh) {
        this.linkanh = linkanh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public User(int id, String taikhoan, String matkhau, String tennguoidung, String gioitinh, Date ngaysinh, String sodienthoai, String email, String diachi, String linkanh) {
        this.id = id;
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.tennguoidung = tennguoidung;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.sodienthoai = sodienthoai;
        this.email = email;
        this.diachi = diachi;
        this.linkanh = linkanh;
    }

    public User() {
    }
}
