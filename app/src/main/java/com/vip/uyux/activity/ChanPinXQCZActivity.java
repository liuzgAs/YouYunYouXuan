package com.vip.uyux.activity;

import android.Manifest;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vip.uyux.R;
import com.vip.uyux.adapter.ChanPinXQTP;
import com.vip.uyux.adapter.MyChuXiaoAdapter;
import com.vip.uyux.adapter.TagAdapter01;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ToLoginActivity;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.customview.FlowTagLayout;
import com.vip.uyux.customview.OnTagSelectListener;
import com.vip.uyux.customview.WrapHeightGridView;
import com.vip.uyux.model.CartAddcart;
import com.vip.uyux.model.CartNum;
import com.vip.uyux.model.GoodsInfo;
import com.vip.uyux.model.GoodsPoster;
import com.vip.uyux.model.ImgsBean;
import com.vip.uyux.model.JieSuan;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.model.ShouCangShanChu;
import com.vip.uyux.model.SimpleInfo;
import com.vip.uyux.util.ACache;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.FileUtil;
import com.vip.uyux.util.GlideApp;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.util.ScreenUtils;
import com.vip.uyux.util.StringUtil;
import com.vip.uyux.viewholder.ItemChanPinXQViewHolder;
import com.vip.uyux.viewholder.LocalImageChanPinHolderView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.CSCustomServiceInfo;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class ChanPinXQCZActivity extends ZjbBaseActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, EasyPermissions.PermissionCallbacks {

    private String did;
    private int id;
    //    private EasyRecyclerView recyclerView;

    private View viewDiBu;
    private View imageGouWuChe;
    private ImageView imageShouCang;
    private View viewGouWuChe;
    //    private RecyclerArrayAdapter<ImgsBean> adapter;
    private GoodsInfo goodsInfo;
    private List<ImgsBean> imgs;
    private List<ImgsBean> imgs2;
    private GoodsInfo.DataBean goodsInfoData;
    private View viewPingLun;
    private View linePingLun;
    private TextView textPingLunTitle;
    private TextView textPingLunDes;
    private TextView textpromotionsBeforeDes;
    private TextView textPromotionsBefore;
    private View viewChuXiao;
    private RecyclerArrayAdapter<GoodsInfo.CommentBean> adapterPingLun;
    private EasyRecyclerView recyclerViewPingLun;
    private TextView textOldPrice;
    private TextView textOldPriceJD;
    private TextView textVipDes;
    private TextView textTitle;
    private TextView textScoreDes;
    private TextView textShareMoney;
    private TextView textSaleNumStockNum;
    private TextView textPrice;
    private TextView textDes;
    private TextView textYuanJia;
    private TextView textCountdownDes;
    private TextView textCountdown;
    private WrapHeightGridView gridview;
    private ConvenientBanner banner;
    private TabLayout tablayout;
    private GoodsInfo.CommentBean comment;
    private int countdown;
    private Timer timer;
    private List<GoodsInfo.DataBean.PromotionsafterBean> promotionsAfter;
    private BroadcastReceiver reciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case Constant.BroadcastCode.SHUA_XIN_CAR:
                    gouWuChe();
                    break;
                case Constant.BroadcastCode.WX_SHARE:
                    if (isShare) {
                        MyDialog.showTipDialog(ChanPinXQCZActivity.this, "分享成功");
                        isShare = false;
                        shareHuiDiao();
                    }
                    break;
                case Constant.BroadcastCode.WX_SHARE_FAIL:
                    if (isShare) {
                        MyDialog.showTipDialog(ChanPinXQCZActivity.this, "取消分享");
                        isShare = false;
                    }
                    break;
                case Constant.BroadcastCode.ZHI_FU_CG:
                    finish();
                    break;
                default:
                    break;
            }
        }
    };
    private Badge badge;
    private boolean isShare = false;
    private List<String> goodsInfoBanner;
    private List<GoodsInfo.SkuCateBean> skuCate;
    private AlertDialog alertDialogGouWu;
    private TagAdapter01 tagAdapter;
    private List<GoodsInfo.SkuLvBean> skuLv;
    private List<List<GoodsInfo.SkuCateBean>> catelist = new ArrayList<>();
    private int stock_num;
    List<List<GoodsInfo.SkuCateBean>> listList = new ArrayList<>();
    private IWXAPI api;
    private int buy_now = 0;
    private MyGuiGeAdapter myGuiGeAdapter;
    private TextView textDialogPrice;
    private TextView textGuiGe;
    private TextView textStock_numD;
    private int sku_id;
    private ViewPager viewPager;
    private View viewError;
    private View viewTop;
    private View viewProgress;
    private View viewDaoJiShi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chan_pin_xqcz);
        api = WXAPIFactory.createWXAPI(ChanPinXQCZActivity.this, Constant.WXAPPID, true);
        init();
    }

    @Override
    protected void initSP() {
        ACache aCache = ACache.get(this, Constant.Acache.LOCATION);
        did = aCache.getAsString(Constant.Acache.DID);
    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        id = intent.getIntExtra(Constant.IntentKey.ID, 0);
    }

    @Override
    protected void findID() {
//        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        viewError = findViewById(R.id.viewError);
        viewTop = findViewById(R.id.viewTop);
        viewProgress = findViewById(R.id.viewProgress);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        imageShouCang = (ImageView) findViewById(R.id.imageShouCang);
        viewDiBu = findViewById(R.id.viewDiBu);
        imageGouWuChe = findViewById(R.id.imageGouWuChe);
        viewGouWuChe = findViewById(R.id.viewGouWuChe);
        viewDaoJiShi = findViewById(R.id.viewDaoJiShi);


        banner = (ConvenientBanner) findViewById(R.id.banner);
        int screenWidth = ScreenUtils.getScreenWidth(ChanPinXQCZActivity.this);
        ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
        layoutParams.width = screenWidth;
        layoutParams.height = screenWidth;
        banner.setLayoutParams(layoutParams);

        banner.setScrollDuration(1000);
        banner.startTurning(3000);
        gridview = (WrapHeightGridView) findViewById(R.id.gridview);
        textCountdown = (TextView) findViewById(R.id.textCountdown);
        textCountdownDes = (TextView) findViewById(R.id.textCountdownDes);
        textDes = (TextView) findViewById(R.id.textDes);
        textYuanJia = (TextView) findViewById(R.id.textYuanJia);
        textPrice = (TextView) findViewById(R.id.textPrice);
        textSaleNumStockNum = (TextView) findViewById(R.id.textSaleNumStockNum);
        textScoreDes = (TextView) findViewById(R.id.textScoreDes);
        textShareMoney = (TextView) findViewById(R.id.textShareMoney);
        textTitle = (TextView) findViewById(R.id.textTitle);
        textVipDes = (TextView) findViewById(R.id.textVipDes);
        textOldPrice = (TextView) findViewById(R.id.textOldPrice);
        textOldPriceJD = (TextView) findViewById(R.id.textOldPriceJD);
        recyclerViewPingLun = (EasyRecyclerView) findViewById(R.id.recyclerViewTop);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewChuXiao = findViewById(R.id.viewChuXiao);
        textPromotionsBefore = (TextView) findViewById(R.id.textPromotionsBefore);
        textpromotionsBeforeDes = (TextView) findViewById(R.id.textpromotionsBeforeDes);
        textPingLunDes = (TextView) findViewById(R.id.textPingLunDes);
        textPingLunTitle = (TextView) findViewById(R.id.textPingLunTitle);
        linePingLun = findViewById(R.id.linePingLun);
        viewPingLun = findViewById(R.id.viewPingLun);
    }

    @Override
    protected void initViews() {
//        initRecycler();
        viewTop.setVisibility(View.GONE);
        viewError.setVisibility(View.GONE);
        viewProgress.setVisibility(View.VISIBLE);
        initFootRecycler();
        ((TextView) findViewById(R.id.textViewTitle)).setText("产品详情");
        viewDiBu.setVisibility(View.GONE);
        badge = new QBadgeView(ChanPinXQCZActivity.this)
                .setBadgeTextColor(Color.WHITE)
                .setBadgeTextSize(10f, true)
                .setBadgeBackgroundColor(getResources().getColor(R.color.basic_color))
                .setBadgeGravity(Gravity.END | Gravity.TOP)
                .setBadgePadding(3f, true)
                .setGravityOffset(8f, 6f, true);
    }

    /**
     * 促销dialog
     */
    private void showChuXiaoDialog() {
        View dialog_tu_pian = LayoutInflater.from(this).inflate(R.layout.dialog_chuciao, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this, R.style.dialog)
                .setView(dialog_tu_pian)
                .create();
        alertDialog.show();
        TextView textTitle = dialog_tu_pian.findViewById(R.id.textTitle);
        textTitle.setText(goodsInfoData.getPromotionsBefore());
        ListView listView = dialog_tu_pian.findViewById(R.id.listView);
        final List<GoodsInfo.DataBean.PromotionsafterBean> promotionsAfter = goodsInfoData.getPromotionsAfter();
        listView.setAdapter(new MyChuXiaoAdapter(this, promotionsAfter));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GoodsInfo.DataBean.PromotionsafterBean promotionsafterBean = goodsInfoData.getPromotionsAfter().get(i);
                Intent intent = new Intent();
                intent.setClass(ChanPinXQCZActivity.this, WebActivity.class);
                intent.putExtra(Constant.IntentKey.TITLE, promotionsafterBean.getTitle());
                intent.putExtra(Constant.IntentKey.URL, promotionsafterBean.getUrl());
                startActivity(intent);
            }
        });
        dialog_tu_pian.findViewById(R.id.textQuXiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        Window dialogWindow = alertDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.dialogFenXiang);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 1); // 高度设置为屏幕的0.6
        lp.height = (int) (d.heightPixels * 0.5f); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    /**
     * 初始化recyclerview
     */
    private void initFootRecycler() {
        recyclerViewPingLun.setLayoutManager(new LinearLayoutManager(ChanPinXQCZActivity.this));
        recyclerViewPingLun.setAdapterWithProgress(adapterPingLun = new RecyclerArrayAdapter<GoodsInfo.CommentBean>(ChanPinXQCZActivity.this) {

            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_chanpin_xq;
                return new ItemChanPinXQViewHolder(parent, layout);
            }
        });
    }

