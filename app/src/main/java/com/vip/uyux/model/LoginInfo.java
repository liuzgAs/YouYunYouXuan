package com.vip.uyux.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/2/28.
 */
public class LoginInfo implements Serializable{
    /**
     * openid : ohREEv15WdYrc59xcrSEuxKobXSI
     * nickname : 
     * sex : 1
     * language : zh_CN
     * city : Shiyan
     * province : Hubei
     * country : CN
     * headimgurl : http://wx.qlogo.cn/mmopen/I2tIp8onuMV1oeuk4YYQuRicMgL7TdqX0qiccUu4E1mMd15Sj2ykdOZWqS6cicpWJez3N9xu2SWFwpTfarvA7iasaTG9EMwdQQ7r/0
     * privilege : []
     * unionid : ojzlywHtiXG23JkjqXdHqgoW21w8
     */
    private int loginType;

    public LoginInfo(int loginType, String openid, String nickname, int sex, String language, String city, String province, String country, String headimgurl) {
        this.loginType = loginType;
        this.openid = openid;
        this.nickname = nickname;
        this.sex = sex;
        this.language = language;
        this.city = city;
        this.province = province;
        this.country = country;
        this.headimgurl = headimgurl;
    }

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    private String openid;
    private String nickname;
    private int sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private String unionid;
    private List<?> privilege;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public List<?> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<?> privilege) {
        this.privilege = privilege;
    }
}
