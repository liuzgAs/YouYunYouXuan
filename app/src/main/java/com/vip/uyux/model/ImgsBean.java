package com.vip.uyux.model;

public class ImgsBean {
    /**
     * thumb : http://app.uyux.vip/attachment/images/1604/2017/12/I3G434jXcj0Xz00m934XxxZeuiy4QU.jpg
     * img : http://app.uyux.vip/attachment/images/1604/2017/12/I3G434jXcj0Xz00m934XxxZeuiy4QU.jpg
     */

    private String thumb;
    private String img;
    private int width;
    private int heigth;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}