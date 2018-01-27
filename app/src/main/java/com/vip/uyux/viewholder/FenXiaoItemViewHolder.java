package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.BonusDistributionorder;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class FenXiaoItemViewHolder extends BaseViewHolder<BonusDistributionorder.DataBean.OrderGoodsBean> {

    private final TextView textTitle;
    private final TextView textPrice;
    private final ImageView imageImg;

    public FenXiaoItemViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textTitle = $(R.id.textTitle);
        textPrice = $(R.id.textPrice);
        imageImg = $(R.id.imageImg);
    }

    @Override
    public void setData(BonusDistributionorder.DataBean.OrderGoodsBean data) {
        super.setData(data);
        GlideApp.with(getContext())
                .asBitmap()
                .centerCrop()
                .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(10, getContext())))
                .load(data.getGoods_img())
                .into(imageImg);
        textTitle.setText(data.getGoods_title());
        textPrice.setText(data.getCreate_time());
    }

}
