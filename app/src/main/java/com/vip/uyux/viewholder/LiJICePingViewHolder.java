package com.vip.uyux.viewholder;

import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.luck.picture.lib.entity.LocalMedia;
import com.vip.uyux.R;
import com.vip.uyux.interfacepage.OnAddPictureListener;
import com.vip.uyux.util.GlideApp;
import com.vip.uyux.util.ImageWidthHeight;
import com.vip.uyux.util.LogUtil;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class LiJICePingViewHolder extends BaseViewHolder<LocalMedia> {

    private final ImageView imageImg;
    private OnAddPictureListener onAddPictureListener;

    public LiJICePingViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        imageImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddPictureListener.addPicture(getDataPosition());
            }
        });
    }

    @Override
    public void setData(LocalMedia data) {
        super.setData(data);
        LogUtil.LogShitou("LiJICePingViewHolder--setData", data.getCompressPath());
        ImageWidthHeight.WidthHeight imgWidthHeigth = ImageWidthHeight.getImgWidthHeigth(data.getCompressPath());
        GlideApp.with(getContext())
                .load(data.getCompressPath())
                .centerCrop()
                .placeholder(R.mipmap.ic_empty)
                .into(new SimpleTarget<Drawable>(imgWidthHeigth.getWidth(), imgWidthHeigth.getHeigth()) {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        imageImg.setImageDrawable(resource);
                    }
                });
    }

    public void setOnAddPictureListener(OnAddPictureListener onAddPictureListener) {
        this.onAddPictureListener = onAddPictureListener;
    }
}
