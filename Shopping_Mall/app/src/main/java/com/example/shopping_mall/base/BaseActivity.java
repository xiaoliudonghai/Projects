package com.example.shopping_mall.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
    protected Context context;
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LayoutId());
        bind = ButterKnife.bind(this);
        context=this;
        initView();
        initMvp();

    }

    protected  void initMvp(){};
    protected  void initView(){};

    protected abstract int LayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind!=null){
            bind.unbind();
        }
    }
}
