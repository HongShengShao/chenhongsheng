package com.example.asus.smslogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button= (Button) findViewById(R.id.but);
        //初始化SMSSDK
        SMSSDK.initSDK(this, "1c0e2609bb4aa", "a941cdb1b2e606adc23902d0f08b60cf");
        button.setOnClickListener(new View.OnClickListener() {
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
                registerPage.show(MainActivity.this);
            }
        });
    }
}
