package com.lupw.simpletablelayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by lupengwei on 2017/11/14.
 * Admin Lupw
 */

public class MFragment extends LazyFragment {
    private TextView txtContent;


    public static MFragment getInstance(String title) {
        MFragment mFragment = new MFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        mFragment.setArguments(args);
        Log.e("MFragment getInstance", title);
        return mFragment;
    }


    @Override
    public View onViewCreate(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        txtContent = (TextView) view.findViewById(R.id.txtContent);
        Log.e("MFragment onViewCreate", getArguments().getString("title"));
        return view;
    }


    @Override
    public void fetchData() {
        txtContent.setText(getArguments().getString("title"));
        Log.e("MFragment fetchData", getArguments().getString("title"));
    }
}
