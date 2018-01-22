package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/22/022.
 *
 * @author ZhangJieBo
 */

public class OrderTiJiao {
    private int loginType;
    private String platform;
    private String uid;
    private String tokenTime;
    private List<Integer> cart;
    private String orderAmount;
    private String did;
    private String aid;

    public OrderTiJiao(int loginType, String platform, List<Integer> cart, String orderAmount, String did, String aid) {
        this.loginType = loginType;
        this.platform = platform;
        this.cart = cart;
        this.orderAmount = orderAmount;
        this.did = did;
        this.aid = aid;
    }

    public OrderTiJiao(int loginType, String platform, String uid, String tokenTime, List<Integer> cart, String orderAmount, String did, String aid) {
        this.loginType = loginType;
        this.platform = platform;
        this.uid = uid;
        this.tokenTime = tokenTime;
        this.cart = cart;
        this.orderAmount = orderAmount;
        this.did = did;
        this.aid = aid;
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

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }
}
