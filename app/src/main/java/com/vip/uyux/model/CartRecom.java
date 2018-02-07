package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/2/7/007.
 *
 * @author ZhangJieBo
 */

public class CartRecom {
    /**
     * data : [{"id":1747,"img":"http://app.uyux.vip/attachment/images/1604/2018/01/p80191e10hZZvONzVt2Hz24YS1d8h0_300x300.png","title":"优云优选新春大礼包","des":"新春大礼包限量发售，优选美味，优云与您共同分享~ 18款进口美食，这个春节让舌头根本停不下来，  送礼佳品，优云预祝您新春大吉！","price":"￥199.00","oldPrice":"￥328.00","vipDes":"VIP最高省16.8元","type":1,"saleNum":5800},{"id":1675,"img":"http://app.uyux.vip/attachment/images/vslai_shop/1604/2017/12/i57AVaONF3bFNlbtUWlVTo375u1fTu_300x300.jpg","title":"日式樱花艺术手绘陶瓷礼盒餐具套装","des":"又是一年樱花祭，如樱花般烂漫。古朴气质，简约时尚，经典色彩","price":"￥399.00","oldPrice":"￥399.00","vipDes":"VIP最高省139.3元","type":1,"saleNum":148},{"id":1695,"img":"http://app.uyux.vip/attachment/images/1604/2018/01/a2yJ5G2Z3QYfNgNYSjOVk22Q3sy2KY_300x300.jpg","title":"黑陶空套装","des":"一盏清茶，不负好时光","price":"￥118.00","oldPrice":"￥298.00","vipDes":"VIP最高省10.78元","type":1,"saleNum":65},{"id":1684,"img":"http://app.uyux.vip/attachment/images/1604/2017/12/py3Q9qBV3W6D8Q3DWi8db6g79IQqvq_300x300.gif","title":"24K玫瑰金心形双驱瘦脸仪","des":"打造精致 小v脸，紧致肌肤 随身体验。微电流美容技术，全身瘦身适用神器","price":"￥269.00","oldPrice":"￥299.00","vipDes":"VIP最高省98.49元","type":1,"saleNum":79},{"id":744,"img":"http://app.uyux.vip/attachment/images/1604/2017/12/r2na666iSXpmCa8a2nelAnxH23nXsC_300x300.jpg","title":"3D可水洗透气面包枕","des":"乳胶枕头 整晚舒适 柔软贴合 弹力承托。曲线护颈，抗菌防螨","price":"￥126.00","oldPrice":"￥199.00","vipDes":"VIP最高省27.1元","type":1,"saleNum":93},{"id":1726,"img":"http://app.uyux.vip/attachment/images/vslai_shop/1604/2018/01/mF5OhB6690074M9Ky9nZX6o1oHmXXk_300x300.jpg","title":"高尔夫球面陶瓷竹木盖调味罐","des":"干净整洁的厨房可以创造视觉上的美感，调味料各就各位一目了然，在烹饪时取放方便给您带来欢快的心情下厨，从此爱上厨房","price":"￥56.00","oldPrice":"￥79.00","vipDes":"VIP最高省24.5元","type":1,"saleNum":45},{"id":670,"img":"http://app.uyux.vip/attachment/images/1604/2017/09/Ip9nURRPrGznfruSRlqu9nRqw9dLFs_300x300.jpg","title":"西铁城电动牙刷","des":"传统牙刷无法彻底清洁口腔 残留食物易生菌斑 随时危害牙齿健康。不怕西铁域牙渍软化系统定向清除残留食物牙菌斑；高频振动快速彭冲刷口腔的每一个角落，瓦解细菌的顽固牙渍，40天超长续航，智能频率定时，声波净","price":"￥135.00","oldPrice":"￥165.00","vipDes":"VIP最高省14.21元","type":1,"saleNum":64},{"id":632,"img":"http://app.uyux.vip/attachment/images/1604/2017/09/uz1CR5f5Wi33iyzYd3Ew8YzCivEAWf_300x300.jpg","title":"优云 高清无感气垫bb","des":"优云首款彩妆巨作  秒速上妆 一拍即合  超纳米研磨分解技术  高清无感 完美遮瑕  轻盈质地 美白滋养 防水防晒 持久不脱妆，秒速上妆 一拍即合。 ","price":"￥179.00","oldPrice":"￥269.00","vipDes":"VIP最高省43.4元","type":1,"saleNum":173},{"id":624,"img":"http://app.uyux.vip/attachment/images/1604/2017/12/ZhSh0Jf2ZJj7TU7bHIHH0b70RbST0u_300x300.jpg","title":"优云 补水保湿面膜","des":"优云自营的补水实力派面膜，在寒冷的冬季令你皮肤不再干燥，焕发水润。零刺激皮肤，伤口也可使用。它超薄，超服帖；蕴含库拉索芦荟精华，采用纳米级低分子锁水技术，令你的皮肤告别暗黄，焕发白皙！","price":"￥69.00","oldPrice":"￥128.00","vipDes":"VIP最高省18.9元","type":1,"saleNum":83},{"id":623,"img":"http://app.uyux.vip/attachment/images/1604/2017/12/c10Rhb1XigZ8H8z0s7838S83Ua37G3_300x300.jpg","title":"优云 国礼焕颜滋养修护面膜","des":"泰国前总理英拉、英国凯特王妃联合推荐、全球第一款可以喝的面膜原液，极致安全，37种动植物精华，珍贵海茴香成分，真蚕丝膜材，持续畅销3年。","price":"￥208.00","oldPrice":"￥298.00","vipDes":"VIP最高省34.3元","type":1,"saleNum":385}]
     * page : {"page":1,"pageTotal":8,"pageSize":10,"dataTotal":77}
     * status : 1
     * info : 操作成功！
     */

    private PageBean page;
    private int status;
    private String info;
    private List<DataBean> data;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class PageBean {
        /**
         * page : 1
         * pageTotal : 8
         * pageSize : 10
         * dataTotal : 77
         */

        private int page;
        private int pageTotal;
        private int pageSize;
        private int dataTotal;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPageTotal() {
            return pageTotal;
        }

        public void setPageTotal(int pageTotal) {
            this.pageTotal = pageTotal;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getDataTotal() {
            return dataTotal;
        }

        public void setDataTotal(int dataTotal) {
            this.dataTotal = dataTotal;
        }
    }

    public static class DataBean {
        /**
         * id : 1747
         * img : http://app.uyux.vip/attachment/images/1604/2018/01/p80191e10hZZvONzVt2Hz24YS1d8h0_300x300.png
         * title : 优云优选新春大礼包
         * des : 新春大礼包限量发售，优选美味，优云与您共同分享~ 18款进口美食，这个春节让舌头根本停不下来，  送礼佳品，优云预祝您新春大吉！
         * price : ￥199.00
         * oldPrice : ￥328.00
         * vipDes : VIP最高省16.8元
         * type : 1
         * saleNum : 5800
         */

        private int id;
        private String img;
        private String title;
        private String des;
        private String price;
        private String oldPrice;
        private String vipDes;
        private int type;
        private int saleNum;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getOldPrice() {
            return oldPrice;
        }

        public void setOldPrice(String oldPrice) {
            this.oldPrice = oldPrice;
        }

        public String getVipDes() {
            return vipDes;
        }

        public void setVipDes(String vipDes) {
            this.vipDes = vipDes;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }
    }
}
