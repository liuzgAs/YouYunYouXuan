package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class ChanPinFootViewHolder extends BaseViewHolder<Integer> {

    private final ImageView imageImg;

    public ChanPinFootViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
    }

    @Override
    public void setData(Integer data) {
        super.setData(data);
        GlideApp.with(getContext())
                .asBitmap()
                .load(data)
                .placeholder(R.mipmap.ic_empty)
                .into(imageImg);
    }
    
}
