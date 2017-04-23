package com.example.asus.todayhead.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by asus on 2017/3/27.
 */

@Table(name="attention")
public class Attention {
    @Column(name="id",isId = true)
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="url")
    private String url;
    @Column(name="state")
    private boolean state;

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

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
