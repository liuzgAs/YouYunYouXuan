package com.vip.uyux.viewholder;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.vip.uyux.R;
import com.vip.uyux.activity.ChanPinLBActivity;
import com.vip.uyux.activity.ChanPinXQActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.IndexHome;
import com.vip.uyux.util.DpUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class IndexViewHolder extends BaseViewHolder<IndexHome.DataBean> {

    private final EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<IndexHome.DataBean.GoodsBean> adapter;
    private final TextView textTitle;
    private final View viewMore;

    public IndexViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textTitle = $(R.id.textTitle);
        recyclerView = $(R.id.recyclerView);
        viewMore = $(R.id.viewMore);
        viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getContext(), ChanPinLBActivity.class);
                intent.putExtra(Constant.IntentKey.TITLE, data.getName());
                intent.putExtra(Constant.IntentKey.PCATE, data.getId());
                getContext().startActivity(intent);
            }
        });
        initRecycler();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter = new RecyclerArrayAdapter<IndexHome.DataBean.GoodsBean>(getContext()) {

            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_index_item;
                return new IndexItemViewHolder(parent, layout);
            }
        });
        SpaceDecoration spaceDecoration = new SpaceDecoration((int) DpUtils.convertDpToPixel(12, getContext()));
        spaceDecoration.setPaddingEdgeSide(false);
        recyclerView.addItemDecoration(spaceDecoration);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                intent.setClass(getContext(), ChanPinXQActivity.class);
                intent.putExtra(Constant.IntentKey.ID, adapter.getItem(position).getId());
                getContext().startActivity(intent);
            }
        });
    }

    IndexHome.DataBean data;

    @Override
    public void setData(IndexHome.DataBean data) {
        super.setData(data);
        this.data = data;
        textTitle.setText(data.getName());
        List<IndexHome.DataBean.GoodsBean> goodsBeanList = data.getGoods();
        adapter.clear();
        adapter.addAll(goodsBeanList);
    }

}
