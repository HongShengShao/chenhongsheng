package com.example.asus.todayhead.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.asus.todayhead.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

       private Button but_guide_page;
       private ViewPager viewPager;
       private LinearLayout linearLayout;
       private List<Integer> list=new ArrayList<>();
       private SharedPreferences sharedPreferences;
       private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences=getSharedPreferences("shared",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        initView();
        boolean b=sharedPreferences.getBoolean("pb",false);
        if (b){
             intent();
        }
    }

    private void initView() {
        but_guide_page= (Button) findViewById(R.id.but_guide_page);
        viewPager= (ViewPager) findViewById(R.id.vp);
        linearLayout= (LinearLayout) findViewById(R.id.linea);
        addData();
    }

    private void addData() {
        list.add(R.mipmap.a);
        list.add(R.mipmap.b);
        list.add(R.mipmap.c);
        setAdapter();
        addView();
    }

private void setAdapter() {
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
            ImageView imageView=new ImageView(MainActivity.this);
            imageView.setImageResource(list.get(position));
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    });
    }

    public void addView(){
        for (int i=0;i<list.size();i++){
            View view=new View(this);
            view.setBackgroundResource(R.drawable.point_bg);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(20,20);
            params.rightMargin=10;
            linearLayout.addView(view,params);
        }
        View view = linearLayout.getChildAt(0);
        view.setEnabled(false);
        pagerListener();
    }

    private void pagerListener() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int count = linearLayout.getChildCount();
                for (int i=0;i<count;i++){
                    View view = linearLayout.getChildAt(i);
                    view.setEnabled(i==position?false:true);
                    if (position==list.size()-1){
                         but_guide_page.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        but_guide_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("pb",true);
                editor.commit();
                intent();
            }
        });
    }

private void intent() {
    Intent intent=new Intent(MainActivity.this,StartActivity.class);
    startActivity(intent);
    finish();
    }

}
