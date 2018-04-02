package com.vip.uyux.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ToLoginActivity;
import com.vip.uyux.base.ZjbBaseNotLeftActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.customview.ColorTrackTabLayout;
import com.vip.uyux.fragment.HaiTaoFragment;
import com.vip.uyux.model.IndexSeaamoycate;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;

import java.util.HashMap;
import java.util.List;

public class HaiTaoActivity extends ZjbBaseNotLeftActivity implements View.OnClickListener {

    private int pcate;
    private ColorTrackTabLayout tablayout;
    private ViewPager viewPager;
    private List<IndexSeaamoycate.DataBean> dataBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hai_tao);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        pcate = intent.getIntExtra(Constant.IntentKey.PCATE, 0);
    }

    @Override
    protected void findID() {
        tablayout = (ColorTrackTabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.id_viewpager);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.viewSearch).setOnClickListener(this);
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.INDEX_SEAAMOYCATE;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("pcate", String.valueOf(pcate));
        return new OkObject(params, url);
    }

    @Override
    protected void initData() {
        ApiClient.post(HaiTaoActivity.this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("ChanPinLBTabActivity--onSuccess", s + "");
                try {

                    IndexSeaamoycate indexSeaamoycate = GsonUtils.parseJSON(s, IndexSeaamoycate.class);
                    if (indexSeaamoycate.getStatus() == 1) {
                        dataBeanList = indexSeaamoycate.getData();
                        viewPager.setAdapter(new MyPageAdapter(getSupportFragmentManager()));
                        tablayout.setupWithViewPager(viewPager);
                        for (int i = 0; i < tablayout.getTabCount(); i++) {
                            tablayout.getTabAt(i).setText(dataBeanList.get(i).getName());
                            if (dataBeanList.get(i).getAct() == 1) {
                                tablayout.getTabAt(i).select();
                            }
                        }
                    } else if (indexSeaamoycate.getStatus() == 3) {
                        MyDialog.showReLoginDialog(HaiTaoActivity.this);
                    } else {
                        Toast.makeText(HaiTaoActivity.this, indexSeaamoycate.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(HaiTaoActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                Toast.makeText(HaiTaoActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.viewSearch:
                Intent intent = new Intent();
                if (isLogin) {
                    intent.setClass(HaiTaoActivity.this, SouSuoActivity.class);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(HaiTaoActivity.this);
                }
                break;
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
            return new HaiTaoFragment(dataBeanList.get(position).getId());
        }

        @Override
        public int getCount() {
            return dataBeanList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return dataBeanList.get(position).getName();
        }
    }
}
