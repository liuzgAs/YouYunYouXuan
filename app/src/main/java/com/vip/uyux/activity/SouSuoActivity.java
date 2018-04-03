package com.vip.uyux.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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
import com.vip.uyux.model.GoodBean;
import com.vip.uyux.model.GoodsIndex;
import com.vip.uyux.model.GoodsSearch;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.util.ScreenUtils;
import com.vip.uyux.viewholder.ChanPinLBViewHolder;

import java.util.HashMap;
import java.util.List;

public class SouSuoActivity extends ZjbBaseActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private FlowTagLayout flowTagLayout01;
    private FlowTagLayout flowTagLayout02;
    private TagHotAdapter tagHotAdapter01;
    private TagHotAdapter tagHotAdapter02;
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<GoodBean> adapter;
    private int page = 0;
    private String keywords;
    private EditText editSouSuo;
    private ImageView imageSouSuo;
    private ScrollView scrollHot;
    private View viewSearch;
    private int[] shaiXuanArr = new int[3];
    private TextView textZongHe;
    private TextView textXiaoLiang;
    private TextView textJiaGe;
    private ImageView sanJiaoUp;
    private ImageView sanJiaoDown;
    private ImageView sanJiaoUp1;
    private ImageView imageBack1;
    private ImageView sanJiaoDown1;
    private View relativeLayout2;

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
        flowTagLayout01 = (FlowTagLayout) findViewById(R.id.flowTagLayout01);
        flowTagLayout02 = (FlowTagLayout) findViewById(R.id.flowTagLayout02);
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        editSouSuo = (EditText) findViewById(R.id.editSouSuo);
        imageSouSuo = (ImageView) findViewById(R.id.imageSouSuo);
        imageBack1= (ImageView) findViewById(R.id.imageBack1);
        scrollHot = (ScrollView) findViewById(R.id.scrollHot);
        viewSearch = findViewById(R.id.viewSearch);
        textZongHe = (TextView) findViewById(R.id.textZongHe);
        textXiaoLiang = (TextView) findViewById(R.id.textXiaoLiang);
        textJiaGe = (TextView) findViewById(R.id.textJiaGe);
        sanJiaoUp = (ImageView) findViewById(R.id.sanJiaoUp);
        sanJiaoDown = (ImageView) findViewById(R.id.sanJiaoDown);
        sanJiaoUp1 = (ImageView) findViewById(R.id.sanJiaoUp1);
        sanJiaoDown1 = (ImageView) findViewById(R.id.sanJiaoDown1);
        relativeLayout2 = findViewById(R.id.relativeLayout2);
    }

    @Override
    protected void initViews() {
        shaiXuanArr[0] = 1;
        shaiXuanArr[1] = 0;
        shaiXuanArr[2] = 0;
        setShaiXuan();
        viewSearch.setVisibility(View.GONE);
        ViewGroup.LayoutParams layoutParams = relativeLayout2.getLayoutParams();
        layoutParams.height = (int) (getResources().getDimension(R.dimen.titleHeight) + ScreenUtils.getStatusBarHeight(this));
        relativeLayout2.setLayoutParams(layoutParams);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.top), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        int red = getResources().getColor(R.color.basic_color);
        recyclerView.setRefreshingColor(red);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<GoodBean>(SouSuoActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_chanpin;
                return new ChanPinLBViewHolder(parent, layout,0);
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                ApiClient.post(SouSuoActivity.this, getOkObjectSouSuo(), new ApiClient.CallBack() {
                    @Override
                    public void onSuccess(String s) {
                        LogUtil.LogShitou("DingDanGLActivity--加载更多", s + "");
                        try {
                            page++;
                            GoodsIndex goodsIndex = GsonUtils.parseJSON(s, GoodsIndex.class);
                            int status = goodsIndex.getStatus();
                            if (status == 1) {
                                List<GoodBean> dataBeanList = goodsIndex.getData();
                                adapter.addAll(dataBeanList);
                            } else if (status == 3) {
                                MyDialog.showReLoginDialog(SouSuoActivity.this);
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
        recyclerView.setRefreshListener(this);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                    Intent intent = new Intent();
                    intent.putExtra(Constant.IntentKey.ID, adapter.getItem(position).getId());
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.setClass(SouSuoActivity.this, ChanPinXQCZActivity.class);
                    startActivity(intent);
            }
        });
    }

    /**
     * 设置筛选
     */
    private void setShaiXuan() {
        if (shaiXuanArr[0] == 0) {
            textZongHe.setTextColor(ContextCompat.getColor(this, R.color.text_gray));
        } else {
            textZongHe.setTextColor(ContextCompat.getColor(this, R.color.basic_color));
        }
        if (shaiXuanArr[1] == 0) {
            textXiaoLiang.setTextColor(ContextCompat.getColor(this, R.color.text_gray));
            sanJiaoUp.setImageResource(R.mipmap.san_jiao_up_g);
            sanJiaoDown.setImageResource(R.mipmap.san_jiao_down_g);
        } else {
            textXiaoLiang.setTextColor(ContextCompat.getColor(this, R.color.basic_color));
            if (shaiXuanArr[1] == 1) {
                sanJiaoUp.setImageResource(R.mipmap.san_jiao_up_r);
                sanJiaoDown.setImageResource(R.mipmap.san_jiao_down_g);
            } else {
                sanJiaoUp.setImageResource(R.mipmap.san_jiao_up_g);
                sanJiaoDown.setImageResource(R.mipmap.san_jiao_down_r);
            }
        }
        if (shaiXuanArr[2] == 0) {
            textJiaGe.setTextColor(ContextCompat.getColor(this, R.color.text_gray));
            sanJiaoUp1.setImageResource(R.mipmap.san_jiao_up_g);
            sanJiaoDown1.setImageResource(R.mipmap.san_jiao_down_g);
        } else {
            textJiaGe.setTextColor(ContextCompat.getColor(this, R.color.basic_color));
            if (shaiXuanArr[2] == 1) {
                sanJiaoUp1.setImageResource(R.mipmap.san_jiao_up_r);
                sanJiaoDown1.setImageResource(R.mipmap.san_jiao_down_g);
            } else {
                sanJiaoUp1.setImageResource(R.mipmap.san_jiao_up_g);
                sanJiaoDown1.setImageResource(R.mipmap.san_jiao_down_r);
            }
        }
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.viewZongHe).setOnClickListener(this);
        findViewById(R.id.viewXiaoLiang).setOnClickListener(this);
        findViewById(R.id.viewJiaGe).setOnClickListener(this);
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
                    viewSearch.setVisibility(View.GONE);
                    scrollHot.setVisibility(View.VISIBLE);
                } else {
                    keywords = s.toString().trim();
                    ApiClient.cancleAll();
                    SouSuo();
                    viewSearch.setVisibility(View.VISIBLE);
                    scrollHot.setVisibility(View.GONE);
                }
            }
        });
        imageSouSuo.setOnClickListener(this);
        findViewById(R.id.imageBack1).setOnClickListener(this);
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.GOODS_SEARCH;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
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
                                editSouSuo.setText(keywords);
                                editSouSuo.setSelection(keywords.length());
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
                                editSouSuo.setText(keywords);
                                editSouSuo.setSelection(keywords.length());
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
        SouSuo();
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObjectSouSuo() {
        String url = Constant.HOST + Constant.Url.GOODS_INDEX;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("p", page + "");
        params.put("keywords", keywords);
        if (shaiXuanArr[0] == 0) {
            if (shaiXuanArr[1]==1){
                params.put("sort", "saleAsc");
            }else if (shaiXuanArr[1]==2){
                params.put("sort", "saleDesc");
            }else {
                if (shaiXuanArr[2]==1){
                    params.put("sort", "priceAsc");
                }else if (shaiXuanArr[2]==2){
                    params.put("sort", "priceDesc");
                }else {

                }
            }
        }
        return new OkObject(params, url);
    }

    private void SouSuo() {
//        editSouSuo.setText(keywords);
//        editSouSuo.setSelection(keywords.length());
        page = 1;
        ApiClient.post(this, getOkObjectSouSuo(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("限时购", s);
                try {
                    page++;
                    GoodsIndex goodsIndex = GsonUtils.parseJSON(s, GoodsIndex.class);
                    if (goodsIndex.getStatus() == 1) {
                        List<GoodBean> indexGoodsData = goodsIndex.getData();
                        adapter.clear();
                        adapter.addAll(indexGoodsData);
                    } else if (goodsIndex.getStatus() == 3) {
                        MyDialog.showReLoginDialog(SouSuoActivity.this);
                    } else {
                        showError(goodsIndex.getInfo());
                    }
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
            case R.id.viewZongHe:
                shaiXuanArr[0] = 1;
                shaiXuanArr[1] = 0;
                shaiXuanArr[2] = 0;
                setShaiXuan();
                recyclerView.showProgress();
                SouSuo();
                break;
            case R.id.viewXiaoLiang:
                if (shaiXuanArr[1] == 0) {
                    shaiXuanArr[0] = 0;
                    shaiXuanArr[1] = 1;
                    shaiXuanArr[2] = 0;
                } else if (shaiXuanArr[1] == 1) {
                    shaiXuanArr[0] = 0;
                    shaiXuanArr[1] = 2;
                    shaiXuanArr[2] = 0;
                } else {
                    shaiXuanArr[0] = 0;
                    shaiXuanArr[1] = 1;
                    shaiXuanArr[2] = 0;
                }
                setShaiXuan();
                recyclerView.showProgress();
                SouSuo();
                break;
            case R.id.viewJiaGe:
                if (shaiXuanArr[2] == 0) {
                    shaiXuanArr[0] = 0;
                    shaiXuanArr[1] = 0;
                    shaiXuanArr[2] = 1;
                } else if (shaiXuanArr[2] == 1) {
                    shaiXuanArr[0] = 0;
                    shaiXuanArr[1] = 0;
                    shaiXuanArr[2] = 2;
                } else {
                    shaiXuanArr[0] = 0;
                    shaiXuanArr[1] = 0;
                    shaiXuanArr[2] = 1;
                }
                setShaiXuan();
                recyclerView.showProgress();
                SouSuo();
                break;
            case R.id.imageSouSuo:
                keywords = editSouSuo.getText().toString().trim();
                editSouSuo.setText(keywords);
                editSouSuo.setSelection(keywords.length());
                break;
            case R.id.imageBack1:
                finish();
                break;
            default:
                break;
        }
    }

}
