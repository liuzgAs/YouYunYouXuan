package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.BonusExchangerecode;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class DuiHuanJLViewHolder extends BaseViewHolder<BonusExchangerecode.ProductIntegralBean> {

    private final ImageView imageImg;
    private final TextView textTitle;
    private final TextView textJiFen;
    private final TextView textDate;
    private final TextView textNum;

    public DuiHuanJLViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textTitle = $(R.id.textTitle);
        textJiFen = $(R.id.textJiFen);
        textDate = $(R.id.textDate);
        textNum = $(R.id.textNum);
    }

    @Override
    public void setData(BonusExchangerecode.ProductIntegralBean data) {
        super.setData(data);
        GlideApp.with(getContext())
                .asBitmap()
                .centerCrop()
                .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(10, getContext())))
                .load(data.getImg())
                .into(imageImg);
        textTitle.setText(data.getGoods_title());
        textJiFen.setText(data.getIntegral()+"积分");
        textDate.setText(data.getCreate_time());
        textNum.setText("×"+data.getNum());
    }

}
