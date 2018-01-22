package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/22/022.
 *
 * @author ZhangJieBo
 */

public class GoodsSearch {
    /**
     * viewLog : []
     * hot : [{"name":"睫毛膏"},{"name":"核桃肉"},{"name":"桂圆肉"},{"name":"茅台"},{"name":"动宝酒"},{"name":"火龙果"},{"name":"充电宝"},{"name":"香水"},{"name":"欧瑞莲化妆品"},{"name":"电磁炉"}]
     * status : 1
     * info : 返回成功！
     */

    private int status;
    private String info;
    private List<HotBean> viewLog;
    private List<HotBean> hot;

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

    public List<HotBean> getViewLog() {
        return viewLog;
    }

    public void setViewLog(List<HotBean> viewLog) {
        this.viewLog = viewLog;
    }

    public List<HotBean> getHot() {
        return hot;
    }

    public void setHot(List<HotBean> hot) {
        this.hot = hot;
    }

    public static class HotBean {
        /**
         * name : 睫毛膏
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
