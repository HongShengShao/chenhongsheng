package com.example.asus.todayhead.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.todayhead.R;
import com.example.asus.todayhead.bean.VideoData;
import com.example.asus.todayhead.manager.MyVideoPagerFragment;

import java.util.ArrayList;

/**
 * Created by asus on 2017/3/17.
 */
public class Fragment2 extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private String[] str=new String[]{"热点","娱乐","搞笑","精品"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.video_viewPager);
        tabLayout= (TabLayout) view.findViewById(R.id.video_tablayout);
        viewPager.setAdapter(new MyVideoPagerFragment(getChildFragmentManager(),getActivity(),str));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

}