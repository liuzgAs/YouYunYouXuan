package com.vip.uyux.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vip.uyux.R;
import com.vip.uyux.base.ZjbBaseNotLeftActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.AdvsBean;

public class ReciverActivity extends ZjbBaseNotLeftActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciver);
        init();
        TextView textTitle = (TextView) findViewById(R.id.textTitle);
        TextView textDes = (TextView) findViewById(R.id.textDes);
        TextView textCancle = (TextView) findViewById(R.id.textCancle);
        TextView textSure = (TextView) findViewById(R.id.textSure);

        final Intent intent = getIntent();
        final AdvsBean extraMapBean = (AdvsBean) intent.getSerializableExtra(Constant.IntentKey.BEAN);
        textTitle.setText(extraMapBean.getTitle());
        textDes.setText(extraMapBean.getSummary());
        textCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        textSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constant.MainActivityAlive==1){
                    Intent intent = new Intent();
                    intent.putExtra(Constant.IntentKey.BEAN,extraMapBean);
                    intent.setAction(Constant.BroadcastCode.ADV);
                    sendBroadcast(intent);
                    finish();
                    return;
                }
                Intent intent1 = new Intent();
                intent1.setClass(ReciverActivity.this, MainActivity.class);
                intent1.putExtra(Constant.IntentKey.BEAN, extraMapBean);
                startActivity(intent1);
                finish();
            }
        });
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

    }

    @Override
    protected void initData() {

    }
}
