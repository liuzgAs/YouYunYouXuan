package com.vip.uyux.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.customview.SingleBtnDialog;
import com.vip.uyux.model.AliPayBean;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.model.OrderPay;
import com.vip.uyux.model.UserMy;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;

import java.util.HashMap;
import java.util.Map;

public class LiJiZFActivity extends ZjbBaseActivity implements View.OnClickListener {

    private String order;
    private String price;
    private TextView textBlance;
    private int payMode = 0;
    private View[] paySelectView = new View[3];
    private View[] payView = new View[3];
    final IWXAPI api = WXAPIFactory.createWXAPI(this, null);
    private BroadcastReceiver recevier = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case Constant.BroadcastCode.PAY_RECEIVER:
                    cancelLoadingDialog();
                    int error = intent.getIntExtra("error", -1);
                    if (error == 0) {
                        paySuccess();
                    } else if (error == -1) {
                        MyDialog.showTipDialog(LiJiZFActivity.this, "支付失败");
                    } else if (error == -2) {
                        MyDialog.showTipDialog(LiJiZFActivity.this, "支付失败");
                    }
                    break;
                default:
                    break;
            }
        }
    };
    private String oid;
    private OrderPay orderPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_li_ji_zf);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        oid = intent.getStringExtra(Constant.IntentKey.ID);
        price = intent.getStringExtra(Constant.IntentKey.VALUE);
    }

    @Override
    protected void findID() {
        textBlance = (TextView) findViewById(R.id.textBlance);
        paySelectView[0] = findViewById(R.id.imageYuE);
        paySelectView[1] = findViewById(R.id.imageZhiFuBao);
        paySelectView[2] = findViewById(R.id.imageWeiXin);
        payView[0] = findViewById(R.id.viewYuE);
        payView[1] = findViewById(R.id.viewZhiFuBao);
        payView[2] = findViewById(R.id.viewWeiXin);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("立即支付");
        ((TextView) findViewById(R.id.textPrice)).setText("¥" + price);
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        for (int i = 0; i < payView.length; i++) {
            final int finalI = i;
            payView[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int j = 0; j < paySelectView.length; j++) {
                        paySelectView[j].setVisibility(View.GONE);
                    }
                    paySelectView[finalI].setVisibility(View.VISIBLE);
                    payMode = finalI;
                }
            });
        }
        findViewById(R.id.btnLiJiZF).setOnClickListener(this);
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.USER_MY;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        return new OkObject(params, url);
    }

    @Override
    protected void initData() {
        showLoadingDialog();
        ApiClient.post(LiJiZFActivity.this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("LiJiZFActivity--onSuccess", s + "");
                try {

                    UserMy userMy = GsonUtils.parseJSON(s, UserMy.class);
                    if (userMy.getStatus() == 1) {
                        textBlance.setText("\u3000|\u3000余额：¥" + userMy.getMoney());
                    } else if (userMy.getStatus() == 3) {
                        MyDialog.showReLoginDialog(LiJiZFActivity.this);
                    } else {
                        Toast.makeText(LiJiZFActivity.this, userMy.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(LiJiZFActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(LiJiZFActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * des： 支付成功
     * author： ZhangJieBo
     * date： 2017/12/25/025 16:11
     */
    private void paySuccess() {
        Intent intent = new Intent();
        intent.setAction(Constant.BroadcastCode.ZHI_FU_CG);
        sendBroadcast(intent);
        final SingleBtnDialog singleBtnDialog = new SingleBtnDialog(this, "支付成功", "确认");
        singleBtnDialog.setClicklistener(new SingleBtnDialog.ClickListenerInterface() {
            @Override
            public void doWhat() {
                singleBtnDialog.dismiss();
//                Intent intent1 = new Intent();
//                intent1.setClass(LiJiZFActivity.this,DingDanXQActivity.class);
//                intent1.putExtra(Constant.IntentKey.VALUE,order);
//                startActivity(intent1);
                finish();
            }
        });
        singleBtnDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    dialog.dismiss();
                    finish();
                }
                return false;
            }
        });
        singleBtnDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLiJiZF:
                if (payMode == 0) {
                    MyDialog.showTipDialog(this,"余额支付暂未开通");
//                    yuEZhiFu();
                } else {
                    zhiFu();
                }
                break;
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }

//    /**
//     * des： 网络请求参数
//     * author： ZhangJieBo
//     * date： 2017/8/28 0028 上午 9:55
//     */
//    private OkObject getOkYEObject() {
//        String url = Constant.HOST + Constant.Url.;
//        HashMap<String, String> params = new HashMap<>();
//        if (isLogin) {
//            params.put("uid", userInfo.getUid());
//            params.put("tokenTime",tokenTime);
//        }
//        params.put("p",String.valueOf(page));
//        return new OkObject(params, url);
//    }
//
//    /**
//     * 余额支付
//     */
//    private void yuEZhiFu() {
//        showLoadingDialog();
//        ApiClient.post(LiJiZFActivity.this, getOkYEObject(), new ApiClient.CallBack() {
//            @Override
//            public void onSuccess(String s) {
//                cancelLoadingDialog();
//                LogUtil.LogShitou("LiJiZFActivity--onSuccess",s+ "");
//                try {
//                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
//                    if (simpleInfo.getStatus()==1){
//                    }else if (simpleInfo.getStatus()==3){
//                        MyDialog.showReLoginDialog(LiJiZFActivity.this);
//                    }else {
//                        Toast.makeText(LiJiZFActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
//                    }
//                } catch (Exception e) {
//                    Toast.makeText(LiJiZFActivity.this,"数据出错", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onError() {
//                cancelLoadingDialog();
//                Toast.makeText(LiJiZFActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getZFOkObject() {
        String url = Constant.HOST + Constant.Url.ORDER_PAY;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("oid", oid + "");
        return new OkObject(params, url);
    }

    /**
     * 支付
     */
    private void zhiFu() {
        showLoadingDialog();
        ApiClient.post(LiJiZFActivity.this, getZFOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("LiJiZFActivity--onSuccess", s + "");
                try {
                    orderPay = GsonUtils.parseJSON(s, OrderPay.class);
                    if (orderPay.getStatus() == 1) {
                        pay();
                    } else if (orderPay.getStatus() == 3) {
                        MyDialog.showReLoginDialog(LiJiZFActivity.this);
                    } else {
                        Toast.makeText(LiJiZFActivity.this, orderPay.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(LiJiZFActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(LiJiZFActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 支付宝
     */
    private void pay() {
        switch (payMode) {
            case 1:
                Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        try {
                            PayTask alipay = new PayTask(LiJiZFActivity.this);
                            Map<String, String> stringMap = alipay.payV2(orderPay.getPayAli(), true);
                            final AliPayBean aliPayBean = GsonUtils.parseJSON(stringMap.get("result"), AliPayBean.class);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (aliPayBean == null) {
                                        MyDialog.showTipDialog(LiJiZFActivity.this, "支付失败");
                                        return;
                                    }
                                    switch (aliPayBean.getAlipay_trade_app_pay_response().getCode()) {
                                        case 10000:
                                            paySuccess();
                                            break;
                                        case 8000:
                                            paySuccess();
                                            break;
                                        case 4000:
                                            MyDialog.showTipDialog(LiJiZFActivity.this, "订单支付失败");
                                            break;
                                        case 5000:
                                            MyDialog.showTipDialog(LiJiZFActivity.this, "重复请求");
                                            break;
                                        case 6001:
                                            MyDialog.showTipDialog(LiJiZFActivity.this, "取消支付");
                                            break;
                                        case 6002:
                                            MyDialog.showTipDialog(LiJiZFActivity.this, "网络连接错误");
                                            break;
                                        case 6004:
                                            MyDialog.showTipDialog(LiJiZFActivity.this, "支付结果未知");
                                            break;
                                        default:
                                            MyDialog.showTipDialog(LiJiZFActivity.this, "支付失败");
                                            break;
                                    }
                                }
                            });

                        } catch (Exception e) {

                        }
                    }
                };
                // 必须异步调用
                Thread payThread = new Thread(payRunnable);
                payThread.start();
                break;
            case 2:
                wechatPay();
                break;
            default:
                break;
        }
    }

    /**
     * 微信支付
     */
    private void wechatPay() {
        if (!checkIsSupportedWeachatPay()) {
            Toast.makeText(LiJiZFActivity.this, "您暂未安装微信或您的微信版本暂不支持支付功能\n请下载安装最新版本的微信", Toast.LENGTH_SHORT).show();
        } else {
            if (orderPay.getPay() == null) {
                MyDialog.showTipDialog(LiJiZFActivity.this, "微信支付暂未开通");
            } else {
                OrderPay.PayBean.ConfigBean config = orderPay.getPay().getConfig();
                api.registerApp(config.getAppid());
                PayReq mPayReq = new PayReq();
                mPayReq.appId = config.getAppid();
                mPayReq.partnerId = config.getPartnerid();
                mPayReq.prepayId = config.getPrepayid();
                mPayReq.packageValue = config.getPackagevalue();
                mPayReq.nonceStr = config.getNoncestr();
                mPayReq.timeStamp = config.getTimestamp() + "";
                mPayReq.sign = config.getSign().toUpperCase();
                api.sendReq(mPayReq);
            }

        }
    }

    /**
     * 检查微信版本是否支付支付或是否安装可支付的微信版本
     */
    private boolean checkIsSupportedWeachatPay() {
        boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
        return isPaySupported;
    }


    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.BroadcastCode.PAY_RECEIVER);
        registerReceiver(recevier, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(recevier);
    }
}
