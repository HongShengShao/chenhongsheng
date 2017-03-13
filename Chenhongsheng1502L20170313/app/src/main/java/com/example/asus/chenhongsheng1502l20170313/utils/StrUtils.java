package com.example.asus.chenhongsheng1502l20170313.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by asus on 2017/3/13.
 */
public class StrUtils {
    public static String str(InputStream inputStream){

        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        byte[] b=new byte[1024];
        int length=0;
        try {
            while((length=inputStream.read(b))!=-1){
                byteArrayOutputStream.write(b,0,length);
            }
            return byteArrayOutputStream.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        {

        }
        return null;
    }
}
