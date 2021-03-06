package com.vip.uyux.activity;

import android.content.Intent;
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

import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.WebPay;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.util.ScreenUtils;


/**
 * des： 网页
 * author： zhangjiebo
 * date： 2017/7/6 0006 下午 1:40
 */
public class WebActivity extends ZjbBaseActivity implements View.OnClickListener {

    private WebView mWebView;
    private String mUrl;
    private String title;
    private WebSettings mSettings;
    private ProgressBar pb1;
    private TextView mTv_title;
    private View viewBar;
    private ImageView imageShouCang;
    private ImageView imageFenXiang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        init();
    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        mUrl = intent.getStringExtra(Constant.IntentKey.URL);
        title = intent.getStringExtra(Constant.IntentKey.TITLE);
        viewBar = findViewById(R.id.viewBar);
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void findID() {
        mWebView = (WebView) findViewById(R.id.webView);
        pb1 = (ProgressBar) findViewById(R.id.progressBar2);
        mTv_title = (TextView) findViewById(R.id.textViewTitle);
        imageShouCang= (ImageView) findViewById(R.id.imageShouCang);
        imageFenXiang= (ImageView) findViewById(R.id.imageFenXiang);

    }

    @Override
    protected void initViews() {
        imageShouCang.setVisibility(View.INVISIBLE);
        imageFenXiang.setVisibility(View.INVISIBLE);
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
                    intent.setClass(WebActivity.this, ChanPinXQCZActivity.class);
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
                            intent.setClass(WebActivity.this, LiJiZFActivity.class);
                            startActivity(intent);
//                            finish();
                        } else {
                            MyDialog.showTipDialog(WebActivity.this, webPay.getInfo());
                        }
                    }
                });
            }
        }, "javaMethod");
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageBack:
                back();
                break;
            default:

                break;
        }
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
}
