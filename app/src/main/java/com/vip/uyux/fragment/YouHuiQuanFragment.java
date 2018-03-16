package com.vip.uyux.fragment;


import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseFragment;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.interfacepage.OnFinishListener;
import com.vip.uyux.interfacepage.OnShareYouHuiQuanListener;
import com.vip.uyux.model.CouponIndex;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.model.ShareBean;
import com.vip.uyux.model.SimpleInfo;
import com.vip.uyux.model.YouHuiQuan;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.viewholder.YouHuiQuanViewHolder;

import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class YouHuiQuanFragment extends ZjbBaseFragment implements SwipeRefreshLayout.OnRefreshListener {


    private View mInflate;
    private String value;
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<YouHuiQuan> adapter;
    private IWXAPI api;
    private boolean isShare = false;
    ShareBean shareBeanX;
    private BroadcastReceiver reciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case Constant.BroadcastCode.WX_SHARE:
                    if (isShare) {
                        MyDialog.showTipDialog(mContext, "分享成功");
                        isShare = false;
                        shareHuiDiao();
                    }
                    break;
                case Constant.BroadcastCode.WX_SHARE_FAIL:
                    if (isShare) {
                        MyDialog.showTipDialog(mContext, "取消分享");
                        isShare = false;
                    }
                    break;
                default:
                    break;
            }
        }
    };
    private int id;

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
        params.put("shareType",String.valueOf(3));
        params.put("source",Constant.source);
        params.put("shareTitle",shareBeanX.getShareTitle());
        params.put("shareImg",shareBeanX.getShareImg());
        params.put("shareDes",shareBeanX.getShareDes());
        params.put("url",shareBeanX.getShareUrl());
        params.put("id",String.valueOf(id));
        return new OkObject(params, url);
    }

    /**
     * 分享回调
     */
    private void shareHuiDiao() {
        showLoadingDialog();
        ApiClient.post(mContext, getHDOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ChanPinXQActivity--onSuccess",s+ "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus()==1){
                        LogUtil.LogShitou("ChanPinXQActivity--onSuccess", "回调成功");
                    }else if (simpleInfo.getStatus()==3){
                        MyDialog.showReLoginDialog(mContext);
                    }else {
                        Toast.makeText(mContext, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(mContext,"数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(mContext, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public YouHuiQuanFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public YouHuiQuanFragment(String value) {
        this.value = value;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_you_hui_quan, container, false);
            api = WXAPIFactory.createWXAPI(mContext, Constant.WXAPPID, true);
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
    protected void initSP() {

    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void findID() {
        recyclerView = mInflate.findViewById(R.id.recyclerView);
    }

    @Override
    protected void initViews() {
        initRecycler();
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
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<YouHuiQuan>(mContext) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_youhuiquan;
                YouHuiQuanViewHolder youHuiQuanViewHolder = new YouHuiQuanViewHolder(parent, layout);
                youHuiQuanViewHolder.setOnFinishListener(new OnFinishListener() {
                    @Override
                    public void toFinish(int position) {
                        Intent intent = new Intent();
                        intent.setAction(Constant.BroadcastCode.SET_MAIN_TAB);
                        intent.putExtra(Constant.IntentKey.POSITION,2);
                        getActivity().sendBroadcast(intent);
                        getActivity().finish();
                    }
                });
                youHuiQuanViewHolder.setOnShareYouHuiQuanListener(new OnShareYouHuiQuanListener() {
                    @Override
                    public void share(YouHuiQuan dataBean) {
                        isShare=true;
                        id = dataBean.getId();
                        MyDialog.shareYouHuiQuan(mContext,api,dataBean);
                    }
                });
                return youHuiQuanViewHolder;
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                ApiClient.post(mContext, getOkObject(), new ApiClient.CallBack() {
                    @Override
                    public void onSuccess(String s) {
                        LogUtil.LogShitou("DingDanGLActivity--加载更多", s + "");
                        try {
                            page++;
                            CouponIndex couponIndex = GsonUtils.parseJSON(s, CouponIndex.class);
                            int status = couponIndex.getStatus();
                            if (status == 1) {
                                List<YouHuiQuan> dataBeanList = couponIndex.getData();
                                for (int i = 0; i < dataBeanList.size(); i++) {
                                    dataBeanList.get(i).setZhanKai(false);
                                }
                                adapter.addAll(dataBeanList);
                            } else if (status == 3) {
                                MyDialog.showReLoginDialog(mContext);
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
    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }

    int page = 1;

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.COUPON_INDEX;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("state", value);
        params.put("p", String.valueOf(page));
        return new OkObject(params, url);
    }

    @Override
    public void onRefresh() {
        page = 1;
        ApiClient.post(mContext, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("", s);
                try {
                    page++;
                    CouponIndex couponIndex = GsonUtils.parseJSON(s, CouponIndex.class);
                    if (couponIndex.getStatus() == 1) {
                        List<YouHuiQuan> dataBeanList = couponIndex.getData();
                        for (int i = 0; i < dataBeanList.size(); i++) {
                            dataBeanList.get(i).setZhanKai(false);
                        }
                        adapter.clear();
                        adapter.addAll(dataBeanList);
                    } else if (couponIndex.getStatus() == 3) {
                        MyDialog.showReLoginDialog(mContext);
                    } else {
                        showError(couponIndex.getInfo());
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
                    View viewLoader = LayoutInflater.from(mContext).inflate(R.layout.view_loaderror, null);
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
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.BroadcastCode.WX_SHARE);
        filter.addAction(Constant.BroadcastCode.WX_SHARE_FAIL);
        getActivity().registerReceiver(reciver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(reciver);
    }
}
