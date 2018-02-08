package com.vip.uyux.model;

public class GoodBean {
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
        private int viewType;

        public int getViewType() {
            return viewType;
        }

        public void setViewType(int viewType) {
            this.viewType = viewType;
        }

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
    }