package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/27/027.
 *
 * @author ZhangJieBo
 */

public class MassageWuliu {
    /**
     * data : [{"id":8,"title":"订单已发货","goods_name":"便捷吹气旅行枕","goods_img":"http://app.uyux.vip/attachment/http://www.uyux.vip/attachment/images/1604/2017/12/zeaileDPEZld8iPgoLvpq7eOQ81c1P.jpg","express_no":"","state":30,"create_time":"2018-01-27"}]
     * page : {"page":1,"pageTotal":1,"pageSize":15,"dataTotal":1}
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
         * dataTotal : 1
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
         * id : 8
         * title : 订单已发货
         * goods_name : 便捷吹气旅行枕
         * goods_img : http://app.uyux.vip/attachment/http://www.uyux.vip/attachment/images/1604/2017/12/zeaileDPEZld8iPgoLvpq7eOQ81c1P.jpg
         * express_no :
         * state : 30
         * create_time : 2018-01-27
         */

        private int id;
        private String title;
        private String goods_name;
        private String goods_img;
        private String express_no;
        private int state;
        private String create_time;

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

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }

        public String getExpress_no() {
            return express_no;
        }

        public void setExpress_no(String express_no) {
            this.express_no = express_no;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
