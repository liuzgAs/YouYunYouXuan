package com.vip.uyux.model;

/**
 * Created by zhangjiebo on 2018/1/13/013.
 *
 * @author ZhangJieBo
 */

public class UserProfile {

    /**
     * headImg : http://www.qizhibq.com/Uploads/avstar.png
     * nickname : 15860026753
     * real_name :
     * sex : 0
     * birthday :
     * status : 1
     * info : 返回成功！
     */

    private String headimg;
    private String nickname;
    private String real_name;
    private int sex;
    private String birthday;
    private String alipay;
    private String alipayname;
    private int status;
    private String info;

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public String getAlipayname() {
        return alipayname;
    }

    public void setAlipayname(String alipayname) {
        this.alipayname = alipayname;
    }

    public String getHeadImg() {
        return headimg;
    }

    public void setHeadImg(String headimg) {
        this.headimg = headimg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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
