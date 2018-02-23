package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.MassageWuliu;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class WuLiuZSViewHolder extends BaseViewHolder<MassageWuliu.DataBean> {

    private final TextView textCreate_time;
    private final TextView textTitle;
    private final TextView textGoods_name;
    private final TextView textExpress_no;
    private final ImageView imageImg;

    public WuLiuZSViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textCreate_time = $(R.id.textCreate_time);
        textTitle = $(R.id.textTitle);
        textGoods_name = $(R.id.textGoods_name);
        textExpress_no = $(R.id.textExpress_no);
        imageImg = $(R.id.imageImg);
    }

    @Override
    public void setData(MassageWuliu.DataBean data) {
        super.setData(data);
        GlideApp.with(getContext())
                .asBitmap()
                .centerCrop()
                .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(10, getContext())))
                .load(data.getImg())
                .into(imageImg);
        textCreate_time.setText(data.getCreate_time());
        textTitle.setText(data.getTitle());
        textExpress_no.setText(data.getExpress_no());
        textGoods_name.setText(data.getDes());
    }

}
