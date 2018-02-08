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
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.CartNeworder;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.model.OrderConfirmbeforeJiFen;
import com.vip.uyux.model.UserAddress;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.viewholder.QueRenDDJFViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QueRenDDJFActivity extends ZjbBaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<OrderConfirmbeforeJiFen.CartBean> adapter;
    private OrderConfirmbeforeJiFen.AdBean orderConfirmbeforeAd;
    private TextView textSum;
    private String sum;
    private int is_address;
    private String shipKey;
    private String shipDes;
    private int id;
    private String num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_que_ren_dd_jf);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        id = intent.getIntExtra(Constant.IntentKey.ID, 0);
        num = intent.getStringExtra(Constant.IntentKey.VALUE);
    }

    @Override
    protected void findID() {
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        textSum = (TextView) findViewById(R.id.textSum);
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
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<OrderConfirmbeforeJiFen.CartBean>(QueRenDDJFActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_quren_dd;
                return new QueRenDDJFViewHolder(parent, layout);
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
                View view = LayoutInflater.from(QueRenDDJFActivity.this).inflate(R.layout.header_address, null);
                textShouHuoRen = view.findViewById(R.id.textShouHuoRen);
                textPhone = view.findViewById(R.id.textPhone);
                textAddress = view.findViewById(R.id.textAddress);
                viewAddress = view.findViewById(R.id.viewAddress);
                viewNoAddress = view.findViewById(R.id.viewNoAddress);
                viewAddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setClass(QueRenDDJFActivity.this, XuanZeSHDZActivity.class);
                        startActivityForResult(intent, Constant.RequestResultCode.address);
                    }
                });
                viewNoAddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setClass(QueRenDDJFActivity.this, XuanZeSHDZActivity.class);
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
        adapter.addFooter(new RecyclerArrayAdapter.ItemView() {

            private TextView textShipKey;
            private TextView textShipDes;
            private TextView textSum1;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(QueRenDDJFActivity.this).inflate(R.layout.foot_queren_dd_jf, null);
                textSum1 = view.findViewById(R.id.textSum);
                textShipKey = view.findViewById(R.id.textShipKey);
                textShipDes = view.findViewById(R.id.textShipDes);
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                textShipKey.setText(shipKey);
                textShipDes.setText(shipDes);
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
        findViewById(R.id.buttonTiJiao).setOnClickListener(this);
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
                orderConfirmbeforeAd = new OrderConfirmbeforeJiFen.AdBean();
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
            case R.id.buttonTiJiao:
                if (orderConfirmbeforeAd == null) {
                    Toast.makeText(QueRenDDJFActivity.this, "请选择收货地址", Toast.LENGTH_SHORT).show();
                    return;
                }
                tiJiaoDD();
                break;
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
    private OkObject getOkQueRenObject() {
        String url = Constant.HOST + Constant.Url.SCORE_NEWORDER;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime",tokenTime);
        }
        params.put("aid",orderConfirmbeforeAd.getId());
        params.put("num",num);
        params.put("id",String.valueOf(id));
        return new OkObject(params, url);
    }

    /**
     * 提交订单
     */
    private void tiJiaoDD() {
        showLoadingDialog();
        ApiClient.post(QueRenDDJFActivity.this, getOkQueRenObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("QueRenDDActivity--onSuccess", s + "");
                try {
                    CartNeworder cartNeworder = GsonUtils.parseJSON(s, CartNeworder.class);
                    if (cartNeworder.getStatus() == 1) {
                        Intent intent = new Intent();
                        intent.setAction(Constant.BroadcastCode.SHUA_XIN_U_BI);
                        sendBroadcast(intent);
                        intent.setClass(QueRenDDJFActivity.this, DuiHuanJLActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (cartNeworder.getStatus() == 3) {
                        MyDialog.showReLoginDialog(QueRenDDJFActivity.this);
                    } else {
                        Toast.makeText(QueRenDDJFActivity.this, cartNeworder.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(QueRenDDJFActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(QueRenDDJFActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getDHOkObject() {
        String url = Constant.HOST + Constant.Url.SCORE_CONFIRMBEFORE;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime",tokenTime);
        }
        params.put("id",String.valueOf(id));
        params.put("num",num);
        return new OkObject(params, url);
    }

    @Override
    public void onRefresh() {

        ApiClient.post(this, getDHOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("确认订单前", s);
                try {
                    OrderConfirmbeforeJiFen orderConfirmbefore = GsonUtils.parseJSON(s, OrderConfirmbeforeJiFen.class);
                    if (orderConfirmbefore.getStatus() == 1) {
                        is_address = orderConfirmbefore.getIs_address();
                        orderConfirmbeforeAd = orderConfirmbefore.getAd();
                        sum = orderConfirmbefore.getSum();
                        textSum.setText("" + sum);
                        shipKey = orderConfirmbefore.getShipKey();
                        shipDes = orderConfirmbefore.getShipDes();
                        OrderConfirmbeforeJiFen.CartBean cartBean = orderConfirmbefore.getCart();
                        List<OrderConfirmbeforeJiFen.CartBean> cartBeanList =new ArrayList<>();
                        cartBeanList.add(cartBean);
                        adapter.clear();
                        adapter.addAll(cartBeanList);
                    } else if (orderConfirmbefore.getStatus() == 3) {
                        MyDialog.showReLoginDialog(QueRenDDJFActivity.this);
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
                    View viewLoader = LayoutInflater.from(QueRenDDJFActivity.this).inflate(R.layout.view_loaderror, null);
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
