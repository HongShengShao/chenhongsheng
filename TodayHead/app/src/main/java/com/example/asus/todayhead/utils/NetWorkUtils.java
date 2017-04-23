package com.example.asus.todayhead.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.asus.todayhead.activity.SystemSetActivity;

/**
 * Created by asus on 2017/3/25.
 */
public class NetWorkUtils {
    public static boolean isAvailable(Context context){

       ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();

        if (networkInfo!=null){
            return true;
        }
        return false;
    }

    public static boolean isMobile(Context context) {
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();

        if (networkInfo!=null&&networkInfo.getType()==connectivityManager.TYPE_MOBILE){
            return true;
        }
        return false;
    }
}
