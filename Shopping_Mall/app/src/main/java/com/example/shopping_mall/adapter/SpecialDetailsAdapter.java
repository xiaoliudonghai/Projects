package com.example.shopping_mall.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping_mall.R;
import com.example.shopping_mall.bean.SpecialDetailsBean;
import com.example.shopping_mall.bean.SpecialDetailsXiangBean;

import java.util.ArrayList;

public class SpecialDetailsAdapter extends RecyclerView.Adapter<SpecialDetailsAdapter.ViewHolder> {
     ArrayList<SpecialDetailsXiangBean.DataBean>lists;
     Context context;


    public SpecialDetailsAdapter( ArrayList<SpecialDetailsXiangBean.DataBean> lists, Context context) {

        this.lists = lists;
        this.context = context;
    }

    @NonNull
    @Override
    public SpecialDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View inflate = LayoutInflater.from(context).inflate(R.layout.special_item, null);
            ViewHolder viewHolder = new ViewHolder(inflate);
            return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull SpecialDetailsAdapter.ViewHolder viewHolder, int i) {
        int itemViewType = getItemViewType(i);


                Glide.with(context).load(lists.get(i).getScene_pic_url()).into(viewHolder.iv);
                viewHolder.title.setText(lists.get(i).getTitle());
                viewHolder.sub.setText(lists.get(i).getSubtitle());
                viewHolder.info.setText(lists.get(i).getPrice_info() + "元起");


    }

    @Override
    public int getItemCount() {
            return lists.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv;
        private final TextView title;
        private final TextView sub;
        private final TextView info;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_special);
            title = itemView.findViewById(R.id.title_special);
            sub = itemView.findViewById(R.id.subtitle_special);
            info = itemView.findViewById(R.id.price_info_special);
        }
    }
}
