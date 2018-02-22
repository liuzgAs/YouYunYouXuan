package com.vip.uyux.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ToLoginActivity;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.interfacepage.OnPingLunListenert;
import com.vip.uyux.model.EvaluationInfo;
import com.vip.uyux.model.GoodsCollect;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.model.ShouCangShanChu;
import com.vip.uyux.model.SimpleInfo;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.util.RecycleViewDistancaUtil;
import com.vip.uyux.viewholder.CePingViewHolder;
import com.vip.uyux.viewholder.CePingXQImgViewHolder;

import java.util.HashMap;
import java.util.List;

public class CePingXQActivity extends ZjbBaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<EvaluationInfo.DataBean> adapter;
    private View viewBar;
    private float guangGaoHeight;
    private int id;
    private int type;
    private int fid;
    private EvaluationInfo evaluationInfo;
    private ImageView imageHead;
    private TextView textNickname;
    private TextView textCollectNum;
    private Button btnBuy;
    private View viewDiBu;
    private EditText editLiuYan;
    private ImageView imageFaSong;
    private ImageView imageShouCang;
    private int viewType;
    private View viewCaiYong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ce_ping_xq);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        id = intent.getIntExtra(Constant.IntentKey.ID, 0);
        viewType = intent.getIntExtra(Constant.IntentKey.TYPE, 0);
    }

    @Override
    protected void findID() {
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        viewBar = findViewById(R.id.viewBar);
        imageHead = (ImageView) findViewById(R.id.imageHead);
        textNickname = (TextView) findViewById(R.id.textNickname);
        textCollectNum = (TextView) findViewById(R.id.textCollectNum);
        btnBuy = (Button) findViewById(R.id.btnBuy);
        viewDiBu = findViewById(R.id.viewDiBu);
        editLiuYan = (EditText) findViewById(R.id.editLiuYan);
        imageFaSong = (ImageView) findViewById(R.id.imageFaSong);
        imageShouCang = (ImageView) findViewById(R.id.imageShouCang);
        viewCaiYong = findViewById(R.id.viewCaiYong);
    }

    @Override
    protected void initViews() {
        viewDiBu.setVisibility(View.GONE);
        guangGaoHeight = DpUtils.convertDpToPixel(150, this);
        viewBar.getBackground().mutate().setAlpha(0);
        initRecycler();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.getSwipeToRefresh().setProgressViewOffset(true, 30, 300);
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.line_width), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<EvaluationInfo.DataBean>(CePingXQActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_ceping_pinglun;
                CePingViewHolder cePingViewHolder = new CePingViewHolder(parent, layout);
                cePingViewHolder.setOnPingLunListenert(new OnPingLunListenert() {
                    @Override
                    public void pingLun(int type, int fid, String name) {
                        CePingXQActivity.this.type = type;
                        CePingXQActivity.this.fid = fid;
                        editLiuYan.setText("");
                        editLiuYan.setHint("回复 "+name);
                        editLiuYan.requestFocus();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(editLiuYan,InputMethodManager.SHOW_FORCED);
                    }
                });
                return cePingViewHolder;
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            private TextView textContent;
            private ImageView imageTop;
            private RecyclerArrayAdapter<EvaluationInfo.ImgsBean> adapterImg;
            private EasyRecyclerView recyclerViewImg;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(CePingXQActivity.this).inflate(R.layout.header_ceping, null);
                recyclerViewImg = view.findViewById(R.id.recyclerView);
                imageTop = view.findViewById(R.id.imageTop);
                textContent = view.findViewById(R.id.textContent);
                initImgRecycler();
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (evaluationInfo != null) {
                    GlideApp.with(CePingXQActivity.this)
                            .load(evaluationInfo.getImg_url())
                            .centerCrop()
                            .placeholder(R.mipmap.ic_empty)
                            .into(new SimpleTarget<Drawable>(evaluationInfo.getImg_w(), evaluationInfo.getImg_h()) {
                                @Override
                                public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                    imageTop.setImageDrawable(resource);
                                }
                            });
                    textContent.setText(evaluationInfo.getTitle());
                    List<EvaluationInfo.ImgsBean> imgsBeanList = evaluationInfo.getImgs();
                    adapterImg.clear();
                    adapterImg.addAll(imgsBeanList);
                }
            }

            /**
             * 初始化recyclerview
             */
            private void initImgRecycler() {
                recyclerViewImg.setLayoutManager(new LinearLayoutManager(CePingXQActivity.this));
                recyclerViewImg.setRefreshingColorResources(R.color.basic_color);
                recyclerViewImg.setAdapterWithProgress(adapterImg = new RecyclerArrayAdapter<EvaluationInfo.ImgsBean>(CePingXQActivity.this) {
                    @Override
                    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                        int layout = R.layout.item_ceping_img;
                        return new CePingXQImgViewHolder(parent, layout);
                    }
                });
            }
        });
        recyclerView.setRefreshListener(this);
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.viewShouCang).setOnClickListener(this);
        findViewById(R.id.viewCaiYong).setOnClickListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int scrollY = RecycleViewDistancaUtil.getDistance(recyclerView, 0);
                if (scrollY <= guangGaoHeight - viewBar.getHeight() && scrollY >= 0) {
                    float percent = (float) scrollY / (guangGaoHeight - viewBar.getHeight());
                    viewBar.getBackground().mutate().setAlpha((int) (255f * percent));
                } else {
                    viewBar.getBackground().mutate().setAlpha(255);
                }
            }
        });
        imageFaSong.setOnClickListener(this);
        btnBuy.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        onRefresh();
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
        params.put("type", String.valueOf(2));
        return new OkObject(params, url);
    }

    /**
     * 收藏
     */
    private void shouCang() {
        showLoadingDialog();
        ApiClient.post(CePingXQActivity.this, getSCOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ChanPinXQActivity--onSuccess", s + "");
                try {
                    GoodsCollect goodsCollect = GsonUtils.parseJSON(s, GoodsCollect.class);
                    Toast.makeText(CePingXQActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                    if (goodsCollect.getStatus() == 1) {
                        evaluationInfo.setIsc(1);
                        evaluationInfo.setCollectNum(goodsCollect.getCollectNum());
                        textCollectNum.setText("收藏(" + goodsCollect.getCollectNum()+ ")");
                        imageShouCang.setImageResource(R.mipmap.dianzan_shixin);
                    } else if (goodsCollect.getStatus() == 3) {
                        MyDialog.showReLoginDialog(CePingXQActivity.this);
                    } else {
                        Toast.makeText(CePingXQActivity.this, goodsCollect.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(CePingXQActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(CePingXQActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 取消收藏
     */
    private void quXiaoSC() {
        String url = Constant.HOST + Constant.Url.GOODS_CANCLECOLLECT;
        ShouCangShanChu shouCangShanChu = new ShouCangShanChu(1, "android", userInfo.getUid(), tokenTime, id, 2);
        showLoadingDialog();
        ApiClient.postJson(CePingXQActivity.this, url, GsonUtils.parseObject(shouCangShanChu), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ChanPinXQActivity--onSuccess", s + "");
                try {
                    GoodsCollect goodsCollect = GsonUtils.parseJSON(s, GoodsCollect.class);
                    if (goodsCollect.getStatus() == 1) {
                        Toast.makeText(CePingXQActivity.this, "取消收藏", Toast.LENGTH_SHORT).show();
                        evaluationInfo.setIsc(0);
                        evaluationInfo.setCollectNum(goodsCollect.getCollectNum());
                        textCollectNum.setText("收藏(" + goodsCollect.getCollectNum()+ ")");
                        imageShouCang.setImageResource(R.mipmap.dinazan);
                    } else if (goodsCollect.getStatus() == 3) {
                        MyDialog.showReLoginDialog(CePingXQActivity.this);
                    } else {
                        Toast.makeText(CePingXQActivity.this, goodsCollect.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(CePingXQActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(CePingXQActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBuy:
                Intent intent = new Intent();
                intent.setClass(this, ChanPinXQActivity.class);
                intent.putExtra(Constant.IntentKey.ID, evaluationInfo.getGoods_id());
                startActivity(intent);
                break;
            case R.id.viewCaiYong:
                if (isLogin){
//                    Intent intent = new Intent();
//                    intent.putExtra(Constant.IntentKey.OGID,)
//                    intent.setClass(this, LiJiCePingActivity.class);
//                    startActivity(intent);
                }else {
                    ToLoginActivity.toLoginActivity(this);
                }
                break;
            case R.id.viewShouCang:
                if (isLogin){
                    if (evaluationInfo.getIsc()==1){
                        quXiaoSC();
                    }else {
                        shouCang();
                    }
                }else {
                    ToLoginActivity.toLoginActivity(this);
                }
                break;
            case R.id.imageFaSong:
                if (isLogin){
                    if (TextUtils.isEmpty(editLiuYan.getText().toString().trim())) {
                        Toast.makeText(CePingXQActivity.this, "请输入留言信息", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    liuYan();
                }else {
                    ToLoginActivity.toLoginActivity(this);
                }
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
    private OkObject getPLOkObject() {
        String url = Constant.HOST + Constant.Url.EVALUATION_MSGSUBMIT;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("id", String.valueOf(id));
        params.put("type", String.valueOf(type));
        params.put("fid", String.valueOf(fid));
        params.put("content", editLiuYan.getText().toString().trim());
        return new OkObject(params, url);
    }

    /**
     * 留言
     */
    private void liuYan() {
        showLoadingDialog();
        ApiClient.post(CePingXQActivity.this, getPLOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("CePingXQActivity--onSuccess", s + "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus() == 1) {
                        editLiuYan.setText("");
                        editLiuYan.setHint("请输入您的留言");
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm != null) {
                            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                                    0);
                        }
                        onRefresh();
                    } else if (simpleInfo.getStatus() == 3) {
                        MyDialog.showReLoginDialog(CePingXQActivity.this);
                    } else {
                        Toast.makeText(CePingXQActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(CePingXQActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(CePingXQActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.EVALUATION_INFO;
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
                LogUtil.LogShitou("", s);
                try {
                    evaluationInfo = GsonUtils.parseJSON(s, EvaluationInfo.class);
                    if (evaluationInfo.getStatus() == 1) {
                        viewDiBu.setVisibility(View.VISIBLE);
                        textNickname.setText(evaluationInfo.getNickname());
                        textCollectNum.setText("收藏(" + evaluationInfo.getCollectNum() + ")");
                        btnBuy.setText(evaluationInfo.getBtnDes() + " >");
                        GlideApp.with(CePingXQActivity.this)
                                .load(evaluationInfo.getHeadimg())
                                .centerCrop()
                                .circleCrop()
                                .placeholder(R.mipmap.ic_empty)
                                .into(imageHead);
                        List<EvaluationInfo.DataBean> dataBeanList = evaluationInfo.getData();
                        adapter.clear();
                        adapter.addAll(dataBeanList);
                        if (evaluationInfo.getIsc()==1){
                            imageShouCang.setImageResource(R.mipmap.dianzan_shixin);
                        }else {
                            imageShouCang.setImageResource(R.mipmap.dinazan);
                        }
                        if (viewType==0){
                            viewCaiYong.setVisibility(View.GONE);
                        }
                    } else if (evaluationInfo.getStatus() == 3) {
                        MyDialog.showReLoginDialog(CePingXQActivity.this);
                    } else {
                        showError(evaluationInfo.getInfo());
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
                    View viewLoader = LayoutInflater.from(CePingXQActivity.this).inflate(R.layout.view_loaderror, null);
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
