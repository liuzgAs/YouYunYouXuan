package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class WoDeCPViewHolder extends BaseViewHolder<Integer> {

    private final ImageView imageImg;

    public WoDeCPViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
    }

    @Override
    public void setData(Integer data) {
        super.setData(data);
        GlideApp.with(getContext())
                .asBitmap()
                .centerCrop()
                .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(10, getContext())))
                .load(R.mipmap.tuijianimg)
                .into(imageImg);
    }

}
