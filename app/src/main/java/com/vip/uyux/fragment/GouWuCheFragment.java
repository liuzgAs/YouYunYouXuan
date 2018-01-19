package com.vip.uyux.fragment;


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

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.vip.uyux.R;
import com.vip.uyux.base.ZjbBaseFragment;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.provider.DataProvider;
import com.vip.uyux.util.ScreenUtils;
import com.vip.uyux.viewholder.CarViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GouWuCheFragment extends ZjbBaseFragment implements View.OnClickListener {

    private View mInflate;
    private View mRelaTitleStatue;
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Integer> adapter;
    private TextView textHeJi;
    private ImageView imageQuanXuan;
    private boolean isQuan = true;
    private BroadcastReceiver reciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
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
    private View viewJieSuan;

    public GouWuCheFragment() {
        // Required empty public constructor
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
    }

    @Override
    protected void initViews() {
        mInflate.findViewById(R.id.imageBack).setVisibility(View.GONE);
        ViewGroup.LayoutParams layoutParams = mRelaTitleStatue.getLayoutParams();
        layoutParams.height =ScreenUtils.getStatusBarHeight(getActivity())+(int) getActivity().getResources().getDimension(R.dimen.titleHeight);
        mRelaTitleStatue.setLayoutParams(layoutParams);
        ((TextView)mInflate.findViewById(R.id.textViewTitle)).setText("购物车");
        initRecycle();
    }

    @Override
    protected void setListeners() {
        mInflate.findViewById(R.id.buttonJieSuan).setOnClickListener(this);
        mInflate.findViewById(R.id.viewQuanXuan).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    private void initRecycle() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.line_width), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        int red = getResources().getColor(R.color.basic_color);
        recyclerView.setRefreshingColor(red);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Integer>(getActivity()) {
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
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.header_car, null);
                return view;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
    }


    public void onRefresh() {
        List<Integer> cartBeanList = new ArrayList<>();
        cartBeanList.addAll(DataProvider.getPersonList(1));
        if (cartBeanList.size() > 0) {
            viewJieSuan.setVisibility(View.VISIBLE);
//            double sum = 0;
//            for (int i = 0; i < cartBeanList.size(); i++) {
//                cartBeanList.get(i).setSelect(true);
//                sum = Arith.add(sum, cartBeanList.get(i).getSum());
//            }
            SpannableString span = new SpannableString("¥888");
            span.setSpan(new RelativeSizeSpan(0.65f), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            textHeJi.setText(span);
            imageQuanXuan.setImageResource(R.mipmap.xuanzhong);
        } else {
            viewJieSuan.setVisibility(View.GONE);
        }
        adapter.clear();
        adapter.addAll(cartBeanList);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.viewQuanXuan:
//                isQuan = !isQuan;
//                List<Cart.CartBean> allData = adapter.getAllData();
//                for (int i = 0; i < allData.size(); i++) {
//                    if (isQuan) {
//                        allData.get(i).setSelect(true);
//                        imageQuanXuan.setImageResource(R.mipmap.xuanzhong);
//                    } else {
//                        imageQuanXuan.setImageResource(R.mipmap.weixuanzhong);
//                        allData.get(i).setSelect(false);
//                    }
//                }
//                adapter.notifyDataSetChanged();
                break;
            case R.id.buttonJieSuan:
//                String id = "";
//                for (int i = 0; i < adapter.getAllData().size(); i++) {
//                    if (adapter.getAllData().get(i).getSelect()) {
//                        id = id + adapter.getAllData().get(i).getId() + ",";
//                    }
//                }
//                if (TextUtils.isEmpty(id)) {
//                    Toast.makeText(getActivity(), "请选择要结算的商品", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                Intent intent = new Intent();
//                intent.putExtra(Constant.IntentKey.ID, id);
//                intent.setClass(getActivity(), QueRenDDActivity.class);
//                startActivity(intent);
                break;
            default:

                break;
        }
    }

    public void checkQuanXuan() {
//        isQuan = true;
//        List<Cart.CartBean> allData = adapter.getAllData();
//        double sum = 0;
//        if (allData.size() > 0) {
//            for (int i = 0; i < allData.size(); i++) {
//                if (!allData.get(i).getSelect()) {
//                    isQuan = false;
//                    break;
//                }
//            }
//            for (int i = 0; i < allData.size(); i++) {
//                if (allData.get(i).getSelect()) {
//                    sum = sum + allData.get(i).getSum();
//                }
//            }
//            textHeJi.setText("¥" + sum);
//            if (isQuan) {
//                imageQuanXuan.setImageResource(R.mipmap.xuanzhong);
//            } else {
//                imageQuanXuan.setImageResource(R.mipmap.weixuanzhong);
//            }
//            viewJieSuan.setVisibility(View.VISIBLE);
//        } else {
//            viewJieSuan.setVisibility(View.GONE);
//        }
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.BroadcastCode.QUAN_XUAN);
        filter.addAction(Constant.BroadcastCode.SHUA_XIN_CAR);
        getActivity().registerReceiver(reciver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(reciver);
    }
}
