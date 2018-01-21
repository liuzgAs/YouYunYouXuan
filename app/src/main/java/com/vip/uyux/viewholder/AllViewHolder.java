package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.IndexCate;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class AllViewHolder extends BaseViewHolder<IndexCate.DataBean.ListBean> {


    private final ImageView imageImg;
    private final TextView textName;

    public AllViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);

        textName = $(R.id.textName);
        imageImg = $(R.id.imageImg);
    }

    @Override
    public void setData(IndexCate.DataBean.ListBean data) {
        super.setData(data);
        textName.setText(data.getName());
        GlideApp.with(getContext())
                .asBitmap()
                .load(data.getImg())
                .placeholder(R.mipmap.ic_empty)
                .into(imageImg);
    }
}
