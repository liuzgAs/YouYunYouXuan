package com.vip.uyux.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.vip.uyux.adapter.BannerAdapter;
import com.vip.uyux.base.ZjbBaseFragment;
import com.vip.uyux.provider.DataProvider;
import com.vip.uyux.util.BannerSettingUtil;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.util.ScreenUtils;
import com.vip.uyux.viewholder.IndexBannerImgHolderView;
import com.vip.uyux.viewholder.IndexItemViewHolder;
import com.vip.uyux.viewholder.IndexViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShouYeFragment extends ZjbBaseFragment implements SwipeRefreshLayout.OnRefreshListener {


    private View mInflate;
    private View viewBar;
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Integer> adapter;

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
        layoutParams.height =ScreenUtils.getStatusBarHeight(getActivity())+(int)DpUtils.convertDpToPixel(50,getActivity());
        viewBar.setLayoutParams(layoutParams);
        initRecycler();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Integer>(getActivity()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_index;
                return new IndexViewHolder(parent, layout);
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            private View viewViewPager;
            private RecyclerArrayAdapter<Integer> adapterZiYin;
            private EasyRecyclerView recyclerZiYinView;
            private TextView textZhiShiQi;
            private ConvenientBanner banner;
            private List<Integer> bannerBeanList = new ArrayList<>();
            private ViewPager id_viewpager;
            private PageIndicatorView mPageIndicatorView;
            private List<Integer> stringList = new ArrayList<>();

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.header_index, null);
                bannerBeanList.add(R.mipmap.shangpin);
                bannerBeanList.add(R.mipmap.shangpin);
                bannerBeanList.add(R.mipmap.shangpin);
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
                        textZhiShiQi.setText((position + 1) + "/" + bannerBeanList.size());
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                recyclerZiYinView = view.findViewById(R.id.recyclerView);
                initZiYinRecycler();
                id_viewpager = view.findViewById(R.id.id_viewpager);
                new BannerSettingUtil(id_viewpager,(int)getActivity().getResources().getDimension(R.dimen.leftAndRight),false).set();
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
                stringList.add(R.mipmap.viewpagerindex);
                stringList.add(R.mipmap.viewpagerindex);
                stringList.add(R.mipmap.viewpagerindex);
                stringList.add(R.mipmap.viewpagerindex);
                viewViewPager = view.findViewById(R.id.viewViewPager);
                return view;
            }

            /**
             * 初始化recyclerview
             */
            private void initZiYinRecycler() {
                recyclerZiYinView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                recyclerZiYinView.setAdapter(adapterZiYin = new RecyclerArrayAdapter<Integer>(getContext()) {

                    @Override
                    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                        int layout = R.layout.item_ziyin;
                        return new IndexItemViewHolder(parent, layout);
                    }
                });
                SpaceDecoration spaceDecoration = new SpaceDecoration((int) DpUtils.convertDpToPixel(12, getContext()));
                spaceDecoration.setPaddingEdgeSide(false);
                recyclerZiYinView.addItemDecoration(spaceDecoration);
                adapterZiYin.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
//                Intent intent = new Intent();
//                intent.setClass(getContext(), CheLiangXQActivity.class);
//                intent.putExtra(Constant.IntentKey.ID,data.getCar().get(position).getId());
//                getContext().startActivity(intent);
                    }
                });
            }

            @Override
            public void onBindView(View headerView) {
                if (bannerBeanList != null) {
                    if (bannerBeanList.size() > 0) {
                        LogUtil.LogShitou("CheLiangXQActivity--bannerBeanList", "" + bannerBeanList.size());
                        banner.setPages(new CBViewHolderCreator() {
                            @Override
                            public Object createHolder() {
                                return new IndexBannerImgHolderView(bannerBeanList);
                            }
                        }, bannerBeanList);
                    } else {
                        textZhiShiQi.setText("0/0");
                    }
                } else {
                    textZhiShiQi.setText("0/0");
                }
                adapterZiYin.clear();
                adapterZiYin.addAll(DataProvider.getPersonList(1));
                if (stringList != null) {
                    if (stringList.size() > 0) {
                        viewViewPager.setVisibility(View.VISIBLE);
                        id_viewpager.setAdapter(new BannerAdapter(getActivity(), stringList));
                        id_viewpager.setCurrentItem(50);
                    } else {
                        viewViewPager.setVisibility(View.GONE);
                    }
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

    int page = 1;

    @Override
    public void onRefresh() {
        adapter.clear();
        adapter.addAll(DataProvider.getPersonList(page));
    }
}
