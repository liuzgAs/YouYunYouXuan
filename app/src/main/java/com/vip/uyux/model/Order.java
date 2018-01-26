package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/26/026.
 *
 * @author ZhangJieBo
 */

public class Order {
    /**
     * type : [{"n":"全部","v":"","act":1},{"n":"待付款","v":"10","act":0},{"n":"待发货","v":"20","act":0},{"n":"待收货","v":"30","act":0},{"n":"待评介","v":"40","act":0}]
     * data : [{"orderSn":"订单编号:UY201801261651493","type":0,"orderSnDes":"待付款","oid":"9","list":[{"id":9,"supplier":"平台自营","state":10,"is_btn":0,"code":"text","text":"未付款","list":[{"id":9,"goods_img":"http://www.uyux.vip/attachment/images/1604/2017/12/zn1uQQ1RWumJ6L2nWzw9Q9OZ6uQlmM.jpg","goods_id":1126,"goods_name":"雪耳","goods_sku":"颜色:咖啡色,尺码:XXL,厚度:加绒,","goods_price":"88.00","quantity":1}]}],"desList":["已优惠：￥5元","运费：包邮"],"sumDes":"共1件商品合计：","sum":"￥88.00","goPay":1,"isCancel":1},{"orderSn":"订单编号:UY201801261649093","type":0,"orderSnDes":"待付款","oid":"8","list":[{"id":8,"supplier":"西瑞科技","state":10,"is_btn":0,"code":"text","text":"未付款","list":[{"id":8,"goods_img":"http://www.uyux.vip/attachment/images/1604/2017/12/zeaileDPEZld8iPgoLvpq7eOQ81c1P.jpg","goods_id":1286,"goods_name":"便捷吹气旅行枕","goods_sku":"颜色:腮红色,尺码:S,厚度:加绒,","goods_price":"88.00","quantity":1}]}],"desList":["已优惠：￥5元","运费：包邮"],"sumDes":"共1件商品合计：","sum":"￥88.00","goPay":1,"isCancel":1},{"orderSn":"订单编号:UY201801252005179","type":1,"orderSnDes":"待付款","oid":"6,7","list":[{"id":6,"supplier":"平台自营","state":10,"is_btn":0,"code":"text","text":"未付款","list":[{"id":6,"goods_img":"http://www.uyux.vip/attachment/images/1604/2018/01/aVvkRUfsjsZJvq0r0kKw707Te7esED.jpg","goods_id":1697,"goods_name":"猴子系列茶宠","goods_sku":"颜色:咖啡色,尺码:M,厚度:加绒,","goods_price":"88.00","quantity":1}]},{"id":7,"supplier":"优云优选12号仓","state":10,"is_btn":0,"code":"text","text":"未付款","list":[{"id":7,"goods_img":"http://www.uyux.vip/attachment/images/1604/2018/01/ggZpBQPGt4GpQcC9pPCZa9VBj4CEZg.jpg","goods_id":1703,"goods_name":"老岩泥旅行装","goods_sku":"","goods_price":"118.00","quantity":1}]}],"desList":["已优惠：￥5元","运费：包邮"],"sumDes":"共2件商品合计：","sum":"￥206.00","goPay":1,"isCancel":1},{"orderSn":"订单编号:UY201801251502588","type":1,"orderSnDes":"待付款","oid":"4,5","list":[{"id":4,"supplier":"平台自营","state":10,"is_btn":0,"code":"text","text":"未付款","list":[{"id":4,"goods_img":"http://www.uyux.vip/attachment/images/1604/2018/01/ggZpBQPGt4GpQcC9pPCZa9VBj4CEZg.jpg","goods_id":1703,"goods_name":"老岩泥旅行装","goods_sku":"","goods_price":"118.00","quantity":1}]},{"id":5,"supplier":"优云优选12号仓","state":10,"is_btn":0,"code":"text","text":"未付款","list":[{"id":5,"goods_img":"http://www.uyux.vip/attachment/images/1604/2018/01/ggZpBQPGt4GpQcC9pPCZa9VBj4CEZg.jpg","goods_id":1703,"goods_name":"老岩泥旅行装","goods_sku":"","goods_price":"118.00","quantity":1}]}],"desList":["已优惠：￥5元","运费：包邮"],"sumDes":"共2件商品合计：","sum":"￥206.00","goPay":1,"isCancel":1}]
     * page : {"page":1,"pageTotal":1,"pageSize":10,"dataTotal":4}
     * status : 1
     * info : 返回成功！
     */

    private PageBean page;
    private int status;
    private String info;
    private List<TypeBean> type;
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

    public List<TypeBean> getType() {
        return type;
    }

