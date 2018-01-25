package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/25/025.
 *
 * @author ZhangJieBo
 */

public class WithdrawNotwithdraw {

    /**
     * n_amount : 5632
     * data : [{"id":1,"name":"周公","create_time":"1576235692","money":"520","x_money":"7520"},{"id":2,"name":"赵寛","create_time":"1576235892","money":"1520","x_money":"12520"}]
     * page : {"page":1,"pageTotal":1,"pageSize":15,"dataTotal":2}
     * status : 1
     * info : 获取成功
     */

    private String n_amount;
    private PageBean page;
    private int status;
    private String info;
    private List<DataBean> data;

    public String getN_amount() {
        return n_amount;
    }

    public void setN_amount(String n_amount) {
        this.n_amount = n_amount;
    }

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
         * pageTotal : 1
         * pageSize : 15
         * dataTotal : 2
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
         * id : 1
         * name : 周公
         * create_time : 1576235692
         * money : 520
         * x_money : 7520
         */

        private int id;
        private String name;
        private String create_time;
        private String money;
        private String x_money;

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

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getX_money() {
            return x_money;
        }

        public void setX_money(String x_money) {
            this.x_money = x_money;
        }
    }
}
