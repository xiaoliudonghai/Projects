package com.example.shopping_mall.adapter;

import android.annotation.SuppressLint;
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
import com.example.shopping_mall.bean.SpecialBean;

import java.util.ArrayList;

public class SpecialAdapter extends RecyclerView.Adapter<SpecialAdapter.ViewHolder> {
     ArrayList<SpecialBean.DataBeanX.DataBean> list;
    Context context;

    public SpecialAdapter(ArrayList<SpecialBean.DataBeanX.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public SpecialAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.special_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialAdapter.ViewHolder viewHolder, final int i) {
        Glide.with(context).load(list.get(i).getScene_pic_url()).into(viewHolder.iv);
        viewHolder.title.setText(list.get(i).getTitle());
        viewHolder.sub.setText(list.get(i).getSubtitle());
        viewHolder.info.setText(list.get(i).getPrice_info()+"元起");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.click(list.get(i).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
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
    private  Click click;

    public void setClick(Click click) {
        this.click = click;
    }

    public interface Click{
        void click(int id);
    }
}
