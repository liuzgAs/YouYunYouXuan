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

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;
import com.vip.uyux.R;
import com.vip.uyux.activity.CePingXQActivity;
import com.vip.uyux.adapter.BannerTuiJianAdapter;
import com.vip.uyux.base.ZjbBaseFragment;
import com.vip.uyux.provider.DataProvider;
import com.vip.uyux.util.BannerSettingUtil;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;
import com.vip.uyux.util.ScreenUtils;
import com.vip.uyux.viewholder.TuiJianViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TuiJianFragment extends ZjbBaseFragment implements SwipeRefreshLayout.OnRefreshListener {


    private View mInflate;
    private ImageView imageBack;
    private View viewBar;
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Integer> adapter;

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
        layoutParams.height =ScreenUtils.getStatusBarHeight(getActivity())+(int) getActivity().getResources().getDimension(R.dimen.titleHeight);
        viewBar.setLayoutParams(layoutParams);
        initRecycler();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.line_width), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Integer>(getActivity()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_tuijian;
                return new TuiJianViewHolder(parent, layout, viewType);
            }

            @Override
            public int getViewType(int position) {
                return getItem(position);
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {

            private ImageView image0000;
            private ImageView image0003;
            private ImageView image0004;
            private ImageView image0005;
            private ImageView image0300;
            private ImageView image0400;
            private ViewPager id_viewpager;
            private PageIndicatorView mPageIndicatorView;
            private List<Integer> stringList = new ArrayList<>();
            private View viewViewPager;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.header_tuijian, null);
                image0000 = view.findViewById(R.id.image0000);
                image0003 = view.findViewById(R.id.image0003);
                image0004 = view.findViewById(R.id.image0004);
                image0005 = view.findViewById(R.id.image0005);
                image0300 = view.findViewById(R.id.image0300);
                image0400 = view.findViewById(R.id.image0400);
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
                stringList.add(R.mipmap.viewpagerindex);
                stringList.add(R.mipmap.viewpagerindex);
                stringList.add(R.mipmap.viewpagerindex);
                stringList.add(R.mipmap.viewpagerindex);
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                GlideApp.with(getActivity())
                        .asBitmap()
                        .circleCrop()
                        .load(R.mipmap.tuijiantouxiang)
                        .placeholder(R.mipmap.ic_empty)
                        .into(image0000);
                GlideApp.with(getActivity())
                        .asBitmap()
                        .circleCrop()
                        .load(R.mipmap.tuijiantouxiang)
                        .placeholder(R.mipmap.ic_empty)
                        .into(image0400);
                GlideApp.with(getContext())
                        .asBitmap()
                        .centerCrop()
                        .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(4, getContext())))
                        .load(R.mipmap.youxuantuijian_tuijian)
                        .into(image0003);
                GlideApp.with(getContext())
                        .asBitmap()
                        .centerCrop()
                        .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(4, getContext())))
                        .load(R.mipmap.youxuantuijian_tuijian)
                        .into(image0004);
                GlideApp.with(getContext())
                        .asBitmap()
                        .centerCrop()
                        .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(4, getContext())))
                        .load(R.mipmap.youxuantuijian_tuijian)
                        .into(image0005);
                GlideApp.with(getContext())
                        .asBitmap()
                        .centerCrop()
                        .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(12, getContext())))
                        .load(R.mipmap.viewpagerindex)
                        .into(image0300);
                if (stringList != null) {
                    if (stringList.size() > 0) {
                        viewViewPager.setVisibility(View.VISIBLE);
                        id_viewpager.setAdapter(new BannerTuiJianAdapter(getActivity(), stringList));
                        id_viewpager.setCurrentItem(50);
                    } else {
                        viewViewPager.setVisibility(View.GONE);
                    }
                }
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                page++;
                adapter.addAll(DataProvider.getPersonList(page));
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
                if (adapter.getViewType(position)==1){
//                    Intent intent = new Intent();
//                    intent.setClass(getActivity(), ChanPinXQActivity.class);
//                    startActivity(intent);
                }else {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), CePingXQActivity.class);
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

    @Override
    public void onRefresh() {
        page = 1;
        adapter.clear();
        adapter.addAll(DataProvider.getPersonList(page));
    }
}
