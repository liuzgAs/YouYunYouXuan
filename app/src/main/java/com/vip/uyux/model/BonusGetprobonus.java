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
     * goods_list : [{"id":1,"goods_title":"一品轩皇家有机食用调和油","goods_rule":"买一送三","price":"78.00","total_money":"9632","des1":"近7日收益¥562","des2":"近7日销量100件","profit":[{"title":"12-28","v1":125,"v2":236},{"title":"12-29","v1":25,"v2":36},{"title":"12-30","v1":15,"v2":26},{"title":"12-31","v1":25,"v2":36},{"title":"01-01","v1":12,"v2":23},{"title":"01-02","v1":25,"v2":36},{"title":"01-03","v1":15,"v2":36}]},{"id":1,"goods_title":"一品轩皇家有机食用调和油","goods_rule":"买一送三","price":"78.00","total_money":"9632","des1":"近7日收益¥562","des2":"近7日销量100件","profit":[{"title":"12-28","v1":125,"v2":236},{"title":"12-29","v1":25,"v2":36},{"title":"12-30","v1":15,"v2":26},{"title":"12-31","v1":25,"v2":36},{"title":"01-01","v1":12,"v2":23},{"title":"01-02","v1":25,"v2":36},{"title":"01-03","v1":15,"v2":36}]},{"id":1,"goods_title":"一品轩皇家有机食用调和油","goods_rule":"买一送三","price":"78.00","total_money":"9632","des1":"近7日收益¥562","des2":"近7日销量100件","profit":[{"title":"12-28","v1":125,"v2":236},{"title":"12-29","v1":25,"v2":36},{"title":"12-30","v1":15,"v2":26},{"title":"12-31","v1":25,"v2":36},{"title":"01-01","v1":12,"v2":23},{"title":"01-02","v1":25,"v2":36},{"title":"01-03","v1":15,"v2":36}]}]
     */

    private String title;
    private double k_money;
    private double y_money;
    private int status;
    private String info;
    private List<GoodsListBean> goods_list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getK_money() {
        return k_money;
    }

    public void setK_money(double k_money) {
        this.k_money = k_money;
    }

    public double getY_money() {
        return y_money;
    }

    public void setY_money(double y_money) {
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

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
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
         * profit : [{"title":"12-28","v1":125,"v2":236},{"title":"12-29","v1":25,"v2":36},{"title":"12-30","v1":15,"v2":26},{"title":"12-31","v1":25,"v2":36},{"title":"01-01","v1":12,"v2":23},{"title":"01-02","v1":25,"v2":36},{"title":"01-03","v1":15,"v2":36}]
         */

        private int id;
        private String goods_title;
        private String goods_rule;
        private String price;
        private String total_money;
        private String des1;
        private String des2;
        private String img;
        private int max;
        private boolean isZhanKai;
        private List<ProfitBean> profit;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public boolean isZhanKai() {
            return isZhanKai;
        }

        public void setZhanKai(boolean zhanKai) {
            isZhanKai = zhanKai;
        }

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

        public List<ProfitBean> getProfit() {
            return profit;
        }

        public void setProfit(List<ProfitBean> profit) {
            this.profit = profit;
        }

        public static class ProfitBean {
            /**
             * title : 12-28
             * v1 : 125
             * v2 : 236
             */

            private String title;
            private int v1;
            private int v2;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getV1() {
                return v1;
            }

            public void setV1(int v1) {
                this.v1 = v1;
            }

            public int getV2() {
                return v2;
            }

            public void setV2(int v2) {
                this.v2 = v2;
            }
        }
    }
}
