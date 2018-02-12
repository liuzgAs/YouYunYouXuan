package com.vip.uyux.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.vip.uyux.R;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.RecycleViewDistancaUtil;
import com.vip.uyux.viewholder.CePingViewHolder;
import com.vip.uyux.viewholder.MyBaseViewHolder;

public class CePingXQActivity extends ZjbBaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Integer> adapter;
    private View viewBar;
    private float guangGaoHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ce_ping_xq);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void findID() {
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        viewBar = findViewById(R.id.viewBar);
    }

    @Override
    protected void initViews() {
        guangGaoHeight = DpUtils.convertDpToPixel(150, this);
        viewBar.getBackground().mutate().setAlpha(0);
        initRecycler();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.getSwipeToRefresh().setProgressViewOffset(true, 30, 300);
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.line_width), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Integer>(CePingXQActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_ceping_pinglun;
                return new CePingViewHolder(parent, layout);
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {

            private RecyclerArrayAdapter<Integer> adapterImg;
            private EasyRecyclerView recyclerViewImg;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(CePingXQActivity.this).inflate(R.layout.header_ceping, null);
                recyclerViewImg = view.findViewById(R.id.recyclerView);
                initImgRecycler();
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                adapterImg.clear();
                adapterImg.add(1);
                adapterImg.add(1);
                adapterImg.add(1);
                adapterImg.notifyDataSetChanged();
            }

            /**
             * 初始化recyclerview
             */
            private void initImgRecycler() {
                recyclerViewImg.setLayoutManager(new LinearLayoutManager(CePingXQActivity.this));
                recyclerViewImg.setRefreshingColorResources(R.color.basic_color);
                recyclerViewImg.setAdapterWithProgress(adapterImg = new RecyclerArrayAdapter<Integer>(CePingXQActivity.this) {
                    @Override
                    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                        int layout = R.layout.item_ceping_img;
                        return new MyBaseViewHolder(parent, layout);
                    }
                });
            }
        });
        recyclerView.setRefreshListener(this);
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int scrollY = RecycleViewDistancaUtil.getDistance(recyclerView, 0);
                if (scrollY <= guangGaoHeight - viewBar.getHeight() && scrollY >= 0) {
                    float percent = (float) scrollY / (guangGaoHeight - viewBar.getHeight());
                    viewBar.getBackground().mutate().setAlpha((int) (255f*percent));
                } else {
                    viewBar.getBackground().mutate().setAlpha(255);
                }
            }
        });
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        adapter.clear();
        adapter.add(1);
        adapter.add(1);
        adapter.add(1);
        adapter.notifyDataSetChanged();
    }
}
