package com.example.shopping_mall.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping_mall.R;
import com.example.shopping_mall.bean.HomePageBean;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

public class HomePage_Adapters extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<HomePageBean.DataBean.BannerBean> homepagebanners;
    //private List<HomePageBean.DataBean.ChannelBean> channel;
    private Context context;
    int a=0;
    private static final int BANNER = 0;
    private static final int COLUMN = 1;
    private static final int MARQUEE = 2;
    private static final int NUM_TWO = 3;
    private static final int TITLE = 4;
    private static final int NUM_THREE = 5;
    private static final int NORMAL = 6;


    public HomePage_Adapters(List<HomePageBean.DataBean.BannerBean> homepagebanners, Context context) {
        this.homepagebanners = homepagebanners;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i==0){
            View inflate = LayoutInflater.from(context).inflate(R.layout.homepage_adapters_banners_layout, null);
            return new ViewHolder(inflate);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.homepage_adapters_channel_layout, null);
            return new ViewHolder2(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int type = getItemViewType(i);
        if (type==0){
            ViewHolder view = (ViewHolder) viewHolder;
            view.b.setImages(homepagebanners);
            view.b.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    HomePageBean.DataBean.BannerBean path1 = (HomePageBean.DataBean.BannerBean) path;
                    Glide.with(context).load(path1.getImage_url()).into(imageView);
                }
            }).start();
        }
//        else if (type==1){
//            if (homepagebanners.size()>0){
//                a= i -1;
//            }else {
//                a=i;
//            }
//            ViewHolder2 holder2 = (ViewHolder2) viewHolder;
//            holder2.tv.setText(channel.get(a).getName());
//           Glide.with(context).load(channel.get(a).getIcon_url()).into(holder2.iv);
  //      }
    }

    @Override
    public int getItemCount() {
        return homepagebanners.size();
//        if (homepagebanners.size()>0){
//            return channel.size()+1;
//        }else{
//            return channel.size();
//        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final Banner b;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            b = itemView.findViewById(R.id.b);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {

        private final TextView tv;
        private final ImageView iv;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            iv = itemView.findViewById(R.id.iv);
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        case BANNER:
                            return 12;
                        case COLUMN:
                            return 3;
                        case MARQUEE:
                            return 12;
                        case NUM_TWO:
                            return 6;
                        case TITLE:
                            return 12;
                        case NUM_THREE:
                            return 4;
                        case NORMAL:
                            return 6;
                        default:
                            return 12;
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return BANNER;
        } else  {
            return COLUMN;
        }

    }
}
