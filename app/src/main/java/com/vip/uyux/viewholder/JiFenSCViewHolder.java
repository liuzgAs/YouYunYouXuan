package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.CustomerGetintegralshop;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class JiFenSCViewHolder extends BaseViewHolder<CustomerGetintegralshop.DataBean> {

    private final ImageView imageImg;
    private final TextView textTitle;
    private final TextView textScore;
    private final TextView textUnit;

    public JiFenSCViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textTitle = $(R.id.textTitle);
        textScore = $(R.id.textScore);
        textUnit = $(R.id.textUnit);
    }

    @Override
    public void setData(CustomerGetintegralshop.DataBean data) {
        super.setData(data);
        GlideApp.with(getContext())
                .asBitmap()
                .centerCrop()
                .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(4, getContext())))
                .load(data.getThumb())
                .into(imageImg);
        textTitle.setText(data.getTitle());
        textScore.setText(String.valueOf(data.getIntegral()));
        textUnit.setText(data.getUnit());
    }

}
