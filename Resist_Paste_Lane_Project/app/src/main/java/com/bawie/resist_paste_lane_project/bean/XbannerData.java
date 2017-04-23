package com.bawie.resist_paste_lane_project.bean;

/**
 * @author 陈宏升
 * @类的作用：
 * @data 2017/4/12 19:59
 */
public class XbannerData {
    private String imageUrl;
    private String url;

    public XbannerData(String imageUrl, String url) {
        this.imageUrl = imageUrl;
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
