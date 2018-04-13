package com.vip.uyux.viewholder;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.ImgsBean;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class ChanPinFootViewHolder extends BaseViewHolder<ImgsBean> {

    private final ImageView imageImg;

    public ChanPinFootViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
    }

    @Override
    public void setData(final ImgsBean data) {
        super.setData(data);
        if (data.getWidth()!=0&&data.getHeigth()!=0){
            GlideApp.with(getContext())
                    .asBitmap()
                    .load(data.getImg())
                    .thumbnail( 0.6f )
                    .into(new SimpleTarget<Bitmap>(data.getWidth(),data.getHeigth()) {
                        @Override
                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                            imageImg.setImageBitmap(resource);
                        }

                        @Override
                        public void onLoadFailed(@Nullable Drawable errorDrawable) {
                            super.onLoadFailed(errorDrawable);
                            imageImg.setImageResource(R.mipmap.ic_empty_h);
                        }
                    });
        }else {
            GlideApp.with(getContext())
                    .asBitmap()
                    .load(data.getImg())
                    .thumbnail( 0.6f )
                    .placeholder(R.mipmap.ic_empty_h)
                    .dontAnimate()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                            int width = resource.getWidth();
                            int height = resource.getHeight();
                            data.setWidth(width);
                            data.setHeigth(height);
                            imageImg.setImageBitmap(resource);
                        }

                        @Override
                        public void onLoadFailed(@Nullable Drawable errorDrawable) {
                            super.onLoadFailed(errorDrawable);
                            imageImg.setImageResource(R.mipmap.ic_empty_h);
                        }
                    });
        }
    }

}
