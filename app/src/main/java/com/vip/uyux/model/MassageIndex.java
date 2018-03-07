package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/3/7/007.
 *
 * @author ZhangJieBo
 */

public class MassageIndex {
    /**
     * data : [{"title":"系统通知","des":"系统通知消息","num":0},{"title":"优选活动","des":"优选活动消息","num":2},{"title":"物流助手","des":"物流助手消息","num":3},{"title":"客服消息","des":"最新客服消息","num":0},{"title":"佣金消息","des":"最新佣金消息","num":3}]
     * status : 1
     * info : 返回成功！
     */

    private int status;
    private String info;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title : 系统通知
         * des : 系统通知消息
         * num : 0
         */

        private String title;
        private String des;
        private int num;

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

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
