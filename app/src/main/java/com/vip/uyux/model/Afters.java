package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/2/5/005.
 *
 * @author ZhangJieBo
 */

public class Afters {
    /**
     * data : [{"id":504,"uid":1011113,"img":"http://app.uyux.vip/attachment/images/1604/2017/12/I3G434jXcj0Xz00m934XxxZeuiy4QU.jpg","goods_id":1353,"goods_name":"美国原装进口桂格燕麦","goods_price":"49.50","create_time":"2018.02.01","quantity":1,"sku_id":0,"headimg":"http://app.uyux.vip","nickname":"片片啊片片","spe_name":""},{"id":503,"uid":1011113,"img":"http://app.uyux.vip/attachment/images/1604/2017/12/kB87BH7H73DHD8H0Bs5HBDhH0X5863.jpg","goods_id":1226,"goods_name":"网红简约透明健身摇摇杯","goods_price":"287.00","create_time":"2018.02.01","quantity":1,"sku_id":0,"headimg":"http://app.uyux.vip","nickname":"片片啊片片","spe_name":""}]
     * page : {"page":1,"pageTotal":1,"pageSize":10,"dataTotal":2}
     * status : 1
     * info : 返回成功！
     */

    private PageBean page;
    private int status;
    private String info;
    private List<DataBean> data;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class PageBean {
        /**
         * page : 1
         * pageTotal : 1
         * pageSize : 10
         * dataTotal : 2
         */

        private int page;
        private int pageTotal;
        private int pageSize;
        private int dataTotal;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPageTotal() {
            return pageTotal;
        }

        public void setPageTotal(int pageTotal) {
            this.pageTotal = pageTotal;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getDataTotal() {
            return dataTotal;
        }

        public void setDataTotal(int dataTotal) {
            this.dataTotal = dataTotal;
        }
    }

    public static class DataBean {
        /**
         * id : 504
         * uid : 1011113
         * img : http://app.uyux.vip/attachment/images/1604/2017/12/I3G434jXcj0Xz00m934XxxZeuiy4QU.jpg
         * goods_id : 1353
         * goods_name : 美国原装进口桂格燕麦
         * goods_price : 49.50
         * create_time : 2018.02.01
         * quantity : 1
         * sku_id : 0
         * headimg : http://app.uyux.vip
         * nickname : 片片啊片片
         * spe_name :
         */

        private int id;
        private int uid;
        private String img;
        private int goods_id;
        private String goods_name;
        private String goods_price;
        private String create_time;
        private int quantity;
        private int sku_id;
        private String headimg;
        private String nickname;
        private String spe_name;

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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getSku_id() {
            return sku_id;
        }

        public void setSku_id(int sku_id) {
            this.sku_id = sku_id;
        }

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

        public String getSpe_name() {
            return spe_name;
        }

        public void setSpe_name(String spe_name) {
            this.spe_name = spe_name;
        }
    }
}
