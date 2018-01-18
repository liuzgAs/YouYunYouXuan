package com.vip.uyux.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vip.uyux.R;
import com.vip.uyux.application.MyApplication;
import com.vip.uyux.base.ZjbBaseNotLeftActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.fragment.ShouYeFragment;
import com.vip.uyux.util.BackHandlerHelper;
import com.vip.uyux.util.UpgradeUtils;

public class MainActivity extends ZjbBaseNotLeftActivity {

    private String[] tabsItem = new String[5];
    private Class[] fragment = new Class[]{
            ShouYeFragment.class,
            ShouYeFragment.class,
            ShouYeFragment.class,
            ShouYeFragment.class,
            ShouYeFragment.class,
    };
    private int[] imgRes = new int[]{
            R.drawable.selector_item01,
            R.drawable.selector_item02,
            R.drawable.selector_item03,
            R.drawable.selector_item04,
            R.drawable.selector_item05,
    };
    public FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //检查更新
        UpgradeUtils.checkUpgrade(MainActivity.this, Constant.HOST + Constant.Url.INDEX_VERSION);
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
        mTabHost = findViewById(R.id.tabHost);
    }

    @Override
    protected void initViews() {
        tabsItem[0] = "首页";
        tabsItem[1] = "推荐";
        tabsItem[2] = "分类";
        tabsItem[3] = "购物袋";
        tabsItem[4] = "我的";
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtab);
        for (int i = 0; i < tabsItem.length; i++) {
            View inflate = getLayoutInflater().inflate(R.layout.tabs_item, null);
            TextView tabsText = inflate.findViewById(R.id.tabs_text);
            ImageView tabsImg = inflate.findViewById(R.id.tabs_img);
//            if (i==1||i==2){
//                tabsImg.setPadding(0,(int) DpUtils.convertDpToPixel(1f,this),0,(int) DpUtils.convertDpToPixel(1f,this));
//            }
            tabsText.setText(tabsItem[i]);
            tabsImg.setImageResource(imgRes[i]);
            mTabHost.addTab(mTabHost.newTabSpec(tabsItem[i]).setIndicator(inflate), fragment[i], null);
        }
    }

    @Override
    protected void setListeners() {
    }

    @Override
    protected void initData() {

    }

    /**
     * 双击退出应用
     */
    private long currentTime = 0;

    @Override
    public void onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this)) {
            if (System.currentTimeMillis() - currentTime > 1000) {
                Toast toast = Toast.makeText(this, "双击退出应用", Toast.LENGTH_SHORT);
                toast.show();
                currentTime = System.currentTimeMillis();
            } else {
                MyApplication.getInstance().exit();
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        }
    }
}
