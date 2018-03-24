package com.vip.uyux.activity;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
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

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.CSCustomServiceInfo;

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
        findViewById(R.id.viewKeFu).setOnClickListener(this);
        findViewById(R.id.viewXiTongXiaoXi).setOnClickListener(this);
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
            case R.id.viewXiTongXiaoXi:
                intent.setClass(XiaoXiZXActivity.this,XiTongXiaoXiActivity.class);
                startActivity(intent);
                break;
            case R.id.viewKeFu:
                rongYun();
                break;
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

    String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    /**
     * 融云
     */
    private void rongYun() {
        LogUtil.LogShitou("ChanPinXQCZActivity--rongYun", "cao");
        if (getApplicationInfo().packageName.equals(getCurProcessName(XiaoXiZXActivity.this))) {
            LogUtil.LogShitou("ChanPinXQCZActivity--getYunToken", userInfo.getYunToken());
            RongIM.connect(userInfo.getYunToken(), new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 * 2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                    LogUtil.LogShitou("CheLiangXQActivity--onTokenIncorrect", "1111");
                }

                /**
                 * 连接融云成功
                 *
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    LogUtil.LogShitou("CheLiangXQActivity--onSuccess", "userid" + userid);
                    final io.rong.imlib.model.UserInfo userInfoRongYun = new io.rong.imlib.model.UserInfo(userInfo.getUid(), userInfo.getUserName(), Uri.parse(userInfo.getHeadImg()));
//                RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
//                    @Override
//                    public UserInfo getUserInfo(String s) {
//                        return userInfoRongYun;
//                    }
//                },true);
//                RongIM.getInstance().refreshUserInfoCache(userInfoRongYun);
                    /**
                     * 设置当前用户信息，
                     * @param userInfo 当前用户信息
                     */
                    RongIM.getInstance().setCurrentUserInfo(userInfoRongYun);
                    /**
                     * 设置消息体内是否携带用户信息。
                     * @param state 是否携带用户信息，true 携带，false 不携带。
                     */
                    RongIM.getInstance().setMessageAttachedUserInfo(true);
                    //首先需要构造使用客服者的用户信息
                    CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
                    CSCustomServiceInfo csInfo = csBuilder.nickName("优云优选").build();

                    /**
                     * 启动客户服聊天界面。
                     *
                     * @param context           应用上下文。
                     * @param customerServiceId 要与之聊天的客服 Id。
                     * @param title             聊天的标题，如果传入空值，则默认显示与之聊天的客服名称。
                     * @param customServiceInfo 当前使用客服者的用户信息。{@link io.rong.imlib.model.CSCustomServiceInfo}
                     */
                    RongIM.getInstance().startCustomerServiceChat(XiaoXiZXActivity.this, "KEFU152119192578109", "在线客服", csInfo);
                }

                /**
                 * 连接融云失败
                 *
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    LogUtil.LogShitou("CheLiangXQActivity--onError", "" + errorCode.toString());
                }


            });

        }else {
            LogUtil.LogShitou("ChanPinXQCZActivity--rongYun", "111111111111");
        }
//        final io.rong.imlib.model.UserInfo userInfoRongYun = new io.rong.imlib.model.UserInfo(userInfo.getUid(), userInfo.getUserName(), Uri.parse(userInfo.getHeadImg()));
//        /**
//         * 设置当前用户信息，
//         * @param userInfo 当前用户信息
//         */
//        RongIM.getInstance().setCurrentUserInfo(userInfoRongYun);
//        /**
//         * 设置消息体内是否携带用户信息。
//         * @param state 是否携带用户信息，true 携带，false 不携带。
//         */
//        RongIM.getInstance().setMessageAttachedUserInfo(true);
//        //首先需要构造使用客服者的用户信息
//        CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
//        CSCustomServiceInfo csInfo = csBuilder.nickName("融云").build();
//
//        /**
//         * 启动客户服聊天界面。
//         *
//         * @param context           应用上下文。
//         * @param customerServiceId 要与之聊天的客服 Id。
//         * @param title             聊天的标题，如果传入空值，则默认显示与之聊天的客服名称。
//         * @param customServiceInfo 当前使用客服者的用户信息。{@link io.rong.imlib.model.CSCustomServiceInfo}
//         */
//        RongIM.getInstance().startCustomerServiceChat(ChanPinXQCZActivity.this, userInfo.getUid(), "在线客服", csInfo);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(reciver);
    }
}
