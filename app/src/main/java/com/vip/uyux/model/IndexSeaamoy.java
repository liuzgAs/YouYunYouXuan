package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/3/5/005.
 *
 * @author ZhangJieBo
 */

public class IndexSeaamoy {
    /**
     * banner : [{"img":"http://api.uyux.vip/Uploads/attachment/20180119/5faa856af1159f4ca38cafa44f44db9e.png","code":"","item_id":0,"url":""},{"img":"http://api.uyux.vip/Uploads/attachment/20180119/5faa856af1159f4ca38cafa44f44db9e.png","code":"","item_id":0,"url":""}]
     * data : [{"id":691,"img":"http://www.uyux.vip/attachment/images/1604/2017/12/kPr7uYyu4PFOFGPB4D86FbPX2pYR8x.jpg","title":"越南jido鸡蛋面包干","des":"严格筛选的好食材才会有好面包干","price":"￥5.80","oldPrice":"￥0.00","vipDes":"五星会员省8元","type":1,"saleNum":5869,"taxPackage":1},{"id":1672,"img":"http://www.uyux.vip/attachment/images/1604/2018/01/p8Dc88G2G86gk6d780PP7b3JkB28g2.jpg","title":"燕知元冰糖即食碗燕 碗装礼盒","des":"极净纯炖 即享美丽","price":"￥398.00","oldPrice":"￥899.00","vipDes":"五星会员省8元","type":1,"saleNum":5869,"taxPackage":1}]
     * page : {"page":1,"pageTotal":0,"pageSize":10,"dataTotal":0}
     */
    private int status;
    private String info;
    private PageBean page;
    private List<AdvsBean> banner;
    private List<DataBean> data;

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

    public List<AdvsBean> getBanner() {
        return banner;
    }

    public void setBanner(List<AdvsBean> banner) {
        this.banner = banner;
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
         * pageTotal : 0
         * pageSize : 10
         * dataTotal : 0
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
         * id : 691
         * img : http://www.uyux.vip/attachment/images/1604/2017/12/kPr7uYyu4PFOFGPB4D86FbPX2pYR8x.jpg
         * title : 越南jido鸡蛋面包干
         * des : 严格筛选的好食材才会有好面包干
         * price : ￥5.80
         * oldPrice : ￥0.00
         * vipDes : 五星会员省8元
         * type : 1
         * saleNum : 5869
         * taxPackage : 1
         */

        private int id;
        private String img;
        private String title;
        private String des;
        private String price;
        private String oldPrice;
        private String vipDes;
        private int type;
        private int saleNum;
        private int taxPackage;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getOldPrice() {
            return oldPrice;
        }

        public void setOldPrice(String oldPrice) {
            this.oldPrice = oldPrice;
        }

        public String getVipDes() {
            return vipDes;
        }

        public void setVipDes(String vipDes) {
            this.vipDes = vipDes;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }

        public int getTaxPackage() {
            return taxPackage;
        }

        public void setTaxPackage(int taxPackage) {
            this.taxPackage = taxPackage;
        }
    }
}
