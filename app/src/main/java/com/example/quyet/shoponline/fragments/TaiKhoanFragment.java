package com.example.quyet.shoponline.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.quyet.shoponline.Config;
import com.example.quyet.shoponline.MainActivity;
import com.example.quyet.shoponline.R;
import com.example.quyet.shoponline.activities.LoginActivity;
import com.github.clans.fab.FloatingActionButton;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Quyet on 10/2/2016.
 */

public class TaiKhoanFragment extends Fragment {
    Button btnLinkDangNhap;
    TextView tvTenDangNhap, tvThayDoiMatKhau, tvTenNguoiDung, tvGioiTinh, tvNgaySinh, tvSoDienThoai, tvEmail, tvDiaChi;
    CircularImageView ivUser;
    View view;
    FloatingActionButton btnLogout;
    SharedPreferences appPrefs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        appPrefs = getActivity().getSharedPreferences("user", MODE_PRIVATE);
        final SharedPreferences.Editor editor = appPrefs.edit();
        int id = appPrefs.getInt(Config.KEY_USER_ID, -1);
        if (id == -1) {
            view = inflater.inflate(R.layout.chua_co_tai_khoan_layout, null);
            referDefaultView();
            btnLinkDangNhap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivityForResult(intent, Config.LOGIN_REQUEST);
                }
            });
        } else {
            view = inflater.inflate(R.layout.tai_khoan_layout, null);
            referView();
            getInfoAccount();
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.clear();
                    editor.commit();
                    startActivity(new Intent(getContext(), MainActivity.class));
                   /* FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.detach(TaiKhoanFragment.this).attach(TaiKhoanFragment.this).commit();*/
                }
            });
        }
        return view;
    }

    private void getInfoAccount() {
        Picasso.with(getContext()).load(appPrefs.getString(Config.KEY_USER_LINK_ANH, Config.URL_DEFAULT_AVARTA).toString()).resize(80, 80).into(ivUser);
        tvTenDangNhap.setText(appPrefs.getString(Config.KEY_USER_TAI_KHOAN, "shoponline"));
        tvTenNguoiDung.setText(appPrefs.getString(Config.KEY_USER_TEN_NGUOI_DUNG, "shoponline"));
        tvGioiTinh.setText(appPrefs.getString(Config.KEY_USER_GIOI_TINH, "Chưa thiết lập"));
        tvNgaySinh.setText(appPrefs.getString(Config.KEY_USER_NGAY_SINH, "Chưa thiết lập"));
        tvSoDienThoai.setText(appPrefs.getString(Config.KEY_USER_SO_DIEN_THOAI, "Chưa thiết lập"));
        tvEmail.setText(appPrefs.getString(Config.KEY_USER_TAI_KHOAN, "Chưa thiết lập"));
        tvDiaChi.setText(appPrefs.getString(Config.KEY_USER_DIA_CHI, "Chưa thiết lập"));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Config.LOGIN_REQUEST){
            if(resultCode == Config.RESULT_CODE_SUCCESS){
                startActivity(new Intent(getContext(), MainActivity.class));
             /*   FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(TaiKhoanFragment.this).attach(TaiKhoanFragment.this).commit();*/
            }
        }
    }

    private void referView() {
        btnLogout = (FloatingActionButton) view.findViewById(R.id.btnLogout);
        ivUser = (CircularImageView) view.findViewById(R.id.iv_user);
        tvThayDoiMatKhau = (TextView) view.findViewById(R.id.tv_thay_doi_mat_khau);
        tvTenDangNhap = (TextView) view.findViewById(R.id.tv_ten_dang_nhap);
        tvTenNguoiDung = (TextView) view.findViewById(R.id.tv_ten_nguoi_dung);
        tvGioiTinh = (TextView) view.findViewById(R.id.tv_gioi_tinh);
        tvNgaySinh = (TextView) view.findViewById(R.id.tv_ngay_sinh);
        tvSoDienThoai = (TextView) view.findViewById(R.id.tv_so_dien_thoai);
        tvEmail = (TextView) view.findViewById(R.id.tv_email);
        tvDiaChi = (TextView) view.findViewById(R.id.tv_dia_chi);
    }

    private void referDefaultView() {
        btnLinkDangNhap = (Button) view.findViewById(R.id.btnLinkDangNhap);
    }

}
