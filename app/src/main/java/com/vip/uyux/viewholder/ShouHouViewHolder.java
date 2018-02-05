package com.vip.uyux.viewholder;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.activity.ShenQingSHActivity;
import com.vip.uyux.constant.Constant;
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
    Afters.DataBean data;

    public ShouHouViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textGoods_name = $(R.id.textGoods_name);
        textQuantity = $(R.id.textQuantity);
        $(R.id.textShenQing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getContext(), ShenQingSHActivity.class);
                intent.putExtra(Constant.IntentKey.ID, data.getId());
                getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void setData(Afters.DataBean data) {
        super.setData(data);
        this.data = data;
        GlideApp.with(getContext())
                .asBitmap()
                .centerCrop()
                .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(10, getContext())))
                .load(data.getImg())
                .into(imageImg);
        textGoods_name.setText(data.getGoods_name());
        textQuantity.setText("×" + data.getQuantity());
    }

}
