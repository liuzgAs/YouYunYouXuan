package com.vip.uyux.fragment;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.vip.uyux.provider.DataProvider;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.viewholder.XiaoFeiMXViewHolder;

/**
 * A simple {@link Fragment} subclass.
 */
public class YongJinFragment extends ZjbBaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private int page = 1;
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Integer> adapter;
    private View mInflate;
    private int type;

    public YongJinFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public YongJinFragment(int type) {
        this.type = type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_yong_jin, container, false);
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
        recyclerView = (EasyRecyclerView) mInflate.findViewById(R.id.recyclerView);
    }

    @Override
    protected void initViews() {
        initRecycle();
    }

    private void initRecycle() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) DpUtils.convertDpToPixel(1f, getActivity()), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Integer>(getActivity()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_chong_zhi_mx;
                return new XiaoFeiMXViewHolder(parent, layout,type);
            }

        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                page++;
                adapter.addAll(DataProvider.getPersonList(page));
            }

            @Override
            public void onMoreClick() {

            }
        });
        adapter.setNoMore(R.layout.view_nomore, new RecyclerArrayAdapter.OnNoMoreListener() {
            @Override
            public void onNoMoreShow() {

            }

            @Override
            public void onNoMoreClick() {
            }
        });
        adapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                adapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                adapter.resumeMore();
            }
        });
        recyclerView.setRefreshListener(this);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void initData() {
        onRefresh();
    }

    @Override
    public void onRefresh() {
        page=1;
        adapter.clear();
        adapter.addAll(DataProvider.getPersonList(page));
        page++;
    }
}
