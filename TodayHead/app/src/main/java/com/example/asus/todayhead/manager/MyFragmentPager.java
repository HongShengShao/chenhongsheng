package com.example.asus.todayhead.manager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.asus.todayhead.fragment.Fragment1;

import java.util.ArrayList;

/**
 * Created by asus on 2017/3/10.
 */
public class MyFragmentPager extends FragmentPagerAdapter {

    private Context context;
    private ArrayList<String> list;

    public MyFragmentPager(FragmentManager fm, Context context, ArrayList<String> list) {
        super(fm);
        this.context=context;
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment1 fragment1=new Fragment1();
        Bundle bundle=new Bundle();
        bundle.putInt("bun",position);
        fragment1.setArguments(bundle);
        return fragment1;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}
