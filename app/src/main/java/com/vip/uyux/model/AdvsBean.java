package com.vip.uyux.model;

import java.io.Serializable;

public class AdvsBean implements Serializable{
    /**
     * img : http://app.uyux.vip/attachment/images/20180129/43d67922853ecb099dc861ed5709cea8.jpg
     * code :
     * item_id : 0
     * url :
     */

    private String img;
    private String code;
    private int item_id;
    private String url;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}