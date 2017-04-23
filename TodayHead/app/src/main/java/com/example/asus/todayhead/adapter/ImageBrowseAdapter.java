package com.example.asus.todayhead.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.asus.todayhead.fragment.Fragment_Browse;

/**
 * Created by asus on 2017/3/24.
 */
public class ImageBrowseAdapter extends FragmentPagerAdapter {

    private String imageUrl;
    public ImageBrowseAdapter(FragmentManager supportFragmentManager, String imageUrl) {
        super(supportFragmentManager);
        this.imageUrl=imageUrl;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment_Browse fragment_browse=new Fragment_Browse();
        Bundle bundle=new Bundle();
        bundle.putInt("bun",position);
        bundle.putString("imageUrl",imageUrl);
        fragment_browse.setArguments(bundle);
        return fragment_browse;
    }

    @Override
    public int getCount() {
        return 1;
    }

}
