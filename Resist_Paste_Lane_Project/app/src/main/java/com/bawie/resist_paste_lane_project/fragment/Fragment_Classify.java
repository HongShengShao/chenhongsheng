package com.bawie.resist_paste_lane_project.fragment;

import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bawie.resist_paste_lane_project.R;
import com.bawie.resist_paste_lane_project.activity.CommodityShowActivity;
import com.bawie.resist_paste_lane_project.activity.EffectActivity;
import com.bawie.resist_paste_lane_project.activity.GoodsShowActivity;
import com.bawie.resist_paste_lane_project.activity.TwoEffectActivity;
import com.bawie.resist_paste_lane_project.adapter.MyClassifyGridViewAdapter;
import com.bawie.resist_paste_lane_project.bean.ClassifyGridViewAdapterBean;
import com.bawie.resist_paste_lane_project.adapter.ClassifyGridViewAdapterfu;
import com.bawie.resist_paste_lane_project.bean.ClassifyBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈宏升
 * @类的作用：
 * @data 2017/4/11 20:04
 */
public class Fragment_Classify extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView,myRecyclerView;
    private String url="http://m.yunifang.com/yunifang/mobile/category/list?random=96333&encode=bf3386e14fe5bb0bcef234baebca2414";
    private ArrayList<ClassifyGridViewAdapterBean> classifyGridViewAdapterBeanList=new ArrayList<>();
    private ImageView imageView_property_mask,fuzhi2,fuzhi3,fuzhi4,fuzhi5,fuzhi6,gongxiao1,gongxiao2,gongxiao3,gongxiao4,gongxiao5;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    List<ClassifyBean.DataBean.CategoryBean.ChildrenBean> children= (List<ClassifyBean.DataBean.CategoryBean.ChildrenBean>) msg.obj;
                    classifyGridViewAdapterBeanList.add(new ClassifyGridViewAdapterBean(children.get(0).getCat_name(),R.color.colorClassifyGridviewText1));
                    classifyGridViewAdapterBeanList.add(new ClassifyGridViewAdapterBean(children.get(1).getCat_name(),R.color.colorClassifyGridviewText2));
                    classifyGridViewAdapterBeanList.add(new ClassifyGridViewAdapterBean(children.get(2).getCat_name(),R.color.colorClassifyGridviewText3));
                    classifyGridViewAdapterBeanList.add(new ClassifyGridViewAdapterBean(children.get(3).getCat_name(),R.color.colorClassifyGridviewText4));
                    classifyGridViewAdapterBeanList.add(new ClassifyGridViewAdapterBean(children.get(4).getCat_name(),R.color.colorClassifyGridviewText5));
                    classifyGridViewAdapterBeanList.add(new ClassifyGridViewAdapterBean(children.get(5).getCat_name(),R.color.colorClassifyGridviewText6));
                    GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setAdapter(new ClassifyGridViewAdapterfu(getActivity(),classifyGridViewAdapterBeanList));
                    recyclerView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    recyclerView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return false;
                        }
                    });
                    break;
                case 1:
                    List<ClassifyBean.DataBean.GoodsBriefBean> goodsBriefBeanList= (List<ClassifyBean.DataBean.GoodsBriefBean>) msg.obj;
                    GridLayoutManager myGridLayoutManager=new GridLayoutManager(getActivity(),2);
                    myRecyclerView.setLayoutManager(myGridLayoutManager);
                    myRecyclerView.setAdapter(new MyClassifyGridViewAdapter(getActivity(),goodsBriefBeanList));
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_classify,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        recyclerView= (RecyclerView) view.findViewById(R.id.classify_recyclerView_fu);
        myRecyclerView= (RecyclerView) view.findViewById(R.id.classify_recyclerView);
        imageView_property_mask= (ImageView) view.findViewById(R.id.fuzhi1);
        fuzhi2= (ImageView) view.findViewById(R.id.fuzhi2);
        fuzhi3= (ImageView) view.findViewById(R.id.fuzhi3);
        fuzhi4= (ImageView) view.findViewById(R.id.fuzhi4);
        fuzhi5= (ImageView) view.findViewById(R.id.fuzhi5);
        fuzhi6= (ImageView) view.findViewById(R.id.fuzhi6);
        gongxiao1= (ImageView) view.findViewById(R.id.gongxiao1);
        gongxiao2= (ImageView) view.findViewById(R.id.gongxiao2);
        gongxiao3= (ImageView) view.findViewById(R.id.gongxiao3);
        gongxiao4= (ImageView) view.findViewById(R.id.gongxiao4);
        gongxiao5= (ImageView) view.findViewById(R.id.gongxiao5);
        imageView_property_mask.setOnClickListener(this);
        fuzhi2.setOnClickListener(this);
        fuzhi3.setOnClickListener(this);
        fuzhi4.setOnClickListener(this);
        fuzhi5.setOnClickListener(this);
        fuzhi6.setOnClickListener(this);
        gongxiao1.setOnClickListener(this);
        gongxiao2.setOnClickListener(this);
        gongxiao3.setOnClickListener(this);
        gongxiao4.setOnClickListener(this);
        gongxiao5.setOnClickListener(this);
        getData();
    }

