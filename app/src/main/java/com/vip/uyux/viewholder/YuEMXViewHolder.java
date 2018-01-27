package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.WithdrawNotwithdraw;


/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class YuEMXViewHolder extends BaseViewHolder<WithdrawNotwithdraw.DataBean> {

    private final TextView textDes;
    private final TextView textCreatetime;
    private final TextView textPrice;
    private final TextView textPrice1;
    int type;

    public YuEMXViewHolder(ViewGroup parent, @LayoutRes int res, int type) {
        super(parent, res);
        this.type = type;
        textDes = $(R.id.textDes);
        textCreatetime = $(R.id.textCreatetime);
        textPrice = $(R.id.textPrice);
        textPrice1 = $(R.id.textPrice1);
        switch (type) {
            case 1:
                textPrice1.setVisibility(View.GONE);
                break;
            case 2:
                textPrice1.setVisibility(View.VISIBLE);
                break;
            default:
                textPrice1.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void setData(WithdrawNotwithdraw.DataBean data) {
        super.setData(data);
        textDes.setText(data.getName());
        textCreatetime.setText(data.getCreate_time());
        textPrice.setText("¥" + data.getMoney());
        if (type == 2) {
            textPrice1.setText("¥" + data.getX_money());
        }
    }

}
