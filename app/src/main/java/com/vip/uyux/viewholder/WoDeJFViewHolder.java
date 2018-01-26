package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.CustomerGetintegral;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class WoDeJFViewHolder extends BaseViewHolder<CustomerGetintegral.DataBean> {

    private final TextView textTitle;
    private final TextView textDes;

    public WoDeJFViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textTitle = $(R.id.textTitle);
        textDes = $(R.id.textDes);
    }

    @Override
    public void setData(CustomerGetintegral.DataBean data) {
        super.setData(data);
        textTitle.setText(data.getName());
        textDes.setText(data.getDes());
    }
    
}
