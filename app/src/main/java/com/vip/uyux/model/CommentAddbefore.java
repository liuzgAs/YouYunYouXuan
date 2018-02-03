package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/2/3/003.
 *
 * @author ZhangJieBo
 */

public class CommentAddbefore {

    /**
     * img : http://app.uyux.vip/attachment/images/1604/2017/12/I3G434jXcj0Xz00m934XxxZeuiy4QU.jpg
     * goods_name : http://app.uyux.vip/attachment/images/1604/2017/12/I3G434jXcj0Xz00m934XxxZeuiy4QU.jpg
     * tag : [{"title":"很漂亮美丽喜欢","id":1},{"title":"美丽喜欢","id":2},{"title":"美丽","id":3},{"title":"喜欢","id":4}]
     * des : 说说您对商品的评价！
     * status : 1
     * info : 返回成功！
     */

    private String img;
    private String goods_name;
    private String des;
    private int status;
    private String info;
    private List<TagBean> tag;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
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
         * title : 很漂亮美丽喜欢
         * id : 1
         */

        private String title;
        private int id;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
