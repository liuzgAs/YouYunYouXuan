package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/2/7/007.
 *
 * @author ZhangJieBo
 */

public class BonusDirect {
    /**
     * direct_bonus : {"id":2,"title":"直推分红","des":"直推团队全年年收益分红，年终开放奖励提现！","is_up":1,"num1":0,"num2":0,"des1":"累计分红","des2":"团队总佣金"}
     * status : 1
     * info : 获取成功
     * data : [{"id":1011134,"grade":"优选达人","headimg":"http://wx.qlogo.cn/mmopen/Q3auHgzwzM4FDKwIuOpu2VdaACMibyaCcy44FcVxYdrNw0G6oSmUGNcibicdOFuVia8v39SgEmrkqMzaI6vhs2bk7w/132","nickname":"biu～","des":"累计收益：0"},{"id":1013053,"grade":"优选达人","headimg":"http://app.uyux.vip/Uploads/avstar.png","nickname":"Benz","des":"累计收益：0"},{"id":1013055,"grade":"优选达人","headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTITWhGqj6macCoVfdiaG7DD9Z2DtAym2VliaHicEEMfmUqZVt0z4djyMkBMaKPnXpjQnnZ5Y2pM91r0Q/132","nickname":"18250161297","des":"累计收益：0"},{"id":1011118,"grade":"优选大师","headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTISj8QhdS54r5I0SMic92qQib1Q2KlicZzpd4YVTibLMV0kgT46mxkHGQSJWvvpzjfQFumvXwuiaJWfs4A/132","nickname":"优云优选示范","des":"累计收益：0"},{"id":1011487,"grade":"优选专家","headimg":"http://wx.qlogo.cn/mmopen/Q3auHgzwzM7JvrRKZg7TlePTEDibJicqu1cOMaibibWN90IlibjtfqdZ76jw8DsTxtmzDlD1ZZzQHAAvspUiaaxpcwJTSunOlkV3Wbt4Yubsib96dQ/132","nickname":"陈诗玲","des":"累计收益：0"},{"id":1013044,"grade":"优选大师","headimg":"http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJicoVXZVQMvic47b7K69U8ia2ialhY8qeicao8nAJboDoajMQzgSPQdOd9ZJ34QCMicNc4pUjcX08iak9lw/132","nickname":"15871105320","des":"累计收益：0"}]
     */

    private DirectBonusBean direct_bonus;
    private int status;
    private String info;
    private List<DataBean> data;

    public DirectBonusBean getDirect_bonus() {
        return direct_bonus;
    }

    public void setDirect_bonus(DirectBonusBean direct_bonus) {
        this.direct_bonus = direct_bonus;
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

    public static class DirectBonusBean {
        /**
         * id : 2
         * title : 直推分红
         * des : 直推团队全年年收益分红，年终开放奖励提现！
         * is_up : 1
         * num1 : 0
         * num2 : 0
         * des1 : 累计分红
         * des2 : 团队总佣金
         */

        private int id;
        private String title;
        private String des;
        private int is_up;
        private String num1;
        private String num2;
        private String des1;
        private String des2;

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

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public int getIs_up() {
            return is_up;
        }

        public void setIs_up(int is_up) {
            this.is_up = is_up;
        }

        public String getNum1() {
            return num1;
        }

        public void setNum1(String num1) {
            this.num1 = num1;
        }

        public String getNum2() {
            return num2;
        }

        public void setNum2(String num2) {
            this.num2 = num2;
        }

        public String getDes1() {
            return des1;
        }

        public void setDes1(String des1) {
            this.des1 = des1;
        }

        public String getDes2() {
            return des2;
        }

        public void setDes2(String des2) {
            this.des2 = des2;
        }
    }

    public static class DataBean {
        /**
         * id : 1011134
         * grade : 优选达人
         * headimg : http://wx.qlogo.cn/mmopen/Q3auHgzwzM4FDKwIuOpu2VdaACMibyaCcy44FcVxYdrNw0G6oSmUGNcibicdOFuVia8v39SgEmrkqMzaI6vhs2bk7w/132
         * nickname : biu～
         * des : 累计收益：0
         */

        private String id;
        private String grade;
        private String headimg;
        private String nickname;
        private String des;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }
    }
}
