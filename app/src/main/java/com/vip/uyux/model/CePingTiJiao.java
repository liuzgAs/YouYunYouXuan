package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/2/12/012.
 *
 * @author ZhangJieBo
 */

public class CePingTiJiao {
    private int loginType;
    private String platform;
    private String uid;
    private String tokenTime;
    private String id;
    private int og_id;
    private String title;
    private String img;
    private List<ImgBean> imgs;

    public CePingTiJiao(int loginType, String platform, String uid, String tokenTime, String id, int og_id, String title, String img, List<ImgBean> imgs) {
        this.loginType = loginType;
        this.platform = platform;
        this.uid = uid;
        this.tokenTime = tokenTime;
        this.id = id;
        this.og_id = og_id;
        this.title = title;
        this.img = img;
        this.imgs = imgs;
    }

    public static class ImgBean {
        private int img;
        private String content;

        public ImgBean(int img, String content) {
            this.img = img;
            this.content = content;
        }

        public int getImg() {
            return img;
        }

        public void setImg(int img) {
            this.img = img;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOg_id() {
        return og_id;
    }

    public void setOg_id(int og_id) {
        this.og_id = og_id;
    }

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

    public List<ImgBean> getImgs() {
        return imgs;
    }

    public void setImgs(List<ImgBean> imgs) {
        this.imgs = imgs;
    }
}
