package com.example.shopping_mall.interfaces.bontract;

import com.example.shopping_mall.bean.BannerBean;
import com.example.shopping_mall.interfaces.IBaseView;
import com.example.shopping_mall.interfaces.IPersenter;

public interface HomePageContract {
    interface View extends IBaseView {
        void  getIndexReturn(BannerBean homePageBean);
    }
    interface Presenter extends IPersenter<View> {
        void getIndex();
    }
}
