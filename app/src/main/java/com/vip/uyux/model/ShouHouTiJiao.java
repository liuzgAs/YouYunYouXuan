package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/2/7/007.
 *
 * @author ZhangJieBo
 */

public class ShouHouTiJiao {
    private int loginType;
    private String platform;
    private String uid;
    private String tokenTime;
    private int id;
    private int tag;
    private List<Integer> imgs;
    private String content;

    public ShouHouTiJiao(int loginType, String platform, String uid, String tokenTime, int id, int tag, List<Integer> imgs, String content) {
        this.loginType = loginType;
        this.platform = platform;
        this.uid = uid;
        this.tokenTime = tokenTime;
        this.id = id;
        this.tag = tag;
        this.imgs = imgs;
        this.content = content;
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

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public List<Integer> getImgs() {
        return imgs;
    }

    public void setImgs(List<Integer> imgs) {
        this.imgs = imgs;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
