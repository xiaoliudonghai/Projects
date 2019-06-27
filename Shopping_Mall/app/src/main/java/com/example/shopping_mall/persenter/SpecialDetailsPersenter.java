package com.example.shopping_mall.persenter;

import com.example.shopping_mall.base.BasePersenter;
import com.example.shopping_mall.bean.SpecialBean;
import com.example.shopping_mall.bean.SpecialDetailsBean;
import com.example.shopping_mall.bean.SpecialDetailsXiangBean;
import com.example.shopping_mall.component.CommonSubscriber;
import com.example.shopping_mall.http.HttpManager;
import com.example.shopping_mall.interfaces.bontract.SpecialContract;
import com.example.shopping_mall.interfaces.bontract.SpecialDetailsContract;
import com.example.shopping_mall.utils.RxUtils;

public class SpecialDetailsPersenter extends BasePersenter<SpecialDetailsContract.View> implements SpecialDetailsContract.Presenter {



    @Override
    public void getDetailsIndex(int id) {
        addSubscribe(HttpManager.getMyApi().getSpecialDetailsXiang(id).compose(RxUtils.<SpecialDetailsXiangBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<SpecialDetailsXiangBean>(myView) {
                    @Override
                    public void onNext(SpecialDetailsXiangBean sou) {
                        myView.getDetailsIndexReturn(sou);
                    }
                }));
    }
}
