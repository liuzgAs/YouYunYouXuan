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
     * k_money : 0
     * y_money : 0
     * des : 提现需要7个工作日审核
     * status : 1
     * info : 获取成功
     * goods_list : [{"goods_title":"地毯地垫厨房浴室防滑地垫门厅玄关入户地垫客厅卧室防尘蹭蹭垫子","id":1746,"img":"images/vslai_shop/1604/2018/01/HVZZP2PvQwm5qTPv24vWOjvOjMOqWP.jpg","goods_rule":"","price":"52.00","total_money":0,"des1":"近7日收益¥0","des2":"近7日销量0件","num1":0,"num2":0,"max":0,"profit":[{"title":"01-31","v1":0,"v2":0},{"title":"02-01","v1":0,"v2":0},{"title":"02-02","v1":0,"v2":0},{"title":"02-03","v1":0,"v2":0},{"title":"02-04","v1":0,"v2":0},{"title":"02-05","v1":0,"v2":0},{"title":"02-06","v1":0,"v2":0}]},{"goods_title":"优云优选新春大礼包","id":1747,"img":"images/1604/2018/01/p80191e10hZZvONzVt2Hz24YS1d8h0.png","goods_rule":"一整箱小吃年货组合礼包，超大容量18款进口零食11","price":"199.00","total_money":0,"des1":"近7日收益¥0","des2":"近7日销量0件","num1":11,"num2":66,"max":0,"profit":[{"title":"01-31","v1":0,"v2":0},{"title":"02-01","v1":0,"v2":0},{"title":"02-02","v1":0,"v2":0},{"title":"02-03","v1":0,"v2":0},{"title":"02-04","v1":0,"v2":0},{"title":"02-05","v1":0,"v2":0},{"title":"02-06","v1":0,"v2":0}]}]
     */

    private String title;
    private String k_money;
    private String y_money;
    private String des;
    private int status;
    private String info;
    private List<GoodsListBean> goods_list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getK_money() {
        return k_money;
    }

    public void setK_money(String k_money) {
        this.k_money = k_money;
    }

    public String getY_money() {
        return y_money;
    }

    public void setY_money(String y_money) {
        this.y_money = y_money;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
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
         * goods_title : 地毯地垫厨房浴室防滑地垫门厅玄关入户地垫客厅卧室防尘蹭蹭垫子
         * id : 1746
         * img : images/vslai_shop/1604/2018/01/HVZZP2PvQwm5qTPv24vWOjvOjMOqWP.jpg
         * goods_rule :
         * price : 52.00
         * total_money : 0
         * des1 : 近7日收益¥0
         * des2 : 近7日销量0件
         * num1 : 0
         * num2 : 0
         * max : 0
         * profit : [{"title":"01-31","v1":0,"v2":0},{"title":"02-01","v1":0,"v2":0},{"title":"02-02","v1":0,"v2":0},{"title":"02-03","v1":0,"v2":0},{"title":"02-04","v1":0,"v2":0},{"title":"02-05","v1":0,"v2":0},{"title":"02-06","v1":0,"v2":0}]
         */

        private String goods_title;
        private int id;
        private String img;
        private String goods_rule;
        private String price;
        private String total_money;

        public String getTotal_money() {
            return total_money;
        }

        private String des1;
        private String des2;
        private int num1;
        private int num2;
        private int max;
        private List<ProfitBean> profit;
        private boolean isZhanKai;

        public void setTotal_money(String total_money) {
            this.total_money = total_money;
        }

        public boolean isZhanKai() {
            return isZhanKai;
        }

        public void setZhanKai(boolean zhanKai) {
            isZhanKai = zhanKai;
        }

        public String getGoods_title() {
            return goods_title;
        }

        public void setGoods_title(String goods_title) {
            this.goods_title = goods_title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
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

        public int getNum1() {
            return num1;
        }

        public void setNum1(int num1) {
            this.num1 = num1;
        }

        public int getNum2() {
            return num2;
        }

        public void setNum2(int num2) {
            this.num2 = num2;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public List<ProfitBean> getProfit() {
            return profit;
        }

        public void setProfit(List<ProfitBean> profit) {
            this.profit = profit;
        }

        public static class ProfitBean {
            /**
             * title : 01-31
             * v1 : 0
             * v2 : 0
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
