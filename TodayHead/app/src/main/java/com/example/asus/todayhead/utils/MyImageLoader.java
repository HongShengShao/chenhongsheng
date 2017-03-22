package com.example.asus.todayhead.utils;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by asus on 2017/3/14.
 */
public class MyImageLoader extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);
        ImageLoaderConfiguration imageLoaderConfiguration=new ImageLoaderConfiguration.Builder(getApplicationContext()).memoryCacheExtraOptions(20,20).build();
        ImageLoader.getInstance().init(imageLoaderConfiguration);
    }
    {
        PlatformConfig.setQQZone("1106036236", "mjFCi0oxXZKZEWJs");
    }
}
