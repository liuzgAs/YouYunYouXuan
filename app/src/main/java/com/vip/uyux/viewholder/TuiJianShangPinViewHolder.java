package com.vip.uyux.viewholder;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.vip.uyux.R;
import com.vip.uyux.customview.EditDialog;
import com.vip.uyux.interfacepage.OnNotifyItemChangeListener;
import com.vip.uyux.interfacepage.OnPictureListener;
import com.vip.uyux.model.BonusSuperioritybefore;
import com.vip.uyux.model.Picture;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class TuiJianShangPinViewHolder extends BaseViewHolder<BonusSuperioritybefore> {
    BonusSuperioritybefore data;
    private final TextView textHuoYuanXingZhi;
    private final EasyRecyclerView recyclerViewHuoYuan;
    private RecyclerArrayAdapter<BonusSuperioritybefore.DataBean> adapterHuoYuanXingZhi;
    private final EasyRecyclerView recyclerViewZhuTu;
    private RecyclerArrayAdapter<Picture> adapterZhuTu;
    private OnPictureListener onPictureListener;
    private OnNotifyItemChangeListener onNotifyItemChangeListener;

    public TuiJianShangPinViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textHuoYuanXingZhi = $(R.id.textHuoYuanXingZhi);
        $(R.id.viewHuoYuanXZ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<BonusSuperioritybefore.NatureBean> natureBeanList = data.getNature();
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
                                    data.setHuoYuanXingZhi(strings[i]);
                                }else {
                                    final EditDialog editDialog = new EditDialog(getContext(), "货源性质", "请输入货源性质", "确认", "取消");
                                    editDialog.setClicklistener(new EditDialog.ClickListenerInterface() {
                                        @Override
                                        public void doConfirm(String intro) {
                                            editDialog.dismiss();
                                            textHuoYuanXingZhi.setText(intro);
                                            data.setHuoYuanXingZhi(intro);
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
        recyclerViewZhuTu = $(R.id.recyclerViewZhuTu);
        initZhuTuRecycler();
    }

    @Override
    public void setData(BonusSuperioritybefore data) {
        super.setData(data);
        this.data=data;
        textHuoYuanXingZhi.setText(data.getHuoYuanXingZhi());
        List<BonusSuperioritybefore.DataBean> dataBeanList = data.getData();
        adapterHuoYuanXingZhi.clear();
        adapterHuoYuanXingZhi.addAll(dataBeanList);
        adapterZhuTu.clear();
        adapterZhuTu.addAll(data.getPicZhuTu());
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

    /**
     * 初始化recyclerview
     */
    private void initZhuTuRecycler() {
        GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        recyclerViewZhuTu.setLayoutManager(manager);
        SpaceDecoration itemDecoration = new SpaceDecoration((int) DpUtils.convertDpToPixel(12f, getContext()));
        recyclerViewZhuTu.addItemDecoration(itemDecoration);
        recyclerViewZhuTu.setRefreshingColorResources(R.color.basic_color);
        recyclerViewZhuTu.setItemAnimator(new DefaultItemAnimator());
        recyclerViewZhuTu.setAdapterWithProgress(adapterZhuTu = new RecyclerArrayAdapter<Picture>(getContext()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.gv_filter_image;
                PictureViewHolder pictureViewHolder = new PictureViewHolder(parent, layout, viewType);
                pictureViewHolder.setOnRemoveListener(new PictureViewHolder.OnRemoveListener() {
                    @Override
                    public void remove(int position) {
                        adapterZhuTu.remove(position);
                        data.getPicZhuTu().remove(position);
                        onNotifyItemChangeListener.notify(0);
                    }
                });
                return pictureViewHolder;
            }

            @Override
            public int getViewType(int position) {
                return getItem(position).getType();
            }
        });
        manager.setSpanSizeLookup(adapterZhuTu.obtainGridSpanSizeLookUp(4));
        adapterZhuTu.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (adapterZhuTu.getItem(position).getType() == 1) {
                    List<LocalMedia> localMediaList = new ArrayList<>();
                    for (int i = 0; i < adapterZhuTu.getAllData().size(); i++) {
                        if (adapterZhuTu.getItem(i).getType() == 0) {
                            localMediaList.add(adapterZhuTu.getItem(i).getLocalMedia());
                        }
                    }
                    onPictureListener.addPicture(localMediaList,1);
                } else {
                    LogUtil.LogShitou("PingJiaActivity--onItemClick", "预览");
                    LocalMedia media = adapterZhuTu.getItem(position).getLocalMedia();
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    List<LocalMedia> localMediaList = new ArrayList<>();
                    for (int i = 0; i < adapterZhuTu.getAllData().size(); i++) {
                        if (adapterZhuTu.getItem(i).getType() == 0) {
                            localMediaList.add(adapterZhuTu.getItem(i).getLocalMedia());
                        }
                    }
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).externalPicturePreview(position, "/custom_file", selectList);
                            onPictureListener.showPicture(localMediaList,position);
                            break;
                        default:
                            break;
                    }
                }
            }
        });
    }

    public void setOnPictureListener(OnPictureListener onPictureListener) {
        this.onPictureListener = onPictureListener;
    }

    public void setOnNotifyItemChangeListener(OnNotifyItemChangeListener onNotifyItemChangeListener) {
        this.onNotifyItemChangeListener = onNotifyItemChangeListener;
    }
}
