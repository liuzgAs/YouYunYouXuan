package com.vip.uyux.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vip.uyux.R;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.LoginInfo;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.model.SimpleInfo;
import com.vip.uyux.model.UserInfo;
import com.vip.uyux.model.WxLogin;
import com.vip.uyux.util.ACache;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.util.MD5Util;
import com.vip.uyux.util.StringUtil;

import java.util.HashMap;


/**
 * 注册
 *
 * @author Administrator
 */
public class ZhuCeActivity extends ZjbBaseActivity implements View.OnClickListener {
    private EditText[] editView = new EditText[4];
    private View[] lineView = new View[4];
    private TextView textSms;
    private Runnable mR;
    private int[] mI;
    private String mPhone_sms;
    private boolean isAgreement = true;
    private ImageView imageAgreement;
    final IWXAPI api = WXAPIFactory.createWXAPI(this, null);
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (TextUtils.equals(action, Constant.BroadcastCode.WX_SHARE)) {
                String code = intent.getStringExtra(Constant.BroadcastCode.WX_LOGIN);
                Log.e("LoginActivity", "DengLuActivity--onReceive--微信的code" + code);
                String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + Constant.WXAPPID + "&secret=" + Constant.WXSCRENT + "&code=" + code + "&grant_type=authorization_code";

                OkGo.<String>get(url)
                        .tag(ZhuCeActivity.this)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                                final String s = response.body();
                                Log.e("LoginActivity", "DengLuActivity--onSuccess--获取token" + s);
                                WxLogin wxLogin = GsonUtils.parseJSON(s, WxLogin.class);
                                String url1 = "https://api.weixin.qq.com/sns/userinfo?access_token=" + wxLogin.getAccess_token() + "&openid=" + wxLogin.getOpenid();
                                OkGo.<String>get(url1)
                                        .tag(ZhuCeActivity.this)
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                                                LogUtil.LogShitou("DengLuActivity--onSuccess", "" + response.body());
                                                String s1 = response.body();
                                                LoginInfo loginInfo = GsonUtils.parseJSON(s1, LoginInfo.class);
                                                loginInfo.setLoginType(2);
                                                otherLogin(loginInfo);
                                            }

