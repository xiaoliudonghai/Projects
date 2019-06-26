package com.example.shopping_mall.apis;

import com.example.shopping_mall.bean.HomePageBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface MyApi {
    @GET("index/index")
    Flowable<HomePageBean> getHomePage();
}
