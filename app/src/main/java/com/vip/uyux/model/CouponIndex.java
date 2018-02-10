package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/25/025.
 *
 * @author ZhangJieBo
 */

public class CouponIndex {
    /**
     * data : [{"id":11,"uid":1013142,"name":"限可口可乐优选自营官方旗舰店下指定产品使用","money":50,"useState":1,"des":"限品类：限可口可乐优选自营官方旗舰店下指定产品使用","limit_money":"满51.00元可用","use_time":"2018.02.10-2018.02.17","is_send":1,"sendDes":"可赠送","btnDes":"立即使用","url":"http://app.uyux.vip/api/Article/info/type/couponsend","url_title":"优惠券赠送规则","share":{"title":"赠送给好友","des":"赠送后该券就不属于你的啦，你的好友领到该券会很开心的","shareImg":"http://app.uyux.vip/Uploads/logo300x300.png","shareTitle":"送你一张优惠券，新年快乐！","shareUrl":"http://app.uyux.vip/mobile/Coupon/send/id/11/uid/1013142","shareDes":"15871105320送了你一张50元优惠券！"}},{"id":10,"uid":1013142,"name":"限可口可乐优选自营官方旗舰店下指定产品使用","money":50,"useState":1,"des":"限品类：限可口可乐优选自营官方旗舰店下指定产品使用","limit_money":"满51.00元可用","use_time":"2018.02.10-2018.02.17","is_send":1,"sendDes":"可赠送","btnDes":"立即使用","url":"http://app.uyux.vip/api/Article/info/type/couponsend","url_title":"优惠券赠送规则","share":{"title":"赠送给好友","des":"赠送后该券就不属于你的啦，你的好友领到该券会很开心的","shareImg":"http://app.uyux.vip/Uploads/logo300x300.png","shareTitle":"送你一张优惠券，新年快乐！","shareUrl":"http://app.uyux.vip/mobile/Coupon/send/id/10/uid/1013142","shareDes":"15871105320送了你一张50元优惠券！"}},{"id":9,"uid":1013142,"name":"限可口可乐优选自营官方旗舰店下指定产品使用","money":50,"useState":1,"des":"限品类：限可口可乐优选自营官方旗舰店下指定产品使用","limit_money":"满51.00元可用","use_time":"2018.02.10-2018.02.17","is_send":1,"sendDes":"可赠送","btnDes":"立即使用","url":"http://app.uyux.vip/api/Article/info/type/couponsend","url_title":"优惠券赠送规则","share":{"title":"赠送给好友","des":"赠送后该券就不属于你的啦，你的好友领到该券会很开心的","shareImg":"http://app.uyux.vip/Uploads/logo300x300.png","shareTitle":"送你一张优惠券，新年快乐！","shareUrl":"http://app.uyux.vip/mobile/Coupon/send/id/9/uid/1013142","shareDes":"15871105320送了你一张50元优惠券！"}}]
     * page : {"page":1,"pageTotal":1,"pageSize":10,"dataTotal":3}
     * type : [{"n":"未使用","v":"1","act":1},{"n":"使用记录","v":"0","act":"0"},{"n":"已过期","v":"0","act":"0"}]
     * status : 1
     * info : 返回成功！
     */

    private PageBean page;
    private int status;
    private String info;
    private List<DataBean> data;
    private List<TypeBean> type;

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

    public List<TypeBean> getType() {
        return type;
    }

    public void setType(List<TypeBean> type) {
        this.type = type;
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
         * id : 11
         * uid : 1013142
         * name : 限可口可乐优选自营官方旗舰店下指定产品使用
         * money : 50
         * useState : 1
         * des : 限品类：限可口可乐优选自营官方旗舰店下指定产品使用
         * limit_money : 满51.00元可用
         * use_time : 2018.02.10-2018.02.17
         * is_send : 1
         * sendDes : 可赠送
         * btnDes : 立即使用
         * url : http://app.uyux.vip/api/Article/info/type/couponsend
         * url_title : 优惠券赠送规则
         * share : {"title":"赠送给好友","des":"赠送后该券就不属于你的啦，你的好友领到该券会很开心的","shareImg":"http://app.uyux.vip/Uploads/logo300x300.png","shareTitle":"送你一张优惠券，新年快乐！","shareUrl":"http://app.uyux.vip/mobile/Coupon/send/id/11/uid/1013142","shareDes":"15871105320送了你一张50元优惠券！"}
         */

        private int id;
        private int uid;
        private String name;
        private String money;
        private int useState;
        private String des;
        private String limit_money;
        private String use_time;
        private int is_send;
        private String sendDes;
        private String btnDes;
        private String url;
        private String url_title;
        private ShareBean share;
        private boolean isZhanKai;


        public boolean isZhanKai() {
            return isZhanKai;
        }

        public void setZhanKai(boolean zhanKai) {
            isZhanKai = zhanKai;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getUseState() {
            return useState;
        }

        public void setUseState(int useState) {
            this.useState = useState;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getLimit_money() {
            return limit_money;
        }

        public void setLimit_money(String limit_money) {
            this.limit_money = limit_money;
        }

        public String getUse_time() {
            return use_time;
        }

        public void setUse_time(String use_time) {
            this.use_time = use_time;
        }

        public int getIs_send() {
            return is_send;
        }

        public void setIs_send(int is_send) {
            this.is_send = is_send;
        }

        public String getSendDes() {
            return sendDes;
        }

        public void setSendDes(String sendDes) {
            this.sendDes = sendDes;
        }

        public String getBtnDes() {
            return btnDes;
        }

        public void setBtnDes(String btnDes) {
            this.btnDes = btnDes;
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

        public ShareBean getShare() {
            return share;
        }

        public void setShare(ShareBean share) {
            this.share = share;
        }

        public static class ShareBean {
            /**
             * title : 赠送给好友
             * des : 赠送后该券就不属于你的啦，你的好友领到该券会很开心的
             * shareImg : http://app.uyux.vip/Uploads/logo300x300.png
             * shareTitle : 送你一张优惠券，新年快乐！
             * shareUrl : http://app.uyux.vip/mobile/Coupon/send/id/11/uid/1013142
             * shareDes : 15871105320送了你一张50元优惠券！
             */

            private String title;
            private String des;
            private String shareImg;
            private String shareTitle;
            private String shareUrl;
            private String shareDes;

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

            public String getShareImg() {
                return shareImg;
            }

            public void setShareImg(String shareImg) {
                this.shareImg = shareImg;
            }

            public String getShareTitle() {
                return shareTitle;
            }

            public void setShareTitle(String shareTitle) {
                this.shareTitle = shareTitle;
            }

            public String getShareUrl() {
                return shareUrl;
            }

            public void setShareUrl(String shareUrl) {
                this.shareUrl = shareUrl;
            }

            public String getShareDes() {
                return shareDes;
            }

            public void setShareDes(String shareDes) {
                this.shareDes = shareDes;
            }
        }
    }

    public static class TypeBean {
        /**
         * n : 未使用
         * v : 1
         * act : 1
         */

        private String n;
        private String v;
        private int act;

        public String getN() {
            return n;
        }

        public void setN(String n) {
            this.n = n;
        }

        public String getV() {
            return v;
        }

        public void setV(String v) {
            this.v = v;
        }

        public int getAct() {
            return act;
        }

        public void setAct(int act) {
            this.act = act;
        }
    }
}
