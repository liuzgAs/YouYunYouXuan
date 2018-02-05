package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.vip.uyux.R;
import com.vip.uyux.model.GoodsInfo;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class ItemChanPinXQViewHolder extends BaseViewHolder<GoodsInfo.CommentBean> {

    private final EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<GoodsInfo.CommentBean.ImgsBean> adapter;
    private final ImageView imageImg;
    private final TextView textName;
    private final TextView textVip;
    private final SimpleRatingBar ratingbar_pingfeng;
    private final TextView textEvaluate_time;
    private final TextView textEvaluate;

    public ItemChanPinXQViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textName = $(R.id.textName);
        textVip = $(R.id.textVip);
        ratingbar_pingfeng = $(R.id.ratingbar_pingfeng);
        textEvaluate_time = $(R.id.textEvaluate_time);
        textEvaluate = $(R.id.textEvaluate);
        recyclerView = $(R.id.recyclerView);
        initZiYinRecycler();
    }

    @Override
    public void setData(GoodsInfo.CommentBean data) {
        super.setData(data);
        GlideApp.with(getContext())
                .load(data.getUser().getHeadimg())
                .centerCrop()
                .circleCrop()
                .placeholder(R.mipmap.ic_empty)
                .into(imageImg);
        textName.setText(data.getUser().getNickname());
        if (TextUtils.isEmpty(data.getUser().getVip())) {
            textVip.setVisibility(View.GONE);
        } else {
            textVip.setVisibility(View.VISIBLE);
            textVip.setText("LV" + data.getUser().getVip());
        }
        ratingbar_pingfeng.setRating((float) data.getUser().getStar());
        textEvaluate_time.setText(data.getEvaluate_time());
        textEvaluate.setText(data.getEvaluate());
        List<GoodsInfo.CommentBean.ImgsBean> imgsBeanList = data.getImgs();
        adapter.clear();
        adapter.addAll(imgsBeanList);
    }


    /**
     * 初始化recyclerview
     */
    private void initZiYinRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter = new RecyclerArrayAdapter<GoodsInfo.CommentBean.ImgsBean>(getContext()) {

            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_image_pinglun;
                return new PingLunImgViewHolder(parent, layout);
            }
        });
        SpaceDecoration spaceDecoration = new SpaceDecoration((int) DpUtils.convertDpToPixel(12, getContext()));
        spaceDecoration.setPaddingEdgeSide(false);
        recyclerView.addItemDecoration(spaceDecoration);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
    }
}
