package com.bawie.resist_paste_lane_project.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bawie.resist_paste_lane_project.activity.CommodityShowActivity;
import com.bawie.resist_paste_lane_project.activity.EffectActivity;
import com.bawie.resist_paste_lane_project.activity.TwoEffectActivity;
import com.bawie.resist_paste_lane_project.bean.MaskEffectBean;
import com.bawie.resist_paste_lane_project.fragment.EffectFragmentPager;
import com.bawie.resist_paste_lane_project.fragment.FragmentPager;

import java.util.ArrayList;

/**
 * @author 陈宏升
 * @类的作用：
 * @data 2017/4/19 8:52
 */
public class EffectViewPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private ArrayList<MaskEffectBean> list;
//    private String[] str=new String[]{"补水保湿","舒缓修护","控油祛痘","美白提亮","紧致抗皱"};

    public EffectViewPagerAdapter(FragmentManager fm, Context context, ArrayList<MaskEffectBean> list) {
        super(fm);
        this.context=context;
        this.list=list;
    }



    @Override
    public Fragment getItem(int position) {
        EffectFragmentPager fragmentPager=new EffectFragmentPager();
        int id = list.get(position).getId();
        Bundle bundle=new Bundle();
        bundle.putInt("position",id);
        fragmentPager.setArguments(bundle);
        return fragmentPager;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getName();
    }
}
