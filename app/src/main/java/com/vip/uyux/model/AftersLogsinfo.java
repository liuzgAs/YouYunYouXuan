package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/2/7/007.
 *
 * @author ZhangJieBo
 */

public class AftersLogsinfo {
    /**
     * title : ["服务单号：","申请时间：2018.02.06"]
     * goods : {"spe_name":"","id":1353,"name":"美国原装进口桂格燕麦","img":"http://app.uyux.vip/attachment/images/1604/2017/12/I3G434jXcj0Xz00m934XxxZeuiy4QU.jpg"}
     * des : [{"title":"退款原因","des":"补发商品"},{"title":"原因描述","des":"啦咯啦咯啦咯啦咯"}]
     * status : 1
     * info : 返回成功！
     */

    private GoodsBean goods;
    private int status;
    private String info;
    private List<String> title;
    private List<DesBean> des;

    public GoodsBean getGoods() {
        return goods;
    }

    public void setGoods(GoodsBean goods) {
        this.goods = goods;
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

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<DesBean> getDes() {
        return des;
    }

    public void setDes(List<DesBean> des) {
        this.des = des;
    }

    public static class GoodsBean {
        /**
         * spe_name :
         * id : 1353
         * name : 美国原装进口桂格燕麦
         * img : http://app.uyux.vip/attachment/images/1604/2017/12/I3G434jXcj0Xz00m934XxxZeuiy4QU.jpg
         */

        private String spe_name;
        private int id;
        private String name;
        private String img;

        public String getSpe_name() {
            return spe_name;
        }

        public void setSpe_name(String spe_name) {
            this.spe_name = spe_name;
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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }

    public static class DesBean {
        /**
         * title : 退款原因
         * des : 补发商品
         */

        private String title;
        private String des;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }
    }
}
