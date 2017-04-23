package com.example.asus.propertyanimationdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView= (ImageView) findViewById(R.id.imageView);
        button= (Button) findViewById(R.id.but);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float translationX = imageView.getTranslationX();
               ObjectAnimator translation=ObjectAnimator.ofFloat(imageView,"translationX",-500f,translationX);
                ObjectAnimator rotateX = ObjectAnimator.ofFloat(imageView, "rotation", 1.0f,360.0f);
                ObjectAnimator alpha = ObjectAnimator.ofFloat(imageView, "alpha", 1.0f, 0.0f,1.0f);
                AnimatorSet animationSet=new AnimatorSet();
                animationSet.play(rotateX).with(alpha).after(translation);
                animationSet.setDuration(5000);
                animationSet.start();
                animationSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationPause(Animator animation) {
                        super.onAnimationPause(animation);
                    }
                });
                animationSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
//                new ObjectAnimator().ofFloat(imageView, "translationX",translationX,-500f,translationX).setDuration(3000).start();
//                new ObjectAnimator().ofFloat(imageView,"scaleX",1f,3f,1f,2f,0f).setDuration(5000).start();
//                objectAnimator.start();
//                objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator animation) {
////                        float f= (float) animation.getAnimatedValue();
////                        imageView.setScaleX(f);
////                        imageView.setScaleY(f);
////                        imageView.setAlpha(f);
//                        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 1f, 0f, 1f);
//                        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 0f, 1f);
//                        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 0f, 1f);
//                        objectAnimator.ofPropertyValuesHolder(imageView,alpha,scaleX,scaleY).setDuration(3000).start();
//                    }
//                });

            }
        });

    }
}
