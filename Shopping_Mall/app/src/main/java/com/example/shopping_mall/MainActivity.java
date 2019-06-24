package com.example.shopping_mall;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.example.shopping_mall.base.BaseActivity;
import com.example.shopping_mall.fragment.ClassifyFragment;
import com.example.shopping_mall.fragment.HomePageFragment;
import com.example.shopping_mall.fragment.MyFragment;
import com.example.shopping_mall.fragment.ShoppingCartFragment;
import com.example.shopping_mall.fragment.SpecialFragment;
import com.example.shopping_mall.utils.FragmentUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.toob)
    Toolbar toob;
    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.tab)
    TabLayout tab;
    private ArrayList<Fragment> list;
    private FragmentManager fm;

    @Override
    protected int LayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();

        //设置Toolbar
        toob.setTitle("");
        setSupportActionBar(toob);
        //设置Fragment管理器
        fm = getSupportFragmentManager();
        //设置fragment集合
        initFragment();
        //给TabLayout设置图片和文字
        initTab();

        //默认显示首页
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.frame,list.get(0));
        fragmentTransaction.commit();
        //TabLyout和Fragment之间的关联
        initTabFragment();
    }

    private void initTab() {
    tab.addTab(tab.newTab().setText("首页").setIcon(R.drawable.homepage));
    tab.addTab(tab.newTab().setText("专题").setIcon(R.drawable.special));
    tab.addTab(tab.newTab().setText("分类").setIcon(R.drawable.classify));
    tab.addTab(tab.newTab().setText("购物车").setIcon(R.drawable.shoppingcart));
    tab.addTab(tab.newTab().setText("我的").setIcon(R.drawable.my));
    }

    private void initTabFragment() {
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment fragment = list.get(position);
                FragmentUtils.addFragment(fm,fragment.getClass(),R.id.frame,null);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initFragment() {
        list = new ArrayList<>();
        list.add(new HomePageFragment());
        list.add(new SpecialFragment());
        list.add(new ClassifyFragment());
        list.add(new ShoppingCartFragment());
        list.add(new MyFragment());
    }
}
