package com.vip.uyux.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.model.SimpleInfo;
import com.vip.uyux.model.UserRealbefore;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.CheckIdCard;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.util.StringUtil;

import java.util.HashMap;
import java.util.List;

public class ShiMingRZActivity extends ZjbBaseActivity implements View.OnClickListener {

    private TextView textDes;
    private UserRealbefore userRealbefore;
    private EditText editPhone;
    private EditText editCode;
    private Runnable mR;
    private int[] mI;
    private String mPhone_sms;
    private TextView buttonSms;
    private EditText editName;
    private EditText editCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shi_ming_rz);
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
        editName = (EditText) findViewById(R.id.editName);
        editCard = (EditText) findViewById(R.id.editCard);
        textDes = (TextView) findViewById(R.id.textDes);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editCode = (EditText) findViewById(R.id.editCode);
        buttonSms = (TextView) findViewById(R.id.buttonSms);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("实名认证");
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.textLiaoJie).setOnClickListener(this);
        findViewById(R.id.btnTiJiao).setOnClickListener(this);
        buttonSms.setOnClickListener(this);
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.USER_REALBEFORE;
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
        ApiClient.post(ShiMingRZActivity.this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ShiMingRZActivity--onSuccess", s + "");
                try {
                    userRealbefore = GsonUtils.parseJSON(s, UserRealbefore.class);
                    if (userRealbefore.getStatus() == 1) {
                        List<String> stringList = userRealbefore.getDes();
                        String des = "";
                        for (int i = 0; i < stringList.size(); i++) {
                            if (i == stringList.size() - 1) {
                                des = des + stringList.get(i);
                            } else {
                                des = des + stringList.get(i)+"\n";
                            }
                        }
                        textDes.setText(des);
                    } else if (userRealbefore.getStatus() == 3) {
                        MyDialog.showReLoginDialog(ShiMingRZActivity.this);
                    } else {
                        Toast.makeText(ShiMingRZActivity.this, userRealbefore.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(ShiMingRZActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(ShiMingRZActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSms:
                sendSMS();
                break;
            case R.id.btnTiJiao:
                if (TextUtils.isEmpty(editName.getText().toString().trim())) {
                    Toast.makeText(ShiMingRZActivity.this, "请输入真实姓名", Toast.LENGTH_SHORT).show();
                    return;
                }
                CheckIdCard checkIdCard = new CheckIdCard(editCard.getText().toString().trim());
                if (!checkIdCard.validate()) {
                    Toast.makeText(ShiMingRZActivity.this, "请输入正确的身份证号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(editPhone.getText().toString().trim())) {
                    Toast.makeText(ShiMingRZActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(editCode.getText().toString().trim())) {
                    Toast.makeText(ShiMingRZActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                tiJiao();
                break;
            case R.id.textLiaoJie:
                Intent intent = new Intent();
                intent.setClass(this, WebActivity.class);
                intent.putExtra(Constant.IntentKey.TITLE, userRealbefore.getUrl_title());
                intent.putExtra(Constant.IntentKey.URL, userRealbefore.getUrl());
                startActivity(intent);
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
    private OkObject getTJOkObject() {
        String url = Constant.HOST + Constant.Url.USER_REALSUBMIT;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime",tokenTime);
        }
        params.put("name",editName.getText().toString().trim());
        params.put("card",editCard.getText().toString().trim());
        params.put("mobile",editPhone.getText().toString().trim());
        params.put("code",editCode.getText().toString().trim());
        return new OkObject(params, url);
    }

    /**
     * 提交
     */
    private void tiJiao() {
        showLoadingDialog();
        ApiClient.post(ShiMingRZActivity.this, getTJOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ShiMingRZActivity--onSuccess",s+ "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus()==1){
                        MyDialog.dialogFinish(ShiMingRZActivity.this,simpleInfo.getInfo());
                    }else if (simpleInfo.getStatus()==3){
                        MyDialog.showReLoginDialog(ShiMingRZActivity.this);
                    }else {
                        Toast.makeText(ShiMingRZActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(ShiMingRZActivity.this,"数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(ShiMingRZActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
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
            Toast.makeText(ShiMingRZActivity.this, "输入正确的手机号", Toast.LENGTH_SHORT).show();
            editPhone.setText("");
        }
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject1() {
        String url = Constant.HOST + Constant.Url.LOGIN_BANKSMS;
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
        ApiClient.post(ShiMingRZActivity.this, getOkObject1(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("RenZhengFragment--获取短信", "" + s);
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    Toast.makeText(ShiMingRZActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    if (simpleInfo.getStatus() == 1) {

                    }
                } catch (Exception e) {
                    Toast.makeText(ShiMingRZActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(ShiMingRZActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
