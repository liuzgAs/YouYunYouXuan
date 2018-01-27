package com.vip.uyux.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vip.uyux.R;
import com.vip.uyux.base.ZjbBaseActivity;

public class XiaoXiZXActivity extends ZjbBaseActivity implements View.OnClickListener {

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

    }

    @Override
    protected void initViews() {
        ((TextView)findViewById(R.id.textViewTitle)).setText("消息中心");
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.viewWuLiuZhuShou).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.viewWuLiuZhuShou:
                intent.setClass(this,WuLiuZhuShouActivity.class);
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
