package com.vip.uyux.fragment;


import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.vip.uyux.R;
import com.vip.uyux.activity.QueRenDDActivity;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseFragment;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.CartIndex;
import com.vip.uyux.model.JieSuan;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.util.ACache;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.Arith;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.util.ScreenUtils;
import com.vip.uyux.viewholder.CarViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GouWuCheFragment extends ZjbBaseFragment implements View.OnClickListener {

    private View mInflate;
    private View mRelaTitleStatue;
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<CartIndex.CartBean> adapter;
    private TextView textHeJi;
    private ImageView imageQuanXuan;
    private boolean isQuan = true;
    private BroadcastReceiver reciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case Constant.BroadcastCode.NUM_CHANGE:
                    shuaXinSum();
                    break;
                case Constant.BroadcastCode.SHUA_XIN_CAR:
                    onRefresh();
                    break;
                case Constant.BroadcastCode.QUAN_XUAN:
                    checkQuanXuan();
                    break;
                default:

                    break;
            }
        }
    };
    private View imageBack;

    /**
     * 刷新car总价
     */
    private void shuaXinSum() {
        double sum = 0;
        for (int i = 0; i < adapter.getAllData().size(); i++) {
            if (adapter.getItem(i).isSelect()) {
                Double mul = Arith.mul((double) adapter.getItem(i).getNum(), adapter.getItem(i).getGoods_price());
                sum = Arith.add(sum, mul);
            }
        }
        SpannableString span = new SpannableString("¥" + sum);
        span.setSpan(new RelativeSizeSpan(0.65f), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textHeJi.setText(span);
    }

    private View viewJieSuan;

    public GouWuCheFragment() {
        // Required empty public constructor
    }

    int type;

    @SuppressLint("ValidFragment")
    public GouWuCheFragment(int type) {
        this.type=type;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_gou_wu_che, container, false);
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
        mRelaTitleStatue = mInflate.findViewById(R.id.relaTitleStatue);
        recyclerView = (EasyRecyclerView) mInflate.findViewById(R.id.recyclerView);
        textHeJi = (TextView) mInflate.findViewById(R.id.textHeJi);
        imageQuanXuan = (ImageView) mInflate.findViewById(R.id.imageQuanXuan);
        viewJieSuan = mInflate.findViewById(R.id.viewJieSuan);
        imageBack = mInflate.findViewById(R.id.imageBack);
    }

    @Override
    protected void initViews() {
        if (type==1){
            imageBack.setVisibility(View.VISIBLE);
        }else {
            imageBack.setVisibility(View.GONE);
        }
        viewJieSuan.setVisibility(View.GONE);
        ViewGroup.LayoutParams layoutParams = mRelaTitleStatue.getLayoutParams();
        layoutParams.height = ScreenUtils.getStatusBarHeight(mContext) + (int) mContext.getResources().getDimension(R.dimen.titleHeight);
        mRelaTitleStatue.setLayoutParams(layoutParams);
        ((TextView) mInflate.findViewById(R.id.textViewTitle)).setText("购物袋");
        initRecycle();
    }

    @Override
    protected void setListeners() {
        mInflate.findViewById(R.id.buttonJieSuan).setOnClickListener(this);
        mInflate.findViewById(R.id.viewQuanXuan).setOnClickListener(this);
        imageBack.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    private void initRecycle() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.line_width), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        int red = getResources().getColor(R.color.basic_color);
        recyclerView.setRefreshingColor(red);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<CartIndex.CartBean>(mContext) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_car;
                CarViewHolder carViewHolder = new CarViewHolder(parent, layout);
                carViewHolder.setOnDeleteListener(new CarViewHolder.onDeleteListener() {
                    @Override
                    public void delete(final int position) {
                    }
                });
                return carViewHolder;
            }
        });
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        ACache aCache = ACache.get(mContext, Constant.Acache.LOCATION);
        String did = aCache.getAsString(Constant.Acache.DID);
        String url = Constant.HOST + Constant.Url.CART_INDEX;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("did", did);
        return new OkObject(params, url);
    }


    public void onRefresh() {
        ApiClient.post(mContext, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("购物车", s);
                try {
                    CartIndex cartIndex = GsonUtils.parseJSON(s, CartIndex.class);
                    if (cartIndex.getStatus() == 1) {
                        List<CartIndex.CartBean> cartBeanList = cartIndex.getCart();
                        if (cartBeanList.size() > 0) {
                            viewJieSuan.setVisibility(View.VISIBLE);
                            double sum = 0;
                            for (int i = 0; i < cartBeanList.size(); i++) {
                                cartBeanList.get(i).setSelect(true);
                                Double mul = Arith.mul((double) cartBeanList.get(i).getNum(), cartBeanList.get(i).getGoods_price());
                                sum = Arith.add(sum, mul);
                            }
                            SpannableString span = new SpannableString("¥" + sum);
                            span.setSpan(new RelativeSizeSpan(0.65f), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            textHeJi.setText(span);
                            imageQuanXuan.setImageResource(R.mipmap.xuanzhong);
                        } else {
                            viewJieSuan.setVisibility(View.GONE);
                        }
                        adapter.clear();
                        adapter.addAll(cartBeanList);

                    } else if (cartIndex.getStatus() == 3) {
                        MyDialog.showReLoginDialog(mContext);
                    } else {
                        showError(cartIndex.getInfo());
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageBack:
                getActivity().finish();
                break;
            case R.id.viewQuanXuan:
                isQuan = !isQuan;
                List<CartIndex.CartBean> allData = adapter.getAllData();
                for (int i = 0; i < allData.size(); i++) {
                    if (isQuan) {
                        allData.get(i).setSelect(true);
                        imageQuanXuan.setImageResource(R.mipmap.xuanzhong);
                    } else {
                        imageQuanXuan.setImageResource(R.mipmap.weixuanzhong);
                        allData.get(i).setSelect(false);
                    }
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.buttonJieSuan:
                List<Integer> integerList = new ArrayList<>();
                for (int i = 0; i < adapter.getAllData().size(); i++) {
                    if (adapter.getAllData().get(i).isSelect()) {
                        integerList.add(adapter.getAllData().get(i).getId());
                    }
                }
                if (integerList.size() == 0) {
                    Toast.makeText(mContext, "请选择要结算的商品", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra(Constant.IntentKey.BEAN, new JieSuan(integerList));
                intent.setClass(mContext, QueRenDDActivity.class);
                startActivity(intent);
                break;
            default:

                break;
        }
    }

    public void checkQuanXuan() {
        isQuan = true;
        List<CartIndex.CartBean> allData = adapter.getAllData();
        if (allData.size() > 0) {
            for (int i = 0; i < allData.size(); i++) {
                if (!allData.get(i).isSelect()) {
                    isQuan = false;
                    break;
                }
            }
            shuaXinSum();
            if (isQuan) {
                imageQuanXuan.setImageResource(R.mipmap.xuanzhong);
            } else {
                imageQuanXuan.setImageResource(R.mipmap.weixuanzhong);
            }
            viewJieSuan.setVisibility(View.VISIBLE);
        } else {
            viewJieSuan.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.BroadcastCode.QUAN_XUAN);
        filter.addAction(Constant.BroadcastCode.SHUA_XIN_CAR);
        filter.addAction(Constant.BroadcastCode.NUM_CHANGE);
        mContext.registerReceiver(reciver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext.unregisterReceiver(reciver);
    }
}
