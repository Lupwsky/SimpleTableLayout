package com.lupw.simpletablelayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lupengwei on 2017/11/14.
 * Admin Lupw
 *
 * 参考 http://www.jianshu.com/p/7e5da3eebd1e
 */

public abstract class LazyFragment extends Fragment {
    protected boolean isViewInitiated;     // 视图是否已经加载
    protected boolean isVisibleToUser;     // Fragment是否对用户可见
    protected boolean isDataInitiated;     // 数据是否已经加载


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return onViewCreate(inflater, container, savedInstanceState);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }


    private void prepareFetchData() {
        if (isViewInitiated && isVisibleToUser && !isDataInitiated) {
            isDataInitiated = true;
            fetchData();
        }
    }


    public abstract void fetchData();


    public abstract View onViewCreate(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);
}
