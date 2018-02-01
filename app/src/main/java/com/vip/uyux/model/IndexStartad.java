package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2017/9/24 0024.
 */
public class IndexStartad {
    /**
     * cityName : 厦门
     * cityId : 60
     * did : 3
     * advs : [{"img":"http://app.uyux.vip/attachment/images/20180129/43d67922853ecb099dc861ed5709cea8.jpg","code":"","item_id":0,"url":""}]
     * status : 1
     * info : 操作成功！
     */

    private String cityName;
    private String cityId;
    private int did;
    private int status;
    private String info;
    private List<AdvsBean> advs;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
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

    public List<AdvsBean> getAdvs() {
        return advs;
    }

    public void setAdvs(List<AdvsBean> advs) {
        this.advs = advs;
    }

    public static class AdvsBean {
        /**
         * img : http://app.uyux.vip/attachment/images/20180129/43d67922853ecb099dc861ed5709cea8.jpg
         * code :
         * item_id : 0
         * url :
         */

        private String img;
        private String code;
        private int item_id;
        private String url;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getItem_id() {
            return item_id;
        }

        public void setItem_id(int item_id) {
            this.item_id = item_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
