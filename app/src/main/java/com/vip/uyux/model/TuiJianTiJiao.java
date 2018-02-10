package com.vip.uyux.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/2/10/010.
 *
 * @author ZhangJieBo
 */

public class TuiJianTiJiao {
    private int loginType;
    private String platform;
    private String uid;
    private String tokenTime;
    private String name;
    private String sn;
    private String brand;
    private String price;
    private String manufacturer;
    private String nature;
    private String intro;
    private List<Integer> high_quality;
    private List<Integer> imgs;
    private List<Integer> imgs1;
    private List<Integer> imgs2;

    public TuiJianTiJiao(int loginType, String platform, String uid, String tokenTime, String name, String sn, String brand, String price, String manufacturer, String nature, String intro, List<Integer> high_quality, List<Integer> imgs, List<Integer> imgs1, List<Integer> imgs2) {
        this.loginType = loginType;
        this.platform = platform;
        this.uid = uid;
        this.tokenTime = tokenTime;
        this.name = name;
        this.sn = sn;
        this.brand = brand;
        this.price = price;
        this.manufacturer = manufacturer;
        this.nature = nature;
        this.intro = intro;
        this.high_quality = high_quality;
        this.imgs = imgs;
        this.imgs1 = imgs1;
        this.imgs2 = imgs2;
    }
}
