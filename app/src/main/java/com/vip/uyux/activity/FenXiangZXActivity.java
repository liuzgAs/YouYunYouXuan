package com.vip.uyux.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.model.ShareBean;
import com.vip.uyux.model.ShareIndex;
import com.vip.uyux.model.SimpleInfo;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.viewholder.FenXiangZXViewHolder;

import java.util.HashMap;

public class FenXiangZXActivity extends ZjbBaseActivity implements View.OnClickListener {
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<ShareIndex> adapter;
    private IWXAPI api;
    private boolean isShare = false;
    ShareBean shareBeanX;
    int shareType;
    private BroadcastReceiver reciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case Constant.BroadcastCode.WX_SHARE:
                    if (isShare) {
                        MyDialog.showTipDialog(FenXiangZXActivity.this, "分享成功");
                        isShare = false;
                        shareHuiDiao();
                    }
                    break;
                case Constant.BroadcastCode.WX_SHARE_FAIL:
                    if (isShare) {
                        MyDialog.showTipDialog(FenXiangZXActivity.this, "取消分享");
                        isShare = false;
                    }
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getHDOkObject() {
        String url = Constant.HOST + Constant.Url.SHARE_SHAREAFTER;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime",tokenTime);
        }
        params.put("shareType",String.valueOf(shareType));
        params.put("source",Constant.source);
        params.put("shareTitle",shareBeanX.getShareTitle());
        params.put("shareImg",shareBeanX.getShareImg());
        params.put("shareDes",shareBeanX.getShareDes());
        params.put("url",shareBeanX.getShareUrl());
        params.put("id",userInfo.getUid());
        return new OkObject(params, url);
    }

    /**
     * 分享回调
     */
    private void shareHuiDiao() {
        showLoadingDialog();
        ApiClient.post(FenXiangZXActivity.this, getHDOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ChanPinXQActivity--onSuccess",s+ "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus()==1){
                        LogUtil.LogShitou("ChanPinXQActivity--onSuccess", "回调成功");
                    }else if (simpleInfo.getStatus()==3){
                        MyDialog.showReLoginDialog(FenXiangZXActivity.this);
                    }else {
                        Toast.makeText(FenXiangZXActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(FenXiangZXActivity.this,"数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(FenXiangZXActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fen_xiang_zx);
        api = WXAPIFactory.createWXAPI(this, Constant.WXAPPID, true);
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
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<ShareIndex>(FenXiangZXActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_fenxiangzhongxin;
                FenXiangZXViewHolder fenXiangZXViewHolder = new FenXiangZXViewHolder(parent, layout);
                fenXiangZXViewHolder.setOnShareListener(new FenXiangZXViewHolder.OnShareListener() {
                    @Override
                    public void share(ShareBean shareBean,int shareType) {
                        FenXiangZXActivity.this.shareType =shareType;
                        shareBeanX = shareBean;
                        isShare=true;
                        MyDialog.share01(getContext(), api, shareBean.getShareUrl(), shareBean.getShareImg(), shareBean.getShareTitle(), shareBean.getShareDes());
                    }
                });
                return fenXiangZXViewHolder;
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
        String url = Constant.HOST + Constant.Url.SHARE_INDEX;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        return new OkObject(params, url);
    }

    @Override
    protected void initData() {
        ApiClient.post(this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("", s);
                try {
                    ShareIndex shareIndex = GsonUtils.parseJSON(s, ShareIndex.class);
                    if (shareIndex.getStatus() == 1) {
                        adapter.clear();
                        adapter.add(shareIndex);
                        adapter.notifyDataSetChanged();
                    } else if (shareIndex.getStatus()== 3) {
                        MyDialog.showReLoginDialog(FenXiangZXActivity.this);
                    } else {
                        showError(shareIndex.getInfo());
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
                    View viewLoader = LayoutInflater.from(FenXiangZXActivity.this).inflate(R.layout.view_loaderror, null);
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

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.BroadcastCode.WX_SHARE);
        filter.addAction(Constant.BroadcastCode.WX_SHARE_FAIL);
        registerReceiver(reciver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(reciver);
    }

}
