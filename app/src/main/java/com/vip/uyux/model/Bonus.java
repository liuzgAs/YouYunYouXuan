package com.vip.uyux.model;

/**
 * Created by zhangjiebo on 2018/1/26/026.
 *
 * @author ZhangJieBo
 */

public class Bonus {
    /**
     * up_url : http://app.uyux.vip/mobile/apply/provider/app/1/uid/1011113/pid/1013055
     * url_title : 升级
     * img : http://app.uyux.vip/attachment/head/20180201/1011113_1517455406_1.png
     * name : 真实
     * grade_name : 优选大师
     * create_time : 2018-02-07
     * product_bonus : {"id":1,"title":"产品分红","is_up":1,"num1":0,"num2":0,"des1":"销售数量","des2":"总收益"}
     * direct_bonus : {"id":2,"title":"直推分红","is_up":1,"num1":0,"num2":0,"des1":"累计分红","des2":"团队总佣金"}
     * status : 1
     * info : 获取成功
     */

    private String up_url;
    private String url_title;
    private String img;
    private String name;
    private String grade_name;
    private String create_time;
    private ProductBonusBean product_bonus;
    private DirectBonusBean direct_bonus;
    private int status;
    private String info;
    private String tel;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUp_url() {
        return up_url;
    }

    public void setUp_url(String up_url) {
        this.up_url = up_url;
    }

    public String getUrl_title() {
        return url_title;
    }

    public void setUrl_title(String url_title) {
        this.url_title = url_title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade_name() {
        return grade_name;
    }

    public void setGrade_name(String grade_name) {
        this.grade_name = grade_name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public ProductBonusBean getProduct_bonus() {
        return product_bonus;
    }

    public void setProduct_bonus(ProductBonusBean product_bonus) {
        this.product_bonus = product_bonus;
    }

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

    public static class ProductBonusBean {
        /**
         * id : 1
         * title : 产品分红
         * is_up : 1
         * num1 : 0
         * num2 : 0
         * des1 : 销售数量
         * des2 : 总收益
         */

        private int id;
        private String title;
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

    public static class DirectBonusBean {
        /**
         * id : 2
         * title : 直推分红
         * is_up : 1
         * num1 : 0
         * num2 : 0
         * des1 : 累计分红
         * des2 : 团队总佣金
         */

        private int id;
        private String title;
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
}
