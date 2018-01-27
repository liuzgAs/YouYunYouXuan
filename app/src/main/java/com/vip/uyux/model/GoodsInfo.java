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
     * data : {"id":1123,"title":"有机小薏米","des":"前凸后凹 碎粒少","price":"22.60","vipPrice":14.6,"oldPrice":"28.80","vipDes":"V1会员价","countdown":0,"countdownDes":"距离抢购还剩","saleNum":"5588","stockNum":49,"shareMoney":"分享赚￥76.8","scoreDes":"最高可获得20积分","imgs":[{"img":"http://www.wanhuaxin.com/attachment/images/1604/2017/12/I6eh7fb7hWeJxh7Ff2BaA6K3xwj4a7.jpg"},{"img":"http://www.wanhuaxin.com/attachment/images/1604/2017/12/Y198of3RfTfo0FOHKxo3oRZu03UfGo.jpg"},{"img":"http://www.uyux.vip/attachment/images/1604/2017/12/e2ZnFwpA9naoF2AoMvyyNt9nNoVN8h.jpg"}],"imgs2":[{"img":"http://www.wanhuaxin.com/attachment/images/1604/2017/12/I6eh7fb7hWeJxh7Ff2BaA6K3xwj4a7.jpg"},{"img":"http://www.wanhuaxin.com/attachment/images/1604/2017/12/Y198of3RfTfo0FOHKxo3oRZu03UfGo.jpg"},{"img":"http://www.uyux.vip/attachment/images/1604/2017/12/e2ZnFwpA9naoF2AoMvyyNt9nNoVN8h.jpg"}]}
     * skuLv : [{"lv":0,"name":"颜色"},{"lv":1,"name":"尺码"}]
     * skuCate : [{"key":8,"name":"咖啡色","value":[{"key":2,"name":"M","value":[],"sku_id":6,"price":"88.00","lv":1},{"key":3,"name":"S","value":[],"sku_id":7,"price":"88.00","lv":1},{"key":4,"name":"L","value":[],"sku_id":8,"price":"88.00","lv":1},{"key":5,"name":"XL","value":[],"sku_id":9,"price":"88.00","lv":1},{"key":6,"name":"XXL","value":[],"sku_id":10,"price":"88.00","lv":1}],"sku_id":0,"price":0,"lv":0},{"key":9,"name":"腮红色","value":[{"key":2,"name":"M","value":[],"sku_id":6,"price":"88.00","lv":1},{"key":3,"name":"S","value":[],"sku_id":7,"price":"88.00","lv":1},{"key":4,"name":"L","value":[],"sku_id":8,"price":"88.00","lv":1},{"key":5,"name":"XL","value":[],"sku_id":9,"price":"88.00","lv":1},{"key":6,"name":"XXL","value":[],"sku_id":10,"price":"88.00","lv":1}],"sku_id":0,"price":0,"lv":0}]
     * status : 1
     * info : 返回成功！
     */

    private DataBean data;
    private int status;
    private String info;
    private List<String> banner;
    private List<SkuLvBean> skuLv;
    private List<SkuCateBean> skuCate;
    private int isc;

    public int getIsc() {
        return isc;
    }

    public void setIsc(int isc) {
        this.isc = isc;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public List<String> getBanner() {
        return banner;
    }

    public void setBanner(List<String> banner) {
        this.banner = banner;
    }

    public List<SkuLvBean> getSkuLv() {
        return skuLv;
    }

    public void setSkuLv(List<SkuLvBean> skuLv) {
        this.skuLv = skuLv;
    }

    public List<SkuCateBean> getSkuCate() {
        return skuCate;
    }

    public void setSkuCate(List<SkuCateBean> skuCate) {
        this.skuCate = skuCate;
    }

    public static class DataBean {
        /**
         * id : 1123
         * title : 有机小薏米
         * des : 前凸后凹 碎粒少
         * price : 22.60
         * vipPrice : 14.6
         * oldPrice : 28.80
         * vipDes : V1会员价
         * countdown : 0
         * countdownDes : 距离抢购还剩
         * saleNum : 5588
         * stockNum : 49
         * shareMoney : 分享赚￥76.8
         * scoreDes : 最高可获得20积分
         * imgs : [{"img":"http://www.wanhuaxin.com/attachment/images/1604/2017/12/I6eh7fb7hWeJxh7Ff2BaA6K3xwj4a7.jpg"},{"img":"http://www.wanhuaxin.com/attachment/images/1604/2017/12/Y198of3RfTfo0FOHKxo3oRZu03UfGo.jpg"},{"img":"http://www.uyux.vip/attachment/images/1604/2017/12/e2ZnFwpA9naoF2AoMvyyNt9nNoVN8h.jpg"}]
         * imgs2 : [{"img":"http://www.wanhuaxin.com/attachment/images/1604/2017/12/I6eh7fb7hWeJxh7Ff2BaA6K3xwj4a7.jpg"},{"img":"http://www.wanhuaxin.com/attachment/images/1604/2017/12/Y198of3RfTfo0FOHKxo3oRZu03UfGo.jpg"},{"img":"http://www.uyux.vip/attachment/images/1604/2017/12/e2ZnFwpA9naoF2AoMvyyNt9nNoVN8h.jpg"}]
         */

        private int id;
        private String title;
        private String des;
        private String price;
        private double vipPrice;
        private String oldPrice;
        private String vipDes;
        private int countdown;
        private String countdownDes;
        private String saleNum;
        private int stockNum;
        private String shareMoney;
        private String scoreDes;
        private String thumb;
        private String tm_url;
        private ShareBean share;
        private List<ImgsBean> imgs;
        private List<ImgsBean> imgs2;

        public String getTm_url() {
            return tm_url;
        }

        public void setTm_url(String tm_url) {
            this.tm_url = tm_url;
        }

        public ShareBean getShare() {
            return share;
        }

        public void setShare(ShareBean share) {
            this.share = share;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public double getVipPrice() {
            return vipPrice;
        }

        public void setVipPrice(double vipPrice) {
            this.vipPrice = vipPrice;
        }

        public String getOldPrice() {
            return oldPrice;
        }

        public void setOldPrice(String oldPrice) {
            this.oldPrice = oldPrice;
        }

        public String getVipDes() {
            return vipDes;
        }

        public void setVipDes(String vipDes) {
            this.vipDes = vipDes;
        }

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

        public String getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(String saleNum) {
            this.saleNum = saleNum;
        }

        public int getStockNum() {
            return stockNum;
        }

        public void setStockNum(int stockNum) {
            this.stockNum = stockNum;
        }

        public String getShareMoney() {
            return shareMoney;
        }

        public void setShareMoney(String shareMoney) {
            this.shareMoney = shareMoney;
        }

        public String getScoreDes() {
            return scoreDes;
        }

        public void setScoreDes(String scoreDes) {
            this.scoreDes = scoreDes;
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

    public static class SkuLvBean {
        /**
         * lv : 0
         * name : 颜色
         */

        private int lv;
        private String name;

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
    }

    public static class SkuCateBean {
        /**
         * key : 8
         * name : 咖啡色
         * value : [{"key":2,"name":"M","value":[],"sku_id":6,"price":"88.00","lv":1},{"key":3,"name":"S","value":[],"sku_id":7,"price":"88.00","lv":1},{"key":4,"name":"L","value":[],"sku_id":8,"price":"88.00","lv":1},{"key":5,"name":"XL","value":[],"sku_id":9,"price":"88.00","lv":1},{"key":6,"name":"XXL","value":[],"sku_id":10,"price":"88.00","lv":1}]
         * sku_id : 0
         * price : 0
         * lv : 0
         */

        private int key;
        private String name;
        private int sku_id;
        private double price;
        private int stock_num;
        private int lv;
        private boolean isSelect ;
        private List<SkuCateBean> value;

        public int getStock_num() {
            return stock_num;
        }

        public void setStock_num(int stock_num) {
            this.stock_num = stock_num;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public List<SkuCateBean> getValue() {
            return value;
        }

        public void setValue(List<SkuCateBean> value) {
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSku_id() {
            return sku_id;
        }

        public void setSku_id(int sku_id) {
            this.sku_id = sku_id;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getLv() {
            return lv;
        }

        public void setLv(int lv) {
            this.lv = lv;
        }

    }
}
