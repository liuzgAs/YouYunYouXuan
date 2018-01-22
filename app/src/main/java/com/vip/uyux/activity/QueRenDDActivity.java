package com.vip.uyux.activity;

import android.content.Intent;
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
import com.vip.uyux.model.JieSuan;
import com.vip.uyux.model.OrderConfirmbefore;
import com.vip.uyux.model.QueRenDD;
import com.vip.uyux.model.UserAddress;
import com.vip.uyux.util.ACache;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.viewholder.QueRenDDViewHolder;

import java.util.List;

public class QueRenDDActivity extends ZjbBaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<OrderConfirmbefore.CartBean> adapter;
    private JieSuan jieSuan;
    private OrderConfirmbefore.AdBean orderConfirmbeforeAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_que_ren_dd);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        jieSuan = (JieSuan) intent.getSerializableExtra(Constant.IntentKey.BEAN);
    }

    @Override
    protected void findID() {
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("确认订单");
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
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<OrderConfirmbefore.CartBean>(QueRenDDActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_quren_dd;
                return new QueRenDDViewHolder(parent, layout);
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {

            private TextView textAddress;
            private TextView textPhone;
            private TextView textShouHuoRen;
            private View viewNoAddress;
            private View viewAddress;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(QueRenDDActivity.this).inflate(R.layout.header_address, null);
                textShouHuoRen = view.findViewById(R.id.textShouHuoRen);
                textPhone = view.findViewById(R.id.textPhone);
                textAddress = view.findViewById(R.id.textAddress);
                viewAddress = view.findViewById(R.id.viewAddress);
                viewNoAddress = view.findViewById(R.id.viewNoAddress);
                viewAddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setClass(QueRenDDActivity.this, XuanZeSHDZActivity.class);
                        startActivityForResult(intent, Constant.RequestResultCode.address);
                    }
                });
                viewNoAddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setClass(QueRenDDActivity.this, XuanZeSHDZActivity.class);
                        startActivityForResult(intent, Constant.RequestResultCode.address);
                    }
                });
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (orderConfirmbeforeAd != null) {
                    viewAddress.setVisibility(View.VISIBLE);
                    viewNoAddress.setVisibility(View.GONE);
                    textShouHuoRen.setText("收件人：" + orderConfirmbeforeAd.getConsignee());
                    textPhone.setText(orderConfirmbeforeAd.getPhone());
                    textAddress.setText(orderConfirmbeforeAd.getArea() + "-" + orderConfirmbeforeAd.getAddress());
                } else {
                    viewAddress.setVisibility(View.GONE);
                    viewNoAddress.setVisibility(View.VISIBLE);
                }
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.RequestResultCode.address && resultCode == Constant.RequestResultCode.address) {
            UserAddress.DataBean dataBean = (UserAddress.DataBean) data.getSerializableExtra(Constant.IntentKey.VALUE);
            if (orderConfirmbeforeAd == null) {
                orderConfirmbeforeAd = new OrderConfirmbefore.AdBean();
            }
            orderConfirmbeforeAd.setConsignee(dataBean.getConsignee());
            orderConfirmbeforeAd.setId(dataBean.getId());
            orderConfirmbeforeAd.setAddress(dataBean.getAddress());
            orderConfirmbeforeAd.setArea(dataBean.getArea());
            orderConfirmbeforeAd.setPhone(dataBean.getPhone());
            adapter.notifyDataSetChanged();
        }
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
    private String getOkObject() {
        ACache aCache = ACache.get(this, Constant.Acache.LOCATION);
        String did = aCache.getAsString(Constant.Acache.DID);
        QueRenDD queRenDD;
        if (isLogin) {
            queRenDD = new QueRenDD(1, "android", userInfo.getUid(), tokenTime, jieSuan.getIntegerList(), did);
        } else {
            queRenDD = new QueRenDD(1, "android", jieSuan.getIntegerList(), did);
        }
        return GsonUtils.parseObject(queRenDD);
    }

    @Override
    public void onRefresh() {
        String url = Constant.HOST + Constant.Url.ORDER_CONFIRMBEFORE;
        ApiClient.postJson(this, url, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("确认订单前", s);
                try {
                    OrderConfirmbefore orderConfirmbefore = GsonUtils.parseJSON(s, OrderConfirmbefore.class);
                    if (orderConfirmbefore.getStatus() == 1) {
                        orderConfirmbeforeAd = orderConfirmbefore.getAd();
                        List<OrderConfirmbefore.CartBean> cartBeanList = orderConfirmbefore.getCart();
                        adapter.clear();
                        adapter.addAll(cartBeanList);
                    } else if (orderConfirmbefore.getStatus() == 3) {
                        MyDialog.showReLoginDialog(QueRenDDActivity.this);
                    } else {
                        showError(orderConfirmbefore.getInfo());
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
                    View viewLoader = LayoutInflater.from(QueRenDDActivity.this).inflate(R.layout.view_loaderror, null);
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
