package com.bwie.test.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bwie.test.R;
import com.bwie.test.view.MyView;


public class MainActivity extends AppCompatActivity {

    private MyView circleView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        circleView = (MyView) findViewById(R.id.circleView);
    }

    int progress = 0;

    public void start(View v) {
        // 1000公里
        circleView.setMax(100);
        progress=0;
        new Thread() {
            public void run() {
                while (true) {
                    progress = progress + 1;
                    String text = progress + "%";
                    circleView.setProgressAndText(progress, text);
                    try {
                        sleep(30);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if (progress == 100) {
                        break;
                    }
                }
            };
        }.start();
    }
}
