package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/26/026.
 *
 * @author ZhangJieBo
 */

public class CustomerIntegragoodsinfo {
    /**
     * banner : ["http://www.uyux.vip/attachment/images/1604/2017/12/p70v790voOTOo5i7fLoITo00VtFpft.jpg"]
     * data : {"thumb":"http://www.uyux.vip/attachment/images/1604/2017/12/p70v790voOTOo5i7fLoITo00VtFpft.jpg","id":1133,"title":"玉米胶囊杯","des":"纯天然PLA材质","integra_price":2999,"oldPrice":"99.00","has_integra":2368,"has_des":"积分不足","saleNum":"5588","stockNum":84,"share":{"title":"赚1.86","des1":"只要你的好友通过你的分享购买此商品，你就能赚到至少","desMoney":1.86,"des2":"元利润哦~","shareImg":"http://www.uyux.vip/attachment/images/1604/2017/12/p70v790voOTOo5i7fLoITo00VtFpft.jpg","shareTitle":"玉米胶囊杯","shareUrl":"http://www.uyux.vip/attachment//M/Goods/details/sid","shareDes":"有人@你　你有一个分享尚未点击"},"imgs":[{"img":"http://www.wanhuaxin.com/attachment/images/1604/2017/12/d4YEuQ07jUKlvkJ3U0MKmXZ04lnNpa.jpeg"},{"img":"http://www.wanhuaxin.com/attachment/images/1604/2017/12/zzknD2LXCd444zDdncccDT7clzG4Pk.jpg"}],"imgs2":[{"img":"http://www.wanhuaxin.com/attachment/images/1604/2017/12/d4YEuQ07jUKlvkJ3U0MKmXZ04lnNpa.jpeg"},{"img":"http://www.wanhuaxin.com/attachment/images/1604/2017/12/zzknD2LXCd444zDdncccDT7clzG4Pk.jpg"}]}
     * isc : 0
     * skuLv : [{"lv":0,"name":"颜色"},{"lv":1,"name":"尺码"},{"lv":2,"name":"厚度"}]
     * skuCate : [{"key":8,"name":"咖啡色","value":[{"key":2,"name":"M","value":[{"key":11,"name":"加绒","value":[],"sku_id":1,"price":"88.00","lv":2},{"key":12,"name":"不加绒","value":[],"sku_id":11,"price":"88.00","lv":2}],"sku_id":0,"price":0,"lv":1},{"key":3,"name":"S","value":[{"key":11,"name":"加绒","value":[],"sku_id":2,"price":"88.00","lv":2},{"key":12,"name":"不加绒","value":[],"sku_id":12,"price":"88.00","lv":2}],"sku_id":0,"price":0,"lv":1},{"key":4,"name":"L","value":[{"key":11,"name":"加绒","value":[],"sku_id":3,"price":"88.00","lv":2},{"key":12,"name":"不加绒","value":[],"sku_id":13,"price":"88.00","lv":2}],"sku_id":0,"price":0,"lv":1},{"key":5,"name":"XL","value":[{"key":11,"name":"加绒","value":[],"sku_id":4,"price":"88.00","lv":2},{"key":12,"name":"不加绒","value":[],"sku_id":14,"price":"88.00","lv":2}],"sku_id":0,"price":0,"lv":1},{"key":6,"name":"XXL","value":[{"key":11,"name":"加绒","value":[],"sku_id":5,"price":"88.00","lv":2},{"key":12,"name":"不加绒","value":[],"sku_id":15,"price":"88.00","lv":2}],"sku_id":0,"price":0,"lv":1}],"sku_id":0,"price":0,"lv":0},{"key":9,"name":"腮红色","value":[{"key":2,"name":"M","value":[{"key":11,"name":"加绒","value":[],"sku_id":6,"price":"88.00","lv":2},{"key":12,"name":"不加绒","value":[],"sku_id":16,"price":"88.00","lv":2}],"sku_id":0,"price":0,"lv":1},{"key":3,"name":"S","value":[{"key":11,"name":"加绒","value":[],"sku_id":7,"price":"88.00","lv":2},{"key":12,"name":"不加绒","value":[],"sku_id":17,"price":"88.00","lv":2}],"sku_id":0,"price":0,"lv":1},{"key":4,"name":"L","value":[{"key":11,"name":"加绒","value":[],"sku_id":8,"price":"88.00","lv":2},{"key":12,"name":"不加绒","value":[],"sku_id":18,"price":"88.00","lv":2}],"sku_id":0,"price":0,"lv":1},{"key":5,"name":"XL","value":[{"key":11,"name":"加绒","value":[],"sku_id":9,"price":"88.00","lv":2},{"key":12,"name":"不加绒","value":[],"sku_id":19,"price":"88.00","lv":2}],"sku_id":0,"price":0,"lv":1},{"key":6,"name":"XXL","value":[{"key":11,"name":"加绒","value":[],"sku_id":10,"price":"88.00","lv":2},{"key":12,"name":"不加绒","value":[],"sku_id":20,"price":"88.00","lv":2}],"sku_id":0,"price":0,"lv":1}],"sku_id":0,"price":0,"lv":0}]
     * status : 1
     * info : 返回成功！
     */

