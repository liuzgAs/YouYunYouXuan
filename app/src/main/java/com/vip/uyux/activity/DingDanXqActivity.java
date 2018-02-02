package com.vip.uyux.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import com.vip.uyux.customview.TwoBtnDialog;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.model.SimpleInfo;
import com.vip.uyux.model.UserOrderinfo;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.viewholder.DDXQViewHolder;
import com.vip.uyux.viewholder.DingDanXQDesViewHolder;

import java.util.HashMap;
import java.util.List;

public class DingDanXqActivity extends ZjbBaseActivity implements View.OnClickListener {

    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<UserOrderinfo.DataBean.ListBeanX> adapter;
    private String id;
    private UserOrderinfo userOrderinfo;
    private TextView textCancle;
    private Button btnPingJia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding_dan_xq);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        id = intent.getStringExtra(Constant.IntentKey.ID);
    }

    @Override
    protected void findID() {
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        textCancle = (TextView) findViewById(R.id.textCancle);
        btnPingJia = (Button) findViewById(R.id.btnPingJia);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("订单详情");
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
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<UserOrderinfo.DataBean.ListBeanX>(DingDanXqActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_ddxq;
                return new DDXQViewHolder(parent, layout);
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {

            private View viewAddress;
            private TextView textAreaAddress;
            private TextView textPhone;
            private TextView textConsignee;
            private TextView textLogDes;
            private TextView textLogTitle;
            private ImageView imageStatus;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(DingDanXqActivity.this).inflate(R.layout.header_ddxq, null);
                imageStatus = view.findViewById(R.id.imageStatus);
                textLogTitle = view.findViewById(R.id.textLogTitle);
                textLogDes = view.findViewById(R.id.textLogDes);
                textConsignee = view.findViewById(R.id.textConsignee);
                textPhone = view.findViewById(R.id.textPhone);
                textAreaAddress = view.findViewById(R.id.textAreaAddress);
                viewAddress = view.findViewById(R.id.viewAddress);
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (userOrderinfo != null) {
                    switch (userOrderinfo.getLogState()) {
                        case 1:
                            imageStatus.setImageResource(R.mipmap.dingdan_xq_daifukuan);
                            break;
                        case 2:
                            imageStatus.setImageResource(R.mipmap.dingdan_xq_yushuzhong);
                            break;
                        case 3:
                            imageStatus.setImageResource(R.mipmap.dingdan_xq_pingjia);
                            break;
                        case 4:
                            imageStatus.setImageResource(R.mipmap.dingdan_xq_wancheng);
                            break;
                        default:
                            break;
                    }
                    textLogTitle.setText(userOrderinfo.getLogTitle());
                    textLogDes.setText(userOrderinfo.getLogDes());
                    UserOrderinfo.AddressBean address = userOrderinfo.getAddress();
                    if (address!=null){
                        viewAddress.setVisibility(View.VISIBLE);
                        textConsignee.setText(address.getConsignee());
                        textPhone.setText(address.getPhone());
                        textAreaAddress.setText(address.getArea()+"-"+address.getAddress());
                    }else {
                        viewAddress.setVisibility(View.GONE);
                    }
                }
            }
        });
        adapter.addFooter(new RecyclerArrayAdapter.ItemView() {

            private RecyclerArrayAdapter<String> adapterDes;
            private EasyRecyclerView recyclerViewFoot;
            private TextView textCreate_time;
            private TextView textOrderSn;
            private TextView textSum;
            private TextView textSumDes;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(DingDanXqActivity.this).inflate(R.layout.footer_dingdan_xq, null);
                textSumDes = view.findViewById(R.id.textSumDes);
                textSum = view.findViewById(R.id.textSum);
                textOrderSn = view.findViewById(R.id.textOrderSn);
                textCreate_time = view.findViewById(R.id.textCreate_time);
                recyclerViewFoot = view.findViewById(R.id.recyclerView);
                initRecycler();
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (userOrderinfo!=null){
                    textSumDes.setText(userOrderinfo.getData().getSumDes());
                    textSum.setText("¥"+userOrderinfo.getData().getSum());
                    textOrderSn.setText(userOrderinfo.getData().getOrderSn());
                    textCreate_time.setText(userOrderinfo.getData().getCreate_time());
                    List<String> desList = userOrderinfo.getData().getDesList();
                    adapterDes.clear();
                    adapterDes.addAll(desList);
                }
            }

            /**
             * 初始化recyclerview
             */
            private void initRecycler() {
                recyclerView.setLayoutManager(new LinearLayoutManager(DingDanXqActivity.this));
                DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.line_width), 0, 0);
                itemDecoration.setDrawLastItem(false);
                recyclerView.addItemDecoration(itemDecoration);
                recyclerView.setRefreshingColorResources(R.color.basic_color);
                recyclerView.setAdapterWithProgress(adapterDes = new RecyclerArrayAdapter<String>(DingDanXqActivity.this) {
                    @Override
                    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                        int layout = R.layout.item_deslist;
                        return new DingDanXQDesViewHolder(parent, layout);
                    }
                });
            }
        });
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.textCancle).setOnClickListener(this);
        findViewById(R.id.btnPingJia).setOnClickListener(this);
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.USER_ORDERINFO;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("id", id);
        return new OkObject(params, url);
    }

    @Override
    protected void initData() {
        ApiClient.post(this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("", s);
                try {
                    userOrderinfo = GsonUtils.parseJSON(s, UserOrderinfo.class);
                    if (userOrderinfo.getStatus() == 1) {
                        if (userOrderinfo.getData().getGoPay()==1){
                            btnPingJia.setVisibility(View.VISIBLE);
                        }else {
                            btnPingJia.setVisibility(View.GONE);
                        }
                        if (userOrderinfo.getData().getIsCancel()==1){
                            textCancle.setVisibility(View.VISIBLE);
                        }else {
                            textCancle.setVisibility(View.GONE);
                        }
                        List<UserOrderinfo.DataBean.ListBeanX> listBeanXList = userOrderinfo.getData().getList();
                        adapter.clear();
                        adapter.addAll(listBeanXList);
                    } else if (userOrderinfo.getStatus() == 3) {
                        MyDialog.showReLoginDialog(DingDanXqActivity.this);
                    } else {
                        showError(userOrderinfo.getInfo());
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
                    View viewLoader = LayoutInflater.from(DingDanXqActivity.this).inflate(R.layout.view_loaderror, null);
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
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getQXOkObject() {
        String url = Constant.HOST + Constant.Url.USER_CANCELORDER;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime",tokenTime);
        }
        params.put("id",String.valueOf(userOrderinfo.getData().getId()));
        return new OkObject(params, url);
    }

    /**
     * 取消订单
     */
    private void quXiaoDingDan() {
        showLoadingDialog();
        ApiClient.post(this, getQXOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
               cancelLoadingDialog();
                LogUtil.LogShitou("DDViewHolder--onSuccess",s+ "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus()==1){
                        Intent intent = new Intent();
                        intent.setAction(Constant.BroadcastCode.SHUAXINDD);
                        sendBroadcast(intent);
                    }else if (simpleInfo.getStatus()==3){
                        MyDialog.showReLoginDialog(DingDanXqActivity.this);
                    }else {
                        Toast.makeText(DingDanXqActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(DingDanXqActivity.this,"数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(DingDanXqActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textCancle:
                final TwoBtnDialog twoBtnDialog = new TwoBtnDialog(this, "确定要取消该订单吗？", "是", "否");
                twoBtnDialog.show();
                twoBtnDialog.setClicklistener(new TwoBtnDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm() {
                        twoBtnDialog.dismiss();
                        quXiaoDingDan();
                    }

                    @Override
                    public void doCancel() {
                        twoBtnDialog.dismiss();
                    }
                });
                break;
            case R.id.btnPingJia:
                Intent intent = new Intent();
                intent.putExtra(Constant.IntentKey.ID, userOrderinfo.getData().getOid());
                intent.putExtra(Constant.IntentKey.VALUE, userOrderinfo.getData().getSum());
                intent.setClass(this, LiJiZFActivity.class);
                startActivity(intent);
                break;
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }
}
