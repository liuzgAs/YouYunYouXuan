package com.vip.uyux.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.vip.uyux.R;
import com.vip.uyux.adapter.YouHuiQuanAdapter;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.CartNeworder;
import com.vip.uyux.model.JieSuan;
import com.vip.uyux.model.OrderConfirmbefore;
import com.vip.uyux.model.OrderTiJiao;
import com.vip.uyux.model.QueRenDD;
import com.vip.uyux.model.UserAddress;
import com.vip.uyux.util.ACache;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.Arith;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.viewholder.QueRenDDViewHolder;

import java.util.List;

public class QueRenDDActivity extends ZjbBaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<OrderConfirmbefore.CartBean> adapter;
    private JieSuan jieSuan;
    private OrderConfirmbefore.AdBean orderConfirmbeforeAd;
    private TextView textSum;
    private String sum;
    private int is_address;
    private int vipLv;
    private String vipKey;
    private String vipDes;
    private String shipKey;
    private String shipDes;
    private BroadcastReceiver reciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case Constant.BroadcastCode.ZHI_FU_CG:
                    finish();
                    break;
                default:
                    break;
            }
        }
    };
    private List<OrderConfirmbefore.CouponBean> couponBeanList;
    private String youHuiQuan;
    private Integer couponId;

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
        adapter.addFooter(new RecyclerArrayAdapter.ItemView() {

            private View viewYouHuiQuan;
            private TextView textYouHuiQuan;
            private TextView textVipKey;
            private TextView textShipKey;
            private TextView textShipDes;
            private TextView textVipDes;
            private TextView textVip;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(QueRenDDActivity.this).inflate(R.layout.foot_queren_dd, null);
                textVip = view.findViewById(R.id.textVip);
                textVipDes = view.findViewById(R.id.textVipDes);
                textShipKey = view.findViewById(R.id.textShipKey);
                textShipDes = view.findViewById(R.id.textShipDes);
                textVipKey = view.findViewById(R.id.textVipKey);
                textYouHuiQuan = view.findViewById(R.id.textYouHuiQuan);
                viewYouHuiQuan = view.findViewById(R.id.viewYouHuiQuan);
                viewYouHuiQuan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showYouHuiQuanDialog();
                    }
                });
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                textVip.setText("LV" + vipLv);
                textVipDes.setText(vipDes);
                textVipKey.setText(vipKey);
                textShipKey.setText(shipKey);
                textShipDes.setText(shipDes);
                if (couponBeanList!=null){
                    if (couponBeanList.size()>0){
                        viewYouHuiQuan.setVisibility(View.VISIBLE);
                        textYouHuiQuan.setHint(couponBeanList.size()+"张优惠券");
                        if (!TextUtils.isEmpty(youHuiQuan)){
                            textYouHuiQuan.setText(youHuiQuan);
                        }
                    }else {
                        viewYouHuiQuan.setVisibility(View.GONE);
                    }
                }else {
                    viewYouHuiQuan.setVisibility(View.GONE);
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
            case R.id.buttonTiJiao:
                if (orderConfirmbeforeAd == null) {
                    Toast.makeText(QueRenDDActivity.this, "请选择收货地址", Toast.LENGTH_SHORT).show();
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
    private String getOkQueRenObject() {
        ACache aCache = ACache.get(this, Constant.Acache.LOCATION);
        String did = aCache.getAsString(Constant.Acache.DID);
        OrderTiJiao orderTiJiao;
        if (isLogin) {
            orderTiJiao = new OrderTiJiao(1, "android", userInfo.getUid(), tokenTime, jieSuan.getIntegerList(), String.valueOf(sum), did, orderConfirmbeforeAd.getId(),couponId);
        } else {
            orderTiJiao = new OrderTiJiao(1, "android", jieSuan.getIntegerList(), sum, did, orderConfirmbeforeAd.getId(),couponId);
        }
        return GsonUtils.parseObject(orderTiJiao);
    }

    /**
     * 提交订单
     */
    private void tiJiaoDD() {
        String url = Constant.HOST + Constant.Url.ORDER_NEWORDER;
        showLoadingDialog();
        ApiClient.postJson(QueRenDDActivity.this, url, getOkQueRenObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("QueRenDDActivity--onSuccess", s + "");
                try {
                    CartNeworder cartNeworder = GsonUtils.parseJSON(s, CartNeworder.class);
                    if (cartNeworder.getStatus() == 1) {
                        Intent intent = new Intent();
                        intent.setAction(Constant.BroadcastCode.SHUA_XIN_CAR);
                        sendBroadcast(intent);
                        intent.putExtra(Constant.IntentKey.ID, cartNeworder.getOid());
                        intent.putExtra(Constant.IntentKey.VALUE, sum);
                        intent.setClass(QueRenDDActivity.this, LiJiZFActivity.class);
                        startActivity(intent);
                    } else if (cartNeworder.getStatus() == 3) {
                        MyDialog.showReLoginDialog(QueRenDDActivity.this);
                    } else {
                        Toast.makeText(QueRenDDActivity.this, cartNeworder.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(QueRenDDActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(QueRenDDActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
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
                        is_address = orderConfirmbefore.getIs_address();
                        orderConfirmbeforeAd = orderConfirmbefore.getAd();
                        sum = orderConfirmbefore.getSum();
                        textSum.setText("¥" + sum);
                        vipLv = orderConfirmbefore.getVipLv();
                        vipKey = orderConfirmbefore.getVipKey();
                        vipDes = orderConfirmbefore.getVipDes();
                        shipKey = orderConfirmbefore.getShipKey();
                        shipDes = orderConfirmbefore.getShipDes();
                        couponBeanList = orderConfirmbefore.getCoupon();
                        youHuiQuan = orderConfirmbefore.getYouHuiQuan();
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

    /**
     * 促销dialog
     */
    private void showYouHuiQuanDialog() {
        View dialog_tu_pian = LayoutInflater.from(this).inflate(R.layout.dialog_chuciao, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this, R.style.dialog)
                .setView(dialog_tu_pian)
                .create();
        alertDialog.show();
        TextView textTitle = dialog_tu_pian.findViewById(R.id.textTitle);
        textTitle.setText("优惠券");
        ListView listView = dialog_tu_pian.findViewById(R.id.listView);
        listView.setAdapter(new YouHuiQuanAdapter(this, couponBeanList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String couponMoney = couponBeanList.get(i).getMoney();
                double sumX = Arith.sub(Double.parseDouble(sum) ,Double.parseDouble(couponMoney));
                sum = String.valueOf(sumX);
                textSum.setText("¥" + sum);
                youHuiQuan="¥"+ couponMoney;
                couponId = couponBeanList.get(i).getId();
                adapter.notifyDataSetChanged();
                alertDialog.dismiss();
            }
        });
        dialog_tu_pian.findViewById(R.id.textQuXiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        Window dialogWindow = alertDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.dialogFenXiang);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 1); // 高度设置为屏幕的0.6
        lp.height = (int) (d.heightPixels * 0.5f); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.BroadcastCode.ZHI_FU_CG);
        registerReceiver(reciver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(reciver);
    }
}
