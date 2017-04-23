package com.bawie.resist_paste_lane_project.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
 * @data 2017/4/19 9:25
 */
public class EffectFragmentPager extends Fragment {

    private int position;
    private RecyclerView recyclerView;
//    private Integer[] integer=new Integer[]{14,13,29,28,15,37};
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    List<CommodityMaskBean.DataBean> dataBeanList= (List<CommodityMaskBean.DataBean>) msg.obj;
                    GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setAdapter(new CommodityMaskAdapter(getActivity(),dataBeanList));
                    break;
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position=getArguments().getInt("position");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(), R.layout.fragmentrecycleview,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        recyclerView= (RecyclerView) view.findViewById(R.id.fragment_recycleView);
        getData();
    }

    private void getData() {
        String url = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=91873&encode=68301f6ea0d6adcc0fee63bd21815d69&category_id=" + position;
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
