package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/2/5/005.
 *
 * @author ZhangJieBo
 */

public class CommentGoods {
    /**
     * goods_name : 美国原装进口桂格燕麦
     * goods_img : http://app.uyux.vip/attachment/images/1604/2017/12/I3G434jXcj0Xz00m934XxxZeuiy4QU.jpg
     * title : 用户点评（1+）
     * hp : 100
     * star : 5
     * data : [{"id":504,"uid":1011113,"goods_id":1353,"evaluate_time":"2018.02.03","evaluate":"aaaa","star":5,"sku_id":0,"headimg":"http://app.uyux.vip","nickname":"片片啊片片","imgs":[{"thumb":"http://app.uyux.vip/attachment/images/1604/2017/12/I3G434jXcj0Xz00m934XxxZeuiy4QU.jpg","img":"http://app.uyux.vip/attachment/images/1604/2017/12/I3G434jXcj0Xz00m934XxxZeuiy4QU.jpg"}],"spe_name":""}]
     * page : {"page":1,"pageTotal":1,"pageSize":10,"dataTotal":1}
     * status : 1
     * info : 返回成功！
     */

    private String goods_name;
    private String goods_img;
    private String title;
    private int hp;
    private int star;
    private PageBean page;
    private int status;
    private String info;
    private List<DataBean> data;

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_img() {
        return goods_img;
    }

    public void setGoods_img(String goods_img) {
        this.goods_img = goods_img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
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
         * dataTotal : 1
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
         * id : 504
         * uid : 1011113
         * goods_id : 1353
         * evaluate_time : 2018.02.03
         * evaluate : aaaa
         * star : 5
         * sku_id : 0
         * headimg : http://app.uyux.vip
         * nickname : 片片啊片片
         * imgs : [{"thumb":"http://app.uyux.vip/attachment/images/1604/2017/12/I3G434jXcj0Xz00m934XxxZeuiy4QU.jpg","img":"http://app.uyux.vip/attachment/images/1604/2017/12/I3G434jXcj0Xz00m934XxxZeuiy4QU.jpg"}]
         * spe_name :
         */

        private int id;
        private int uid;
        private int goods_id;
        private String evaluate_time;
        private String evaluate;
        private int star;
        private int sku_id;
        private String headimg;
        private String nickname;
        private String spe_name;
        private List<ImgsBean> imgs;

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

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getEvaluate_time() {
            return evaluate_time;
        }

        public void setEvaluate_time(String evaluate_time) {
            this.evaluate_time = evaluate_time;
        }

        public String getEvaluate() {
            return evaluate;
        }

        public void setEvaluate(String evaluate) {
            this.evaluate = evaluate;
        }

        public int getStar() {
            return star;
        }

        public void setStar(int star) {
            this.star = star;
        }

        public int getSku_id() {
            return sku_id;
        }

        public void setSku_id(int sku_id) {
            this.sku_id = sku_id;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getSpe_name() {
            return spe_name;
        }

        public void setSpe_name(String spe_name) {
            this.spe_name = spe_name;
        }

        public List<ImgsBean> getImgs() {
            return imgs;
        }

        public void setImgs(List<ImgsBean> imgs) {
            this.imgs = imgs;
        }

    }
}
