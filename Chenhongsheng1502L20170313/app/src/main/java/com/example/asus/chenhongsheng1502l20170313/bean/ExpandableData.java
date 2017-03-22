package com.example.asus.chenhongsheng1502l20170313.bean;

import java.io.Serializable;

/**
 * Created by asus on 2017/3/13.
 */
public class ExpandableData implements Serializable{
    public String city;
    public String cityid;
    public String parentid;

    @Override
    public String toString() {
        return "ExpandableData{" +
                "city='" + city + '\'' +
                ", cityid='" + cityid + '\'' +
                ", parentid='" + parentid + '\'' +
                '}';
    }
}
