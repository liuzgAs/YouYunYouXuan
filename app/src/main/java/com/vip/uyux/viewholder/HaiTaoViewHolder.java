package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.customview.YuanJiaoImageView;
import com.vip.uyux.model.IndexSeaamoy;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class HaiTaoViewHolder extends BaseViewHolder<IndexSeaamoy.DataBean> {

    private final YuanJiaoImageView imageImg;
    private final ImageView imageBaoShui;
    private final TextView textTitle;
    private final TextView textDes;
    private final TextView textPrice;
    private final TextView textSale;

    public HaiTaoViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        imageBaoShui = $(R.id.imageBaoShui);
        textTitle = $(R.id.textTitle);
        textDes = $(R.id.textDes);
        textPrice = $(R.id.textPrice);
        textSale = $(R.id.textSale);
        float dp12 = DpUtils.convertDpToPixel(12, getContext());
        imageImg.setRids(new float[]{dp12, dp12, dp12, dp12, 0, 0, 0, 0});
    }

    @Override
    public void setData(IndexSeaamoy.DataBean data) {
        super.setData(data);
        GlideApp.with(getContext())
                .load(data.getImg())
                .centerCrop()
                .placeholder(R.mipmap.ic_empty)
                .transition(new DrawableTransitionOptions().crossFade(500))
                .into(imageImg);
        if (data.getTaxPackage()==1){
            imageBaoShui.setVisibility(View.VISIBLE);
        }else {
            imageBaoShui.setVisibility(View.INVISIBLE);
        }
        textTitle.setText(data.getTitle());
        textDes.setText(data.getDes());
        textPrice.setText(data.getPrice());
        textSale.setText("已售"+data.getSaleNum()+"件");
    }

}
