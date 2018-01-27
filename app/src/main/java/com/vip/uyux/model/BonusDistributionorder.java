package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/27/027.
 *
 * @author ZhangJieBo
 */

public class BonusDistributionorder {

    /**
     * data : [{"id":1,"order_no":"15252253325[一级]","name":"周公","headimg":"","nickname":"liuming1235","grade_name":"大师","estimate_money":22,"status":10,"order_goods":[{"gid":1,"goods_img":"","goods_title":"一品轩皇家有机食用调和油","create_time":"2018-08-31 14:59:12"},{"gid":2,"goods_img":"","goods_title":"一品轩皇家有机食用调和油","create_time":"2018-08-31 14:59:12"}]},{"id":1,"order_no":"15252253325[一级]","name":"凯凯","headimg":"","nickname":"liuming1235","grade_name":"大师","estimate_money":22,"status":10,"order_goods":[{"gid":1,"goods_img":"","goods_title":"一品轩皇家有机食用调和油","create_time":"2018-08-31 14:59:12"},{"gid":2,"goods_img":"","goods_title":"一品轩皇家有机食用调和油","create_time":"2018-08-31 14:59:12"}]},{"id":1,"order_no":"15252253325[一级]","name":"巴萨","headimg":"","nickname":"liuming1235","grade_name":"大师","estimate_money":22,"status":20,"order_goods":[{"gid":1,"goods_img":"","goods_title":"一品轩皇家有机食用调和油","create_time":"2018-08-31 14:59:12"},{"gid":2,"goods_img":"","goods_title":"一品轩皇家有机食用调和油","create_time":"2018-08-31 14:59:12"}]},{"id":1,"order_no":"15252253325[一级]","name":"周大及","headimg":"","nickname":"liuming1235","grade_name":"大师","estimate_money":22,"status":30,"order_goods":[{"gid":1,"goods_img":"","goods_title":"一品轩皇家有机食用调和油","create_time":"2018-08-31 14:59:12"},{"gid":2,"goods_img":"","goods_title":"一品轩皇家有机食用调和油","create_time":"2018-08-31 14:59:12"}]},{"id":1,"order_no":"15252253325[一级]","name":"张铪","headimg":"","nickname":"liuming1235","grade_name":"大师","estimate_money":22,"status":40,"order_goods":[{"gid":1,"goods_img":"","goods_title":"一品轩皇家有机食用调和油","create_time":"2018-08-31 14:59:12"},{"gid":2,"goods_img":"","goods_title":"一品轩皇家有机食用调和油","create_time":"2018-08-31 14:59:12"}]},{"id":1,"order_no":"15252253325[一级]","name":"孙怡","headimg":"","nickname":"liuming1235","grade_name":"大师","estimate_money":22,"status":50,"order_goods":[{"gid":1,"goods_img":"","goods_title":"一品轩皇家有机食用调和油","create_time":"2018-08-31 14:59:12"},{"gid":2,"goods_img":"","goods_title":"一品轩皇家有机食用调和油","create_time":"2018-08-31 14:59:12"}]}]
     * page : {"page":1,"pageTotal":1,"pageSize":15,"dataTotal":6}
     * status : 1
     * info : 获取成功
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
         * pageSize : 15
         * dataTotal : 6
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
         * id : 1
         * order_no : 15252253325[一级]
         * name : 周公
         * headimg :
         * nickname : liuming1235
         * grade_name : 大师
         * estimate_money : 22
         * status : 10
         * order_goods : [{"gid":1,"goods_img":"","goods_title":"一品轩皇家有机食用调和油","create_time":"2018-08-31 14:59:12"},{"gid":2,"goods_img":"","goods_title":"一品轩皇家有机食用调和油","create_time":"2018-08-31 14:59:12"}]
         */

        private int id;
        private String order_no;
        private String name;
        private String headimg;
        private String nickname;
        private String grade_name;
        private int estimate_money;
        private int status;
        private String status_v;
        private List<OrderGoodsBean> order_goods;

        public String getStatus_v() {
            return status_v;
        }

        public void setStatus_v(String status_v) {
            this.status_v = status_v;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getGrade_name() {
            return grade_name;
        }

        public void setGrade_name(String grade_name) {
            this.grade_name = grade_name;
        }

        public int getEstimate_money() {
            return estimate_money;
        }

        public void setEstimate_money(int estimate_money) {
            this.estimate_money = estimate_money;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<OrderGoodsBean> getOrder_goods() {
            return order_goods;
        }

        public void setOrder_goods(List<OrderGoodsBean> order_goods) {
            this.order_goods = order_goods;
        }

        public static class OrderGoodsBean {
            /**
             * gid : 1
             * goods_img :
             * goods_title : 一品轩皇家有机食用调和油
             * create_time : 2018-08-31 14:59:12
             */

            private int gid;
            private String goods_img;
            private String goods_title;
            private String create_time;

            public int getGid() {
                return gid;
            }

            public void setGid(int gid) {
                this.gid = gid;
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

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }
    }
}
