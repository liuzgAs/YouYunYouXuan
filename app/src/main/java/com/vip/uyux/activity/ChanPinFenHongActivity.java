package com.vip.uyux.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.BonusGetprobonus;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.viewholder.ChanPinFHViewHolder;

import java.util.HashMap;
import java.util.List;

public class ChanPinFenHongActivity extends ZjbBaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private EasyRecyclerView recyclerView;
    public RecyclerArrayAdapter<BonusGetprobonus.GoodsListBean> adapter;
    private String k_money;
    private String y_money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chan_pin_fen_hong);
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
    }

    @Override
    protected void initViews() {
        initRecycler();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.top), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<BonusGetprobonus.GoodsListBean>(ChanPinFenHongActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_chanpin_fenhong;
                return new ChanPinFHViewHolder(parent, layout);
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {

            private TextView textYuJiFH;
            private TextView textKeTiXian;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(ChanPinFenHongActivity.this).inflate(R.layout.header_chanpin_fenhong, null);
                textKeTiXian = view.findViewById(R.id.textKeTiXian);
                textYuJiFH = view.findViewById(R.id.textYuJiFH);
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                textKeTiXian.setText(String.valueOf(k_money));
                textYuJiFH.setText(String.valueOf(y_money));
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                ApiClient.post(ChanPinFenHongActivity.this, getOkObject(), new ApiClient.CallBack() {
                    @Override
                    public void onSuccess(String s) {
                        LogUtil.LogShitou("DingDanGLActivity--加载更多", s+"");
                        try {
                            page++;
                            BonusGetprobonus bonusGetprobonus = GsonUtils.parseJSON(s, BonusGetprobonus.class);
                            int status = bonusGetprobonus.getStatus();
                            if (status == 1) {
                                List<BonusGetprobonus.GoodsListBean> goods_list = bonusGetprobonus.getGoods_list();
                                for (int i = 0; i < goods_list.size(); i++) {
                                    goods_list.get(i).setZhanKai(false);
                                }
                                adapter.addAll(goods_list);
                            } else if (status == 3) {
                                MyDialog.showReLoginDialog(ChanPinFenHongActivity.this);
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

    int page = 1;

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.BONUS_GETPROBONUS;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime",tokenTime);
        }
        params.put("p",String.valueOf(page));
        return new OkObject(params, url);
    }

    @Override
    public void onRefresh() {
        page = 1;
        ApiClient.post(this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("", s);
                s="{\n" +
                        "    \"title\": \"产品分红\",\n" +
                        "    \"k_money\": 0,\n" +
                        "    \"y_money\": 0,\n" +
                        "    \"des\": \"提现需要7个工作日审核\",\n" +
                        "    \"status\": 1,\n" +
                        "    \"info\": \"获取成功\",\n" +
                        "    \"goods_list\": [\n" +
                        "        {\n" +
                        "            \"goods_title\": \"地毯地垫厨房浴室防滑地垫门厅玄关入户地垫客厅卧室防尘蹭蹭垫子\",\n" +
                        "            \"id\": 1746,\n" +
                        "            \"img\": \"images/vslai_shop/1604/2018/01/HVZZP2PvQwm5qTPv24vWOjvOjMOqWP.jpg\",\n" +
                        "            \"goods_rule\": \"\",\n" +
                        "            \"price\": \"52.00\",\n" +
                        "            \"total_money\": 0,\n" +
                        "            \"des1\": \"近7日收益¥0\",\n" +
                        "            \"des2\": \"近7日销量0件\",\n" +
                        "            \"num1\": 0,\n" +
                        "            \"num2\": 0,\n" +
                        "            \"max\": 0,\n" +
                        "            \"profit\": [\n" +
                        "                {\n" +
                        "                    \"title\": \"01-31\",\n" +
                        "                    \"v1\": 0,\n" +
                        "                    \"v2\": 0\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"title\": \"02-01\",\n" +
                        "                    \"v1\": 0,\n" +
                        "                    \"v2\": 0\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"title\": \"02-02\",\n" +
                        "                    \"v1\": 0,\n" +
                        "                    \"v2\": 0\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"title\": \"02-03\",\n" +
                        "                    \"v1\": 0,\n" +
                        "                    \"v2\": 0\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"title\": \"02-04\",\n" +
                        "                    \"v1\": 0,\n" +
                        "                    \"v2\": 0\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"title\": \"02-05\",\n" +
                        "                    \"v1\": 0,\n" +
                        "                    \"v2\": 0\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"title\": \"02-06\",\n" +
                        "                    \"v1\": 0,\n" +
                        "                    \"v2\": 0\n" +
                        "                }\n" +
                        "            ]\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"goods_title\": \"优云优选新春大礼包\",\n" +
                        "            \"id\": 1747,\n" +
                        "            \"img\": \"images/1604/2018/01/p80191e10hZZvONzVt2Hz24YS1d8h0.png\",\n" +
                        "            \"goods_rule\": \"一整箱小吃年货组合礼包，超大容量18款进口零食11\",\n" +
                        "            \"price\": \"199.00\",\n" +
                        "            \"total_money\": 0,\n" +
                        "            \"des1\": \"近7日收益¥0\",\n" +
                        "            \"des2\": \"近7日销量0件\",\n" +
                        "            \"num1\": 11,\n" +
                        "            \"num2\": 66,\n" +
                        "            \"max\": 0,\n" +
                        "            \"profit\": [\n" +
                        "                {\n" +
                        "                    \"title\": \"01-31\",\n" +
                        "                    \"v1\": 0,\n" +
                        "                    \"v2\": 0\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"title\": \"02-01\",\n" +
                        "                    \"v1\": 0,\n" +
                        "                    \"v2\": 0\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"title\": \"02-02\",\n" +
                        "                    \"v1\": 0,\n" +
                        "                    \"v2\": 0\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"title\": \"02-03\",\n" +
                        "                    \"v1\": 0,\n" +
                        "                    \"v2\": 0\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"title\": \"02-04\",\n" +
                        "                    \"v1\": 0,\n" +
                        "                    \"v2\": 0\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"title\": \"02-05\",\n" +
                        "                    \"v1\": 0,\n" +
                        "                    \"v2\": 0\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"title\": \"02-06\",\n" +
                        "                    \"v1\": 0,\n" +
                        "                    \"v2\": 0\n" +
                        "                }\n" +
                        "            ]\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}";
                try {
                    page++;
                    BonusGetprobonus bonusGetprobonus = GsonUtils.parseJSON(s, BonusGetprobonus.class);
                    if (bonusGetprobonus.getStatus() == 1) {
                        k_money = bonusGetprobonus.getK_money();
                        y_money = bonusGetprobonus.getY_money();
                        List<BonusGetprobonus.GoodsListBean> goods_list = bonusGetprobonus.getGoods_list();
                        for (int i = 0; i < goods_list.size(); i++) {
                            goods_list.get(i).setZhanKai(false);
                        }
                        adapter.clear();
                        adapter.addAll(goods_list);
                    } else if (bonusGetprobonus.getStatus() == 3) {
                        MyDialog.showReLoginDialog(ChanPinFenHongActivity.this);
                    } else {
                        showError(bonusGetprobonus.getInfo());
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
                    View viewLoader = LayoutInflater.from(ChanPinFenHongActivity.this).inflate(R.layout.view_loaderror, null);
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
