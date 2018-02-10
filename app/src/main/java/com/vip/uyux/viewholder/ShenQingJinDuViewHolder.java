package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.BonusSuperioritylogs;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class ShenQingJinDuViewHolder extends BaseViewHolder<BonusSuperioritylogs.DataBean> {

    private final ImageView imageStatue;
    private final ImageView imageImg;
    private final TextView textState_des;
    private final TextView textName;
    private final TextView textPrice;

    public ShenQingJinDuViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageStatue = $(R.id.imageStatue);
        imageImg = $(R.id.imageImg);
        textState_des = $(R.id.textState_des);
        textName = $(R.id.textName);
        textPrice = $(R.id.textPrice);
    }

    @Override
    public void setData(BonusSuperioritylogs.DataBean data) {
        super.setData(data);
        switch (data.getState()) {
            case 0:
                imageStatue.setImageResource(R.mipmap.shengqingjindu1);
                break;
            case 1:
                imageStatue.setImageResource(R.mipmap.shengqingjindu3);
                break;
            case 2:
                imageStatue.setImageResource(R.mipmap.shengqingjindu2);
                break;
            default:
                break;
        }
        GlideApp.with(getContext())
                .asBitmap()
                .centerCrop()
                .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(8, getContext())))
                .load(data.getImg())
                .into(imageImg);
        textState_des.setText(data.getState_des());
        textName.setText(data.getName());
        textPrice.setText("¥"+data.getPrice());
    }

}
