package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.Order;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class DDItemViewHolder extends BaseViewHolder<Order.DataBean.ListBeanX.ListBean> {

    private final ImageView imageImg;
    private final TextView textTitle;
    private final TextView textDes;
    private final TextView textPrice;
    private final TextView textNum;

    public DDItemViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textTitle = $(R.id.textTitle);
        textDes = $(R.id.textDes);
        textPrice = $(R.id.textPrice);
        textNum = $(R.id.textNum);
    }

    @Override
    public void setData(Order.DataBean.ListBeanX.ListBean data) {
        super.setData(data);
        GlideApp.with(getContext())
                .asBitmap()
                .centerCrop()
                .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(10, getContext())))
                .load(data.getGoods_img())
                .into(imageImg);
        textTitle.setText(data.getGoods_name());
        textDes.setText(data.getGoods_sku());
        textNum.setText("×"+data.getQuantity());
        textPrice.setText("¥"+data.getGoods_price());
    }

}
