package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/2/3/003.
 *
 * @author ZhangJieBo
 */

public class PingJia {
    private int loginType;
    private String platform;
    private String uid;
    private String tokenTime;
    private int id;
    private int star;
    private String evaluate;
    private List<Integer> tag;
    private List<Integer> imgs;

    public PingJia(int loginType, String platform, String uid, String tokenTime, int id, int star, String evaluate, List<Integer> tag, List<Integer> imgs) {
        this.loginType = loginType;
        this.platform = platform;
        this.uid = uid;
        this.tokenTime = tokenTime;
        this.id = id;
        this.star = star;
        this.evaluate = evaluate;
        this.tag = tag;
        this.imgs = imgs;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public List<Integer> getTag() {
        return tag;
    }

    public void setTag(List<Integer> tag) {
        this.tag = tag;
    }

    public List<Integer> getImgs() {
        return imgs;
    }

    public void setImgs(List<Integer> imgs) {
        this.imgs = imgs;
    }
}
