package com.vip.uyux.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.EvaluationInfo;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.util.RecycleViewDistancaUtil;
import com.vip.uyux.viewholder.CePingViewHolder;
import com.vip.uyux.viewholder.CePingXQImgViewHolder;

import java.util.HashMap;
import java.util.List;

public class CePingXQActivity extends ZjbBaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<EvaluationInfo.DataBean> adapter;
    private View viewBar;
    private float guangGaoHeight;
    private int id;
    private EvaluationInfo evaluationInfo;
    private ImageView imageHead;
    private TextView textNickname;

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
        Intent intent = getIntent();
        id = intent.getIntExtra(Constant.IntentKey.ID, 0);
    }

    @Override
    protected void findID() {
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        viewBar = findViewById(R.id.viewBar);
        imageHead = (ImageView) findViewById(R.id.imageHead);
        textNickname = (TextView) findViewById(R.id.textNickname);
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
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<EvaluationInfo.DataBean>(CePingXQActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_ceping_pinglun;
                return new CePingViewHolder(parent, layout);
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {

            private TextView textContent;
            private ImageView imageTop;
            private RecyclerArrayAdapter<EvaluationInfo.ImgsBean> adapterImg;
            private EasyRecyclerView recyclerViewImg;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(CePingXQActivity.this).inflate(R.layout.header_ceping, null);
                recyclerViewImg = view.findViewById(R.id.recyclerView);
                imageTop = view.findViewById(R.id.imageTop);
                textContent = view.findViewById(R.id.textContent);
                initImgRecycler();
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (evaluationInfo != null) {
                    GlideApp.with(CePingXQActivity.this)
                            .load(evaluationInfo.getImg_url())
                            .centerCrop()
                            .placeholder(R.mipmap.ic_empty)
                            .into(new SimpleTarget<Drawable>(evaluationInfo.getImg_w(),evaluationInfo.getImg_h()) {
                                @Override
                                public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                    imageTop.setImageDrawable(resource);
                                }
                            });
                    textContent.setText(evaluationInfo.getTitle());
                    List<EvaluationInfo.ImgsBean> imgsBeanList = evaluationInfo.getImgs();
                    adapterImg.clear();
                    adapterImg.addAll(imgsBeanList);
                }
            }

            /**
             * 初始化recyclerview
             */
            private void initImgRecycler() {
                recyclerViewImg.setLayoutManager(new LinearLayoutManager(CePingXQActivity.this));
                recyclerViewImg.setRefreshingColorResources(R.color.basic_color);
                recyclerViewImg.setAdapterWithProgress(adapterImg = new RecyclerArrayAdapter<EvaluationInfo.ImgsBean>(CePingXQActivity.this) {
                    @Override
                    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                        int layout = R.layout.item_ceping_img;
                        return new CePingXQImgViewHolder(parent, layout);
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
                    viewBar.getBackground().mutate().setAlpha((int) (255f * percent));
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

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.EVALUATION_INFO;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("id", String.valueOf(id));
        return new OkObject(params, url);
    }

    @Override
    public void onRefresh() {
        ApiClient.post(this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("", s);
                try {
                    evaluationInfo = GsonUtils.parseJSON(s, EvaluationInfo.class);
                    if (evaluationInfo.getStatus() == 1) {
                        textNickname.setText(evaluationInfo.getNickname());
                        GlideApp.with(CePingXQActivity.this)
                                .load(evaluationInfo.getHeadimg())
                                .centerCrop()
                                .circleCrop()
                                .placeholder(R.mipmap.ic_empty)
                                .into(imageHead);
                        List<EvaluationInfo.DataBean> dataBeanList = evaluationInfo.getData();
                        adapter.clear();
                        adapter.addAll(dataBeanList);
                    } else if (evaluationInfo.getStatus() == 3) {
                        MyDialog.showReLoginDialog(CePingXQActivity.this);
                    } else {
                        showError(evaluationInfo.getInfo());
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
                    View viewLoader = LayoutInflater.from(CePingXQActivity.this).inflate(R.layout.view_loaderror, null);
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
