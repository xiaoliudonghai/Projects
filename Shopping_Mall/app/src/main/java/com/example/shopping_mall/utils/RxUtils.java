package com.example.shopping_mall.utils;


import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxUtils {
    /**
     * rxjava 线程切换统一处理
     * @param <T>
     * @return
     */
    //背压是线程抽取
    public static <T> FlowableTransformer<T, T> rxScheduler(){
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                //线程切换统一处理
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
