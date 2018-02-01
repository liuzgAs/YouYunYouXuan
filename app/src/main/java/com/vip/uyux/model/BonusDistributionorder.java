package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/27/027.
 *
 * @author ZhangJieBo
 */

public class BonusDistributionorder {

    /**
     * amount : 0.12
     * data : [{"oid":237,"orderSn":"编号UY201801280351091[直推]","orderSnDes":"已付款","nickname":"优云优选示范","nicknameDes":1011118,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTISj8QhdS54r5I0SMic92qQib1Q2KlicZzpd4YVTibLMV0kgT46mxkHGQSJWvvpzjfQFumvXwuiaJWfs4A/132","gn":"","des":"预计佣金：￥0.12"}]
     * page : {"page":"1","pageTotal":1,"pageSize":10,"dataTotal":1}
     * type : [{"n":"全部","v":"0","act":1},{"n":"已付款","v":"20","act":0},{"n":"已完成","v":"40","act":0},{"n":"已返佣","v":"60","act":0}]
     * status : 1
     * info : 操作成功！
     */

    private String amount;
    private PageBean page;
    private int status;
    private String info;
    private List<DataBean> data;
    private List<TypeBean> type;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<TypeBean> getType() {
        return type;
    }

    public void setType(List<TypeBean> type) {
        this.type = type;
    }

    public static class PageBean {
        /**
         * page : 1
         * pageTotal : 1
         * pageSize : 10
         * dataTotal : 1
         */

        private String page;
        private int pageTotal;
        private int pageSize;
        private int dataTotal;

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public int getPageTotal() {
            return pageTotal;
        }

        public void setPageTotal(int pageTotal) {
            this.pageTotal = pageTotal;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getDataTotal() {
            return dataTotal;
        }

        public void setDataTotal(int dataTotal) {
            this.dataTotal = dataTotal;
        }
    }

    public static class DataBean {
        /**
         * oid : 237
         * orderSn : 编号UY201801280351091[直推]
         * orderSnDes : 已付款
         * nickname : 优云优选示范
         * nicknameDes : 1011118
         * headimg : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTISj8QhdS54r5I0SMic92qQib1Q2KlicZzpd4YVTibLMV0kgT46mxkHGQSJWvvpzjfQFumvXwuiaJWfs4A/132
         * gn :
         * des : 预计佣金：￥0.12
         */

        private int oid;
        private String orderSn;
        private String orderSnDes;
        private String nickname;
        private String nicknameDes;
        private String headimg;
        private String gn;
        private String des;

        public int getOid() {
            return oid;
        }

        public void setOid(int oid) {
            this.oid = oid;
        }

        public String getOrderSn() {
            return orderSn;
        }

        public void setOrderSn(String orderSn) {
            this.orderSn = orderSn;
        }

        public String getOrderSnDes() {
            return orderSnDes;
        }

        public void setOrderSnDes(String orderSnDes) {
            this.orderSnDes = orderSnDes;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getNicknameDes() {
            return nicknameDes;
        }

        public void setNicknameDes(String nicknameDes) {
            this.nicknameDes = nicknameDes;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getGn() {
            return gn;
        }

        public void setGn(String gn) {
            this.gn = gn;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }
    }

    public static class TypeBean {
        /**
         * n : 全部
         * v : 0
         * act : 1
         */

        private String n;
        private String v;
        private int act;

        public String getN() {
            return n;
        }

        public void setN(String n) {
            this.n = n;
        }

        public String getV() {
            return v;
        }

        public void setV(String v) {
            this.v = v;
        }

        public int getAct() {
            return act;
        }

        public void setAct(int act) {
            this.act = act;
        }
    }
}
