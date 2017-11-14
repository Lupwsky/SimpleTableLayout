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
import com.lupw.tablelayout.R;
import com.lupw.tablelayout.ViewPagerUtil;
import com.lupw.tablelayout.view.DotView;

/**
 * Created by lupengwei on 2017/7/21.
 * Admin Lupw
 *
 * 一个添加了底部圆形指示器的ViewPager
 */

public class VPBottomTableView extends LinearLayout {
    private ViewPager viewPager;
    private DotView dotView;

    public VPBottomTableView(Context context) {
        super(context);
        initViews(context);
    }

    public VPBottomTableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public VPBottomTableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        View view = inflater.inflate(R.layout.dot_view, null);
        dotView = (DotView) view.findViewById(R.id.dotView);
        viewPager = (ViewPager) view.findViewById(R.id.dotViewPager);

        ViewPagerUtil.bind(viewPager, new OnPageScrollListener() {
            @Override
            public void onPageScroll(int enterPosition, int leavePosition, float percent) {
                if (enterPosition > leavePosition) {
                    dotView.setValue(leavePosition, percent, true);
                } else {
                    dotView.setValue(leavePosition, percent, false);
                }
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        addView(view);
    }

    public void setAdapter(PagerAdapter adapter) {
        viewPager.setAdapter(adapter);
    }
}
