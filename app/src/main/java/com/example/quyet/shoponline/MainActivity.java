package com.example.quyet.shoponline;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.quyet.shoponline.fragments.CaiDatFragment;
import com.example.quyet.shoponline.fragments.TaiKhoanFragment;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    SharedPreferences appPrefs;
    SharedPreferences.Editor editor;
    TextView tvUsername, tvEmail;
    CircularImageView ivAvarta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appPrefs = getBaseContext().getSharedPreferences(Config.SHARE_PREFRENCES_LOAI_HANG, MODE_PRIVATE);
        editor = appPrefs.edit();
        /**
         *Setup the DrawerLayout and NavigationView
         */

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);

        appPrefs = getBaseContext().getSharedPreferences("user", MODE_PRIVATE);

        View v = mNavigationView.getHeaderView(0);
        tvUsername = (TextView) v.findViewById(R.id.username);
        tvUsername.setText(appPrefs.getString(Config.KEY_USER_TEN_NGUOI_DUNG, "Chưa có tài khoản"));
        tvEmail = (TextView) v.findViewById(R.id.email);
        tvEmail.setText(appPrefs.getString(Config.KEY_USER_EMAIL, "Chưa có email"));
        ivAvarta = (CircularImageView) v.findViewById(R.id.ivAvarta);
        Picasso.with(this).load(appPrefs.getString(Config.KEY_USER_LINK_ANH, Config.URL_DEFAULT_AVARTA)).into(ivAvarta);
        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();

        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                if (menuItem.getItemId() == R.id.nav_TrangChu) {
                    editor.putInt(Config.KEY_LOAI_HANG_ID, 0);
                    editor.commit();
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();
                } else if (menuItem.getItemId() == R.id.nav_QuanAo) {
                    editor.putInt(Config.KEY_LOAI_HANG_ID, 1);
                    editor.commit();
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();
                } else if (menuItem.getItemId() == R.id.nav_Giay) {
                    editor.putInt(Config.KEY_LOAI_HANG_ID, 2);
                    editor.commit();
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();
                } else if (menuItem.getItemId() == R.id.nav_DoTheThao) {
                    editor.putInt(Config.KEY_LOAI_HANG_ID, 3);
                    editor.commit();
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();
                } else if (menuItem.getItemId() == R.id.nav_TuVaPhuKien) {
                    editor.putInt(Config.KEY_LOAI_HANG_ID, 4);
                    editor.commit();
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();
                } else if (menuItem.getItemId() == R.id.nav_TaiKhoan) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new TaiKhoanFragment()).commit();
                } else if (menuItem.getItemId() == R.id.nav_CaiDat) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new CaiDatFragment()).commit();
                }

                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
