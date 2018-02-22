package com.vip.uyux.viewholder;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.vip.uyux.R;
import com.vip.uyux.interfacepage.OnPingLunListenert;
import com.vip.uyux.model.EvaluationInfo;
import com.vip.uyux.util.GlideApp;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class CePingViewHolder extends BaseViewHolder<EvaluationInfo.DataBean> {

    private final EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<EvaluationInfo.DataBean.ListBean> adapter;
    private final ImageView imageHead;
    private final TextView textNickname;
    private final TextView textContent;
    private OnPingLunListenert onPingLunListenert;
    EvaluationInfo.DataBean data;

    public CePingViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageHead = $(R.id.imageHead);
        textNickname = $(R.id.textNickname);
        textContent = $(R.id.textContent);
        recyclerView = $(R.id.recyclerView);
        $(R.id.imageLiuYan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPingLunListenert.pingLun(1, data.getId(),data.getNickname());
            }
        });
        initRecycler();
    }

    @Override
    public void setData(EvaluationInfo.DataBean data) {
        super.setData(data);
        this.data = data;
        GlideApp.with(getContext())
                .load(data.getHeadimg())
                .centerCrop()
                .circleCrop()
                .placeholder(R.mipmap.ic_empty)
                .into(imageHead);
        textNickname.setText(data.getNickname());
        textContent.setText(data.getContent());
        List<EvaluationInfo.DataBean.ListBean> listBeanList = data.getList();
        adapter.clear();
        adapter.addAll(listBeanList);
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
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<EvaluationInfo.DataBean.ListBean>(getContext()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_pingce_pinglun;
                return new LiuYanHuiFuViewHolder(parent, layout);
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                onPingLunListenert.pingLun(adapter.getItem(position).getType(), adapter.getItem(position).getId(),adapter.getItem(position).getNickname());
            }
        });
    }

    public void setOnPingLunListenert(OnPingLunListenert onPingLunListenert) {
        this.onPingLunListenert = onPingLunListenert;
    }
}
