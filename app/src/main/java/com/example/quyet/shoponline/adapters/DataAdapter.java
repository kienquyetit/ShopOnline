package com.example.quyet.shoponline.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quyet.shoponline.R;
import com.example.quyet.shoponline.objects.SanPhamDaiDien;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Quyet on 10/2/2016.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<SanPhamDaiDien> arrQuanAo;
    private Context context;

    public DataAdapter(Context context, ArrayList<SanPhamDaiDien> arr) {
        this.arrQuanAo = arr;
        this.context = context;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tv_ten_sp.setText(arrQuanAo.get(i).getTen());
        viewHolder.tv_gia_sp.setText("đ " + arrQuanAo.get(i).getGiaca());
        viewHolder.tv_so_luot_thich.setText(String.valueOf(arrQuanAo.get(i).getSoluotthich()));
        viewHolder.tv_so_luot_danh_gia.setText("(" + arrQuanAo.get(i).getSoluotdanhgia() + ")");
        Picasso.with(context).load(arrQuanAo.get(i).getLinkanh()).resize(170, 255).into(viewHolder.iv_sp);
    }

    @Override
    public int getItemCount() {
        return arrQuanAo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_ten_sp, tv_gia_sp, tv_so_luot_thich, tv_so_luot_danh_gia;
        public ImageView iv_sp;

        public ViewHolder(View view) {
            super(view);
            iv_sp = (ImageView) view.findViewById(R.id.iv_sp);
            tv_ten_sp = (TextView) view.findViewById(R.id.tv_ten_sp);
            tv_gia_sp = (TextView) view.findViewById(R.id.tv_gia_sp);
            tv_so_luot_thich = (TextView) view.findViewById(R.id.tv_so_luot_thich);
            tv_so_luot_danh_gia = (TextView) view.findViewById(R.id.tv_so_luot_danh_gia);
        }
    }
}

