package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/23/023.
 *
 * @author ZhangJieBo
 */

public class GoodsIndex {
    /**
     * data : [{"id":691,"img":"http://www.uyux.vip/attachment/images/1604/2017/12/kPr7uYyu4PFOFGPB4D86FbPX2pYR8x.jpg","title":"越南jido鸡蛋面包干","des":"严格筛选的好食材才会有好面包干","price":"￥5.80","oldPrice":"￥0.00","vipDes":"五星会员省8元","type":1,"saleNum":5869},{"id":1672,"img":"http://www.uyux.vip/attachment/images/1604/2018/01/p8Dc88G2G86gk6d780PP7b3JkB28g2.jpg","title":"燕知元冰糖即食碗燕 碗装礼盒","des":"极净纯炖 即享美丽","price":"￥398.00","oldPrice":"￥899.00","vipDes":"五星会员省8元","type":1,"saleNum":5869},{"id":1670,"img":"http://www.uyux.vip/attachment/images/1604/2018/01/M57t5070xrrixn0skx14000EA3VxRI.jpg","title":"燕知元冰糖即食燕窝 玻璃瓶装礼盒","des":"来自东南亚热带雨林深处天然燕窝原料","price":"￥198.00","oldPrice":"￥399.00","vipDes":"五星会员省8元","type":1,"saleNum":5869},{"id":1356,"img":"http://www.uyux.vip/attachment/images/1604/2017/12/ijdI4A9Cgs9duDD9zx4BpCjcb1d9uc.jpg","title":"元初食品山楂片","des":"拣选优质\u201c大金星\u201d山楂为原料","price":"￥8.50","oldPrice":"￥9.90","vipDes":"五星会员省8元","type":1,"saleNum":5869},{"id":1351,"img":"http://www.uyux.vip/attachment/images/1604/2017/12/D0Nvz5N5GVVb308G9yFzzPGS00833v.jpg","title":"元初食品大马怡宝咖啡","des":"4合1榛果白咖啡（480g)","price":"￥49.50","oldPrice":"￥0.00","vipDes":"五星会员省8元","type":1,"saleNum":5869},{"id":1154,"img":"http://www.uyux.vip/attachment/images/1604/2017/12/fif28vrqLl2A966QlTlm7VMFI9zPVM.png","title":"1000金额测试","des":"1","price":"￥1000.00","oldPrice":"￥1000.00","vipDes":"五星会员省8元","type":1,"saleNum":5869},{"id":888,"img":"http://www.uyux.vip/attachment/images/1604/2017/12/P4414mMwe5h412Or5maEJ434JWa1iL.jpg","title":"金小熊哈蜜瓜灌心饼","des":"造型好看 一口咬下 香浓的味道融化在口中","price":"￥7.80","oldPrice":"￥8.00","vipDes":"五星会员省8元","type":1,"saleNum":5869},{"id":886,"img":"http://www.uyux.vip/attachment/images/1604/2017/12/DXI71777737diA1z07DNm771717u7x.jpg","title":"榴莲威化饼干","des":"满足舌尖的挑剔","price":"￥17.80","oldPrice":"￥20.00","vipDes":"五星会员省8元","type":1,"saleNum":5869},{"id":885,"img":"http://www.uyux.vip/attachment/images/1604/2017/12/AaAYJAlu5pJAME2tCRElRma5h95aCz.jpg","title":"山竹威化饼干","des":"触手可及的南洋美味","price":"￥17.80","oldPrice":"￥20.00","vipDes":"五星会员省8元","type":1,"saleNum":5869},{"id":693,"img":"http://www.uyux.vip/attachment/images/1604/2017/12/h8C7C88mFb4PPP7r8mSlZ9PRVff6m7.jpg","title":"意大利夹心饼干","des":"浓郁的夹心融化在口中 味蕾的小满足","price":"￥25.90","oldPrice":"￥29.90","vipDes":"五星会员省8元","type":1,"saleNum":5869}]
     * status : 1
     * info : 返回成功！
     */

    private int status;
    private String info;
    private String title;
    private List<GoodBean> data;
    private int is_cate;
    private List<CateBean> cate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIs_cate() {
        return is_cate;
    }

    public void setIs_cate(int is_cate) {
        this.is_cate = is_cate;
    }

    public List<CateBean> getCate() {
        return cate;
    }

    public void setCate(List<CateBean> cate) {
        this.cate = cate;
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

    public List<GoodBean> getData() {
        return data;
    }

    public void setData(List<GoodBean> data) {
        this.data = data;
    }


    public static class CateBean{
        private int id;
        private String name;
        private int act;

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

        public int getAct() {
            return act;
        }

        public void setAct(int act) {
            this.act = act;
        }
    }
}
