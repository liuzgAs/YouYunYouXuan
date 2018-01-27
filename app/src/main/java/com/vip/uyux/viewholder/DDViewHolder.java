package com.vip.uyux.viewholder;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.vip.uyux.R;
import com.vip.uyux.activity.LiJiZFActivity;
import com.vip.uyux.activity.WoDeDDActivity;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.customview.TwoBtnDialog;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.model.Order;
import com.vip.uyux.model.SimpleInfo;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class DDViewHolder extends BaseViewHolder<Order.DataBean> {

    private final EasyRecyclerView recyclerView;
    private final EasyRecyclerView recyclerViewDes;
    private RecyclerArrayAdapter<Order.DataBean.ListBeanX> adapter;
    private RecyclerArrayAdapter<String> adapterDes;
    private final TextView textOrderSn;
    private final TextView textOrderSnDes;
    private final TextView textSumDes;
    private final TextView textSum;
    private final TextView textCancle;
    private final Button btnPingJia;
    Order.DataBean data;

    public DDViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textOrderSn = $(R.id.textOrderSn);
        textOrderSnDes = $(R.id.textOrderSnDes);
        textSumDes = $(R.id.textSumDes);
        textSum = $(R.id.textSum);
        textCancle = $(R.id.textCancle);
        btnPingJia = $(R.id.btnPingJia);
        btnPingJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(Constant.IntentKey.ID, data.getOid());
                intent.putExtra(Constant.IntentKey.VALUE, data.getSum());
                intent.setClass(getContext(), LiJiZFActivity.class);
                getContext().startActivity(intent);
            }
        });
        textCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TwoBtnDialog twoBtnDialog = new TwoBtnDialog(getContext(), "确定要取消该订单吗？", "是", "否");
                twoBtnDialog.show();
                twoBtnDialog.setClicklistener(new TwoBtnDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm() {
                        twoBtnDialog.dismiss();
                        quXiaoDingDan();
                    }

                    @Override
                    public void doCancel() {
                        twoBtnDialog.dismiss();
                    }
                });
            }
        });
        recyclerView = $(R.id.recyclerView);
        recyclerViewDes = $(R.id.recyclerViewDes);
        initRecycler();
        initDesRecycler();
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.USER_CANCELORDER;
        HashMap<String, String> params = new HashMap<>();
        if (((WoDeDDActivity)getContext()).isLogin) {
            params.put("uid", ((WoDeDDActivity)getContext()).userInfo.getUid());
            params.put("tokenTime",((WoDeDDActivity)getContext()).tokenTime);
        }
        params.put("id",String.valueOf(data.getOid()));
        return new OkObject(params, url);
    }

    /**
     * 取消订单
     */
    private void quXiaoDingDan() {
        ((WoDeDDActivity)getContext()).showLoadingDialog();
        ApiClient.post(getContext(), getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                ((WoDeDDActivity)getContext()).cancelLoadingDialog();
                LogUtil.LogShitou("DDViewHolder--onSuccess",s+ "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus()==1){
                        Intent intent = new Intent();
                        intent.setAction(Constant.BroadcastCode.SHUAXINDD);
                        getContext().sendBroadcast(intent);
                    }else if (simpleInfo.getStatus()==3){
                        MyDialog.showReLoginDialog(getContext());
                    }else {
                        Toast.makeText(getContext(), simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(),"数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                ((WoDeDDActivity)getContext()).cancelLoadingDialog();
                Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getContext().getResources().getDimension(R.dimen.line_width), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Order.DataBean.ListBeanX>(getContext()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_ding_dan_cangku;
                return new DDCangKuViewHolder(parent, layout);
            }
        });
    }

    /**
     * 初始化recyclerview
     */
    private void initDesRecycler() {
        recyclerViewDes.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewDes.setAdapterWithProgress(adapterDes = new RecyclerArrayAdapter<String>(getContext()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_dd_des;
                return new DDDesViewHolder(parent, layout);
            }
        });
    }

    @Override
    public void setData(Order.DataBean data) {
        super.setData(data);
        this.data = data;
        textOrderSn.setText(data.getOrderSn());
        textOrderSnDes.setText(data.getOrderSnDes());
        textSumDes.setText(data.getSumDes());
        textSum.setText(data.getSum());
        if (data.getGoPay() == 1) {
            btnPingJia.setVisibility(View.VISIBLE);
        } else {
            btnPingJia.setVisibility(View.GONE);
        }
        if (data.getIsCancel() == 1) {
            textCancle.setVisibility(View.VISIBLE);
        } else {
            btnPingJia.setVisibility(View.GONE);
        }
        List<Order.DataBean.ListBeanX> listBeanXList = data.getList();
        adapter.clear();
        adapter.addAll(listBeanXList);
        List<String> desList = data.getDesList();
        adapterDes.clear();
        adapterDes.addAll(desList);
    }

}
