package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.model.TuiJianSP;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class TuiJianShangPinViewHolder03 extends BaseViewHolder<TuiJianSP> {
    TuiJianSP data;

    public TuiJianShangPinViewHolder03(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
    }

    @Override
    public void setData(TuiJianSP data) {
        super.setData(data);
        this.data=data;

    }
}
