package com.example.shopping_mall.persenter;

import com.example.shopping_mall.base.BasePersenter;
import com.example.shopping_mall.bean.SpecialDetailsBean;
import com.example.shopping_mall.bean.SpecialDetailsXiangBean;
import com.example.shopping_mall.component.CommonSubscriber;
import com.example.shopping_mall.http.HttpManager;
import com.example.shopping_mall.interfaces.bontract.SpecialDetails1Contract;
import com.example.shopping_mall.interfaces.bontract.SpecialDetailsContract;
import com.example.shopping_mall.utils.RxUtils;

public class SpecialDetails1Persenter extends BasePersenter<SpecialDetails1Contract.View> implements SpecialDetails1Contract.Presenter {
    @Override
        public void getIndex(int id) {
            addSubscribe(HttpManager.getMyApi().getSpecialDetails(id).compose(RxUtils.<SpecialDetailsBean>rxScheduler())
                    .subscribeWith(new CommonSubscriber<SpecialDetailsBean>(myView) {
                        @Override
                        public void onNext(SpecialDetailsBean sou) {
                            myView.getIndexReturn(sou);
                        }
                    }));
    }
}
