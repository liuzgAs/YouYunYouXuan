package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/25/025.
 *
 * @author ZhangJieBo
 */

public class CustomerMyteam {
    /**
     * total_num : 12
     * title : [{"id":1,"title":"亲友(2)"},{"id":2,"title":"好友(2)"},{"id":3,"title":"朋友(2)"}]
     * data : [{"id":1,"name":"李丹尼","create_time":"1576235692","member":"5","money":123562},{"id":2,"name":"孙李","create_time":"1576235892","member":"7","money":85986}]
     * page : {"page":1,"pageTotal":1,"pageSize":15,"dataTotal":2}
     * status : 1
     * info : 获取成功
     */

    private int total_num;
    private PageBean page;
    private int status;
    private String info;
    private List<TitleBean> title;
    private List<DataBean> data;

    public int getTotal_num() {
        return total_num;
    }

    public void setTotal_num(int total_num) {
        this.total_num = total_num;
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

    public List<TitleBean> getTitle() {
        return title;
    }

    public void setTitle(List<TitleBean> title) {
        this.title = title;
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

    public static class TitleBean {
        /**
         * id : 1
         * title : 亲友(2)
         */

        private int id;
        private String title;

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
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 李丹尼
         * create_time : 1576235692
         * member : 5
         * money : 123562
         */

        private int id;
        private String name;
        private String create_time;
        private String member;
        private String headimg;
        private int money;

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
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

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getMember() {
            return member;
        }

        public void setMember(String member) {
            this.member = member;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }
    }
}
