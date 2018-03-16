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
    private List<YouHuiQuan> data;
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

    public List<YouHuiQuan> getData() {
        return data;
    }

    public void setData(List<YouHuiQuan> data) {
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
