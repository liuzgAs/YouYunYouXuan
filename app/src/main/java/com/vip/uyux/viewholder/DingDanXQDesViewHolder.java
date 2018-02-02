package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class DingDanXQDesViewHolder extends BaseViewHolder<String> {

    private final TextView textDes;

    public DingDanXQDesViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textDes = $(R.id.textDes);
    }

    @Override
    public void setData(String data) {
        super.setData(data);
        textDes.setText(data);
    }
    
}
