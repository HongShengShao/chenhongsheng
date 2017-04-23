package com.bawie.resist_paste_lane_project.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bawie.resist_paste_lane_project.R;
import com.bawie.resist_paste_lane_project.adapter.CommodityMaskAdapter;
import com.bawie.resist_paste_lane_project.bean.CommodityMaskBean;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

/**
 * @author 陈宏升
 * @类的作用：
 * @data 2017/4/18 13:58
 */
public class GoodsShowActivity extends Activity {

    private RecyclerView recyclerView;
    private int id;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    List<CommodityMaskBean.DataBean> dataBeanList= (List<CommodityMaskBean.DataBean>) msg.obj;
                    GridLayoutManager gridLayoutManager=new GridLayoutManager(GoodsShowActivity.this,2);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setAdapter(new CommodityMaskAdapter(GoodsShowActivity.this,dataBeanList));
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        Intent intent=getIntent();
        id=intent.getIntExtra("id",0);
        recyclerView= (RecyclerView) findViewById(R.id.goods_recyclerView);
        getData();
    }

    private void getData() {
        String url = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=91873&encode=68301f6ea0d6adcc0fee63bd21815d69&category_id="+id;
        OkHttpClient okHttpClient = new OkHttpClient();
        Request requset = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(requset);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                CommodityMaskBean commodityMaskBean = gson.fromJson(string, CommodityMaskBean.class);
                List<CommodityMaskBean.DataBean> data = commodityMaskBean.getData();
                Message message1 = handler.obtainMessage(0, data);
                message1.sendToTarget();
            }
        });
    }
}
