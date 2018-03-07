package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/23/023.
 *
 * @author ZhangJieBo
 */

public class IndexRecom {
    /**
     * banner : [{"img":"http://api.uyux.vip/Uploads/attachment/20180119/5ce9903e9c1a656874d488ebc077fa88.png","code":"","item_id":0,"url":""},{"img":"http://api.uyux.vip/Uploads/attachment/20180119/5ce9903e9c1a656874d488ebc077fa88.png","code":"","item_id":0,"url":""},{"img":"http://api.uyux.vip/Uploads/attachment/20180119/5ce9903e9c1a656874d488ebc077fa88.png","code":"","item_id":0,"url":""}]
     * banner2 : [{"img":"http://api.uyux.vip/Uploads/attachment/20180119/5faa856af1159f4ca38cafa44f44db9e.png","code":"","item_id":0,"url":""}]
     * data : [{"id":632,"img":"http://www.uyux.vip/attachment/images/1604/2017/09/uz1CR5f5Wi33iyzYd3Ew8YzCivEAWf.jpg","title":"优云 高清无感气垫bb","des":"秒速上妆 一拍即合  超纳米研磨分解技术  高清无感 完美遮瑕  轻盈质地 美白滋养 防水防晒 持久不脱妆","price":"￥179.00","oldPrice":"￥269.00","vipDes":"五星会员省8元","type":1,"saleNum":5869},{"id":660,"img":"http://www.uyux.vip/attachment/images/1604/2017/09/H10WQr7Krk16HrRjK1d13h6D331r6Q.jpg","title":"伊莱克斯 多士炉","des":"7档烤色控制，烤出不同口感","price":"￥210.00","oldPrice":"￥268.00","vipDes":"五星会员省8元","type":1,"saleNum":5869},{"id":640,"img":"http://www.uyux.vip/attachment/images/1604/2017/09/KLiZt8IIp8EjkzKbKZ3z1lyAbK8Tw4.jpg","title":"BENTLY  简约时尚男士/女士手表","des":"BENTLY  简约时尚男士/女士手表","price":"￥1299.00","oldPrice":"￥1980.00","vipDes":"五星会员省8元","type":1,"saleNum":5869},{"id":620,"img":"http://www.uyux.vip/attachment/images/1604/2017/09/l7S4RUsF474B4FU7wl5wsbb5ibrcFC.jpg","title":"酷友  悬浮的超低音音响","des":"酷友  悬浮的超低音音响","price":"￥2099.00","oldPrice":"￥2299.00","vipDes":"五星会员省8元","type":1,"saleNum":5869},{"id":371,"img":"http://www.uyux.vip/attachment/images/1604/2017/09/KN1L8UGNt4g8X4XGm2gu4xf3Xg3Fg8.jpg","title":"片仔癀皇后牌  粉刺修复露60g+祛痘活肤洁面乳 80g","des":"","price":"￥69.00","oldPrice":"￥85.00","vipDes":"五星会员省8元","type":1,"saleNum":5869},{"id":352,"img":"http://www.uyux.vip/attachment/images/1604/2017/09/xcQp9g55hL7dG9L590cq5fZ9Dismjf.png","title":"bering新品男女商务手表 皮带防水石英表蓝宝石时尚简约男表14240","des":"","price":"￥1150.00","oldPrice":"￥1350.00","vipDes":"五星会员省8元","type":1,"saleNum":5869},{"id":350,"img":"http://www.uyux.vip/attachment/images/1604/2017/09/NrjbBMBzijqmYi52L5MlA9c75vAi2l.jpg","title":"Bering白令进口中性钢带腕表大表盘简约情侣手表商务休闲11139S","des":"","price":"￥869.00","oldPrice":"￥989.00","vipDes":"五星会员省8元","type":1,"saleNum":5869},{"id":349,"img":"http://www.uyux.vip/attachment/images/1604/2017/09/OMTJZt6rT26srmYrx169dtyd5mmb7r.png","title":"Bering白令进口腕表简约皮带商务男表时尚防水轻薄手表13242","des":"","price":"￥1260.00","oldPrice":"￥1450.00","vipDes":"五星会员省8元","type":1,"saleNum":5869}]
     * status : 1
     * info : 操作成功！
     */

    private int status;
    private String info;
    private List<AdvsBean> banner;
    private List<AdvsBean> banner2;
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

    public List<AdvsBean> getBanner() {
        return banner;
    }

    public void setBanner(List<AdvsBean> banner) {
        this.banner = banner;
    }

    public List<AdvsBean> getBanner2() {
        return banner2;
    }

    public void setBanner2(List<AdvsBean> banner2) {
        this.banner2 = banner2;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int id;
        private String title;
        private String img;
        private int type;
        private int isc;
        private String nickname;
        private int collectNum;

        public int getIsc() {
            return isc;
        }

        public void setIsc(int isc) {
            this.isc = isc;
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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
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

        public int getCollectNum() {
            return collectNum;
        }

        public void setCollectNum(int collectNum) {
            this.collectNum = collectNum;
        }
    }

}
