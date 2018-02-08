package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.interfacepage.OnNotifyItemChangeListener;
import com.vip.uyux.model.CustomerMyteam;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class TuDuiViewHolder extends BaseViewHolder<CustomerMyteam.DataBean> {

    private final ImageView imageImg;
    private final TextView textName;
    private final TextView textDate;
    private final TextView textMoney;
    private final TextView textMobile;
    private final TextView textMember;
    private final TextView textReal_name;
    private final ImageView imageJiaJian;
    CustomerMyteam.DataBean data;
    private final View viewBtm;
    private OnNotifyItemChangeListener onNotifyItemChangeListener;

    public TuDuiViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textName = $(R.id.textName);
        textDate = $(R.id.textDate);
        textMoney = $(R.id.textMoney);
        textMobile = $(R.id.textMobile);
        textMember = $(R.id.textMember);
        textReal_name = $(R.id.textReal_name);
        imageJiaJian = $(R.id.imageJiaJian);
        imageJiaJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (data.isZhanKai()){
                    data.setZhanKai(false);
                }else {
                    data.setZhanKai(true);
                }
                onNotifyItemChangeListener.notify(getDataPosition());
            }
        });
        viewBtm = $(R.id.viewBtm);
    }

    @Override
    public void setData(CustomerMyteam.DataBean data) {
        super.setData(data);
        this.data=data;
        GlideApp.with(getContext())
                .load(data.getHeadimg())
                .centerCrop()
                .placeholder(R.mipmap.ic_empty)
                .circleCrop()
                .into(imageImg);
        textName.setText(data.getName());
        textDate.setText(data.getCreate_time());
        textMoney.setText("+"+data.getMoney());
        textMember.setText(String.valueOf(data.getMember()));
        textReal_name.setText("("+data.getReal_name()+")");
        textMobile.setText(data.getMobile());
        if (data.isZhanKai()){
            imageJiaJian.setImageResource(R.mipmap.jian);
            viewBtm.setVisibility(View.VISIBLE);
        }else {
            imageJiaJian.setImageResource(R.mipmap.jia);
            viewBtm.setVisibility(View.GONE);
        }
    }

    public void setOnNotifyItemChangeListener(OnNotifyItemChangeListener onNotifyItemChangeListener) {
        this.onNotifyItemChangeListener = onNotifyItemChangeListener;
    }
}
