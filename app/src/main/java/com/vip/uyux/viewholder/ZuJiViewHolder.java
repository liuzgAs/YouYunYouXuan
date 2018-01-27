package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.GoodsViewlog;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class ZuJiViewHolder extends BaseViewHolder<GoodsViewlog.DataBean> {

    private final ImageView imageXuanZhong;
    private final ImageView imageImg;
    private final TextView textTitle;

    public ZuJiViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageXuanZhong = $(R.id.imageXuanZhong);
        imageImg = $(R.id.imageImg);
        textTitle = $(R.id.textTitle);
    }

    @Override
    public void setData(GoodsViewlog.DataBean data) {
        super.setData(data);
        if (data.isBianJi()) {
            imageXuanZhong.setVisibility(View.VISIBLE);
            if (data.isSelect()) {
                imageXuanZhong.setImageResource(R.mipmap.xuanzhong);
            } else {
                imageXuanZhong.setImageResource(R.mipmap.weixuanzhong);
            }
        } else {
            imageXuanZhong.setVisibility(View.GONE);
        }
        GlideApp.with(getContext())
                .asBitmap()
                .centerCrop()
                .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(10, getContext())))
                .load(data.getImg())
                .into(imageImg);
        textTitle.setText(data.getTitle());
    }

}
