package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class YouHuiQuanViewHolder extends BaseViewHolder<Integer> {

    private final TextView textAmount;

    public YouHuiQuanViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textAmount = $(R.id.textAmount);
    }

    @Override
    public void setData(Integer data) {
        super.setData(data);
        SpannableString span = new SpannableString("¥50");
        span.setSpan(new RelativeSizeSpan(0.4f), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textAmount.setText(span);
    }
    
}
