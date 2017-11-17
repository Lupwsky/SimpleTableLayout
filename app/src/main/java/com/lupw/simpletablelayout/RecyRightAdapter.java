package com.lupw.simpletablelayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lupengwei on 2017/11/15.
 *
 * Admin Lupw
 */

public class RecyRightAdapter extends RecyclerView.Adapter<RecyRightAdapter.ViewHolder>{
    private final static int LAYOUT_TYPE_TITLE = 0;
    private final static int LAYOUT_TYPE_CONTENT = 1;
    private Context context;
    private List<RightBean> dataList;


    public RecyRightAdapter(Context context, List<RightBean> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    @Override
    public int getItemViewType(int position) {
        RightBean bean = dataList.get(position);
        if (bean.isTitle()) return LAYOUT_TYPE_TITLE;
        else return LAYOUT_TYPE_CONTENT;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case LAYOUT_TYPE_TITLE:
                view = LayoutInflater.from(context).inflate(R.layout.item_right_title_layout, parent, false);
                break;
            case LAYOUT_TYPE_CONTENT:
                view = LayoutInflater.from(context).inflate(R.layout.item_right_content_layout, parent, false);
                break;
            default:
                view = LayoutInflater.from(context).inflate(R.layout.item_none_content_layout, parent, false);
                break;
        }
        return new ViewHolder(view, viewType);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RightBean bean = dataList.get(position);
        holder.txtContent.setText(bean.getName() + "(" + position + ")");
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtContent;

        ViewHolder(View itemView, int viewType) {
            super(itemView);
            switch (viewType) {
                case LAYOUT_TYPE_TITLE:
                    txtContent = (TextView) itemView.findViewById(R.id.txtContent);
                    break;
                case LAYOUT_TYPE_CONTENT:
                    txtContent = (TextView) itemView.findViewById(R.id.txtContent);
                    break;
                default:
                    break;
            }
        }
    }
}
