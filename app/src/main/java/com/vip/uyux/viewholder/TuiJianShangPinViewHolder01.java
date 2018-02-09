package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.vip.uyux.R;
import com.vip.uyux.interfacepage.OnNotifyItemChangeListener;
import com.vip.uyux.interfacepage.OnPictureListener;
import com.vip.uyux.model.Picture;
import com.vip.uyux.model.TuiJianSP;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class TuiJianShangPinViewHolder01 extends BaseViewHolder<TuiJianSP> {
    TuiJianSP data;
    private final EasyRecyclerView recyclerViewZhuTu;
    public RecyclerArrayAdapter<Picture> adapterZhuTu;
    private OnPictureListener onPictureListener;
    private OnNotifyItemChangeListener onNotifyItemChangeListener;

    public TuiJianShangPinViewHolder01(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        recyclerViewZhuTu = $(R.id.recyclerViewZhuTu);
        initZhuTuRecycler();
    }

    @Override
    public void setData(TuiJianSP data) {
        super.setData(data);
        this.data=data;
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
                        onNotifyItemChangeListener.notify(getDataPosition());
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
                    onPictureListener.addPicture(localMediaList,getDataPosition());
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
        adapterZhuTu.clear();
        adapterZhuTu.add(new Picture(1,new LocalMedia()));
    }

    public void setOnPictureListener(OnPictureListener onPictureListener) {
        this.onPictureListener = onPictureListener;
    }

    public void setOnNotifyItemChangeListener(OnNotifyItemChangeListener onNotifyItemChangeListener) {
        this.onNotifyItemChangeListener = onNotifyItemChangeListener;
    }
}
