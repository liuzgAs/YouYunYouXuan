package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/21/021.
 *
 * @author ZhangJieBo
 */

public class GoodsInfo {

    /**
     * banner : ["http://www.uyux.vip/attachment/images/1604/2018/01/BaKAlOoxZAlXK4aXx4zgJXALl24jjJ.jpg","http://www.uyux.vip/attachment/images/1604/2018/01/Eee7RQ88eC97g9897Cs7y89gqJyyUj.jpg"]
     * data : {"id":1697,"title":"猴子系列茶宠","des":"优质紫砂泥料 造型精致","price":"205.00","vipPrice":197,"oldPrice":"0.00","vipDes":"V1会员价","countdown":0,"countdownDes":"距离抢购还剩","saleNum":"5588","stockNum":198,"shareMoney":"分享赚￥76.8","scoreDes":"最高可获得20积分","imgs":[{"img":"http://www.uyux.vip/attachment/images/1604/2017/12/y3e5407Hva12Y242E245117VpaZ0E9.jpg","w":563,"h":260},{"img":"http://www.uyux.vip/attachment/images/1604/2018/01/qKvBkNEGeZaKQ93gZKV01eg9UGae98.jpg","w":784,"h":6211},{"img":"http://www.uyux.vip/attachment/images/1604/2018/01/MAqnvNhvPVp9NJpMC9QLSsmPacva53.jpg","w":804,"h":6644},{"img":"http://www.uyux.vip/attachment/images/1604/2018/01/U42U40uENII1NtpLUleVipIp2duhnE.jpg","w":804,"h":7762},{"img":"http://www.uyux.vip/attachment/images/1604/2018/01/gG1e1eiJESIj1qE8rkk118818r18Ir.jpg","w":804,"h":6023},{"img":"http://www.uyux.vip/attachment/images/1604/2017/12/YV4884R448Uku6OKFGR4Kg44964r4W.jpg","w":900,"h":470}],"imgs2":[{"img":"http://www.uyux.vip/attachment/images/1604/2017/12/y3e5407Hva12Y242E245117VpaZ0E9.jpg","w":563,"h":260},{"img":"http://www.uyux.vip/attachment/images/1604/2018/01/qKvBkNEGeZaKQ93gZKV01eg9UGae98.jpg","w":784,"h":6211},{"img":"http://www.uyux.vip/attachment/images/1604/2018/01/MAqnvNhvPVp9NJpMC9QLSsmPacva53.jpg","w":804,"h":6644},{"img":"http://www.uyux.vip/attachment/images/1604/2018/01/U42U40uENII1NtpLUleVipIp2duhnE.jpg","w":804,"h":7762},{"img":"http://www.uyux.vip/attachment/images/1604/2018/01/gG1e1eiJESIj1qE8rkk118818r18Ir.jpg","w":804,"h":6023},{"img":"http://www.uyux.vip/attachment/images/1604/2017/12/YV4884R448Uku6OKFGR4Kg44964r4W.jpg","w":900,"h":470}]}
     * status : 1
     * info : 返回成功！
     */

    private DataBean data;
    private int status;
    private String info;
    private List<String> banner;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public List<String> getBanner() {
        return banner;
    }

    public void setBanner(List<String> banner) {
        this.banner = banner;
    }

