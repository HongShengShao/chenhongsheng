package com.example.asus.todayhead.manager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.example.asus.todayhead.fragment.Fragment_Video;

/**
 * Created by asus on 2017/3/21.
 */
public class MyVideoPagerFragment extends FragmentPagerAdapter {


    private Context context;
    private String[] str;

    public MyVideoPagerFragment(FragmentManager fm, Context context, String[] str) {
        super(fm);
        this.context = context;
        this.str = str;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment_Video fragment_video=new Fragment_Video();
        Bundle bundle=new Bundle();
        bundle.putInt("bun",position);
        fragment_video.setArguments(bundle);
        return fragment_video;
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
