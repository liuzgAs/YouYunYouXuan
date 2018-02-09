package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.BonusSuperioritybefore;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class ZiZhiZMViewHolder extends BaseViewHolder<BonusSuperioritybefore.DataBean> {

    private final CheckBox checkbox;
    BonusSuperioritybefore.DataBean data;

    public ZiZhiZMViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        checkbox = $(R.id.checkbox);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                data.setCheck(b);
            }
        });
    }

    @Override
    public void setData(BonusSuperioritybefore.DataBean data) {
        super.setData(data);
        this.data=data;
        checkbox.setText(data.getName());
        checkbox.setChecked(data.isCheck());
    }

}
