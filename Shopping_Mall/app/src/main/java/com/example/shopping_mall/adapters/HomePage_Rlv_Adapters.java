package com.example.shopping_mall.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.example.shopping_mall.R;
import com.example.shopping_mall.bean.HomePageBean;

import java.util.ArrayList;
import java.util.List;

public class HomePage_Rlv_Adapters extends RecyclerView.Adapter<HomePage_Rlv_Adapters.ViewHolder> {
    private List<HomePageBean.DataBean.BrandListBean> list;
    private Context context;

    public HomePage_Rlv_Adapters(List<HomePageBean.DataBean.BrandListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public HomePage_Rlv_Adapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.homepage_rlv_adapters_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomePage_Rlv_Adapters.ViewHolder viewHolder, final int i) {
//        Glide.with(context).load(list.get(i).getPic_url()).into(viewHolder.iv);
       //获取item宽度，计算图片等比例缩放后的高度，为imageview设置参数
        String pic_url = list.get(i).getPic_url();
        Glide.with(context)//activty
                .load(pic_url)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        int height = resource.getHeight();//获取bitmap信息，可赋值给外部变量操作，也可在此时行操作。
                        int width = resource.getWidth();
                         Log.e("宽高","----宽"+width+"-----高"+height);
                        viewHolder.iv.setImageBitmap(resource);

                        int wd = width*100/360;
                        int hh = height*100/wd;
                         Log.e("计算后宽高","----宽"+wd+"===="+360+"-----高"+hh);
                        //重新设置控件的宽高
                        ViewGroup.LayoutParams pas1 = viewHolder.iv.getLayoutParams();
                        pas1.height=hh;
                        pas1.width=360;
                        viewHolder.iv.setLayoutParams(pas1);


                        //打印控件的显示宽高
                        ViewGroup.LayoutParams pas2 = viewHolder.iv.getLayoutParams();
                          Log.e("控件宽高","----宽"+pas2.width+"-----高"+pas2.height);
                    }
                });


        viewHolder.tvname.setText(list.get(i).getName());
        viewHolder.tvfloor_price.setText(list.get(i).getFloor_price()+"元起");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv;
        private final TextView tvname;
        private final TextView tvfloor_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tvname = itemView.findViewById(R.id.tvname);
            tvfloor_price = itemView.findViewById(R.id.tvfloor_price);
        }
    }
}