private void getData() {
    RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
    StringRequest stringRequest=new StringRequest(url, new Response.Listener<String>() {
        @Override
        public void onResponse(String s) {
            Gson gson=new Gson();
            ClassifyBean classifyBean = gson.fromJson(s, ClassifyBean.class);
            ClassifyBean.DataBean data = classifyBean.getData();
            List<ClassifyBean.DataBean.CategoryBean> category = data.getCategory();
            ClassifyBean.DataBean.CategoryBean categoryBean = category.get(2);
            List<ClassifyBean.DataBean.GoodsBriefBean> goodsBriefBeanList = data.getGoodsBrief();
            List<ClassifyBean.DataBean.CategoryBean.ChildrenBean> children = categoryBean.getChildren();
            Message message1= handler.obtainMessage(0, children);
            message1.sendToTarget();
            Message message2 = handler.obtainMessage(1, goodsBriefBeanList);
            message2.sendToTarget();

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {

        }
    });
    requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fuzhi1:
                Intent intent=new Intent(getActivity(),CommodityShowActivity.class);
                startActivity(intent);
                break;
            case R.id.fuzhi2:
                Intent intent2=new Intent(getActivity(),GoodsShowActivity.class);
                intent2.putExtra("id",39);
                startActivity(intent2);
                break;
            case R.id.fuzhi3:
                Intent intent3=new Intent(getActivity(),GoodsShowActivity.class);
                intent3.putExtra("id",40);
                startActivity(intent3);
                break;
            case R.id.fuzhi4:
                Intent intent4=new Intent(getActivity(),GoodsShowActivity.class);
                intent4.putExtra("id",24);
                startActivity(intent4);
                break;
            case R.id.fuzhi5:
                Intent intent5=new Intent(getActivity(),GoodsShowActivity.class);
                intent5.putExtra("id",35);
                startActivity(intent5);
                break;
            case R.id.fuzhi6:
                Intent intent6=new Intent(getActivity(),GoodsShowActivity.class);
                intent6.putExtra("id",33);
                startActivity(intent6);
                break;
            case R.id.gongxiao1:
                Intent gongxiao1=new Intent(getActivity(), TwoEffectActivity.class);
                gongxiao1.putExtra("id",0);
                startActivity(gongxiao1);
                break;
            case R.id.gongxiao2:
                Intent gongxiao2=new Intent(getActivity(), TwoEffectActivity.class);
                gongxiao2.putExtra("id",1);
                startActivity(gongxiao2);
                break;
            case R.id.gongxiao3:
                Intent gongxiao3=new Intent(getActivity(), TwoEffectActivity.class);
                gongxiao3.putExtra("id",2);
                startActivity(gongxiao3);
                break;
            case R.id.gongxiao4:
                Intent gongxiao4=new Intent(getActivity(), TwoEffectActivity.class);
                gongxiao4.putExtra("id",3);
                startActivity(gongxiao4);
                break;
            case R.id.gongxiao5:
                Intent gongxiao5=new Intent(getActivity(), TwoEffectActivity.class);
                gongxiao5.putExtra("id",4);
                startActivity(gongxiao5);
                break;
        }
    }
}
