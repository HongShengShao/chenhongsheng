package com.example.asus.todayhead.bean;

/**
 * Created by asus on 2017/3/27.
 */
public class ListAttention {
    private int id;
    private String name;
    private String url;

    public ListAttention(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
