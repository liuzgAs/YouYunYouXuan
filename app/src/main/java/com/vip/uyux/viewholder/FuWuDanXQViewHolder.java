package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.AftersLogsinfo;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class FuWuDanXQViewHolder extends BaseViewHolder<AftersLogsinfo.DesBean> {

    private final TextView textTitle;
    private final TextView textDes;

    public FuWuDanXQViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textTitle = $(R.id.textTitle);
        textDes = $(R.id.textDes);
    }

    @Override
    public void setData(AftersLogsinfo.DesBean data) {
        super.setData(data);
        textTitle.setText(data.getTitle());
        textDes.setText(data.getDes());
    }
    
}
