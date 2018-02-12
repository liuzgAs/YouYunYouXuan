package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.EvaluationInfo;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class LiuYanHuiFuViewHolder extends BaseViewHolder<EvaluationInfo.DataBean.ListBean> {

    private final TextView textDes;

    public LiuYanHuiFuViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textDes = $(R.id.textDes);
    }

    @Override
    public void setData(EvaluationInfo.DataBean.ListBean data) {
        super.setData(data);
        if (data.getType()==1){
            SpannableString span = new SpannableString(data.getNickname()+"："+data.getContent());
            span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(),R.color.basic_color)), 0, data.getNickname().length()+1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            textDes.setText(span);
        }else {
            SpannableString span = new SpannableString(data.getNickname()+"回复"+data.getTonickname()+":"+data.getContent());
            span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(),R.color.basic_color)), 0, data.getNickname().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(),R.color.basic_color)), data.getNickname().length()+2, data.getNickname().length()+2+data.getTonickname().length()+1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            textDes.setText(span);
        }
    }
    
}
