package com.example.shopping_mall.interfaces.bontract;

import com.example.shopping_mall.bean.HomePageBean;
import com.example.shopping_mall.interfaces.IBaseView;
import com.example.shopping_mall.interfaces.IPersenter;

public interface HomePageContract {
    interface View extends IBaseView {
        void  getHomePageBeanReturn(HomePageBean homePageBean);
    }
    interface Presenter extends IPersenter<View> {
        void getHomePageBean();
    }
}
