package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.customview.MyIm;
import com.vip.uyux.model.GoodBean;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class TuiJianViewHolder extends BaseViewHolder<GoodBean> {

    private final ImageView imageImg;
    private final ImageView imageDianZan;
    private final TextView textTitle;
    private final TextView textPrice;
    private final TextView textDianZan;
    private final TextView textName;
    int viewType;

    public TuiJianViewHolder(ViewGroup parent, @LayoutRes int res, int viewType) {
        super(parent, res);
        this.viewType=viewType;
        imageImg = $(R.id.imageImg);
        imageDianZan = $(R.id.imageDianZan);
        textTitle = $(R.id.textTitle);
        textPrice = $(R.id.textPrice);
        textDianZan = $(R.id.textDianZan);
        textName = $(R.id.textName);

    }

    @Override
    public void setData(GoodBean data) {
        super.setData(data);
        GlideApp.with(getContext())
                .asBitmap()
                .centerCrop()
                .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(10,getContext())))
                .load(data.getImg())
                .into(imageImg);
        SpannableString span = new SpannableString("i " + data.getTitle());
        MyIm imgspan;
        switch (viewType) {
            case 1:
                textPrice.setVisibility(View.VISIBLE);
                imageDianZan.setVisibility(View.GONE);
                textDianZan.setVisibility(View.GONE);
                imgspan = new MyIm(getContext(), R.mipmap.haowu);
                break;
            default:
                imageDianZan.setVisibility(View.VISIBLE);
                textDianZan.setVisibility(View.VISIBLE);
                textPrice.setVisibility(View.GONE);
                imgspan = new MyIm(getContext(), R.mipmap.ceping);

                break;
        }
        span.setSpan(imgspan, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        textTitle.setText(span);
        textPrice.setText(data.getPrice());
        textName.setVisibility(View.GONE);
    }

}
