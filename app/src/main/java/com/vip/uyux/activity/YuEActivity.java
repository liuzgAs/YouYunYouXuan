package com.vip.uyux.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.model.WithdrawNotwithdraw;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.DateTransforam;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.viewholder.XiaoFeiMXViewHolder;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class YuEActivity extends ZjbBaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<WithdrawNotwithdraw.DataBean> adapter;
    private TextView textShouYi;
    private TextView textStart;
    private TextView textViewRight;
    private TextView textEnd;
    private String date_begin = "";
    private String date_end = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yu_e);
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
        textShouYi = (TextView) findViewById(R.id.textShouYi);
        textStart = (TextView) findViewById(R.id.textStart);
        textEnd = (TextView) findViewById(R.id.textEnd);
        textViewRight= (TextView) findViewById(R.id.textViewRight);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("我的余额");
        textViewRight.setText("提现记录");
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
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<WithdrawNotwithdraw.DataBean>(YuEActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_chong_zhi_mx;
                return new XiaoFeiMXViewHolder(parent, layout, 1);
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                ApiClient.post(YuEActivity.this, getOkObject(), new ApiClient.CallBack() {
                    @Override
                    public void onSuccess(String s) {
                        LogUtil.LogShitou("DingDanGLActivity--加载更多", s + "");
                        try {
                            page++;
                            WithdrawNotwithdraw dataBean = GsonUtils.parseJSON(s, WithdrawNotwithdraw.class);
                            int status = dataBean.getStatus();
                            if (status == 1) {
                                List<WithdrawNotwithdraw.DataBean> dataBeanList = dataBean.getData();
                                adapter.addAll(dataBeanList);
                            } else if (status == 3) {
                                MyDialog.showReLoginDialog(YuEActivity.this);
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
        adapter.setNoMore(R.layout.view_nomore, new RecyclerArrayAdapter.OnNoMoreListener() {
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
            }
        });
        recyclerView.setRefreshListener(this);
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.btnLiJiTX).setOnClickListener(this);
        findViewById(R.id.viewStart).setOnClickListener(this);
        findViewById(R.id.viewEnd).setOnClickListener(this);
        textViewRight.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLiJiTX:
                Intent intent = new Intent();
                intent.setClass(this,TiXianActivity.class);
                intent.putExtra(Constant.IntentKey.TYPE,1);
                startActivity(intent);
                break;
            case R.id.imageBack:
                finish();
                break;
            case R.id.textViewRight:
                Intent intent1 = new Intent();
                intent1.setClass(YuEActivity.this, TiXianJLActivity.class);
                intent1.putExtra(Constant.IntentKey.TYPE,1);
                startActivity(intent1);
                break;
            case R.id.viewStart:
                Calendar c1 = Calendar.getInstance();
                DatePickerDialog datePickerDialog1 = new DatePickerDialog(YuEActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        try {
                            date_begin = DateTransforam.dateToStamp(year + "-" + (month + 1) + "-" + dayOfMonth);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        textStart.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        onRefresh();
                    }
                }, c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH));
                if (!TextUtils.isEmpty(date_end)) {
                    datePickerDialog1.getDatePicker().setMaxDate(Long.parseLong(date_end) * 1000);
                } else {
                    datePickerDialog1.getDatePicker().setMaxDate(System.currentTimeMillis());
                }
                datePickerDialog1.show();
                break;
            case R.id.viewEnd:
                Calendar c = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(YuEActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        try {
                            date_end = DateTransforam.dateToStamp(year + "-" + (month + 1) + "-" + dayOfMonth);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        textEnd.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        onRefresh();
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                if (!TextUtils.isEmpty(date_begin)) {
                    datePickerDialog.getDatePicker().setMinDate(Long.parseLong(date_begin) * 1000);
                }
                datePickerDialog.show();
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
        String url = Constant.HOST + Constant.Url.WITHDRAW_BALANCE;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("p", String.valueOf(page));
        params.put("date_begin", date_begin);
        params.put("date_end", date_end);
        return new OkObject(params, url);
    }

    @Override
    public void onRefresh() {
        page = 1;
        ApiClient.post(this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("", s);
                try {
                    page++;
                    WithdrawNotwithdraw withdrawNotwithdraw = GsonUtils.parseJSON(s, WithdrawNotwithdraw.class);
                    if (withdrawNotwithdraw.getStatus() == 1) {
                        textShouYi.setText(withdrawNotwithdraw.getN_amount());
                        List<WithdrawNotwithdraw.DataBean> dataBeanList = withdrawNotwithdraw.getData();
                        adapter.clear();
                        adapter.addAll(dataBeanList);
                    } else if (withdrawNotwithdraw.getStatus() == 3) {
                        MyDialog.showReLoginDialog(YuEActivity.this);
                    } else {
                        showError(withdrawNotwithdraw.getInfo());
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
                    View viewLoader = LayoutInflater.from(YuEActivity.this).inflate(R.layout.view_loaderror, null);
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
}
