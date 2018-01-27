package com.vip.uyux.model;

/**
 * Created by zhangjiebo on 2018/1/28/028.
 *
 * @author ZhangJieBo
 */

public class WebPay {
    /**
     * opid : 51
     * oid : 59
     * order_amount : 1799
     * status : 1
     * info : 返回成功！
     */

    private String opid;
    private String oid;
    private String order_amount;
    private int status;
    private String info;

    public String getOpid() {
        return opid;
    }

    public void setOpid(String opid) {
        this.opid = opid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(String order_amount) {
        this.order_amount = order_amount;
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
