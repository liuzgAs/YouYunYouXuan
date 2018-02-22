package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/2/22/022.
 *
 * @author ZhangJieBo
 */

public class UserRealbefore {
    /**
     * des : ["海关要求购买跨境商品需要提供实名信息哦~","如因信息填写错误,导至海关过关失败自行承担责任~"]
     * url : http://app.uyux.vip/api/Article/info/type/real
     * url_title : 了解 实名认证
     * status : 1
     * info : 操作成功！
     */

    private String url;
    private String url_title;
    private int status;
    private String info;
    private List<String> des;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl_title() {
        return url_title;
    }

    public void setUrl_title(String url_title) {
        this.url_title = url_title;
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

    public List<String> getDes() {
        return des;
    }

    public void setDes(List<String> des) {
        this.des = des;
    }
}
