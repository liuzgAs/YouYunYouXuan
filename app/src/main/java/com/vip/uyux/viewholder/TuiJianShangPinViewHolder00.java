package com.vip.uyux.viewholder;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.vip.uyux.R;
import com.vip.uyux.customview.EditDialog;
import com.vip.uyux.model.BonusSuperioritybefore;
import com.vip.uyux.model.TuiJianSP;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class TuiJianShangPinViewHolder00 extends BaseViewHolder<TuiJianSP> {
    TuiJianSP data;
    private final TextView textHuoYuanXingZhi;
    private final EasyRecyclerView recyclerViewHuoYuan;
    private RecyclerArrayAdapter<BonusSuperioritybefore.DataBean> adapterHuoYuanXingZhi;

    public TuiJianShangPinViewHolder00(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textHuoYuanXingZhi = $(R.id.textHuoYuanXingZhi);
        $(R.id.viewHuoYuanXZ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<BonusSuperioritybefore.NatureBean> natureBeanList = data.getBonusSuperioritybefore().getNature();
                final String[] strings = new String[natureBeanList.size()+1];
                for (int i = 0; i < natureBeanList.size(); i++) {
                    strings[i]=natureBeanList.get(i).getName();
                }
                strings[strings.length-1] = "其他";
                new AlertDialog.Builder(getContext())
                        .setItems(strings, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, final int i) {
                                if (i<strings.length-1){
                                    textHuoYuanXingZhi.setText(strings[i]);
                                    data.getBonusSuperioritybefore().setHuoYuanXingZhi(strings[i]);
                                }else {
                                    final EditDialog editDialog = new EditDialog(getContext(), "货源性质", "请输入货源性质", "确认", "取消");
                                    editDialog.setClicklistener(new EditDialog.ClickListenerInterface() {
                                        @Override
                                        public void doConfirm(String intro) {
                                            editDialog.dismiss();
                                            textHuoYuanXingZhi.setText(intro);
                                            data.getBonusSuperioritybefore().setHuoYuanXingZhi(intro);
                                        }

                                        @Override
                                        public void doCancel() {
                                            editDialog.dismiss();
                                        }
                                    });
                                    editDialog.show();
                                }
                            }
                        })
                        .show();
            }
        });
        recyclerViewHuoYuan = $(R.id.recyclerViewHuoYuan);
        initHuoYuanYouShiRecycler();
    }

    @Override
    public void setData(TuiJianSP data) {
        super.setData(data);
        this.data=data;
        textHuoYuanXingZhi.setText(data.getBonusSuperioritybefore().getHuoYuanXingZhi());
        List<BonusSuperioritybefore.DataBean> dataBeanList = data.getBonusSuperioritybefore().getData();
        adapterHuoYuanXingZhi.clear();
        adapterHuoYuanXingZhi.addAll(dataBeanList);

    }

    /**
     * 初始化recyclerview
     */
    private void initHuoYuanYouShiRecycler() {
        recyclerViewHuoYuan.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getContext().getResources().getDimension(R.dimen.line_width), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerViewHuoYuan.addItemDecoration(itemDecoration);
        recyclerViewHuoYuan.setRefreshingColorResources(R.color.basic_color);
        recyclerViewHuoYuan.setAdapterWithProgress(adapterHuoYuanXingZhi = new RecyclerArrayAdapter<BonusSuperioritybefore.DataBean>(getContext()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_zizhizhengming;
                return new ZiZhiZMViewHolder(parent, layout);
            }
        });
    }


}
