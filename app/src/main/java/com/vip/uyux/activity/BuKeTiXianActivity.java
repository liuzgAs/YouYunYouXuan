package com.vip.uyux.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.vip.uyux.R;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.customview.SingleBtnDialog;
import com.vip.uyux.fragment.YongJinFragment;

import java.util.ArrayList;
import java.util.List;

public class BuKeTiXianActivity extends ZjbBaseActivity implements View.OnClickListener {
    private TabLayout tablayout;
    private ViewPager viewPager;
    List<String> list = new ArrayList<>();
    private TextView textShouYi;
    private int type;
    private Button btnLiJiTX;
    private TextView textViewTitle;
    private String upUrl;
    private ImageView imageWhat;
    private BroadcastReceiver reciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case Constant.BroadcastCode.TIXIAN:
                    initData();
                    break;
                default:
                    break;
            }
        }
    };
    private TextView textViewRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_de_yu_e);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        type = intent.getIntExtra(Constant.IntentKey.TYPE, 0);
    }

    @Override
    protected void findID() {
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        textShouYi = (TextView) findViewById(R.id.textShouYi);
        btnLiJiTX = (Button) findViewById(R.id.btnLiJiTX);
        textViewTitle = ((TextView) findViewById(R.id.textViewTitle));
        imageWhat = (ImageView) findViewById(R.id.imageWhat);
        textViewRight = (TextView) findViewById(R.id.textViewRight);
    }

    @Override
    protected void initViews() {
        textViewRight.setText("提现记录");
        switch (type) {
            case 1:
                textViewTitle.setText("不可提现佣金");
                imageWhat.setVisibility(View.VISIBLE);
                list.clear();
                list.add("团队推广");
                list.add("返佣明细");
                break;
            case 2:
                textViewTitle.setText("可提现佣金");
                list.clear();
                list.add("团队推广");
                list.add("返佣明细");
                break;
            case 3:
                textViewTitle.setText("分销佣金");
                list.clear();
                list.add("团队推广");
                list.add("返佣明细");
                break;
            case 4:
                textViewTitle.setText("预计佣金");
                list.clear();
                list.add("团队推广");
                list.add("返佣明细");
                break;
            default:
                textViewTitle.setText("不可提现佣金");
                list.clear();
                list.add("团队推广");
                list.add("消费返佣");
                break;
        }
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
        switch (type) {
            case 1:
                btnLiJiTX.setText("立即升级马上提现");
                break;
            case 2:
                btnLiJiTX.setText("我要提现");
                break;
            case 3:
                btnLiJiTX.setText("累计佣金");
                break;
            case 4:
                btnLiJiTX.setVisibility(View.INVISIBLE);
                break;
            default:
                break;
        }
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        btnLiJiTX.setOnClickListener(this);
        imageWhat.setOnClickListener(this);
        textViewRight.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        SpannableString span = new SpannableString("¥473");
        span.setSpan(new RelativeSizeSpan(0.4f), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textShouYi.setText(span);
    }

    public void setMoney(String money, String upUrl) {
        this.upUrl = upUrl;
        SpannableString span = new SpannableString("¥" + money);
        span.setSpan(new RelativeSizeSpan(0.4f), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textShouYi.setText(span);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.textViewRight:
                intent.setClass(BuKeTiXianActivity.this, TiXianJLActivity.class);
                intent.putExtra(Constant.IntentKey.TYPE, type);
                startActivity(intent);
                break;
            case R.id.imageWhat:
                final SingleBtnDialog singleBtnDialog = new SingleBtnDialog(this, "您的身份等级低于下线等级情况下，获得推⼴广奖励和消费返佣为待提现状态，升级等级即可提现", "知道了");
                singleBtnDialog.show();
                singleBtnDialog.setClicklistener(new SingleBtnDialog.ClickListenerInterface() {
                    @Override
                    public void doWhat() {
                        singleBtnDialog.dismiss();
                    }
                });
                break;
            case R.id.btnLiJiTX:
                switch (type) {
                    case 1:
                        intent.setClass(this, WebActivity.class);
                        intent.putExtra(Constant.IntentKey.TITLE, "立即升级");
                        intent.putExtra(Constant.IntentKey.URL, upUrl);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.setClass(this, TiXianActivity.class);
                        intent.putExtra(Constant.IntentKey.TYPE, 2);
                        startActivity(intent);
                        break;
                    case 3:
//                        intent.setClass(this, TiXianActivity.class);
//                        intent.putExtra(Constant.IntentKey.TYPE, 2);
//                        startActivity(intent);
                        break;
                    default:
                        break;
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
            switch (position) {
                case 0:
                    return new YongJinFragment(1, type);
                case 1:
                    return new YongJinFragment(2, type);
                default:
                    return new YongJinFragment(2, type);
            }

        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.BroadcastCode.TIXIAN);
        registerReceiver(reciver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(reciver);
    }
}
