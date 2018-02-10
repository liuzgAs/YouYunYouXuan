package com.vip.uyux.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseNotLeftActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.fragment.YouHuiQuanFragment;
import com.vip.uyux.model.CouponIndex;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;

import java.util.HashMap;
import java.util.List;

public class YouHuiQuanActivity extends ZjbBaseNotLeftActivity implements View.OnClickListener {


    private TabLayout tablayout;
    private ViewPager viewPager;
    private List<CouponIndex.TypeBean> typeBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_hui_quan);
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
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    @Override
    protected void initViews() {
        ((TextView)findViewById(R.id.textViewTitle)).setText("优惠券");
    }


    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.COUPON_INDEX;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime",tokenTime);
        }
        return new OkObject(params, url);
    }

    @Override
    protected void initData() {
        ApiClient.post(YouHuiQuanActivity.this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("YouHuiQuanActivity--onSuccess",s+ "");
                try {
                    CouponIndex couponIndex = GsonUtils.parseJSON(s, CouponIndex.class);
                    if (couponIndex.getStatus()==1){
                        typeBeanList = couponIndex.getType();
                        viewPager.setAdapter(new MyPageAdapter(getSupportFragmentManager()));
                        tablayout.setupWithViewPager(viewPager);
                        tablayout.removeAllTabs();
                        for (int i = 0; i < typeBeanList.size(); i++) {
                            View view = LayoutInflater.from(YouHuiQuanActivity.this).inflate(R.layout.item_tablayout, null);
                            TextView textTitle = view.findViewById(R.id.textTitle);
                            textTitle.setText(typeBeanList.get(i).getN());
                            if (i == 0) {
                                tablayout.addTab(tablayout.newTab().setCustomView(view), true);
                            } else {
                                tablayout.addTab(tablayout.newTab().setCustomView(view), false);
                            }
                        }
                    }else if (couponIndex.getStatus()==3){
                        MyDialog.showReLoginDialog(YouHuiQuanActivity.this);
                    }else {
                        Toast.makeText(YouHuiQuanActivity.this, couponIndex.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(YouHuiQuanActivity.this,"数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                Toast.makeText(YouHuiQuanActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }

    class MyPageAdapter extends FragmentPagerAdapter {

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new YouHuiQuanFragment(typeBeanList.get(position).getV());
        }

        @Override
        public int getCount() {
            return typeBeanList.size();
        }
    }
}
