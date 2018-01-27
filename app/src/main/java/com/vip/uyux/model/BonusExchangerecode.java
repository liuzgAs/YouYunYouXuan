package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/27/027.
 *
 * @author ZhangJieBo
 */

public class BonusExchangerecode {

    /**
     * status : 1
     * info : 获取成功
     * page : {"page":1,"pageTotal":1,"pageSize":15,"dataTotal":3}
     * product_integral : [{"id":1,"goods_title":"一品轩皇家有机食用调和油","integral":"78","img":"","num":"2","create_time":"2018-01-25 16:27:20"},{"id":1,"goods_title":"一品轩皇家有机食用调和油","integral":"88","img":"","num":"2","create_time":"2018-01-25 16:10:40"},{"id":1,"goods_title":"一品轩皇家有机食用调和油","integral":"98","img":"","num":"1","create_time":"2018-01-25 02:34:00"}]
     */

    private int status;
    private String info;
    private PageBean page;
    private List<ProductIntegralBean> product_integral;

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

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public List<ProductIntegralBean> getProduct_integral() {
        return product_integral;
    }

    public void setProduct_integral(List<ProductIntegralBean> product_integral) {
        this.product_integral = product_integral;
    }

    public static class PageBean {
        /**
         * page : 1
         * pageTotal : 1
         * pageSize : 15
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

    public static class ProductIntegralBean {
        /**
         * id : 1
         * goods_title : 一品轩皇家有机食用调和油
         * integral : 78
         * img :
         * num : 2
         * create_time : 2018-01-25 16:27:20
         */

        private int id;
        private String goods_title;
        private String integral;
        private String img;
        private String num;
        private String create_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGoods_title() {
            return goods_title;
        }

        public void setGoods_title(String goods_title) {
            this.goods_title = goods_title;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
