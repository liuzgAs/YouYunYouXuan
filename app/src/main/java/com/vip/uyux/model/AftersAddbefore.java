package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/2/7/007.
 *
 * @author ZhangJieBo
 */

public class AftersAddbefore {
    /**
     * img : http://app.uyux.vip/attachment/images/1604/2017/12/I3G434jXcj0Xz00m934XxxZeuiy4QU.jpg
     * goods_price : 49.50
     * goods_name : 美国原装进口桂格燕麦
     * spe_name :
     * tag : [{"id":1,"title":"退货"},{"id":2,"title":"换货"},{"id":3,"title":"维修"},{"id":4,"title":"补发商品"}]
     * des : 请在此输入问题描述！
     * des2 : 提交服务单后，售后专员可能与您电话沟通，请保持手机通畅！
     * status : 1
     * info : 返回成功！
     */

    private String img;
    private String goods_price;
    private String goods_name;
    private String spe_name;
    private String des;
    private String des2;
    private String intro;
    private int status;
    private String info;
    private List<TagBean> tag;

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getSpe_name() {
        return spe_name;
    }

    public void setSpe_name(String spe_name) {
        this.spe_name = spe_name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDes2() {
        return des2;
    }

    public void setDes2(String des2) {
        this.des2 = des2;
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

    public List<TagBean> getTag() {
        return tag;
    }

    public void setTag(List<TagBean> tag) {
        this.tag = tag;
    }

    public static class TagBean {
        /**
         * id : 1
         * title : 退货
         */

        private int id;
        private String title;
        private boolean isSelect;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
