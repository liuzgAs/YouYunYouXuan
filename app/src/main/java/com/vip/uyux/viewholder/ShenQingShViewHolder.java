package com.vip.uyux.viewholder;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.activity.FuWuDanXQActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.AftersLogs;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class ShenQingShViewHolder extends BaseViewHolder<AftersLogs.DataBean> {

    private final ImageView imageImg;
    private final TextView textOrder_sn;
    private final TextView textSpe_name;
    private final TextView textQuantity;
    private final TextView textDes;
    private final Button btnIs_view;
    AftersLogs.DataBean data;

    public ShenQingShViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textOrder_sn = $(R.id.textOrder_sn);
        textSpe_name = $(R.id.textSpe_name);
        textQuantity = $(R.id.textQuantity);
        textDes = $(R.id.textDes);
        btnIs_view = $(R.id.btnIs_view);
        btnIs_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getContext(), FuWuDanXQActivity.class);
                intent.putExtra(Constant.IntentKey.ID,data.getId());
                getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void setData(AftersLogs.DataBean data) {
        super.setData(data);
        this.data=data;
        GlideApp.with(getContext())
                .load(data.getImg())
                .centerCrop()
                .placeholder(R.mipmap.ic_empty)
                .into(imageImg);
        textOrder_sn.setText(data.getOrder_sn());
        textSpe_name.setText(data.getSpe_name());
        textQuantity.setText("×"+data.getQuantity());
        textDes.setText(data.getDes());
        if (data.getIs_view()==1){
            btnIs_view.setVisibility(View.VISIBLE);
        }else {
            btnIs_view.setVisibility(View.GONE);
        }
    }
    
}
