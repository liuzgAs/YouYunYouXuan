package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/25/025.
 *
 * @author ZhangJieBo
 */

public class GoodsShareimgs {
    /**
     * imgs : ["http://www.uyux.vip/attachment/images/1604/2018/01/BaKAlOoxZAlXK4aXx4zgJXALl24jjJ.jpg","http://www.uyux.vip/attachment/images/1604/2018/01/Eee7RQ88eC97g9897Cs7y89gqJyyUj.jpg"]
     * shareTitle : 猴子系列茶宠
     * shareDes : 猴子系列茶宠优质紫砂泥料 造型精致
     * status : 1
     * info : 操作成功！
     */

    private String shareTitle;
    private String shareDes;
    private int status;
    private String info;
    private List<String> imgs;

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareDes() {
        return shareDes;
    }

    public void setShareDes(String shareDes) {
        this.shareDes = shareDes;
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

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }
}
