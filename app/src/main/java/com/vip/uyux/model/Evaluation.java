package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/2/12/012.
 *
 * @author ZhangJieBo
 */

public class Evaluation {
    /**
     * is_des : 0
     * des : 已买了0件商品快去写用后感受吧！
     * data : [{"id":3,"img":"http://app.uyux.vip/attachment/head/20180212/1013142_1518400367_1.png","create_time":"2018.02.12 09:52:47","title":"测一测"},{"id":2,"img":"http://app.uyux.vip/attachment/head/20180212/1013142_1518399422_1.png","create_time":"2018.02.12 09:37:20","title":"测一测"},{"id":1,"img":"http://app.uyux.vip/attachment/head/20180212/1013142_1518399422_1.png","create_time":"2018.02.12 09:37:02","title":"测一测"}]
     * page : {"page":1,"pageTotal":1,"pageSize":10,"dataTotal":3}
     * status : 1
     * info : 返回成功！
     */

    private int is_des;
    private String des;
    private PageBean page;
    private int status;
    private String info;
    private List<DataBean> data;

    public int getIs_des() {
        return is_des;
    }

    public void setIs_des(int is_des) {
        this.is_des = is_des;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

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
         * dataTotal : 3
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
         * id : 3
         * img : http://app.uyux.vip/attachment/head/20180212/1013142_1518400367_1.png
         * create_time : 2018.02.12 09:52:47
         * title : 测一测
         */

        private int id;
        private String img;
        private String create_time;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
