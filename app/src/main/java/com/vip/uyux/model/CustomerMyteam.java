package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/25/025.
 *
 * @author ZhangJieBo
 */

public class CustomerMyteam {
    /**
     * total_num : 2
     * title : [{"id":1,"title":"直推(2)"},{"id":2,"title":"间推(1)"},{"id":3,"title":"衍推(0)"}]
     * data : [{"id":1013055,"create_time":"2018.02.01","real_name":"暂未实名","name":"Benz","grade":"优选专家","mobile":"18250161297","headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTITWhGqj6macCoVfdiaG7DD9Z2DtAym2VliaHicEEMfmUqZVt0z4djyMkBMaKPnXpjQnnZ5Y2pM91r0Q/132","member":1,"money":489.88},{"id":1011134,"create_time":"2017.12.30","real_name":"biu～","name":"biu~  ","grade":"优选达人","mobile":"18359260160","headimg":"http://wx.qlogo.cn/mmopen/Q3auHgzwzM4FDKwIuOpu2VdaACMibyaCcy44FcVxYdrNw0G6oSmUGNcibicdOFuVia8v39SgEmrkqMzaI6vhs2bk7w/132","member":0,"money":0}]
     * page : {"page":1,"pageTotal":1,"pageSize":10,"dataTotal":2}
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
         * pageSize : 10
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
         * title : 直推(2)
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
         * id : 1013055
         * create_time : 2018.02.01
         * real_name : 暂未实名
         * name : Benz
         * grade : 优选专家
         * mobile : 18250161297
         * headimg : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTITWhGqj6macCoVfdiaG7DD9Z2DtAym2VliaHicEEMfmUqZVt0z4djyMkBMaKPnXpjQnnZ5Y2pM91r0Q/132
         * member : 1
         * money : 489.88
         */

        private int id;
        private String create_time;
        private String real_name;
        private String name;
        private String grade;
        private String mobile;
        private String headimg;
        private int member;
        private String money;
        private boolean isZhanKai;

        public boolean isZhanKai() {
            return isZhanKai;
        }

        public void setZhanKai(boolean zhanKai) {
            isZhanKai = zhanKai;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public int getMember() {
            return member;
        }

        public void setMember(int member) {
            this.member = member;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
