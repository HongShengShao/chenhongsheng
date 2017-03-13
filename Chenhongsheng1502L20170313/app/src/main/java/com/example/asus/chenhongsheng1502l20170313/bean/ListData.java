package com.example.asus.chenhongsheng1502l20170313.bean;

import java.util.ArrayList;

/**
 * Created by asus on 2017/3/13.
 */
public class ListData {
    private String city;
    private String parentid;
    private ArrayList<ExpandableData> list;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public ArrayList<ExpandableData> getList() {
        return list;
    }

    public void setList(ArrayList<ExpandableData> list) {
        this.list = list;
    }

    public ListData() {
    }
}
