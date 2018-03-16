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
import com.vip.uyux.interfacepage.OnShouCangListener;
import com.vip.uyux.model.IndexRecom;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class TuiJianViewHolder extends BaseViewHolder<IndexRecom.DataBean> {

    private final ImageView imageImg;
    private final TextView textTitle;
    private final TextView textDianZan;
    private final TextView textName;
    IndexRecom.DataBean data;
    private final ImageView imageDianZan;
    private OnShouCangListener onShouCangListener;

    public TuiJianViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textTitle = $(R.id.textTitle);
        textDianZan = $(R.id.textDianZan);
        textName = $(R.id.textName);
        imageDianZan = $(R.id.imageDianZan);
        $(R.id.viewDianZan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (data.getIsc() == 1) {
                    onShouCangListener.qXShouCang(getDataPosition());
                } else {
                    onShouCangListener.shouCang(getDataPosition());
                }
            }
        });
    }

    @Override
    public void setData(IndexRecom.DataBean data) {
        super.setData(data);
        this.data = data;
        GlideApp.with(getContext())
                .asBitmap()
                .centerCrop()
                .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(10, getContext())))
                .load(data.getImg())
                .into(imageImg);
        SpannableString span = new SpannableString("i " + data.getTitle());
        MyIm imgspan;
        if (data.getType() == 4) {
            imgspan = new MyIm(getContext(), R.mipmap.ceping);
            span.setSpan(imgspan, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        } else if (data.getType()==3) {
            imgspan = new MyIm(getContext(), R.mipmap.haowu);
            span.setSpan(imgspan, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        textTitle.setText(span);
        textDianZan.setText(String.valueOf(data.getCollectNum()));
        textName.setText(data.getNickname());
        if (data.getIsc() == 1) {
            imageDianZan.setImageResource(R.mipmap.dianzan_shixin);
        } else {
            imageDianZan.setImageResource(R.mipmap.dinazan);
        }
    }

    public void setOnShouCangListener(OnShouCangListener onShouCangListener) {
        this.onShouCangListener = onShouCangListener;
    }
}
