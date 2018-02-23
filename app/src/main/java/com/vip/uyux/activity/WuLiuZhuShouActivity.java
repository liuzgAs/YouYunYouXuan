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
import com.vip.uyux.model.MassageWuliu;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.viewholder.WuLiuZSViewHolder;

import java.util.HashMap;
import java.util.List;

public class WuLiuZhuShouActivity extends ZjbBaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<MassageWuliu.DataBean> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wu_liu_zhu_shou);
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
        ((TextView) findViewById(R.id.textViewTitle)).setText("物流助手");
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
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<MassageWuliu.DataBean>(WuLiuZhuShouActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_wuliuzhushou;
                return new WuLiuZSViewHolder(parent, layout);
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                ApiClient.post(WuLiuZhuShouActivity.this, getOkObject(), new ApiClient.CallBack() {
                    @Override
                    public void onSuccess(String s) {
                        LogUtil.LogShitou("DingDanGLActivity--加载更多", s+"");
                        try {
                            page++;
                            MassageWuliu massageWuliu = GsonUtils.parseJSON(s, MassageWuliu.class);
                            int status = massageWuliu.getStatus();
                            if (status == 1) {
                                List<MassageWuliu.DataBean> dataBeanList = massageWuliu.getData();
                                adapter.addAll(dataBeanList);
                            } else if (status == 3) {
                                MyDialog.showReLoginDialog(WuLiuZhuShouActivity.this);
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

    int page =1;

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.MASSAGE_WULIU;
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
     page =1;
     ApiClient.post(this, getOkObject(), new ApiClient.CallBack() {
         @Override
         public void onSuccess(String s) {
             LogUtil.LogShitou("", s);
             try {
                 page++;
                 MassageWuliu massageWuliu = GsonUtils.parseJSON(s, MassageWuliu.class);
                 if (massageWuliu.getStatus() == 1) {
                     List<MassageWuliu.DataBean> dataBeanList = massageWuliu.getData();
                     adapter.clear();
                     adapter.addAll(dataBeanList);
                 } else if (massageWuliu.getStatus()== 3) {
                     MyDialog.showReLoginDialog(WuLiuZhuShouActivity.this);
                 } else {
                     showError(massageWuliu.getInfo());
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
                 View viewLoader = LayoutInflater.from(WuLiuZhuShouActivity.this).inflate(R.layout.view_loaderror, null);
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
