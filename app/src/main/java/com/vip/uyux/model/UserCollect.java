package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/24/024.
 *
 * @author ZhangJieBo
 */

public class UserCollect {
    /**
     * type : [{"n":"商品","v":"0","act":1},{"n":"测评","v":"1","act":"0"}]
     * data : [{"id":8,"goods_id":1226,"title":"网红简约透明健身摇摇杯","img":"http://app.uyux.vip/attachment/images/1604/2017/12/kB87BH7H73DHD8H0Bs5HBDhH0X5863.jpg","type":1},{"id":7,"goods_id":1227,"title":"特别钻石版马卡龙充电电暖宝","img":"http://app.uyux.vip/attachment/images/1604/2017/12/O10QT864Ttz31T30vT8zm3o5vNCtOo.jpg","type":1},{"id":6,"goods_id":1228,"title":"USB桌面喷雾空气加湿器","img":"http://app.uyux.vip/attachment/images/vslai_shop/1604/2017/12/IAM4UeMe8zx8maeyaC3kmAmtMMqyKJ.jpg","type":1},{"id":5,"goods_id":1229,"title":"萌宠二代粉色MEMOBIRD咕咕机G2热敏打印机迷你WIFI手机标签照片","img":"http://app.uyux.vip/attachment/images/vslai_shop/1604/2017/12/SF1MAu7BMj7p7fPMXfPmZ3J1UeMf5F.jpg","type":1},{"id":4,"goods_id":1230,"title":"复古丹麦陶瓷餐具杯碗盘","img":"http://app.uyux.vip/attachment/images/vslai_shop/1604/2017/12/vx3h5vvG9vxxpVJghuyI3VnxNJUvUU.jpg","type":1},{"id":3,"goods_id":1231,"title":"床头鲸鱼海豚闹铃","img":"http://app.uyux.vip/attachment/images/vslai_shop/1604/2017/12/kkFXmDNymYf2DNzi9XMKsisCCX1niI.jpg","type":1},{"id":2,"goods_id":1233,"title":"MiNi CooLi 创意礼品迷你冷空调风扇 送老婆闺蜜 新奇生日礼物","img":"http://app.uyux.vip/attachment/images/vslai_shop/1604/2017/12/F03RiwJjCvhiMMw2FJMVvrtI7i7lFl.jpg","type":1},{"id":1,"goods_id":1123,"title":"有机小薏米","img":"http://app.uyux.vip/attachment/images/1604/2017/12/TLl0pMGbPm8rZHQXojzeYCHhj9hBll.jpg","type":1}]
     * page : {"page":1,"pageTotal":1,"pageSize":10,"dataTotal":8}
     * status : 1
     * info : 返回成功！
     */

    private PageBean page;
    private int status;
    private String info;
    private List<TypeBean> type;
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

    public List<TypeBean> getType() {
        return type;
    }

    public void setType(List<TypeBean> type) {
        this.type = type;
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
         * dataTotal : 8
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
         * n : 商品
         * v : 0
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

    public static class DataBean {
        /**
         * id : 8
         * goods_id : 1226
         * title : 网红简约透明健身摇摇杯
         * img : http://app.uyux.vip/attachment/images/1604/2017/12/kB87BH7H73DHD8H0Bs5HBDhH0X5863.jpg
         * type : 1
         */

        private int id;
        private int goods_id;
        private String title;
        private String img;
        private int type;
        private boolean isBianJi;
        private boolean isSelect;

        public boolean isBianJi() {
            return isBianJi;
        }

        public void setBianJi(boolean bianJi) {
            isBianJi = bianJi;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
