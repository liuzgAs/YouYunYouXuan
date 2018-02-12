package com.vip.uyux.model;

import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/2/12/012.
 *
 * @author ZhangJieBo
 */

public class EvaluationAddbefore {
    /**
     * title : 测一测
     * img : 3768
     * img_w : 1920
     * img_h : 1080
     * img_url : http://app.uyux.vip/attachment/head/20180212/1013142_1518400367_1.png
     * id : 3
     * imgs : [{"img":3766,"content":"内容","img_url":"http://app.uyux.vip/attachment/head/20180212/1013142_1518400366_1.png","img_w":754,"img_h":1008},{"img":3767,"content":"内容","img_url":"http://app.uyux.vip/attachment/head/20180212/1013142_1518400366_2.png","img_w":754,"img_h":1008}]
     * status : 1
     * info : 返回成功！
     */

    private String title;
    private String img;
    private int img_w;
    private int img_h;
    private String img_url;
    private LocalMedia imgBean;
    private String id;
    private int status;
    private String info;
    private List<ImgsBean> imgs;

    public LocalMedia getImgBean() {
        return imgBean;
    }

    public void setImgBean(LocalMedia imgBean) {
        this.imgBean = imgBean;
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

    public int getImg_w() {
        return img_w;
    }

    public void setImg_w(int img_w) {
        this.img_w = img_w;
    }

    public int getImg_h() {
        return img_h;
    }

    public void setImg_h(int img_h) {
        this.img_h = img_h;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<ImgsBean> getImgs() {
        return imgs;
    }

    public void setImgs(List<ImgsBean> imgs) {
        this.imgs = imgs;
    }

    public static class ImgsBean {
        /**
         * img : 3766
         * content : 内容
         * img_url : http://app.uyux.vip/attachment/head/20180212/1013142_1518400366_1.png
         * img_w : 754
         * img_h : 1008
         */

        private int img;
        private String content;
        private String img_url;
        private int img_w;
        private int img_h;
        private LocalMedia imgBean;

        public ImgsBean(int img, String content, LocalMedia imgBean) {
            this.img = img;
            this.content = content;
            this.imgBean = imgBean;
        }

        public LocalMedia getImgBean() {
            return imgBean;
        }

        public void setImgBean(LocalMedia imgBean) {
            this.imgBean = imgBean;
        }

        public int getImg() {
            return img;
        }

        public void setImg(int img) {
            this.img = img;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public int getImg_w() {
            return img_w;
        }

        public void setImg_w(int img_w) {
            this.img_w = img_w;
        }

        public int getImg_h() {
            return img_h;
        }

        public void setImg_h(int img_h) {
            this.img_h = img_h;
        }
    }
}
