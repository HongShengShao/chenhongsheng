package com.example.asus.todayhead.utils;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.xutils.DbManager;
import org.xutils.x;

/**
 * Created by asus on 2017/3/14.
 */
public class MyImageLoader extends Application {


    private static DbManager.DaoConfig config;
    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);
        x.Ext.init(this);
        x.Ext.setDebug(false);
        ImageLoaderConfiguration imageLoaderConfiguration=new ImageLoaderConfiguration.Builder(getApplicationContext()).memoryCacheExtraOptions(20,20).build();
        ImageLoader.getInstance().init(imageLoaderConfiguration);
    }
    {
        PlatformConfig.setQQZone("1106036236", "mjFCi0oxXZKZEWJs");
    }

    public static DbManager getDb(){
        config=new DbManager.DaoConfig()
                //创建数据库的名称
                .setDbName("attention")
                //数据库版本号
                .setDbVersion(1)
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                    }
                });
        DbManager db=x.getDb(config);
        return db;
    }
}
