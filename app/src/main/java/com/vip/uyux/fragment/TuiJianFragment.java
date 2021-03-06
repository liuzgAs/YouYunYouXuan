package com.vip.uyux.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.rd.PageIndicatorView;
import com.vip.uyux.R;
import com.vip.uyux.activity.CePingXQActivity;
import com.vip.uyux.activity.WebHaoWuActivity;
import com.vip.uyux.adapter.BannerTuiJianAdapter;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseFragment;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.AdvsBean;
import com.vip.uyux.model.GoodsCollect;
import com.vip.uyux.model.IndexRecom;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.model.ShouCangShanChu;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.util.ScreenUtils;
import com.vip.uyux.viewholder.TuiJianViewHolder;
import com.vip.uyux.viewholder.TuiJianViewXHolder;

import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TuiJianFragment extends ZjbBaseFragment implements SwipeRefreshLayout.OnRefreshListener {


    private View mInflate;
    private ImageView imageBack;
    private View viewBar;
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<IndexRecom.DataBean> adapter;
    private List<AdvsBean> bannerBeanList;
    private List<AdvsBean> banner2BeanList;
    private List<IndexRecom.EvaluationBean> evaluations;

    public TuiJianFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_tui_jian, container, false);
            init();
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mInflate.getParent();
        if (parent != null) {
            parent.removeView(mInflate);
        }
        return mInflate;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void findID() {
        imageBack = mInflate.findViewById(R.id.imageBack);
        viewBar = mInflate.findViewById(R.id.viewBar);
        recyclerView = mInflate.findViewById(R.id.recyclerView);
    }

    @Override
    protected void initViews() {
        ((TextView) mInflate.findViewById(R.id.textViewTitle)).setText("优选推荐");
        imageBack.setVisibility(View.GONE);
        ViewGroup.LayoutParams layoutParams = viewBar.getLayoutParams();
        layoutParams.height = ScreenUtils.getStatusBarHeight(mContext) + (int) mContext.getResources().getDimension(R.dimen.titleHeight);
        viewBar.setLayoutParams(layoutParams);
        initRecycler();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.line_width), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<IndexRecom.DataBean>(mContext) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout;
                switch (viewType) {
                    case 3:
                        layout = R.layout.item_tuijian_x;
                        return new TuiJianViewXHolder(parent, layout);
                    case 4:
                        layout = R.layout.item_tuijian;
                        return new TuiJianViewHolder(parent, layout);
                    default:
                        layout = R.layout.item_tuijian;
                        return new TuiJianViewHolder(parent, layout);
                }
            }

            @Override
            public int getViewType(int position) {
                return getItem(position).getType();
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {

            private PageIndicatorView pageIndicatorView;
            private View viewRecom;
            private View evaluation;
            private ImageView imageImg;
            private TextView textTitle;
            private TextView textprice;
            private TextView textDes;
            private TextView textName;


            //            private ImageView image0000;
//            private ImageView image0003;
//            private ImageView image0004;
//            private ImageView image0005;
            private ImageView image0300;
            //            private ImageView image0400;
            private ViewPager id_viewpager;
            private View viewViewPager;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.header_tuijian, null);
//                image0000 = view.findViewById(R.id.image0000);
//                image0003 = view.findViewById(R.id.image0003);
//                image0004 = view.findViewById(R.id.image0004);
//                image0005 = view.findViewById(R.id.image0005);
                image0300 = view.findViewById(R.id.image0300);
//                image0400 = view.findViewById(R.id.image0400);
                id_viewpager = view.findViewById(R.id.id_viewpager);
                viewViewPager = view.findViewById(R.id.viewViewPager);
                evaluation = view.findViewById(R.id.evaluation);
                imageImg = view.findViewById(R.id.imageImg);
                textTitle = view.findViewById(R.id.textTitle);
                textprice = view.findViewById(R.id.textprice);
                textDes = view.findViewById(R.id.textDes);
                textName = view.findViewById(R.id.textName);

                image0300.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.putExtra(Constant.IntentKey.BEAN, banner2BeanList.get(0));
                        intent.setAction(Constant.BroadcastCode.ADV);
                        mContext.sendBroadcast(intent);
                    }
                });
                evaluation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setClass(mContext, WebHaoWuActivity.class);
                        intent.putExtra(Constant.IntentKey.TITLE, evaluations.get(0).getTitle());
                        intent.putExtra(Constant.IntentKey.URL, evaluations.get(0).getUrl());
                        intent.putExtra(Constant.IntentKey.TYPE, evaluations.get(0));
                        startActivity(intent);
                    }
                });
                viewRecom = view.findViewById(R.id.viewRecom);
                pageIndicatorView = view.findViewById(R.id.pageIndicatorView);
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (bannerBeanList != null) {
                    if (bannerBeanList.size() > 0) {
                        viewViewPager.setVisibility(View.VISIBLE);
                        BannerTuiJianAdapter adapter = new BannerTuiJianAdapter(mContext, bannerBeanList);
                        id_viewpager.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        if (bannerBeanList.size() > 1) {
                            pageIndicatorView.setVisibility(View.VISIBLE);
                        } else {
                            pageIndicatorView.setVisibility(View.GONE);
                        }
                    } else {
                        viewViewPager.setVisibility(View.GONE);
                    }
                }
                if (banner2BeanList != null) {
                    if (banner2BeanList.size() > 0) {
                        GlideApp.with(getContext())
                                .asBitmap()
                                .centerCrop()
                                .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(12, getContext())))
                                .load(banner2BeanList.get(0).getImg())
                                .into(image0300);
                    } else {
                        viewRecom.setVisibility(View.GONE);
                    }
                } else {
                    viewRecom.setVisibility(View.GONE);
                }
                if (evaluations != null) {
                    if (evaluations.size()>0){
                        evaluation.setVisibility(View.VISIBLE);
                        textTitle.setText(evaluations.get(0).getTitle());
                        textprice.setText(evaluations.get(0).getPrice());
                        textDes.setText(evaluations.get(0).getDes());
                        textName.setText(evaluations.get(0).getNickname());
                        GlideApp.with(getContext())
                                .asBitmap()
                                .centerCrop()
                                .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(12, getContext())))
                                .load(evaluations.get(0).getImg())
                                .into(imageImg);
                    }else {
                        evaluation.setVisibility(View.GONE);
                    }
                } else {
                    evaluation.setVisibility(View.GONE);
                }
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                ApiClient.post(mContext, getOkObject(), new ApiClient.CallBack() {
                    @Override
                    public void onSuccess(String s) {
                        LogUtil.LogShitou("DingDanGLActivity--加载更多", s + "");
                        try {
                            page++;
                            IndexRecom indexRecom = GsonUtils.parseJSON(s, IndexRecom.class);
                            int status = indexRecom.getStatus();
                            if (status == 1) {
                                List<IndexRecom.DataBean> dataBeanList = indexRecom.getData();
                                adapter.addAll(dataBeanList);
                            } else if (status == 3) {
                                MyDialog.showReLoginDialog(mContext);
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
                if (adapter.getItem(position).getType() == 4) {
                    Intent intent = new Intent();
                    intent.setClass(mContext, CePingXQActivity.class);
                    intent.putExtra(Constant.IntentKey.ID, adapter.getItem(position).getId());
                    startActivity(intent);
                } else if (adapter.getItem(position).getType() == 3) {
                    Intent intent = new Intent();
                    intent.setClass(mContext, WebHaoWuActivity.class);
                    intent.putExtra(Constant.IntentKey.TITLE, adapter.getItem(position).getTitle());
                    intent.putExtra(Constant.IntentKey.URL, adapter.getItem(position).getUrl());
                    intent.putExtra(Constant.IntentKey.BEAN, adapter.getItem(position));
                    startActivity(intent);
                }
            }
        });
        recyclerView.setRefreshListener(this);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void initData() {
        onRefresh();
    }

    int page = 1;

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.INDEX_RECOM;
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
        ApiClient.post(mContext, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("推荐", s);
                try {
                    page++;
                    IndexRecom indexRecom = GsonUtils.parseJSON(s, IndexRecom.class);
                    if (indexRecom.getStatus() == 1) {
                        bannerBeanList = indexRecom.getBanner();
                        banner2BeanList = indexRecom.getBanner2();
                        evaluations = indexRecom.getEvaluation();
                        List<IndexRecom.DataBean> dataBeanList = indexRecom.getData();
                        adapter.clear();
                        adapter.addAll(dataBeanList);
                    } else if (indexRecom.getStatus() == 3) {
                        MyDialog.showReLoginDialog(mContext);
                    } else {
                        showError(indexRecom.getInfo());
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
                    View viewLoader = LayoutInflater.from(mContext).inflate(R.layout.view_loaderror, null);
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
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getSCOkObject(int position) {
        String url = Constant.HOST + Constant.Url.GOODS_COLLECT;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("item_id", String.valueOf(adapter.getItem(position).getId()));
        params.put("type", String.valueOf(2));
        return new OkObject(params, url);
    }

    /**
     * 收藏
     */
    private void shouCangX(final int position) {
        showLoadingDialog();
        ApiClient.post(mContext, getSCOkObject(position), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ChanPinXQCZActivity--onSuccess", s + "");
                try {
                    GoodsCollect goodsCollect = GsonUtils.parseJSON(s, GoodsCollect.class);
                    Toast.makeText(mContext, "收藏成功", Toast.LENGTH_SHORT).show();
                    if (goodsCollect.getStatus() == 1) {
                        adapter.getItem(position).setIsc(1);
                        adapter.getItem(position).setCollectNum(goodsCollect.getCollectNum());
                        adapter.notifyDataSetChanged();
                    } else if (goodsCollect.getStatus() == 3) {
                        MyDialog.showReLoginDialog(mContext);
                    } else {
                        Toast.makeText(mContext, goodsCollect.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(mContext, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(mContext, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 取消收藏
     */
    private void quXiaoSC(final int position) {
        String url = Constant.HOST + Constant.Url.GOODS_CANCLECOLLECT;
        ShouCangShanChu shouCangShanChu = new ShouCangShanChu(1, "android", userInfo.getUid(), tokenTime, adapter.getItem(position).getId(), 2);
        showLoadingDialog();
        ApiClient.postJson(mContext, url, GsonUtils.parseObject(shouCangShanChu), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ChanPinXQCZActivity--onSuccess", s + "");
                try {
                    GoodsCollect goodsCollect = GsonUtils.parseJSON(s, GoodsCollect.class);
                    if (goodsCollect.getStatus() == 1) {
                        Toast.makeText(mContext, "取消收藏", Toast.LENGTH_SHORT).show();
                        adapter.getItem(position).setIsc(0);
                        adapter.getItem(position).setCollectNum(goodsCollect.getCollectNum());
                        adapter.notifyDataSetChanged();
                    } else if (goodsCollect.getStatus() == 3) {
                        MyDialog.showReLoginDialog(mContext);
                    } else {
                        Toast.makeText(mContext, goodsCollect.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(mContext, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(mContext, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
