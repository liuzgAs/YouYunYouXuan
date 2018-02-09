package com.vip.uyux.model;

/**
 * Created by zhangjiebo on 2018/2/9/009.
 *
 * @author ZhangJieBo
 */

public class TuiJianSP {
    private BonusSuperioritybefore bonusSuperioritybefore;
    private int viewType;

    public TuiJianSP(BonusSuperioritybefore bonusSuperioritybefore, int viewType) {
        this.bonusSuperioritybefore = bonusSuperioritybefore;
        this.viewType = viewType;
    }

    public BonusSuperioritybefore getBonusSuperioritybefore() {
        return bonusSuperioritybefore;
    }

    public void setBonusSuperioritybefore(BonusSuperioritybefore bonusSuperioritybefore) {
        this.bonusSuperioritybefore = bonusSuperioritybefore;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
