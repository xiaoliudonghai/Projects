package com.example.shopping_mall.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopping_mall.R;
import com.example.shopping_mall.bean.ShopCarBean;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ShopCarBean> list;
    private boolean isShow ;//判断是否是编辑状态/未编辑状态，进去时是未编辑状态，所以设置为true

    public void setShow(boolean show) {
        isShow = show;
//        notifyDataSetChanged();
    }

    public void setList(ArrayList<ShopCarBean> list) {
        this.list = list;
//        notifyDataSetChanged();
    }

    public MyAdapter(Context context, ArrayList<ShopCarBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shopingcar_rv, null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final ShopCarBean shopCarBean = list.get(i);
        //判断是否是显示状态
        boolean isChoosed = shopCarBean.isChoosed;
        if (isChoosed) {
            viewHolder.ck_shopcar_chose.setChecked(true);
        } else {
            viewHolder.ck_shopcar_chose.setChecked(false);
        }
        viewHolder.tv_shopcar_name.setText(list.get(i).getName());
        viewHolder.tv_shopcar_price.setText(list.get(i).getPrice() + "");
        viewHolder.tv_shopcar_num.setText(list.get(i).getCount()+"");

        //单选框
        viewHolder.ck_shopcar_chose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopCarBean.setChoosed(((CheckBox) v).isChecked());
                checkInterface.checkGroup(i, ((CheckBox) v).isChecked());
            }
        });
        //增加按钮
        viewHolder.iv_shopcar_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回调
                changeCountInterface.doIncrease(i, viewHolder.tv_shopcar_num, viewHolder.ck_shopcar_chose.isChecked());
            }
        });
        //删减按钮
        viewHolder.iv_shopcar_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeCountInterface.doDelete(i, viewHolder.tv_shopcar_num, viewHolder.ck_shopcar_chose.isChecked());
            }
        });


        //删除商品
        viewHolder.iv_shopcar_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //一个对话框提示一下
                AlertDialog alert = new AlertDialog.Builder(context).create();
                alert.setTitle("操作提示");
                alert.setMessage("您确定要将这些商品从购物车中移除吗？");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                //把接口回调放到确定按钮里
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                changeCountInterface.itemDelete(i);
                            }
                        });
                alert.show();
            }
        });
        //判断是不是编辑状态
        if (isShow){
            //如果是编辑状态，增加，减少，复选框，删除按钮显示
            viewHolder.ck_shopcar_chose.setVisibility(View.VISIBLE);
            viewHolder.iv_shopcar_delete.setVisibility(View.VISIBLE);
            viewHolder.iv_shopcar_sub.setVisibility(View.VISIBLE);
            viewHolder.iv_shopcar_add.setVisibility(View.VISIBLE);
        }else {
            //如果不是是编辑状态，增加，减少，复选框，删除按钮隐藏
            viewHolder.ck_shopcar_chose.setVisibility(View.GONE);
            viewHolder.iv_shopcar_delete.setVisibility(View.GONE);
            viewHolder.iv_shopcar_sub.setVisibility(View.GONE);
            viewHolder.iv_shopcar_add.setVisibility(View.GONE);
        }



    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox ck_shopcar_chose;
        private ImageView iv_shopcar_pic;
        private TextView tv_shopcar_name;
        private TextView tv_shopcar_price;
        private TextView iv_shopcar_sub;
        private TextView tv_shopcar_num;
        private TextView iv_shopcar_add;
        private ImageView iv_shopcar_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ck_shopcar_chose = itemView.findViewById(R.id.ck_shopcar_chose);
            iv_shopcar_pic = itemView.findViewById(R.id.iv_shopcar_pic);
            tv_shopcar_name = itemView.findViewById(R.id.tv_shopcar_name);
            tv_shopcar_price = itemView.findViewById(R.id.tv_shopcar_price);
            iv_shopcar_sub = itemView.findViewById(R.id.iv_shopcar_sub);
            tv_shopcar_num = itemView.findViewById(R.id.tv_shopcar_num);
            iv_shopcar_add = itemView.findViewById(R.id.iv_shopcar_add);
            iv_shopcar_delete = itemView.findViewById(R.id.iv_shopcar_delete);
        }
    }

    /*
     *单选框的接口回调
     * */
    private CheckInterface checkInterface;

    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    //
    public interface CheckInterface {
        //元素位置，和选中元素的状态
        void checkGroup(int position, boolean isChecked);
    }




    /*
     *改变数量的接口
     * */
    private ChangeCountInterface changeCountInterface;

    public void setChangeCountInterface(ChangeCountInterface changeCountInterface) {
        this.changeCountInterface = changeCountInterface;
    }

    public interface ChangeCountInterface {
        //增加操作
        //元素的位置，增加后变化的显示，和是否选中
        void doIncrease(int position, View shouCountView, boolean isCheck);

        //删减操作
        void doDelete(int position, View shouCountView, boolean isCheck);

        //删除条目//选中的条目
        void itemDelete(int position);

    }


}
