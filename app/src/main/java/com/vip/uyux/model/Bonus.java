package com.vip.uyux.model;

/**
 * Created by zhangjiebo on 2018/1/26/026.
 *
 * @author ZhangJieBo
 */

public class Bonus {
    /**
     * img :
     * name : 胡安
     * grade_name : 大师
     * create_time : 2018-01-25
     * product_bonus : {"id":1,"title":"产品分红","total_money":"155","y_money":"15767"}
     * direct_bonus : {"id":2,"title":"直推分红","l_money":"147622","z_money":"1579"}
     * status : 1
     * info : 获取成功
     */

    private String img;
    private String name;
    private String grade_name;
    private String create_time;
    private ProductBonusBean product_bonus;
    private DirectBonusBean direct_bonus;
    private int status;
    private String info;

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
         * total_money : 155
         * y_money : 15767
         */

        private int id;
        private String title;
        private String total_money;
        private String y_money;
        private int is_up;

        public int getIs_up() {
            return is_up;
        }

        public void setIs_up(int is_up) {
            this.is_up = is_up;
        }

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

        public String getTotal_money() {
            return total_money;
        }

        public void setTotal_money(String total_money) {
            this.total_money = total_money;
        }

        public String getY_money() {
            return y_money;
        }

        public void setY_money(String y_money) {
            this.y_money = y_money;
        }
    }

    public static class DirectBonusBean {
        /**
         * id : 2
         * title : 直推分红
         * l_money : 147622
         * z_money : 1579
         */

        private int id;
        private String title;
        private String l_money;
        private String z_money;
        private int is_up;

        public int getIs_up() {
            return is_up;
        }

        public void setIs_up(int is_up) {
            this.is_up = is_up;
        }

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

        public String getL_money() {
            return l_money;
        }

        public void setL_money(String l_money) {
            this.l_money = l_money;
        }

        public String getZ_money() {
            return z_money;
        }

        public void setZ_money(String z_money) {
            this.z_money = z_money;
        }
    }
}
