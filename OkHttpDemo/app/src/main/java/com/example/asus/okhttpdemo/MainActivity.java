package com.example.asus.okhttpdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String str= (String) msg.obj;
                    Log.i("xxx",str);
                    break;
                case 1:
                    String str2= (String) msg.obj;
                    Log.i("xxx",str2);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
        postData();
    }

    private void postData() {
        //创建OkHttpClient对象
        OkHttpClient mokHttpClient=new OkHttpClient();
        //创建url
        String url="http://www.93.gov.cn/93app/data.do?\" + \"channelId=\" + 0 ";
        //创建FormEncodingBuilder
        FormEncodingBuilder build=new FormEncodingBuilder();
        //将参数存入FormEncodingBuilder中
        build.add("startNum","0");
        //创建一个request
        Request request=new Request.Builder().url(url).post(build.build()).build();
        //new call
        Call call = mokHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String string = response.body().string();
                Message message = handler.obtainMessage(1, string);
                message.sendToTarget();
            }
        });

    }

    private void getData() {
    //创建OkHttpClient对象
    OkHttpClient mokHttpClient=new OkHttpClient();
     //创建一个request
    Request request=new Request.Builder().url("http://www.93.gov.cn/93app/data.do?" + "channelId=" + 0 + "&startNum=" + 0).build();
    //new call
    Call call = mokHttpClient.newCall(request);
    //请求加入调度
    //异步的方式
    call.enqueue(new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {

        }

        @Override
        public void onResponse(Response response) throws IOException {
            String string = response.body().string();
//            Message message = handler.obtainMessage(0, string);
//            message.sendToTarget();
        }
    });
//    try {
//        //阻塞式
//        Response response = call.execute();
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
}
}
