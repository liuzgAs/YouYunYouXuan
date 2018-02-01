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

public class FenXiaoDDActivity extends ZjbBaseActivity implements View.OnClickListener {
    private TabLayout tablayout;
    private ViewPager viewPager;
    List<String> list = new ArrayList<>();
    private TextView textSum;
    private int type;
    private TextView textDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenxiao_yj);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        type = intent.getIntExtra(Constant.IntentKey.TYPE, 1);
    }

    @Override
    protected void findID() {
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        textSum = (TextView) findViewById(R.id.textSum);
        textDes = (TextView) findViewById(R.id.textDes);
    }

    @Override
    protected void initViews() {
        switch (type) {
            case 1:
                ((TextView) findViewById(R.id.textViewTitle)).setText("预计佣金");
                textDes.setText("元");
                break;
            case 2:
                ((TextView) findViewById(R.id.textViewTitle)).setText("分销订单");
                textDes.setText("累计订单数");
                break;
            default:
                break;
        }
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
                    return new FenXiaoFragment(0,type);
                case 1:
                    return new FenXiaoFragment(10,type);
                case 2:
                    return new FenXiaoFragment(20,type);
//                case 3:
//                    return new FenXiaoFragment(30);
//                case 4:
//                    return new FenXiaoFragment(40);
                default:
                    return new FenXiaoFragment(0,type);
            }

        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

    public void setSum(String sum){
        textSum.setText(sum);
    }
}
