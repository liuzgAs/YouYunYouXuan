package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/2/7/007.
 *
 * @author ZhangJieBo
 */

public class AftersLogs {
    /**
     * data : [{"id":18,"uid":1011113,"create_time":"2018.02.07","state":10,"goods_id":1353,"order_sn":"申请中","sku_id":0,"goods_name":"美国原装进口桂格燕麦","img":"http://app.uyux.vip/attachment/images/1604/2017/12/I3G434jXcj0Xz00m934XxxZeuiy4QU.jpg","quantity":1,"tag":3,"headimg":"http://app.uyux.vip/attachment/head/20180201/1011113_1517455406_1.png","is_view":1,"des":"记录申请中，等待工人人员审核！","nickname":"真实","spe_name":""},{"id":17,"uid":1011113,"create_time":"2018.02.07","state":10,"goods_id":1353,"order_sn":"申请中","sku_id":0,"goods_name":"美国原装进口桂格燕麦","img":"http://app.uyux.vip/attachment/images/1604/2017/12/I3G434jXcj0Xz00m934XxxZeuiy4QU.jpg","quantity":1,"tag":4,"headimg":"http://app.uyux.vip/attachment/head/20180201/1011113_1517455406_1.png","is_view":1,"des":"记录申请中，等待工人人员审核！","nickname":"真实","spe_name":""},{"id":16,"uid":1011113,"create_time":"2018.02.07","state":10,"goods_id":1353,"order_sn":"申请中","sku_id":0,"goods_name":"美国原装进口桂格燕麦","img":"http://app.uyux.vip/attachment/images/1604/2017/12/I3G434jXcj0Xz00m934XxxZeuiy4QU.jpg","quantity":1,"tag":4,"headimg":"http://app.uyux.vip/attachment/head/20180201/1011113_1517455406_1.png","is_view":1,"des":"记录申请中，等待工人人员审核！","nickname":"真实","spe_name":""},{"id":15,"uid":1011113,"create_time":"2018.02.07","state":10,"goods_id":1353,"order_sn":"申请中","sku_id":0,"goods_name":"美国原装进口桂格燕麦","img":"http://app.uyux.vip/attachment/images/1604/2017/12/I3G434jXcj0Xz00m934XxxZeuiy4QU.jpg","quantity":1,"tag":4,"headimg":"http://app.uyux.vip/attachment/head/20180201/1011113_1517455406_1.png","is_view":1,"des":"记录申请中，等待工人人员审核！","nickname":"真实","spe_name":""},{"id":14,"uid":1011113,"create_time":"2018.02.07","state":10,"goods_id":1353,"order_sn":"申请中","sku_id":0,"goods_name":"美国原装进口桂格燕麦","img":"http://app.uyux.vip/attachment/images/1604/2017/12/I3G434jXcj0Xz00m934XxxZeuiy4QU.jpg","quantity":1,"tag":4,"headimg":"http://app.uyux.vip/attachment/head/20180201/1011113_1517455406_1.png","is_view":1,"des":"记录申请中，等待工人人员审核！","nickname":"真实","spe_name":""},{"id":13,"uid":1011113,"create_time":"2018.02.06","state":10,"goods_id":1353,"order_sn":"申请中","sku_id":0,"goods_name":"美国原装进口桂格燕麦","img":"http://app.uyux.vip/attachment/images/1604/2017/12/I3G434jXcj0Xz00m934XxxZeuiy4QU.jpg","quantity":1,"tag":1,"headimg":"http://app.uyux.vip/attachment/head/20180201/1011113_1517455406_1.png","is_view":1,"des":"记录申请中，等待工人人员审核！","nickname":"真实","spe_name":""},{"id":12,"uid":1011113,"create_time":"2018.02.06","state":10,"goods_id":1353,"order_sn":"申请中","sku_id":0,"goods_name":"美国原装进口桂格燕麦","img":"http://app.uyux.vip/attachment/images/1604/2017/12/I3G434jXcj0Xz00m934XxxZeuiy4QU.jpg","quantity":1,"tag":4,"headimg":"http://app.uyux.vip/attachment/head/20180201/1011113_1517455406_1.png","is_view":1,"des":"记录申请中，等待工人人员审核！","nickname":"真实","spe_name":""}]
     * page : {"page":1,"pageTotal":1,"pageSize":10,"dataTotal":7}
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
         * dataTotal : 7
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
         * id : 18
         * uid : 1011113
         * create_time : 2018.02.07
         * state : 10
         * goods_id : 1353
         * order_sn : 申请中
         * sku_id : 0
         * goods_name : 美国原装进口桂格燕麦
         * img : http://app.uyux.vip/attachment/images/1604/2017/12/I3G434jXcj0Xz00m934XxxZeuiy4QU.jpg
         * quantity : 1
         * tag : 3
         * headimg : http://app.uyux.vip/attachment/head/20180201/1011113_1517455406_1.png
         * is_view : 1
         * des : 记录申请中，等待工人人员审核！
         * nickname : 真实
         * spe_name :
         */

        private int id;
        private int uid;
        private String create_time;
        private int state;
        private int goods_id;
        private String order_sn;
        private int sku_id;
        private String goods_name;
        private String img;
        private int quantity;
        private int tag;
        private String headimg;
        private int is_view;
        private String des;
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

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public int getSku_id() {
            return sku_id;
        }

        public void setSku_id(int sku_id) {
            this.sku_id = sku_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getTag() {
            return tag;
        }

        public void setTag(int tag) {
            this.tag = tag;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public int getIs_view() {
            return is_view;
        }

        public void setIs_view(int is_view) {
            this.is_view = is_view;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
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
