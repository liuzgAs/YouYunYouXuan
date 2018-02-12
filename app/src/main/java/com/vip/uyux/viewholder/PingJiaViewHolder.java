package com.vip.uyux.viewholder;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.activity.LiJiCePingActivity;
import com.vip.uyux.activity.PingJiaActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.Comment;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class PingJiaViewHolder extends BaseViewHolder<Comment.DataBean> {
    private final ImageView imageImg;
    private final TextView textName;
    private final TextView textDes;
    private final ImageView imageGood;
    private final TextView textGoodName;
    private final TextView textPrice;
    private final TextView textDate;
    private final SimpleRatingBar ratingbar_pingfeng;
    private final View viewGood;
    private final View viewPingJia;
    private final EasyRecyclerView recyclerView;
    private final TextView textSpe_name;
    private final View viewQuCePing;

    public PingJiaViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textName = $(R.id.textName);
        textDes = $(R.id.textDes);
        ratingbar_pingfeng = $(R.id.ratingbar_pingfeng);
        imageGood = $(R.id.imageGood);
        textGoodName = $(R.id.textGoodName);
        textPrice = $(R.id.textPrice);
        textDate = $(R.id.textDate);
        viewGood = $(R.id.viewGood);
        viewPingJia = $(R.id.viewPingJia);
        recyclerView = $(R.id.recyclerView);
        textSpe_name = $(R.id.textSpe_name);
        viewQuCePing = $(R.id.viewQuCePing);
        viewPingJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getContext(), PingJiaActivity.class);
                intent.putExtra(Constant.IntentKey.ID, data.getId());
                getContext().startActivity(intent);
            }
        });
        viewQuCePing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (data.getGo_evaluation()) {
                    case 1:
                        /*去立即测评页面*/
                        Intent intent = new Intent();
                        intent.putExtra(Constant.IntentKey.OGID,data.getId());
                        intent.setClass(getContext(), LiJiCePingActivity.class);
                        getContext().startActivity(intent);
                        break;
                    case 2:
                        /*去官方推荐*/
                        break;
                    default:
                        break;
                }
            }
        });
    }

    Comment.DataBean data;

    @Override
    public void setData(Comment.DataBean data) {
        super.setData(data);
        this.data = data;
        switch (data.getEvaluate_status()) {
            case 1:
                viewPingJia.setVisibility(View.GONE);
                ratingbar_pingfeng.setVisibility(View.VISIBLE);
                textDes.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                break;
            case 0:
                viewPingJia.setVisibility(View.VISIBLE);
                ratingbar_pingfeng.setVisibility(View.GONE);
                textDes.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                break;
            default:

                break;
        }
        GlideApp.with(getContext())
                .asBitmap()
                .load(data.getHeadimg())
                .circleCrop()
                .placeholder(R.mipmap.ic_empty)
                .into(imageImg);
        textName.setText(data.getNickname());
        textDes.setText(data.getEvaluate().toString().trim());
        ratingbar_pingfeng.setRating(data.getStar());
        viewGood.setVisibility(View.VISIBLE);
        GlideApp.with(getContext())
                .asBitmap()
                .centerCrop()
                .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(8, getContext())))
                .load(data.getImg())
                .into(imageGood);
        textGoodName.setText(data.getGoods_name());
        textPrice.setText("¥" + data.getGoods_price());
        textDate.setText(data.getCreate_time());
        textSpe_name.setText(data.getSpe_name());
        if (data.getGo_evaluation() == 0) {
            viewQuCePing.setVisibility(View.GONE);
        } else {
            viewQuCePing.setVisibility(View.VISIBLE);
        }
    }

}
