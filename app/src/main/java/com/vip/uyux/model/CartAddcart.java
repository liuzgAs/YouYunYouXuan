package com.vip.uyux.model;

/**
 * Created by zhangjiebo on 2018/1/22/022.
 *
 * @author ZhangJieBo
 */

public class CartAddcart {
    /**
     * cartId : 3
     * status : 1
     * info : 返回成功！
     */

    private String cartId;
    private int status;
    private String info;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
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
}
