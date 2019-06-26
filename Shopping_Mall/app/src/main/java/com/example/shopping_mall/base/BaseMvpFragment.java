package com.example.shopping_mall.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



;import com.example.shopping_mall.R;
import com.example.shopping_mall.interfaces.IBaseView;
import com.example.shopping_mall.interfaces.IPersenter;
import com.example.shopping_mall.utils.SystemUtils;

public abstract class BaseMvpFragment<V extends IBaseView,P extends IPersenter> extends BaseFragment implements IBaseView {

    protected P myPersenter;
    @Override
    protected void initMvp (){
        super.initMvp();
       //判断是否有网
        if(!SystemUtils.checkNetWork()){
            //自定义布局实现无网络状态下的提示
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout, null);
            new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            getActivity().addContentView(inflate,null);
        }else{
            initView();
            myPersenter=createPersenter();
            myPersenter.attchView(this);
            initData();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myPersenter!=null){
            myPersenter.detachView();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (myPersenter!=null){
            myPersenter.attchView(this);
        }
    }

    //初始化数据
    protected  void initData(){};
    protected  void initView(){};
    //创建p
    protected abstract P createPersenter();

    @Override
    public void shouLoading() {

    }

    @Override
    public void shouError(String s) {

    }
}
