package com.example.asus.chenhongsheng1502l20170313.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by asus on 2017/3/13.
 */
public class NetWorkUtil {
    public static boolean isNetWork(Context context){

        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();

        if (networkInfo!=null){
            return true;
        }

        return false;
    }
}
