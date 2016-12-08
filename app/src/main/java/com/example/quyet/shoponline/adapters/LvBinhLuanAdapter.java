package com.example.quyet.shoponline.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.quyet.shoponline.R;
import com.example.quyet.shoponline.objects.BinhLuan;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Quyet on 10/14/2016.
 */

public class LvBinhLuanAdapter extends ArrayAdapter{

    Context mContext;
    ArrayList<BinhLuan> arr;
    int mResource;

    public LvBinhLuanAdapter(Context context, int resource, ArrayList<BinhLuan> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        arr = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(mResource, null);
        }
        CircularImageView iv_nguoi_dung_binh_luan = (CircularImageView) v.findViewById(R.id.iv_nguoi_dung_binh_luan);
        Picasso.with(mContext).load(arr.get(position).getLinkanh()).into(iv_nguoi_dung_binh_luan);
        TextView tv_ten_nguoi_dung_binh_luan = (TextView) v.findViewById(R.id.tv_ten_nguoi_dung_binh_luan);
        tv_ten_nguoi_dung_binh_luan.setText(arr.get(position).getTennguoidung());
        TextView tv_noi_dung_binh_luan = (TextView) v.findViewById(R.id.tv_noi_dung_binh_luan);
        tv_noi_dung_binh_luan.setText(arr.get(position).getNoidung());
        return v;
    }
}
