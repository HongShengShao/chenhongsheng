package com.example.asus.bawie1502l20170316;

/**
 * Created by asus on 2017/3/16.
 */
public class NewsData {
    private String author_name;
    private String title;

    public NewsData(String author_name, String title) {
        this.author_name = author_name;
        this.title = title;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
