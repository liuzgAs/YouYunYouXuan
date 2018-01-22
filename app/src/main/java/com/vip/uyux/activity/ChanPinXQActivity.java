package com.vip.uyux.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.customview.WrapHeightGridView;
import com.vip.uyux.model.GoodsInfo;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.provider.DataProvider;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.viewholder.ChanPinFootViewHolder;
import com.vip.uyux.viewholder.ItemChanPinXQViewHolder;
import com.vip.uyux.viewholder.LocalImageChanPinHolderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChanPinXQActivity extends ZjbBaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Integer> adapter;
    private List<String> stringList = new ArrayList<>();
    private List<String> goodsInfoBanner;
    private int id;

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
            }
        });
        adapter.addFooter(new RecyclerArrayAdapter.ItemView() {
            private RecyclerArrayAdapter<Integer> adapterFoot;
            private EasyRecyclerView recyclerViewFoot;
            private TabLayout tablayout;
            private List<Integer> imageList = new ArrayList<>();

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
                recyclerViewFoot = view.findViewById(R.id.recyclerView);
                imageList.add(R.mipmap.chanpinxiangqing);
                imageList.add(R.mipmap.chanpinxiangqing);
                imageList.add(R.mipmap.chanpinxiangqing);
                initFootRecycler();
                return view;
            }

            /**
             * 初始化recyclerview
             */
            private void initFootRecycler() {
                recyclerViewFoot.setLayoutManager(new LinearLayoutManager(ChanPinXQActivity.this));
                recyclerViewFoot.setAdapterWithProgress(adapterFoot = new RecyclerArrayAdapter<Integer>(ChanPinXQActivity.this) {
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

            @Override
            public void onBindView(View headerView) {
                adapterFoot.clear();
                adapterFoot.addAll(imageList);
            }
        });
        recyclerView.setRefreshListener(this);
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        onRefresh();
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
            params.put("tokenTime",tokenTime);
        }
        params.put("id",String.valueOf(id));
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
                        GoodsInfo.DataBean goodsInfoData = goodsInfo.getData();
                        adapter.clear();
                        adapter.addAll(DataProvider.getPersonList(1));
                    } else if (goodsInfo.getStatus()== 3) {
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
}