//    /**
//     * 初始化recyclerview
//     */
//    private void initRecycler() {
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.line_width), 0, 0);
//        itemDecoration.setDrawLastItem(false);
//        recyclerView.addItemDecoration(itemDecoration);
//        recyclerView.setRefreshingColorResources(R.color.basic_color);
//        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<ImgsBean>(ChanPinXQCZActivity.this) {
//            @Override
//            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
//                int layout = R.layout.item_image;
//                return new ChanPinFootViewHolder(parent, layout);
//            }
//        });
//    }

    @Override
    protected void setListeners() {
        textOldPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ChanPinXQCZActivity.this, WebActivity.class);
                intent.putExtra(Constant.IntentKey.TITLE, "天猫价");
                intent.putExtra(Constant.IntentKey.URL, goodsInfoData.getTm_url());
                startActivity(intent);
            }
        });
        textOldPriceJD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ChanPinXQCZActivity.this, WebActivity.class);
                intent.putExtra(Constant.IntentKey.TITLE, "京东价");
                intent.putExtra(Constant.IntentKey.URL, goodsInfoData.getJd_url());
                startActivity(intent);
            }
        });
//        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                int position = tab.getPosition();
//                if (position == 0) {
//                    if (imgs != null) {
//                        adapter.clear();
//                        adapter.addAll(imgs);
//                    }
//                } else {
//                    if (imgs2 != null) {
//                        adapter.clear();
//                        adapter.addAll(imgs2);
//                    }
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
        viewChuXiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChuXiaoDialog();
            }
        });
        viewPingLun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ChanPinXQCZActivity.this, PingLunLBActivity.class);
                intent.putExtra(Constant.IntentKey.ID, id);
                startActivity(intent);
            }
        });
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.textLiJiGouMai).setOnClickListener(this);
        findViewById(R.id.textJiaRuGWC).setOnClickListener(this);
        findViewById(R.id.imageFenXiang).setOnClickListener(this);
        findViewById(R.id.imageKeFu).setOnClickListener(this);
        viewGouWuChe.setOnClickListener(this);
        imageShouCang.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        onRefresh();
        gouWuChe();
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.GOODS_INFO;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("id", String.valueOf(id));
        return new OkObject(params, url);
    }

    @Override
    public void onRefresh() {
        ApiClient.post(this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("产品详情", s);
                try {
                    goodsInfo = GsonUtils.parseJSON(s, GoodsInfo.class);
                    if (goodsInfo.getStatus() == 1) {
                        viewTop.setVisibility(View.VISIBLE);
                        viewError.setVisibility(View.GONE);
                        viewProgress.setVisibility(View.GONE);

                        goodsInfoBanner = goodsInfo.getBanner();
                        goodsInfoData = goodsInfo.getData();
                        countdown = goodsInfoData.getCountdown();
                        imgs = goodsInfoData.getImgs();
                        imgs2 = goodsInfoData.getImgs2();
                        viewPager.setAdapter(new ChanPinXQTP(getSupportFragmentManager(), imgs, imgs2));
                        tablayout.setupWithViewPager(viewPager);
                        tablayout.removeAllTabs();
                        for (int i = 0; i < 2; i++) {
                            View item_tablayout = LayoutInflater.from(ChanPinXQCZActivity.this).inflate(R.layout.item_tablayout, null);
                            TextView textTitle = item_tablayout.findViewById(R.id.textTitle);
                            if (i == 0) {
                                textTitle.setText("宝贝详情");
                                tablayout.addTab(tablayout.newTab().setCustomView(item_tablayout), true);
                            } else {
                                textTitle.setText("规格参数");
                                tablayout.addTab(tablayout.newTab().setCustomView(item_tablayout), false);
                            }
                        }
                        skuCate = goodsInfo.getSkuCate();
                        skuLv = goodsInfo.getSkuLv();
                        stock_num = goodsInfo.getData().getStockNum();
                        listList.clear();
                        List<List<GoodsInfo.SkuCateBean>> listList = readTree(skuCate);
                        catelist.addAll(listList);
                        viewDiBu.setVisibility(View.VISIBLE);
                        if (goodsInfo.getIsc() == 1) {
                            imageShouCang.setImageResource(R.mipmap.shoucang_xq_true);
                        } else {
                            imageShouCang.setImageResource(R.mipmap.shoucang_xq);
                        }
//                        if (imgs != null) {
//                            adapter.clear();
//                            adapter.addAll(imgs);
//                        }
                        promotionsAfter = goodsInfo.getData().getPromotionsAfter();
                        comment = goodsInfo.getComment();
                        if (comment != null) {
                            viewPingLun.setVisibility(View.VISIBLE);
                            linePingLun.setVisibility(View.VISIBLE);
                            textPingLunTitle.setText(comment.getTitle());
                            textPingLunDes.setText(comment.getDes());
                            adapterPingLun.clear();
                            adapterPingLun.add(comment);
                            adapterPingLun.notifyDataSetChanged();
                        } else {
                            viewPingLun.setVisibility(View.GONE);
                            linePingLun.setVisibility(View.GONE);
                        }
                        if (goodsInfoBanner != null) {
                            if (goodsInfoBanner.size()==1){
                                banner.setCanLoop(false);
                            }else {
                                banner.setPageIndicator(new int[]{R.drawable.shape_indicator_normal, R.drawable.shape_indicator_selected});
                            }
                            banner.setPages(new CBViewHolderCreator() {
                                @Override
                                public Object createHolder() {
                                    return new LocalImageChanPinHolderView();
                                }
                            }, goodsInfoBanner);
                        }
                        if (goodsInfoData != null) {
                            if (timer != null) {
                                timer.cancel();
                            }
                            timer = new Timer();
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (countdown >= 1) {
                                                viewDaoJiShi.setVisibility(View.VISIBLE);
                                                countdown--;
                                                textCountdown.setText(StringUtil.TimeFormat(countdown));
//                                        if (textDaoJiShi != null && viewKeGouMai != null) {
//                                            textDaoJiShi.setText("倒计时 " + StringUtil.TimeFormat(countdown));
//                                            if (countdown > 0) {
//                                                textDaoJiShi.setVisibility(View.VISIBLE);
//                                                viewKeGouMai.setVisibility(View.GONE);
//                                            } else {
//                                                textDaoJiShi.setVisibility(View.GONE);
//                                                viewKeGouMai.setVisibility(View.VISIBLE);
//                                            }
//                                        }
                                            } else {
                                                textCountdown.setText("00:00:00");
                                                timer.cancel();
                                                viewDaoJiShi.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                                }
                            }, 0, 1000);
                            textCountdownDes.setText(goodsInfoData.getCountdownDes());
                            textDes.setText(goodsInfoData.getDes());
                            if (TextUtils.isEmpty(goodsInfoData.getVipPrice())) {
                                textYuanJia.setText("");
                            } else {
                                textYuanJia.setText("¥" + goodsInfoData.getVipPrice());
                            }
                            textPrice.setText("¥" + goodsInfoData.getPrice());
                            textSaleNumStockNum.setText("已售" + goodsInfoData.getSaleNum() + "|剩" + goodsInfoData.getStockNum() + "件");
                            textScoreDes.setText(goodsInfoData.getScoreDes());
                            textShareMoney.setText(goodsInfoData.getShareMoney());
                            textTitle.setText(goodsInfoData.getTitle());
                            if (TextUtils.isEmpty(goodsInfoData.getVipDes())) {
                                textVipDes.setVisibility(View.GONE);
                            } else {
                                textVipDes.setVisibility(View.VISIBLE);
                                textVipDes.setText(goodsInfoData.getVipDes());
                            }
                            if (goodsInfoData.getIs_gotm() == 1) {
                                textOldPrice.setText("天猫价¥" + goodsInfoData.getTm_price());
                                textOldPrice.setVisibility(View.VISIBLE);
                            } else {
                                textOldPrice.setVisibility(View.GONE);
                            }
                            if (goodsInfoData.getIs_gojd() == 1) {
                                textOldPriceJD.setText("京东价¥" + goodsInfoData.getJd_price());
                                textOldPriceJD.setVisibility(View.VISIBLE);
                            } else {
                                textOldPriceJD.setVisibility(View.GONE);
                            }
                            if (promotionsAfter != null) {
                                if (promotionsAfter.size() > 0) {
                                    viewChuXiao.setVisibility(View.VISIBLE);
                                    textPromotionsBefore.setText(goodsInfoData.getPromotionsBefore());
                                    textpromotionsBeforeDes.setText(goodsInfoData.getPromotionsAfter().get(0).getTitle());
                                } else {
                                    viewChuXiao.setVisibility(View.GONE);
                                }
                            } else {
                                viewChuXiao.setVisibility(View.GONE);
                            }
                            if (goodsInfoData.getServeiceDes() != null) {
                                if (goodsInfoData.getServeiceDes().size() > 0) {
                                    gridview.setVisibility(View.VISIBLE);
                                    gridview.setAdapter(new MyAdapter());
                                } else {
                                    gridview.setVisibility(View.GONE);
                                }
                            } else {
                                gridview.setVisibility(View.GONE);
                            }
                        }
                        LogUtil.LogShitou("ChanPinXQCZActivity--onSuccess", "" + GsonUtils.parseObject(catelist));
                        if (comment != null) {
                            viewPingLun.setVisibility(View.VISIBLE);
                            linePingLun.setVisibility(View.VISIBLE);
                            textPingLunTitle.setText(comment.getTitle());
                            textPingLunDes.setText(comment.getDes());
                            adapterPingLun.clear();
                            adapterPingLun.add(comment);
                            adapterPingLun.notifyDataSetChanged();
                        } else {
                            viewPingLun.setVisibility(View.GONE);
                            linePingLun.setVisibility(View.GONE);
                        }
                    } else if (goodsInfo.getStatus() == 3) {
                        MyDialog.showReLoginDialog(ChanPinXQCZActivity.this);
                    } else {
                        showError(goodsInfo.getInfo());
                    }
                } catch (Exception e) {
                    showError("数据出错");
                }
            }

            @Override
            public void onError() {
                showError("网络出错");
            }

            /**
             * 错误显示
             * @param msg
             */
            private void showError(String msg) {
                try {

                    viewTop.setVisibility(View.GONE);
                    viewError.setVisibility(View.VISIBLE);
                    viewProgress.setVisibility(View.GONE);

                    TextView textMsg = viewError.findViewById(R.id.textMsg);
                    textMsg.setText(msg);
                    viewError.findViewById(R.id.buttonReLoad).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            recyclerView.showProgress();
                            initData();
                        }
                    });
                } catch (Exception e) {
                }
            }
        });
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getCarOkObject() {
        String url = Constant.HOST + Constant.Url.CART_NUM;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("did", did);
        return new OkObject(params, url);
    }

    private void gouWuChe() {
        ApiClient.post(ChanPinXQCZActivity.this, getCarOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("MainActivity--onSuccess", s + "");
                try {
                    CartNum cartNum = GsonUtils.parseJSON(s, CartNum.class);
                    if (cartNum.getStatus() == 1) {
                        if (cartNum.getNum() > 0) {
                            badge.setBadgeText("")
                                    .bindTarget(imageGouWuChe);
                        } else {
                            badge.hide(true);
                        }
                    } else if (cartNum.getStatus() == 3) {
                        MyDialog.showReLoginDialog(ChanPinXQCZActivity.this);
                    } else {
                        Toast.makeText(ChanPinXQCZActivity.this, cartNum.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(ChanPinXQCZActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                Toast.makeText(ChanPinXQCZActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getHDOkObject() {
        String url = Constant.HOST + Constant.Url.SHARE_SHAREAFTER;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("shareType", String.valueOf(0));
        params.put("source", Constant.source);
        params.put("shareTitle", goodsInfo.getData().getShare().getShareTitle());
        params.put("shareImg", goodsInfo.getData().getShare().getShareImg());
        params.put("shareDes", goodsInfo.getData().getShare().getShareDes());
        params.put("url", goodsInfo.getData().getShare().getShareUrl());
        params.put("id", String.valueOf(id));
        return new OkObject(params, url);
    }

    /**
     * 分享回调
     */
    private void shareHuiDiao() {
        showLoadingDialog();
        ApiClient.post(ChanPinXQCZActivity.this, getHDOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ChanPinXQCZActivity--onSuccess", s + "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus() == 1) {
                        LogUtil.LogShitou("ChanPinXQCZActivity--onSuccess", "回调成功");
                    } else if (simpleInfo.getStatus() == 3) {
                        MyDialog.showReLoginDialog(ChanPinXQCZActivity.this);
                    } else {
                        Toast.makeText(ChanPinXQCZActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(ChanPinXQCZActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(ChanPinXQCZActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //递归方法
    List<List<GoodsInfo.SkuCateBean>> readTree(List<GoodsInfo.SkuCateBean> skuCate) {
        if (skuCate.size() > 0) {
            for (int i = 0; i < skuCate.size(); i++) {
                skuCate.get(i).setSelect(false);
            }
            skuCate.get(0).setSelect(true);
            listList.add(skuCate);
            if (skuCate.get(0).getValue().size() > 0) {
                readTree(skuCate.get(0).getValue());
            }
        }
        return listList;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageKeFu:
                if (isLogin) {
                    rongYun();
                } else {
                    ToLoginActivity.toLoginActivity(ChanPinXQCZActivity.this);
                }
                break;
            case R.id.viewGouWuChe:
                Intent intent = new Intent();
                intent.setClass(this, GouWuCheActivity.class);
                startActivity(intent);
                break;
            case R.id.imageFenXiang:
                isShare = true;
                MyDialog.share(this, "ChanPinXQCZActivity", api, String.valueOf(id), goodsInfo.getData().getShare());
                break;
            case R.id.imageShouCang:
                if (isLogin) {
                    if (goodsInfo.getIsc() == 0) {
                        shouCang();
                    } else {
                        quXiaoSC();
                    }
                } else {
                    ToLoginActivity.toLoginActivity(this);
                }
                break;
            case R.id.textLiJiGouMai:
                if (isLogin) {
                    buy_now = 1;
                    mai();
                } else {
                    ToLoginActivity.toLoginActivity(this);
                }
                break;
            case R.id.textJiaRuGWC:
                buy_now = 0;
                mai();
                break;
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
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
     * 融云
     */
    private void rongYun() {
        LogUtil.LogShitou("ChanPinXQCZActivity--rongYun", "cao");
        if (userInfo==null){
            return;
        }
        if (getApplicationInfo().packageName.equals(getCurProcessName(ChanPinXQCZActivity.this))) {
            LogUtil.LogShitou("ChanPinXQCZActivity--getYunToken", userInfo.getYunToken());
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
                    LogUtil.LogShitou("CheLiangXQActivity--onSuccess", "userid" + userid);
                    final io.rong.imlib.model.UserInfo userInfoRongYun = new io.rong.imlib.model.UserInfo(userInfo.getUid(), userInfo.getUserName(), Uri.parse(userInfo.getHeadImg()));
//                RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
//                    @Override
//                    public UserInfo getUserInfo(String s) {
//                        return userInfoRongYun;
//                    }
//                },true);
//                RongIM.getInstance().refreshUserInfoCache(userInfoRongYun);
                    /**
                     * 设置当前用户信息，
                     * @param userInfo 当前用户信息
                     */
                    RongIM.getInstance().setCurrentUserInfo(userInfoRongYun);
                    /**
                     * 设置消息体内是否携带用户信息。
                     * @param state 是否携带用户信息，true 携带，false 不携带。
                     */
                    RongIM.getInstance().setMessageAttachedUserInfo(true);
                    //首先需要构造使用客服者的用户信息
                    CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
                    CSCustomServiceInfo csInfo = csBuilder.nickName("优云优选").build();

                    /**
                     * 启动客户服聊天界面。
                     *
                     * @param context           应用上下文。
                     * @param customerServiceId 要与之聊天的客服 Id。
                     * @param title             聊天的标题，如果传入空值，则默认显示与之聊天的客服名称。
                     * @param customServiceInfo 当前使用客服者的用户信息。{@link io.rong.imlib.model.CSCustomServiceInfo}
                     */
                    RongIM.getInstance().startCustomerServiceChat(ChanPinXQCZActivity.this, "KEFU152119192578109", "在线客服", csInfo);
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

        }else {
            LogUtil.LogShitou("ChanPinXQCZActivity--rongYun", "111111111111");
        }
//        final io.rong.imlib.model.UserInfo userInfoRongYun = new io.rong.imlib.model.UserInfo(userInfo.getUid(), userInfo.getUserName(), Uri.parse(userInfo.getHeadImg()));
//        /**
//         * 设置当前用户信息，
//         * @param userInfo 当前用户信息
//         */
//        RongIM.getInstance().setCurrentUserInfo(userInfoRongYun);
//        /**
//         * 设置消息体内是否携带用户信息。
//         * @param state 是否携带用户信息，true 携带，false 不携带。
//         */
//        RongIM.getInstance().setMessageAttachedUserInfo(true);
//        //首先需要构造使用客服者的用户信息
//        CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
//        CSCustomServiceInfo csInfo = csBuilder.nickName("融云").build();
//
//        /**
//         * 启动客户服聊天界面。
//         *
//         * @param context           应用上下文。
//         * @param customerServiceId 要与之聊天的客服 Id。
//         * @param title             聊天的标题，如果传入空值，则默认显示与之聊天的客服名称。
//         * @param customServiceInfo 当前使用客服者的用户信息。{@link io.rong.imlib.model.CSCustomServiceInfo}
//         */
//        RongIM.getInstance().startCustomerServiceChat(ChanPinXQCZActivity.this, userInfo.getUid(), "在线客服", csInfo);
    }

    /**
     * 取消收藏
     */
    private void quXiaoSC() {
        String url = Constant.HOST + Constant.Url.GOODS_CANCLECOLLECT;
        ShouCangShanChu shouCangShanChu = new ShouCangShanChu(1, "android", userInfo.getUid(), tokenTime, id, 1);
        showLoadingDialog();
        ApiClient.postJson(ChanPinXQCZActivity.this, url, GsonUtils.parseObject(shouCangShanChu), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ChanPinXQCZActivity--onSuccess", s + "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus() == 1) {
                        Intent intent = new Intent();
                        intent.setAction(Constant.BroadcastCode.SHUA_XIN_SHOU_CANG);
                        sendBroadcast(intent);
                        Toast.makeText(ChanPinXQCZActivity.this, "取消收藏", Toast.LENGTH_SHORT).show();
                        goodsInfo.setIsc(0);
                        imageShouCang.setImageResource(R.mipmap.shoucang_xq);
                    } else if (simpleInfo.getStatus() == 3) {
                        MyDialog.showReLoginDialog(ChanPinXQCZActivity.this);
                    } else {
                        Toast.makeText(ChanPinXQCZActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(ChanPinXQCZActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(ChanPinXQCZActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getSCOkObject() {
        String url = Constant.HOST + Constant.Url.GOODS_COLLECT;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("item_id", String.valueOf(id));
        params.put("type", String.valueOf(1));
        return new OkObject(params, url);
    }

    /**
     * 收藏
     */
    private void shouCang() {
        showLoadingDialog();
        ApiClient.post(ChanPinXQCZActivity.this, getSCOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ChanPinXQCZActivity--onSuccess", s + "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus() == 1) {
                        Toast.makeText(ChanPinXQCZActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                        goodsInfo.setIsc(1);
                        imageShouCang.setImageResource(R.mipmap.shoucang_xq_true);
                        Intent intent = new Intent();
                        intent.setAction(Constant.BroadcastCode.SHUA_XIN_SHOU_CANG);
                        sendBroadcast(intent);
                    } else if (simpleInfo.getStatus() == 3) {
                        MyDialog.showReLoginDialog(ChanPinXQCZActivity.this);
                    } else {
                        Toast.makeText(ChanPinXQCZActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(ChanPinXQCZActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(ChanPinXQCZActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * des： 加入购物车弹窗
     * author： ZhangJieBo
     * date： 2017/9/26 0026 下午 4:04
     */

    private void mai() {
        LayoutInflater inflater = LayoutInflater.from(ChanPinXQCZActivity.this);
        View dialog_chan_pin = inflater.inflate(R.layout.dialog_chan_pin, null);
        alertDialogGouWu = new AlertDialog.Builder(ChanPinXQCZActivity.this, R.style.dialog)
                .setView(dialog_chan_pin)
                .create();
        alertDialogGouWu.show();
        ListView listView = dialog_chan_pin.findViewById(R.id.listView);
        myGuiGeAdapter = new ChanPinXQCZActivity.MyGuiGeAdapter();
        listView.setAdapter(myGuiGeAdapter);
        dialog_chan_pin.findViewById(R.id.imageCancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogGouWu.dismiss();
            }
        });
        ImageView imageImg = dialog_chan_pin.findViewById(R.id.imageImg);
        GlideApp.with(ChanPinXQCZActivity.this)
                .asBitmap()
                .centerCrop()
                .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(10, ChanPinXQCZActivity.this)))
                .load(goodsInfoData.getThumb())
                .into(imageImg);
        textDialogPrice = dialog_chan_pin.findViewById(R.id.textDialogPrice);
        textDialogPrice.setText("¥" + goodsInfoData.getPrice());
        textGuiGe = dialog_chan_pin.findViewById(R.id.textGuiGe);
        textStock_numD = dialog_chan_pin.findViewById(R.id.textStock_numD);
        textStock_numD.setText("库存" + stock_num + "件");
        ImageView imageAdd = dialog_chan_pin.findViewById(R.id.imageAdd);
        final ImageView imageDelete = dialog_chan_pin.findViewById(R.id.imageDelete);
        final EditText editNum = dialog_chan_pin.findViewById(R.id.editNum);
        imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editNum.getText().toString().trim())) {
                    editNum.setText("1");
                    editNum.setSelection(1);
                } else {
                    int goodsNum = Integer.parseInt(editNum.getText().toString().trim());
                    if (goodsNum < stock_num) {
                        editNum.setText((goodsNum + 1) + "");
                        editNum.setSelection(((goodsNum + 1) + "").length());
                    } else {
                        Toast.makeText(ChanPinXQCZActivity.this, "库存不足", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editNum.getText().toString().trim())) {

                } else {
                    int goodsNum = Integer.parseInt(editNum.getText().toString().trim());
                    if (goodsNum > 1) {
                        editNum.setText((goodsNum - 1) + "");
                        editNum.setSelection(((goodsNum - 1) + "").length());
                    }
                }
            }
        });
//        editNum.setFilters(new InputFilter[]{new InputFilter() {
//            @Override
//            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//                if ((source.equals("0") && dest.toString().length() == 0)) {
//                    return "1";
//                }
//                return null;
//            }
//        }});
//        editNum.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                if (TextUtils.isEmpty(editable.toString())) {
//                    editNum.setText("1");
//                    editNum.setSelection(1);
//                }
////                data.setNum(Integer.parseInt(editNum.getText().toString().trim()));
////                Double price = Arith.mul((double) data.getNum(), Double.parseDouble(data.getGoods_price()));
////                ((QueRenDDActivity) getContext()).textSum.setText("¥" + price);
//            }
//        });
        dialog_chan_pin.findViewById(R.id.buttonSure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editNum.getText().toString().trim())) {
                    Toast.makeText(ChanPinXQCZActivity.this, "商品数量必须大于等于一", Toast.LENGTH_SHORT).show();
                    return;
                }
                int goodsNum = Integer.parseInt(editNum.getText().toString().trim());
                if (goodsNum <= stock_num) {
                } else {
                    Toast.makeText(ChanPinXQCZActivity.this, "库存不足", Toast.LENGTH_SHORT).show();
                    return;
                }
                alertDialogGouWu.dismiss();
                addCar(editNum.getText().toString().trim());
            }
        });


        Window dialogWindow = alertDialogGouWu.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.dialogFenXiang);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = ChanPinXQCZActivity.this.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 1); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getCarOkObject(String num) {
        String url = Constant.HOST + Constant.Url.CART_ADDCART;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("goods_id", String.valueOf(id));
        params.put("sku_id", String.valueOf(sku_id));
        params.put("buy_now", String.valueOf(buy_now));
        params.put("did", String.valueOf(did));
        params.put("num", num);
        return new OkObject(params, url);
    }

    /**
     * 添加到购物车
     */
    private void addCar(String num) {
        showLoadingDialog();
        ApiClient.post(ChanPinXQCZActivity.this, getCarOkObject(num), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ChanPinXQCZActivity--加入购物车", s + "");
                try {
                    CartAddcart cartAddcart = GsonUtils.parseJSON(s, CartAddcart.class);
                    if (cartAddcart.getStatus() == 1) {
                        if (buy_now == 1) {
                            List<Integer> integerList = new ArrayList<>();
                            integerList.add(cartAddcart.getCartId());
                            Intent intent = new Intent();
                            intent.putExtra(Constant.IntentKey.BEAN, new JieSuan(integerList));
                            intent.setClass(ChanPinXQCZActivity.this, QueRenDDActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(ChanPinXQCZActivity.this, "加入购物车成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setAction(Constant.BroadcastCode.SHUA_XIN_CAR);
                            sendBroadcast(intent);
                        }
                    } else if (cartAddcart.getStatus() == 3) {
                        MyDialog.showReLoginDialog(ChanPinXQCZActivity.this);
                    } else {
                        Toast.makeText(ChanPinXQCZActivity.this, cartAddcart.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(ChanPinXQCZActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(ChanPinXQCZActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static final int SOREAGE = 1991;

    @AfterPermissionGranted(SOREAGE)
    public void methodRequiresTwoPermission() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Already have permission, do the thing
            haiBao();
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "需要开启定位权限",
                    SOREAGE, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            haiBao();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        } else {
            methodRequiresTwoPermission();
        }
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getHaiBaoOkObject() {
        String url = Constant.HOST + Constant.Url.GOODS_POSTER;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("id", String.valueOf(id));
        return new OkObject(params, url);
    }

    /**
     * 多图分享朋友圈
     */
    public void haiBao() {
        showLoadingDialog();
        ApiClient.post(ChanPinXQCZActivity.this, getHaiBaoOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("MyDialog--onSuccess", s + "");
                try {
                    final GoodsPoster goodsPoster = GsonUtils.parseJSON(s, GoodsPoster.class);
                    if (goodsPoster.getStatus() == 1) {
                        GlideApp.with(ChanPinXQCZActivity.this)
                                .asBitmap()
                                .load(goodsPoster.getImg())
                                .placeholder(R.mipmap.ic_empty)
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(final Bitmap resource, Transition<? super Bitmap> transition) {
                                        View dialog_fen_xiang_erm = LayoutInflater.from(ChanPinXQCZActivity.this).inflate(R.layout.dialog_fen_xiang_erm, null);
                                        final ImageView imageImg = (ImageView) dialog_fen_xiang_erm.findViewById(R.id.imageImg);
                                        ViewGroup.LayoutParams layoutParams = imageImg.getLayoutParams();
                                        layoutParams.height = (int) ((ScreenUtils.getScreenWidth(ChanPinXQCZActivity.this) - DpUtils.convertDpToPixel(20, ChanPinXQCZActivity.this)) * (734f / 496f));
                                        imageImg.setLayoutParams(layoutParams);

                                        imageImg.setImageBitmap(resource);
                                        final AlertDialog alertDialog = new AlertDialog.Builder(ChanPinXQCZActivity.this, R.style.dialog)
                                                .setView(dialog_fen_xiang_erm)
                                                .create();
                                        dialog_fen_xiang_erm.findViewById(R.id.textWeiXin).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                alertDialog.dismiss();
                                                MyDialog.shareImg(api, resource, 0);
                                            }


                                        });
                                        dialog_fen_xiang_erm.findViewById(R.id.textPengYouQuan).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                alertDialog.dismiss();
                                                MyDialog.shareImg(api, resource, 1);
                                            }
                                        });
                                        alertDialog.show();
                                        Window dialogWindow = alertDialog.getWindow();
                                        dialogWindow.setGravity(Gravity.CENTER);
                                        dialogWindow.setWindowAnimations(R.style.AnimFromTopToButtom);
                                        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                                        DisplayMetrics d = getResources().getDisplayMetrics(); // 获取屏幕宽、高用
                                        lp.width = (int) (d.widthPixels - DpUtils.convertDpToPixel(20, ChanPinXQCZActivity.this)); // 高度设置为屏幕的0.6
                                        dialogWindow.setAttributes(lp);
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    FileUtil.saveMyBitmap(ChanPinXQCZActivity.this, "优云优选海报" + System.currentTimeMillis(), resource);
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            LogUtil.LogShitou("MyDialog--run", "海报保存在\"/优云优选/\"目录下");
                                                            Toast.makeText(ChanPinXQCZActivity.this, "海报保存在\"/优云优选/\"目录下", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                    LogUtil.LogShitou("MyDialog--run", "保存失败");
                                                }
                                            }
                                        }).start();
                                    }
                                });

                    } else if (goodsPoster.getStatus() == 3) {
                        MyDialog.showReLoginDialog(ChanPinXQCZActivity.this);
                    } else {
                        Toast.makeText(ChanPinXQCZActivity.this, goodsPoster.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(ChanPinXQCZActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(ChanPinXQCZActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class MyAdapter extends BaseAdapter {
        class ViewHolder {
            public TextView title;
        }

        @Override
        public int getCount() {
            return goodsInfoData.getServeiceDes().size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(ChanPinXQCZActivity.this).inflate(R.layout.item_fuwu, null);
                holder.title = (TextView) convertView.findViewById(R.id.textFuWu);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.title.setText(goodsInfoData.getServeiceDes().get(position));
            return convertView;
        }
    }

    class MyGuiGeAdapter extends BaseAdapter {
        class ViewHolder {
            public TextView title;
            private FlowTagLayout flowTagLayout;
        }

        @Override
        public int getCount() {
            return catelist.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            MyGuiGeAdapter.ViewHolder holder;
            if (convertView == null) {
                holder = new MyGuiGeAdapter.ViewHolder();
                convertView = LayoutInflater.from(ChanPinXQCZActivity.this).inflate(R.layout.item_guige, null);
                holder.title = (TextView) convertView.findViewById(R.id.textTitle);
                holder.flowTagLayout = convertView.findViewById(R.id.flowTagLayout);
                convertView.setTag(holder);
            } else {
                holder = (MyGuiGeAdapter.ViewHolder) convertView.getTag();
            }
            final List<GoodsInfo.SkuCateBean> skuCateBeans = catelist.get(position);
            holder.title.setText(skuLv.get(position).getName());
            holder.flowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE_FALSE);
            tagAdapter = new TagAdapter01(ChanPinXQCZActivity.this);
            holder.flowTagLayout.setAdapter(tagAdapter);
            tagAdapter.clearAndAddAll(skuCateBeans);
            for (int i = 0; i < skuCateBeans.size(); i++) {
                if (skuCateBeans.get(i).isSelect()) {
                    holder.flowTagLayout.setSelect(i);
                    break;
                }
            }
            shuaXinDialog();
            holder.flowTagLayout.setOnTagSelectListener(new OnTagSelectListener() {
                @Override
                public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                    Integer integer = selectedList.get(0);
                    LogUtil.LogShitou("MyGuiGeAdapter--onItemSelect", "" + integer);
                    for (int i = 0; i < skuCateBeans.size(); i++) {
                        skuCateBeans.get(i).setSelect(false);
                    }
                    skuCateBeans.get(integer).setSelect(true);
                    List<GoodsInfo.SkuCateBean> cateBeanList = skuCateBeans.get(integer).getValue();
                    List<List<GoodsInfo.SkuCateBean>> listList1 = new ArrayList<>();
                    for (int i = 0; i < position + 1; i++) {
                        listList1.add(catelist.get(i));
                    }
                    listList.clear();
                    List<List<GoodsInfo.SkuCateBean>> listList = readTree(cateBeanList);
                    if (listList.size() > 0) {
                        listList1.addAll(listList);
                    }
                    catelist.clear();
                    catelist.addAll(listList1);
                    myGuiGeAdapter.notifyDataSetChanged();
                    shuaXinDialog();
                }


            });
            return convertView;
        }

        private void shuaXinDialog() {
            List<GoodsInfo.SkuCateBean> skuCateBeans1 = catelist.get(catelist.size() - 1);
            for (int i = 0; i < skuCateBeans1.size(); i++) {
                GoodsInfo.SkuCateBean skuCateBean = skuCateBeans1.get(i);
                if (skuCateBean.isSelect()) {
                    textDialogPrice.setText("¥" + skuCateBean.getPrice());
                    sku_id = skuCateBean.getSku_id();
                }

            }
            String name = "";
            for (int i = 0; i < catelist.size(); i++) {
                for (int j = 0; j < catelist.get(i).size(); j++) {
                    if (catelist.get(i).get(j).isSelect()) {
                        name = name + catelist.get(i).get(j).getName() + " ";
                        stock_num = catelist.get(i).get(j).getStock_num();
                    }
                }
            }
            textGuiGe.setText(name);
            textStock_numD.setText("库存" + stock_num + "件");
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.BroadcastCode.SHUA_XIN_CAR);
        filter.addAction(Constant.BroadcastCode.WX_SHARE);
        filter.addAction(Constant.BroadcastCode.WX_SHARE_FAIL);
        filter.addAction(Constant.BroadcastCode.ZHI_FU_CG);
        registerReceiver(reciver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
        unregisterReceiver(reciver);
    }
}
