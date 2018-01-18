package com.vip.uyux.viewholder;

import android.graphics.Paint;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class IndexItemViewHolder extends BaseViewHolder<Integer> {

    private final TextView textCutPrice;

    public IndexItemViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textCutPrice = $(R.id.textCutPrice);
    }

    @Override
    public void setData(Integer data) {
        super.setData(data);
        textCutPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
    }
    
}
