package com.example.shopping_mall.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shopping_mall.R;
import com.example.shopping_mall.adapters.HomePage_Adapters;
import com.example.shopping_mall.adapters.HomePage_Rlv_Adapters;
import com.example.shopping_mall.adapters.HomePage_Tab_Adapters;
import com.example.shopping_mall.persenter.HomePagePersenter;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends BaseMvpFragment implements HomePageContract.View {


    @BindView(R.id.rv_homepage)
    RecyclerView rvHomepage;
    Unbinder unbinder;
    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder1;
    @BindView(R.id.rlv)
    RecyclerView rlv;
    Unbinder unbinder2;
    private List<HomePageBean.DataBean.BannerBean> bannerBeans;
    private List<HomePageBean.DataBean.ChannelBean> channelBeans;
    private HomePage_Adapters homePage_adapters;
    private HomePage_Tab_Adapters homePage_tab_adapters;
    private ArrayList<HomePageBean.DataBean.BrandListBean> brandListBeans;
    private HomePage_Rlv_Adapters homePage_rlv_adapters;

    @Override
    protected IPersenter createPersenter() {
        return new HomePagePersenter();
    }

    @Override
    protected int LayoutId() {
        return R.layout.fragment_home_page;
    }

    @Override
    public void getHomePageBeanReturn(HomePageBean homePageBean) {
        List<HomePageBean.DataBean.BannerBean> banner = homePageBean.getData().getBanner();
        bannerBeans.addAll(banner);
        List<HomePageBean.DataBean.ChannelBean> channel = homePageBean.getData().getChannel();
        channelBeans.addAll(channel);
        List<HomePageBean.DataBean.BrandListBean> brandList = homePageBean.getData().getBrandList();
        brandListBeans.addAll(brandList);
        homePage_rlv_adapters.notifyDataSetChanged();
        homePage_adapters.notifyDataSetChanged();
        homePage_tab_adapters.notifyDataSetChanged();
    }

    @Override
    protected void initView() {
        super.initView();
        rvHomepage.setLayoutManager(new LinearLayoutManager(getContext()));
        bannerBeans = new ArrayList<>();
        channelBeans = new ArrayList<>();
        homePage_adapters = new HomePage_Adapters(bannerBeans, getContext());
        rvHomepage.setAdapter(homePage_adapters);

        rv.setLayoutManager(new GridLayoutManager(getContext(), 5));
        homePage_tab_adapters = new HomePage_Tab_Adapters(channelBeans, getContext());
        rv.setAdapter(homePage_tab_adapters);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rlv.setLayoutManager(staggeredGridLayoutManager);
//        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        brandListBeans = new ArrayList<>();
        homePage_rlv_adapters = new HomePage_Rlv_Adapters(brandListBeans, getContext());
        rlv.setAdapter(homePage_rlv_adapters);

    }

    @Override
    protected void initData() {
        super.initData();
        ((HomePagePersenter) myPersenter).getHomePageBean();
    }


}
