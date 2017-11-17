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

public class RecyLeftAdapter extends RecyclerView.Adapter<RecyLeftAdapter.ViewHolder>{
    private Context context;
    private List<LeftBean> dataList;
    private Listener listener;

    public RecyLeftAdapter(Context context, List<LeftBean> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_left_content_layout, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        LeftBean bean = dataList.get(position);
        holder.txtContent.setText(bean.getName());
        holder.txtContent.setTag(holder.getLayoutPosition());
        holder.txtContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) listener.onClick((Integer) v.getTag());
            }
        });

        if (bean.isSelected()) {
            holder.txtContent.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        } else {
            holder.txtContent.setTextColor(context.getResources().getColor(R.color.colorGreyFont));
        }
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtContent;
         ViewHolder(View itemView) {
            super(itemView);
             txtContent = (TextView) itemView.findViewById(R.id.txtContent);
        }
    }

    public interface Listener {
        void onClick(int position);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
}
