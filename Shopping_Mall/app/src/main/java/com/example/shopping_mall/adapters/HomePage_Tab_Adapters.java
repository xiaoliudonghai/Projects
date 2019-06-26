package com.example.shopping_mall.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping_mall.R;
import com.example.shopping_mall.bean.HomePageBean;

import java.util.ArrayList;
import java.util.List;

public class HomePage_Tab_Adapters extends RecyclerView.Adapter<HomePage_Tab_Adapters.ViewHolder> {
    private List<HomePageBean.DataBean.ChannelBean> list;
    private Context context;

    public HomePage_Tab_Adapters(List<HomePageBean.DataBean.ChannelBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public HomePage_Tab_Adapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.homepage_adapters_channel_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomePage_Tab_Adapters.ViewHolder viewHolder, int i) {
        viewHolder.tv.setText(list.get(i).getName());
        Glide.with(context).load(list.get(i).getIcon_url()).into(viewHolder.iv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv;
        private final ImageView iv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            iv = itemView.findViewById(R.id.iv);
        }
    }
}
