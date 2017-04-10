package com.bwie.test.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;



/**
 * @author 陈宏升
 * @类的作用：
 * @data 2017/4/10 9:00
 */
public class MyView extends View {

    int progress = 0;
    private String text="0%";
    private int max = 100;

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 对于画笔
        Paint paint = new Paint();
        // 设置抗锯齿
        paint.setAntiAlias(true);
        // 设置画笔颜色
        paint.setColor(Color.BLUE);
        // 三种样式--Stroke 只要描边 Fill 填充 FILL_AND_STROKE和既有描边又有填充
        paint.setStyle(Paint.Style.STROKE);
        //设置描边宽度
        paint.setStrokeWidth(2);
        //定义外圈员的颜色
        paint.setColor(Color.RED);
        //绘制圆形进度条--获取当前控件多大，正好让进度条在这个控件区间内
        canvas.drawCircle(getMeasuredWidth()/2, getMeasuredWidth()/2, getMeasuredWidth()/2, paint);
        //重新设置描边宽度，这个宽度最好能完全盖过圆形
        paint.setStrokeWidth(3);

        //定义限制圆弧的矩形，当前这样定义正好让圆弧和圆重合
        RectF oval = new RectF(0, 0, getMeasuredWidth(), getMeasuredWidth());
        //设置进度条（圆弧的颜色）
        paint.setColor(Color.GREEN);
        //绘制，设置进度条的度数从0开始，结束值是个变量，可以自己自由设置，来设置进度
        //true和false 代表是否使用中心点，如果true，代表连接中心点，会出现扇形的效果
        canvas.drawArc(oval, 0, 360 * progress / max, false, paint);
        //文字的绘制
        paint.setTextSize(40);
        //设置文字宽度
        paint.setStrokeWidth(1.0f);
        //测量文字大小-提前准备个矩形
        Rect bounds = new Rect();
        //测量文字的宽和高，测量的值可以根据矩形获取
        paint.getTextBounds(text, 0, text.length(), bounds);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        //绘制文字，计算文字的宽高进行设置
        canvas.drawText(text, getMeasuredWidth()/2 - bounds.width() / 2,
                getMeasuredWidth()/2 + bounds.height() / 2, paint);

    }
    /**
     * 初始设置当前进度的最大值-默认100
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }
    /**
     * 更新进度和文字
     * @param progress
     * @param text
     */
    public void setProgressAndText(int progress, String text) {
        this.progress = progress;
        this.text = text;
        //重新绘制
        postInvalidate();
    }

}
