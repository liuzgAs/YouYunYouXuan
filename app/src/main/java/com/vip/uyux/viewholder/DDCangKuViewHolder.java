package com.vip.uyux.viewholder;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.vip.uyux.R;
import com.vip.uyux.activity.DingDanXqActivity;
import com.vip.uyux.activity.PingJiaGLActivity;
import com.vip.uyux.activity.WebActivity;
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
public class DDCangKuViewHolder extends BaseViewHolder<Order.DataBean.ListBeanX> {

    private final EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Order.DataBean.ListBeanX.ListBean> adapter;
    private final TextView textSupplier;
    private final TextView textText;
    Order.DataBean.ListBeanX data;
    String id;
    private final TextView textShip;

    public DDCangKuViewHolder(ViewGroup parent, @LayoutRes int res,String id) {
        super(parent, res);
        this.id=id;
        textSupplier = $(R.id.textSupplier);
        textText = $(R.id.textText);
        textShip = $(R.id.textShip);
        recyclerView = $(R.id.recyclerView);
        textText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (data.getCode()) {
                    case "confirmShip":
                        final TwoBtnDialog twoBtnDialog = new TwoBtnDialog(getContext(), "确定要确认收货吗？", "是", "否");
                        twoBtnDialog.show();
                        twoBtnDialog.setClicklistener(new TwoBtnDialog.ClickListenerInterface() {
                            @Override
                            public void doConfirm() {
                                twoBtnDialog.dismiss();
                                queRenDD();
                            }

                            @Override
                            public void doCancel() {
                                twoBtnDialog.dismiss();
                            }
                        });
                        break;
                    case "goComment":
                        Intent intent = new Intent();
                        intent.setClass(getContext(), PingJiaGLActivity.class);
                        getContext().startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });
        textShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getContext(), WebActivity.class);
                intent.putExtra(Constant.IntentKey.TITLE,"物流信息");
                intent.putExtra(Constant.IntentKey.URL,data.getShip_url());
                getContext().startActivity(intent);
            }
        });
        initRecycler();
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.USER_CONFIRMSHIP;
        HashMap<String, String> params = new HashMap<>();
        if (((WoDeDDActivity)getContext()).isLogin) {
            params.put("uid", ((WoDeDDActivity)getContext()).userInfo.getUid());
            params.put("tokenTime",((WoDeDDActivity)getContext()).tokenTime);
        }
        params.put("id",String.valueOf(data.getId()));
        return new OkObject(params, url);
    }

    /**
     * 确认订单
     */
    private void queRenDD() {
        ((WoDeDDActivity)getContext()).showLoadingDialog();
        ApiClient.post(getContext(), getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                ((WoDeDDActivity)getContext()).cancelLoadingDialog();
                LogUtil.LogShitou("DDCangKuViewHolder--onSuccess",s+ "");
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
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Order.DataBean.ListBeanX.ListBean>(getContext()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_ding_dan;
                return new DDItemViewHolder(parent, layout);
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                intent.setClass(getContext(), DingDanXqActivity.class);
                intent.putExtra(Constant.IntentKey.ID,id);
                getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void setData(Order.DataBean.ListBeanX data) {
        super.setData(data);
        this.data = data;
        textSupplier.setText(data.getSupplier());
        if (data.getIs_btn() == 1) {
            textText.setVisibility(View.VISIBLE);
            textText.setText(data.getText());
        } else {
            textText.setVisibility(View.GONE);
        }
        if (data.getIs_ship()==1){
            textShip.setVisibility(View.VISIBLE);
        }else {
            textShip.setVisibility(View.GONE);
        }
        List<Order.DataBean.ListBeanX.ListBean> listBeanList = data.getList();
        adapter.clear();
        adapter.addAll(listBeanList);
    }

}
