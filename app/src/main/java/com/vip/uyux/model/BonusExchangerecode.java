package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/27/027.
 *
 * @author ZhangJieBo
 */

public class BonusExchangerecode {

    /**
     * data : [{"goods_id":1772,"quantity":1,"goods_score":"100","img":"http://app.uyux.vip/attachment/images/20180128/6429276d15105828008b84183a972c1b_300x300.png","goods_name":"测试专增11","pay_time":"2018.02.07 15:32:33"},{"goods_id":1744,"quantity":1,"goods_score":"0","img":"http://app.uyux.vip/attachment/images/vslai_shop/1604/2018/01/Brw1IOIoHoIVIOY4Buw44945oSGs14_300x300.jpg","goods_name":"法兰绒地垫","pay_time":"2018.02.07 14:45:48"}]
     * ts : 2018.02.07-2018.02.07
     * page : {"page":1,"pageTotal":1,"pageSize":10,"dataTotal":2}
     * status : 1
     * info : 返回成功！
     */

    private String ts;
    private PageBean page;
    private int status;
    private String info;
    private List<DataBean> data;

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
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
         * goods_id : 1772
         * quantity : 1
         * goods_score : 100
         * img : http://app.uyux.vip/attachment/images/20180128/6429276d15105828008b84183a972c1b_300x300.png
         * goods_name : 测试专增11
         * pay_time : 2018.02.07 15:32:33
         */

        private int goods_id;
        private int quantity;
        private String goods_score;
        private String img;
        private String goods_name;
        private String pay_time;

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getGoods_score() {
            return goods_score;
        }

        public void setGoods_score(String goods_score) {
            this.goods_score = goods_score;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }
    }
}
