package com.example.shopping_mall.persenter;

import android.util.Log;

import com.example.shopping_mall.base.BasePersenter;
import com.example.shopping_mall.bean.ClassifyTabBean;
import com.example.shopping_mall.component.CommonSubscriber;
import com.example.shopping_mall.http.HttpManager;
import com.example.shopping_mall.interfaces.bontract.ClassifyContract;
import com.example.shopping_mall.utils.RxUtils;

import static android.support.constraint.Constraints.TAG;

public class ClassifyPresenter extends BasePersenter<ClassifyContract.View> implements ClassifyContract.Presenter{
    @Override
    public void Classify() {
        addSubscribe(HttpManager.getMyApi().classifybean()
        .compose(RxUtils.<ClassifyTabBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<ClassifyTabBean>(myView) {
                    @Override
                    public void onNext(ClassifyTabBean classifyTabBean) {
                        myView.getClassify(classifyTabBean);
                        Log.e(TAG, "请求数据: "+classifyTabBean );
                    }
                })
        );
    }
}
