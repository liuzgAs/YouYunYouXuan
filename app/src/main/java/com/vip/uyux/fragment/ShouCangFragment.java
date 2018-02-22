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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.vip.uyux.R;
import com.vip.uyux.activity.ChanPinXQActivity;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseFragment;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.interfacepage.OnBianJiListener;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.model.SimpleInfo;
import com.vip.uyux.model.UserCollect;
import com.vip.uyux.model.ZuJiShanChu;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.viewholder.ShouCangViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShouCangFragment extends ZjbBaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private String v;
    private View mInflate;
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<UserCollect.DataBean> adapter;
    public boolean isBianJi = false;
    private boolean isQuanXuan = false;
    private View viewDiBu;
    private View viewQuanXuan;
    private ImageView imageQuanXuan;
    private OnBianJiListener onBianJiListener;
    private BroadcastReceiver reciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case Constant.BroadcastCode.SHUA_XIN_SHOU_CANG:
                    onRefresh();
                    break;
                default:
                    break;
            }
        }
    };

    public ShouCangFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public ShouCangFragment(String v) {
        // Required empty public constructor
        this.v = v;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_shou_cang, container, false);
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
        viewDiBu = mInflate.findViewById(R.id.viewDiBu);
        viewQuanXuan = mInflate.findViewById(R.id.viewQuanXuan);
        imageQuanXuan = (ImageView) mInflate.findViewById(R.id.imageQuanXuan);
    }

    @Override
    protected void initViews() {
        viewDiBu.setVisibility(View.GONE);
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
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<UserCollect.DataBean>(mContext) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_zuji;
                return new ShouCangViewHolder(parent, layout);
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
                            UserCollect userCollect = GsonUtils.parseJSON(s, UserCollect.class);
                            int status = userCollect.getStatus();
                            if (status == 1) {
                                List<UserCollect.DataBean> dataBeanList = userCollect.getData();
                                int position = 0;
                                for (int i = 0; i < dataBeanList.size(); i++) {
                                    dataBeanList.get(i).setBianJi(false);
                                    dataBeanList.get(i).setSelect(false);
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
                if (isBianJi) {
                    if (adapter.getItem(position).isSelect()) {
                        adapter.getItem(position).setSelect(false);
                    } else {
                        adapter.getItem(position).setSelect(true);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Intent intent = new Intent();
                    intent.setClass(mContext, ChanPinXQActivity.class);
                    intent.putExtra(Constant.IntentKey.ID, adapter.getItem(position).getGoods_id());
                    startActivity(intent);
                }
            }
        });
        recyclerView.setRefreshListener(this);
    }

    @Override
    protected void setListeners() {
        mInflate.findViewById(R.id.textShanCHu).setOnClickListener(this);
        viewQuanXuan.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textShanCHu:
                shanChu();
                break;
            case R.id.viewQuanXuan:
                isQuanXuan = !isQuanXuan;
                if (isQuanXuan) {
                    imageQuanXuan.setImageResource(R.mipmap.xuanzhong);
                    for (int i = 0; i < adapter.getAllData().size(); i++) {
                        adapter.getItem(i).setSelect(true);
                        adapter.getItem(i).setBianJi(true);
                    }
                } else {
                    imageQuanXuan.setImageResource(R.mipmap.weixuanzhong);
                    for (int i = 0; i < adapter.getAllData().size(); i++) {
                        adapter.getItem(i).setSelect(false);
                        adapter.getItem(i).setBianJi(true);
                    }
                }
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

    /**
     * 删除足迹
     */
    private void shanChu() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < adapter.getAllData().size(); i++) {
            if (adapter.getItem(i).isSelect()) {
                list.add(adapter.getItem(i).getId());
            }
        }
        if (list.size() == 0) {
            MyDialog.showTipDialog(mContext, "请选择要取消收藏的商品");
            return;
        }
        showLoadingDialog();
        ZuJiShanChu zuJiShanChu = new ZuJiShanChu(1, "android", userInfo.getUid(), tokenTime, list);
        String url = Constant.HOST + Constant.Url.USER_COLLECTDEL;
        ApiClient.postJson(mContext, url, GsonUtils.parseObject(zuJiShanChu), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("WoDeSCActivity--onSuccess", s + "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus() == 1) {
                        viewDiBu.setVisibility(View.GONE);
                        imageQuanXuan.setImageResource(R.mipmap.weixuanzhong);
                        onBianJiListener.setBianJi("编辑");
                        onRefresh();
                    } else if (simpleInfo.getStatus() == 3) {
                        MyDialog.showReLoginDialog(mContext);
                    } else {
                        Toast.makeText(mContext, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(mContext, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(mContext, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    int page = 1;

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.USER_COLLECT;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("p", String.valueOf(page));
        params.put("state", v);
        return new OkObject(params, url);
    }

    @Override
    public void onRefresh() {
        page = 1;
        ApiClient.post(mContext, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("我的收藏", s);
                try {
                    page++;
                    UserCollect userCollect = GsonUtils.parseJSON(s, UserCollect.class);
                    if (userCollect.getStatus() == 1) {
                        List<UserCollect.DataBean> dataBeanList = userCollect.getData();
                        int position = 0;
                        for (int i = 0; i < dataBeanList.size(); i++) {
                            dataBeanList.get(i).setBianJi(false);
                            dataBeanList.get(i).setSelect(false);
                        }
                        adapter.clear();
                        adapter.addAll(dataBeanList);
                    } else if (userCollect.getStatus() == 3) {
                        MyDialog.showReLoginDialog(mContext);
                    } else {
                        showError(userCollect.getInfo());
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

    public void setOnBianJiListener(OnBianJiListener onBianJiListener) {
        this.onBianJiListener = onBianJiListener;
    }

    public void setBianJiBtn() {
        isQuanXuan = false;
        isBianJi = !isBianJi;
        if (isBianJi) {
            viewDiBu.setVisibility(View.VISIBLE);
            imageQuanXuan.setImageResource(R.mipmap.weixuanzhong);
            onBianJiListener.setBianJi("取消");
            for (int i = 0; i < adapter.getAllData().size(); i++) {
                adapter.getItem(i).setSelect(false);
                adapter.getItem(i).setBianJi(true);
            }
        } else {
            viewDiBu.setVisibility(View.GONE);
            imageQuanXuan.setImageResource(R.mipmap.weixuanzhong);
            onBianJiListener.setBianJi("编辑");
            for (int i = 0; i < adapter.getAllData().size(); i++) {
                adapter.getItem(i).setSelect(false);
                adapter.getItem(i).setBianJi(false);
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.BroadcastCode.SHUA_XIN_SHOU_CANG);
        mContext.registerReceiver(reciver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext.unregisterReceiver(reciver);
    }
}
