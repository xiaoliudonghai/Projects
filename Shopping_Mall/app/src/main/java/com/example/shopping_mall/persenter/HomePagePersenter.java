package com.example.shopping_mall.persenter;

import com.example.shopping_mall.base.BasePersenter;
import com.example.shopping_mall.bean.BannerBean;

import com.example.shopping_mall.component.CommonSubscriber;
import com.example.shopping_mall.http.HttpManager;
import com.example.shopping_mall.interfaces.bontract.HomePageContract;
import com.example.shopping_mall.utils.RxUtils;

public class HomePagePersenter extends BasePersenter<HomePageContract.View> implements HomePageContract.Presenter {
    @Override
    public void getIndex() {
        addSubscribe(HttpManager.getMyApi().getHomePage().compose(RxUtils.<BannerBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<BannerBean>(myView) {
                    @Override
                    public void onNext(BannerBean homePageBean) {
                        myView.getIndexReturn(homePageBean);
                    }
                }));
    }
}
