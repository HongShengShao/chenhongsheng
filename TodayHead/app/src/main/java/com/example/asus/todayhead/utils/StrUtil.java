package com.example.asus.todayhead.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by asus on 2017/3/13.
 */
public class StrUtil {
    public static String str(InputStream inputStream){

        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        byte[] b=new byte[1024];
        int length=0;
        try {
            while ((length=inputStream.read(b))!=-1){
              byteArrayOutputStream.write(b,0,length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toString();
    }
}
