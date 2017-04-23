package com.bawie.resist_paste_lane_project.application;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.x;

/**
 * @author 陈宏升
 * @类的作用：
 * @data 2017/4/20 19:36
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration imageLoaderConfiguration=new ImageLoaderConfiguration.Builder(getApplicationContext()).memoryCacheExtraOptions(400,400).build();
        ImageLoader.getInstance().init(imageLoaderConfiguration);
        x.Ext.init(this);
    }
}
