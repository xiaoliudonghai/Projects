package com.example.shopping_mall.interfaces.bontract;

import com.example.shopping_mall.bean.SpecialBean;
import com.example.shopping_mall.interfaces.IBaseView;
import com.example.shopping_mall.interfaces.IPersenter;

public interface SpecialContract {
    interface View extends IBaseView {
        void  getIndexReturn(SpecialBean specialBean);
    }
    interface Presenter extends IPersenter<View> {
        void getIndex();
    }
}
