package com.vip.uyux.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.vip.uyux.R;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.fragment.GouWuCheFragment;

public class GouWuCheActivity extends ZjbBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gou_wu_che);
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
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        GouWuCheFragment fragment1 = new GouWuCheFragment(1);
        transaction.add(R.id.fragment, fragment1);
        transaction.commit();
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void initData() {

    }
}
