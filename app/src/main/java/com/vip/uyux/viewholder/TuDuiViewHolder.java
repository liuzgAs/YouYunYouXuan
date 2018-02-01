package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.CustomerMyteam;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class TuDuiViewHolder extends BaseViewHolder<CustomerMyteam.DataBean> {

    private final ImageView imageImg;
    private final TextView textName;
    private final TextView textDes;
    private final TextView textDate;

    public TuDuiViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textName = $(R.id.textName);
        textDes = $(R.id.textDes);
        textDate = $(R.id.textDate);
    }

    @Override
    public void setData(CustomerMyteam.DataBean data) {
        super.setData(data);
        GlideApp.with(getContext())
                .load(data.getHeadimg())
                .centerCrop()
                .placeholder(R.mipmap.ic_empty)
                .circleCrop()
                .into(imageImg);
        textName.setText(data.getName());
        textDes.setText("佣金：+" +data.getMoney()+"\u3000\u3000"+"成员数："+data.getMember());
        textDate.setText(data.getCreate_time());
    }
    
}
