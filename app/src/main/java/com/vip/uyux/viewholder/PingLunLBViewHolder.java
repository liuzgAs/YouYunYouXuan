package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
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
import com.vip.uyux.model.CommentGoods;
import com.vip.uyux.model.ImgsBean;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class PingLunLBViewHolder extends BaseViewHolder<CommentGoods.DataBean> {

    private final EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<ImgsBean> adapter;
    private final ImageView imageImg;
    private final TextView textName;
    private final TextView textVip;
    private final SimpleRatingBar ratingbar_pingfeng;
    private final TextView textEvaluate_time;
    private final TextView textEvaluate;

    public PingLunLBViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textName = $(R.id.textName);
        textVip = $(R.id.textVip);
        ratingbar_pingfeng = $(R.id.ratingbar_pingfeng);
        textEvaluate_time = $(R.id.textEvaluate_time);
        textEvaluate = $(R.id.textEvaluate);
        recyclerView = $(R.id.recyclerView);
        textVip.setVisibility(View.GONE);
        initZiYinRecycler();
    }

    @Override
    public void setData(CommentGoods.DataBean data) {
        super.setData(data);
        GlideApp.with(getContext())
                .load(data.getHeadimg())
                .centerCrop()
                .circleCrop()
                .placeholder(R.mipmap.ic_empty)
                .into(imageImg);
        textName.setText(data.getNickname());
        ratingbar_pingfeng.setRating((float) data.getStar());
        textEvaluate_time.setText(data.getEvaluate_time());
        textEvaluate.setText(data.getEvaluate());
        List<ImgsBean> imgsBeanList = data.getImgs();
        adapter.clear();
        adapter.addAll(imgsBeanList);
    }


    /**
     * 初始化recyclerview
     */
    private void initZiYinRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter = new RecyclerArrayAdapter<ImgsBean>(getContext()) {

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
