package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/3/24/024.
 *
 * @author ZhangJieBo
 */

public class MassageMsg {

    /**
     * data : [{"id":64,"title":"test11111","create_time":"2018-03-10","des":"","url":"","url_title":"","img":"","express_no":""},{"id":63,"title":"test11111","create_time":"2018-03-10","des":"","url":"","url_title":"","img":"","express_no":""},{"id":56,"title":"订单已发货","create_time":"2018-03-10","des":"您的订单已发货订单已发货","url":"","url_title":"","img":"","express_no":""},{"id":49,"title":"订单已发货","create_time":"2018-03-10","des":"您的订单已发货订单已发货","url":"","url_title":"","img":"","express_no":""},{"id":47,"title":"因条件不符，卖家已驳回您的申请","create_time":"2018-03-10","des":"您申请的退款 因条件不符，卖家已驳回您的申请","url":"","url_title":"","img":"","express_no":""},{"id":45,"title":"订单已发货","create_time":"2018-03-10","des":"您的订单已发货订单已发货","url":"","url_title":"","img":"","express_no":""}]
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
         * id : 64
         * title : test11111
         * create_time : 2018-03-10
         * des :
         * url :
         * url_title :
         * img :
         * express_no :
         */

        private int id;
        private String title;
        private String create_time;
        private String des;
        private String url;
        private String url_title;
        private String img;
        private String express_no;

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

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl_title() {
            return url_title;
        }

        public void setUrl_title(String url_title) {
            this.url_title = url_title;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getExpress_no() {
            return express_no;
        }

        public void setExpress_no(String express_no) {
            this.express_no = express_no;
        }
    }
}
