package com.lupw.tablelayout.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import com.lupw.tablelayout.OnTabSelectedListener;
import com.lupw.tablelayout.R;
import com.lupw.tablelayout.utils.DensityUtil;

import java.util.List;

/**
 * Created by lupengwei on 2017/11/14.
 * Admin Lupw
 */

public class ScrollTableView extends LinearLayout{
    private Context context;
    private TableView tableView;
    private HorizontalScrollView scrollView;
    private OnTabSelectedListener listener;

    public ScrollTableView(Context context) {
        super(context);
        init(context);
    }

    public ScrollTableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScrollTableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        this.context = context;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.table_scroll_view, null);
        tableView = (TableView) view.findViewById(R.id.tabView);
        scrollView = (HorizontalScrollView) view.findViewById(R.id.scrollView);
        scrollView.setSmoothScrollingEnabled(true);
        addView(view);
    }


    public void setNumList(List<Integer> numList) {
        tableView.setNumList(numList);
    }


    public void setTitles(List<String> dataList) {
        tableView.setTitles(dataList);
    }


    public void setValue(int positionTemp, float positionOffset, boolean isLeft) {
        tableView.setValue(positionTemp, positionOffset, isLeft);
    }


    public void setListener(OnTabSelectedListener onTabSelectedListener) {
        tableView.setListener(onTabSelectedListener);
    }

    public void scrollTo(int position) {
        int currentX = tableView.getCurrentX(position);
        if (currentX > scrollView.getWidth() / 2) {
            scrollView.smoothScrollTo(currentX - scrollView.getWidth() / 2, 0);
        }

        if (position == 0) {
            scrollView.smoothScrollTo(0, 0);
        }
    }
}