                                            @Override
                                            public void onError(com.lzy.okgo.model.Response<String> response) {
                                                super.onError(response);
                                            }
                                        });
                            }

                            @Override
                            public void onError(com.lzy.okgo.model.Response<String> response) {
                                super.onError(response);
                            }
                        });
            }
            if (TextUtils.equals(action, Constant.BroadcastCode.WX_SHARE_FAIL)) {
                cancelLoadingDialog();
            }
        }
    };

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getBDWXOkObject(LoginInfo loginInfo) {
        ACache aCache = ACache.get(this, Constant.Acache.LOCATION);
        String did = aCache.getAsString(Constant.Acache.DID);
        tokenTime = System.currentTimeMillis() + "";
        String url = Constant.HOST + Constant.Url.LOGIN_LOGINWXBIND;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", userInfo.getUid());
        params.put("did", did);
        params.put("openid", loginInfo.getOpenid());
        params.put("unionid", loginInfo.getUnionid());
        params.put("nickname", loginInfo.getNickname());
        params.put("headimgurl", loginInfo.getHeadimgurl());
        params.put("city", loginInfo.getCity());
        params.put("sex", String.valueOf(loginInfo.getSex()));
        params.put("tokenTime", tokenTime);
        return new OkObject(params, url);
    }

    private void otherLogin(LoginInfo loginInfo) {
        ApiClient.post(ZhuCeActivity.this, getBDWXOkObject(loginInfo), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("DengLuActivity--onSuccess", s + "");
                try {
                    loginSuccess(s, tokenTime);
                } catch (Exception e) {
                    Toast.makeText(ZhuCeActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(ZhuCeActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ce);
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
        editView[0] = (EditText) findViewById(R.id.edit01);
        editView[1] = (EditText) findViewById(R.id.edit02);
        editView[2] = (EditText) findViewById(R.id.edit03);
        editView[3] = (EditText) findViewById(R.id.edit04);
//        editView[4] = (EditText) findViewById(R.id.edit05);
        lineView[0] = findViewById(R.id.line01);
        lineView[1] = findViewById(R.id.line02);
        lineView[2] = findViewById(R.id.line03);
        lineView[3] = findViewById(R.id.line04);
//        lineView[4] = findViewById(R.id.line05);
        textSms = (TextView) findViewById(R.id.textSms);
        imageAgreement = (ImageView) findViewById(R.id.imageAgreement);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void setListeners() {
        findViewById(R.id.buttonZhuCe).setOnClickListener(this);
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.textXieYi).setOnClickListener(this);
        /*下划线监听*/
        for (int i = 0; i < editView.length; i++) {
            final int finalI = i;
            editView[i].setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        for (View aLineView : lineView) {
                            aLineView.setBackgroundColor(ContextCompat.getColor(ZhuCeActivity.this, R.color.text_gray));
                        }
                        lineView[finalI].setBackgroundColor(ContextCompat.getColor(ZhuCeActivity.this, R.color.basic_color));
                    }
                }
            });
        }
        textSms.setOnClickListener(this);
        imageAgreement.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    /**
     * des： 控制协议选中
     * author： ZhangJieBo
     * date： 2017/11/14 0014 上午 10:17
     */
    private void setAgreement() {
        if (isAgreement) {
            imageAgreement.setImageResource(R.mipmap.xuanzhong);
        } else {
            imageAgreement.setImageResource(R.mipmap.weixuanzhong);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textXieYi:
                Intent intent = new Intent();
                intent.setClass(ZhuCeActivity.this, WebActivity.class);
                intent.putExtra(Constant.IntentKey.URL, Constant.WEB_HOST + Constant.Url.YONG_HU_XIE_YI);
                intent.putExtra(Constant.IntentKey.TITLE, "用户协议");
                startActivity(intent);
                break;
            case R.id.imageAgreement:
                isAgreement = !isAgreement;
                setAgreement();
                break;
            case R.id.textSms:
                sendSMS();
                break;
            case R.id.buttonZhuCe:
                if (TextUtils.isEmpty(mPhone_sms)) {
                    Toast.makeText(ZhuCeActivity.this, "请先发送验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(editView[1].getText().toString().trim())) {
                    Toast.makeText(ZhuCeActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(editView[2].getText().toString().trim()) || TextUtils.isEmpty(editView[3].getText().toString().trim())) {
                    Toast.makeText(ZhuCeActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.equals(editView[2].getText().toString().trim(), editView[3].getText().toString().trim())) {
                    Toast.makeText(ZhuCeActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (editView[2].getText().toString().trim().length() < 6) {
                    Toast.makeText(ZhuCeActivity.this, "密码不能小于6位", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isAgreement) {
                    Toast.makeText(ZhuCeActivity.this, R.string.xieyi, Toast.LENGTH_SHORT).show();
                    return;
                }
                zuCe();
                break;
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }

    private OkObject getOkObject1() {
        String url = Constant.HOST + Constant.Url.LOGIN_REGISTER;
        HashMap<String, String> params = new HashMap<>();
        params.put("userName", mPhone_sms);
        params.put("userPwd", MD5Util.getMD5(MD5Util.getMD5(editView[2].getText().toString().trim()) + "ad"));
        params.put("code", editView[1].getText().toString().trim());
//        params.put("tx_code", editView[4].getText().toString().trim());
        /*用户类型卖家注册时传1买家注册传0*/
        params.put("type", "0");
        return new OkObject(params, url);
    }

    /**
     * des： 注册
     * author： ZhangJieBo
     * date： 2017/8/22 0022 上午 10:48
     */
    private void zuCe() {
        showLoadingDialog();
        ApiClient.post(this, getOkObject1(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ZhuCeActivity--onSuccess", "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    Toast.makeText(ZhuCeActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    if (simpleInfo.getStatus() == 1) {
                        loginPsw();
                    } else {

                    }
                } catch (Exception e) {
                    Toast.makeText(ZhuCeActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(ZhuCeActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObjectPsw(String tokenTime) {
        ACache aCache = ACache.get(this, Constant.Acache.LOCATION);
        String did = aCache.getAsString(Constant.Acache.DID);
        String url = Constant.HOST + Constant.Url.LOGIN_INDEX;
        HashMap<String, String> params = new HashMap<>();
        params.put("userName", editView[0].getText().toString().trim());
        params.put("tokenTime", tokenTime);
        params.put("userPwd", MD5Util.getMD5(MD5Util.getMD5(editView[1].getText().toString().trim()) + "ad"));
        params.put("did", did);
        return new OkObject(params, url);
    }

    /**
     * 密码登录
     */
    private void loginPsw() {
        showLoadingDialog();
        tokenTime = System.currentTimeMillis() + "";
        ApiClient.post(ZhuCeActivity.this, getOkObjectPsw(tokenTime), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("DengLuActivity--登录返回", "" + s);
                cancelLoadingDialog();
                try {
                    loginSuccess(s, tokenTime);
                } catch (Exception e) {
                    Toast.makeText(ZhuCeActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(ZhuCeActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * des： 登录成功处理
     * author： ZhangJieBo
     * date： 2017/9/8 0008 下午 5:27
     */
    private void loginSuccess(String s, String tokenTime) {
        userInfo = GsonUtils.parseJSON(s, UserInfo.class);
        if (userInfo.getStatus() == 1) {
            if (userInfo.getGoAuthorize() == 1) {
                if (!checkIsSupportedWeachatPay()) {
                    Toast.makeText(this, getResources().getString(R.string.ninzanweianzhuang), Toast.LENGTH_SHORT).show();
                    return;
                }
                showLoadingDialog();
                api.registerApp(Constant.WXAPPID);
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wechat_sdk_demo_test";
                api.sendReq(req);
                return;
            }
            Intent intent = new Intent();
            intent.setAction(Constant.BroadcastCode.GUANBIDENGLU);
            sendBroadcast(intent);
            ACache aCache = ACache.get(ZhuCeActivity.this, Constant.Acache.APP);
            aCache.put(Constant.Acache.USER_INFO, userInfo);
            aCache.put(Constant.Acache.TOKENTIME, tokenTime);
            Constant.changeControl++;
            finish();
        }
        Toast.makeText(ZhuCeActivity.this, userInfo.getInfo(), Toast.LENGTH_SHORT).show();
    }

    /**
     * 检查微信版本是否支付支付或是否安装可支付的微信版本
     */
    private boolean checkIsSupportedWeachatPay() {
        boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
        return isPaySupported;
    }

    /**
     * des： 短信发送按钮状态
     * author： ZhangJieBo
     * date： 2017/8/22 0022 上午 10:26
     */
    private void sendSMS() {
        textSms.removeCallbacks(mR);
        boolean mobileNO = StringUtil.isMobileNO(editView[0].getText().toString().trim());
        if (mobileNO) {
            mPhone_sms = editView[0].getText().toString().trim();
            textSms.setEnabled(false);
            mI = new int[]{60};

            mR = new Runnable() {
                @Override
                public void run() {
                    textSms.setText((mI[0]--) + "秒后重发");
                    if (mI[0] == 0) {
                        textSms.setEnabled(true);
                        textSms.setText("重新发送");
                        return;
                    } else {
                    }
                    textSms.postDelayed(mR, 1000);
                }
            };
            textSms.postDelayed(mR, 0);
            getSms();
        } else {
            Toast.makeText(ZhuCeActivity.this, "输入正确的手机号", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.LOGIN_REGSMS;
        HashMap<String, String> params = new HashMap<>();
        params.put("userName", mPhone_sms);
        return new OkObject(params, url);
    }

    /**
     * des： 获取短信
     * author： ZhangJieBo
     * date： 2017/7/6 0006 下午 2:45
     */
    private void getSms() {
        showLoadingDialog();
        ApiClient.post(this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ZhuCeActivity--获取短信", "" + s);
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    Toast.makeText(ZhuCeActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    if (simpleInfo.getStatus() == 1) {

                    }
                } catch (Exception e) {
                    Toast.makeText(ZhuCeActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(ZhuCeActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.BroadcastCode.WX_SHARE);
        filter.addAction(Constant.BroadcastCode.WX_SHARE_FAIL);
        filter.addAction(Constant.BroadcastCode.BANG_DING);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        Constant.isLogin = false;
    }
}
