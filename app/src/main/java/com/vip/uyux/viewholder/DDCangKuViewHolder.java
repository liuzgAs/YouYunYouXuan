package com.vip.uyux.viewholder;

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

    public DDCangKuViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textSupplier = $(R.id.textSupplier);
        textText = $(R.id.textText);
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
                        Toast.makeText(getContext(), "评价", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
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
        List<Order.DataBean.ListBeanX.ListBean> listBeanList = data.getList();
        adapter.clear();
        adapter.addAll(listBeanList);
    }

}
