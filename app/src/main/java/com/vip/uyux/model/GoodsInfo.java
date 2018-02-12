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
    private CommentBean comment;

    public CommentBean getComment() {
        return comment;
    }

    public void setComment(CommentBean comment) {
        this.comment = comment;
    }

    public static class CommentBean {
        private String title;
        private String des;
        private UserBean user;
        private List<ImgsBean> imgs;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public List<ImgsBean> getImgs() {
            return imgs;
        }

        public void setImgs(List<ImgsBean> imgs) {
            this.imgs = imgs;
        }

        public static class UserBean {
            private String headimg;
            private String nickname;
            private String vip;
            private int star;

            public String getHeadimg() {
                return headimg;
            }

            public void setHeadimg(String headimg) {
                this.headimg = headimg;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getVip() {
                return vip;
            }

            public void setVip(String vip) {
                this.vip = vip;
            }

            public int getStar() {
                return star;
            }

            public void setStar(int star) {
                this.star = star;
            }
        }

        private String evaluate_time;
        private String evaluate;

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

        public String getEvaluate_time() {
            return evaluate_time;
        }

        public void setEvaluate_time(String evaluate_time) {
            this.evaluate_time = evaluate_time;
        }

        public String getEvaluate() {
            return evaluate;
        }

        public void setEvaluate(String evaluate) {
            this.evaluate = evaluate;
        }
    }

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
        private String vipPrice;
        private String oldPrice;
        private String vipDes;
        private int countdown;
        private String countdownDes;
        private String saleNum;
        private int stockNum;
        private String shareMoney;
        private String scoreDes;
        private String thumb;
        private int is_gotm;
        private String tm_url;
        private String tm_price;
        private int is_gojd;
        private String jd_url;
        private String jd_price;
        private ShareBean share;
        private List<ImgsBean> imgs;
        private List<ImgsBean> imgs2;
        private List<PromotionsafterBean> promotionsAfter;
        private List<String> serveiceDes;
        private String promotionsBefore;

        public int getIs_gotm() {
            return is_gotm;
        }

        public void setIs_gotm(int is_gotm) {
            this.is_gotm = is_gotm;
        }

        public String getTm_price() {
            return tm_price;
        }

        public void setTm_price(String tm_price) {
            this.tm_price = tm_price;
        }

        public int getIs_gojd() {
            return is_gojd;
        }

        public void setIs_gojd(int is_gojd) {
            this.is_gojd = is_gojd;
        }

        public String getJd_url() {
            return jd_url;
        }

        public void setJd_url(String jd_url) {
            this.jd_url = jd_url;
        }

        public String getJd_price() {
            return jd_price;
        }

        public void setJd_price(String jd_price) {
            this.jd_price = jd_price;
        }

        public String getPromotionsBefore() {
            return promotionsBefore;
        }

        public void setPromotionsBefore(String promotionsBefore) {
            this.promotionsBefore = promotionsBefore;
        }

        public List<String> getServeiceDes() {
            return serveiceDes;
        }

        public void setServeiceDes(List<String> serveiceDes) {
            this.serveiceDes = serveiceDes;
        }

        public List<PromotionsafterBean> getPromotionsAfter() {
            return promotionsAfter;
        }

        public void setPromotionsAfter(List<PromotionsafterBean> promotionsAfter) {
            this.promotionsAfter = promotionsAfter;
        }

        public static class PromotionsafterBean {
            private String title;
            private String url;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

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

        public String getVipPrice() {
            return vipPrice;
        }

        public void setVipPrice(String vipPrice) {
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
        private boolean isSelect;
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
