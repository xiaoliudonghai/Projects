package com.example.shopping_mall.apis;

import com.example.shopping_mall.bean.BannerBean;
import com.example.shopping_mall.bean.ClassifyTabBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface MyApi {
    @GET("banner/json")
    Flowable<BannerBean> getHomePage();

    //分类tablayout接口
    @GET("catalog/index")
    Flowable<ClassifyTabBean> classifybean();
}
