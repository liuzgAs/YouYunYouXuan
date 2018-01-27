package com.vip.uyux.viewholder;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.vip.uyux.R;
import com.vip.uyux.model.BonusDistributionorder;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class FenXiaoViewHolder extends BaseViewHolder<BonusDistributionorder.DataBean> {

    private final EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<BonusDistributionorder.DataBean.OrderGoodsBean> adapter;
    private final TextView textOrder_no;
    private final TextView textStatus_v;
    private final TextView textName;
    private final TextView textLv;
    private final TextView textDes;
    private final TextView textEstimate_money;
    private final ImageView imageImg;

    public FenXiaoViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        recyclerView = $(R.id.recyclerView);
        textOrder_no = $(R.id.textOrder_no);
        textStatus_v = $(R.id.textStatus_v);
        textName = $(R.id.textName);
        textLv = $(R.id.textLv);
        textDes = $(R.id.textDes);
        textEstimate_money = $(R.id.textEstimate_money);
        imageImg = $(R.id.imageImg);
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
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<BonusDistributionorder.DataBean.OrderGoodsBean>(getContext()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_cepin;
                return new FenXiaoItemViewHolder(parent, layout);
            }
        });
    }

    @Override
    public void setData(BonusDistributionorder.DataBean data) {
        super.setData(data);
        List<BonusDistributionorder.DataBean.OrderGoodsBean> goodsBeanList = data.getOrder_goods();
        adapter.clear();
        adapter.addAll(goodsBeanList);
    }

    
}
