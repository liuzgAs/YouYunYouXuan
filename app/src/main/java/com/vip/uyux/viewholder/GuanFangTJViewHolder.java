package com.vip.uyux.viewholder;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.activity.LiJiCePingActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.EvaluationOfficial;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;
import com.vip.uyux.util.LogUtil;

/**
 * Created by zhangjiebo on 2017/3/28 0028.
 * @author ZhangJieBo
 */
public class GuanFangTJViewHolder extends BaseViewHolder<EvaluationOfficial.DataBean> {

    private final TextView textTitle;
    EvaluationOfficial.DataBean data;
    private final ImageView imageImg;
    private OnGetOgId onGetOgId;

    public GuanFangTJViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textTitle = $(R.id.textTitle);
        $(R.id.viewCaiYong).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ogId = onGetOgId.getOgId();
                LogUtil.LogShitou("GuanFangTJViewHolder--onClick", "ogid"+ogId);
                Intent intent = new Intent();
                intent.putExtra(Constant.IntentKey.ID,data.getId());
                intent.putExtra(Constant.IntentKey.OGID,ogId);
                intent.setClass(getContext(), LiJiCePingActivity.class);
                getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void setData(EvaluationOfficial.DataBean data) {
        super.setData(data);
        this.data = data;
        GlideApp.with(getContext())
                .asBitmap()
                .centerCrop()
                .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(10, getContext())))
                .load(data.getImg())
                .into(imageImg);
        textTitle.setText(data.getTitle());
    }

    public interface OnGetOgId{
        int getOgId();
    }

    public void setOnGetOgId(OnGetOgId onGetOgId) {
        this.onGetOgId = onGetOgId;
    }
}
