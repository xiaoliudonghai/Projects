package com.example.shopping_mall.fragment;


import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.TextView;

import com.example.shopping_mall.R;
import com.example.shopping_mall.base.BaseFragment;
import com.example.shopping_mall.base.BaseMvpFragment;
import com.example.shopping_mall.bean.BannerBean;
import com.example.shopping_mall.interfaces.IPersenter;
import com.example.shopping_mall.interfaces.bontract.HomePageContract;
import com.example.shopping_mall.persenter.HomePagePersenter;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends BaseFragment {


    @BindView(R.id.tv)
    TextView tv;
    Unbinder unbinder;


    @Override
    protected int LayoutId() {
        return R.layout.fragment_home_page;
    }
}
