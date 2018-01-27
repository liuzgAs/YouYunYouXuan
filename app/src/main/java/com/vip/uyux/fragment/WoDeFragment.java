package com.vip.uyux.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vip.uyux.R;
import com.vip.uyux.activity.FenXiangZXActivity;
import com.vip.uyux.activity.GeRenXXActivity;
import com.vip.uyux.activity.JiFenSCActivity;
import com.vip.uyux.activity.SheZhiActivity;
import com.vip.uyux.activity.WoDeCPActivity;
import com.vip.uyux.activity.WoDeDDActivity;
import com.vip.uyux.activity.WoDeJFActivity;
import com.vip.uyux.activity.WoDeSCActivity;
import com.vip.uyux.activity.YouHuiQuanActivity;
import com.vip.uyux.activity.YuEActivity;
import com.vip.uyux.activity.ZuJiActivity;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ToLoginActivity;
import com.vip.uyux.base.ZjbBaseFragment;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.model.UserMy;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GlideApp;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.util.ScreenUtils;

import java.util.HashMap;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WoDeFragment extends ZjbBaseFragment implements View.OnClickListener {


    private View mInflate;
    private View viewBar;
    private ImageView imageHead;
    private TextView textName;
    private TextView textLv0;
    private TextView textId;
    private TextView textGrowthDes;
    private ImageView imageShengJi;
    private ImageView imageLvB;
    private ImageView imageLv;
    private TextView textYuE;
    private TextView textJiFen;
    private Badge[] badgeDD = new Badge[5];
    private Badge badge;
    private ImageView imageXiaoXi;
    private TextView textCouponNum;
    private BroadcastReceiver reciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case Constant.BroadcastCode.USERINFO:
                    initData();
                    break;
                case Constant.BroadcastCode.TIXIAN:
                    initData();
                    break;
                default:
                    break;
            }
        }
    };
    private ImageView[] imageDD = new ImageView[5];
    private int[] imageDDID = new int[]{
            R.id.image0300,
            R.id.image0301,
            R.id.image0302,
            R.id.image0303,
            R.id.image0304,
    };

    public WoDeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_wo_de, container, false);
            init();
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mInflate.getParent();
        if (parent != null) {
            parent.removeView(mInflate);
        }
        return mInflate;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void findID() {
        viewBar = mInflate.findViewById(R.id.viewBar);
        imageHead = mInflate.findViewById(R.id.imageHead);
        textName = mInflate.findViewById(R.id.textName);
        textLv0 = mInflate.findViewById(R.id.textLv0);
        textId = mInflate.findViewById(R.id.textId);
        textGrowthDes = mInflate.findViewById(R.id.textGrowthDes);
        imageShengJi = mInflate.findViewById(R.id.imageShengJi);
        imageLvB = mInflate.findViewById(R.id.imageLvB);
        imageLv = mInflate.findViewById(R.id.imageLv);
        textYuE = mInflate.findViewById(R.id.textYuE);
        textJiFen = mInflate.findViewById(R.id.textJiFen);
        badge = new QBadgeView(mContext)
                .setBadgeTextColor(Color.WHITE)
                .setBadgeTextSize(10f, true)
                .setBadgeBackgroundColor(getResources().getColor(R.color.basic_color))
                .setBadgeGravity(Gravity.END | Gravity.TOP)
                .setBadgePadding(2f, true)
                .setGravityOffset(5f, 5f, true);
        imageXiaoXi = mInflate.findViewById(R.id.imageXiaoXi);
        textCouponNum = mInflate.findViewById(R.id.textCouponNum);
        for (int i = 0; i < imageDD.length; i++) {
            imageDD[i] = mInflate.findViewById(imageDDID[i]);
            badgeDD[i] = new QBadgeView(getContext())
                    .setBadgeTextColor(Color.WHITE)
                    .setBadgeTextSize(8f, true)
                    .setBadgeBackgroundColor(getContext().getResources().getColor(R.color.red))
                    .setBadgeGravity(Gravity.END | Gravity.TOP)
                    .setGravityOffset(0f, 0f, true);
        }
    }

    @Override
    protected void initViews() {
        ViewGroup.LayoutParams layoutParams = viewBar.getLayoutParams();
        layoutParams.height = ScreenUtils.getStatusBarHeight(mContext) + (int) mContext.getResources().getDimension(R.dimen.titleHeight);
        viewBar.setLayoutParams(layoutParams);
    }

    @Override
    protected void setListeners() {
        mInflate.findViewById(R.id.viewYuE).setOnClickListener(this);
        mInflate.findViewById(R.id.imageFenXiangZX).setOnClickListener(this);
        mInflate.findViewById(R.id.viewGeRen).setOnClickListener(this);
        mInflate.findViewById(R.id.viewYouHuiQuan).setOnClickListener(this);
        mInflate.findViewById(R.id.viewWoDeCP).setOnClickListener(this);
        mInflate.findViewById(R.id.viewZuJi).setOnClickListener(this);
        mInflate.findViewById(R.id.viewShouCang).setOnClickListener(this);
        mInflate.findViewById(R.id.viewWoDeJF).setOnClickListener(this);
        mInflate.findViewById(R.id.viewDaiFuKuan).setOnClickListener(this);
        mInflate.findViewById(R.id.viewDaiFaHuo).setOnClickListener(this);
        mInflate.findViewById(R.id.viewDaiShouHuo).setOnClickListener(this);
        mInflate.findViewById(R.id.viewDaiPingJia).setOnClickListener(this);
        mInflate.findViewById(R.id.viewUBiSC).setOnClickListener(this);
        mInflate.findViewById(R.id.imageSheZhi).setOnClickListener(this);
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.USER_MY;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        return new OkObject(params, url);
    }

    @Override
    protected void initData() {
        LogUtil.LogShitou("WoDeFragment--initData", "11111" + isLogin);
        if (isLogin) {
            GlideApp.with(WoDeFragment.this)
                    .load(userInfo.getHeadImg())
                    .centerCrop()
                    .dontAnimate()
                    .placeholder(R.mipmap.ic_empty)
                    .into(imageHead);
            textName.setText(userInfo.getUserName());
            textLv0.setVisibility(View.GONE);
            textId.setText("");
            textGrowthDes.setText("成长值：0");
            imageShengJi.setVisibility(View.VISIBLE);
            imageLvB.setVisibility(View.GONE);
            imageLv.setVisibility(View.GONE);
            showLoadingDialog();
            ApiClient.post(mContext, getOkObject(), new ApiClient.CallBack() {
                @Override
                public void onSuccess(String s) {
                    cancelLoadingDialog();
                    LogUtil.LogShitou("WoDeFragment--onSuccess", s + "");
                    try {
                        UserMy userMy = GsonUtils.parseJSON(s, UserMy.class);
                        if (userMy.getStatus() == 1) {
                            GlideApp.with(WoDeFragment.this)
                                    .load(userMy.getHeadimg())
                                    .centerCrop()
                                    .dontAnimate()
                                    .placeholder(R.mipmap.ic_empty)
                                    .into(imageHead);
                            textName.setText(userMy.getNickname());
                            textId.setText(userMy.getId());
                            textGrowthDes.setText(userMy.getGrowthDes());
                            for (int i = 0; i < imageDD.length; i++) {
                                badgeDD[i].setBadgeNumber(userMy.getOrderNum().get(i)).bindTarget(imageDD[i]);
                            }
                            if (userMy.getLv() > 0) {
                                textLv0.setVisibility(View.GONE);
                                imageShengJi.setVisibility(View.GONE);
                                imageLvB.setVisibility(View.VISIBLE);
                                switch (userMy.getLv()) {
                                    case 1:
                                        imageLv.setImageResource(R.mipmap.lv1);
                                        break;
                                    case 2:
                                        imageLv.setImageResource(R.mipmap.lv2);
                                        break;
                                    case 3:
                                        imageLv.setImageResource(R.mipmap.lv3);
                                        break;
                                    case 4:
                                        imageLv.setImageResource(R.mipmap.lv4);
                                        break;
                                    case 5:
                                        imageLv.setImageResource(R.mipmap.lv5);
                                        break;
                                    default:
                                        break;
                                }
                                imageLv.setVisibility(View.VISIBLE);
                                textYuE.setText(userMy.getMoney());
                                textJiFen.setText(String.valueOf(userMy.getScore()));
                                textCouponNum.setText(userMy.getCouponNum() + "张");
                            } else {
                                textLv0.setVisibility(View.VISIBLE);
                                imageShengJi.setVisibility(View.VISIBLE);
                                imageLvB.setVisibility(View.GONE);
                                imageLv.setVisibility(View.GONE);
                                badge.setBadgeNumber(userMy.getTipsNum()).bindTarget(imageXiaoXi);
                            }
                        } else if (userMy.getStatus() == 3) {
                            MyDialog.showReLoginDialog(mContext);
                        } else {
                            Toast.makeText(mContext, userMy.getInfo(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(mContext, "数据出错", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError() {
                    cancelLoadingDialog();
                    Toast.makeText(mContext, "请求失败", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            textName.setText("登录");
            textLv0.setVisibility(View.VISIBLE);
            textId.setText("");
            textGrowthDes.setText("成长值：0");
            imageShengJi.setVisibility(View.VISIBLE);
            imageLvB.setVisibility(View.GONE);
            imageLv.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.imageSheZhi:
                intent.setClass(mContext, SheZhiActivity.class);
                startActivity(intent);
                break;
            case R.id.viewUBiSC:
                if (isLogin) {
                    intent.setClass(mContext, JiFenSCActivity.class);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(mContext);
                }
                break;
            case R.id.viewDaiFuKuan:
                if (isLogin) {
                    intent.setClass(mContext, WoDeDDActivity.class);
                    intent.putExtra(Constant.IntentKey.TYPE, 1);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(mContext);
                }
                break;
            case R.id.viewDaiFaHuo:
                if (isLogin) {
                    intent.setClass(mContext, WoDeDDActivity.class);
                    intent.putExtra(Constant.IntentKey.TYPE, 2);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(mContext);
                }
                break;
            case R.id.viewDaiShouHuo:
                if (isLogin) {
                    intent.setClass(mContext, WoDeDDActivity.class);
                    intent.putExtra(Constant.IntentKey.TYPE, 3);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(mContext);
                }
                break;
            case R.id.viewDaiPingJia:
                if (isLogin) {
                    intent.setClass(mContext, WoDeDDActivity.class);
                    intent.putExtra(Constant.IntentKey.TYPE, 4);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(mContext);
                }
                break;
            case R.id.viewWoDeJF:
                if (isLogin) {
                    intent.setClass(mContext, WoDeJFActivity.class);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(mContext);
                }
                break;
            case R.id.viewShouCang:
                if (isLogin) {
                    intent.setClass(mContext, WoDeSCActivity.class);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(mContext);
                }
                break;
            case R.id.viewZuJi:
                if (isLogin) {
                    intent.setClass(mContext, ZuJiActivity.class);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(mContext);
                }
                break;
            case R.id.viewWoDeCP:
                if (isLogin) {
                    intent.setClass(mContext, WoDeCPActivity.class);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(mContext);
                }
                break;
            case R.id.viewYouHuiQuan:
                if (isLogin) {
                    intent.setClass(mContext, YouHuiQuanActivity.class);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(mContext);
                }
                break;
            case R.id.viewGeRen:
                if (isLogin) {
                    intent.setClass(mContext, GeRenXXActivity.class);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(mContext);
                }
                break;
            case R.id.imageFenXiangZX:
                if (isLogin) {
                    intent.setClass(mContext, FenXiangZXActivity.class);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(mContext);
                }
                break;
            case R.id.viewYuE:
                if (isLogin) {
                    intent.setClass(mContext, YuEActivity.class);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(mContext);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.BroadcastCode.USERINFO);
        filter.addAction(Constant.BroadcastCode.TIXIAN);
        getActivity().registerReceiver(reciver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(reciver);
    }
}
