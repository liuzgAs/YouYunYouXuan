package com.vip.uyux.model;

/**
 * Created by Administrator on 2017/2/28.
 */
public class WxLogin {
    /**
     * access_token : mYTcWaSfGqKiZVl9Km8CgRM9ho9r6CE7cJPp59vsvWamng1lbU_qad6fE3Y-1k_wcyWwEAVIywfFGuoVki96IU7wZqgnDvCucEaepg7JnKQ
     * expires_in : 7200
     * refresh_token : IcyY5CsMkshfIFZKEM4HbtMXGnGYmKoIPE2MTTJjAoe303P9zRjYCcUIgfns2bhqof5Rb9MKtE_ejH3Es2nZw8hXhvDaWYdbPanBnG1nppU
     * openid : ohREEv15WdYrc59xcrSEuxKobXSI
     * scope : snsapi_userinfo
     * unionid : ojzlywHtiXG23JkjqXdHqgoW21w8
     */

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
