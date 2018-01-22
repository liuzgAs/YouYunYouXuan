package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/21/021.
 *
 * @author ZhangJieBo
 */

public class GoodsInfo {

    /**
     * banner : ["http://www.uyux.vip/attachment/images/1604/2017/12/ZuWjCyEEMEjsc7syEwMSsLsPv2Vw6c.jpg"]
     * data : {"countdown":0,"countdownDes":"距离抢购还剩","des":"前凸后凹 碎粒少","id":1123,"imgs":[{"img":"http://www.wanhuaxin.com/attachment/images/1604/2017/12/I6eh7fb7hWeJxh7Ff2BaA6K3xwj4a7.jpg"},{"img":"http://www.wanhuaxin.com/attachment/images/1604/2017/12/Y198of3RfTfo0FOHKxo3oRZu03UfGo.jpg"},{"img":"http://www.uyux.vip/attachment/images/1604/2017/12/e2ZnFwpA9naoF2AoMvyyNt9nNoVN8h.jpg"}],"imgs2":[{"img":"http://www.wanhuaxin.com/attachment/images/1604/2017/12/I6eh7fb7hWeJxh7Ff2BaA6K3xwj4a7.jpg"},{"img":"http://www.wanhuaxin.com/attachment/images/1604/2017/12/Y198of3RfTfo0FOHKxo3oRZu03UfGo.jpg"},{"img":"http://www.uyux.vip/attachment/images/1604/2017/12/e2ZnFwpA9naoF2AoMvyyNt9nNoVN8h.jpg"}],"oldPrice":"28.80","price":"22.60","saleNum":"5588","scoreDes":"最高可获得20积分","shareMoney":"分享赚￥76.8","stockNum":49,"title":"有机小薏米","vipDes":"V1会员价","vipPrice":14.6}
     * info : 返回成功！
     * skuCate : [{"key":8,"lv":0,"name":"咖啡色","price":0,"value":[{"key":2,"lv":1,"name":"M","price":"88.00","value":6},{"key":3,"lv":1,"name":"S","price":"88.00","value":7},{"key":4,"lv":1,"name":"L","price":"88.00","value":8},{"key":5,"lv":1,"name":"XL","price":"88.00","value":9},{"key":6,"lv":1,"name":"XXL","price":"88.00","value":10}]},{"key":9,"lv":0,"name":"腮红色","price":0,"value":[{"key":2,"lv":1,"name":"M","price":"88.00","value":6},{"key":3,"lv":1,"name":"S","price":"88.00","value":7},{"key":4,"lv":1,"name":"L","price":"88.00","value":8},{"key":5,"lv":1,"name":"XL","price":"88.00","value":9},{"key":6,"lv":1,"name":"XXL","price":"88.00","value":10}]}]
     * status : 1
     */

    private DataBean data;
    private String info;
    private int status;
    private List<String> banner;
    private List<SkuCateBean> skuCate;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getBanner() {
        return banner;
    }

    public void setBanner(List<String> banner) {
        this.banner = banner;
    }

    public List<SkuCateBean> getSkuCate() {
        return skuCate;
    }

    public void setSkuCate(List<SkuCateBean> skuCate) {
        this.skuCate = skuCate;
    }

    public static class DataBean {
        /**
         * countdown : 0
         * countdownDes : 距离抢购还剩
         * des : 前凸后凹 碎粒少
         * id : 1123
         * imgs : [{"img":"http://www.wanhuaxin.com/attachment/images/1604/2017/12/I6eh7fb7hWeJxh7Ff2BaA6K3xwj4a7.jpg"},{"img":"http://www.wanhuaxin.com/attachment/images/1604/2017/12/Y198of3RfTfo0FOHKxo3oRZu03UfGo.jpg"},{"img":"http://www.uyux.vip/attachment/images/1604/2017/12/e2ZnFwpA9naoF2AoMvyyNt9nNoVN8h.jpg"}]
         * imgs2 : [{"img":"http://www.wanhuaxin.com/attachment/images/1604/2017/12/I6eh7fb7hWeJxh7Ff2BaA6K3xwj4a7.jpg"},{"img":"http://www.wanhuaxin.com/attachment/images/1604/2017/12/Y198of3RfTfo0FOHKxo3oRZu03UfGo.jpg"},{"img":"http://www.uyux.vip/attachment/images/1604/2017/12/e2ZnFwpA9naoF2AoMvyyNt9nNoVN8h.jpg"}]
         * oldPrice : 28.80
         * price : 22.60
         * saleNum : 5588
         * scoreDes : 最高可获得20积分
         * shareMoney : 分享赚￥76.8
         * stockNum : 49
         * title : 有机小薏米
         * vipDes : V1会员价
         * vipPrice : 14.6
         */

