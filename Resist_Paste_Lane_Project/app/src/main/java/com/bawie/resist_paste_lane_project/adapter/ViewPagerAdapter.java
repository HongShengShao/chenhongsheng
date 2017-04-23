package com.bawie.resist_paste_lane_project.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bawie.resist_paste_lane_project.activity.CommodityShowActivity;
import com.bawie.resist_paste_lane_project.fragment.FragmentPager;

/**
 * @author 陈宏升
 * @类的作用：
 * @data 2017/4/18 12:28
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private String[] str=new String[]{"贴式面膜","睡眠面膜","泥浆面膜"};

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        FragmentPager fragmentPager=new FragmentPager();
        Bundle bundle=new Bundle();
        bundle.putInt("position",position);
        fragmentPager.setArguments(bundle);
        return fragmentPager;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return str[position];
    }
}
