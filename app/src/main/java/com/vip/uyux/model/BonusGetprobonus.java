package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/26/026.
 *
 * @author ZhangJieBo
 */

public class BonusGetprobonus {
    /**
     * title : 产品分红
     * k_money : 2365
     * y_money : 8365
     * status : 1
     * info : 获取成功
     * goods_list : [{"id":1,"goods_title":"一品轩皇家有机食用调和油","goods_rule":"买一送三","price":"78.00","total_money":"9632","des1":"近7日收益¥562","des2":"近7日销量100件"},{"id":1,"goods_title":"一品轩皇家有机食用调和油","goods_rule":"买一送三","price":"78.00","total_money":"9632","des1":"近7日收益¥562","des2":"近7日销量100件"},{"id":1,"goods_title":"一品轩皇家有机食用调和油","goods_rule":"买一送三","price":"78.00","total_money":"9632","des1":"近7日收益¥562","des2":"近7日销量100件"}]
     * statistics : {"sale":125,"s_money":1236,"visit":25,"visit1":256}
     */

    private String title;
    private int k_money;
    private int y_money;
    private int status;
    private String info;
    private StatisticsBean statistics;
    private List<GoodsListBean> goods_list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getK_money() {
        return k_money;
    }

    public void setK_money(int k_money) {
        this.k_money = k_money;
    }

    public int getY_money() {
        return y_money;
    }

    public void setY_money(int y_money) {
        this.y_money = y_money;
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

    public StatisticsBean getStatistics() {
        return statistics;
    }

    public void setStatistics(StatisticsBean statistics) {
        this.statistics = statistics;
    }

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public static class StatisticsBean {
        /**
         * sale : 125
         * s_money : 1236
         * visit : 25
         * visit1 : 256
         */

        private int sale;
        private int s_money;
        private int visit;
        private int visit1;

        public int getSale() {
            return sale;
        }

        public void setSale(int sale) {
            this.sale = sale;
        }

        public int getS_money() {
            return s_money;
        }

        public void setS_money(int s_money) {
            this.s_money = s_money;
        }

        public int getVisit() {
            return visit;
        }

        public void setVisit(int visit) {
            this.visit = visit;
        }

        public int getVisit1() {
            return visit1;
        }

        public void setVisit1(int visit1) {
            this.visit1 = visit1;
        }
    }

    public static class GoodsListBean {
        /**
         * id : 1
         * goods_title : 一品轩皇家有机食用调和油
         * goods_rule : 买一送三
         * price : 78.00
         * total_money : 9632
         * des1 : 近7日收益¥562
         * des2 : 近7日销量100件
         */

        private int id;
        private String goods_title;
        private String goods_rule;
        private String price;
        private String total_money;
        private String des1;
        private String des2;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGoods_title() {
            return goods_title;
        }

        public void setGoods_title(String goods_title) {
            this.goods_title = goods_title;
        }

        public String getGoods_rule() {
            return goods_rule;
        }

        public void setGoods_rule(String goods_rule) {
            this.goods_rule = goods_rule;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTotal_money() {
            return total_money;
        }

        public void setTotal_money(String total_money) {
            this.total_money = total_money;
        }

        public String getDes1() {
            return des1;
        }

        public void setDes1(String des1) {
            this.des1 = des1;
        }

        public String getDes2() {
            return des2;
        }

        public void setDes2(String des2) {
            this.des2 = des2;
        }
    }
}
