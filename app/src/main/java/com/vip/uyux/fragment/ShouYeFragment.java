package com.vip.uyux.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.vip.uyux.R;
import com.vip.uyux.activity.ChanPinLBActivity;
import com.vip.uyux.activity.ChanPinXQCZActivity;
import com.vip.uyux.activity.HaiTaoActivity;
import com.vip.uyux.activity.SouSuoActivity;
import com.vip.uyux.activity.XiaoXiZXActivity;
import com.vip.uyux.adapter.BannerAdapter;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ToLoginActivity;
import com.vip.uyux.base.ZjbBaseFragment;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.AdvsBean;
import com.vip.uyux.model.GoodBean;
import com.vip.uyux.model.IndexHome;
import com.vip.uyux.model.MassageNum;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.util.ScreenUtils;
import com.vip.uyux.viewholder.IndexBannerImgHolderView;
import com.vip.uyux.viewholder.IndexViewHolder;
import com.vip.uyux.viewholder.IndexZiYinViewHolder;

import java.util.HashMap;
import java.util.List;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShouYeFragment extends ZjbBaseFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {


    private View mInflate;
    private View viewBar;
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<IndexHome.DataBean> adapter;
    private List<AdvsBean> bannerList;
    private List<AdvsBean> banner2BeanList;
    private String num1;
    private String num2;
    private String num3;
    private String num4;
    private List<GoodBean> recomBeanList;
    private boolean isScroll = false;
    private float downX;
    private float upX;
    private Badge badge;
    private ImageView imageXiaoXi;
    private BroadcastReceiver reciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case Constant.BroadcastCode.SHUA_XIN_TIPS:
                    setTips();
                    break;
                default:
                    break;
            }
        }
    };
    private List<IndexHome.SeaAmoyBean> seaAmoy;

    public ShouYeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_shou_ye, container, false);
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
        viewBar = mInflate.findViewById(R.id.viewBar);
        recyclerView = mInflate.findViewById(R.id.recyclerView);
        imageXiaoXi = mInflate.findViewById(R.id.imageTip);
    }

    @Override
    protected void initViews() {
        ViewGroup.LayoutParams layoutParams = viewBar.getLayoutParams();
        layoutParams.height = ScreenUtils.getStatusBarHeight(mContext) + (int) DpUtils.convertDpToPixel(50, mContext);
        viewBar.setLayoutParams(layoutParams);
        badge = new QBadgeView(mContext)
                .setBadgeTextColor(Color.WHITE)
                .setBadgeTextSize(10f, true)
                .setBadgeBackgroundColor(getResources().getColor(R.color.basic_color))
                .setBadgeGravity(Gravity.END | Gravity.TOP)
                .setBadgePadding(2f, true)
                .setGravityOffset(2f, 0f, true);
        initRecycler();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.top), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<IndexHome.DataBean>(mContext) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_index;
                return new IndexViewHolder(parent, layout);
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            private TextView textNum3;
            private TextView textNum4;
            private LinearLayout viewNum2;
            private LinearLayout viewNum;
            private View viewViewPager;
            private RecyclerArrayAdapter<GoodBean> adapterZiYin;
            private EasyRecyclerView recyclerZiYinView;
            private TextView textZhiShiQi;
            private ConvenientBanner banner;
            private ViewPager id_viewpager;
            private TextView[] textHaiTaoTitle = new TextView[4];
            private TextView[] textHaiTaoDes = new TextView[4];
            private ImageView[] imageImg1 = new ImageView[4];
            private ImageView[] imageImg2 = new ImageView[4];
            private View[] viewHaiTao = new View[4];

            @Override
            public View onCreateView(ViewGroup parent) {
                LogUtil.LogShitou("ShouYeFragment--onCreateView", "11111111111");
                View view = LayoutInflater.from(mContext).inflate(R.layout.header_index, null);
                int screenWidth = ScreenUtils.getScreenWidth(mContext);
                banner = view.findViewById(R.id.banner);
                ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
                layoutParams.width = screenWidth;
                LogUtil.LogShitou("ShouYeFragment--onCreateView", ""+(int) (578f * (float) screenWidth / 1080f));
                layoutParams.height = (int) (578f * (float) screenWidth / 1080f);
                banner.setLayoutParams(layoutParams);
                banner.setScrollDuration(1000);
                banner.startTurning(3000);
                textZhiShiQi = view.findViewById(R.id.textZhiShiQi);
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
                recyclerZiYinView = view.findViewById(R.id.recyclerView);
                initZiYinRecycler();
                id_viewpager = view.findViewById(R.id.id_viewpager);
//                new BannerSettingUtil(id_viewpager, (int) mContext.getResources().getDimension(R.dimen.leftAndRight), false).set();
                viewViewPager = view.findViewById(R.id.viewViewPager);
                viewNum = view.findViewById(R.id.viewNum);
                viewNum2 = view.findViewById(R.id.viewNum2);
                textNum3 = view.findViewById(R.id.textNum3);
                textNum4 = view.findViewById(R.id.textNum4);
                view.findViewById(R.id.viewIsNew).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setClass(mContext, ChanPinLBActivity.class);
                        intent.putExtra(Constant.IntentKey.TITLE, "本周上新");
                        intent.putExtra(Constant.IntentKey.ISNEW, 1);
                        startActivity(intent);
                    }
                });
                view.findViewById(R.id.viewIsHot).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setClass(mContext, ChanPinLBActivity.class);
                        intent.putExtra(Constant.IntentKey.TITLE, "热卖推荐");
                        intent.putExtra(Constant.IntentKey.ISHOT, 1);
                        startActivity(intent);
                    }
                });
                textHaiTaoTitle[0] = view.findViewById(R.id.textHaiTaoTitle0);
                textHaiTaoTitle[1] = view.findViewById(R.id.textHaiTaoTitle1);
                textHaiTaoTitle[2] = view.findViewById(R.id.textHaiTaoTitle2);
                textHaiTaoTitle[3] = view.findViewById(R.id.textHaiTaoTitle3);
                textHaiTaoDes[0] = view.findViewById(R.id.textHaiTaoDes0);
                textHaiTaoDes[1] = view.findViewById(R.id.textHaiTaoDes1);
                textHaiTaoDes[2] = view.findViewById(R.id.textHaiTaoDes2);
                textHaiTaoDes[3] = view.findViewById(R.id.textHaiTaoDes3);
                imageImg1[0] = view.findViewById(R.id.imageHaiTaoImg0001);
                imageImg1[1] = view.findViewById(R.id.imageHaiTaoImg0101);
                imageImg1[2] = view.findViewById(R.id.imageHaiTaoImg0201);
                imageImg1[3] = view.findViewById(R.id.imageHaiTaoImg0301);
                imageImg2[0] = view.findViewById(R.id.imageHaiTaoImg0002);
                imageImg2[1] = view.findViewById(R.id.imageHaiTaoImg0102);
                imageImg2[2] = view.findViewById(R.id.imageHaiTaoImg0202);
                imageImg2[3] = view.findViewById(R.id.imageHaiTaoImg0302);
                viewHaiTao[0] = view.findViewById(R.id.viewHaiTao0);
                viewHaiTao[1] = view.findViewById(R.id.viewHaiTao1);
                viewHaiTao[2] = view.findViewById(R.id.viewHaiTao2);
                viewHaiTao[3] = view.findViewById(R.id.viewHaiTao3);
                for (int i = 0; i < viewHaiTao.length; i++) {
                    final int finalI = i;
                    viewHaiTao[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent();
                            intent.setClass(mContext, HaiTaoActivity.class);
                            intent.putExtra(Constant.IntentKey.PCATE,seaAmoy.get(finalI).getPcate());
                            startActivity(intent);
                        }
                    });
                }
                return view;
            }

            /**
             * 初始化recyclerview
             */
            private void initZiYinRecycler() {
                recyclerZiYinView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                recyclerZiYinView.setAdapter(adapterZiYin = new RecyclerArrayAdapter<GoodBean>(getContext()) {

                    @Override
                    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                        int layout = R.layout.item_ziyin;
                        return new IndexZiYinViewHolder(parent, layout);
                    }
                });
                SpaceDecoration spaceDecoration = new SpaceDecoration((int) DpUtils.convertDpToPixel(12, getContext()));
                spaceDecoration.setPaddingEdgeSide(false);
                recyclerZiYinView.addItemDecoration(spaceDecoration);
                adapterZiYin.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent();
                        intent.setClass(mContext, ChanPinXQCZActivity.class);
                        intent.putExtra(Constant.IntentKey.ID, adapterZiYin.getItem(position).getId());
                        getContext().startActivity(intent);
                    }
                });
            }

            @Override
            public void onBindView(View headerView) {
                if (bannerList != null) {
                    if (bannerList.size() > 0) {
                        banner.setPages(new CBViewHolderCreator() {
                            @Override
                            public Object createHolder() {
                                return new IndexBannerImgHolderView();
                            }
                        }, bannerList);
                    } else {
                        textZhiShiQi.setText("0/0");
                    }
                } else {
                    textZhiShiQi.setText("0/0");
                }
                if (recomBeanList != null) {
                    adapterZiYin.clear();
                    adapterZiYin.addAll(recomBeanList);
                } else {

                }
                if (banner2BeanList != null) {
                    BannerAdapter adapter = new BannerAdapter(mContext, banner2BeanList);
                    id_viewpager.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                LogUtil.LogShitou("ShouYeFragment--onBindView", "" + num1);
                if (!TextUtils.isEmpty(num1)) {
                    viewNum.removeAllViews();
                    String[] split = num1.split("");
                    for (int i = 1; i < split.length; i++) {
                        TextView viewNum1 = (TextView) LayoutInflater.from(mContext).inflate(R.layout.index_num, null);
                        viewNum1.setText(split[i]);
                        viewNum.addView(viewNum1);
                    }
                }
                if (!TextUtils.isEmpty(num2)) {
                    viewNum2.removeAllViews();
                    String[] split = num2.split("");
                    for (int i = 1; i < split.length; i++) {
                        TextView viewNum1 = (TextView) LayoutInflater.from(mContext).inflate(R.layout.index_num, null);
                        viewNum1.setText(split[i]);
                        viewNum2.addView(viewNum1);
                    }
                }
                if (!TextUtils.isEmpty(num3)) {
                    textNum3.setText("共" + num3 + "件上架新商品");
                }
                if (!TextUtils.isEmpty(num4)) {
                    textNum4.setText("共" + num4 + "件爆款推荐");
                }
                if (seaAmoy != null) {
                    if (seaAmoy.size() >= 4) {
                        for (int i = 0; i < seaAmoy.size(); i++) {
                            textHaiTaoTitle[i].setText(seaAmoy.get(i).getTitle());
                            textHaiTaoDes[i].setText(seaAmoy.get(i).getDes());
                            GlideApp.with(ShouYeFragment.this)
                                    .load(seaAmoy.get(i).getImg1())
                                    .centerCrop()
                                    .placeholder(R.mipmap.ic_empty)
                                    .transition(new DrawableTransitionOptions().crossFade(500))
                                    .into(imageImg1[i]);
                            GlideApp.with(ShouYeFragment.this)
                                    .load(seaAmoy.get(i).getImg2())
                                    .centerCrop()
                                    .placeholder(R.mipmap.ic_empty)
                                    .transition(new DrawableTransitionOptions().crossFade(500))
                                    .into(imageImg2[i]);
                        }
                    }
                }
            }
        });
        adapter.addFooter(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.view_nomore_shouye, null);
                return view;
            }

            @Override
            public void onBindView(View headerView) {

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
        mInflate.findViewById(R.id.imageSearch).setOnClickListener(this);
        mInflate.findViewById(R.id.imageTip).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        onRefresh();
        setTips();
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getTipOkObject() {
        String url = Constant.HOST + Constant.Url.MASSAGE_NUM;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        return new OkObject(params, url);
    }

    /**
     * 显示消息数
     */
    private void setTips() {
        ApiClient.post(mContext, getTipOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("WoDeFragment--onSuccess", s + "");
                try {
                    MassageNum massageNum = GsonUtils.parseJSON(s, MassageNum.class);
                    if (massageNum.getStatus() == 1) {
                        badge.setBadgeNumber(massageNum.getNum()).bindTarget(imageXiaoXi);
                    } else if (massageNum.getStatus() == 3) {
                        MyDialog.showReLoginDialog(mContext);
                    } else {
                        Toast.makeText(mContext, massageNum.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(mContext, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                Toast.makeText(mContext, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.INDEX_HOME;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        return new OkObject(params, url);
    }

    @Override
    public void onRefresh() {
        ApiClient.post(mContext, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("首页", s);
                try {
                    IndexHome indexHome = GsonUtils.parseJSON(s, IndexHome.class);
                    if (indexHome.getStatus() == 1) {
                        bannerList = indexHome.getBanner();
                        banner2BeanList = indexHome.getBanner2();
                        num1 = indexHome.getNum1();
                        num2 = indexHome.getNum2();
                        num3 = indexHome.getNum3();
                        num4 = indexHome.getNum4();
                        recomBeanList = indexHome.getRecom();
                        seaAmoy = indexHome.getSeaAmoy();
                        List<IndexHome.DataBean> dataBeanList = indexHome.getData();
                        adapter.clear();
                        adapter.addAll(dataBeanList);
                    } else if (indexHome.getStatus() == 3) {
                        MyDialog.showReLoginDialog(mContext);
                    } else {
                        showError(indexHome.getInfo());
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

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.imageTip:
                if (isLogin) {
                    intent.setClass(mContext, XiaoXiZXActivity.class);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(mContext);
                }
                break;
            case R.id.imageSearch:
                intent.setClass(mContext, SouSuoActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.BroadcastCode.SHUA_XIN_TIPS);
        getActivity().registerReceiver(reciver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(reciver);
    }
}
