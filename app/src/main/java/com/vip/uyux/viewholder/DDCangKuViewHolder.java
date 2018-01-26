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
import com.vip.uyux.model.Order;

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
                        Toast.makeText(getContext(), "确认收货", Toast.LENGTH_SHORT).show();
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
