package com.vip.uyux.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.vip.uyux.R;
import com.vip.uyux.adapter.TagAdapter01;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.customview.FlowTagLayout;
import com.vip.uyux.customview.WrapHeightGridView;
import com.vip.uyux.model.GoodsInfo;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.provider.DataProvider;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.util.StringUtil;
import com.vip.uyux.viewholder.ChanPinFootViewHolder;
import com.vip.uyux.viewholder.ItemChanPinXQViewHolder;
import com.vip.uyux.viewholder.LocalImageChanPinHolderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ChanPinXQActivity extends ZjbBaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Integer> adapter;
    private List<String> stringList = new ArrayList<>();
    private List<String> goodsInfoBanner;
    private int id;
    private GoodsInfo.DataBean goodsInfoData;
    private int countdown;
    private Timer timer;
    private List<GoodsInfo.DataBean.ImgsBean> imgs;
    private List<GoodsInfo.DataBean.ImgsBean> imgs2;
    private List<GoodsInfo.SkuCateBean> skuCate;
    private AlertDialog alertDialogGouWu;
    private TagAdapter01 tagAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chan_pin_xq);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        id = intent.getIntExtra(Constant.IntentKey.ID, 0);
    }

    @Override
    protected void findID() {
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("产品详情");
        initRecycler();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.line_width), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Integer>(ChanPinXQActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_chanpin_xq;
                return new ItemChanPinXQViewHolder(parent, layout);
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {

            private TextView textOldPrice;
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

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(ChanPinXQActivity.this).inflate(R.layout.header_chenpin, null);
                banner = (ConvenientBanner) view.findViewById(R.id.banner);
                banner.setScrollDuration(1000);
                banner.startTurning(3000);
                gridview = view.findViewById(R.id.gridview);
                stringList.add("30天无忧退换货");
                stringList.add("48小时快速退款");
                stringList.add("满88元免邮费");
                stringList.add("优云优选自营品牌");
                gridview.setAdapter(new MyAdapter());
                textCountdown = view.findViewById(R.id.textCountdown);
                textCountdownDes = view.findViewById(R.id.textCountdownDes);
                textDes = view.findViewById(R.id.textDes);
                textYuanJia = view.findViewById(R.id.textYuanJia);
                textPrice = view.findViewById(R.id.textPrice);
                textSaleNumStockNum = view.findViewById(R.id.textSaleNumStockNum);
                textScoreDes = view.findViewById(R.id.textScoreDes);
                textShareMoney = view.findViewById(R.id.textShareMoney);
                textTitle = view.findViewById(R.id.textTitle);
                textVipDes = view.findViewById(R.id.textVipDes);
                textOldPrice = view.findViewById(R.id.textOldPrice);
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (goodsInfoBanner != null) {
                    banner.setPages(new CBViewHolderCreator() {
                        @Override
                        public Object createHolder() {
                            return new LocalImageChanPinHolderView();
                        }
                    }, goodsInfoBanner);
                    banner.setPageIndicator(new int[]{R.drawable.shape_indicator_normal, R.drawable.shape_indicator_selected});
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
                                    }
                                }
                            });
                        }
                    }, 0, 1000);
                    textCountdownDes.setText(goodsInfoData.getCountdownDes());
                    textDes.setText(goodsInfoData.getDes());
                    textYuanJia.setText("¥" + goodsInfoData.getVipPrice());
                    textPrice.setText("¥" + goodsInfoData.getPrice());
                    textSaleNumStockNum.setText("已售" + goodsInfoData.getSaleNum() + "|剩" + goodsInfoData.getStockNum() + "件");
                    textScoreDes.setText(goodsInfoData.getScoreDes());
                    textShareMoney.setText(goodsInfoData.getShareMoney());
                    textTitle.setText(goodsInfoData.getTitle());
                    textVipDes.setText(goodsInfoData.getVipDes());
                    textOldPrice.setText("天猫价¥" + goodsInfoData.getOldPrice());
                }
            }
        });
        adapter.addFooter(new RecyclerArrayAdapter.ItemView() {
            private RecyclerArrayAdapter<GoodsInfo.DataBean.ImgsBean> adapterFoot;
            private EasyRecyclerView recyclerViewFoot;
            private RecyclerArrayAdapter<GoodsInfo.DataBean.ImgsBean> adapterFoot1;
            private EasyRecyclerView recyclerViewFoot1;
            private TabLayout tablayout;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(ChanPinXQActivity.this).inflate(R.layout.footer_chanpin_xq, null);
                tablayout = (TabLayout) view.findViewById(R.id.tablayout);
                for (int i = 0; i < 2; i++) {
                    View item_tablayout = LayoutInflater.from(ChanPinXQActivity.this).inflate(R.layout.item_tablayout, null);
                    TextView textTitle = (TextView) item_tablayout.findViewById(R.id.textTitle);
                    if (i == 0) {
                        textTitle.setText("宝贝详情");
                        tablayout.addTab(tablayout.newTab().setCustomView(item_tablayout), true);
                    } else {
                        textTitle.setText("规格参数");
                        tablayout.addTab(tablayout.newTab().setCustomView(item_tablayout), false);
                    }
                }
                tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        int position = tab.getPosition();
                        if (position == 0) {
                            recyclerViewFoot.setVisibility(View.VISIBLE);
                            recyclerViewFoot1.setVisibility(View.GONE);
                        } else {
                            recyclerViewFoot.setVisibility(View.GONE);
                            recyclerViewFoot1.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });
                recyclerViewFoot = view.findViewById(R.id.recyclerView);
                recyclerViewFoot1 = view.findViewById(R.id.recyclerView1);
                initFootRecycler();
                initFootRecycler1();
                return view;
            }

            /**
             * 初始化recyclerview
             */
            private void initFootRecycler() {
                recyclerViewFoot.setLayoutManager(new LinearLayoutManager(ChanPinXQActivity.this));
                recyclerViewFoot.setAdapterWithProgress(adapterFoot = new RecyclerArrayAdapter<GoodsInfo.DataBean.ImgsBean>(ChanPinXQActivity.this) {
                    @Override
                    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                        int layout = R.layout.item_image;
                        return new ChanPinFootViewHolder(parent, layout);
                    }
                });
                adapterFoot.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                    }
                });
            }

            /**
             * 初始化recyclerview
             */
            private void initFootRecycler1() {
                recyclerViewFoot1.setLayoutManager(new LinearLayoutManager(ChanPinXQActivity.this));
                recyclerViewFoot1.setAdapterWithProgress(adapterFoot1 = new RecyclerArrayAdapter<GoodsInfo.DataBean.ImgsBean>(ChanPinXQActivity.this) {
                    @Override
                    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                        int layout = R.layout.item_image;
                        return new ChanPinFootViewHolder(parent, layout);
                    }
                });
                adapterFoot1.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                    }
                });
            }

            @Override
            public void onBindView(View headerView) {
                recyclerViewFoot.setVisibility(View.VISIBLE);
                recyclerViewFoot1.setVisibility(View.GONE);
                adapterFoot.clear();
                adapterFoot1.clear();
                if (imgs != null) {
                    adapterFoot.addAll(imgs);
                }
                if (imgs2 != null) {
                    adapterFoot1.addAll(imgs2);
                }
            }
        });
        recyclerView.setRefreshListener(this);
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.textLiJiGouMai).setOnClickListener(this);
        findViewById(R.id.textJiaRuGWC).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textLiJiGouMai:
                mai();
                break;
            case R.id.textJiaRuGWC:
                mai();
                break;
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
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
                    GoodsInfo goodsInfo = GsonUtils.parseJSON(s, GoodsInfo.class);
                    if (goodsInfo.getStatus() == 1) {
                        goodsInfoBanner = goodsInfo.getBanner();
                        goodsInfoData = goodsInfo.getData();
                        countdown = goodsInfoData.getCountdown();
                        imgs = goodsInfoData.getImgs();
                        imgs2 = goodsInfoData.getImgs2();
                        skuCate = goodsInfo.getSkuCate();
                        for (int i = 0; i < skuCate.size(); i++) {
                            List<GoodsInfo.SkuCateBean.ValueBean> valueBeanList = skuCate.get(i).getValue();
                            for (int j = 0; j < valueBeanList.size(); j++) {
                                valueBeanList.get(j).setSelect(false);
                            }
                            valueBeanList.get(0).setSelect(true);
                        }
                        adapter.clear();
                        adapter.addAll(DataProvider.getPersonList(1));
                    } else if (goodsInfo.getStatus() == 3) {
                        MyDialog.showReLoginDialog(ChanPinXQActivity.this);
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
                    View viewLoader = LayoutInflater.from(ChanPinXQActivity.this).inflate(R.layout.view_loaderror, null);
                    TextView textMsg = viewLoader.findViewById(R.id.textMsg);
                    textMsg.setText(msg);
                    viewLoader.findViewById(R.id.buttonReLoad).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            recyclerView.showProgress();
                            initData();
                        }
                    });
                    recyclerView.setErrorView(viewLoader);
                    recyclerView.showError();
                } catch (Exception e) {
                }
            }
        });

    }

    class MyAdapter extends BaseAdapter {
        class ViewHolder {
            public TextView title;
        }

        @Override
        public int getCount() {
            return stringList.size();
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
                convertView = LayoutInflater.from(ChanPinXQActivity.this).inflate(R.layout.item_fuwu, null);
                holder.title = (TextView) convertView.findViewById(R.id.textFuWu);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.title.setText(stringList.get(position));
            return convertView;
        }
    }

    /**
     * des： 加入购物车弹窗
     * author： ZhangJieBo
     * date： 2017/9/26 0026 下午 4:04
     */

    private void mai() {
        LayoutInflater inflater = LayoutInflater.from(ChanPinXQActivity.this);
        View dialog_chan_pin = inflater.inflate(R.layout.dialog_chan_pin, null);
        alertDialogGouWu = new AlertDialog.Builder(ChanPinXQActivity.this, R.style.dialog)
                .setView(dialog_chan_pin)
                .create();
        alertDialogGouWu.show();
        ListView listView = dialog_chan_pin.findViewById(R.id.listView);
        listView.setAdapter(new MyGuiGeAdapter());
        Window dialogWindow = alertDialogGouWu.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.dialogFenXiang);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = ChanPinXQActivity.this.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 1); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    class MyGuiGeAdapter extends BaseAdapter {
        class ViewHolder {
            public TextView title;
            private FlowTagLayout flowTagLayout;
        }

        @Override
        public int getCount() {
            return skuCate.size();
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
                convertView = LayoutInflater.from(ChanPinXQActivity.this).inflate(R.layout.item_guige, null);
                holder.title = (TextView) convertView.findViewById(R.id.textTitle);
                holder.flowTagLayout = convertView.findViewById(R.id.flowTagLayout);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            GoodsInfo.SkuCateBean skuCateBean = skuCate.get(position);
            holder.title.setText(skuCateBean.getName());
            holder.flowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
            tagAdapter = new TagAdapter01(ChanPinXQActivity.this);
            holder.flowTagLayout.setAdapter(tagAdapter);
            List<GoodsInfo.SkuCateBean.ValueBean> valueBeanList = skuCateBean.getValue();
            tagAdapter.clearAndAddAll(valueBeanList);
            for (int i = 0; i < valueBeanList.size(); i++) {
                if (valueBeanList.get(i).isSelect()){
                    holder.flowTagLayout.setSelect(i);
                    break;
                }
            }
            return convertView;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}
