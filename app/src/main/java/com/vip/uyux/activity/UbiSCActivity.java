package com.vip.uyux.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.AdvsBean;
import com.vip.uyux.model.CustomerGetintegralshop;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.viewholder.IndexBannerImgHolderView;
import com.vip.uyux.viewholder.JiFenSCViewHolder;

import java.util.HashMap;
import java.util.List;

public class UbiSCActivity extends ZjbBaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<CustomerGetintegralshop.DataBean> adapter;
    private String top_img;
    private int my_integral;
    private int exchange_recode;
    private BroadcastReceiver reciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case Constant.BroadcastCode.SHUA_XIN_U_BI:
                    onRefresh();
                    break;
                default:
                    break;
            }
        }
    };
    private TextView textDuiHuanJL;
    private TextView textScore;
    private EditText editSearch;
//    private View viewTouch;
    private View coordinator;
    private String keywords="";
    private TextView textZhiShiQi;
    private ConvenientBanner banner;
    private List<AdvsBean> bannerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_yun_sc);
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
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        textScore = (TextView) findViewById(R.id.textScore);
        textDuiHuanJL = (TextView) findViewById(R.id.textDuiHuanJL);
        editSearch = (EditText) findViewById(R.id.editSearch);
//        viewTouch = findViewById(R.id.viewTouch);
        coordinator = findViewById(R.id.coordinator);
        banner = (ConvenientBanner) findViewById(R.id.banner);
        banner.setScrollDuration(1000);
        banner.startTurning(3000);
        textZhiShiQi = (TextView) findViewById(R.id.textZhiShiQi);
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                textZhiShiQi.setText((position + 1) + "/" + bannerList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("U币商城");
        initRecycler();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        SpaceDecoration itemDecoration = new SpaceDecoration((int) DpUtils.convertDpToPixel(10f, this));
//        itemDecoration.setPaddingEdgeSide(true);
//        itemDecoration.setPaddingStart(false);
//        itemDecoration.setPaddingHeaderFooter(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<CustomerGetintegralshop.DataBean>(UbiSCActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_jifensc;
                return new JiFenSCViewHolder(parent, layout);
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                ApiClient.post(UbiSCActivity.this, getOkObject(), new ApiClient.CallBack() {
                    @Override
                    public void onSuccess(String s) {
                        LogUtil.LogShitou("DingDanGLActivity--加载更多", s + "");
                        try {
                            page++;
                            CustomerGetintegralshop customerGetintegralshop = GsonUtils.parseJSON(s, CustomerGetintegralshop.class);
                            int status = customerGetintegralshop.getStatus();
                            if (status == 1) {
                                List<CustomerGetintegralshop.DataBean> dataBeanList = customerGetintegralshop.getData();
                                adapter.addAll(dataBeanList);
                            } else if (status == 3) {
                                MyDialog.showReLoginDialog(UbiSCActivity.this);
                            } else {
                                adapter.pauseMore();
                            }
                        } catch (Exception e) {
                            adapter.pauseMore();
                        }
                    }

                    @Override
                    public void onError() {
                        adapter.pauseMore();
                    }
                });
            }

            @Override
            public void onMoreClick() {

            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            private TextView textDuiHuanJL1;
            private TextView textScore1;
            private EditText editSearch1;
            private TextView textZhiShiQi1;
            private View viewSearch;
            private ConvenientBanner banner1;
            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(UbiSCActivity.this).inflate(R.layout.header_ubi, null);
                textScore1 = view.findViewById(R.id.textScore);
                textDuiHuanJL1 = view.findViewById(R.id.textDuiHuanJL);
                editSearch1 = view.findViewById(R.id.editSearch);
                banner1 = view.findViewById(R.id.banner);
                banner.setScrollDuration(1000);
                banner.startTurning(3000);
                textZhiShiQi1 = view.findViewById(R.id.textZhiShiQi);
                viewSearch= view.findViewById(R.id.viewSearch);
                editSearch1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if (b) {
                            ViewGroup.LayoutParams layoutParams = editSearch1.getLayoutParams();
                            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                            editSearch1.setLayoutParams(layoutParams);
                        } else {
                            ViewGroup.LayoutParams layoutParams = editSearch1.getLayoutParams();
                            layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                            editSearch1.setLayoutParams(layoutParams);
                        }
                    }
                });
                editSearch1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        keywords = editable.toString().trim();
                        onRefresh();
                    }
                });
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (customerGetintegralshop1!=null){
                    bannerList = customerGetintegralshop1.getBanner();
                    my_integral = customerGetintegralshop1.getMy_integral();
                    exchange_recode = customerGetintegralshop1.getExchange_recode();
                    textScore1.setText(String.valueOf(my_integral));
                    textDuiHuanJL1.setText(String.valueOf(exchange_recode));
                    if (bannerList != null) {
                        if (bannerList.size() > 0) {
                            banner1.setPages(new CBViewHolderCreator() {
                                @Override
                                public Object createHolder() {
                                    return new IndexBannerImgHolderView();
                                }
                            }, bannerList);
                        } else {
                            textZhiShiQi1.setText("0/0");
                        }
                    } else {
                        textZhiShiQi1.setText("0/0");
                    }
                }
            }
        });
        adapter.setNoMore(R.layout.view_nomore_ubi, new RecyclerArrayAdapter.OnNoMoreListener() {
            @Override
            public void onNoMoreShow() {

            }

            @Override
            public void onNoMoreClick() {
            }
        });
        adapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                adapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                adapter.resumeMore();
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                intent.setClass(UbiSCActivity.this, ChanPinJFXQActivity.class);
                intent.putExtra(Constant.IntentKey.ID, adapter.getItem(position).getId());
                startActivity(intent);
            }
        });
        recyclerView.setRefreshListener(this);
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.viewSearch).setOnClickListener(this);
        editSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    ViewGroup.LayoutParams layoutParams = editSearch.getLayoutParams();
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    editSearch.setLayoutParams(layoutParams);
                } else {
                    ViewGroup.LayoutParams layoutParams = editSearch.getLayoutParams();
                    layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    editSearch.setLayoutParams(layoutParams);
                }
            }
        });
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                keywords = editable.toString().trim();
                onRefresh();
            }
        });
