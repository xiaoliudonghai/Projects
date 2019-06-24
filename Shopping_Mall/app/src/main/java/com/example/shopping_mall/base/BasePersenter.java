package com.example.shopping_mall.base;


import com.example.shopping_mall.interfaces.IBaseView;
import com.example.shopping_mall.interfaces.IPersenter;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePersenter<V extends IBaseView> implements IPersenter<V> {
    protected V myView;
    private WeakReference<V> weakReference;


    //rxjava2 数据加载的时候，界面回收一起的数据内存泄漏
    protected CompositeDisposable compositeDisposable;
    @Override
    public void attchView(V view) {
        //弱引用 view
        weakReference = new WeakReference<>(view);
        this.myView=weakReference.get();
    }

    //解绑view的关联，同时解绑数据加载的关闭，避免出现内存泄漏
    @Override
    public void detachView() {
        this.myView=null;
        unSubscribe();

    }
    //解绑观察者和被观察者
    private void unSubscribe() {
        if (compositeDisposable!=null){
            compositeDisposable.clear();
        }
    }


    //添加观察者和被观察者的操作类
    protected  void addSubscribe(Disposable disposable){
        if (compositeDisposable==null){
             compositeDisposable = new CompositeDisposable();
             compositeDisposable.add(disposable);
        }
    }
}
