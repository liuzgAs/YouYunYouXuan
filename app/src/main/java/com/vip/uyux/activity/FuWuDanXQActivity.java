package com.vip.uyux.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.AftersLogsinfo;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GlideApp;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.viewholder.FuWuDanXQViewHolder;

import java.util.HashMap;
import java.util.List;

public class FuWuDanXQActivity extends ZjbBaseActivity implements View.OnClickListener {

    private int id;
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<AftersLogsinfo.DesBean> adapter;
    private AftersLogsinfo aftersLogsinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fu_wu_dan_xq);
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
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("服务单详情");
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
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<AftersLogsinfo.DesBean>(FuWuDanXQActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_fuwudan_xq;
                return new FuWuDanXQViewHolder(parent, layout);
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {

            private ImageView imageImg;
            private TextView textSpe_name;
            private TextView textGoods_name;
            private TextView textShenQingSJ;
            private TextView textFuWuDanHao;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(FuWuDanXQActivity.this).inflate(R.layout.header_fuwudan_xq, null);
                textFuWuDanHao = view.findViewById(R.id.textFuWuDanHao);
                textShenQingSJ = view.findViewById(R.id.textShenQingSJ);
                textGoods_name = view.findViewById(R.id.textGoods_name);
                textSpe_name = view.findViewById(R.id.textSpe_name);
                imageImg = view.findViewById(R.id.imageImg);
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (aftersLogsinfo != null) {
                    GlideApp.with(FuWuDanXQActivity.this)
                            .load(aftersLogsinfo.getGoods().getImg())
                            .centerCrop()
                            .placeholder(R.mipmap.ic_empty)
                            .into(imageImg);
                    textFuWuDanHao.setText(aftersLogsinfo.getTitle().get(0));
                    textShenQingSJ.setText(aftersLogsinfo.getTitle().get(1));
                    textGoods_name.setText(aftersLogsinfo.getGoods().getName());
                    textSpe_name.setText(aftersLogsinfo.getGoods().getSpe_name());
                }
            }
        });
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.AFTERS_LOGSINFO;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("id", String.valueOf(id));
        return new OkObject(params, url);
    }

    @Override
    protected void initData() {
        ApiClient.post(this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("", s);
                try {
                    aftersLogsinfo = GsonUtils.parseJSON(s, AftersLogsinfo.class);
                    if (aftersLogsinfo.getStatus() == 1) {
                        List<AftersLogsinfo.DesBean> desBeanList = aftersLogsinfo.getDes();
                        adapter.clear();
                        adapter.addAll(desBeanList);
                    } else if (aftersLogsinfo.getStatus() == 3) {
                        MyDialog.showReLoginDialog(FuWuDanXQActivity.this);
                    } else {
                        showError(aftersLogsinfo.getInfo());
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
                    View viewLoader = LayoutInflater.from(FuWuDanXQActivity.this).inflate(R.layout.view_loaderror, null);
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
}
