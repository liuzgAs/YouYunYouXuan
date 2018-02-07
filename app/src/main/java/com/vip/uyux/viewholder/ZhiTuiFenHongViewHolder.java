package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.BonusDirect;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class ZhiTuiFenHongViewHolder extends BaseViewHolder<BonusDirect.DataBean> {

    private final ImageView imageImg;
    private final TextView textName;
    private final TextView textLv;
    private final TextView textID;
    private final TextView textLeiJi;

    public ZhiTuiFenHongViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textName = $(R.id.textName);
        textLv = $(R.id.textLv);
        textID = $(R.id.textID);
        textLeiJi = $(R.id.textLeiJi);
    }

    @Override
    public void setData(BonusDirect.DataBean data) {
        super.setData(data);
        GlideApp.with(getContext())
                .load(data.getHeadimg())
                .centerCrop()
                .circleCrop()
                .placeholder(R.mipmap.ic_empty)
                .into(imageImg);
        textName.setText(data.getNickname());
        textLv.setText(data.getGrade());
        textID.setText("ID："+data.getId());
        textLeiJi.setText(data.getDes());
    }

}
