package com.example.shopping_mall.apis;

import com.example.shopping_mall.bean.SpecialBean;
import com.example.shopping_mall.bean.SpecialDetailsBean;
import com.example.shopping_mall.bean.SpecialDetailsXiangBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApi {
    //专题的列表
    @GET("topic/list")
    Flowable<SpecialBean> getSpecial();
    //专题的详情
    @GET("topic/detail")
    Flowable<SpecialDetailsBean> getSpecialDetails(@Query("id") int id);
    //相关专题
    @GET("topic/related")
    Flowable<SpecialDetailsXiangBean> getSpecialDetailsXiang(@Query("id") int id);
}
