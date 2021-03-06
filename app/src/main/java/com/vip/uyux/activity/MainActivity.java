package com.vip.uyux.activity;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vip.uyux.R;
import com.vip.uyux.application.MyApplication;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseNotLeftActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.fragment.FenLeiCZFragment;
import com.vip.uyux.fragment.GouWuCheFragment;
import com.vip.uyux.fragment.ShouYeFragment;
import com.vip.uyux.fragment.TuiJianFragment;
import com.vip.uyux.fragment.WoDeFragment;
import com.vip.uyux.model.AdvsBean;
import com.vip.uyux.model.CartNum;
import com.vip.uyux.model.FragmentTabHost;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.util.ACache;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.BackHandlerHelper;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.util.UpgradeUtils;

import java.util.HashMap;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends ZjbBaseNotLeftActivity {

    private String[] tabsItem = new String[5];
    private Class[] fragment = new Class[]{
            ShouYeFragment.class,
            TuiJianFragment.class,
            FenLeiCZFragment.class,
            GouWuCheFragment.class,
            WoDeFragment.class,
    };
    private int[] imgRes = new int[]{
            R.drawable.selector_item01,
            R.drawable.selector_item02,
            R.drawable.selector_item03,
            R.drawable.selector_item04,
            R.drawable.selector_item05,
    };
    public FragmentTabHost mTabHost;
    private BroadcastReceiver reciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case Constant.BroadcastCode.ADV:
                    AdvsBean advsBean = (AdvsBean) intent.getSerializableExtra(Constant.IntentKey.BEAN);
                    tiaoZhuan(advsBean);
                    break;
                case Constant.BroadcastCode.SHUA_XIN_CAR:
                    initData();
                    break;
                case Constant.BroadcastCode.SET_MAIN_TAB:
                    int position = intent.getIntExtra(Constant.IntentKey.POSITION, 0);
                    mTabHost.setCurrentTab(position);
                    break;
                default:
                    break;
            }
        }
    };
    private View inflate3;
    private String did;
    private Badge badge;

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
        Constant.MainActivityAlive = 1;
        ACache aCache = ACache.get(this, Constant.Acache.LOCATION);
        did = aCache.getAsString(Constant.Acache.DID);
    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        AdvsBean advsBean = (AdvsBean) intent.getSerializableExtra(Constant.IntentKey.BEAN);
        if (advsBean != null) {
            tiaoZhuan(advsBean);
        }
    }

    /**
     * code跳转
     *
     * @param advsBean
     */
    private void tiaoZhuan(AdvsBean advsBean) {
        Intent intent = new Intent();
        switch (advsBean.getCode()) {
            case "web":
                intent.setClass(this, WebActivity.class);
                intent.putExtra(Constant.IntentKey.TITLE, advsBean.getTitle());
                intent.putExtra(Constant.IntentKey.URL, advsBean.getUrl());
                startActivity(intent);
                break;
            case "app_i":
                break;
            case "app_goods_info":
                intent.setClass(this, ChanPinXQCZActivity.class);
                intent.putExtra(Constant.IntentKey.ID, advsBean.getItem_id());
                startActivity(intent);
                break;
            case "app_user_msg":
                intent.setClass(this, XiaoXiZXActivity.class);
                startActivity(intent);
                break;
            case "app_goods_pcate":
                intent.setClass(this, ChanPinLBTabActivity.class);
                intent.putExtra(Constant.IntentKey.TITLE, advsBean.getTitle());
                intent.putExtra(Constant.IntentKey.PCATE, advsBean.getItem_id());
                startActivity(intent);
                break;
            case "app_goods_cate":
                intent.setClass(this, ChanPinLBTabActivity.class);
                intent.putExtra(Constant.IntentKey.TITLE, advsBean.getTitle());
                intent.putExtra(Constant.IntentKey.CATE, advsBean.getItem_id());
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void findID() {
        mTabHost = findViewById(R.id.tabHost);
        badge = new QBadgeView(MainActivity.this)
                .setBadgeTextColor(Color.WHITE)
                .setBadgeTextSize(10f, true)
                .setBadgeBackgroundColor(getResources().getColor(R.color.basic_color))
                .setBadgeGravity(Gravity.END | Gravity.TOP)
                .setBadgePadding(3f, true)
                .setGravityOffset(20f, 2f, true);
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
            View inflate;
            if (i == 3) {
                inflate3 = getLayoutInflater().inflate(R.layout.tabs_item, null);
                TextView tabsText = inflate3.findViewById(R.id.tabs_text);
                ImageView tabsImg = inflate3.findViewById(R.id.tabs_img);
                tabsImg.setImageResource(imgRes[i]);
                tabsText.setText(tabsItem[i]);
                mTabHost.addTab(mTabHost.newTabSpec(tabsItem[i]).setIndicator(inflate3), fragment[i], null);
            } else {
                inflate = getLayoutInflater().inflate(R.layout.tabs_item, null);
                TextView tabsText = inflate.findViewById(R.id.tabs_text);
                ImageView tabsImg = inflate.findViewById(R.id.tabs_img);
                tabsImg.setImageResource(imgRes[i]);
                tabsText.setText(tabsItem[i]);
                mTabHost.addTab(mTabHost.newTabSpec(tabsItem[i]).setIndicator(inflate), fragment[i], null);
            }
        }
    }

    @Override
    protected void setListeners() {
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.CART_NUM;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("did", did);
        return new OkObject(params, url);
    }

    @Override
    protected void initData() {
        ApiClient.post(MainActivity.this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("MainActivity--onSuccess", s + "");
                try {
                    CartNum cartNum = GsonUtils.parseJSON(s, CartNum.class);
                    if (cartNum.getStatus() == 1) {
                        if (cartNum.getNum() > 0) {
                            badge.setBadgeText("")
                                    .bindTarget(inflate3);
                        } else {
                            badge.hide(true);
                        }
                    } else if (cartNum.getStatus() == 3) {
                        MyDialog.showReLoginDialog(MainActivity.this);
                    } else {
                        Toast.makeText(MainActivity.this, cartNum.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
        if (isLogin) {
            if (getApplicationInfo().packageName.equals(getCurProcessName(MainActivity.this))) {
                RongIM.connect(userInfo.getYunToken(), new RongIMClient.ConnectCallback() {

                    /**
                     * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                     * 2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                     */
                    @Override
                    public void onTokenIncorrect() {
                        LogUtil.LogShitou("CheLiangXQActivity--onTokenIncorrect", "1111");
                    }

                    /**
                     * 连接融云成功
                     *
                     * @param userid 当前 token 对应的用户 id
                     */
                    @Override
                    public void onSuccess(String userid) {
                    }

                    /**
                     * 连接融云失败
                     *
                     * @param errorCode 错误码，可到官网 查看错误码对应的注释
                     */
                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {
                        LogUtil.LogShitou("CheLiangXQActivity--onError", "" + errorCode.toString());
                    }
                });
            } else {
                LogUtil.LogShitou("ChanPinXQCZActivity--rongYun", "111111111111");
            }
        }
    }

    String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
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

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.BroadcastCode.ADV);
        filter.addAction(Constant.BroadcastCode.SHUA_XIN_CAR);
        filter.addAction(Constant.BroadcastCode.SET_MAIN_TAB);
        registerReceiver(reciver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(reciver);
        Constant.MainActivityAlive = 0;
    }
}
