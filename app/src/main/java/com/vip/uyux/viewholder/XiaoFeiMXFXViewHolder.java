package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.WithdrawNotwithdraw;


/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class XiaoFeiMXFXViewHolder extends BaseViewHolder<WithdrawNotwithdraw.DataBean> {

    private final TextView textDes;
    private final TextView textCreatetime;
    private final TextView textPrice;
    private final TextView textMoneyDes;
    private final TextView textGrade;
    private final TextView textNameDes;
    int type;

    public XiaoFeiMXFXViewHolder(ViewGroup parent, @LayoutRes int res, int type) {
        super(parent, res);
        this.type = type;
        textDes = $(R.id.textDes);
        textCreatetime = $(R.id.textCreatetime);
        textPrice = $(R.id.textPrice);
        textMoneyDes = $(R.id.textMoneyDes);
        textGrade = $(R.id.textGrade);
        textNameDes = $(R.id.textNameDes);
    }

    @Override
    public void setData(WithdrawNotwithdraw.DataBean data) {
        super.setData(data);
        textDes.setText(data.getName());
        textCreatetime.setText(data.getCreate_time());
        textPrice.setText("¥" + data.getMoney());
        textMoneyDes.setText(data.getMoneyDes());
        textGrade.setText(data.getGrade());
        textNameDes.setText(data.getNameDes());
    }

}
