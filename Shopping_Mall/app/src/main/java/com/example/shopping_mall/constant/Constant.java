package com.example.shopping_mall.constant;



import com.example.shopping_mall.app.MyApplication;

import java.io.File;

public class Constant {
    public static final int LOGIN=0;
    public static final int RETC=1;
    public static final int OKETC=2;
    public static final String url="http://cdwan.cn:8360/api/";
   // public static final String url="https://www.wanandroid.com/";
    //网络缓存的地址
    public static final String PATH_DATA = MyApplication.myApplication.getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/lianxi";
}
