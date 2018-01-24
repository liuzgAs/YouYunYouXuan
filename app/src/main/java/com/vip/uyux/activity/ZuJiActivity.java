package com.vip.uyux.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.jude.easyrecyclerview.decoration.StickyHeaderDecoration;
import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.GoodsViewlog;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.viewholder.ZuJiEmptyViewHolder;
import com.vip.uyux.viewholder.ZuJiViewHolder;

import java.util.HashMap;
import java.util.List;

public class ZuJiActivity extends ZjbBaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<GoodsViewlog.DataBean> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zu_ji);
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
        ((TextView) findViewById(R.id.textViewTitle)).setText("我的足迹");
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
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<GoodsViewlog.DataBean>(ZuJiActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout;
                switch (viewType) {
                    case 0:
                        layout= R.layout.item_zuji_empty;
                        return new ZuJiEmptyViewHolder(parent, layout);
                    case 1:
                         layout = R.layout.item_zuji;
                        return new ZuJiViewHolder(parent, layout);
                    default:
                         layout = R.layout.item_zuji;
                        return new ZuJiViewHolder(parent, layout);
                }
            }

            @Override
            public int getViewType(int position) {
                return getItem(position).getType();
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                ApiClient.post(ZuJiActivity.this, getOkObject(), new ApiClient.CallBack() {
                    @Override
                    public void onSuccess(String s) {
                        LogUtil.LogShitou("DingDanGLActivity--加载更多", s + "");
                        try {
                            page++;
                            GoodsViewlog goodsViewlog = GsonUtils.parseJSON(s, GoodsViewlog.class);
                            int status = goodsViewlog.getStatus();
                            if (status == 1) {
                                List<GoodsViewlog.DataBean> dataBeanList = goodsViewlog.getData();
                                adapter.addAll(dataBeanList);
                            } else if (status == 3) {
                                MyDialog.showReLoginDialog(ZuJiActivity.this);
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
        String url = Constant.HOST + Constant.Url.GOODS_VIEWLOG;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("p", String.valueOf(page));
        return new OkObject(params, url);
    }

    @Override
    public void onRefresh() {
        page = 1;
        ApiClient.post(this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("足迹", s);
                s= "{\n" +
                        "\t\"data\":[\n" +
                        "\t\t{\n" +
                        "\t\t\t\"goods_id\":0,\n" +
                        "\t\t\t\"id\":0,\n" +
                        "\t\t\t\"img\":\"\",\n" +
                        "\t\t\t\"title\":\"今日浏览\",\n" +
                        "\t\t\t\"type\":0\n" +
                        "\t\t},\n" +
                        "\t\t{\n" +
                        "\t\t\t\"goods_id\":1226,\n" +
                        "\t\t\t\"id\":2016,\n" +
                        "\t\t\t\"img\":\"http://www.uyux.vip/attachment/images/1604/2017/12/kB87BH7H73DHD8H0Bs5HBDhH0X5863.jpg\",\n" +
                        "\t\t\t\"title\":\"网红简约透明健身摇摇杯\",\n" +
                        "\t\t\t\"type\":1\n" +
                        "\t\t},\n" +
                        "\t\t{\n" +
                        "\t\t\t\"goods_id\":1286,\n" +
                        "\t\t\t\"id\":2015,\n" +
                        "\t\t\t\"img\":\"http://www.uyux.vip/attachment/images/1604/2017/12/zeaileDPEZld8iPgoLvpq7eOQ81c1P.jpg\",\n" +
                        "\t\t\t\"title\":\"便捷吹气旅行枕\",\n" +
                        "\t\t\t\"type\":1\n" +
                        "\t\t},\n" +
                        "\t\t{\n" +
                        "\t\t\t\"goods_id\":1353,\n" +
                        "\t\t\t\"id\":2014,\n" +
                        "\t\t\t\"img\":\"http://www.uyux.vip/attachment/images/1604/2017/12/I3G434jXcj0Xz00m934XxxZeuiy4QU.jpg\",\n" +
                        "\t\t\t\"title\":\"美国原装进口桂格燕麦\",\n" +
                        "\t\t\t\"type\":1\n" +
                        "\t\t},\n" +
                        "\t\t{\n" +
                        "\t\t\t\"goods_id\":0,\n" +
                        "\t\t\t\"id\":0,\n" +
                        "\t\t\t\"img\":\"\",\n" +
                        "\t\t\t\"title\":\"2018.01.23\",\n" +
                        "\t\t\t\"type\":0\n" +
                        "\t\t},\n" +
                        "\t\t{\n" +
                        "\t\t\t\"goods_id\":1123,\n" +
                        "\t\t\t\"id\":1791,\n" +
                        "\t\t\t\"img\":\"http://www.uyux.vip/attachment/images/1604/2017/12/TLl0pMGbPm8rZHQXojzeYCHhj9hBll.jpg\",\n" +
                        "\t\t\t\"title\":\"有机小薏米\",\n" +
                        "\t\t\t\"type\":1\n" +
                        "\t\t},\n" +
                        "\t\t{\n" +
                        "\t\t\t\"goods_id\":1123,\n" +
                        "\t\t\t\"id\":1791,\n" +
                        "\t\t\t\"img\":\"http://www.uyux.vip/attachment/images/1604/2017/12/TLl0pMGbPm8rZHQXojzeYCHhj9hBll.jpg\",\n" +
                        "\t\t\t\"title\":\"有机小薏米\",\n" +
                        "\t\t\t\"type\":1\n" +
                        "\t\t},\n" +
                        "\t\t{\n" +
                        "\t\t\t\"goods_id\":1123,\n" +
                        "\t\t\t\"id\":1791,\n" +
                        "\t\t\t\"img\":\"http://www.uyux.vip/attachment/images/1604/2017/12/TLl0pMGbPm8rZHQXojzeYCHhj9hBll.jpg\",\n" +
                        "\t\t\t\"title\":\"有机小薏米\",\n" +
                        "\t\t\t\"type\":1\n" +
                        "\t\t},\n" +
                        "\t\t{\n" +
                        "\t\t\t\"goods_id\":0,\n" +
                        "\t\t\t\"id\":0,\n" +
                        "\t\t\t\"img\":\"\",\n" +
                        "\t\t\t\"title\":\"2018.01.23\",\n" +
                        "\t\t\t\"type\":0\n" +
                        "\t\t},\n" +
                        "\t\t{\n" +
                        "\t\t\t\"goods_id\":1123,\n" +
                        "\t\t\t\"id\":1791,\n" +
                        "\t\t\t\"img\":\"http://www.uyux.vip/attachment/images/1604/2017/12/TLl0pMGbPm8rZHQXojzeYCHhj9hBll.jpg\",\n" +
                        "\t\t\t\"title\":\"有机小薏米\",\n" +
                        "\t\t\t\"type\":1\n" +
                        "\t\t},\n" +
                        "\t\t{\n" +
                        "\t\t\t\"goods_id\":1123,\n" +
                        "\t\t\t\"id\":1791,\n" +
                        "\t\t\t\"img\":\"http://www.uyux.vip/attachment/images/1604/2017/12/TLl0pMGbPm8rZHQXojzeYCHhj9hBll.jpg\",\n" +
                        "\t\t\t\"title\":\"有机小薏米\",\n" +
                        "\t\t\t\"type\":1\n" +
                        "\t\t},\n" +
                        "\t\t{\n" +
                        "\t\t\t\"goods_id\":1123,\n" +
                        "\t\t\t\"id\":1791,\n" +
                        "\t\t\t\"img\":\"http://www.uyux.vip/attachment/images/1604/2017/12/TLl0pMGbPm8rZHQXojzeYCHhj9hBll.jpg\",\n" +
                        "\t\t\t\"title\":\"有机小薏米\",\n" +
                        "\t\t\t\"type\":1\n" +
                        "\t\t},\n" +
                        "\t\t{\n" +
                        "\t\t\t\"goods_id\":0,\n" +
                        "\t\t\t\"id\":0,\n" +
                        "\t\t\t\"img\":\"\",\n" +
                        "\t\t\t\"title\":\"2018.01.23\",\n" +
                        "\t\t\t\"type\":0\n" +
                        "\t\t},\n" +
                        "\t\t{\n" +
                        "\t\t\t\"goods_id\":1123,\n" +
                        "\t\t\t\"id\":1791,\n" +
                        "\t\t\t\"img\":\"http://www.uyux.vip/attachment/images/1604/2017/12/TLl0pMGbPm8rZHQXojzeYCHhj9hBll.jpg\",\n" +
                        "\t\t\t\"title\":\"有机小薏米\",\n" +
                        "\t\t\t\"type\":1\n" +
                        "\t\t},\n" +
                        "\t\t{\n" +
                        "\t\t\t\"goods_id\":1123,\n" +
                        "\t\t\t\"id\":1791,\n" +
                        "\t\t\t\"img\":\"http://www.uyux.vip/attachment/images/1604/2017/12/TLl0pMGbPm8rZHQXojzeYCHhj9hBll.jpg\",\n" +
                        "\t\t\t\"title\":\"有机小薏米\",\n" +
                        "\t\t\t\"type\":1\n" +
                        "\t\t},\n" +
                        "\t\t{\n" +
                        "\t\t\t\"goods_id\":1123,\n" +
                        "\t\t\t\"id\":1791,\n" +
                        "\t\t\t\"img\":\"http://www.uyux.vip/attachment/images/1604/2017/12/TLl0pMGbPm8rZHQXojzeYCHhj9hBll.jpg\",\n" +
                        "\t\t\t\"title\":\"有机小薏米\",\n" +
                        "\t\t\t\"type\":1\n" +
                        "\t\t},\n" +
                        "\t\t{\n" +
                        "\t\t\t\"goods_id\":0,\n" +
                        "\t\t\t\"id\":0,\n" +
                        "\t\t\t\"img\":\"\",\n" +
                        "\t\t\t\"title\":\"2018.01.23\",\n" +
                        "\t\t\t\"type\":0\n" +
                        "\t\t},\n" +
                        "\t\t{\n" +
                        "\t\t\t\"goods_id\":1123,\n" +
                        "\t\t\t\"id\":1791,\n" +
                        "\t\t\t\"img\":\"http://www.uyux.vip/attachment/images/1604/2017/12/TLl0pMGbPm8rZHQXojzeYCHhj9hBll.jpg\",\n" +
                        "\t\t\t\"title\":\"有机小薏米\",\n" +
                        "\t\t\t\"type\":1\n" +
                        "\t\t},\n" +
                        "\t\t{\n" +
                        "\t\t\t\"goods_id\":1123,\n" +
                        "\t\t\t\"id\":1791,\n" +
                        "\t\t\t\"img\":\"http://www.uyux.vip/attachment/images/1604/2017/12/TLl0pMGbPm8rZHQXojzeYCHhj9hBll.jpg\",\n" +
                        "\t\t\t\"title\":\"有机小薏米\",\n" +
                        "\t\t\t\"type\":1\n" +
                        "\t\t},\n" +
                        "\t\t{\n" +
                        "\t\t\t\"goods_id\":1123,\n" +
                        "\t\t\t\"id\":1791,\n" +
                        "\t\t\t\"img\":\"http://www.uyux.vip/attachment/images/1604/2017/12/TLl0pMGbPm8rZHQXojzeYCHhj9hBll.jpg\",\n" +
                        "\t\t\t\"title\":\"有机小薏米\",\n" +
                        "\t\t\t\"type\":1\n" +
                        "\t\t},\n" +
                        "\t\t{\n" +
                        "\t\t\t\"goods_id\":0,\n" +
                        "\t\t\t\"id\":0,\n" +
                        "\t\t\t\"img\":\"\",\n" +
                        "\t\t\t\"title\":\"2018.01.23\",\n" +
                        "\t\t\t\"type\":0\n" +
                        "\t\t},\n" +
                        "\t\t{\n" +
                        "\t\t\t\"goods_id\":1123,\n" +
                        "\t\t\t\"id\":1791,\n" +
                        "\t\t\t\"img\":\"http://www.uyux.vip/attachment/images/1604/2017/12/TLl0pMGbPm8rZHQXojzeYCHhj9hBll.jpg\",\n" +
                        "\t\t\t\"title\":\"有机小薏米\",\n" +
                        "\t\t\t\"type\":1\n" +
                        "\t\t},\n" +
                        "\t\t{\n" +
                        "\t\t\t\"goods_id\":1123,\n" +
                        "\t\t\t\"id\":1791,\n" +
                        "\t\t\t\"img\":\"http://www.uyux.vip/attachment/images/1604/2017/12/TLl0pMGbPm8rZHQXojzeYCHhj9hBll.jpg\",\n" +
                        "\t\t\t\"title\":\"有机小薏米\",\n" +
                        "\t\t\t\"type\":1\n" +
                        "\t\t},\n" +
                        "\t\t{\n" +
                        "\t\t\t\"goods_id\":1123,\n" +
                        "\t\t\t\"id\":1791,\n" +
                        "\t\t\t\"img\":\"http://www.uyux.vip/attachment/images/1604/2017/12/TLl0pMGbPm8rZHQXojzeYCHhj9hBll.jpg\",\n" +
                        "\t\t\t\"title\":\"有机小薏米\",\n" +
                        "\t\t\t\"type\":1\n" +
                        "\t\t}\n" +
                        "\t],\n" +
                        "\t\"info\":\"返回成功！\",\n" +
                        "\t\"page\":{\n" +
                        "\t\t\"dataTotal\":4,\n" +
                        "\t\t\"page\":1,\n" +
                        "\t\t\"pageSize\":10,\n" +
                        "\t\t\"pageTotal\":1\n" +
                        "\t},\n" +
                        "\t\"status\":1\n" +
                        "}";
                try {
                    page++;
                    GoodsViewlog goodsViewlog = GsonUtils.parseJSON(s, GoodsViewlog.class);
                    if (goodsViewlog.getStatus() == 1) {
                        List<GoodsViewlog.DataBean> dataBeanList = goodsViewlog.getData();
                        adapter.clear();
                        adapter.addAll(dataBeanList);
                              /*StickyHeader*/
                        StickyHeaderDecoration decoration = new StickyHeaderDecoration(new StickyHeaderAdapter(ZuJiActivity.this));
                        decoration.setIncludeHeader(false);
                        recyclerView.addItemDecoration(decoration);
                    } else if (goodsViewlog.getStatus() == 3) {
                        MyDialog.showReLoginDialog(ZuJiActivity.this);
                    } else {
                        showError(goodsViewlog.getInfo());
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
                    View viewLoader = LayoutInflater.from(ZuJiActivity.this).inflate(R.layout.view_loaderror, null);
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

    /**
     * 磁贴adapter
     */
    public class StickyHeaderAdapter implements StickyHeaderDecoration.IStickyHeaderAdapter<StickyHeaderAdapter.HeaderHolder> {

        private LayoutInflater mInflater;
        private Context context;

        private StickyHeaderAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
            this.context = context;
        }

        @Override
        public long getHeaderId(int position) {
            return position;
        }

        @Override
        public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
            final View view = mInflater.inflate(R.layout.sticky_header_pin_pai, parent, false);
            return new HeaderHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(HeaderHolder viewholder, final int position) {
            if (position < 26) {
                viewholder.textTitle.setText(adapter.getItem(position).getTitle());
            }
        }

        class HeaderHolder extends RecyclerView.ViewHolder {
            public TextView textTitle;

            private HeaderHolder(View itemView) {
                super(itemView);
                textTitle = itemView.findViewById(R.id.textTitle);
            }
        }
    }
}
