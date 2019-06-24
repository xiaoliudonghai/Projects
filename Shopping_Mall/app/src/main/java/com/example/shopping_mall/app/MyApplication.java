package com.example.shopping_mall.app;

import android.app.Application;

public class MyApplication extends Application {
    public static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication=this;
    }

    public static MyApplication getMyApplication() {
        return myApplication;
    }
}
