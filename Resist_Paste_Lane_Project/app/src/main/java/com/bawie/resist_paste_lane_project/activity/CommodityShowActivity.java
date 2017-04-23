package com.bawie.resist_paste_lane_project.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawie.resist_paste_lane_project.R;
import com.bawie.resist_paste_lane_project.adapter.CommodityMaskAdapter;
import com.bawie.resist_paste_lane_project.adapter.EffectViewPagerAdapter;
import com.bawie.resist_paste_lane_project.adapter.MyClassifyGridViewAdapter;
import com.bawie.resist_paste_lane_project.adapter.ViewPagerAdapter;
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
 * @data 2017/4/18 8:44
 */
public class CommodityShowActivity extends FragmentActivity {

    private TextView textView;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commodityshowactivity);
        textView= (TextView) findViewById(R.id.textView_mask);
        tabLayout= (TabLayout) findViewById(R.id.tablayout_mask);
        viewPager= (ViewPager) findViewById(R.id.viewPager_mask);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setSelectedTabIndicatorColor(Color.RED);
        tabLayout.setTabTextColors(Color.BLACK,Color.RED);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),CommodityShowActivity.this));
        tabLayout.setupWithViewPager(viewPager);
    }
}
