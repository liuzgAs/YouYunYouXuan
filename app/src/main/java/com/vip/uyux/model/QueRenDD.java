package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/22/022.
 *
 * @author ZhangJieBo
 */

public class QueRenDD {
    private int loginType;
    private String platform;
    private String uid;
    private String tokenTime;
    private List<Integer> cart;
    private String did;

    public QueRenDD( int loginType, String platform, String uid, String tokenTime, List<Integer> cart, String did) {
        this.loginType = loginType;
        this.platform = platform;
        this.uid = uid;
        this.tokenTime = tokenTime;
        this.cart = cart;
        this.did = did;
    }

    public QueRenDD( int loginType, String platform, List<Integer> cart, String did) {
        this.loginType = loginType;
        this.platform = platform;
        this.cart = cart;
        this.did = did;
    }

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTokenTime() {
        return tokenTime;
    }

    public void setTokenTime(String tokenTime) {
        this.tokenTime = tokenTime;
    }

    public List<Integer> getCart() {
        return cart;
    }

    public void setCart(List<Integer> cart) {
        this.cart = cart;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }
}
