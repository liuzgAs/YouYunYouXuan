package com.vip.uyux.model;

/**
 * Created by zhangjiebo on 2018/1/22/022.
 *
 * @author ZhangJieBo
 */

public class OrderConfirmbeforeJiFen {
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
    private String sum;
    private AdBean ad;
    private String shipKey;
    private String shipDes;
    private int status;
    private String info;
    private CartBean cart;

    public int getIs_address() {
        return is_address;
    }

    public void setIs_address(int is_address) {
        this.is_address = is_address;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public AdBean getAd() {
        return ad;
    }

    public void setAd(AdBean ad) {
        this.ad = ad;
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

    public CartBean getCart() {
        return cart;
    }

    public void setCart(CartBean cart) {
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

        private int goods_id;
        private int goods_score;
        private int num;
        private String goods_img;
        private String goods_title;

        public int getGoods_score() {
            return goods_score;
        }

        public void setGoods_score(int goods_score) {
            this.goods_score = goods_score;
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
