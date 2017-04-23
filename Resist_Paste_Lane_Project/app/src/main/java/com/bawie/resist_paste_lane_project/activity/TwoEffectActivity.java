package com.bawie.resist_paste_lane_project.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.bawie.resist_paste_lane_project.R;
import com.bawie.resist_paste_lane_project.adapter.EffectViewPagerAdapter;
import com.bawie.resist_paste_lane_project.adapter.TwoEffectViewPagerAdapter;
import com.bawie.resist_paste_lane_project.bean.MaskEffectBean;

import java.util.ArrayList;

/**
 * @author 陈宏升
 * @类的作用：
 * @data 2017/4/19 19:43
 */
public class TwoEffectActivity extends FragmentActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.effectactivity);
        Intent intent=getIntent();
        id=intent.getIntExtra("id",0);
        tabLayout= (TabLayout) findViewById(R.id.tablayout_effect);
        viewPager= (ViewPager) findViewById(R.id.viewPager_effect);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setSelectedTabIndicatorColor(Color.RED);
        tabLayout.setTabTextColors(Color.BLACK,Color.RED);
        viewPager.setAdapter(new TwoEffectViewPagerAdapter(getSupportFragmentManager(),TwoEffectActivity.this));
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(id);
    }

}