        private int countdown;
        private String countdownDes;
        private String des;
        private int id;
        private String oldPrice;
        private String price;
        private String saleNum;
        private String scoreDes;
        private String shareMoney;
        private int stockNum;
        private String title;
        private String vipDes;
        private String vipPrice;
        private List<ImgsBean> imgs;
        private List<ImgsBean> imgs2;

        public int getCountdown() {
            return countdown;
        }

        public void setCountdown(int countdown) {
            this.countdown = countdown;
        }

        public String getCountdownDes() {
            return countdownDes;
        }

        public void setCountdownDes(String countdownDes) {
            this.countdownDes = countdownDes;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOldPrice() {
            return oldPrice;
        }

        public void setOldPrice(String oldPrice) {
            this.oldPrice = oldPrice;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(String saleNum) {
            this.saleNum = saleNum;
        }

        public String getScoreDes() {
            return scoreDes;
        }

        public void setScoreDes(String scoreDes) {
            this.scoreDes = scoreDes;
        }

        public String getShareMoney() {
            return shareMoney;
        }

        public void setShareMoney(String shareMoney) {
            this.shareMoney = shareMoney;
        }

        public int getStockNum() {
            return stockNum;
        }

        public void setStockNum(int stockNum) {
            this.stockNum = stockNum;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getVipDes() {
            return vipDes;
        }

        public void setVipDes(String vipDes) {
            this.vipDes = vipDes;
        }

        public String getVipPrice() {
            return vipPrice;
        }

        public void setVipPrice(String vipPrice) {
            this.vipPrice = vipPrice;
        }

        public List<ImgsBean> getImgs() {
            return imgs;
        }

        public void setImgs(List<ImgsBean> imgs) {
            this.imgs = imgs;
        }

        public List<ImgsBean> getImgs2() {
            return imgs2;
        }

        public void setImgs2(List<ImgsBean> imgs2) {
            this.imgs2 = imgs2;
        }

        public static class ImgsBean {
            /**
             * img : http://www.wanhuaxin.com/attachment/images/1604/2017/12/I6eh7fb7hWeJxh7Ff2BaA6K3xwj4a7.jpg
             */

            private String img;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }

    }

    public static class SkuCateBean {
        /**
         * key : 8
         * lv : 0
         * name : 咖啡色
         * price : 0
         * value : [{"key":2,"lv":1,"name":"M","price":"88.00","value":6},{"key":3,"lv":1,"name":"S","price":"88.00","value":7},{"key":4,"lv":1,"name":"L","price":"88.00","value":8},{"key":5,"lv":1,"name":"XL","price":"88.00","value":9},{"key":6,"lv":1,"name":"XXL","price":"88.00","value":10}]
         */

        private int key;
        private int lv;
        private String name;
        private int price;
        private List<ValueBean> value;

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getLv() {
            return lv;
        }

        public void setLv(int lv) {
            this.lv = lv;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public List<ValueBean> getValue() {
            return value;
        }

        public void setValue(List<ValueBean> value) {
            this.value = value;
        }

        public static class ValueBean {
            /**
             * key : 2
             * lv : 1
             * name : M
             * price : 88.00
             * value : 6
             */

            private int key;
            private int lv;
            private String name;
            private String price;
            private int value;
            private boolean isSelect;

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public int getKey() {
                return key;
            }

            public void setKey(int key) {
                this.key = key;
            }

            public int getLv() {
                return lv;
            }

            public void setLv(int lv) {
                this.lv = lv;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }
        }
    }
}
