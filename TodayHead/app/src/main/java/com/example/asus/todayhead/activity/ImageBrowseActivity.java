package com.example.asus.todayhead.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.asus.todayhead.R;
import com.example.asus.todayhead.adapter.ImageBrowseAdapter;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by asus on 2017/3/24.
 */
public class ImageBrowseActivity extends Activity {
    private ViewPager viewPager;
    private ArrayList<String> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_imagebrowse);
        viewPager= (ViewPager) findViewById(R.id.viewPager_imageBrowse);
        Intent intent=getIntent();
        list= intent.getStringArrayListExtra("url");
        getAdapter();

    }

    private void getAdapter() {

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                PhotoView photoView=new PhotoView(ImageBrowseActivity.this);
                Glide.with(ImageBrowseActivity.this).load(list.get(position)).into(photoView);
                photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                    @Override
                    public void onPhotoTap(View view, float v, float v1) {
                        finish();
                    }
                });
                container.addView(photoView);
                return photoView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {

                container.removeView((View) object);
            }
        });
    }
}
