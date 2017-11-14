package com.lupw.simpletablelayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by lupengwei on 2017/11/14.
 * Admin Lupw
 */

public class MFragment extends Fragment {


    public static MFragment getInstance(String title) {
        MFragment mFragment = new MFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        mFragment.setArguments(args);
        return mFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        TextView txtContent = (TextView) view.findViewById(R.id.txtContent);
        txtContent.setText(getArguments().getString("title"));
        return view;
    }
}
