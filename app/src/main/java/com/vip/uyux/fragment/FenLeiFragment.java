package com.vip.uyux.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.vip.uyux.R;
import com.vip.uyux.base.ZjbBaseFragment;
import com.vip.uyux.provider.DataProvider;
import com.vip.uyux.util.GlideApp;
import com.vip.uyux.util.ScreenUtils;
import com.vip.uyux.viewholder.AllViewHolder;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class FenLeiFragment extends ZjbBaseFragment {
    private VerticalTabLayout verticalTabLayout;
    private List<String> tabString = new ArrayList<>();
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<List<Integer>> adapter;
    private int id = 0;
    private Integer advTop;
    /**
     * 是否点击tablayout刷新
     */
    private boolean isSelect = false;
    private View mInflate;
    private View viewBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.activity_fen_lei, container, false);
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
    protected void initSP() {

    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void findID() {
        verticalTabLayout = (VerticalTabLayout) mInflate.findViewById(R.id.verticalTabLayout);
        recyclerView = (EasyRecyclerView) mInflate.findViewById(R.id.recyclerView);
        viewBar = mInflate.findViewById(R.id.viewBar);
    }

    @Override
    protected void initViews() {
        ViewGroup.LayoutParams layoutParams = viewBar.getLayoutParams();
        layoutParams.height = ScreenUtils.getStatusBarHeight(getActivity())+(int) getActivity().getResources().getDimension(R.dimen.titleHeight);
        viewBar.setLayoutParams(layoutParams);
        verticalTabLayout.setVisibility(View.GONE);
        tabString.add("推荐分类");
        tabString.add("美食");
        tabString.add("酒店");
        tabString.add("休闲娱乐");
        tabString.add("生活服务");
        tabString.add("家具建材");
        tabString.add("旅游");
        tabString.add("保险");
        tabString.add("教育");
        tabString.add("房产");
        tabString.add("汽车");
        tabString.add("服装");
        tabString.add("数码电器");
        tabString.add("推荐分类");
        tabString.add("美食");
        tabString.add("酒店");
        tabString.add("休闲娱乐");
        tabString.add("生活服务");
        tabString.add("家具建材");
        tabString.add("旅游");
        tabString.add("保险");
        tabString.add("教育");
        tabString.add("房产");
        tabString.add("汽车");
        tabString.add("服装");
        tabString.add("数码电器");
        verticalTabLayout.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return tabString.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return new TabView.TabIcon.Builder()
                        .setIcon(R.drawable.divider_tablayout, R.drawable.divider_tablayout)
                        .setIconGravity(Gravity.BOTTOM)
                        .setIconMargin(dp2px(0))
                        .setIconSize(dp2px(100), dp2px(1))
                        .build();
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new TabView.TabTitle.Builder()
                        .setContent(tabString.get(position))
                        .setTextColor(getResources().getColor(R.color.basic_color), getResources().getColor(R.color.light_black))
                        .setTextSize(13)
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return 0;
            }
        });
        initRecycle();
    }

    @Override
    protected void setListeners() {
        verticalTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                isSelect =true;
                adapter.clear();
                recyclerView.showProgress();
                onRefresh();
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    private void initRecycle() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<List<Integer>>(getActivity()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_fenlei;
                return new AllViewHolder(parent, layout);
            }

        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {

            private ImageView imageImg;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.head_fenlei, null);
                imageImg = (ImageView) view.findViewById(R.id.imageImg);
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (advTop!=null){
                    GlideApp.with(getActivity())
                            .asBitmap()
                            .load(R.mipmap.fenlei_head)
                            .placeholder(R.mipmap.ic_empty)
                            .into(imageImg);
                }
            }
        });
    }


    public void onRefresh() {
        recyclerView.showProgress();
        if (!isSelect){
            verticalTabLayout.setVisibility(View.VISIBLE);
            verticalTabLayout.setTabAdapter(new TabAdapter() {
                @Override
                public int getCount() {
                    return tabString.size();
                }

                @Override
                public ITabView.TabBadge getBadge(int position) {
                    return null;
                }

                @Override
                public ITabView.TabIcon getIcon(int position) {
                    return new TabView.TabIcon.Builder()
                            .setIcon(R.drawable.divider_tablayout, R.drawable.divider_tablayout)
                            .setIconGravity(Gravity.BOTTOM)
                            .setIconMargin(dp2px(0))
                            .setIconSize(dp2px(100), dp2px(1))
                            .build();
                }

                @Override
                public ITabView.TabTitle getTitle(int position) {
                    return new TabView.TabTitle.Builder()
                            .setContent(tabString.get(position))
                            .setTextColor(getResources().getColor(R.color.basic_color), getResources().getColor(R.color.light_black))
                            .setTextSize(13)
                            .build();
                }

                @Override
                public int getBackground(int position) {
                    return 0;
                }
            });
        }
        isSelect=true;
        adapter.clear();
        adapter.add(DataProvider.getPersonList(1));
        adapter.add(DataProvider.getPersonList(1));
        adapter.notifyDataSetChanged();
    }

    protected int dp2px(float dp) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
