package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.MassageMsg;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class XiTongXiaoXiViewHolder extends BaseViewHolder<MassageMsg.DataBean> {

    private final TextView textCreate_time;
    private final TextView textTitle;
    private final TextView textdes;

    public XiTongXiaoXiViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textCreate_time = $(R.id.textCreate_time);
        textTitle = $(R.id.textTitle);
        textdes = $(R.id.textdes);
    }

    @Override
    public void setData(MassageMsg.DataBean data) {
        super.setData(data);
        textCreate_time.setText(data.getCreate_time());
        textTitle.setText(data.getTitle());
        textdes.setText(data.getDes());
    }
    
}
