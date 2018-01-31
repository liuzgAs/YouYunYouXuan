package com.vip.uyux.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vip.uyux.R;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.LoginInfo;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.model.SimpleInfo;
import com.vip.uyux.model.UserInfo;
import com.vip.uyux.util.ACache;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.util.StringUtil;

import java.util.HashMap;

public class BangDingTelActivity extends ZjbBaseActivity implements View.OnClickListener {

    private LoginInfo loginInfo;
    private String did;
    private TextView buttonSms;
    private EditText editPhone;
    private EditText editCode;
    private Runnable mR;
    private int[] mI;
    private String mPhone_sms;
//    private EditText editPsw;
//    private EditText editPsw1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bang_ding_tel);
        init();
    }

    @Override
    protected void initSP() {
        ACache aCache = ACache.get(this, Constant.Acache.LOCATION);
        did = aCache.getAsString(Constant.Acache.DID);
    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        loginInfo = (LoginInfo) intent.getSerializableExtra(Constant.IntentKey.BEAN);
    }

    @Override
    protected void findID() {
        buttonSms = (TextView) findViewById(R.id.buttonSms);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editCode = (EditText) findViewById(R.id.editCode);
//        editPsw = (EditText) findViewById(R.id.editPsw);
//        editPsw1 = (EditText) findViewById(R.id.editpsw1);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("绑定手机号");
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.buttonTiJiao).setOnClickListener(this);
        buttonSms.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        tokenTime = System.currentTimeMillis() + "";
        String url = Constant.HOST + Constant.Url.LOGIN_WXBINDSMS;
        HashMap<String, String> params = new HashMap<>();
        params.put("did", did);
        params.put("code", editCode.getText().toString().trim());
        params.put("userName", editPhone.getText().toString().trim());
//        params.put("userPwd", editPsw.getText().toString().trim());
        params.put("openid", loginInfo.getOpenid());
        params.put("unionid", loginInfo.getUnionid());
        params.put("nickname", loginInfo.getNickname());
        params.put("headimgurl", loginInfo.getHeadimgurl());
        params.put("city", loginInfo.getCity());
        params.put("sex", String.valueOf(loginInfo.getSex()));
        params.put("tokenTime", tokenTime);
        return new OkObject(params, url);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSms:
                sendSMS();
                break;
            case R.id.buttonTiJiao:
                if (TextUtils.isEmpty(editPhone.getText().toString().trim())) {
                    Toast.makeText(BangDingTelActivity.this, "请输入银行预留手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(editCode.getText().toString().trim())) {
                    Toast.makeText(BangDingTelActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
//                if (TextUtils.isEmpty(editPsw.getText().toString().trim()) || TextUtils.isEmpty(editPsw1.getText().toString().trim())) {
//                    Toast.makeText(BangDingTelActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (!TextUtils.equals(editPsw.getText().toString().trim(), editPsw1.getText().toString().trim())) {
//                    Toast.makeText(BangDingTelActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (editPsw.getText().toString().trim().length()<6) {
//                    Toast.makeText(BangDingTelActivity.this, "密码不能小于6位", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                showLoadingDialog();
                ApiClient.post(BangDingTelActivity.this, getOkObject(), new ApiClient.CallBack() {
                    @Override
                    public void onSuccess(String s) {
                        cancelLoadingDialog();
                        LogUtil.LogShitou("BangDingTelActivity--onSuccess", s + "");
                        try {
                            loginSuccess(s, tokenTime);
                        } catch (Exception e) {
                            Toast.makeText(BangDingTelActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError() {
                        cancelLoadingDialog();
                        Toast.makeText(BangDingTelActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * des： 登录成功处理
     * author： ZhangJieBo
     * date： 2017/9/8 0008 下午 5:27
     */
    private void loginSuccess(String s, String tokenTime) {
        userInfo = GsonUtils.parseJSON(s, UserInfo.class);
        if (userInfo.getStatus() == 1) {
            Intent intent = new Intent();
            intent.setAction(Constant.BroadcastCode.BANG_DING);
            sendBroadcast(intent);
            ACache aCache = ACache.get(BangDingTelActivity.this, Constant.Acache.APP);
            aCache.put(Constant.Acache.USER_INFO, userInfo);
            aCache.put(Constant.Acache.TOKENTIME, tokenTime);
            Constant.changeControl++;
            finish();
        }
        Toast.makeText(BangDingTelActivity.this, userInfo.getInfo(), Toast.LENGTH_SHORT).show();
    }

    /**
     * des： 短信发送按钮状态
     * author： ZhangJieBo
     * date： 2017/8/22 0022 上午 10:26
     */
    private void sendSMS() {
        buttonSms.removeCallbacks(mR);
        boolean mobileNO = StringUtil.isMobileNO(editPhone.getText().toString().trim());
        if (mobileNO) {
            mPhone_sms = editPhone.getText().toString().trim();
            buttonSms.setEnabled(false);
            mI = new int[]{60};

            mR = new Runnable() {
                @Override
                public void run() {
                    buttonSms.setText((mI[0]--) + "秒后重发");
                    if (mI[0] == 0) {
                        buttonSms.setEnabled(true);
                        buttonSms.setText("重新发送");
                        return;
                    } else {

                    }
                    buttonSms.postDelayed(mR, 1000);
                }
            };
            buttonSms.postDelayed(mR, 0);
            getSms();
        } else {
            Toast.makeText(BangDingTelActivity.this, "输入正确的手机号", Toast.LENGTH_SHORT).show();
            editPhone.setText("");
        }
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject1() {
        String url = Constant.HOST + Constant.Url.LOGIN_BINDSMS;
        HashMap<String, String> params = new HashMap<>();
        params.put("userName", mPhone_sms);
        return new OkObject(params, url);
    }


    /**
     * des： 获取短信
     * author： ZhangJieBo
     * date： 2017/9/11 0011 下午 4:32
     */
    private void getSms() {
        showLoadingDialog();
        ApiClient.post(BangDingTelActivity.this, getOkObject1(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("RenZhengFragment--获取短信", "" + s);
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    Toast.makeText(BangDingTelActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    if (simpleInfo.getStatus() == 1) {

                    }
                } catch (Exception e) {
                    Toast.makeText(BangDingTelActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(BangDingTelActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
