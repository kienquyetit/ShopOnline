package com.example.quyet.shoponline.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quyet.shoponline.Config;
import com.example.quyet.shoponline.R;
import com.example.quyet.shoponline.RequestHandler;
import com.example.quyet.shoponline.adapters.LvBinhLuanAdapter;
import com.example.quyet.shoponline.objects.BinhLuan;
import com.example.quyet.shoponline.objects.DonDatHang;
import com.example.quyet.shoponline.objects.QuanAo;
import com.example.quyet.shoponline.objects.Thich;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ChiTietSPActivity extends AppCompatActivity {

    private EditText editSoLuong, editNoiDungBinhLuan;
    private ListView lv_binh_luan;
    private Toolbar mToolbar;
    private ImageView iv_sp_chi_tiet;
    private TextView tv_ten_sp_chi_tiet, tv_gia_sp_chi_tiet, tv_gioi_thieu_sp_chi_tiet, tv_danh_muc, tv_thuong_hieu, tv_mau_sac, tv_chat_lieu, tv_size;
    private Button btn_danh_gia_sp_chi_tiet, btn_thich_sp_chi_tiet, btn_binh_luan_sp_chi_tiet, btn_mua_ngay;
    private int id;
    SharedPreferences appPrefs;
    private QuanAo quanAo = null;
    private Thich thich = null;
    private ArrayList<BinhLuan> arrBinhLuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_sp);
        referView();
        Intent intent = getIntent();
        id = intent.getIntExtra(Config.EMP_ID, 0);
        arrBinhLuan = new ArrayList<>();
        getJSON();
        setSupportActionBar(mToolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

        btn_thich_sp_chi_tiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseStateThich();
            }
        });
        btn_binh_luan_sp_chi_tiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBinhLuan();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        btn_mua_ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyNow();
            }
        });
    }

    private void chooseStateThich() {
        if (thich == null) {
            addThich();
            btn_thich_sp_chi_tiet.setTextColor(Color.RED);
        } else {
            deleteThich();
            btn_thich_sp_chi_tiet.setTextColor(Color.BLACK);
        }
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    private void stateThich(String json) {
        JSONObject jsonObject = null;
        appPrefs = getBaseContext().getSharedPreferences("user", MODE_PRIVATE);
        int id_nguoi_dung = appPrefs.getInt("id", 0);
        try {
            jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            for (int i = 0; i < result.length(); i++) {
                JSONObject c = result.getJSONObject(i);
                if (!c.isNull(Config.TAG_LIKE_ID) && c.getInt(Config.TAG_LIKE_ID_NGUOI_DUNG) == id_nguoi_dung) {
                    thich = new Thich();
                    thich.setId(c.getInt(Config.TAG_LIKE_ID));
                    thich.setId_nguoi_dung(c.getInt(Config.TAG_LIKE_ID_NGUOI_DUNG));
                    thich.setId_hang(c.getInt(Config.TAG_LIKE_ID_HANG));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (thich != null) {
            btn_thich_sp_chi_tiet.setTextColor(Color.RED);
        }
    }

    private void addThich() {
        appPrefs = getBaseContext().getSharedPreferences("user", MODE_PRIVATE);
        int id_nguoi_dung = appPrefs.getInt("id", 0);
        final Thich thich = new Thich();
        thich.setId_nguoi_dung(id_nguoi_dung);
        thich.setId_hang(quanAo.getId());

        class AddProduct extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ChiTietSPActivity.this, "Adding...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_LIKE_ID_HANG, String.valueOf(thich.getId_hang()));
                params.put(Config.KEY_LIKE_ID_NGUOI_DUNG, String.valueOf(thich.getId_nguoi_dung()));
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_POST_LIKE, params);
                return res;
            }
        }

        AddProduct ap = new AddProduct();
        ap.execute();
    }

    private void deleteThich() {
        class DeleteEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ChiTietSPActivity.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_DELETE_LIKE, String.valueOf(thich.getId()));
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void getStateThich() {
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ChiTietSPActivity.this, "Fetching...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                stateThich(s);
                loading.dismiss();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_LIKE, String.valueOf(quanAo.getId()));
                return s;
            }
        }
        GetJSON ge = new GetJSON();
        ge.execute();
    }

    private void addBinhLuan() {
        appPrefs = getBaseContext().getSharedPreferences("user", MODE_PRIVATE);
        final BinhLuan binhLuan = new BinhLuan();
        binhLuan.setId_hang(quanAo.getId());
        binhLuan.setId_nguoi_dung(appPrefs.getInt(Config.KEY_COMMENT_ID_NGUOI_DUNG, 0));
        binhLuan.setTennguoidung(appPrefs.getString(Config.KEY_COMMENT_TEN_NGUOI_DUNG, "No Name"));
        binhLuan.setLinkanh(appPrefs.getString(Config.KEY_COMMENT_LINK_ANH, Config.URL_DEFAULT_AVARTA));
        binhLuan.setNoidung(editNoiDungBinhLuan.getText().toString());

        class AddBinhLuan extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ChiTietSPActivity.this, "Adding...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_COMMENT_ID_NGUOI_DUNG, String.valueOf(binhLuan.getId_nguoi_dung()));
                params.put(Config.KEY_COMMENT_TEN_NGUOI_DUNG, binhLuan.getTennguoidung());
                params.put(Config.KEY_COMMENT_LINK_ANH, binhLuan.getLinkanh());
                params.put(Config.KEY_COMMENT_ID_HANG, String.valueOf(binhLuan.getId_hang()));
                params.put(Config.KEY_COMMENT_NOI_DUNG, binhLuan.getNoidung());

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_POST_BINH_LUAN, params);
                return res;
            }
        }

        AddBinhLuan abl = new AddBinhLuan();
        abl.execute();
    }

    private void buyNow() {
        final Dialog dialog = new Dialog(ChiTietSPActivity.this);
        dialog.setContentView(R.layout.custom_dialog_mua_ngay_layout);
        dialog.setTitle("Đặt hàng");

        // set the custom dialog components - text, image and button
        TextView tvTenHang = (TextView) dialog.findViewById(R.id.tv_ten_hang);
        tvTenHang.setText(quanAo.getTen());
        editSoLuong = (EditText) dialog.findViewById(R.id.edit_so_luong);
        Button btnXacNhan = (Button) dialog.findViewById(R.id.btnXacNhan);
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editSoLuong.getText().toString().equals(""))
                    addProduct();
                dialog.dismiss();
            }
        });
        Button btnHuyBo = (Button) dialog.findViewById(R.id.btnHuyBo);
        btnHuyBo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void addProduct() {
        appPrefs = getBaseContext().getSharedPreferences("user", MODE_PRIVATE);
        final DonDatHang donDatHang = new DonDatHang();
        donDatHang.setId_hang(quanAo.getId());
        donDatHang.setSoluong(Integer.parseInt(editSoLuong.getText().toString()));
        donDatHang.setTenkhachhang(appPrefs.getString(Config.KEY_USER_TEN_NGUOI_DUNG, "shoponline"));
        donDatHang.setSodienthoai(appPrefs.getString(Config.KEY_USER_SO_DIEN_THOAI, "Chưa thiết lập"));
        donDatHang.setDiachikhachhang(appPrefs.getString(Config.KEY_USER_DIA_CHI, "Chưa đánh giá"));

        class AddProduct extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ChiTietSPActivity.this, "Adding...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_DON_DAT_HANG_ID, String.valueOf(donDatHang.getId()));
                params.put(Config.KEY_DON_DAT_HANG_ID_HANG, String.valueOf(donDatHang.getId_hang()));
                params.put(Config.KEY_DON_DAT_HANG_SO_LUONG, String.valueOf(donDatHang.getSoluong()));
                params.put(Config.KEY_DON_DAT_HANG_TEN_KHACH_HANG, donDatHang.getTenkhachhang());
                params.put(Config.KEY_DON_DAT_HANG_SO_DIEN_THOAI, donDatHang.getSodienthoai());
                params.put(Config.KEY_DON_DAT_HANG_DIA_CHI_KHACH_HANG, donDatHang.getDiachikhachhang());
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_POST_DON_HANG, params);
                return res;
            }
        }

        AddProduct ap = new AddProduct();
        ap.execute();
    }

    // Lấy chi tiết sản phẩm
    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ChiTietSPActivity.this, "Fetching...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                showProduct(s);
                getStateThich();
                getComments();
                loading.dismiss();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_PRODUCT, String.valueOf(id));
                return s;
            }
        }
        GetJSON ge = new GetJSON();
        ge.execute();
    }

    // Lấy tất cả bình luận của sản phẩm
    private void getComments() {
        class GetJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                showComments(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_COMMENTS, String.valueOf(quanAo.getId()));
                return s;
            }
        }
        GetJSON ge = new GetJSON();
        ge.execute();
    }

    private void showComments(String json) {

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject c = result.getJSONObject(i);
                BinhLuan binhLuan = new BinhLuan();
                binhLuan.setId(c.getInt(Config.TAG_COMMENT_ID));
                binhLuan.setId_nguoi_dung(c.getInt(Config.TAG_COMMENT_ID_NGUOI_DUNG));
                binhLuan.setTennguoidung(c.getString(Config.TAG_COMMENT_TEN_NGUOI_DUNG));
                binhLuan.setLinkanh(c.getString(Config.TAG_COMMENT_LINK_ANH));
                binhLuan.setId_hang(c.getInt(Config.TAG_COMMENT_ID_HANG));
                binhLuan.setNoidung(c.getString(Config.TAG_COMMENT_NOI_DUNG));

                arrBinhLuan.add(binhLuan);
            }

            LvBinhLuanAdapter adapter = new LvBinhLuanAdapter(getBaseContext(), R.layout.row_binh_luan_layout, arrBinhLuan);
            lv_binh_luan.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showProduct(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            quanAo = new QuanAo();
            quanAo.setId(c.getInt(Config.TAG_PRODUCT_ID));
            quanAo.setTen(c.getString(Config.TAG_PRODUCT_TEN));
            quanAo.setDanhmuc(c.getString(Config.TAG_PRODUCT_DANH_MUC));
            quanAo.setThuonghieu(c.getString(Config.TAG_PRODUCT_THUONG_HIEU));
            quanAo.setMausac(c.getString(Config.TAG_PRODUCT_MAU_SAC));
            quanAo.setChatlieu(c.getString(Config.TAG_PRODUCT_CHAT_LIEU));
            quanAo.setSize(c.getString(Config.TAG_PRODUCT_SIZE));
            quanAo.setGioitthieu(c.getString(Config.TAG_PRODUCT_GIOI_THIEU));
            quanAo.setGiaca(c.getString(Config.TAG_PRODUCT_GIA_CA));
            quanAo.setLinkanh(c.getString(Config.TAG_PRODUCT_LINK_ANH));
            quanAo.setSoluotthich(Integer.parseInt(c.getString(Config.TAG_PRODUCT_LUOT_THICH)));
            quanAo.setSoluotdanhgia(Integer.parseInt(c.getString(Config.TAG_PRODUCT_LUOT_DANH_GIA)));

            mToolbar.setTitle(quanAo.getTen());
            tv_ten_sp_chi_tiet.setText(quanAo.getTen());
            tv_danh_muc.setText(quanAo.getDanhmuc());
            tv_thuong_hieu.setText(quanAo.getThuonghieu());
            tv_mau_sac.setText(quanAo.getMausac());
            tv_chat_lieu.setText(quanAo.getChatlieu());
            tv_size.setText(quanAo.getSize());
            tv_gioi_thieu_sp_chi_tiet.setText(quanAo.getGioitthieu());
            tv_gia_sp_chi_tiet.setText(quanAo.getGiaca() + " đ");
            Picasso.with(this).load(quanAo.getLinkanh()).into(iv_sp_chi_tiet);
            btn_thich_sp_chi_tiet.setText("Thích (" + quanAo.getSoluotthich() + ")");
            btn_danh_gia_sp_chi_tiet.setText(" Đánh giá (" + quanAo.getSoluotdanhgia() + ")");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void referView() {
        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        iv_sp_chi_tiet = (ImageView) findViewById(R.id.iv_sp_chi_tiet);
        tv_ten_sp_chi_tiet = (TextView) findViewById(R.id.tv_ten_sp_chi_tiet);
        tv_danh_muc = (TextView) findViewById(R.id.tv_danh_muc);
        tv_thuong_hieu = (TextView) findViewById(R.id.tv_thuong_hieu);
        tv_mau_sac = (TextView) findViewById(R.id.tv_mau_sac);
        tv_chat_lieu = (TextView) findViewById(R.id.tv_chat_lieu);
        tv_size = (TextView) findViewById(R.id.tv_size);
        tv_gioi_thieu_sp_chi_tiet = (TextView) findViewById(R.id.tv_gioi_thieu_sp_chi_tiet);
        tv_gia_sp_chi_tiet = (TextView) findViewById(R.id.tv_gia_sp_chi_tiet);
        editNoiDungBinhLuan = (EditText) findViewById(R.id.edit_noi_dung_binh_luan);
        btn_danh_gia_sp_chi_tiet = (Button) findViewById(R.id.btn_danh_gia_sp_chi_tiet);
        btn_thich_sp_chi_tiet = (Button) findViewById(R.id.btn_thich_sp_chi_tiet);
        btn_binh_luan_sp_chi_tiet = (Button) findViewById(R.id.btn_binh_luan_sp_chi_tiet);
        btn_mua_ngay = (Button) findViewById(R.id.btn_mua_ngay);
        lv_binh_luan = (ListView) findViewById(R.id.lv_binh_luan);
    }
}
