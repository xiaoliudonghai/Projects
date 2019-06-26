package com.example.shopping_mall.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopping_mall.R;
import com.example.shopping_mall.adapter.Classify_Fragment_Adapter;
import com.example.shopping_mall.base.BaseMvpFragment;
import com.example.shopping_mall.bean.ClassifyTabBean;
import com.example.shopping_mall.interfaces.bontract.ClassifyContract;
import com.example.shopping_mall.persenter.ClassifyPresenter;
import com.example.shopping_mall.ui.SearchActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassifyFragment extends BaseMvpFragment<ClassifyContract.View, ClassifyContract.Presenter> implements ClassifyContract.View {


    @BindView(R.id.Image)
    ImageView Image;
    @BindView(R.id.Ed_name)
    EditText EdName;
    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.txt)
    TextView txt;
    @BindView(R.id.vertical)
    VerticalTabLayout vertical;
    @BindView(R.id.Recycler)
    RecyclerView Recycler;
    Unbinder unbinder;
    private ArrayList<ClassifyTabBean.DataBean.CategoryListBean> categoryListBeans;
    private LinearLayoutManager mLayoutManager;
    private Classify_Fragment_Adapter classify_fragment_adapter;
    private boolean isScroll;


    @Override
    protected ClassifyContract.Presenter createPersenter() {
        return new ClassifyPresenter();
    }

    @Override
    protected int LayoutId() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void initData() {
        ((ClassifyPresenter) myPersenter).Classify();
    }

    @Override
    public void getClassify(ClassifyTabBean classifyTabBean) {
        categoryListBeans = new ArrayList<>();
        List<ClassifyTabBean.DataBean.CategoryListBean> categoryList = classifyTabBean.getData().getCategoryList();
        categoryListBeans.addAll(categoryList);
        classify_fragment_adapter = new Classify_Fragment_Adapter(categoryListBeans, getContext());
        Recycler.setAdapter(classify_fragment_adapter);

        vertical.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                LinearLayoutManager layoutManager = (LinearLayoutManager)Recycler.getLayoutManager();
                layoutManager.scrollToPositionWithOffset(position, 0);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });

        Recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //重写该方法主要是判断recyclerview是否在滑动
                //0停止 ，1,2都是滑动
                if (newState == 0) {
                    isScroll = false;
                } else {
                    isScroll = true;
                }
                LinearLayoutManager layoutManager = (LinearLayoutManager) Recycler.getLayoutManager();
                //可见的第一条条目
                int top = layoutManager.findFirstVisibleItemPosition();
                vertical.setTabSelected(top);
            }


            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //这个主要是recyclerview滑动时让tab定位的方法

                                               /*recyclerView : 当前滚动的view
                                dx : 水平滚动距离
                                dy : 垂直滚动距离
                                dx > 0 时为手指向左滚动,列表滚动显示右面的内容
                                dx < 0 时为手指向右滚动,列表滚动显示左面的内容
                                dy > 0 时为手指向上滚动,列表滚动显示下面的内容
                                dy < 0 时为手指向下滚动,列表滚动显示上面的内容*/
                LinearLayoutManager layoutManager = (LinearLayoutManager) Recycler.getLayoutManager();
                //可见的第一条条目
                int top = layoutManager.findFirstVisibleItemPosition();
                //可见的最后一条条目
                int bottom = layoutManager.findLastVisibleItemPosition();
                if (isScroll) {
                    if (dy > 0) {
                        vertical.setTabSelected(top);
                    }
                }
            }
        });

        vertical.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return categoryListBeans.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new TabView.TabTitle.Builder()
                        .setContent(categoryListBeans.get(position).getName())
                        .build()
                        ;
            }

            @Override
            public int getBackground(int position) {
                return 0;
            }
        });
    }

    @Override
    protected void initView() {
        super.initView();

        mLayoutManager = new LinearLayoutManager(getContext());
        Recycler.setLayoutManager(mLayoutManager);

        Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

    }
}
