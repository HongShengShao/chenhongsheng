package com.example.asus.todayhead.adapter;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.example.asus.todayhead.R;
import com.example.asus.todayhead.manager.MyFragmentPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;

/**
 * Created by asus on 2017/3/10.
 */
public class Fragment_home extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<String> list=new ArrayList<>();
    @Nullable

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_home,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tabLayout= (TabLayout) view.findViewById(R.id.tabLayout_home);
        viewPager= (ViewPager) view.findViewById(R.id.viewPager_home);
        addListData();
    }

    private void addListData() {
        list.add("推荐");
        list.add("热点");
        list.add("本地");
        list.add("视频");
        list.add("社会");
        list.add("娱乐");
        list.add("科技");
        list.add("汽车");
        list.add("体育");
        list.add("财经");
        list.add("军事");
        list.add("国际");
        list.add("段子");
        list.add("趣图");
        list.add("健康");
        list.add("美女");
        setAdapter();
    }

    private void setAdapter() {
         viewPager.setAdapter(new MyFragmentPager(getChildFragmentManager(),getActivity(),list));
         tabLayout.setupWithViewPager(viewPager);
         tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
}
