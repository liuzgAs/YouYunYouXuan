package com.vip.uyux.viewholder;

import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.GoodsInfo;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class ChanPinFootViewHolder extends BaseViewHolder<GoodsInfo.DataBean.ImgsBean> {

    private final ImageView imageImg;

    public ChanPinFootViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
    }

    @Override
    public void setData(final GoodsInfo.DataBean.ImgsBean data) {
        super.setData(data);
        GlideApp.with(getContext())
                .asBitmap()
                .load(data.getImg())
                .placeholder(R.mipmap.none_data)
                .dontAnimate()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        imageImg.setImageBitmap(resource);
                    }
                });
    }

}
