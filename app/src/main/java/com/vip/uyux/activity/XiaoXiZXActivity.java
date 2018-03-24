package com.vip.uyux.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.MassageIndex;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;

import java.util.HashMap;
import java.util.List;

public class XiaoXiZXActivity extends ZjbBaseActivity implements View.OnClickListener {

    private TextView[] textTipNum = new TextView[5];
    private TextView[] textTitle = new TextView[5];
    private TextView[] textDes = new TextView[5];
    private int dp20;
    private int dp10;
    private BroadcastReceiver reciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case Constant.BroadcastCode.SHUA_XIN_TIPS:
                    initData();
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiao_xi_zx);
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
        textTipNum[0] = (TextView) findViewById(R.id.textTipNum00);
        textTipNum[1] = (TextView) findViewById(R.id.textTipNum01);
        textTipNum[2] = (TextView) findViewById(R.id.textTipNum02);
        textTipNum[3] = (TextView) findViewById(R.id.textTipNum03);
        textTipNum[4] = (TextView) findViewById(R.id.textTipNum04);
        textTitle[0] = (TextView) findViewById(R.id.textTitle00);
        textTitle[1] = (TextView) findViewById(R.id.textTitle01);
        textTitle[2] = (TextView) findViewById(R.id.textTitle02);
        textTitle[3] = (TextView) findViewById(R.id.textTitle03);
        textTitle[4] = (TextView) findViewById(R.id.textTitle04);
        textDes[0] = (TextView) findViewById(R.id.textDes00);
        textDes[1] = (TextView) findViewById(R.id.textDes01);
        textDes[2] = (TextView) findViewById(R.id.textDes02);
        textDes[3] = (TextView) findViewById(R.id.textDes03);
        textDes[4] = (TextView) findViewById(R.id.textDes04);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("消息中心");
        dp20 = (int) DpUtils.convertDpToPixel(20f, this);
        dp10 = (int) DpUtils.convertDpToPixel(10f, this);
        textTipNum[0].setVisibility(View.GONE);
        textTipNum[1].setVisibility(View.GONE);
        textTipNum[2].setVisibility(View.GONE);
        textTipNum[3].setVisibility(View.GONE);
        textTipNum[4].setVisibility(View.GONE);
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.viewWuLiuZhuShou).setOnClickListener(this);
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.MASSAGE_INDEX;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        return new OkObject(params, url);
    }

    @Override
    protected void initData() {
        ApiClient.post(XiaoXiZXActivity.this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("XiaoXiZXActivity--onSuccess", s + "");
                try {
                    MassageIndex massageIndex = GsonUtils.parseJSON(s, MassageIndex.class);
                    if (massageIndex.getStatus() == 1) {
                        List<MassageIndex.DataBean> dataBeanList = massageIndex.getData();
                        for (int i = 0; i < dataBeanList.size(); i++) {
                            textTitle[i].setText(dataBeanList.get(i).getTitle());
                            textDes[i].setText(dataBeanList.get(i).getDes());
                            textTipNum[i].setText(String.valueOf(dataBeanList.get(i).getNum()));
                            if (String.valueOf(dataBeanList.get(i).getNum()).length() > 1) {
                                textTipNum[i].setPadding(dp10, 0, dp10, 0);
                            } else {
                                if (dataBeanList.get(i).getNum()==0){
                                    textTipNum[i].setVisibility(View.GONE);
                                }else {
                                    textTipNum[i].setVisibility(View.VISIBLE);
                                    ViewGroup.LayoutParams layoutParams = textTipNum[i].getLayoutParams();
                                    layoutParams.width = dp20;
                                    layoutParams.height = dp20;
                                    textTipNum[i].setLayoutParams(layoutParams);
                                }
                            }
                        }
                    } else if (massageIndex.getStatus() == 3) {
                        MyDialog.showReLoginDialog(XiaoXiZXActivity.this);
                    } else {
                        Toast.makeText(XiaoXiZXActivity.this, massageIndex.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(XiaoXiZXActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                Toast.makeText(XiaoXiZXActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.BroadcastCode.SHUA_XIN_TIPS);
        registerReceiver(reciver, filter);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.viewWuLiuZhuShou:
                intent.setClass(this, WuLiuZhuShouActivity.class);
                startActivity(intent);
                break;
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(reciver);
    }
}
