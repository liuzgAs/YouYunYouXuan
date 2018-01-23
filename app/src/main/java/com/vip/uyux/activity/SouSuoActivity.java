package com.vip.uyux.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.vip.uyux.R;
import com.vip.uyux.adapter.TagHotAdapter;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.customview.FlowTagLayout;
import com.vip.uyux.customview.OnTagClickListener;
import com.vip.uyux.model.GoodsSearch;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.util.ScreenUtils;
import com.vip.uyux.viewholder.MyBaseViewHolder;

import java.util.HashMap;
import java.util.List;

public class SouSuoActivity extends ZjbBaseActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private FlowTagLayout flowTagLayout01;
    private FlowTagLayout flowTagLayout02;
    private TagHotAdapter tagHotAdapter01;
    private TagHotAdapter tagHotAdapter02;
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Integer> adapter;
    private int page = 0;
    private String keywords;
    private EditText editSouSuo;
    private ImageView imageSouSuo;
    private ScrollView scrollHot;
    private View include3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sou_suo);
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
        include3 = findViewById(R.id.include3);
        flowTagLayout01 = (FlowTagLayout) findViewById(R.id.flowTagLayout01);
        flowTagLayout02 = (FlowTagLayout) findViewById(R.id.flowTagLayout02);
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        editSouSuo = (EditText) findViewById(R.id.editSouSuo);
        imageSouSuo = (ImageView) findViewById(R.id.imageSouSuo);
        scrollHot = (ScrollView) findViewById(R.id.scrollHot);
    }

    @Override
    protected void initViews() {
        ((TextView) include3.findViewById(R.id.textViewTitle)).setText("搜索");
        recyclerView.setVisibility(View.GONE);
        ViewGroup.LayoutParams layoutParams = include3.getLayoutParams();
        layoutParams.height = (int) (getResources().getDimension(R.dimen.titleHeight) + ScreenUtils.getStatusBarHeight(this));
        include3.setLayoutParams(layoutParams);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.top), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        int red = getResources().getColor(R.color.basic_color);
        recyclerView.setRefreshingColor(red);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Integer>(SouSuoActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_chanpin;
                return new MyBaseViewHolder(parent, layout);
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
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
        recyclerView.setRefreshListener(this);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                    Intent intent = new Intent();
//                    intent.putExtra(Constant.INTENT_KEY.id, adapter.getItem(position).getId());
//                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                    intent.setClass(SouSuoActivity.this, ChanPinXQActivity.class);
//                    startActivity(intent);
            }
        });
    }

    @Override
    protected void setListeners() {
        editSouSuo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    scrollHot.setVisibility(View.VISIBLE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    scrollHot.setVisibility(View.GONE);
                }
            }
        });
        imageSouSuo.setOnClickListener(this);
        include3.findViewById(R.id.imageBack).setOnClickListener(this);
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.GOODS_SEARCH;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin){
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        return new OkObject(params, url);
    }

    @Override
    protected void initData() {
        showLoadingDialog();
        ApiClient.post(SouSuoActivity.this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("SouSuoActivity--首页搜索", s + "");
                try {
                    GoodsSearch goodsSearch = GsonUtils.parseJSON(s, GoodsSearch.class);
                    if (goodsSearch.getStatus() == 1) {
                        final List<GoodsSearch.HotBean> indexSearchData01 = goodsSearch.getViewLog();
                        final List<GoodsSearch.HotBean> indexSearchData2 = goodsSearch.getHot();
                        flowTagLayout01.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_NONE);
                        tagHotAdapter01 = new TagHotAdapter(SouSuoActivity.this);
                        flowTagLayout01.setAdapter(tagHotAdapter01);
                        tagHotAdapter01.clearAndAddAll(indexSearchData01);
                        flowTagLayout01.setOnTagClickListener(new OnTagClickListener() {
                            @Override
                            public void onItemClick(FlowTagLayout parent, View view, int position) {
                                keywords = indexSearchData01.get(position).getName();
                                SouSuo();
                            }
                        });
                        flowTagLayout02.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_NONE);
                        tagHotAdapter02 = new TagHotAdapter(SouSuoActivity.this);
                        flowTagLayout02.setAdapter(tagHotAdapter02);
                        tagHotAdapter02.clearAndAddAll(indexSearchData2);
                        flowTagLayout02.setOnTagClickListener(new OnTagClickListener() {
                            @Override
                            public void onItemClick(FlowTagLayout parent, View view, int position) {
                                keywords = indexSearchData2.get(position).getName();
                                SouSuo();
                            }
                        });
                    } else if (goodsSearch.getStatus() == 3) {
                        MyDialog.showReLoginDialog(SouSuoActivity.this);
                    } else {
                        Toast.makeText(SouSuoActivity.this, goodsSearch.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(SouSuoActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(SouSuoActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRefresh() {

    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObjectSouSuo() {
        String url = Constant.HOST + Constant.Url.GOODS_INDEX;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin){
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("p", page + "");
        params.put("keywords", keywords);
        return new OkObject(params, url);
    }

    private void SouSuo() {
        editSouSuo.setText(keywords);
        editSouSuo.setSelection(keywords.length());
        page = 1;
        ApiClient.post(this, getOkObjectSouSuo(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("限时购", s);
                try {
                    page++;
//                    IndexGoods indexGoods = GsonUtils.parseJSON(s, IndexGoods.class);
//                    if (indexGoods.getStatus() == 1) {
//                        List<IndexDataBean> indexGoodsData = indexGoods.getData();
//                        adapter.clear();
//                        adapter.addAll(indexGoodsData);
//                    } else if (indexGoods.getStatus() == 3) {
//                        MyDialog.showReLoginDialog(SouSuoActivity.this);
//                    } else {
//                        showError(indexGoods.getInfo());
//                    }
                } catch (Exception e) {
                    showError("数据出错");
                }
            }

            @Override
            public void onError() {
                showError("网络出错");
            }

            public void showError(String msg) {
                View view_loaderror = LayoutInflater.from(SouSuoActivity.this).inflate(R.layout.view_loaderror, null);
                TextView textMsg = (TextView) view_loaderror.findViewById(R.id.textMsg);
                textMsg.setText(msg);
                view_loaderror.findViewById(R.id.buttonReLoad).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recyclerView.showProgress();
                        initData();
                    }
                });
                recyclerView.setErrorView(view_loaderror);
                recyclerView.showError();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageSouSuo:
                keywords = editSouSuo.getText().toString().trim();
                SouSuo();
                break;
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }

}
