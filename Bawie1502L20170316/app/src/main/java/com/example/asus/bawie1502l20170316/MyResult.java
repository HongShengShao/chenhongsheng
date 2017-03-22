package com.example.asus.bawie1502l20170316;

import java.util.ArrayList;

/**
 * Created by asus on 2017/3/16.
 */
public class MyResult {
    private ArrayList<NewsData> data;

    public MyResult(ArrayList<NewsData> data) {
        this.data = data;
    }

    public ArrayList<NewsData> getData() {
        return data;
    }

    public void setData(ArrayList<NewsData> data) {
        this.data = data;
    }
}
