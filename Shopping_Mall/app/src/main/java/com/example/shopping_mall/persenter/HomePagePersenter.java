package com.example.shopping_mall.persenter;

import com.example.shopping_mall.base.BasePersenter;

import com.example.shopping_mall.bean.HomePageBean;
import com.example.shopping_mall.component.CommonSubscriber;
import com.example.shopping_mall.http.HttpManager;
import com.example.shopping_mall.interfaces.bontract.HomePageContract;
import com.example.shopping_mall.utils.RxUtils;

public class HomePagePersenter extends BasePersenter<HomePageContract.View> implements HomePageContract.Presenter {

    @Override
    public void getHomePageBean() {
        addSubscribe(HttpManager.getMyApi().getHomePage().compose(RxUtils.<HomePageBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<HomePageBean>(myView) {
                    @Override
                    public void onNext(HomePageBean homePageBean) {
                        myView.getHomePageBeanReturn(homePageBean);
                    }
                }));
    }
}
