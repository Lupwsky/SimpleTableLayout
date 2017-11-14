package com.lupw.tablelayout.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.lupw.tablelayout.OnPageScrollListener;
import com.lupw.tablelayout.OnTabSelectedListener;
import com.lupw.tablelayout.R;
import com.lupw.tablelayout.ViewPagerUtil;

import java.util.List;

/**
 * Created by lupengwei on 2017/7/26.
 * Admin Lupw
 *
 * 添加了TableLayout顶部导航栏的的ViewPager，支持滑动
 */

public class VPTopTableView extends LinearLayout {
    private ViewPager viewPager;
    private ScrollTableView tableView;

    public VPTopTableView(Context context) {
        super(context);
        initViews(context);

    }

    public VPTopTableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public VPTopTableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }


    /**
     * 初始化视图
     *
     * @param context 上下文
     */
    private void initViews(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.vp_top_table_view, null);
        viewPager = (ViewPager) view.findViewById(R.id.tabViewPager);
        tableView = (ScrollTableView) view.findViewById(R.id.tabView);
        tableView.setListener(new OnTabSelectedListener() {
            @Override
            public void tabSelected(int position) {
                viewPager.setCurrentItem(position, false);
            }
        });

        ViewPagerUtil.bind(viewPager, new OnPageScrollListener() {
            @Override
            public void onPageScroll(int enterPosition, int leavePosition, float percent) {
                if (enterPosition > leavePosition) {
                    tableView.setValue(leavePosition, percent, true);
                } else {
                    tableView.setValue(leavePosition, percent, false);
                }
            }

            @Override
            public void onPageSelected(int position) {
                tableView.scrollTo(position);  // 让选中的tab居中
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        addView(view);
    }


    public void setAdapter(PagerAdapter adapter) {
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount() - 1);
    }


    public void setNumList(List<Integer> numList) {
        tableView.setNumList(numList);
    }


    public void setTitles(List<String> dataList) {
        tableView.setTitles(dataList);
    }
}
