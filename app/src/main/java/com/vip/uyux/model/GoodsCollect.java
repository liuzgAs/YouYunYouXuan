package com.vip.uyux.model;

/**
 * Created by zhangjiebo on 2018/2/22/022.
 *
 * @author ZhangJieBo
 */

public class GoodsCollect {

    /**
     * collectNum : 1
     * status : 1
     * info : 操作成功！
     */

    private int collectNum;
    private int status;
    private String info;

    public int getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(int collectNum) {
        this.collectNum = collectNum;
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
