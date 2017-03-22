package com.example.asus.chenhongsheng1502l20170313.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by asus on 2017/3/13.
 */
public class ListData implements Serializable{
    public ArrayList<ExpandableData> result;


    @Override
    public String toString() {
        return "ListData{" +
                "list=" + result +
                '}';
    }
}
