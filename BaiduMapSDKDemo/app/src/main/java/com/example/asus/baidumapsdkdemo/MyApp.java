package com.example.asus.baidumapsdkdemo;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * 1. 类的用途
 * 2. @author forever
 * 3. @date 2017/4/19 11:09
 */

public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
    }
}
