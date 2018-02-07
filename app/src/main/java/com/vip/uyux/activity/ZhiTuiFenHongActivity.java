package com.vip.uyux.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.BonusDirect;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.viewholder.ZhiTuiFenHongViewHolder;

import java.util.HashMap;
import java.util.List;

public class ZhiTuiFenHongActivity extends ZjbBaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<BonusDirect.DataBean> adapter;
    private BonusDirect.DirectBonusBean direct_bonus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi_tui_fen_hong);
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
        initRecycler();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.line_width), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<BonusDirect.DataBean>(ZhiTuiFenHongActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_zhituifenhong;
                return new ZhiTuiFenHongViewHolder(parent, layout);
            }
        });

        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {

            private TextView textDes;
            private TextView testDes2;
            private TextView testDes1;
            private TextView textYuJiFH;
            private TextView textKeTiXian;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(ZhiTuiFenHongActivity.this).inflate(R.layout.header_chanpin_zhitui, null);
                textKeTiXian = view.findViewById(R.id.textKeTiXian);
                textYuJiFH = view.findViewById(R.id.textYuJiFH);
                testDes1 = view.findViewById(R.id.testDes1);
                testDes2 = view.findViewById(R.id.testDes2);
                textDes = view.findViewById(R.id.textDes);
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (direct_bonus!=null){
                    textKeTiXian.setText(direct_bonus.getNum1());
                    textYuJiFH.setText(direct_bonus.getNum2());
                    testDes1.setText(direct_bonus.getDes1());
                    testDes2.setText(direct_bonus.getDes2());
                    textDes.setText(direct_bonus.getDes());
                }
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                ApiClient.post(ZhiTuiFenHongActivity.this, getOkObject(), new ApiClient.CallBack() {
                    @Override
                    public void onSuccess(String s) {
                        LogUtil.LogShitou("DingDanGLActivity--加载更多", s+"");
                        try {
                            page++;
                            BonusDirect bonusDirect = GsonUtils.parseJSON(s, BonusDirect.class);
                            int status = bonusDirect.getStatus();
                            if (status == 1) {
                                List<BonusDirect.DataBean> dataBeanList = bonusDirect.getData();
                                adapter.addAll(dataBeanList);
                            } else if (status == 3) {
                                MyDialog.showReLoginDialog(ZhiTuiFenHongActivity.this);
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
            }
        });
        recyclerView.setRefreshListener(this);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void initData() {
        onRefresh();
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.BONUS_DIRECT;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("p", String.valueOf(page));
        return new OkObject(params, url);
    }

    int page = 1;

    @Override
    public void onRefresh() {
        page = 1;
        ApiClient.post(this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("", s);
                try {
                    page++;
                    BonusDirect bonusDirect = GsonUtils.parseJSON(s, BonusDirect.class);
                    if (bonusDirect.getStatus() == 1) {
                        direct_bonus = bonusDirect.getDirect_bonus();
                        List<BonusDirect.DataBean> dataBeanList = bonusDirect.getData();
                        adapter.clear();
                        adapter.addAll(dataBeanList);
                    } else if (bonusDirect.getStatus() == 3) {
                        MyDialog.showReLoginDialog(ZhiTuiFenHongActivity.this);
                    } else {
                        showError(bonusDirect.getInfo());
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
                    View viewLoader = LayoutInflater.from(ZhiTuiFenHongActivity.this).inflate(R.layout.view_loaderror, null);
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
