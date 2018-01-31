package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/27/027.
 *
 * @author ZhangJieBo
 */

public class ShareIndex {
    /**
     * VipShare : {"shareDes":"分享内容后台填写","shareImg":"http://app.uyux.vip/attachment/head/20180127/1011653_1517018769_1.png","shareTitle":"分享标题后台填写","shareUrl":"http://apizza.cc/"}
     * college_url : http://apizza.cc/
     * des : 加入时间:2018.01.26 08:21
     * grade_name : 专家
     * headimg : http://app.uyux.vip/attachment/head/20180127/1011653_1517018769_1.png
     * info : 返回成功！
     * is_up : 1
     * money : [88.21,81.22,90.32]
     * nickname : 大屌萌妹
     * num : [36.21,25,12,33]
     * status : 1
     * teamShare : {"shareDes":"分享内容后台填写","shareImg":"http://app.uyux.vip/attachment/head/20180127/1011653_1517018769_1.png","shareTitle":"分享标题后台填写","shareUrl":"http://apizza.cc/"}
     * up_url : http://apizza.cc/
     */

    private VipShareBean VipShare;
    private String college_url;
    private String des;
    private String grade_name;
    private String headimg;
    private String info;
    private int is_up;
    private String nickname;
    private int status;
    private TeamShareBean teamShare;
    private String up_url;
    private List<String> money;
    private List<String> num;

    public VipShareBean getVipShare() {
        return VipShare;
    }

    public void setVipShare(VipShareBean VipShare) {
        this.VipShare = VipShare;
    }

    public String getCollege_url() {
        return college_url;
    }

    public void setCollege_url(String college_url) {
        this.college_url = college_url;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getGrade_name() {
        return grade_name;
    }

    public void setGrade_name(String grade_name) {
        this.grade_name = grade_name;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getIs_up() {
        return is_up;
    }

    public void setIs_up(int is_up) {
        this.is_up = is_up;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public TeamShareBean getTeamShare() {
        return teamShare;
    }

    public void setTeamShare(TeamShareBean teamShare) {
        this.teamShare = teamShare;
    }

    public String getUp_url() {
        return up_url;
    }

    public void setUp_url(String up_url) {
        this.up_url = up_url;
    }

    public List<String> getMoney() {
        return money;
    }

    public void setMoney(List<String> money) {
        this.money = money;
    }

    public List<String> getNum() {
        return num;
    }

    public void setNum(List<String> num) {
        this.num = num;
    }

    public static class VipShareBean {
        /**
         * shareDes : 分享内容后台填写
         * shareImg : http://app.uyux.vip/attachment/head/20180127/1011653_1517018769_1.png
         * shareTitle : 分享标题后台填写
         * shareUrl : http://apizza.cc/
         */

        private String shareDes;
        private String shareImg;
        private String shareTitle;
        private String shareUrl;

        public String getShareDes() {
            return shareDes;
        }

        public void setShareDes(String shareDes) {
            this.shareDes = shareDes;
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
    }

    public static class TeamShareBean {
        /**
         * shareDes : 分享内容后台填写
         * shareImg : http://app.uyux.vip/attachment/head/20180127/1011653_1517018769_1.png
         * shareTitle : 分享标题后台填写
         * shareUrl : http://apizza.cc/
         */

        private String shareDes;
        private String shareImg;
        private String shareTitle;
        private String shareUrl;

        public String getShareDes() {
            return shareDes;
        }

        public void setShareDes(String shareDes) {
            this.shareDes = shareDes;
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
    }
}
