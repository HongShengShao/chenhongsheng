package com.bawie.resist_paste_lane_project.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bawie.resist_paste_lane_project.R;
import com.bawie.resist_paste_lane_project.fragment.Fragment_Classify;
import com.bawie.resist_paste_lane_project.fragment.Fragment_Home_Page;
import com.bawie.resist_paste_lane_project.fragment.Fragment_My;
import com.bawie.resist_paste_lane_project.fragment.Fragment_Shopping_Cart;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout frameLayout;
    private RadioGroup radioGroup;
    private RadioButton radioButton_home_page,radioButton_classify,radioButton_my,radioButton_shopping_cart;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        frameLayout= (FrameLayout) findViewById(R.id.frameLayout);
        radioGroup= (RadioGroup) findViewById(R.id.radioGroup);
        radioButton_home_page= (RadioButton) findViewById(R.id.radioButton_home_page);
        radioButton_classify= (RadioButton) findViewById(R.id.radioButton_classify);
        radioButton_shopping_cart= (RadioButton) findViewById(R.id.radioButton_shopping_cart);
        radioButton_my= (RadioButton) findViewById(R.id.radioButton_my);
        radioButton_home_page.setOnClickListener(this);
        radioButton_classify.setOnClickListener(this);
        radioButton_shopping_cart.setOnClickListener(this);
        radioButton_my.setOnClickListener(this);
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayout,new Fragment_Home_Page());
        fragmentTransaction.commit();
        radioButton_home_page.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.radioButton_home_page:
                replaceFragment(new Fragment_Home_Page());
                break;
            case R.id.radioButton_classify:
                replaceFragment(new Fragment_Classify());
                break;
            case R.id.radioButton_shopping_cart:
                replaceFragment(new Fragment_Shopping_Cart());
                break;
            case R.id.radioButton_my:
                replaceFragment(new Fragment_My());
                break;
        }
    }

    private void replaceFragment(Fragment fragment) {
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
}
