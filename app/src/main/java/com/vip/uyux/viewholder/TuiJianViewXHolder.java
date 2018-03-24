package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.IndexRecom;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class TuiJianViewXHolder extends BaseViewHolder<IndexRecom.DataBean> {

    private final ImageView imageImg;
    private final TextView textTitle;
    private final TextView textName;
    IndexRecom.DataBean data;
    private final TextView textDes;

    public TuiJianViewXHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textTitle = $(R.id.textTitle);
        textName = $(R.id.textName);
        textDes = $(R.id.textDes);
    }

    @Override
    public void setData(IndexRecom.DataBean data) {
        super.setData(data);
        this.data = data;
        GlideApp.with(getContext())
                .asBitmap()
                .centerCrop()
                .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(12, getContext())))
                .load(data.getImg())
                .into(imageImg);
        textTitle.setText(data.getTitle());
        textName.setText(data.getNickname());
        textDes.setText(data.getDes());
    }

}
