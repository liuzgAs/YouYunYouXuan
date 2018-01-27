package com.vip.uyux.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.GoodsIndex;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.viewholder.ChanPinLBViewHolder;

import java.util.HashMap;
import java.util.List;

public class ChanPinLBActivity extends ZjbBaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<GoodsIndex.DataBean> adapter;
    private int cate;
    private String title;
    private int pcate;
    private ImageView imgeRight;
    private boolean isDuoLie = false;
    private DividerDecoration itemDecoration;
    private SpaceDecoration itemDecoration1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chan_pin_lb);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        cate = intent.getIntExtra(Constant.IntentKey.CATE, 0);
        pcate = intent.getIntExtra(Constant.IntentKey.PCATE, 0);
        title = intent.getStringExtra(Constant.IntentKey.TITLE);
    }

    @Override
    protected void findID() {
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        imgeRight = (ImageView) findViewById(R.id.imgeRight);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText(title);
        initRecycler();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.line_width), 0, 0);
        itemDecoration.setDrawLastItem(false);
        itemDecoration1 = new SpaceDecoration((int) DpUtils.convertDpToPixel(10f, ChanPinLBActivity.this));
//        recyclerView.addItemDecoration(itemDecoration1);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<GoodsIndex.DataBean>(ChanPinLBActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout ;
                if (viewType==1){
                    layout = R.layout.item_lb_duolie;
                    LogUtil.LogShitou("ChanPinLBActivity--OnCreateViewHolder", ""+viewType);
                }else {
                    LogUtil.LogShitou("ChanPinLBActivity--OnCreateViewHolder", ""+viewType);
                    layout = R.layout.item_chanpin;
                }
                return new ChanPinLBViewHolder(parent, layout,viewType);
            }

            @Override
            public int getViewType(int position) {
                return getItem(position).getViewType();
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                ApiClient.post(ChanPinLBActivity.this, getOkObject(), new ApiClient.CallBack() {
                    @Override
                    public void onSuccess(String s) {
                        LogUtil.LogShitou("DingDanGLActivity--加载更多", s + "");
                        try {
                            page++;
                            GoodsIndex goodsIndex = GsonUtils.parseJSON(s, GoodsIndex.class);
                            int status = goodsIndex.getStatus();
                            if (status == 1) {
                                List<GoodsIndex.DataBean> dataBeanList = goodsIndex.getData();
                                if (isDuoLie){
                                    for (int i = 0; i < dataBeanList.size(); i++) {
                                        dataBeanList.get(i).setViewType(1);
                                    }
                                }else {
                                    for (int i = 0; i < dataBeanList.size(); i++) {
                                        dataBeanList.get(i).setViewType(0);
                                    }
                                }
                                adapter.addAll(dataBeanList);
                            } else if (status == 3) {
                                MyDialog.showReLoginDialog(ChanPinLBActivity.this);
                            } else {
                                adapter.pauseMore();
                            }
                        } catch (Exception e) {
                            adapter.pauseMore();
                        }
                    }

                    @Override
                    public void onError() {
                        adapter.pauseMore();
                    }
                });
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
                Intent intent = new Intent();
                intent.setClass(ChanPinLBActivity.this, ChanPinXQActivity.class);
                intent.putExtra(Constant.IntentKey.ID, adapter.getItem(position).getId());
                startActivity(intent);
            }
        });
        recyclerView.setRefreshListener(this);
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        imgeRight.setOnClickListener(this);
    }

    int page = 1;

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.GOODS_INDEX;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("p", String.valueOf(page));
        params.put("cate", String.valueOf(cate));
        params.put("pcate", String.valueOf(pcate));
        return new OkObject(params, url);
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgeRight:
                isDuoLie = !isDuoLie;
                if (isDuoLie) {
                    for (int i = 0; i < adapter.getAllData().size(); i++) {
                        adapter.getItem(i).setViewType(1);
                    }
                    recyclerView.setLayoutManager(new GridLayoutManager(this,2 ));
                    if (itemDecoration!=null){
                        recyclerView.removeItemDecoration(itemDecoration);
                    }
                    imgeRight.setImageResource(R.mipmap.liebiao2);
                    recyclerView.addItemDecoration(itemDecoration1);
                } else {
                    for (int i = 0; i < adapter.getAllData().size(); i++) {
                        adapter.getItem(i).setViewType(0);
                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    if (itemDecoration1!=null){
                        recyclerView.removeItemDecoration(itemDecoration1);
                    }
                    imgeRight.setImageResource(R.mipmap.liebiao);
                    recyclerView.addItemDecoration(itemDecoration);
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        page = 1;
        ApiClient.post(this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("产品列表", s);
                try {
                    page++;
                    GoodsIndex goodsIndex = GsonUtils.parseJSON(s, GoodsIndex.class);
                    if (goodsIndex.getStatus() == 1) {
                        List<GoodsIndex.DataBean> dataBeanList = goodsIndex.getData();
                        if (isDuoLie){
                            for (int i = 0; i < dataBeanList.size(); i++) {
                                dataBeanList.get(i).setViewType(1);
                            }
                        }else {
                            for (int i = 0; i < dataBeanList.size(); i++) {
                                dataBeanList.get(i).setViewType(0);
                            }
                        }
                        adapter.clear();
                        adapter.addAll(dataBeanList);
                    } else if (goodsIndex.getStatus() == 3) {
                        MyDialog.showReLoginDialog(ChanPinLBActivity.this);
                    } else {
                        showError(goodsIndex.getInfo());
                    }
                } catch (Exception e) {
                    showError("数据出错");
                }
            }

            @Override
            public void onError() {
                showError("网络出错");
            }

            /**
             * 错误显示
             * @param msg
             */
            private void showError(String msg) {
                try {
                    View viewLoader = LayoutInflater.from(ChanPinLBActivity.this).inflate(R.layout.view_loaderror, null);
                    TextView textMsg = viewLoader.findViewById(R.id.textMsg);
                    textMsg.setText(msg);
                    viewLoader.findViewById(R.id.buttonReLoad).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            recyclerView.showProgress();
                            initData();
                        }
                    });
                    recyclerView.setErrorView(viewLoader);
                    recyclerView.showError();
                } catch (Exception e) {
                }
            }
        });
    }
}
