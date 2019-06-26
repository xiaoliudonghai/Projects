package com.example.shopping_mall.interfaces.bontract;

import com.example.shopping_mall.bean.ClassifyTabBean;
import com.example.shopping_mall.interfaces.IBaseView;
import com.example.shopping_mall.interfaces.IPersenter;

public interface ClassifyContract {
    interface View extends IBaseView{
        void getClassify(ClassifyTabBean classifyTabBean);
    }

    interface Presenter extends IPersenter<View>{
        void Classify();
    }
}
