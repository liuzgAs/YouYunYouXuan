package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.BankCardlist;
import com.vip.uyux.util.GlideApp;


/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class XuanZeXYKViewHolder extends BaseViewHolder<BankCardlist.DataBean> {


    private final ImageView imageImg;
    private final TextView textBankName;
    private final TextView textBankCard;
    private final TextView textName;

    public XuanZeXYKViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textBankName = $(R.id.textBankName);
        textBankCard = $(R.id.textBankCard);
        textName = $(R.id.textName);
    }

    @Override
    public void setData(BankCardlist.DataBean data) {
        super.setData(data);
        GlideApp.with(getContext())
                .asBitmap()
                .load(data.getImg())
                .placeholder(R.mipmap.ic_empty)
                .into(imageImg);
        textBankName.setText(data.getName());
        textBankCard.setText("**** **** **** "+data.getBankCard());
        textName.setText(data.getName());
    }
    
}
