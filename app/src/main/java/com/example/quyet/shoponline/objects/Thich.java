package com.example.quyet.shoponline.objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Quyet on 10/14/2016.
 */

public class Thich implements Parcelable{
    private int id;
    private int id_nguoi_dung;
    private int id_hang;

    public Thich() {
    }

    public Thich(int id_nguoi_dung, int id, int id_hang) {
        this.id_nguoi_dung = id_nguoi_dung;
        this.id = id;
        this.id_hang = id_hang;
    }

    protected Thich(Parcel in) {
        id = in.readInt();
        id_nguoi_dung = in.readInt();
        id_hang = in.readInt();
    }

    public static final Creator<Thich> CREATOR = new Creator<Thich>() {
        @Override
        public Thich createFromParcel(Parcel in) {
            return new Thich(in);
        }

        @Override
        public Thich[] newArray(int size) {
            return new Thich[size];
        }
    };

    public int getId_nguoi_dung() {
        return id_nguoi_dung;
    }

    public void setId_nguoi_dung(int id_nguoi_dung) {
        this.id_nguoi_dung = id_nguoi_dung;
    }

    public int getId_hang() {
        return id_hang;
    }

    public void setId_hang(int id_hang) {
        this.id_hang = id_hang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(id_nguoi_dung);
        dest.writeInt(id_hang);
    }
}
