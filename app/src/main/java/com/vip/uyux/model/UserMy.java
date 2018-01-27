package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/23/023.
 *
 * @author ZhangJieBo
 */

public class UserMy {
    /**
     * id : ID:1011653
     * nickname : 18559666682
     * headimg : http://api.uyux.vip/Uploads/avstar.png
     * lv : 0
     * money : 0
     * growthDes : 成长值：0
     * tipsNum : 0
     * couponNum : 0
     * score : 0
     * grade : 0
     * status : 1
     * info : 返回成功！
     */

    private String id;
    private String nickname;
    private String qc_url;
    private String headimg;
    private int lv;
    private String money;
    private String growthDes;
    private int tipsNum;
    private int couponNum;
    private int score;
    private int grade;
    private int status;
    private String info;
    private List<Integer> orderNum ;

    public String getQc_url() {
        return qc_url;
    }

    public void setQc_url(String qc_url) {
        this.qc_url = qc_url;
    }

    public List<Integer> getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(List<Integer> orderNum) {
        this.orderNum = orderNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getGrowthDes() {
        return growthDes;
    }

    public void setGrowthDes(String growthDes) {
        this.growthDes = growthDes;
    }

    public int getTipsNum() {
        return tipsNum;
    }

    public void setTipsNum(int tipsNum) {
        this.tipsNum = tipsNum;
    }

    public int getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(int couponNum) {
        this.couponNum = couponNum;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
