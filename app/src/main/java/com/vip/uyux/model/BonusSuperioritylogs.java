package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/2/10/010.
 *
 * @author ZhangJieBo
 */

public class BonusSuperioritylogs {
    /**
     * data : [{"id":4,"state":1,"name":"aaa","price":"44","img":"http://app.uyux.vip/Uploads/attachment/20180123/1_1516701675_1.png","state_des":"审核通过,aasddd"}]
     * page : {"page":1,"pageTotal":1,"pageSize":10,"dataTotal":1}
     * status : 1
     * info : 操作成功！
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
         * id : 4
         * state : 1
         * name : aaa
         * price : 44
         * img : http://app.uyux.vip/Uploads/attachment/20180123/1_1516701675_1.png
         * state_des : 审核通过,aasddd
         */

        private int id;
        private int state;
        private String name;
        private String price;
        private String img;
        private String state_des;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getState_des() {
            return state_des;
        }

        public void setState_des(String state_des) {
            this.state_des = state_des;
        }
    }
}
