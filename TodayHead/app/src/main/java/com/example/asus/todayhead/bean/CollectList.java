package com.example.asus.todayhead.bean;

/**
 * Created by asus on 2017/3/23.
 */
public class CollectList {

    private String title;
    private String media_name;
    private String url;

    public CollectList(String title, String media_name, String url) {
        this.title = title;
        this.media_name = media_name;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMedia_name() {
        return media_name;
    }

    public void setMedia_name(String media_name) {
        this.media_name = media_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
