package com.example.shopping_mall.component;

import android.text.TextUtils;
import com.example.shopping_mall.interfaces.IBaseView;

import io.reactivex.subscribers.ResourceSubscriber;

public abstract class  CommonSubscriber <T> extends ResourceSubscriber<T> {
    private IBaseView iBaseView;
    private String errorMag;
    private boolean isShowErrorState=false;

    public CommonSubscriber(IBaseView iBaseView) {
        this.iBaseView = iBaseView;
    }

    public CommonSubscriber(IBaseView iBaseView, String errorMag) {
        this.iBaseView = iBaseView;
        this.errorMag = errorMag;
    }

    public CommonSubscriber(IBaseView iBaseView, String errorMag, boolean isShowErrorState) {
        this.iBaseView = iBaseView;
        this.errorMag = errorMag;
        this.isShowErrorState = isShowErrorState;
    }

    public CommonSubscriber(IBaseView iBaseView, boolean isShowErrorState) {
        this.iBaseView = iBaseView;
        this.isShowErrorState = isShowErrorState;
    }

    @Override
    public void onError(Throwable t) {
        if (iBaseView==null){
            return;
        }
        if (errorMag!=null&& TextUtils.isEmpty(errorMag)){
            iBaseView.shouError(errorMag);
        }
    }

    @Override
    public void onComplete() {

    }
}