    public void setType(List<TypeBean> type) {
        this.type = type;
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
         * pageTotal : 1
         * pageSize : 10
         * dataTotal : 4
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

    public static class TypeBean {
        /**
         * n : 全部
         * v :
         * act : 1
         */

        private String n;
        private String v;
        private int act;

        public String getN() {
            return n;
        }

        public void setN(String n) {
            this.n = n;
        }

        public String getV() {
            return v;
        }

        public void setV(String v) {
            this.v = v;
        }

        public int getAct() {
            return act;
        }

        public void setAct(int act) {
            this.act = act;
        }
    }

    public static class DataBean {
        /**
         * orderSn : 订单编号:UY201801261651493
         * type : 0
         * orderSnDes : 待付款
         * oid : 9
         * list : [{"id":9,"supplier":"平台自营","state":10,"is_btn":0,"code":"text","text":"未付款","list":[{"id":9,"goods_img":"http://www.uyux.vip/attachment/images/1604/2017/12/zn1uQQ1RWumJ6L2nWzw9Q9OZ6uQlmM.jpg","goods_id":1126,"goods_name":"雪耳","goods_sku":"颜色:咖啡色,尺码:XXL,厚度:加绒,","goods_price":"88.00","quantity":1}]}]
         * desList : ["已优惠：￥5元","运费：包邮"]
         * sumDes : 共1件商品合计：
         * sum : ￥88.00
         * goPay : 1
         * isCancel : 1
         */

        private String orderSn;
        private int type;
        private String orderSnDes;
        private String oid;
        private String sumDes;
        private String sum;
        private int goPay;
        private int isCancel;
        private List<ListBeanX> list;
        private List<String> desList;

        public String getOrderSn() {
            return orderSn;
        }

        public void setOrderSn(String orderSn) {
            this.orderSn = orderSn;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getOrderSnDes() {
            return orderSnDes;
        }

        public void setOrderSnDes(String orderSnDes) {
            this.orderSnDes = orderSnDes;
        }

        public String getOid() {
            return oid;
        }

        public void setOid(String oid) {
            this.oid = oid;
        }

        public String getSumDes() {
            return sumDes;
        }

        public void setSumDes(String sumDes) {
            this.sumDes = sumDes;
        }

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }

        public int getGoPay() {
            return goPay;
        }

        public void setGoPay(int goPay) {
            this.goPay = goPay;
        }

        public int getIsCancel() {
            return isCancel;
        }

        public void setIsCancel(int isCancel) {
            this.isCancel = isCancel;
        }

        public List<ListBeanX> getList() {
            return list;
        }

        public void setList(List<ListBeanX> list) {
            this.list = list;
        }

        public List<String> getDesList() {
            return desList;
        }

        public void setDesList(List<String> desList) {
            this.desList = desList;
        }

        public static class ListBeanX {
            /**
             * id : 9
             * supplier : 平台自营
             * state : 10
             * is_btn : 0
             * code : text
             * text : 未付款
             * list : [{"id":9,"goods_img":"http://www.uyux.vip/attachment/images/1604/2017/12/zn1uQQ1RWumJ6L2nWzw9Q9OZ6uQlmM.jpg","goods_id":1126,"goods_name":"雪耳","goods_sku":"颜色:咖啡色,尺码:XXL,厚度:加绒,","goods_price":"88.00","quantity":1}]
             */

            private int id;
            private String supplier;
            private int state;
            private int is_btn;
            private String code;
            private String text;
            private List<ListBean> list;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSupplier() {
                return supplier;
            }

            public void setSupplier(String supplier) {
                this.supplier = supplier;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public int getIs_btn() {
                return is_btn;
            }

            public void setIs_btn(int is_btn) {
                this.is_btn = is_btn;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * id : 9
                 * goods_img : http://www.uyux.vip/attachment/images/1604/2017/12/zn1uQQ1RWumJ6L2nWzw9Q9OZ6uQlmM.jpg
                 * goods_id : 1126
                 * goods_name : 雪耳
                 * goods_sku : 颜色:咖啡色,尺码:XXL,厚度:加绒,
                 * goods_price : 88.00
                 * quantity : 1
                 */

                private int id;
                private String goods_img;
                private int goods_id;
                private String goods_name;
                private String goods_sku;
                private String goods_price;
                private int quantity;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getGoods_img() {
                    return goods_img;
                }

                public void setGoods_img(String goods_img) {
                    this.goods_img = goods_img;
                }

                public int getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(int goods_id) {
                    this.goods_id = goods_id;
                }

                public String getGoods_name() {
                    return goods_name;
                }

                public void setGoods_name(String goods_name) {
                    this.goods_name = goods_name;
                }

                public String getGoods_sku() {
                    return goods_sku;
                }

                public void setGoods_sku(String goods_sku) {
                    this.goods_sku = goods_sku;
                }

                public String getGoods_price() {
                    return goods_price;
                }

                public void setGoods_price(String goods_price) {
                    this.goods_price = goods_price;
                }

                public int getQuantity() {
                    return quantity;
                }

                public void setQuantity(int quantity) {
                    this.quantity = quantity;
                }
            }
        }
    }
}
