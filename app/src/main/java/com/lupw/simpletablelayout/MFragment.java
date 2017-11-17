package com.lupw.simpletablelayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lupengwei on 2017/11/14.
 * Admin Lupw
 */

public class MFragment extends LazyFragment {
    private View view;
    private RecyclerView leftRecycler;
    private RecyclerView rightRecycler;
    private RecyLeftAdapter leftAdapter;
    private RecyRightAdapter rightAdapter;
    private LinearLayoutManager leftLayoutManager;
    private LinearLayoutManager rightLayoutManager;
    private int currentPosition = 0, lastPosition = 0;
    private boolean isScrollByClick = false;

    public static MFragment getInstance(String title) {
        MFragment mFragment = new MFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        mFragment.setArguments(args);
        return mFragment;
    }


    @Override
    public View onViewCreate(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_item, container, false);
        }
        return view;
    }


    @Override
    public void fetchData() {
        leftRecycler = (RecyclerView) view.findViewById(R.id.leftRecycler);
        rightRecycler = (RecyclerView) view.findViewById(R.id.rightRecycler);

        leftLayoutManager = new LinearLayoutManager(getActivity());
        rightLayoutManager = new SnappingLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        leftRecycler.setLayoutManager(leftLayoutManager);
        rightRecycler.setLayoutManager(rightLayoutManager);

        final List<LeftBean> leftDataList = new ArrayList<>();
        leftDataList.add(new LeftBean("1", "子标题1", true));
        leftDataList.add(new LeftBean("2", "子标题2", false));
        leftDataList.add(new LeftBean("3", "子标题3", false));
        leftDataList.add(new LeftBean("4", "子标题4", false));
        leftDataList.add(new LeftBean("5", "子标题5", false));
        leftDataList.add(new LeftBean("6", "子标题6", false));
        leftDataList.add(new LeftBean("7", "子标题7", false));
        leftDataList.add(new LeftBean("8", "子标题8", false));
        leftDataList.add(new LeftBean("9", "子标题9", false));
        leftDataList.add(new LeftBean("10", "子标题10", false));
        leftDataList.add(new LeftBean("11", "子标题11", false));
        leftDataList.add(new LeftBean("12", "子标题12", false));
        leftDataList.add(new LeftBean("13", "子标题13", false));
        leftDataList.add(new LeftBean("14", "子标题14", false));
        leftDataList.add(new LeftBean("15", "子标题15", false));
        leftDataList.add(new LeftBean("16", "子标题16", false));
        leftDataList.add(new LeftBean("17", "子标题17", false));
        leftDataList.add(new LeftBean("18", "子标题18", false));
        leftDataList.add(new LeftBean("19", "子标题19", false));
        leftDataList.add(new LeftBean("20", "子标题20", false));
        leftAdapter = new RecyLeftAdapter(getActivity(), leftDataList);

        final List<RightBean> rightDataList = new ArrayList<>();
        rightDataList.add(new RightBean("1", "子标题1", true));
        rightDataList.add(new RightBean("1-1", "子标题1-内容1", false));
        rightDataList.add(new RightBean("1-2", "子标题1-内容2", false));
        rightDataList.add(new RightBean("1-3", "子标题1-内容3", false));
        rightDataList.add(new RightBean("1-4", "子标题1-内容4", false));

        rightDataList.add(new RightBean("2", "子标题2", true));
        rightDataList.add(new RightBean("2-1", "子标题2-内容1", false));
        rightDataList.add(new RightBean("2-2", "子标题2-内容2", false));
        rightDataList.add(new RightBean("2-3", "子标题2-内容3", false));
        rightDataList.add(new RightBean("2-4", "子标题2-内容4", false));
        rightDataList.add(new RightBean("2-5", "子标题2-内容5", false));

        rightDataList.add(new RightBean("3", "子标题3", true));
        rightDataList.add(new RightBean("3-1", "子标题3-内容1", false));
        rightDataList.add(new RightBean("3-2", "子标题3-内容2", false));
        rightDataList.add(new RightBean("3-3", "子标题3-内容3", false));
        rightDataList.add(new RightBean("3-4", "子标题3-内容4", false));
        rightDataList.add(new RightBean("3-5", "子标题3-内容5", false));

        rightDataList.add(new RightBean("4", "子标题4", true));
        rightDataList.add(new RightBean("4-1", "子标题4-内容1", false));
        rightDataList.add(new RightBean("4-2", "子标题4-内容2", false));
        rightDataList.add(new RightBean("4-3", "子标题4-内容3", false));
        rightDataList.add(new RightBean("4-4", "子标题4-内容4", false));
        rightDataList.add(new RightBean("4-5", "子标题4-内容5", false));
        rightDataList.add(new RightBean("4-6", "子标题4-内容6", false));

        rightDataList.add(new RightBean("5", "子标题5", true));
        rightDataList.add(new RightBean("5-1", "子标题5-内容1", false));
        rightDataList.add(new RightBean("5-2", "子标题5-内容2", false));
        rightDataList.add(new RightBean("5-3", "子标题5-内容3", false));

        rightDataList.add(new RightBean("6", "子标题6", true));
        rightDataList.add(new RightBean("6-1", "子标题6-内容1", false));
        rightDataList.add(new RightBean("6-2", "子标题6-内容2", false));
        rightDataList.add(new RightBean("6-3", "子标题6-内容3", false));

        rightDataList.add(new RightBean("7", "子标题7", true));
        rightDataList.add(new RightBean("7-1", "子标题7-内容1", false));
        rightDataList.add(new RightBean("7-2", "子标题7-内容2", false));
        rightDataList.add(new RightBean("7-3", "子标题7-内容3", false));

        rightDataList.add(new RightBean("8", "子标题8", true));
        rightDataList.add(new RightBean("8-1", "子标题8-内容1", false));
        rightDataList.add(new RightBean("8-2", "子标题8-内容2", false));
        rightDataList.add(new RightBean("8-3", "子标题8-内容3", false));

        rightDataList.add(new RightBean("9", "子标题9", true));
        rightDataList.add(new RightBean("9-1", "子标题9-内容1", false));
        rightDataList.add(new RightBean("9-2", "子标题9-内容2", false));
        rightDataList.add(new RightBean("9-3", "子标题9-内容3", false));

        rightDataList.add(new RightBean("10", "子标题10", true));
        rightDataList.add(new RightBean("10-1", "子标题10-内容1", false));
        rightDataList.add(new RightBean("10-2", "子标题10-内容2", false));
        rightDataList.add(new RightBean("10-3", "子标题10-内容3", false));

        rightDataList.add(new RightBean("11", "子标题11", true));
        rightDataList.add(new RightBean("11-1", "子标题11-内容1", false));

        rightDataList.add(new RightBean("12", "子标题12", true));
        rightDataList.add(new RightBean("12-1", "子标题12-内容1", false));

        rightDataList.add(new RightBean("13", "子标题13", true));
        rightDataList.add(new RightBean("13-1", "子标题13-内容1", false));
        rightDataList.add(new RightBean("13-2", "子标题13-内容2", false));
        rightDataList.add(new RightBean("13-3", "子标题13-内容3", false));

        rightDataList.add(new RightBean("14", "子标题14", true));
        rightDataList.add(new RightBean("14-1", "子标题14-内容1", false));

        rightDataList.add(new RightBean("15", "子标题15", true));
        rightDataList.add(new RightBean("15-1", "子标题15-内容1", false));
        rightDataList.add(new RightBean("15-2", "子标题15-内容2", false));

        rightDataList.add(new RightBean("16", "子标题16", true));
        rightDataList.add(new RightBean("16-1", "子标题16-内容1", false));

        rightDataList.add(new RightBean("17", "子标题17", true));
        rightDataList.add(new RightBean("17-1", "子标题17-内容1", false));

        rightDataList.add(new RightBean("18", "子标题18", true));
        rightDataList.add(new RightBean("18-1", "子标题18-内容1", false));
        rightDataList.add(new RightBean("18-2", "子标题18-内容2", false));
        rightDataList.add(new RightBean("18-3", "子标题18-内容3", false));

        rightDataList.add(new RightBean("19", "子标题19", true));
        rightDataList.add(new RightBean("19-1", "子标题19-内容1", false));
        rightDataList.add(new RightBean("19-2", "子标题19-内容2", false));
        rightDataList.add(new RightBean("19-3", "子标题19-内容3", false));

        rightDataList.add(new RightBean("20", "子标题20", true));
        rightDataList.add(new RightBean("20-1", "子标题20-内容1", false));
        rightDataList.add(new RightBean("20-2", "子标题20-内容2", false));
        rightDataList.add(new RightBean("20-3", "子标题20-内容3", false));
        rightAdapter = new RecyRightAdapter(getActivity(), rightDataList);

        leftAdapter.setListener(new RecyLeftAdapter.Listener() {
            @Override
            public void onClick(int position) {
                isScrollByClick = true;
                int index;
                LeftBean leftBean = leftDataList.get(position);
                RightBean rightBean;
                for (index = 0; index < rightDataList.size(); index++) {
                    rightBean = rightDataList.get(index);
                    if (rightBean.getId().equals(leftBean.getId())) {
                        break;
                    }
                }
                rightRecycler.smoothScrollToPosition(index);

                for (LeftBean leftBean1 : leftDataList) {
                    leftBean1.setSelected(false);
                }
                leftBean.setSelected(true);
                leftAdapter.notifyDataSetChanged();
            }
        });
        leftRecycler.setAdapter(leftAdapter);

        rightRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    isScrollByClick = false;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isScrollByClick) return;
                currentPosition = rightLayoutManager.findFirstVisibleItemPosition();
                RightBean rightBean = rightDataList.get(currentPosition);

                if (rightBean.isTitle()) {
                    if (lastPosition != currentPosition) {
                        for (int i = 0; i < leftDataList.size(); i++) {
                            LeftBean leftBean = leftDataList.get(i);
                            if (leftBean.getId().equals(rightBean.getId())) {
                                Log.e("SCROLL", rightBean.getId());
                                leftRecycler.smoothScrollToPosition(i);

                                LeftBean leftBean2 = leftDataList.get(i);
                                for (LeftBean leftBean1 : leftDataList) {
                                    leftBean1.setSelected(false);
                                }
                                leftBean2.setSelected(true);
                                leftAdapter.notifyDataSetChanged();
                                break;
                            }
                        }
                        lastPosition = currentPosition;
                    }
                }
            }
        });
        rightRecycler.setAdapter(rightAdapter);
    }
}
