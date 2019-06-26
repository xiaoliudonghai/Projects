package com.example.shopping_mall.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.shopping_mall.R;
import com.example.shopping_mall.adapter.SpecialDetailsAdapter;
import com.example.shopping_mall.base.BaseMvpActivity;
import com.example.shopping_mall.bean.SpecialDetailsBean;
import com.example.shopping_mall.bean.SpecialDetailsXiangBean;
import com.example.shopping_mall.interfaces.IPersenter;
import com.example.shopping_mall.interfaces.bontract.SpecialDetailsContract;
import com.example.shopping_mall.persenter.SpecialDetailsPersenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpecialDetailsActivity extends BaseMvpActivity implements SpecialDetailsContract.View {


    @BindView(R.id.Rlv_SpecialDetails)
    RecyclerView RlvSpecialDetails;
    @BindView(R.id.toob_Special)
    Toolbar toobSpecial;
    private int id;
    private ArrayList<SpecialDetailsBean.DataBean> list;
    private ArrayList<SpecialDetailsXiangBean.DataBean> lists;
    private SpecialDetailsAdapter base;

    @Override
    protected IPersenter createPersenter() {
        return new SpecialDetailsPersenter();
    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_special_details;
    }

    @Override
    protected void initView() {
        super.initView();
        toobSpecial.setTitle("");
        setSupportActionBar(toobSpecial);

        id = getIntent().getIntExtra("id", 0);
        list = new ArrayList<>();
        lists = new ArrayList<>();
        RlvSpecialDetails.setLayoutManager(new LinearLayoutManager(this));
        base = new SpecialDetailsAdapter(list, lists, this);
        RlvSpecialDetails.setAdapter(base);
    }

    @Override
    protected void initData() {
        super.initData();
        ((SpecialDetailsPersenter) myPersenter).getIndex(id);
        ((SpecialDetailsPersenter) myPersenter).getDetailsIndex(id);
    }

    @Override
    public void getIndexReturn(SpecialDetailsBean specialDetailsBean) {
        if (specialDetailsBean != null) {
            list.add(specialDetailsBean.getData());
            base.notifyDataSetChanged();
        }
    }

    @Override
    public void getDetailsIndexReturn(SpecialDetailsXiangBean specialDetailsXiangBean) {
        if (specialDetailsXiangBean != null) {
            lists.addAll(specialDetailsXiangBean.getData());
            base.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        id=0;
    }
}
