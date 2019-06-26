package com.example.shopping_mall.persenter;

import com.example.shopping_mall.base.BasePersenter;

import com.example.shopping_mall.bean.SpecialBean;
import com.example.shopping_mall.component.CommonSubscriber;
import com.example.shopping_mall.http.HttpManager;
import com.example.shopping_mall.interfaces.bontract.SpecialContract;
import com.example.shopping_mall.utils.RxUtils;

public class SpecialPersenter extends BasePersenter<SpecialContract.View> implements SpecialContract.Presenter {
    @Override
    public void getIndex() {
        addSubscribe(HttpManager.getMyApi().getSpecial().compose(RxUtils.<SpecialBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<SpecialBean>(myView) {
                    @Override
                    public void onNext(SpecialBean sou) {
                        myView.getIndexReturn(sou);
                    }
                }));
    }
}
