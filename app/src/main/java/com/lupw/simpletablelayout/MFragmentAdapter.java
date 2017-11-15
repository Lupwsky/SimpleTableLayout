package com.lupw.simpletablelayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by lupengwei on 2017/11/14.
 * Admin Lupw
 */

public class MFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> dataList;

    public MFragmentAdapter(FragmentManager fm, List<Fragment> dataList) {
        super(fm);
        this.dataList = dataList;
    }


    @Override
    public Fragment getItem(int position) {
        return dataList.get(position);
    }


    @Override
    public int getCount() {
        return dataList.size();
    }
}
