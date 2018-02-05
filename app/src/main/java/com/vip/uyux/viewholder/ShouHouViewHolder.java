package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.Afters;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class ShouHouViewHolder extends BaseViewHolder<Afters.DataBean> {

    private final ImageView imageImg;
    private final TextView textGoods_name;
    private final TextView textQuantity;

    public ShouHouViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textGoods_name = $(R.id.textGoods_name);
        textQuantity = $(R.id.textQuantity);
    }

    @Override
    public void setData(Afters.DataBean data) {
        super.setData(data);
        GlideApp.with(getContext())
                .asBitmap()
                .centerCrop()
                .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(10, getContext())))
                .load(data.getImg())
                .into(imageImg);
        textGoods_name.setText(data.getGoods_name());
        textQuantity.setText("×"+data.getQuantity());
    }

}
