package com.example.shopping_mall.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shopping_mall.R;
import com.example.shopping_mall.adapter.SpecialAdapter;
import com.example.shopping_mall.base.BaseMvpFragment;
import com.example.shopping_mall.bean.SpecialBean;
import com.example.shopping_mall.interfaces.IPersenter;
import com.example.shopping_mall.interfaces.bontract.SpecialContract;
import com.example.shopping_mall.persenter.SpecialPersenter;
import com.example.shopping_mall.ui.SpcialDetails1Activity;
import com.example.shopping_mall.ui.SpecialDetailsActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecialFragment extends BaseMvpFragment implements SpecialContract.View {


    @BindView(R.id.Rlv_Special)
    RecyclerView RlvSpecial;
    Unbinder unbinder;
    private ArrayList<SpecialBean.DataBeanX.DataBean> list;
    private SpecialAdapter specialAdapter;

    @Override
    protected IPersenter createPersenter() {
        return new SpecialPersenter();
    }

    @Override
    protected int LayoutId() {
        return R.layout.fragment_special;
    }

    @Override
    protected void initView() {
        super.initView();
        //设置布局管理器
        RlvSpecial.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置集合
        list = new ArrayList<>();
        //初始化适配器
        specialAdapter = new SpecialAdapter(list, getActivity());
        RlvSpecial.setAdapter(specialAdapter);

        //接口回调
        specialAdapter.setClick(new SpecialAdapter.Click() {
            @Override
            public void click(int id) {
                Intent intent = new Intent(getActivity(), SpcialDetails1Activity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        ((SpecialPersenter) myPersenter).getIndex();
    }

    @Override
    public void getIndexReturn(SpecialBean specialBean) {
        if (specialBean!=null){
            list.addAll(specialBean.getData().getData());
            specialAdapter.notifyDataSetChanged();
        }
    }

}
