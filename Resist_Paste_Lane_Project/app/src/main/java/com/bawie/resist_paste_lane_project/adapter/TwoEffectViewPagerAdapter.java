package com.bawie.resist_paste_lane_project.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bawie.resist_paste_lane_project.activity.TwoEffectActivity;
import com.bawie.resist_paste_lane_project.fragment.TwoEffectFragmentPager;

/**
 * @author 陈宏升
 * @类的作用：
 * @data 2017/4/19 19:44
 */
public class TwoEffectViewPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private String[] str=new String[]{"补水保湿","舒缓修复","控油祛痘","美白提亮","紧致抗皱"};

    public TwoEffectViewPagerAdapter(FragmentManager fm, TwoEffectActivity twoEffectActivity) {
        super(fm);
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        TwoEffectFragmentPager twoFragment=new TwoEffectFragmentPager();
        Bundle bundle=new Bundle();
        bundle.putInt("position",position);
        twoFragment.setArguments(bundle);
        return twoFragment;
    }

    @Override
    public int getCount() {
        return str.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return str[position];
    }
}
