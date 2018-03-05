package com.vip.uyux.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseNotLeftActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.customview.ColorTrackTabLayout;
import com.vip.uyux.fragment.ChanPinLBFragment;
import com.vip.uyux.model.GoodsIndex;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;

import java.util.HashMap;
import java.util.List;


public class ChanPinLBTabActivity extends ZjbBaseNotLeftActivity implements View.OnClickListener {
    private ColorTrackTabLayout tablayout;
    private ViewPager viewPager;
    private List<GoodsIndex.CateBean> cateBeanList;
    private int cate;
    private int pcate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chan_pin_lbtab);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        cate = intent.getIntExtra(Constant.IntentKey.CATE, 0);
        pcate = intent.getIntExtra(Constant.IntentKey.PCATE, 0);
    }

    @Override
    protected void findID() {
        tablayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewPager);
    }

    @Override
    protected void initViews() {

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
        String url = Constant.HOST + Constant.Url.GOODS_INDEX;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("cate", String.valueOf(cate));
        params.put("pcate", String.valueOf(pcate));
        return new OkObject(params, url);
    }

    @Override
    protected void initData() {
        ApiClient.post(ChanPinLBTabActivity.this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("ChanPinLBTabActivity--onSuccess", s + "");
                try {

                    GoodsIndex goodsIndex = GsonUtils.parseJSON(s, GoodsIndex.class);
                    if (goodsIndex.getStatus() == 1) {
                        ((TextView) findViewById(R.id.textViewTitle)).setText(goodsIndex.getTitle());
                        cateBeanList = goodsIndex.getCate();
                        viewPager.setAdapter(new ChanPinLBTabActivity.MyPageAdapter(getSupportFragmentManager()));
                        tablayout.setupWithViewPager(viewPager);
//                        tablayout.setTabTextColors(ContextCompat.getColor(ChanPinLBTabActivity.this, R.color.light_black), ContextCompat.getColor(ChanPinLBTabActivity.this, R.color.basic_color));
                        for (int i = 0; i < tablayout.getTabCount(); i++) {
                            tablayout.getTabAt(i).setText(cateBeanList.get(i).getName());
                            if (cateBeanList.get(i).getAct() == 1) {
                                tablayout.getTabAt(i).select();
                            }
                        }
//                        reflex(tablayout);
                    } else if (goodsIndex.getStatus() == 3) {
                        MyDialog.showReLoginDialog(ChanPinLBTabActivity.this);
                    } else {
                        Toast.makeText(ChanPinLBTabActivity.this, goodsIndex.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(ChanPinLBTabActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                Toast.makeText(ChanPinLBTabActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
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
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    class MyPageAdapter extends FragmentPagerAdapter {

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new ChanPinLBFragment(cateBeanList.get(position).getId());

        }

        @Override
        public int getCount() {
            return cateBeanList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return cateBeanList.get(position).getName();
        }
    }

}
