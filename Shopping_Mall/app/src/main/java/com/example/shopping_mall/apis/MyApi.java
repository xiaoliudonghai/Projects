package com.example.shopping_mall.apis;


import com.example.shopping_mall.bean.HomePageBean;
import com.example.shopping_mall.bean.SpecialBean;
import com.example.shopping_mall.bean.SpecialDetailsBean;
import com.example.shopping_mall.bean.SpecialDetailsXiangBean;
import com.example.shopping_mall.bean.ClassifyTabBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApi {
	//ר����б�
	@GET("topic/list")
	Flowable<SpecialBean> getSpecial();
	//ר�������
	@GET("topic/detail")
	Flowable<SpecialDetailsBean> getSpecialDetails(@Query("id") int id);
	//���ר��
	@GET("topic/related")
	Flowable<SpecialDetailsXiangBean> getSpecialDetailsXiang(@Query("id") int id);

	//����tablayout�ӿ�
	@GET("catalog/index")
	Flowable<ClassifyTabBean> classifybean();
	@GET("index/index")
	Flowable<HomePageBean> getHomePage();
}
