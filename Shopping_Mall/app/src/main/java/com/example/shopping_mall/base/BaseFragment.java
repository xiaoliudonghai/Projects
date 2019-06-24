package com.example.shopping_mall.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    protected Context context;
    private Unbinder bind;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(LayoutId(), container, false);
        bind = ButterKnife.bind(this,inflate);
        initView();
        initMvp();
        return inflate;
    }
    protected  void initMvp(){};
    protected  void initView(){};

    protected abstract int LayoutId();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bind!=null){
            bind.unbind();
        }
    }
}
