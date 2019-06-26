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
import com.example.shopping_mall.bean.ClassifyTabBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;

public class Classify_Fragment_Adapter extends RecyclerView.Adapter<Classify_Fragment_Adapter.ViewHolder> {

    private ArrayList<ClassifyTabBean.DataBean.CategoryListBean> categoryListBeans;
    private Context context;

    public Classify_Fragment_Adapter(ArrayList<ClassifyTabBean.DataBean.CategoryListBean> categoryListBeans, Context context) {
        this.categoryListBeans = categoryListBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_calssify_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        Glide.with(context).load(categoryListBeans.get(i).getBanner_url()).into(viewHolder.cf_iamge);
        viewHolder.tv.setText(categoryListBeans.get(i).getName());
        ArrayList<String> titles = new ArrayList<>();
        if (categoryListBeans.get(i).getSubCategoryList()!=null) {
            for (int j = 0; j < categoryListBeans.get(i).getSubCategoryList().size(); j++) {
                String name = categoryListBeans.get(i).getSubCategoryList().get(j).getName();
                titles.add(name);
            }


            if (titles != null) {
                viewHolder.tabflowlayout.setAdapter(new TagAdapter<String>(titles) {
                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_classify_tablayout_item, null);
                        ImageView cf_icon = inflate.findViewById(R.id.cf_icon);
                        TextView tag_textview = inflate.findViewById(R.id.tag_textview);
                        String banner_url = categoryListBeans.get(i).getSubCategoryList().get(position).getBanner_url();
                        if (banner_url!=null) {
                            Glide.with(context).load(banner_url).into(cf_icon);
                        }
                        tag_textview.setText(s);
                        return inflate;
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return categoryListBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView cf_iamge;
        public TextView tv;
        public TagFlowLayout tabflowlayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.rootView=rootView;
            cf_iamge=itemView.findViewById(R.id.cf_image);
            tv=itemView.findViewById(R.id.cf_titile);
            tabflowlayout=itemView.findViewById(R.id.tabflowlayout);
        }
    }
}
