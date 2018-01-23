package com.vip.uyux.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.vip.uyux.R;
import com.vip.uyux.activity.SouSuoActivity;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseFragment;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.IndexCate;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GlideApp;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.util.ScreenUtils;
import com.vip.uyux.viewholder.AllViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class FenLeiFragment extends ZjbBaseFragment implements View.OnClickListener {
    private VerticalTabLayout verticalTabLayout;
    private List<String> tabString = new ArrayList<>();
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<IndexCate.DataBean.ListBean> adapter;
    private int id = 0;
    private String advTop;
    /**
     * 是否点击tablayout刷新
     */
    private boolean isSelect = false;
    private View mInflate;
    private View viewBar;
    private List<IndexCate.DataBean> dataBeanList;

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
        verticalTabLayout =  mInflate.findViewById(R.id.verticalTabLayout);
        recyclerView =  mInflate.findViewById(R.id.recyclerView);
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
                advTop = dataBeanList.get(position).getImg();
                adapter.clear();
//                recyclerView.showProgress();
                adapter.addAll(dataBeanList.get(position).getList());
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });
        mInflate.findViewById(R.id.viewSearch).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    private void initRecycle() {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.grid), 0, 0);
//        itemDecoration.setDrawLastItem(false);
//        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<IndexCate.DataBean.ListBean>(getActivity()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_grid_fenlei;
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
                            .into(imageImg);
                }
            }
        });
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.INDEX_CATE;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime",tokenTime);
        }
        return new OkObject(params, url);
    }

    public void onRefresh() {
        ApiClient.post(getActivity(), getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("分类", s);
                try {
                    IndexCate indexCate = GsonUtils.parseJSON(s, IndexCate.class);
                    if (indexCate.getStatus() == 1) {
                        dataBeanList = indexCate.getData();
                        verticalTabLayout.setVisibility(View.VISIBLE);
                        verticalTabLayout.setTabAdapter(new TabAdapter() {
                            @Override
                            public int getCount() {
                                return dataBeanList.size();
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
                                        .setContent(dataBeanList.get(position).getName())
                                        .setTextColor(getResources().getColor(R.color.basic_color), getResources().getColor(R.color.light_black))
                                        .setTextSize(13)
                                        .build();
                            }

                            @Override
                            public int getBackground(int position) {
                                return 0;
                            }
                        });
                        if (dataBeanList.size()>0){
                            adapter.clear();
                            advTop = dataBeanList.get(0).getImg();
                            List<IndexCate.DataBean.ListBean> listBeanList = dataBeanList.get(0).getList();
                            adapter.addAll(listBeanList);
                        }
                    } else if (indexCate.getStatus()== 3) {
                        MyDialog.showReLoginDialog(getActivity());
                    } else {
                        showError(indexCate.getInfo());
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

    protected int dp2px(float dp) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.viewSearch:
                Intent intent = new Intent();
                intent.setClass(getActivity(), SouSuoActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
