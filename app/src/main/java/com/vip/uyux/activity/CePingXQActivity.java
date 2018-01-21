package com.vip.uyux.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.vip.uyux.R;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.provider.DataProvider;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;
import com.vip.uyux.viewholder.CePingViewHolder;

public class CePingXQActivity extends ZjbBaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Integer> adapter;

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
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("测评详情");
        initRecycler();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Integer>(CePingXQActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_ceping;
                return new CePingViewHolder(parent, layout);
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {

            private ImageView imageImg;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(CePingXQActivity.this).inflate(R.layout.header_ceping, null);
                imageImg = view.findViewById(R.id.imageImg);
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                GlideApp.with(CePingXQActivity.this)
                        .asBitmap()
                        .centerCrop()
                        .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(3,CePingXQActivity.this)))
                        .load(R.mipmap.tuijianimg)
                        .into(imageImg);
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
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
        recyclerView.setRefreshListener(this);
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
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

    int page = 1;

    @Override
    public void onRefresh() {
        page = 1;
        adapter.clear();
        adapter.addAll(DataProvider.getPersonList(page));
        page++;
    }
}
