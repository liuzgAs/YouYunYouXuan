package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/2/9/009.
 *
 * @author ZhangJieBo
 */

public class BonusSuperioritybefore {

    /**
     * data : [{"id":1,"name":"有效资质证明1"},{"id":2,"name":"有效资质证明2"},{"id":3,"name":"有效资质证明3"}]
     * nature : [{"name":"有效资质证明1"},{"name":"有效资质证明2"},{"name":"有效资质证明3"}]
     * url : http://app.uyux.vip/api/Article/info/type/cooperation
     * url_title : 合作协议
     * status : 1
     * info : 操作成功！
     */

    private String url;
    private String url_title;
    private int status;
    private String info;
    private String huoYuanXingZhi;
    private List<DataBean> data;
    private List<NatureBean> nature;

    public String getHuoYuanXingZhi() {
        return huoYuanXingZhi;
    }

    public void setHuoYuanXingZhi(String huoYuanXingZhi) {
        this.huoYuanXingZhi = huoYuanXingZhi;
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

    public List<NatureBean> getNature() {
        return nature;
    }

    public void setNature(List<NatureBean> nature) {
        this.nature = nature;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 有效资质证明1
         */

        private int id;
        private String name;
        private boolean isCheck;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class NatureBean {
        /**
         * name : 有效资质证明1
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
