package com.example.quyet.shoponline.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.quyet.shoponline.R;

import java.util.ArrayList;

/**
 * Created by Quyet on 10/2/2016.
 */

public class CaiDatFragment extends Fragment {
    ListView lvCaiDat;
    ArrayList<String> arr = new ArrayList<String>();
    ArrayAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cai_dat_layout,null);
        lvCaiDat = (ListView) view.findViewById(R.id.lv_cai_dat);
        setArrayAdapter();
        adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_multiple_choice, arr);
        lvCaiDat.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lvCaiDat.setAdapter(adapter);
        lvCaiDat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return view;
    }

    private void setArrayAdapter(){
        arr.add("Nhận thông báo khuyến mại");
    }
}