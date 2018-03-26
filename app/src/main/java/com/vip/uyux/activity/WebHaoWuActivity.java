package com.vip.uyux.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ToLoginActivity;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.IndexRecom;
import com.vip.uyux.model.IndexShareinfo;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.model.ShouCangShanChu;
import com.vip.uyux.model.SimpleInfo;
import com.vip.uyux.model.WebPay;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.util.ScreenUtils;

import java.util.HashMap;


/**
 * des： 网页
 * author： zhangjiebo
 * date： 2017/7/6 0006 下午 1:40
 */
public class WebHaoWuActivity extends ZjbBaseActivity implements View.OnClickListener {

    private IWXAPI api;
    private boolean isShare = false;
    private IndexRecom.DataBean dataBean;

    private WebView mWebView;
    private String mUrl;
    private String title;
    private WebSettings mSettings;
    private ProgressBar pb1;
    private TextView mTv_title;
    private View viewBar;
    private ImageView imageShouCang;

    private BroadcastReceiver reciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case Constant.BroadcastCode.WX_SHARE:
                    if (isShare) {
                        MyDialog.showTipDialog(WebHaoWuActivity.this, "分享成功");
                        isShare = false;
                        shareHuiDiao();
                    }
                    break;
                case Constant.BroadcastCode.WX_SHARE_FAIL:
                    if (isShare) {
                        MyDialog.showTipDialog(WebHaoWuActivity.this, "取消分享");
                        isShare = false;
                    }
                    break;
                default:
                    break;
            }
        }
    };
    private IndexShareinfo.ShareBean share;

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getHDOkObject() {
        String url = Constant.HOST + Constant.Url.SHARE_SHAREAFTER;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime",tokenTime);
        }
        params.put("shareType",String.valueOf(2));
        params.put("source",Constant.source);
        params.put("shareTitle",share.getShareTitle());
        params.put("shareImg",share.getShareImg());
        params.put("shareDes",share.getShareDes());
        params.put("url",share.getShareUrl());
        params.put("id",userInfo.getUid());
        return new OkObject(params, url);
    }

    /**
     * 分享回调
     */
    private void shareHuiDiao() {
        showLoadingDialog();
        ApiClient.post(WebHaoWuActivity.this, getHDOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ChanPinXQActivity--onSuccess",s+ "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus()==1){
                        LogUtil.LogShitou("ChanPinXQActivity--onSuccess", "回调成功");
                    }else if (simpleInfo.getStatus()==3){
                        MyDialog.showReLoginDialog(WebHaoWuActivity.this);
                    }else {
                        Toast.makeText(WebHaoWuActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(WebHaoWuActivity.this,"数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(WebHaoWuActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        api = WXAPIFactory.createWXAPI(this, Constant.WXAPPID, true);
        init();
    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        mUrl = intent.getStringExtra(Constant.IntentKey.URL);
        title = intent.getStringExtra(Constant.IntentKey.TITLE);
        dataBean = (IndexRecom.DataBean) intent.getSerializableExtra(Constant.IntentKey.BEAN);
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void findID() {
        viewBar = findViewById(R.id.viewBar);
        mWebView = (WebView) findViewById(R.id.webView);
        pb1 = (ProgressBar) findViewById(R.id.progressBar2);
        mTv_title = (TextView) findViewById(R.id.textViewTitle);
        imageShouCang = (ImageView) findViewById(R.id.imageShouCang);
    }

    @Override
    protected void initViews() {
        if (dataBean.getIsc() == 1) {
            imageShouCang.setImageResource(R.mipmap.shoucang_xq_true);
        } else {
            imageShouCang.setImageResource(R.mipmap.shoucang_xq);
        }
        ViewGroup.LayoutParams layoutParams = viewBar.getLayoutParams();
        layoutParams.height = (int) (getResources().getDimension(R.dimen.titleHeight) + ScreenUtils.getStatusBarHeight(this));
        viewBar.setLayoutParams(layoutParams);
        if (!TextUtils.isEmpty(title)) {
            mTv_title.setText(title);
        }
        mWebView.loadUrl(mUrl);
        mWebView.setWebViewClient(new WebViewClient());//覆盖第三方浏览器
        mSettings = mWebView.getSettings();
        mSettings.setJavaScriptEnabled(true);
        mSettings.setUseWideViewPort(true);
        mSettings.setLoadWithOverviewMode(true);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                pb1.setProgress(newProgress);
                if (newProgress == 100) {
                    pb1.setVisibility(View.GONE);
                } else {
                    pb1.setVisibility(View.VISIBLE);
                }

            }


        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (url.contains("http://app.uyux.vip/mobile/goods/details/id/")) {
                    String substring = url.substring(44, url.length());
                    String replace = substring.replace(".", ",");
                    String[] split = replace.split(",");
                    LogUtil.LogShitou("WebActivity--onProgressChanged", "" + split[0]);
                    Intent intent = new Intent();
                    intent.setClass(WebHaoWuActivity.this, WebHaoWuActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra(Constant.IntentKey.ID, split[0]);
                    startActivity(intent);
                    if (mWebView.canGoBack()) {
                        mWebView.goBack();
                    }
                }
            }
        });
        mWebView.addJavascriptInterface(new Object() {
            @JavascriptInterface
            public void closeActivity(final String value) {
                LogUtil.LogShitou("WebActivity--closeActivity", "" + value);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        WebPay webPay = GsonUtils.parseJSON(value, WebPay.class);
                        if (webPay.getStatus() == 1) {
                            Intent intent = new Intent();
                            intent.setAction(Constant.BroadcastCode.USERINFO);
                            sendBroadcast(intent);
                            intent.putExtra(Constant.IntentKey.ID, webPay.getOid());
                            intent.putExtra(Constant.IntentKey.VALUE, webPay.getOrder_amount());
                            intent.setClass(WebHaoWuActivity.this, LiJiZFActivity.class);
                            startActivity(intent);
//                            finish();
                        } else {
                            MyDialog.showTipDialog(WebHaoWuActivity.this, webPay.getInfo());
                        }
                    }
                });
            }
        }, "javaMethod");
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.imageFenXiang).setOnClickListener(this);
        imageShouCang.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageFenXiang:
                if (isLogin){
                    share();
                }else {
                    ToLoginActivity.toLoginActivity(this);
                }
                break;
            case R.id.imageShouCang:
                if (isLogin) {
                    if (dataBean.getIsc() == 0) {
                        shouCang();
                    } else {
                        quXiaoSC();
                    }
                } else {
                    ToLoginActivity.toLoginActivity(this);
                }
                break;
            case R.id.imageBack:
                back();
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
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.INDEX_SHAREINFO;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime",tokenTime);
        }
        params.put("type","hw");
        params.put("id",String.valueOf(dataBean.getId()));
        return new OkObject(params, url);
    }

    /**
     * 分享
     */
    private void share() {
        showLoadingDialog();
        ApiClient.post(WebHaoWuActivity.this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("WebHaoWuActivity--onSuccess",s+ "");
                try {
                    IndexShareinfo indexShareinfo = GsonUtils.parseJSON(s, IndexShareinfo.class);
                    if (indexShareinfo.getStatus()==1){
                        share = indexShareinfo.getShare();
                        isShare=true;
                        MyDialog.share01(WebHaoWuActivity.this, api, share.getShareUrl(), share.getShareImg(), share.getShareTitle(), share.getShareDes());
                    }else if (indexShareinfo.getStatus()==3){
                        MyDialog.showReLoginDialog(WebHaoWuActivity.this);
                    }else {
                        Toast.makeText(WebHaoWuActivity.this, indexShareinfo.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(WebHaoWuActivity.this,"数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(WebHaoWuActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 取消收藏
     */
    private void quXiaoSC() {
        String url = Constant.HOST + Constant.Url.GOODS_CANCLECOLLECT;
        ShouCangShanChu shouCangShanChu = new ShouCangShanChu(1, "android", userInfo.getUid(), tokenTime, dataBean.getId(), 2);
        showLoadingDialog();
        ApiClient.postJson(WebHaoWuActivity.this, url, GsonUtils.parseObject(shouCangShanChu), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("WebHaoWuActivity--onSuccess", s + "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus() == 1) {
                        Intent intent = new Intent();
                        intent.setAction(Constant.BroadcastCode.SHUA_XIN_SHOU_CANG);
                        sendBroadcast(intent);
                        Toast.makeText(WebHaoWuActivity.this, "取消收藏", Toast.LENGTH_SHORT).show();
                        dataBean.setIsc(0);
                        imageShouCang.setImageResource(R.mipmap.shoucang_xq);
                    } else if (simpleInfo.getStatus() == 3) {
                        MyDialog.showReLoginDialog(WebHaoWuActivity.this);
                    } else {
                        Toast.makeText(WebHaoWuActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(WebHaoWuActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(WebHaoWuActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getSCOkObject() {
        String url = Constant.HOST + Constant.Url.GOODS_COLLECT;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("item_id", String.valueOf(dataBean.getId()));
        params.put("type", String.valueOf(2));
        return new OkObject(params, url);
    }

    /**
     * 收藏
     */
    private void shouCang() {
        showLoadingDialog();
        ApiClient.post(WebHaoWuActivity.this, getSCOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("WebHaoWuActivity--onSuccess", s + "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus() == 1) {
                        Toast.makeText(WebHaoWuActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                        dataBean.setIsc(1);
                        imageShouCang.setImageResource(R.mipmap.shoucang_xq_true);
                        Intent intent = new Intent();
                        intent.setAction(Constant.BroadcastCode.SHUA_XIN_SHOU_CANG);
                        sendBroadcast(intent);
                    } else if (simpleInfo.getStatus() == 3) {
                        MyDialog.showReLoginDialog(WebHaoWuActivity.this);
                    } else {
                        Toast.makeText(WebHaoWuActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(WebHaoWuActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(WebHaoWuActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void back() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        back();
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.BroadcastCode.WX_SHARE);
        filter.addAction(Constant.BroadcastCode.WX_SHARE_FAIL);
        registerReceiver(reciver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(reciver);
    }
}
