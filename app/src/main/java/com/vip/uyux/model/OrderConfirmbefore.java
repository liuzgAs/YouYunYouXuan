package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/22/022.
 *
 * @author ZhangJieBo
 */

public class OrderConfirmbefore {
    /**
     * is_address : 0
     * cart : [{"id":2,"uid":1,"goods_id":1697,"num":1,"spe_name":"颜色:咖啡色,尺码:M","sku_id":1,"buy_now":0,"did":1,"create_time":1516515840,"update_time":0,"goods_price":"88.00","goods_img":"http://www.uyux.vip/attachment/images/1604/2018/01/aVvkRUfsjsZJvq0r0kKw707Te7esED.jpg","goods_title":"猴子系列茶宠"}]
     * sum : 88
     * ad : {"id":1,"consignee":"asd","phone":"15860026755","address":"观音山商务区6号楼","area":"福建省-厦门市-思明区"}
     * vipKey : 会员权益
     * vipDes : 已为您节省10元
     * vipLv : 2
     * shipKey : 配送方式
     * shipDes : 快递(包邮)
     * status : 1
     * info : 返回成功！
     */

    private int is_address;
    private double sum;
    private AdBean ad;
    private String vipKey;
    private String vipDes;
    private int vipLv;
    private String shipKey;
    private String shipDes;
    private int status;
    private String info;
    private List<CartBean> cart;

    public int getIs_address() {
        return is_address;
    }

    public void setIs_address(int is_address) {
        this.is_address = is_address;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public AdBean getAd() {
        return ad;
    }

    public void setAd(AdBean ad) {
        this.ad = ad;
    }

    public String getVipKey() {
        return vipKey;
    }

    public void setVipKey(String vipKey) {
        this.vipKey = vipKey;
    }

    public String getVipDes() {
        return vipDes;
    }

    public void setVipDes(String vipDes) {
        this.vipDes = vipDes;
    }

    public int getVipLv() {
        return vipLv;
    }

    public void setVipLv(int vipLv) {
        this.vipLv = vipLv;
    }

    public String getShipKey() {
        return shipKey;
    }

    public void setShipKey(String shipKey) {
        this.shipKey = shipKey;
    }

    public String getShipDes() {
        return shipDes;
    }

    public void setShipDes(String shipDes) {
        this.shipDes = shipDes;
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

    public static class AdBean {
        /**
         * id : 1
         * consignee : asd
         * phone : 15860026755
         * address : 观音山商务区6号楼
         * area : 福建省-厦门市-思明区
         */

        private String id;
        private String consignee;
        private String phone;
        private String address;
        private String area;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }
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
        private String goods_price;
        private String goods_img;
        private String goods_title;

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

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
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
