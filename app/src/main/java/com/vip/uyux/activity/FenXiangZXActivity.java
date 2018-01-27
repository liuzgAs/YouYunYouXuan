package com.vip.uyux.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vip.uyux.R;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;

public class FenXiangZXActivity extends ZjbBaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fen_xiang_zx);
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

    }

    @Override
    protected void setListeners() {
        findViewById(R.id.viewFenHongZX).setOnClickListener(this);
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.viewBuKeTiXian).setOnClickListener(this);
        findViewById(R.id.viewWoDeKeHu).setOnClickListener(this);
        findViewById(R.id.viewWoDeTD).setOnClickListener(this);
        findViewById(R.id.viewKeTiXian).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.viewKeTiXian:
                intent.setClass(this, BuKeTiXianActivity.class);
                intent.putExtra(Constant.IntentKey.TYPE,2);
                startActivity(intent);
                break;
            case R.id.viewWoDeTD:
                intent.setClass(this, WoDeTDActivity.class);
                startActivity(intent);
                break;
            case R.id.viewWoDeKeHu:
                intent.setClass(this, WoDeKeHuActivity.class);
                startActivity(intent);
                break;
            case R.id.viewBuKeTiXian:
                intent.setClass(this, BuKeTiXianActivity.class);
                intent.putExtra(Constant.IntentKey.TYPE,1);
                startActivity(intent);
                break;
            case R.id.viewFenHongZX:
                intent.setClass(this, FenHongZXActivity.class);
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
