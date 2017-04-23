package com.bawie.resist_paste_lane_project.bean;

import java.io.Serializable;

/**
 * @author 陈宏升
 * @类的作用：
 * @data 2017/4/19 19:24
 */
public class MaskEffectBean implements Serializable{
    private String name;
    private int id;
    private int position;

    public MaskEffectBean(String name, int id, int position) {
        this.name = name;
        this.id = id;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
