package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/2/12/012.
 *
 * @author ZhangJieBo
 */

public class EvaluationInfo {
    /**
     * img_w : 251
     * img_h : 88
     * img_url : http://app.uyux.vip/Uploads/attachment/20180123/1_1516701675_1.png
     * headimg : http://app.uyux.vip
     * nickname : 片片啊片片
     * title : asd123456
     * imgs : [{"img":1,"content":"asd1234565555555555555","img_url":"http://app.uyux.vip/Uploads/attachment/20180123/1_1516701675_1.png","img_w":251,"img_h":88}]
     * grade : 1
     * collectNum : 0
     * goods_id : 1226
     * btnDes : ￥287.00购买
     * share : {"shareImg":"http://app.uyux.vip/Uploads/logo300x300.png","shareTitle":"网红简约透明健身摇摇杯","shareUrl":"","shareDes":"asd123456"}
     * data : [{"id":14,"nickname":"片片啊片片","headimg":"http://app.uyux.vip","content":"工工","type":0,"list":[{"id":15,"type":1,"nickname":"片片啊片片","content":"工工aaaaaaaaaaaa"},{"id":15,"type":2,"nickname":"片片啊片片","tonickname":"片片啊片片","content":"工工aaaaaaaaaaaa"}]}]
     * status : 1
     * info : 返回成功！
     */

    private int img_w;
    private int img_h;
    private String img_url;
    private String headimg;
    private String nickname;
    private String title;
    private int grade;
    private int collectNum;
    private int goods_id;
    private String btnDes;
    private ShareBean share;
    private int status;
    private String info;
    private List<ImgsBean> imgs;
    private List<DataBean> data;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(int collectNum) {
        this.collectNum = collectNum;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getBtnDes() {
        return btnDes;
    }

    public void setBtnDes(String btnDes) {
        this.btnDes = btnDes;
    }

    public ShareBean getShare() {
        return share;
    }

    public void setShare(ShareBean share) {
        this.share = share;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class ShareBean {
        /**
         * shareImg : http://app.uyux.vip/Uploads/logo300x300.png
         * shareTitle : 网红简约透明健身摇摇杯
         * shareUrl :
         * shareDes : asd123456
         */

        private String shareImg;
        private String shareTitle;
        private String shareUrl;
        private String shareDes;

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

    public static class ImgsBean {
        /**
         * img : 1
         * content : asd1234565555555555555
         * img_url : http://app.uyux.vip/Uploads/attachment/20180123/1_1516701675_1.png
         * img_w : 251
         * img_h : 88
         */

        private int img;
        private String content;
        private String img_url;
        private int img_w;
        private int img_h;

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

    public static class DataBean {
        /**
         * id : 14
         * nickname : 片片啊片片
         * headimg : http://app.uyux.vip
         * content : 工工
         * type : 0
         * list : [{"id":15,"type":1,"nickname":"片片啊片片","content":"工工aaaaaaaaaaaa"},{"id":15,"type":2,"nickname":"片片啊片片","tonickname":"片片啊片片","content":"工工aaaaaaaaaaaa"}]
         */

        private int id;
        private String nickname;
        private String headimg;
        private String content;
        private int type;
        private List<ListBean> list;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 15
             * type : 1
             * nickname : 片片啊片片
             * content : 工工aaaaaaaaaaaa
             * tonickname : 片片啊片片
             */

            private int id;
            private int type;
            private String nickname;
            private String content;
            private String tonickname;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTonickname() {
                return tonickname;
            }

            public void setTonickname(String tonickname) {
                this.tonickname = tonickname;
            }
        }
    }
}
