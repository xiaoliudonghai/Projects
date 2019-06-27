package com.example.shopping_mall.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping_mall.R;
import com.example.shopping_mall.adapter.SpecialDetailsAdapter;
import com.example.shopping_mall.base.BaseMvpActivity;
import com.example.shopping_mall.bean.SpecialDetailsBean;
import com.example.shopping_mall.interfaces.IPersenter;
import com.example.shopping_mall.interfaces.bontract.SpecialDetails1Contract;
import com.example.shopping_mall.persenter.SpecialDetails1Persenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SpcialDetails1Activity extends BaseMvpActivity implements SpecialDetails1Contract.View {

    @BindView(R.id.iv_specialdetails)
    ImageView ivSpecialdetails;
    @BindView(R.id.tv_specialdetails)
    TextView tvSpecialdetails;
    @BindView(R.id.tv2_specialdetails)
    TextView tv2Specialdetails;
    @BindView(R.id.tv3_specialdetails)
    TextView tv3Specialdetails;
    @BindView(R.id.sub_web_collectTv)
    TextView subWebCollectTv;
    @BindView(R.id.sub_web_shopTv)
    TextView subWebShopTv;
    @BindView(R.id.sub_web_serverTv)
    TextView subWebServerTv;
    @BindView(R.id.ll_specialdetails)
    LinearLayout llSpecialdetails;
    @BindView(R.id.tv4_specialdetails)
    TextView tv4Specialdetails;
    @BindView(R.id.bt_spcial_details)
    Button btSpcialDetails;
    @BindView(R.id.bt_spcial1_details)
    Button btSpcial1Details;
    private int id;

    @Override
    protected int LayoutId() {
        return R.layout.activity_spcial_details1;
    }

    @Override
    protected IPersenter createPersenter() {
        return new SpecialDetails1Persenter();
    }

    @Override
    protected void initView() {
        super.initView();
        id = getIntent().getIntExtra("id", 0);
    }

    @Override
    protected void initData() {
        super.initData();
        ((SpecialDetails1Persenter) myPersenter).getIndex(id);
    }

    @Override
    public void getIndexReturn(SpecialDetailsBean specialDetailsBean) {
        if (specialDetailsBean != null) {
            Glide.with(this).load(specialDetailsBean.getData().getScene_pic_url()).into(ivSpecialdetails);
            tvSpecialdetails.setText(specialDetailsBean.getData().getTitle());
            tv2Specialdetails.setText(specialDetailsBean.getData().getSubtitle());
            tv3Specialdetails.setText(specialDetailsBean.getData().getPrice_info() + "元");
        }
    }

    @OnClick({R.id.sub_web_collectTv, R.id.sub_web_shopTv, R.id.sub_web_serverTv, R.id.bt_spcial_details, R.id.bt_spcial1_details,R.id.tv4_specialdetails})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sub_web_collectTv:
                break;
            case R.id.sub_web_shopTv:
                break;
            case R.id.sub_web_serverTv:
                break;
            case R.id.bt_spcial_details:
                break;
            case R.id.bt_spcial1_details:
                break;
            case R.id.tv4_specialdetails:
                Intent intent = new Intent(this, SpecialDetailsActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
                break;
        }
    }



}
