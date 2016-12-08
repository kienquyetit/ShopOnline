package com.example.quyet.shoponline.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.quyet.shoponline.Config;
import com.example.quyet.shoponline.R;
import com.example.quyet.shoponline.RequestHandler;
import com.example.quyet.shoponline.activities.ChiTietSPActivity;
import com.example.quyet.shoponline.adapters.DataAdapter;
import com.example.quyet.shoponline.interfaces.RecyclerViewOnClickListenerHack;
import com.example.quyet.shoponline.objects.SanPhamDaiDien;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Quyet on 10/2/2016.
 */

public class BanChayFragment extends Fragment implements RecyclerViewOnClickListenerHack, View.OnClickListener {

    private ArrayList<SanPhamDaiDien> mList;
    private CarouselView carouselView;
    private int[] sampleImages = {R.drawable.quang_cao_1, R.drawable.quang_cao_2};
    private View view;
    private String JSON_STRING;
    SharedPreferences appPrefs;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ban_chay_layout, null);

        carouselView = (CarouselView) view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);
        appPrefs = getActivity().getSharedPreferences(Config.SHARE_PREFRENCES_LOAI_HANG, MODE_PRIVATE);
        getJSON();
        return view;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    private void showProduct() {
        JSONObject jsonObject = null;
        mList = new ArrayList<SanPhamDaiDien>();
        mList.clear();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                int id = Integer.parseInt(jo.getString(Config.TAG_PRODUCT_ID));
                String ten = jo.getString(Config.TAG_PRODUCT_TEN);
                String giaca = jo.getString(Config.TAG_PRODUCT_GIA_CA);
                String linkanh = jo.getString(Config.TAG_PRODUCT_LINK_ANH);
                int soluotthich = Integer.parseInt(jo.getString(Config.TAG_PRODUCT_LUOT_THICH));
                int soluotdanhgia = Integer.parseInt(jo.getString(Config.TAG_PRODUCT_LUOT_DANH_GIA));

                SanPhamDaiDien product = new SanPhamDaiDien(soluotthich, soluotdanhgia, linkanh, giaca, ten, id);
                mList.add(product);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.ban_chay_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<SanPhamDaiDien> androidVersions = mList;
        DataAdapter adapter = new DataAdapter(getContext(), androidVersions);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new BanChayFragment.RecyclerViewTouchListener(getActivity(), recyclerView, this));
    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getContext(), "Đang tải dữ liệu", "Chờ một xíu ...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showProduct();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s;
                int id = appPrefs.getInt(Config.KEY_LOAI_HANG_ID, 0);
                if(id == 1){
                    s = rh.sendGetRequest(Config.URL_GET_QUAN_AO);
                }
                else if(id == 2){
                    s = rh.sendGetRequest(Config.URL_GET_GIAY);
                }
                else if(id == 3){
                    s = rh.sendGetRequest(Config.URL_GET_DO_THE_THAO);
                }
                else if(id == 4){
                    s = rh.sendGetRequest(Config.URL_GET_TUI_VA_PHU_KIEN);
                }
                else {
                    s = rh.sendGetRequest(Config.URL_GET_ALL);
                }
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onClickListener(View view, int position) {
        Intent intent = new Intent(getActivity(), ChiTietSPActivity.class);
        intent.putExtra(Config.EMP_ID, mList.get(position).getId());
        getActivity().startActivity(intent);
    }

    @Override
    public void onLongPressClickListener(View view, int position) {

    }

    @Override
    public void onClick(View v) {

    }

    private static class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener {
        private Context mContext;
        private GestureDetector mGestureDetector;
        private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

        public RecyclerViewTouchListener(Context c, final RecyclerView rv, RecyclerViewOnClickListenerHack rvoclh) {
            mContext = c;
            mRecyclerViewOnClickListenerHack = rvoclh;

            mGestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public void onLongPress(MotionEvent e) {
                    super.onLongPress(e);

                    View cv = rv.findChildViewUnder(e.getX(), e.getY());

                    if (cv != null && mRecyclerViewOnClickListenerHack != null) {
                        mRecyclerViewOnClickListenerHack.onLongPressClickListener(cv,
                                rv.getChildPosition(cv));
                    }
                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    View cv = rv.findChildViewUnder(e.getX(), e.getY());

                    if (cv != null && mRecyclerViewOnClickListenerHack != null) {
                        mRecyclerViewOnClickListenerHack.onClickListener(cv,
                                rv.getChildPosition(cv));
                    }

                    return (true);
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            mGestureDetector.onTouchEvent(e);
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