//        viewTouch.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                viewTouch.setFocusable(true);
//                viewTouch.setFocusableInTouchMode(true);
//                viewTouch.requestFocus();
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                if (imm != null) {
//                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
//                            0);
//                }
//                return false;
//            }
//        });
//        coordinator.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                coordinator.setFocusable(true);
//                coordinator.setFocusableInTouchMode(true);
//                coordinator.requestFocus();
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                if (imm != null) {
//                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
//                            0);
//                }
//                return false;
//            }
//        });
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.viewSearch:
                if (!editSearch.isFocused()) {
                    editSearch.requestFocus();
                }
                break;
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }

    int page = 1;

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.CUSTOMER_GETINTEGRALSHOP;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("p", String.valueOf(page));
        params.put("keywords", keywords);
        return new OkObject(params, url);
    }
    CustomerGetintegralshop customerGetintegralshop1;
    @Override
    public void onRefresh() {
        page = 1;
        ApiClient.post(this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("", s);
                try {
                    page++;
                    CustomerGetintegralshop customerGetintegralshop = GsonUtils.parseJSON(s, CustomerGetintegralshop.class);
                    if (customerGetintegralshop.getStatus() == 1) {
                        customerGetintegralshop1=customerGetintegralshop;
                        List<CustomerGetintegralshop.DataBean> dataBeanList = customerGetintegralshop.getData();
                        adapter.clear();
                        adapter.addAll(dataBeanList);
                    } else if (customerGetintegralshop.getStatus() == 3) {
                        MyDialog.showReLoginDialog(UbiSCActivity.this);
                    } else {
                        showError(customerGetintegralshop.getInfo());
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
                    View viewLoader = LayoutInflater.from(UbiSCActivity.this).inflate(R.layout.view_loaderror, null);
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

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.BroadcastCode.SHUA_XIN_U_BI);
        registerReceiver(reciver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(reciver);
    }

}
