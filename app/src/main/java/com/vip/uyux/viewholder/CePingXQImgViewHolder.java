package com.vip.uyux.viewholder;

import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.EvaluationInfo;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class CePingXQImgViewHolder extends BaseViewHolder<EvaluationInfo.ImgsBean> {

    private final TextView textContent;
    private final ImageView imageImg;

    public CePingXQImgViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textContent = $(R.id.textContent);
        imageImg = $(R.id.imageImg);
    }

    @Override
    public void setData(EvaluationInfo.ImgsBean data) {
        super.setData(data);
        if (!TextUtils.isEmpty(data.getImg_url())) {
            imageImg.setVisibility(View.VISIBLE);
            GlideApp.with(getContext())
                    .load(data.getImg_url())
                    .centerCrop()
                    .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(10, getContext())))
                    .placeholder(R.mipmap.ic_empty)
                    .into(new SimpleTarget<Drawable>(data.getImg_w(), data.getImg_h()) {
                        @Override
                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                            imageImg.setImageDrawable(resource);
                        }
                    });
        } else {
            imageImg.setVisibility(View.GONE);
        }

        textContent.setText(data.getContent());
    }

}
