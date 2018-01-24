package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.model.GoodsViewlog;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class ZuJiEmptyViewHolder extends BaseViewHolder<GoodsViewlog.DataBean> {

    public ZuJiEmptyViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
    }

    @Override
    public void setData(GoodsViewlog.DataBean data) {
        super.setData(data);
    }
    
}
