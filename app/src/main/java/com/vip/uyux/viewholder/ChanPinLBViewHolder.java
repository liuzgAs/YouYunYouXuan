package com.vip.uyux.viewholder;

import android.graphics.Paint;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.GoodsIndex;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class ChanPinLBViewHolder extends BaseViewHolder<GoodsIndex.DataBean> {

    private final ImageView imageImg;
    private final TextView textTitle;
    private final TextView textPrice;
    private final TextView textHuiYuan;
    private final TextView textDes;
    private final TextView textOld;
    private final TextView textSale;
    private final ImageView imageZiYin;

    public ChanPinLBViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textTitle = $(R.id.textTitle);
        textPrice = $(R.id.textPrice);
        textHuiYuan = $(R.id.textHuiYuan);
        textDes = $(R.id.textDes);
        textOld = $(R.id.textOld);
        textOld.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
        textSale = $(R.id.textSale);
        imageZiYin = $(R.id.imageZiYin);
    }

    @Override
    public void setData(GoodsIndex.DataBean data) {
        super.setData(data);
        GlideApp.with(getContext())
                .asBitmap()
                .centerCrop()
                .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(10, getContext())))
                .load(data.getImg())
                .into(imageImg);
        textTitle.setText(data.getTitle());
        textPrice.setText(data.getPrice());
        textOld.setText(data.getOldPrice());
        textHuiYuan.setText(data.getVipDes());
        textDes.setText(data.getDes());
        textSale.setText("已售"+data.getSaleNum()+"件");
    }

}