    private DataBean data;
    private int isc;
    private int status;
    private String info;
    private List<String> banner;
    private List<SkuLvBean> skuLv;
    private List<SkuCateBean> skuCate;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getIsc() {
        return isc;
    }

    public void setIsc(int isc) {
        this.isc = isc;
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
         * thumb : http://www.uyux.vip/attachment/images/1604/2017/12/p70v790voOTOo5i7fLoITo00VtFpft.jpg
         * id : 1133
         * title : 玉米胶囊杯
         * des : 纯天然PLA材质
         * integra_price : 2999
         * oldPrice : 99.00
         * has_integra : 2368
         * has_des : 积分不足
         * saleNum : 5588
         * stockNum : 84
         * share : {"title":"赚1.86","des1":"只要你的好友通过你的分享购买此商品，你就能赚到至少","desMoney":1.86,"des2":"元利润哦~","shareImg":"http://www.uyux.vip/attachment/images/1604/2017/12/p70v790voOTOo5i7fLoITo00VtFpft.jpg","shareTitle":"玉米胶囊杯","shareUrl":"http://www.uyux.vip/attachment//M/Goods/details/sid","shareDes":"有人@你　你有一个分享尚未点击"}
         * imgs : [{"img":"http://www.wanhuaxin.com/attachment/images/1604/2017/12/d4YEuQ07jUKlvkJ3U0MKmXZ04lnNpa.jpeg"},{"img":"http://www.wanhuaxin.com/attachment/images/1604/2017/12/zzknD2LXCd444zDdncccDT7clzG4Pk.jpg"}]
         * imgs2 : [{"img":"http://www.wanhuaxin.com/attachment/images/1604/2017/12/d4YEuQ07jUKlvkJ3U0MKmXZ04lnNpa.jpeg"},{"img":"http://www.wanhuaxin.com/attachment/images/1604/2017/12/zzknD2LXCd444zDdncccDT7clzG4Pk.jpg"}]
         */

        private String thumb;
        private int id;
        private String title;
        private String des;
        private int integra_price;
        private String oldPrice;
        private int has_integra;
        private String has_des;
        private String saleNum;
        private int stockNum;
        private ShareBean share;
        private List<ImgsBean> imgs;
        private List<ImgsBean> imgs2;

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

        public int getIntegra_price() {
            return integra_price;
        }

        public void setIntegra_price(int integra_price) {
            this.integra_price = integra_price;
        }

        public String getOldPrice() {
            return oldPrice;
        }

        public void setOldPrice(String oldPrice) {
            this.oldPrice = oldPrice;
        }

        public int getHas_integra() {
            return has_integra;
        }

        public void setHas_integra(int has_integra) {
            this.has_integra = has_integra;
        }

        public String getHas_des() {
            return has_des;
        }

        public void setHas_des(String has_des) {
            this.has_des = has_des;
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

        public ShareBean getShare() {
            return share;
        }

        public void setShare(ShareBean share) {
            this.share = share;
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
             * img : http://www.wanhuaxin.com/attachment/images/1604/2017/12/d4YEuQ07jUKlvkJ3U0MKmXZ04lnNpa.jpeg
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
         * value : [{"key":2,"name":"M","value":[{"key":11,"name":"加绒","value":[],"sku_id":1,"price":"88.00","lv":2},{"key":12,"name":"不加绒","value":[],"sku_id":11,"price":"88.00","lv":2}],"sku_id":0,"price":0,"lv":1},{"key":3,"name":"S","value":[{"key":11,"name":"加绒","value":[],"sku_id":2,"price":"88.00","lv":2},{"key":12,"name":"不加绒","value":[],"sku_id":12,"price":"88.00","lv":2}],"sku_id":0,"price":0,"lv":1},{"key":4,"name":"L","value":[{"key":11,"name":"加绒","value":[],"sku_id":3,"price":"88.00","lv":2},{"key":12,"name":"不加绒","value":[],"sku_id":13,"price":"88.00","lv":2}],"sku_id":0,"price":0,"lv":1},{"key":5,"name":"XL","value":[{"key":11,"name":"加绒","value":[],"sku_id":4,"price":"88.00","lv":2},{"key":12,"name":"不加绒","value":[],"sku_id":14,"price":"88.00","lv":2}],"sku_id":0,"price":0,"lv":1},{"key":6,"name":"XXL","value":[{"key":11,"name":"加绒","value":[],"sku_id":5,"price":"88.00","lv":2},{"key":12,"name":"不加绒","value":[],"sku_id":15,"price":"88.00","lv":2}],"sku_id":0,"price":0,"lv":1}]
         * sku_id : 0
         * price : 0
         * lv : 0
         */

        private int key;
        private String name;
        private int sku_id;
        private int price;
        private int lv;
        private int stock_num;
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

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getLv() {
            return lv;
        }

        public void setLv(int lv) {
            this.lv = lv;
        }

        public List<SkuCateBean> getValue() {
            return value;
        }

        public void setValue(List<SkuCateBean> value) {
            this.value = value;
        }

    }
}
