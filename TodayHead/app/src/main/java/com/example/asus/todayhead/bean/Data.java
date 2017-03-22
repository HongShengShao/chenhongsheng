package com.example.asus.todayhead.bean;

import java.util.ArrayList;

/**
 * Created by asus on 2017/3/14.
 */
public class Data {
    private ArrayList<Image_list> image_list;
    private ArrayList<Large_image_list> large_image_list;
    private String media_name;
    private Middle_image middle_image;
    private String title;
    private String url;

    public ArrayList<Image_list> getImage_list() {
        return image_list;
    }

    public void setImage_list(ArrayList<Image_list> image_list) {
        this.image_list = image_list;
    }

    public ArrayList<Large_image_list> getLarge_image_list() {
        return large_image_list;
    }

    public void setLarge_image_list(ArrayList<Large_image_list> large_image_list) {
        this.large_image_list = large_image_list;
    }

    public String getMedia_name() {
        return media_name;
    }

    public void setMedia_name(String media_name) {
        this.media_name = media_name;
    }

    public Middle_image getMiddle_image() {
        return middle_image;
    }

    public void setMiddle_image(Middle_image middle_image) {
        this.middle_image = middle_image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
