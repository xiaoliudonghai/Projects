package com.example.shopping_mall.interfaces.bontract;

import com.example.shopping_mall.bean.SpecialDetailsBean;
import com.example.shopping_mall.bean.SpecialDetailsXiangBean;
import com.example.shopping_mall.interfaces.IBaseView;
import com.example.shopping_mall.interfaces.IPersenter;

public interface SpecialDetails1Contract {
    interface View extends IBaseView {
        void  getIndexReturn(SpecialDetailsBean specialDetailsBean);

    }
    interface Presenter extends IPersenter<View> {
        void getIndex(int id);

    }
}
