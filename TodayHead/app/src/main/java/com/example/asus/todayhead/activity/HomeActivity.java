package com.example.asus.todayhead.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.todayhead.R;
import com.example.asus.todayhead.adapter.Fragment_home;

import com.example.asus.todayhead.fragment.Fragment2;

import com.example.asus.todayhead.fragment.Fragment3;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;


/**
 * Created by asus on 2017/3/10.
 */
public class HomeActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private RadioButton radioButton1, radioButton2, radioButton3;
    private RadioGroup radioGroup;
    private ImageView imageView,imageView2,image_phone,image_seek;
    private TextView textView_system_set;
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
    private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ};
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    private Button button_night,button_collect;
    private int theme = R.style.AppTheme;
    private ImageView imageView_qq;
    private TextView textView_qq;
    private LinearLayout linearLayout_login;
    private LinearLayout linearLayout_login_qq;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            theme = savedInstanceState.getInt("theme");
            setTheme(theme);
        }
        setContentView(R.layout.activity_home);
        SMSSDK.initSDK(this, "1c0e2609bb4aa", "a941cdb1b2e606adc23902d0f08b60cf");
        //初始化JPush
        JPushInterface.init(this);
        //设置debug模式
        JPushInterface.setDebugMode(true);
        initView();
    }

    private void initView() {
        frameLayout = (FrameLayout) findViewById(R.id.frame_home);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        imageView= (ImageView) findViewById(R.id.image_toggle);
        image_seek= (ImageView) findViewById(R.id.image_seek);
        radioGroup.check(R.id.radioButton1);


        slidigMenuMethod();
    }

    private void slidigMenuMethod() {
        final SlidingMenu slidingMenu = new SlidingMenu(this);
        //设置侧滑的方向
        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置不让整个屏幕滑出
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
       //设置阴影图片
//        slidingMenu.setShadowDrawable(R.drawable.common_ic_googleplayservices);
        //SlidingMenu滑动时的渐变程度
        slidingMenu.setFadeDegree(0.4f);
        ////SlidingMenu划出时主页面显示的剩余宽度
//        slidingMenu.setBehindOffset(350);
        //设置SlidingMenu菜单的宽度
        slidingMenu.setBehindWidth(500);
        //设置滑动时拖拽效果
        slidingMenu.setBehindScrollScale(1);
        //让侧滑依附在activity上
        slidingMenu.attachToActivity(HomeActivity.this, SlidingMenu.SLIDING_CONTENT);
        //设置侧滑的布局
        slidingMenu.setMenu(R.layout.activity_menu);

        imageView2 = (ImageView) findViewById(R.id.image_qq);
        button_night = (Button) findViewById(R.id.but_night);
        button_collect= (Button) findViewById(R.id.but_collect);
        image_phone= (ImageView) findViewById(R.id.image_phone);
        textView_system_set= (TextView) findViewById(R.id.text_system_set);
        linearLayout_login = (LinearLayout) findViewById(R.id.lineaLayout_login);
        linearLayout_login_qq = (LinearLayout) findViewById(R.id.lineaLayout_login_qq);
        imageView_qq = (ImageView) findViewById(R.id.image_qq_login_head);
        textView_qq = (TextView) findViewById(R.id.text_qq_login_name);




        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidingMenu.toggle();
            }
        });
        image_seek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent=new Intent(HomeActivity.this,SeekActivity.class);
                startActivity(intent);
            }
        });
        image_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RegisterPage registerPage = new RegisterPage();
                registerPage.setRegisterCallback(new EventHandler() {
                    @Override
                    public void afterEvent(int event, int result, Object data) {
                        super.afterEvent(event, result, data);
                        //解析注册结果
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            HashMap<String, Object> map = (HashMap<String, Object>) data;
                            String country = (String) map.get("country");
                            String phone = (String) map.get("phone");
                        }
                    }
                });
                registerPage.show(HomeActivity.this);
            }
        });
        initPlatforms();
        final boolean isauth = UMShareAPI.get(HomeActivity.this).isAuthorize(HomeActivity.this, platforms.get(0).mPlatform);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isauth) {
                    Toast.makeText(HomeActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
                    UMShareAPI.get(HomeActivity.this).deleteOauth(HomeActivity.this, platforms.get(0).mPlatform, authListener);

                } else {
                    Toast.makeText(HomeActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    UMShareAPI.get(HomeActivity.this).doOauthVerify(HomeActivity.this, platforms.get(0).mPlatform, authListener);
                }
            }
        });
        button_night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "切换", Toast.LENGTH_SHORT).show();
                theme = (theme == R.style.AppTheme) ? R.style.NightAppTheme : R.style.AppTheme;
                //重新创建
                recreate();
            }
        });
        button_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,CollectActivity.class);
                startActivity(intent);
            }
        });
        textView_system_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  Intent intent=new Intent(HomeActivity.this,SystemSetActivity.class);
                  startActivity(intent);
            }
        });
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
                        fragmentManager(new Fragment2());
                        break;
                    case R.id.radioButton3:
                        fragmentManager(new Fragment3());
                        break;
                }
            }
        });
    }

    private void fragmentManager(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        beginTransaction.replace(R.id.frame_home, fragment);
        beginTransaction.commit();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private void initPlatforms() {
        platforms.clear();
        for (SHARE_MEDIA e : list) {
            if (!e.toString().equals(SHARE_MEDIA.GENERIC.toString())) {
                platforms.add(e.toSnsPlatform());
            }
        }
    }

    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            // SocializeUtils.safeShowDialog(dialog);
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            //  SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(HomeActivity.this, "成功了", Toast.LENGTH_LONG).show();
            switch (action){
                case ACTION_AUTHORIZE:
                    UMShareAPI mShareAPI = UMShareAPI.get(HomeActivity.this);
                   mShareAPI.getPlatformInfo(HomeActivity.this, SHARE_MEDIA.QQ,authListener);
                    break;
                case ACTION_GET_PROFILE:
                   String iconurl = data.get("iconurl");
                   String name = data.get("name");
                    ImageOptions imageOptions=new ImageOptions.Builder().setCircular(true).build();
                    x.image().bind(imageView_qq,iconurl,imageOptions);
//                    ImageLoader.getInstance().displayImage(iconurl,imageView_qq);
                    textView_qq.setText(name);
                    linearLayout_login.setVisibility(View.GONE);
                    linearLayout_login_qq.setVisibility(View.VISIBLE);
                    Toast.makeText(HomeActivity.this,name+"",Toast.LENGTH_LONG).show();
                    break;
            }

            //  notifyDataSetChanged();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            //  SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(HomeActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            //   SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(HomeActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("theme", theme);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null){
            savedInstanceState.getInt("theme");
        }
    }
}
