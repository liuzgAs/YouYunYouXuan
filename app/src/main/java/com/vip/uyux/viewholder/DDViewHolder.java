package com.vip.uyux.viewholder;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.vip.uyux.R;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class DDViewHolder extends BaseViewHolder<Integer> {

    private final EasyRecyclerView recyclerView;
    private final EasyRecyclerView recyclerViewDes;
    private RecyclerArrayAdapter<Integer> adapter;
    private RecyclerArrayAdapter<Integer> adapterDes;

    public DDViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        recyclerView = $(R.id.recyclerView);
        recyclerViewDes = $(R.id.recyclerViewDes);
        initRecycler();
        initDesRecycler();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getContext().getResources().getDimension(R.dimen.line_width), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Integer>(getContext()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_ding_dan_cangku;
                return new DDCangKuViewHolder(parent, layout);
            }
        });
    }

    /**
     * 初始化recyclerview
     */
    private void initDesRecycler() {
        recyclerViewDes.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewDes.setAdapterWithProgress(adapterDes = new RecyclerArrayAdapter<Integer>(getContext()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_dd_des;
                return new MyBaseViewHolder(parent, layout);
            }
        });
    }

    @Override
    public void setData(Integer data) {
        super.setData(data);
        adapter.clear();
        adapter.add(1);
        adapter.add(1);
        adapter.notifyDataSetChanged();
        adapterDes.clear();
        adapterDes.add(1);
        adapterDes.add(1);
        adapterDes.notifyDataSetChanged();
    }

}
