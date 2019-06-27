package com.example.shopping_mall.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopping_mall.R;
import com.example.shopping_mall.adapters.MyAdapter;
import com.example.shopping_mall.bean.ShopCarBean;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingCartFragment extends Fragment {

    @BindView(R.id.shop_car_ll)
    LinearLayout shopCarLl;
    @BindView(R.id.shop_car_rv)
    RecyclerView shopCarRv;
    @BindView(R.id.shop_car_rbAll)
    CheckBox shopCarRbAll;
    @BindView(R.id.shop_car_price)
    TextView shopCarPrice;
    @BindView(R.id.shop_car_edit)
    TextView shopCarEdit;
    @BindView(R.id.shop_car_ll2)
    RelativeLayout shopCarLl2;
    Unbinder unbinder;
    @BindView(R.id.shop_car_quanxuan)
    TextView shopCarQuanxuan;
    //    @BindView(R.id.shop_car_kuaihao)
//    TextView shopCarKuaihao;
    @BindView(R.id.shop_car_xiadan)
    Button shopCarXiadan;
    private ArrayList<ShopCarBean> shopCarBeans;
    private MyAdapter adapter;
    private boolean flag = false;
    private int Allitemcount;
    private double Allprice;
    private int Allcount;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;

    }

    private void initView() {
        shopCarBeans = new ArrayList<>();
        shopCarBeans.add(new ShopCarBean("2019夏季爆款T恤", 1, 1));
        shopCarBeans.add(new ShopCarBean("2019夏季爆款外套", 1, 1));
        shopCarBeans.add(new ShopCarBean("2019夏季爆款牛仔裤", 1, 1));
        shopCarBeans.add(new ShopCarBean("2019夏季爆款帽子", 1, 1));
        shopCarBeans.add(new ShopCarBean("2019夏季爆款背心", 1.2, 1));
        shopCarBeans.add(new ShopCarBean("2019夏季爆款内裤", 1.2, 1));
        shopCarBeans.add(new ShopCarBean("2019夏季爆款围巾", 1, 1));
        adapter = new MyAdapter(getActivity(), shopCarBeans);
        shopCarRv.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        shopCarRv.setLayoutManager(manager);
        adapter.notifyDataSetChanged();
        adapter.setShow(false);
        adapter.setChangeCountInterface(new MyAdapter.ChangeCountInterface() {
            @Override
            public void doIncrease(int position, View shouCountView, boolean isCheck) {
                //增加数量方法
                ShopCarBean carBean = shopCarBeans.get(position);//得到当前条目上的对象
                int count = carBean.getCount();//得到当前条目上的数量
                count++;
                ((TextView) shouCountView).setText(count + "");//把传过来的的显示数量的布局改变

                //将当前得到的条目数量重新赋值到实体类中去，因为算数量是通过实体类字段来改变的，！
                carBean.setCount(count);
//              Toast.makeText(getActivity(), "我点到了", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                totalprices();//改变之后重新计算
            }

            @Override
            public void doDelete(int position, View shouCountView, boolean isCheck) {
                //减少数量
                ShopCarBean shopCarBean = shopCarBeans.get(position);
                int count = shopCarBean.getCount();
                if (count == 1) {
                    //当数量等于是，按钮响应事件取消
                    Toast.makeText(getActivity(), "不能在少了，亲~~", Toast.LENGTH_SHORT).show();
                    return;
                }
                count--;
                ((TextView) shouCountView).setText(count + "");//把传过来的的显示数量的布局改变
                //减的时候给实体类的数量设置
                shopCarBean.setCount(count);
                adapter.notifyDataSetChanged();
                totalprices();//计算

            }

            @Override
            public void itemDelete(int position) {
                //删除条目
                shopCarBeans.remove(position);
                //删除
                totalprices();
                adapter.setList(shopCarBeans);
                adapter.notifyDataSetChanged();
            }
        });

        adapter.setCheckInterface(new MyAdapter.CheckInterface() {
            @Override
            public void checkGroup(int position, boolean isChecked) {
                //得到当前选中的对象
                ShopCarBean shopCarBean = shopCarBeans.get(position);
                shopCarBean.setChoosed(isChecked);//把当前对象的状态设置为点击后传过来的状态，做一个绑定
                //是选中状态
                if (isChecked){
                    //全选按钮就给他关掉
                    shopCarRbAll.setChecked(false);
                }else {
                    shopCarRbAll.setChecked(false);
                }
                adapter.notifyDataSetChanged();
                //调用计算的方法
                totalprices();
            }
        });
    }


    @OnClick({R.id.shop_car_rbAll, R.id.shop_car_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //全选按钮
            case R.id.shop_car_rbAll:
                //集合的长度不能等于0
                if (shopCarBeans.size() != 0) {
                    //全选按钮如果是选中状态，就把数据集合里所有的状态设置为选中状态
                    if (shopCarRbAll.isChecked()) {
                        for (int i = 0; i < shopCarBeans.size(); i++) {
                            shopCarBeans.get(i).setChoosed(true);
                        }
                        adapter.setList(shopCarBeans);
                        adapter.notifyDataSetChanged();
                        totalprices();
                    } else {
                        //如果不是，就设置为未选中状态
                        for (int i = 0; i < shopCarBeans.size(); i++) {
                            shopCarBeans.get(i).setChoosed(false);
                        }
                        shopCarQuanxuan.setText("全选()");
                        shopCarPrice.setText("￥");
                        adapter.setList(shopCarBeans);
                        adapter.notifyDataSetChanged();
                    }

                }
                break;
            case R.id.shop_car_edit:
                //编辑按钮
                //把当前的一个选中//未选中状态赋过去
                flag = !flag;
                if (flag) {//true
                    //当前是完成状态，按钮设置为“编辑”
                    shopCarEdit.setText("编辑");
                    //并且改变当前的显示状态
                    adapter.setList(shopCarBeans);
                    adapter.setShow(false);
                    shopCarQuanxuan.setText("");
                    shopCarRbAll.setVisibility(View.GONE);
                    shopCarPrice.setVisibility(View.GONE);

                    adapter.notifyDataSetChanged();
                } else {//false//当前是编辑状态，设置按钮为
                    shopCarEdit.setText("完成");
                    adapter.setList(shopCarBeans);
                    adapter.setShow(true);
                    shopCarQuanxuan.setVisibility(View.VISIBLE);
                    shopCarPrice.setVisibility(View.VISIBLE);
                    shopCarRbAll.setVisibility(View.VISIBLE);
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }

    /*
    * 计算价钱和数量的方法，
    * */
    public void totalprices() {
        //初始化数据
        Allitemcount = 0;
        Allprice = 0;
        Allcount = 0;
        for (int i = 0; i < shopCarBeans.size(); i++) {
            ShopCarBean shopCarBean = shopCarBeans.get(i);
            if (shopCarBean.isChoosed()) {
                Allitemcount++;
                int count = shopCarBean.getCount();
                Allcount = Allcount + count;
                Allprice = Allprice + shopCarBean.getPrice() * shopCarBean.getCount();

            }
        }
        //保留俩位小数,不然计算的时候无规律小数位错乱
        DecimalFormat df = new DecimalFormat("0.00");
        shopCarPrice.setText("￥" + String.valueOf(df.format(Allprice)));
        shopCarQuanxuan.setText("全选（" + Allcount + " )");
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
