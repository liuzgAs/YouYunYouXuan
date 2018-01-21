package com.vip.uyux.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;
import com.vip.uyux.R;
import com.vip.uyux.activity.ChanPinXQActivity;
import com.vip.uyux.adapter.BannerAdapter;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseFragment;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.IndexHome;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.provider.DataProvider;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.BannerSettingUtil;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.util.ScreenUtils;
import com.vip.uyux.viewholder.IndexBannerImgHolderView;
import com.vip.uyux.viewholder.IndexViewHolder;
import com.vip.uyux.viewholder.IndexZiYinViewHolder;

import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShouYeFragment extends ZjbBaseFragment implements SwipeRefreshLayout.OnRefreshListener {


    private View mInflate;
    private View viewBar;
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<IndexHome.DataBean> adapter;
    private List<IndexHome.BannerBean> bannerList;
    private List<IndexHome.Banner2Bean> banner2BeanList;
    private String num1;
    private String num2;
    private String num3;
    private String num4;
    private List<IndexHome.RecomBean> recomBeanList;

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
    }

    @Override
    protected void initViews() {
        ViewGroup.LayoutParams layoutParams = viewBar.getLayoutParams();
        layoutParams.height = ScreenUtils.getStatusBarHeight(getActivity()) + (int) DpUtils.convertDpToPixel(50, getActivity());
        viewBar.setLayoutParams(layoutParams);
        initRecycler();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<IndexHome.DataBean>(getActivity()) {
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
            private RecyclerArrayAdapter<IndexHome.RecomBean> adapterZiYin;
            private EasyRecyclerView recyclerZiYinView;
            private TextView textZhiShiQi;
            private ConvenientBanner banner;
            private ViewPager id_viewpager;
            private PageIndicatorView mPageIndicatorView;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.header_index, null);
                banner = view.findViewById(R.id.banner);
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
                new BannerSettingUtil(id_viewpager, (int) getActivity().getResources().getDimension(R.dimen.leftAndRight), false).set();
                mPageIndicatorView = view.findViewById(R.id.pageIndicatorView);
                mPageIndicatorView.setAnimationType(AnimationType.WORM);
                mPageIndicatorView.setCount(3);
                id_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        mPageIndicatorView.setSelection(position % 3);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                viewViewPager = view.findViewById(R.id.viewViewPager);
                viewNum = view.findViewById(R.id.viewNum);
                viewNum2 = view.findViewById(R.id.viewNum2);
                textNum3 = view.findViewById(R.id.textNum3);
                textNum4 = view.findViewById(R.id.textNum4);
                return view;
            }

            /**
             * 初始化recyclerview
             */
            private void initZiYinRecycler() {
                recyclerZiYinView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                recyclerZiYinView.setAdapter(adapterZiYin = new RecyclerArrayAdapter<IndexHome.RecomBean>(getContext()) {

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
                        intent.setClass(getActivity(), ChanPinXQActivity.class);
                        getContext().startActivity(intent);
                    }
                });
            }

            @Override
            public void onBindView(View headerView) {
                if (bannerList != null) {
                    if (bannerList.size() > 0) {
                        LogUtil.LogShitou("CheLiangXQActivity--bannerBeanList", "" + bannerList.size());
                        banner.setPages(new CBViewHolderCreator() {
                            @Override
                            public Object createHolder() {
                                return new IndexBannerImgHolderView(bannerList);
                            }
                        }, bannerList);
                    } else {
                        textZhiShiQi.setText("0/0");
                    }
                } else {
                    textZhiShiQi.setText("0/0");
                }
                if (recomBeanList!=null){
                    adapterZiYin.clear();
                    adapterZiYin.addAll(recomBeanList);
                }else {

                }
                if (banner2BeanList != null) {
                    if (banner2BeanList.size() > 0) {
                        viewViewPager.setVisibility(View.VISIBLE);
                        id_viewpager.setAdapter(new BannerAdapter(getActivity(), banner2BeanList));
                        id_viewpager.setCurrentItem(50);
                    } else {
                        viewViewPager.setVisibility(View.GONE);
                    }
                }
                LogUtil.LogShitou("ShouYeFragment--onBindView", "" + num1);
                if (!TextUtils.isEmpty(num1)) {
                    viewNum.removeAllViews();
                    String[] split = num1.split("");
                    for (int i = 1; i < split.length; i++) {
                        LogUtil.LogShitou("ShouYeFragment--onBindView", "" + split[i]);
                        TextView viewNum1 = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.index_num, null);
                        viewNum1.setText(split[i]);
                        viewNum.addView(viewNum1);
                    }
                }
                if (!TextUtils.isEmpty(num2)) {
                    viewNum2.removeAllViews();
                    String[] split = num2.split("");
                    for (int i = 1; i < split.length; i++) {
                        LogUtil.LogShitou("ShouYeFragment--onBindView", "" + split[i]);
                        TextView viewNum1 = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.index_num, null);
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
        ApiClient.post(getActivity(), getOkObject(), new ApiClient.CallBack() {
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
                        List<IndexHome.DataBean> dataBeanList = indexHome.getData();
                        adapter.clear();
                        adapter.addAll(dataBeanList);
                    } else if (indexHome.getStatus() == 3) {
                        MyDialog.showReLoginDialog(getActivity());
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
                    View viewLoader = LayoutInflater.from(getActivity()).inflate(R.layout.view_loaderror, null);
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
