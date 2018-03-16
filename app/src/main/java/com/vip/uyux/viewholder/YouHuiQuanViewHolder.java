package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.interfacepage.OnFinishListener;
import com.vip.uyux.interfacepage.OnShareYouHuiQuanListener;
import com.vip.uyux.model.YouHuiQuan;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class YouHuiQuanViewHolder extends BaseViewHolder<YouHuiQuan> {


    private final TextView textName;
    private final TextView textMoney;
    private final TextView textLimit_money;
    private final TextView textDes;
    private final TextView textUse_time;
    private final TextView textSendDes;
    private final TextView btnBtnDes;
    private final View viewBg;
    private final View viewKeZengSong;
    private final View viewZengSong;
    YouHuiQuan data;
    OnFinishListener onFinishListener;
    OnShareYouHuiQuanListener OnShareYouHuiQuanListener;
    private final ImageView imageKeZengSong;
    private final ImageView imageGou;

    public YouHuiQuanViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textName = $(R.id.textName);
        textMoney = $(R.id.textMoney);
        textLimit_money = $(R.id.textLimit_money);
        textDes = $(R.id.textDes);
        textUse_time = $(R.id.textUse_time);
        textSendDes = $(R.id.textSendDes);
        btnBtnDes = $(R.id.btnBtnDes);
        viewBg = $(R.id.viewBg);
        viewKeZengSong = $(R.id.viewKeZengSong);
        viewZengSong = $(R.id.viewZengSong);
        imageKeZengSong = $(R.id.imageKeZengSong);
        imageGou = $(R.id.imageGou);
        viewKeZengSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (data.isZhanKai()){
                    data.setZhanKai(false);
                    viewZengSong.setVisibility(View.GONE);
                }else {
                    data.setZhanKai(true);
                    viewZengSong.setVisibility(View.VISIBLE);
                }
            }
        });
        btnBtnDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (data.getUseState()==1){
                    onFinishListener.toFinish(getDataPosition());
                }
            }
        });
        viewZengSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnShareYouHuiQuanListener.share(data);
            }
        });
    }

    @Override
    public void setData(YouHuiQuan data) {
        super.setData(data);
        this.data=data;
        textName.setText(data.getName());
        SpannableString span = new SpannableString("¥"+data.getMoney());
        span.setSpan(new RelativeSizeSpan(0.4f), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textMoney.setText(span);
        textLimit_money.setText(data.getLimit_money());
        textDes.setText(data.getDes());
        textUse_time.setText(data.getUse_time());
        textSendDes.setText(data.getSendDes());
        btnBtnDes.setText(data.getBtnDes());
        if (data.getUseState()==1){
            viewBg.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.basic_color));
            btnBtnDes.setTextColor(ContextCompat.getColor(getContext(),R.color.basic_color));
            btnBtnDes.setBackgroundResource(R.drawable.shape_basic01_1dp_25dp);
        }else {
            viewBg.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.youHuiQuanGray));
            btnBtnDes.setTextColor(ContextCompat.getColor(getContext(),R.color.text_gray));
            btnBtnDes.setBackgroundResource(R.drawable.shape_gray1dp_20dp);
        }
        if (data.getIs_send()==1){
            viewKeZengSong.setEnabled(true);
            imageKeZengSong.setVisibility(View.VISIBLE);
        }else {
            viewKeZengSong.setEnabled(false);
            imageKeZengSong.setVisibility(View.GONE);
        }
        if (data.isZhanKai()){
            viewZengSong.setVisibility(View.VISIBLE);
        }else {
            viewZengSong.setVisibility(View.GONE);
        }
        if (data.isSelect()){
            imageGou.setSelected(true);
        }else {
            imageGou.setSelected(false);
        }
    }

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }

    public void setOnShareYouHuiQuanListener(com.vip.uyux.interfacepage.OnShareYouHuiQuanListener onShareYouHuiQuanListener) {
        OnShareYouHuiQuanListener = onShareYouHuiQuanListener;
    }
}
