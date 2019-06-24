package com.example.shopping_mall.interfaces;

public interface IPersenter<V extends IBaseView> {
    void attchView(V view);
    void detachView();
}
