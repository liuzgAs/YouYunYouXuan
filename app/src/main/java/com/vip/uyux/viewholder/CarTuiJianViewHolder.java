package com.vip.uyux.viewholder;

import android.graphics.Paint;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.customview.YuanJiaoImageView;
import com.vip.uyux.model.CartRecom;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class CarTuiJianViewHolder extends BaseViewHolder<CartRecom.DataBean> {

    private final YuanJiaoImageView imageImg;
    private final TextView textTitle;
    private final TextView textPrice;
    private final TextView textHuiYuan;
    private final TextView textDes;
    private final TextView textOld;
    private final TextView textSale;
    private final ImageView imageZiYin;
    int viewType;

    public CarTuiJianViewHolder(ViewGroup parent, @LayoutRes int res, int viewType) {
        super(parent, res);
        this.viewType=viewType;
        imageImg = $(R.id.imageImg);
        if (viewType==1){
            float dp12 = DpUtils.convertDpToPixel(12, getContext());
            imageImg.setRids(new float[]{dp12, dp12, dp12, dp12, 0, 0, 0, 0});
        }
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
    public void setData(CartRecom.DataBean data) {
        super.setData(data);
        if (viewType==1){
            GlideApp.with(getContext())
                    .load(data.getImg())
                    .centerCrop()
                    .placeholder(R.mipmap.ic_empty)
                    .into(imageImg);
        }else {
            GlideApp.with(getContext())
                    .asBitmap()
                    .centerCrop()
                    .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(10, getContext())))
                    .load(data.getImg())
                    .into(imageImg);
        }
        textTitle.setText(data.getTitle());
        textPrice.setText(data.getPrice());
        textOld.setText(data.getOldPrice());
        textHuiYuan.setText(data.getVipDes());
        textDes.setText(data.getDes());
        textSale.setText("已售" + data.getSaleNum() + "件");
    }

}
