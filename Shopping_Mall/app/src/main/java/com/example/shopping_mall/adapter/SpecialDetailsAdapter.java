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

public class SpecialDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
     ArrayList<SpecialDetailsBean.DataBean> list;
     ArrayList<SpecialDetailsXiangBean.DataBean>lists;
     Context context;
     int a=0;

    public SpecialDetailsAdapter(ArrayList<SpecialDetailsBean.DataBean> list, ArrayList<SpecialDetailsXiangBean.DataBean> lists, Context context) {
        this.list = list;
        this.lists = lists;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i==0){
            View inflate = LayoutInflater.from(context).inflate(R.layout.special_item, null);
            ViewHolder viewHolder = new ViewHolder(inflate);
            return viewHolder;
        }else if (i==1){
            View inflate = LayoutInflater.from(context).inflate(R.layout.specialdetails_item, null);
            ViewHolder1 viewHolder1 = new ViewHolder1(inflate);
            return viewHolder1;
        } else{
            View inflate = LayoutInflater.from(context).inflate(R.layout.special_item, null);
            ViewHolder2 viewHolder = new ViewHolder2(inflate);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int itemViewType = getItemViewType(i);
        if (itemViewType==0){
            ViewHolder viewHolder1 = (ViewHolder) viewHolder;
            Glide.with(context).load(list.get(i).getScene_pic_url()).into(viewHolder1.iv);
            viewHolder1.title.setText(list.get(i).getTitle());
            viewHolder1.sub.setText(list.get(i).getSubtitle());
            viewHolder1.info.setText(list.get(i).getPrice_info()+"元起");
        }else if (itemViewType==1){
            ViewHolder1 viewHolder1 = (ViewHolder1) viewHolder;
            viewHolder1.tv.setText("相关专题");
        }else {
            if (lists.size() > 0) {
                if (list.size() > 0) {
                    a = i - 2;
                } else {
                    a = i - 1;
                }
                ViewHolder2 viewHolder1 = (ViewHolder2) viewHolder;
                Glide.with(context).load(lists.get(a).getScene_pic_url()).into(viewHolder1.iv);
                viewHolder1.title.setText(lists.get(a).getTitle());
                viewHolder1.sub.setText(lists.get(a).getSubtitle());
                viewHolder1.info.setText(lists.get(a).getPrice_info() + "元起");
            }
        }
    }

    @Override
    public int getItemCount() {
        if (list.size()>0){
            return lists.size()+2;
        }else {
            return lists.size()+1;
        }
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
    public class ViewHolder1 extends RecyclerView.ViewHolder {

        private final TextView tv;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_specialdetails);
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder {
        private final ImageView iv;
        private final TextView title;
        private final TextView sub;
        private final TextView info;
        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_special);
            title = itemView.findViewById(R.id.title_special);
            sub = itemView.findViewById(R.id.subtitle_special);
            info = itemView.findViewById(R.id.price_info_special);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (list.size()>0 &&position==0){
            return 0;
        }else{
            if (position==1) {
                return 1;
            }else{
                return 2;
            }
        }
    }
}
