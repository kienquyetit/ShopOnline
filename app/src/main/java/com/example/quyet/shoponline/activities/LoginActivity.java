package com.example.quyet.shoponline.activities;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quyet.shoponline.Config;
import com.example.quyet.shoponline.R;
import com.example.quyet.shoponline.RequestHandler;
import com.example.quyet.shoponline.objects.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;

public class LoginActivity extends AppCompatActivity {

    EditText editTaiKhoan, editMatKhau;
    Button btnDangNhap;
    TextView tvQuenMatKhau, tvDangKyTaiKhoan, tvNotify;
    private User user;
    private String tk, mk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        referView();
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tk = editTaiKhoan.getText().toString();
                mk = editMatKhau.getText().toString();
                getUser();
            }
        });
    }

    private void getUser() {
        class GetEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LoginActivity.this, "Đang kiểm tra tài khoản ...", "Đợi xíu ...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                sharedPrefProduct(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_USER, tk);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void sharedPrefProduct(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            if(c.isNull(Config.TAG_USER_TAI_KHOAN)){
                tvNotify.setText("Tài khoản bạn vừa nhập chưa chính xác.");
            }
            else {
                user = new User();
                user.setId(Integer.parseInt(c.getString(Config.TAG_USER_ID)));
                user.setTaikhoan(c.getString(Config.TAG_USER_TAI_KHOAN));
                user.setMatkhau(c.getString(Config.TAG_USER_MAT_KHAU));
                user.setTennguoidung(c.getString(Config.TAG_USER_TEN_NGUOI_DUNG));
                user.setGioitinh(c.getString(Config.TAG_USER_GIOI_TINH));
                user.setNgaysinh(Date.valueOf(c.getString(Config.TAG_USER_NGAY_SINH)));
                user.setSodienthoai(c.getString(Config.TAG_USER_SO_DIEN_THOAI));
                user.setEmail(c.getString(Config.TAG_USER_EMAIL));
                user.setDiachi(c.getString(Config.TAG_USER_DIA_CHI));
                user.setLinkanh(c.getString(Config.TAG_USER_LINK_ANH));

                if (user.getMatkhau().toString().equals(mk)) {
                    SharedPreferences appPrefs = getSharedPreferences("user", MODE_PRIVATE);

                    SharedPreferences.Editor prefsEditor = appPrefs.edit();
                    prefsEditor.putInt(Config.KEY_USER_ID, user.getId());
                    prefsEditor.putString(Config.KEY_USER_TAI_KHOAN, user.getTaikhoan());
                    prefsEditor.putString(Config.KEY_USER_MAT_KHAU, user.getMatkhau());
                    prefsEditor.putString(Config.KEY_USER_TEN_NGUOI_DUNG, user.getTennguoidung());
                    prefsEditor.putString(Config.KEY_USER_GIOI_TINH, user.getGioitinh());
                    prefsEditor.putString(Config.KEY_USER_NGAY_SINH, String.valueOf(user.getNgaysinh()));
                    prefsEditor.putString(Config.KEY_USER_SO_DIEN_THOAI, user.getSodienthoai());
                    prefsEditor.putString(Config.KEY_USER_EMAIL, user.getEmail());
                    prefsEditor.putString(Config.KEY_USER_DIA_CHI, user.getDiachi());
                    prefsEditor.putString(Config.KEY_USER_LINK_ANH, user.getLinkanh());
                    prefsEditor.commit();
                    setResult(Config.LOGIN_REQUEST);
                    finish();
                } else {
                    tvNotify.setText("Mật khẩu bạn vừa nhập chưa chính xác.");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void referView() {
        editTaiKhoan = (EditText) findViewById(R.id.editTaiKhoan);
        editMatKhau = (EditText) findViewById(R.id.editMatKhau);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        tvDangKyTaiKhoan = (TextView) findViewById(R.id.tv_dang_ky_tai_khoan);
        tvQuenMatKhau = (TextView) findViewById(R.id.tv_quen_mat_khau);
        tvNotify = (TextView) findViewById(R.id.tv_notify);
    }

    public void onClickThoat(View view) {
        finish();
    }
}
