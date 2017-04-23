package com.example.asus.gesturedetectorstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    private GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gestureDetector=new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                if (Math.abs(velocityY)<100){
                        return true;
                }
                if((e2.getRawY()-e1.getRawY())>200){
                      finish();
                    return true;
                }
                if ((e2.getRawY()-e1.getRawY())<200){
                     finish();
                    return true;
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }

        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
