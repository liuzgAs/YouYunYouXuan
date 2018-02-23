package com.vip.uyux.fragment;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.vip.uyux.R;
import com.vip.uyux.base.ZjbBaseFragment;
import com.vip.uyux.model.ImgsBean;
import com.vip.uyux.viewholder.ChanPinFootViewHolder;
import com.vip.uyux.viewholder.MyBaseViewHolder;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChanPinXqTuPianFragment extends ZjbBaseFragment {
    List<ImgsBean> imgsBeanList;
    private View mInflate;
    private EasyRecyclerView recyclerView;
    private EasyRecyclerView recyclerViewEmpty;
    private RecyclerArrayAdapter<ImgsBean> adapter;
    private RecyclerArrayAdapter<Integer> adapterEmpty;

    @SuppressLint("ValidFragment")
    public ChanPinXqTuPianFragment(List<ImgsBean> imgsBeanList) {
        this.imgsBeanList=imgsBeanList;
    }

    public ChanPinXqTuPianFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_chan_pin_xq_tu_pian, container, false);
            init();
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mInflate.getParent();
        if (parent != null) {
            parent.removeView(mInflate);
        }
        return mInflate;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void findID() {
        recyclerView =  mInflate.findViewById(R.id.recyclerView);
        recyclerViewEmpty =  mInflate.findViewById(R.id.recyclerViewEmpty);
    }

    @Override
    protected void initViews() {
        recyclerViewEmpty.setVisibility(View.GONE);
        initRecycler();
        initRecyclerEmpty();
        adapterEmpty.add(1);
        adapterEmpty.notifyDataSetChanged();
    }

        /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.line_width), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<ImgsBean>(mContext) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_image;
                return new ChanPinFootViewHolder(parent, layout);
            }
        });
    }

    /**
     * 初始化recyclerview
     */
    private void initRecyclerEmpty() {
        recyclerViewEmpty.setLayoutManager(new LinearLayoutManager(mContext));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.line_width), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerViewEmpty.addItemDecoration(itemDecoration);
        recyclerViewEmpty.setRefreshingColorResources(R.color.basic_color);
        recyclerViewEmpty.setAdapterWithProgress(adapterEmpty = new RecyclerArrayAdapter<Integer>(mContext) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.view_empty;
                return new MyBaseViewHolder(parent, layout);
            }
        });
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void initData() {
        adapter.clear();
        adapter.addAll(imgsBeanList);
        if (imgsBeanList.size()==0){
            recyclerViewEmpty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else {
            recyclerViewEmpty.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}
