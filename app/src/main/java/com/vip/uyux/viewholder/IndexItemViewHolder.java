package com.vip.uyux.viewholder;

import android.graphics.Paint;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.customview.YuanJiaoImageView;
import com.vip.uyux.model.IndexHome;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class IndexItemViewHolder extends BaseViewHolder<IndexHome.DataBean.GoodsBean> {

    private final TextView textCutPrice;
    private final YuanJiaoImageView imageImg;
    private final TextView textVipDes;
    private final ImageView imageZiYin;
    private final TextView textTitle;
    private final TextView textDes;
    private final TextView textPrice;
    private final TextView textSaleNum;

    public IndexItemViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textCutPrice = $(R.id.textCutPrice);
        textVipDes = $(R.id.textVipDes);
        imageImg = $(R.id.imageImg);
        imageZiYin = $(R.id.imageZiYin);
        textTitle = $(R.id.textTitle);
        textDes = $(R.id.textDes);
        textPrice = $(R.id.textPrice);
        textSaleNum = $(R.id.textSaleNum);
        float dp12 = DpUtils.convertDpToPixel(12, getContext());
        imageImg.setRids(new float[]{dp12, dp12, dp12, dp12, 0, 0, 0, 0});
        textCutPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void setData(IndexHome.DataBean.GoodsBean data) {
        super.setData(data);
        GlideApp.with(getContext())
                .load(data.getImg())
                .centerCrop()
                .placeholder(R.mipmap.ic_empty)
                .into(imageImg);
        textCutPrice.setText(data.getOldPrice());
        textVipDes.setText(data.getVipDes());
        ViewGroup.LayoutParams layoutParams = imageZiYin.getLayoutParams();
        imageZiYin.setVisibility(View.VISIBLE);
        switch (data.getType()) {
            case 1:
                layoutParams.width= (int)DpUtils.convertDpToPixel(30,getContext());
                layoutParams.height= (int)DpUtils.convertDpToPixel(20,getContext());
                imageZiYin.setLayoutParams(layoutParams);
                imageZiYin.setImageResource(R.mipmap.ziyin);
                break;
            case 2:
                layoutParams.width= (int)DpUtils.convertDpToPixel(50,getContext());
                layoutParams.height= (int)DpUtils.convertDpToPixel(20,getContext());
                imageZiYin.setLayoutParams(layoutParams);
                imageZiYin.setImageResource(R.mipmap.ziyinpinpai);
                break;
            case 3:
                layoutParams.width= (int)DpUtils.convertDpToPixel(30,getContext());
                layoutParams.height= (int)DpUtils.convertDpToPixel(20,getContext());
                imageZiYin.setLayoutParams(layoutParams);
                imageZiYin.setImageResource(R.mipmap.haowu);
                break;
            case 4:
                layoutParams.width= (int)DpUtils.convertDpToPixel(30,getContext());
                layoutParams.height= (int)DpUtils.convertDpToPixel(20,getContext());
                imageZiYin.setLayoutParams(layoutParams);
                imageZiYin.setImageResource(R.mipmap.ceping);
                break;
            default:
                imageZiYin.setVisibility(View.GONE);
                break;
        }
        textTitle.setText(data.getTitle());
        textDes.setText(data.getDes());
        textPrice.setText(data.getPrice());
        textSaleNum.setText("已售" + data.getSaleNum());
    }

}
