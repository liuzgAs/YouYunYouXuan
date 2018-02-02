package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.WithdrawGetwithdraw;


/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class TiXianJLViewHolder extends BaseViewHolder<WithdrawGetwithdraw.DataBean> {

    private final TextView textDes;
    private final TextView textCreatetime;
    private final TextView textPrice;
    private final TextView textPrice1;

    public TiXianJLViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textDes = $(R.id.textDes);
        textCreatetime = $(R.id.textCreatetime);
        textPrice = $(R.id.textPrice);
        textPrice1 = $(R.id.textPrice1);
        textPrice1.setVisibility(View.GONE);
    }

    @Override
    public void setData(WithdrawGetwithdraw.DataBean data) {
        super.setData(data);
        textDes.setText("提现"+data.getMoney());
        textCreatetime.setText(data.getCreate_time());
        if (data.getStatus()==1){
            textPrice.setText("提现中");
        }else{
            textPrice.setText("已到账");
        }
    }

}
