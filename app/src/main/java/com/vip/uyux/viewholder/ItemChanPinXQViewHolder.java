package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.vip.uyux.R;
import com.vip.uyux.provider.DataProvider;
import com.vip.uyux.util.DpUtils;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class ItemChanPinXQViewHolder extends BaseViewHolder<Integer> {

    private final EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Integer> adapter;

    public ItemChanPinXQViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        recyclerView = $(R.id.recyclerView);
        initZiYinRecycler();
    }

    @Override
    public void setData(Integer data) {
        super.setData(data);
        adapter.clear();
        adapter.addAll(DataProvider.getPersonList(1));
    }


    /**
     * 初始化recyclerview
     */
    private void initZiYinRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter = new RecyclerArrayAdapter<Integer>(getContext()) {

            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_image_pinglun;
                return new MyBaseViewHolder(parent, layout);
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
