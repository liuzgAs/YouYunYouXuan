package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/22/022.
 *
 * @author ZhangJieBo
 */

public class CartIndex {
    /**
     * cart : [{"id":2,"uid":1,"goods_id":1697,"num":1,"spe_name":"颜色:咖啡色,尺码:M","sku_id":1,"buy_now":0,"did":1,"create_time":1516515840,"update_time":0,"goods_price":"88.00","goods_img":"http://www.uyux.vip/attachment/images/1604/2018/01/aVvkRUfsjsZJvq0r0kKw707Te7esED.jpg","goods_title":"猴子系列茶宠"}]
     * sum : 88
     * status : 1
     * info : 返回成功！
     */

    private int sum;
    private int status;
    private String info;
    private List<CartBean> cart;

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
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

    public List<CartBean> getCart() {
        return cart;
    }

    public void setCart(List<CartBean> cart) {
        this.cart = cart;
    }

    public static class CartBean {
        /**
         * id : 2
         * uid : 1
         * goods_id : 1697
         * num : 1
         * spe_name : 颜色:咖啡色,尺码:M
         * sku_id : 1
         * buy_now : 0
         * did : 1
         * create_time : 1516515840
         * update_time : 0
         * goods_price : 88.00
         * goods_img : http://www.uyux.vip/attachment/images/1604/2018/01/aVvkRUfsjsZJvq0r0kKw707Te7esED.jpg
         * goods_title : 猴子系列茶宠
         */

        private int id;
        private int uid;
        private int goods_id;
        private int num;
        private String spe_name;
        private int sku_id;
        private int buy_now;
        private int did;
        private int create_time;
        private int update_time;
        private double goods_price;
        private String goods_img;
        private String goods_title;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getSpe_name() {
            return spe_name;
        }

        public void setSpe_name(String spe_name) {
            this.spe_name = spe_name;
        }

        public int getSku_id() {
            return sku_id;
        }

        public void setSku_id(int sku_id) {
            this.sku_id = sku_id;
        }

        public int getBuy_now() {
            return buy_now;
        }

        public void setBuy_now(int buy_now) {
            this.buy_now = buy_now;
        }

        public int getDid() {
            return did;
        }

        public void setDid(int did) {
            this.did = did;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public int getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(int update_time) {
            this.update_time = update_time;
        }

        public double getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(double goods_price) {
            this.goods_price = goods_price;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }

        public String getGoods_title() {
            return goods_title;
        }

        public void setGoods_title(String goods_title) {
            this.goods_title = goods_title;
        }
    }
}
