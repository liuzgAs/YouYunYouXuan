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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vip.uyux.R;
import com.vip.uyux.activity.FenXiangZXActivity;
import com.vip.uyux.activity.GeRenXXActivity;
import com.vip.uyux.activity.PingJiaGLActivity;
import com.vip.uyux.activity.SheZhiActivity;
import com.vip.uyux.activity.ShouHouFWActivity;
import com.vip.uyux.activity.UbiSCActivity;
import com.vip.uyux.activity.WebActivity;
import com.vip.uyux.activity.WoDeCPActivity;
import com.vip.uyux.activity.WoDeDDActivity;
import com.vip.uyux.activity.WoDeJFActivity;
import com.vip.uyux.activity.WoDeSCCZActivity;
import com.vip.uyux.activity.XiaoXiZXActivity;
import com.vip.uyux.activity.YouHuiQuanActivity;
import com.vip.uyux.activity.YuEActivity;
import com.vip.uyux.activity.ZuJiActivity;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ToLoginActivity;
import com.vip.uyux.base.ZjbBaseFragment;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.MassageNum;
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
    private TextView textId;
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
                case Constant.BroadcastCode.SHUA_XIN_TIPS:
                    setTips();
                    break;
                case Constant.BroadcastCode.SHUAXINDD:
                    initData();
                    break;
                case Constant.BroadcastCode.USERINFO:
                    initData();
                    break;
                case Constant.BroadcastCode.TIXIAN:
                    initData();
                    break;
                case Constant.BroadcastCode.SHUA_XIN_U_BI:
                    initData();
                    break;
                case Constant.BroadcastCode.ZHI_FU_CG:
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
    private String qc_url;
    private ImageView imageFenXiangZX;
    private Button btnShengJi;
    private TextView textScore;
    private TextView textChengZhangZhi;
    private TextView textLv;
    private View viewDiBu;
    private ImageView imageZhuanShi;
    private TextView textFuWu;
    private TextView textShengJiFW;
    private ImageView image0100;
    private ImageView image0102;
    private ImageView image0101;
    private ImageView image0103;
    private View text0101;
    private View text0100;
    private View text0102;
    private String vip_url;
    private String vip_url_title;

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
        textId = mInflate.findViewById(R.id.textId);
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
        imageFenXiangZX = mInflate.findViewById(R.id.imageFenXiangZX);
        btnShengJi = mInflate.findViewById(R.id.btnShengJi);
        textScore = mInflate.findViewById(R.id.textScore);
        textChengZhangZhi = mInflate.findViewById(R.id.textChengZhangZhi);
        textLv = mInflate.findViewById(R.id.textLv);
        viewDiBu = mInflate.findViewById(R.id.viewDiBu);
        imageZhuanShi = mInflate.findViewById(R.id.imageZhuanShi);
        textFuWu = mInflate.findViewById(R.id.textFuWu);
        textShengJiFW = mInflate.findViewById(R.id.textShengJiFW);
        image0100 = mInflate.findViewById(R.id.image0100);
        image0102 = mInflate.findViewById(R.id.image0102);
        image0101 = mInflate.findViewById(R.id.image0101);
        image0103 = mInflate.findViewById(R.id.image0103);
        text0101 = mInflate.findViewById(R.id.text0101);
        text0100 = mInflate.findViewById(R.id.text0100);
        text0102 = mInflate.findViewById(R.id.text0102);
    }

    @Override
    protected void initViews() {
        ViewGroup.LayoutParams layoutParams = viewBar.getLayoutParams();
        layoutParams.height = ScreenUtils.getStatusBarHeight(mContext) + (int) mContext.getResources().getDimension(R.dimen.titleHeight);
        viewBar.setLayoutParams(layoutParams);
        imageFenXiangZX.setVisibility(View.GONE);
    }

    @Override
    protected void setListeners() {
        mInflate.findViewById(R.id.viewYuE).setOnClickListener(this);
        imageFenXiangZX.setOnClickListener(this);
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
        mInflate.findViewById(R.id.imageXiaoXi).setOnClickListener(this);
        mInflate.findViewById(R.id.viewGuanYuWM).setOnClickListener(this);
        mInflate.findViewById(R.id.viewPingJiaGL).setOnClickListener(this);
        mInflate.findViewById(R.id.viewTuiHuanHuo).setOnClickListener(this);
        btnShengJi.setOnClickListener(this);
        viewDiBu.setOnClickListener(this);
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
            textId.setText("");
            textLv.setText("VIP.0");
            textScore.setVisibility(View.GONE);
            textChengZhangZhi.setVisibility(View.GONE);
            btnShengJi.setVisibility(View.VISIBLE);
            viewDiBu.setBackgroundResource(R.drawable.shape_wode_dibu);
            imageZhuanShi.setImageResource(R.mipmap.zhuanshi_b);
            textFuWu.setVisibility(View.GONE);
            textShengJiFW.setVisibility(View.VISIBLE);
            text0101.setBackgroundResource(R.color.light_black);
            text0100.setBackgroundResource(R.color.light_black);
            text0102.setBackgroundResource(R.color.light_black);
            image0100.setImageResource(R.mipmap.gengduo_mine_b);
            image0102.setImageResource(R.mipmap.mine_baobao_b);
            image0101.setImageResource(R.mipmap.mine_che_b);
            image0103.setImageResource(R.mipmap.mine_shandian_b);
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
                            qc_url = userMy.getQc_url();
                            for (int i = 0; i < imageDD.length; i++) {
                                badgeDD[i].setBadgeNumber(userMy.getOrderNum().get(i)).bindTarget(imageDD[i]);
                            }
                            if (userMy.getGrade() != 0) {
                                imageFenXiangZX.setVisibility(View.VISIBLE);
                            } else {
                                imageFenXiangZX.setVisibility(View.GONE);
                            }
                            textLv.setText("VIP." + userMy.getLv());
                            textYuE.setText(userMy.getMoney());
                            textJiFen.setText(String.valueOf(userMy.getScore()));
                            textCouponNum.setText(userMy.getCouponNum() + "张");
                            vip_url = userMy.getVip_url();
                            vip_url_title = userMy.getVip_url_title();
                            if (userMy.getLv() > 0) {
                                viewDiBu.setBackgroundColor(Color.TRANSPARENT);
                                btnShengJi.setVisibility(View.GONE);
                                textScore.setText(userMy.getGrowthDes());
                                textScore.setVisibility(View.VISIBLE);
                                textChengZhangZhi.setVisibility(View.VISIBLE);
                                imageZhuanShi.setImageResource(R.mipmap.zhuanshi);
                                textFuWu.setVisibility(View.VISIBLE);
                                textShengJiFW.setVisibility(View.GONE);
                                text0101.setBackgroundColor(Color.parseColor("#656055"));
                                text0100.setBackgroundColor(Color.parseColor("#656055"));
                                text0102.setBackgroundColor(Color.parseColor("#656055"));
                                image0100.setImageResource(R.mipmap.gengduo_mine);
                                image0102.setImageResource(R.mipmap.mine_baobao);
                                image0101.setImageResource(R.mipmap.mine_che);
                                image0103.setImageResource(R.mipmap.mine_shandian);
                            } else {
                                imageZhuanShi.setImageResource(R.mipmap.zhuanshi_b);
                                textScore.setVisibility(View.GONE);
                                textChengZhangZhi.setVisibility(View.GONE);
                                viewDiBu.setBackgroundResource(R.drawable.shape_wode_dibu);
                                btnShengJi.setVisibility(View.VISIBLE);
                                textFuWu.setVisibility(View.GONE);
                                textShengJiFW.setVisibility(View.VISIBLE);
                                text0101.setBackgroundResource(R.color.light_black);
                                text0100.setBackgroundResource(R.color.light_black);
                                text0102.setBackgroundResource(R.color.light_black);
                                image0100.setImageResource(R.mipmap.gengduo_mine_b);
                                image0102.setImageResource(R.mipmap.mine_baobao_b);
                                image0101.setImageResource(R.mipmap.mine_che_b);
                                image0103.setImageResource(R.mipmap.mine_shandian_b);
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
            textId.setText("");
            textScore.setVisibility(View.GONE);
            textChengZhangZhi.setVisibility(View.GONE);
            btnShengJi.setVisibility(View.VISIBLE);
            textLv.setText("VIP.0");
            imageHead.setImageResource(R.mipmap.touxiang_mine);
            viewDiBu.setBackgroundResource(R.drawable.shape_wode_dibu);
            imageZhuanShi.setImageResource(R.mipmap.zhuanshi_b);
            textFuWu.setVisibility(View.GONE);
            textShengJiFW.setVisibility(View.VISIBLE);
            text0101.setBackgroundResource(R.color.light_black);
            text0100.setBackgroundResource(R.color.light_black);
            text0102.setBackgroundResource(R.color.light_black);
            image0100.setImageResource(R.mipmap.gengduo_mine_b);
            image0102.setImageResource(R.mipmap.mine_baobao_b);
            image0101.setImageResource(R.mipmap.mine_che_b);
            image0103.setImageResource(R.mipmap.mine_shandian_b);
        }
        setTips();
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getTipOkObject() {
        String url = Constant.HOST + Constant.Url.MASSAGE_NUM;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        return new OkObject(params, url);
    }

    /**
     * 显示消息数
     */
    private void setTips() {
        ApiClient.post(mContext, getTipOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("WoDeFragment--onSuccess", s + "");
                try {
                    MassageNum massageNum = GsonUtils.parseJSON(s, MassageNum.class);
                    if (massageNum.getStatus() == 1) {
                        badge.setBadgeNumber(massageNum.getNum()).bindTarget(imageXiaoXi);
                    } else if (massageNum.getStatus() == 3) {
                        MyDialog.showReLoginDialog(mContext);
                    } else {
                        Toast.makeText(mContext, massageNum.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(mContext, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                Toast.makeText(mContext, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.viewTuiHuanHuo:
                intent.setClass(mContext, ShouHouFWActivity.class);
                startActivity(intent);
                break;
            case R.id.btnShengJi:
                if (isLogin) {
                    intent.setClass(mContext, WebActivity.class);
                    intent.putExtra(Constant.IntentKey.TITLE, "升级");
                    intent.putExtra(Constant.IntentKey.URL, qc_url);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(mContext);
                }
                break;
            case R.id.viewDiBu:
                if (isLogin){
                    intent.setClass(mContext, WebActivity.class);
                    intent.putExtra(Constant.IntentKey.TITLE, vip_url_title);
                    intent.putExtra(Constant.IntentKey.URL, vip_url);
                    startActivity(intent);
                }else {
                    ToLoginActivity.toLoginActivity(mContext);
                }
                break;
            case R.id.viewGuanYuWM:
                intent.setClass(mContext, WebActivity.class);
                intent.putExtra(Constant.IntentKey.TITLE, "关于我们");
                intent.putExtra(Constant.IntentKey.URL, Constant.Url.ABOUT);
                startActivity(intent);
                break;
            case R.id.viewPingJiaGL:
                if (isLogin) {
                    intent.setClass(mContext, PingJiaGLActivity.class);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(mContext);
                }
                break;
            case R.id.imageXiaoXi:
                if (isLogin) {
                    intent.setClass(mContext, XiaoXiZXActivity.class);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(mContext);
                }
                break;
            case R.id.imageSheZhi:
                intent.setClass(mContext, SheZhiActivity.class);
                startActivity(intent);
                break;
            case R.id.viewUBiSC:
                if (isLogin) {
                    intent.setClass(mContext, UbiSCActivity.class);
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
                    intent.setClass(mContext, WoDeSCCZActivity.class);
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
                    startActivityForResult(intent, Constant.RequestResultCode.YOU_HUI_QUAN);
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
        filter.addAction(Constant.BroadcastCode.SHUAXINDD);
        filter.addAction(Constant.BroadcastCode.TIXIAN);
        filter.addAction(Constant.BroadcastCode.SHUA_XIN_U_BI);
        filter.addAction(Constant.BroadcastCode.SHUA_XIN_TIPS);
        filter.addAction(Constant.BroadcastCode.ZHI_FU_CG);
        getActivity().registerReceiver(reciver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(reciver);
    }

}
