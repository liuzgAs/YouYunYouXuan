package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/3/5/005.
 *
 * @author ZhangJieBo
 */

public class IndexSeaamoycate {
    /**
     * data : [{"id":0,"name":"推荐","key":"pcate","act":1},{"id":39,"name":"全球美食","key":"pcate","act":0},{"id":135,"name":"家居日用","key":"pcate","act":0},{"id":22,"name":"餐厨用具","key":"pcate","act":0},{"id":4,"name":"美妆个护","key":"pcate","act":0},{"id":160,"name":"3C电器","key":"pcate","act":0},{"id":134,"name":"鞋服箱包","key":"pcate","act":0},{"id":184,"name":"母婴儿童","key":"pcate","act":0},{"id":32,"name":"杂货铺子","key":"pcate","act":0}]
     * status : 1
     * info : 操作成功！
     */

    private int status;
    private String info;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 0
         * name : 推荐
         * key : pcate
         * act : 1
         */

        private int id;
        private String name;
        private String key;
        private int act;

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

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public int getAct() {
            return act;
        }

        public void setAct(int act) {
            this.act = act;
        }
    }
}
