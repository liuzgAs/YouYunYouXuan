package com.vip.uyux.model;

/**
 * Created by zhangjiebo on 2017/9/27 0027.
 */
public class CartNeworder {
    private String info;
    private int status;
    private int goRealName;
    private String oid;

    public int getGoRealName() {
        return goRealName;
    }

    public void setGoRealName(int goRealName) {
        this.goRealName = goRealName;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
