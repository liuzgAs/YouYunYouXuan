package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/26/026.
 *
 * @author ZhangJieBo
 */

public class CustomerGetintegralshop {

    /**
     * page : {"page":1,"pageTotal":1,"pageSize":15,"dataTotal":4}
     * status : 1
     * info : 获取成功
     * top_img :
     * my_integral : 67889
     * exchange_recode : 6
     * data : [{"id":683,"title":"会员","thumb":"http://api.uyux.vipimages/1604/2017/09/QLItWBhBL8LDt3iIN9CpD8IdRq9WWw.jpg","productprice":"90.00","price":"90.00","integral":2999,"unit":"分"},{"id":15,"title":"保税区直发 美国Palmer&#039;s帕玛氏去疤痕膏 去妊娠纹 100g","thumb":"http://api.uyux.vipimages/1604/2017/09/eW34ll9PtlV7n54l33lxctU5T32ETx.jpg","productprice":"90.00","price":"90.00","integral":2999,"unit":"分"},{"id":5,"title":"352 X80极速空气净化器 家用卧室除雾霾PM2.5办公室除烟尘","thumb":"http://api.uyux.vipimages/vslai_shop/1604/2017/09/ms2F02E5V5V7JYbBBj7dS0apz0ONj9.jpg","productprice":"0.00","price":"0.00","integral":2999,"unit":"分"},{"id":1,"title":"Haier/海尔GDZE6-1W家用烘干机滚筒式6公斤杀菌排气式壁挂干衣机","thumb":"http://api.uyux.vipimages/vslai_shop/1604/2017/09/ZO7wx02Xo6QqiioC8xVNCnN6IGv6Wo.jpg","productprice":"0.00","price":"0.00","integral":2999,"unit":"分"}]
     */

    private PageBean page;
    private int status;
    private String info;
    private String top_img;
    private int my_integral;
    private int exchange_recode;
    private List<DataBean> data;
    private List<AdvsBean> banner;

    public List<AdvsBean> getBanner() {
        return banner;
    }

    public void setBanner(List<AdvsBean> banner) {
        this.banner = banner;
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

    public String getTop_img() {
        return top_img;
    }

    public void setTop_img(String top_img) {
        this.top_img = top_img;
    }

    public int getMy_integral() {
        return my_integral;
    }

    public void setMy_integral(int my_integral) {
        this.my_integral = my_integral;
    }

    public int getExchange_recode() {
        return exchange_recode;
    }

    public void setExchange_recode(int exchange_recode) {
        this.exchange_recode = exchange_recode;
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
         * dataTotal : 4
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
         * id : 683
         * title : 会员
         * thumb : http://api.uyux.vipimages/1604/2017/09/QLItWBhBL8LDt3iIN9CpD8IdRq9WWw.jpg
         * productprice : 90.00
         * price : 90.00
         * integral : 2999
         * unit : 分
         */

        private int id;
        private String title;
        private String thumb;
        private String productprice;
        private String price;
        private int integral;
        private String unit;

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

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getProductprice() {
            return productprice;
        }

        public void setProductprice(String productprice) {
            this.productprice = productprice;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
}
