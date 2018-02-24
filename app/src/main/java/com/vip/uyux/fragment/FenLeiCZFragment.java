package com.vip.uyux.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vip.uyux.R;
import com.vip.uyux.activity.SouSuoActivity;
import com.vip.uyux.activity.XiaoXiZXActivity;
import com.vip.uyux.adapter.FenLeiPageAdapter;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ToLoginActivity;
import com.vip.uyux.base.ZjbBaseFragment;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.customview.DefaultTransformer;
import com.vip.uyux.customview.VerticalViewPager;
import com.vip.uyux.model.IndexCate;
import com.vip.uyux.model.MassageNum;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.util.ScreenUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FenLeiCZFragment extends ZjbBaseFragment implements View.OnClickListener {
    private VerticalTabLayout verticalTabLayout;
    private List<String> tabString = new ArrayList<>();
    private int id = 0;
    /**
     * 是否点击tablayout刷新
     */
    private View mInflate;
    private View viewBar;
    private List<IndexCate.DataBean> dataBeanList;
    private String name;
    private TextView textBgDes;
    private VerticalViewPager viewPager;
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

    public FenLeiCZFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_fen_lei_cz, container, false);
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
        verticalTabLayout = mInflate.findViewById(R.id.verticalTabLayout);
        viewBar = mInflate.findViewById(R.id.viewBar);
        textBgDes = mInflate.findViewById(R.id.textBgDes);
        viewPager = mInflate.findViewById(R.id.viewPager);
        imageXiaoXi = mInflate.findViewById(R.id.imageXiaoXi);
    }

    @Override
    protected void initViews() {
        ViewGroup.LayoutParams layoutParams = viewBar.getLayoutParams();
        layoutParams.height = ScreenUtils.getStatusBarHeight(mContext) + (int) mContext.getResources().getDimension(R.dimen.titleHeight);
        viewBar.setLayoutParams(layoutParams);
        viewPager.setPageTransformer(false, new DefaultTransformer());
        verticalTabLayout.setVisibility(View.GONE);
        badge = new QBadgeView(mContext)
                .setBadgeTextColor(Color.WHITE)
                .setBadgeTextSize(10f, true)
                .setBadgeBackgroundColor(getResources().getColor(R.color.basic_color))
                .setBadgeGravity(Gravity.END | Gravity.TOP)
                .setBadgePadding(2f, true)
                .setGravityOffset(5f, 5f, true);
//        tabString.add("推荐分类");
//        tabString.add("美食");
//        tabString.add("酒店");
//        tabString.add("休闲娱乐");
//        tabString.add("生活服务");
//        tabString.add("家具建材");
//        tabString.add("旅游");
//        tabString.add("保险");
//        tabString.add("教育");
//        tabString.add("房产");
//        tabString.add("汽车");
//        tabString.add("服装");
//        tabString.add("数码电器");
//        tabString.add("推荐分类");
//        tabString.add("美食");
//        tabString.add("酒店");
//        tabString.add("休闲娱乐");
//        tabString.add("生活服务");
//        tabString.add("家具建材");
//        tabString.add("旅游");
//        tabString.add("保险");
//        tabString.add("教育");
//        tabString.add("房产");
//        tabString.add("汽车");
//        tabString.add("服装");
//        tabString.add("数码电器");
//        verticalTabLayout.setTabAdapter(new TabAdapter() {
//            @Override
//            public int getCount() {
//                return tabString.size();
//            }
//
//            @Override
//            public ITabView.TabBadge getBadge(int position) {
//                return null;
//            }
//
//            @Override
//            public ITabView.TabIcon getIcon(int position) {
//                return new TabView.TabIcon.Builder()
//                        .setIcon(R.drawable.divider_tablayout, R.drawable.divider_tablayout)
//                        .setIconGravity(Gravity.BOTTOM)
//                        .setIconMargin(dp2px(0))
//                        .setIconSize(dp2px(100), dp2px(1))
//                        .build();
//            }
//
//            @Override
//            public ITabView.TabTitle getTitle(int position) {
//                return new TabView.TabTitle.Builder()
//                        .setContent(tabString.get(position))
//                        .setTextColor(getResources().getColor(R.color.basic_color), getResources().getColor(R.color.light_black))
//                        .setTextSize(13)
//                        .build();
//            }
//
//            @Override
//            public int getBackground(int position) {
//                return 0;
//            }
//        });
    }

    @Override
    protected void setListeners() {
//        verticalTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabView tab, int position) {
//                id = dataBeanList.get(position).getId();
//                name = dataBeanList.get(position).getName();
//            }
//
//            @Override
//            public void onTabReselected(TabView tab, int position) {
//
//            }
//        });
        mInflate.findViewById(R.id.viewSearch).setOnClickListener(this);
        mInflate.findViewById(R.id.imageXiaoXi).setOnClickListener(this);
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
        String url = Constant.HOST + Constant.Url.INDEX_CATE;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        return new OkObject(params, url);
    }

    public void onRefresh() {
        ApiClient.post(mContext, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("分类", s);
                try {
                    IndexCate indexCate = GsonUtils.parseJSON(s, IndexCate.class);
                    if (indexCate.getStatus() == 1) {
                        dataBeanList = indexCate.getData();
                        viewPager.setAdapter(new FenLeiPageAdapter(getChildFragmentManager(),dataBeanList));
                        verticalTabLayout.setupWithViewPager(viewPager);
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
                        textBgDes.setText(indexCate.getBgDes());
                    } else if (indexCate.getStatus() == 3) {
                        MyDialog.showReLoginDialog(mContext);
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
            }
        });
    }

    protected int dp2px(float dp) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.imageXiaoXi:
                if (isLogin) {
                    intent.setClass(mContext, XiaoXiZXActivity.class);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(mContext);
                }
                break;
            case R.id.viewSearch:
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
