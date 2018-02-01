package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.BonusDistributionorder;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class FenXiaoViewHolder extends BaseViewHolder<BonusDistributionorder.DataBean> {

    private final TextView textOrder_no;
    private final TextView textStatus_v;
    private final TextView textName;
    private final TextView textLv;
    private final TextView textDes;
    private final TextView textEstimate_money;
    private final ImageView imageImg;

    public FenXiaoViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textOrder_no = $(R.id.textOrder_no);
        textStatus_v = $(R.id.textStatus_v);
        textName = $(R.id.textName);
        textLv = $(R.id.textLv);
        textDes = $(R.id.textDes);
        textEstimate_money = $(R.id.textEstimate_money);
        imageImg = $(R.id.imageImg);
    }


    @Override
    public void setData(BonusDistributionorder.DataBean data) {
        super.setData(data);
        textOrder_no.setText(data.getOrderSn());
        textStatus_v.setText(data.getOrderSnDes());
        GlideApp.with(getContext())
                .load(data.getHeadimg())
                .centerCrop()
                .circleCrop()
                .placeholder(R.mipmap.ic_empty)
                .into(imageImg);
        textName.setText(data.getNickname());
        textDes.setText(data.getNicknameDes());
        if (TextUtils.isEmpty(data.getGn())){
            textLv.setVisibility(View.GONE);
        }else {
            textLv.setText(data.getGn());
            textLv.setVisibility(View.VISIBLE);
        }
        textEstimate_money.setText(data.getDes());
    }


}
