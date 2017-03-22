package com.example.asus.bawie1502l20170315;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button but_alpha,but_translate,but_scale,but_set;
    private Animation animation_alpha,animation_translate,animation_scale,animation_set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animation_alpha=new AnimationUtils().loadAnimation(this,R.anim.anim_alpha);
        animation_translate=new AnimationUtils().loadAnimation(this,R.anim.anim_translate);
        animation_scale=new AnimationUtils().loadAnimation(this,R.anim.scale);
        animation_set=new AnimationUtils().loadAnimation(this,R.anim.anim_set);
        animation_set=new AnimationUtils().loadAnimation(this,R.anim.anim_set);
        initView();
    }

    private void initView() {
       but_alpha= (Button) findViewById(R.id.but_alpha);
        but_translate= (Button) findViewById(R.id.but_translate);
        but_scale= (Button) findViewById(R.id.but_scale);
        but_set= (Button) findViewById(R.id.but_set);
        imageView= (ImageView) findViewById(R.id.imageView);
        buttonListener();
    }

    private void buttonListener() {
        but_alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              imageView.startAnimation(animation_alpha);
            }
        });
        but_translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.startAnimation(animation_translate);
            }
        });
        but_scale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.startAnimation(animation_scale);
            }
        });
        but_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             imageView.startAnimation(animation_set);
            }
        });
    }
}
