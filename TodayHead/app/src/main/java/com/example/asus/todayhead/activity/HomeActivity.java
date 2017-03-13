package com.example.asus.todayhead.activity;

import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.asus.todayhead.R;
import com.example.asus.todayhead.adapter.Fragment_home;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;


/**
 * Created by asus on 2017/3/10.
 */
public class HomeActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    private RadioGroup radioGroup;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initView() {
        frameLayout = (FrameLayout) findViewById(R.id.frame_home);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        radioButton4 = (RadioButton) findViewById(R.id.radioButton4);
        SlidingMenu slidingMenu = new SlidingMenu(this);
        //设置侧滑的方向
        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置不让整个屏幕滑出
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        //设置屏幕滑出的宽度
        slidingMenu.setBehindOffset(100);
        //让侧滑依附在activity上
        slidingMenu.attachToActivity(HomeActivity.this, SlidingMenu.SLIDING_CONTENT);
        //设置侧滑的布局
        slidingMenu.setMenu(R.layout.activity_menu);

       getSupportFragmentManager().beginTransaction().replace(R.id.frame_home,new Fragment_home()).commit();
        radioListener();
    }

    private void radioListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton1:
                        fragmentManager(new Fragment_home());
                        break;
                    case R.id.radioButton2:
                        break;
                    case R.id.radioButton3:
                        break;
                    case R.id.radioButton4:
                        break;
                }
            }
        });
    }

    private void fragmentManager(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        beginTransaction.replace(R.id.frame_home, fragment);
        beginTransaction.commit();
//       getSupportFragmentManager().beginTransaction().replace(R.id.frame_home,new Fragment_main_interface()).commit();
//        getSupportFragmentManager().beginTransaction().replace(R.id.cehua,new Fragment_side_interface()).commit();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Home Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.asus.todayhead.activity/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Home Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.asus.todayhead.activity/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
