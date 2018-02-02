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
import android.widget.Toast;

import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.fragment.FenXiaoFragment;
import com.vip.uyux.model.BonusDistributionorder;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;

import java.util.HashMap;
import java.util.List;

public class FenXiaoDDActivity extends ZjbBaseActivity implements View.OnClickListener {
    private TabLayout tablayout;
    private ViewPager viewPager;
    private TextView textSum;
    private int type;
    private TextView textDes;
    private List<BonusDistributionorder.TypeBean> typeBeanList;

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
        onRefresh();
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url;
        switch (type) {
            case 1:
                url = Constant.HOST + Constant.Url.SHARE_ESTIMATE;
                break;
            case 2:
                url = Constant.HOST + Constant.Url.SHARE_ORDER;
                break;
            default:
                url = Constant.HOST + Constant.Url.SHARE_ESTIMATE;
                break;
        }
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        return new OkObject(params, url);
    }

    public void onRefresh() {

        showLoadingDialog();
        ApiClient.post(FenXiaoDDActivity.this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("FenXiaoDDActivity--onSuccess", s + "");
                try {
                    BonusDistributionorder bonusDistributionorder = GsonUtils.parseJSON(s, BonusDistributionorder.class);
                    if (bonusDistributionorder.getStatus() == 1) {
                        typeBeanList = bonusDistributionorder.getType();
                        viewPager.setAdapter(new MyPageAdapter(getSupportFragmentManager()));
                        tablayout.setupWithViewPager(viewPager);
                        tablayout.removeAllTabs();
                        for (int i = 0; i < typeBeanList.size(); i++) {
                            View view = LayoutInflater.from(FenXiaoDDActivity.this).inflate(R.layout.item_tablayout, null);
                            TextView textTitle = view.findViewById(R.id.textTitle);
                            textTitle.setText(typeBeanList.get(i).getN());
                            if (i == 0) {
                                tablayout.addTab(tablayout.newTab().setCustomView(view), true);
                            } else {
                                tablayout.addTab(tablayout.newTab().setCustomView(view), false);
                            }
                        }
                    } else if (bonusDistributionorder.getStatus() == 3) {
                        MyDialog.showReLoginDialog(FenXiaoDDActivity.this);
                    } else {
                        Toast.makeText(FenXiaoDDActivity.this, bonusDistributionorder.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(FenXiaoDDActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(FenXiaoDDActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });


    }

    class MyPageAdapter extends FragmentPagerAdapter {

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new FenXiaoFragment(typeBeanList.get(position).getV(), type);
        }

        @Override
        public int getCount() {
            return typeBeanList.size();
        }
    }

    public void setSum(String sum) {
        textSum.setText(sum);
    }
}
