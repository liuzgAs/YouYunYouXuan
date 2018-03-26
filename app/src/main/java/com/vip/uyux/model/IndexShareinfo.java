package com.vip.uyux.model;

/**
 * Created by zhangjiebo on 2018/3/24/024.
 *
 * @author ZhangJieBo
 */

public class IndexShareinfo {

    /**
     * share : {"shareImg":"http://app.uyux.vip/attachment/images/20180213/ceb7f6ffe27c0ddaa45559a38946a081.jpg","shareTitle":"骨传导运动蓝牙耳机，轻盈舒适，长时间佩戴无束缚。给您带来不一样的运动感觉骨传导运动蓝牙耳机，轻盈舒适，长时间佩戴无束缚。给您带来","shareUrl":"http://app.uyux.vip/api/Article/hwinfo/id/3/pid/1013180.html","shareDes":"骨传导运动蓝牙耳机，轻盈舒适，长时间佩戴无束缚。给您带来不一样的运动感觉口令君君的时候我"}
     * status : 1
     * info : 操作成功！
     */

    private ShareBean share;
    private int status;
    private String info;

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

    public static class ShareBean {
        /**
         * shareImg : http://app.uyux.vip/attachment/images/20180213/ceb7f6ffe27c0ddaa45559a38946a081.jpg
         * shareTitle : 骨传导运动蓝牙耳机，轻盈舒适，长时间佩戴无束缚。给您带来不一样的运动感觉骨传导运动蓝牙耳机，轻盈舒适，长时间佩戴无束缚。给您带来
         * shareUrl : http://app.uyux.vip/api/Article/hwinfo/id/3/pid/1013180.html
         * shareDes : 骨传导运动蓝牙耳机，轻盈舒适，长时间佩戴无束缚。给您带来不一样的运动感觉口令君君的时候我
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
}
