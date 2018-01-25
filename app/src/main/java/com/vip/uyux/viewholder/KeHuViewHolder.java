package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.Customer;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class KeHuViewHolder extends BaseViewHolder<Customer.DataBean> {

    private final ImageView imageImg;
    private final TextView textName;
    private final TextView textDes;

    public KeHuViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textName = $(R.id.textName);
        textDes = $(R.id.textDes);
    }

    @Override
    public void setData(Customer.DataBean data) {
        super.setData(data);
        GlideApp.with(getContext())
                .load(data.getHeadimg())
                .centerCrop()
                .placeholder(R.mipmap.ic_empty)
                .circleCrop()
                .into(imageImg);
        textName.setText(data.getName());

        textDes.setText("绑定有效天数："+data.getBand()+"\u3000\u3000"+"累计消费：¥"+data.getLmoney());
    }
    
}
