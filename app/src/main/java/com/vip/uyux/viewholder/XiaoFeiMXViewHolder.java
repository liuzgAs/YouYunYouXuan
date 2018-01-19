package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;


/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class XiaoFeiMXViewHolder extends BaseViewHolder<Integer> {

    private final TextView textDes;
    private final TextView textCreatetime;
    private final TextView textPrice;
    private final TextView textPrice1;

    public XiaoFeiMXViewHolder(ViewGroup parent, @LayoutRes int res, int type) {
        super(parent, res);
        textDes = $(R.id.textDes);
        textCreatetime = $(R.id.textCreatetime);
        textPrice = $(R.id.textPrice);
        textPrice1 = $(R.id.textPrice1);
        switch (type) {
            case 0:
                textPrice1.setVisibility(View.GONE);
                break;
            default:
                textPrice1.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void setData(Integer data) {
        super.setData(data);
//        textDes.setText(data.getTitle());
//        textCreatetime.setText(data.getCreate_time());
//        textPrice.setText("¥"+data.getMoney());
    }

}
