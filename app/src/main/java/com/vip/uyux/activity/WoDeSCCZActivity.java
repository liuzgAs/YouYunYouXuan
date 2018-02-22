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
import com.vip.uyux.fragment.ShouCangFragment;
import com.vip.uyux.interfacepage.OnBianJiListener;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.model.UserCollect;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;

import java.util.HashMap;
import java.util.List;

public class WoDeSCCZActivity extends ZjbBaseNotLeftActivity implements View.OnClickListener {
    private TabLayout tablayout;
    private ViewPager viewPager;
    private List<UserCollect.TypeBean> typeBeanList;
    private TextView textViewRight;
    private ShouCangFragment[] shouCangFragments;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_de_sccz);
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
        textViewRight = (TextView) findViewById(R.id.textViewRight);
    }

    @Override
    protected void initViews() {
        textViewRight.setText("编辑");
        ((TextView) findViewById(R.id.textViewTitle)).setText("我的收藏");
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        textViewRight.setOnClickListener(this);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                WoDeSCCZActivity.this.position = position;
                if (shouCangFragments[position].isBianJi) {
                    textViewRight.setText("取消");
                } else {
                    textViewRight.setText("编辑");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.USER_COLLECT;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        return new OkObject(params, url);
    }

    @Override
    protected void initData() {
        ApiClient.post(WoDeSCCZActivity.this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("PingJiaGLActivity--onSuccess", s + "");
                try {
                    UserCollect userCollect = GsonUtils.parseJSON(s, UserCollect.class);
                    if (userCollect.getStatus() == 1) {
                        typeBeanList = userCollect.getType();
                        shouCangFragments = new ShouCangFragment[typeBeanList.size()];
                        viewPager.setAdapter(new MyPageAdapter(getSupportFragmentManager()));
                        tablayout.setupWithViewPager(viewPager);
                        tablayout.removeAllTabs();
                        for (int i = 0; i < typeBeanList.size(); i++) {
                            View view = LayoutInflater.from(WoDeSCCZActivity.this).inflate(R.layout.item_tablayout, null);
                            TextView textTitle = view.findViewById(R.id.textTitle);
                            textTitle.setText(typeBeanList.get(i).getN());
                            if (typeBeanList.get(i).getAct() == 1) {
                                tablayout.addTab(tablayout.newTab().setCustomView(view), true);
                            } else {
                                tablayout.addTab(tablayout.newTab().setCustomView(view), false);
                            }
                        }
                    } else if (userCollect.getStatus() == 3) {
                        MyDialog.showReLoginDialog(WoDeSCCZActivity.this);
                    } else {
                        Toast.makeText(WoDeSCCZActivity.this, userCollect.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(WoDeSCCZActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                Toast.makeText(WoDeSCCZActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textViewRight:
                shouCangFragments[position].setBianJiBtn();
                break;
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
            shouCangFragments[position] = new ShouCangFragment(typeBeanList.get(position).getV());
            shouCangFragments[position].setOnBianJiListener(new OnBianJiListener() {
                @Override
                public void setBianJi(String bianJi) {
                    textViewRight.setText(bianJi);
                }
            });
            return shouCangFragments[position];

        }

        @Override
        public int getCount() {
            return typeBeanList.size();
        }
    }
}
