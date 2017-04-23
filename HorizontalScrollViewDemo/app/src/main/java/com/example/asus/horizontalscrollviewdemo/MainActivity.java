package com.example.asus.horizontalscrollviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mLinearLayout;
    private int[] image = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e,
            R.drawable.f, R.drawable.a, R.drawable.b, R.drawable.c};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inintent();
    }

    private void inintent() {
        mLinearLayout = (LinearLayout) findViewById(R.id.linear);
        //开始添加数据
        for (int x = 0; x < image.length; x++) {
            //寻找行布局，第一个参数为行布局ID，第二个参数为这个行布局需要放到那个容器上
            View view = LayoutInflater.from(this).inflate(R.layout.item_text, mLinearLayout, false);
            //通过View寻找ID实例化控件
            ImageView img = (ImageView) view.findViewById(R.id.imageView);
            //实例化TextView控件
            TextView tv = (TextView) view.findViewById(R.id.textView);
            //将int数组中的数据放到ImageView中
            img.setImageResource(image[x]);
            //给TextView添加文字
            tv.setText("第" + (x + 1) + "张");
            //把行布局放到linear里
            mLinearLayout.addView(view);
        }
    }
}
