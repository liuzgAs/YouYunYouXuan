package com.vip.uyux.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.vip.uyux.R;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.fragment.FenXiaoFragment;

import java.util.ArrayList;
import java.util.List;

public class YuJiYJActivity extends ZjbBaseActivity implements View.OnClickListener {
    private TabLayout tablayout;
    private ViewPager viewPager;
    List<String> list = new ArrayList<>();
    private String sum;
    private TextView textSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yu_ji_yj);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        sum = intent.getStringExtra(Constant.IntentKey.VALUE);
    }

    @Override
    protected void findID() {
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        textSum = (TextView) findViewById(R.id.textSum);
    }

    @Override
    protected void initViews() {
        textSum.setText(sum);
        list.add("预返佣单");
        list.add("已返佣单");
        list.add("失效佣单");
//        list.add("返佣明细");
        viewPager.setAdapter(new MyPageAdapter(getSupportFragmentManager()));
        tablayout.setupWithViewPager(viewPager);
        tablayout.removeAllTabs();
        for (int i = 0; i < list.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_tablayout, null);
            TextView textTitle = view.findViewById(R.id.textTitle);
            textTitle.setText(list.get(i));
            if (i == 0) {
                tablayout.addTab(tablayout.newTab().setCustomView(view), true);
            } else {
                tablayout.addTab(tablayout.newTab().setCustomView(view), false);
            }
        }
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    class MyPageAdapter extends FragmentPagerAdapter {

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FenXiaoFragment(0+"",1);
                case 1:
                    return new FenXiaoFragment(10+"",1);
                case 2:
                    return new FenXiaoFragment(20+"",1);
//                case 3:
//                    return new FenXiaoFragment(30);
//                case 4:
//                    return new FenXiaoFragment(40);
                default:
                    return new FenXiaoFragment(0+"",1);
            }

        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
