package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/2/2/002.
 *
 * @author ZhangJieBo
 */

public class UserOrderinfo {

    /**
     * logState : 2
     * logTitle : 已付款等待卖家发货
     * logDes :
     * address : {"consignee":"123","phone":"15860026753","address":"我要去","area":"北京-北京-东城区"}
     * data : {"id":283,"oid":"294","list":[{"id":294,"supplier":"金达威","state":20,"vip_amount":"0.00","is_btn":0,"code":"text","text":"已付款","list":[{"id":295,"goods_img":"http://app.uyux.vip/attachment/images/20180128/6429276d15105828008b84183a972c1b.png","goods_id":1754,"goods_name":"测试999","goods_sku":"","goods_price":"0.10","quantity":1}]}],"desList":["运费：包邮"],"sumDes":"小计：","sum":"￥0.10","orderSn":"订单编号:UY201801301559163","create_time":"创建时间:2018.01.30","goPay":0,"isCancel":0}
     * status : 1
     * info : 操作成功！
     */

    private int logState;
    private String logTitle;
    private String logDes;
    private AddressBean address;
    private DataBean data;
    private int status;
    private String info;

    public int getLogState() {
        return logState;
    }

    public void setLogState(int logState) {
        this.logState = logState;
    }

    public String getLogTitle() {
        return logTitle;
    }

    public void setLogTitle(String logTitle) {
        this.logTitle = logTitle;
    }

    public String getLogDes() {
        return logDes;
    }

    public void setLogDes(String logDes) {
        this.logDes = logDes;
    }

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class AddressBean {
        /**
         * consignee : 123
         * phone : 15860026753
         * address : 我要去
         * area : 北京-北京-东城区
         */

        private String consignee;
        private String phone;
        private String address;
        private String area;

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }
    }

    public static class DataBean {
        /**
         * id : 283
         * oid : 294
         * list : [{"id":294,"supplier":"金达威","state":20,"vip_amount":"0.00","is_btn":0,"code":"text","text":"已付款","list":[{"id":295,"goods_img":"http://app.uyux.vip/attachment/images/20180128/6429276d15105828008b84183a972c1b.png","goods_id":1754,"goods_name":"测试999","goods_sku":"","goods_price":"0.10","quantity":1}]}]
         * desList : ["运费：包邮"]
         * sumDes : 小计：
         * sum : ￥0.10
         * orderSn : 订单编号:UY201801301559163
         * create_time : 创建时间:2018.01.30
         * goPay : 0
         * isCancel : 0
         */

        private int id;
        private String oid;
        private String sumDes;
        private String sum;
        private String orderSn;
        private String create_time;
        private int goPay;
        private int isCancel;
        private List<ListBeanX> list;
        private List<String> desList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getOrderSn() {
            return orderSn;
        }

        public void setOrderSn(String orderSn) {
            this.orderSn = orderSn;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
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
             * id : 294
             * supplier : 金达威
             * state : 20
             * vip_amount : 0.00
             * is_btn : 0
             * code : text
             * text : 已付款
             * list : [{"id":295,"goods_img":"http://app.uyux.vip/attachment/images/20180128/6429276d15105828008b84183a972c1b.png","goods_id":1754,"goods_name":"测试999","goods_sku":"","goods_price":"0.10","quantity":1}]
             */

            private int id;
            private String supplier;
            private int state;
            private String vip_amount;
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

            public String getVip_amount() {
                return vip_amount;
            }

            public void setVip_amount(String vip_amount) {
                this.vip_amount = vip_amount;
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
                 * id : 295
                 * goods_img : http://app.uyux.vip/attachment/images/20180128/6429276d15105828008b84183a972c1b.png
                 * goods_id : 1754
                 * goods_name : 测试999
                 * goods_sku :
                 * goods_price : 0.10
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
