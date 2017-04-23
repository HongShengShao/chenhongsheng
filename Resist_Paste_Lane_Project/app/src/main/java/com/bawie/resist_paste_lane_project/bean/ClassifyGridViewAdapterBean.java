package com.bawie.resist_paste_lane_project.bean;

/**
 * @author 陈宏升
 * @类的作用：
 * @data 2017/4/17 13:42
 */
public class ClassifyGridViewAdapterBean {

    private String str;
    private int color;

    public ClassifyGridViewAdapterBean(String str, int color) {
        this.str = str;
        this.color = color;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
