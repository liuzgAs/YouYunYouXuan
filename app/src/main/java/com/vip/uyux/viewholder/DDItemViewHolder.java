package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class DDItemViewHolder extends BaseViewHolder<Integer> {

    private final ImageView imageImg;
    private final TextView textTitle;
    private final TextView textDes;
    private final TextView textPrice;
    private final TextView textNum;

    public DDItemViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textTitle = $(R.id.textTitle);
        textDes = $(R.id.textDes);
        textPrice = $(R.id.textPrice);
        textNum = $(R.id.textNum);
    }

    @Override
    public void setData(Integer data) {
        super.setData(data);
//        GlideApp.with(getContext())
//                .asBitmap()
//                .centerCrop()
//                .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(4, getContext())))
//                .load(data.getGoods_img())
//                .into(imageImg);
//        textTitle.setText(data.getGoods_title());
//        textDes.setText(data.getSpe_name());
//        textNum.setText("×"+data.getNum());
//        textPrice.setText("¥"+data.getGoods_price());
    }

}
