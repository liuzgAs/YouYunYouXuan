package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class DDDesViewHolder extends BaseViewHolder<String> {

    private final TextView textTitle;

    public DDDesViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textTitle = $(R.id.textTitle);
    }

    @Override
    public void setData(String data) {
        super.setData(data);
        textTitle.setText(data);
    }
    
}