    public static class DataBean {
        /**
         * id : 1697
         * title : 猴子系列茶宠
         * des : 优质紫砂泥料 造型精致
         * price : 205.00
         * vipPrice : 197
         * oldPrice : 0.00
         * vipDes : V1会员价
         * countdown : 0
         * countdownDes : 距离抢购还剩
         * saleNum : 5588
         * stockNum : 198
         * shareMoney : 分享赚￥76.8
         * scoreDes : 最高可获得20积分
         * imgs : [{"img":"http://www.uyux.vip/attachment/images/1604/2017/12/y3e5407Hva12Y242E245117VpaZ0E9.jpg","w":563,"h":260},{"img":"http://www.uyux.vip/attachment/images/1604/2018/01/qKvBkNEGeZaKQ93gZKV01eg9UGae98.jpg","w":784,"h":6211},{"img":"http://www.uyux.vip/attachment/images/1604/2018/01/MAqnvNhvPVp9NJpMC9QLSsmPacva53.jpg","w":804,"h":6644},{"img":"http://www.uyux.vip/attachment/images/1604/2018/01/U42U40uENII1NtpLUleVipIp2duhnE.jpg","w":804,"h":7762},{"img":"http://www.uyux.vip/attachment/images/1604/2018/01/gG1e1eiJESIj1qE8rkk118818r18Ir.jpg","w":804,"h":6023},{"img":"http://www.uyux.vip/attachment/images/1604/2017/12/YV4884R448Uku6OKFGR4Kg44964r4W.jpg","w":900,"h":470}]
         * imgs2 : [{"img":"http://www.uyux.vip/attachment/images/1604/2017/12/y3e5407Hva12Y242E245117VpaZ0E9.jpg","w":563,"h":260},{"img":"http://www.uyux.vip/attachment/images/1604/2018/01/qKvBkNEGeZaKQ93gZKV01eg9UGae98.jpg","w":784,"h":6211},{"img":"http://www.uyux.vip/attachment/images/1604/2018/01/MAqnvNhvPVp9NJpMC9QLSsmPacva53.jpg","w":804,"h":6644},{"img":"http://www.uyux.vip/attachment/images/1604/2018/01/U42U40uENII1NtpLUleVipIp2duhnE.jpg","w":804,"h":7762},{"img":"http://www.uyux.vip/attachment/images/1604/2018/01/gG1e1eiJESIj1qE8rkk118818r18Ir.jpg","w":804,"h":6023},{"img":"http://www.uyux.vip/attachment/images/1604/2017/12/YV4884R448Uku6OKFGR4Kg44964r4W.jpg","w":900,"h":470}]
         */

        private int id;
        private String title;
        private String des;
        private String price;
        private int vipPrice;
        private String oldPrice;
        private String vipDes;
        private int countdown;
        private String countdownDes;
        private String saleNum;
        private int stockNum;
        private String shareMoney;
        private String scoreDes;
        private List<ImgsBean> imgs;
        private List<Imgs2Bean> imgs2;

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

        public int getVipPrice() {
            return vipPrice;
        }

        public void setVipPrice(int vipPrice) {
            this.vipPrice = vipPrice;
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

        public int getCountdown() {
            return countdown;
        }

        public void setCountdown(int countdown) {
            this.countdown = countdown;
        }

        public String getCountdownDes() {
            return countdownDes;
        }

        public void setCountdownDes(String countdownDes) {
            this.countdownDes = countdownDes;
        }

        public String getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(String saleNum) {
            this.saleNum = saleNum;
        }

        public int getStockNum() {
            return stockNum;
        }

        public void setStockNum(int stockNum) {
            this.stockNum = stockNum;
        }

        public String getShareMoney() {
            return shareMoney;
        }

        public void setShareMoney(String shareMoney) {
            this.shareMoney = shareMoney;
        }

        public String getScoreDes() {
            return scoreDes;
        }

        public void setScoreDes(String scoreDes) {
            this.scoreDes = scoreDes;
        }

        public List<ImgsBean> getImgs() {
            return imgs;
        }

        public void setImgs(List<ImgsBean> imgs) {
            this.imgs = imgs;
        }

        public List<Imgs2Bean> getImgs2() {
            return imgs2;
        }

        public void setImgs2(List<Imgs2Bean> imgs2) {
            this.imgs2 = imgs2;
        }

        public static class ImgsBean {
            /**
             * img : http://www.uyux.vip/attachment/images/1604/2017/12/y3e5407Hva12Y242E245117VpaZ0E9.jpg
             * w : 563
             * h : 260
             */

            private String img;
            private int w;
            private int h;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getW() {
                return w;
            }

            public void setW(int w) {
                this.w = w;
            }

            public int getH() {
                return h;
            }

            public void setH(int h) {
                this.h = h;
            }
        }

        public static class Imgs2Bean {
            /**
             * img : http://www.uyux.vip/attachment/images/1604/2017/12/y3e5407Hva12Y242E245117VpaZ0E9.jpg
             * w : 563
             * h : 260
             */

            private String img;
            private int w;
            private int h;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getW() {
                return w;
            }

            public void setW(int w) {
                this.w = w;
            }

            public int getH() {
                return h;
            }

            public void setH(int h) {
                this.h = h;
            }
        }
    }
}
