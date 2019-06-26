package com.example.shopping_mall.interfaces.bontract;

import com.example.shopping_mall.bean.SpecialBean;
import com.example.shopping_mall.bean.SpecialDetailsBean;
import com.example.shopping_mall.bean.SpecialDetailsXiangBean;
import com.example.shopping_mall.interfaces.IBaseView;
import com.example.shopping_mall.interfaces.IPersenter;

public interface SpecialDetailsContract {
    interface View extends IBaseView {
        void  getIndexReturn(SpecialDetailsBean specialDetailsBean);
        void  getDetailsIndexReturn(SpecialDetailsXiangBean specialDetailsXiangBean);
    }
    interface Presenter extends IPersenter<View> {
        void getIndex(int id);
        void getDetailsIndex(int id);
    }
}
