package com.vip.uyux.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.vip.uyux.R;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.model.Picture;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.viewholder.PictureViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ShenQingSHActivity extends ZjbBaseActivity implements View.OnClickListener {

    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Picture> adapter;
    private int selectMax = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shen_qing_sh);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void findID() {
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
    }

    @Override
    protected void initViews() {
        ((TextView)findViewById(R.id.textViewTitle)).setText("申请售后");
        initRecycler();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(manager);
        SpaceDecoration itemDecoration = new SpaceDecoration((int) DpUtils.convertDpToPixel(12f, ShenQingSHActivity.this));
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Picture>(ShenQingSHActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.gv_filter_image;
                PictureViewHolder pictureViewHolder = new PictureViewHolder(parent, layout, viewType);
                pictureViewHolder.setOnRemoveListener(new PictureViewHolder.OnRemoveListener() {
                    @Override
                    public void remove(int position) {
                        adapter.remove(position);
                        if (adapter.getAllData().size() == selectMax - 1) {
                            if (adapter.getItem(selectMax - 2).getType() != 1) {
                                adapter.add(new Picture(1, new LocalMedia()));
                            }
                        }
                    }
                });
                return pictureViewHolder;
            }
        });
        manager.setSpanSizeLookup(adapter.obtainGridSpanSizeLookUp(4));
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(ShenQingSHActivity.this).inflate(R.layout.header_shenqingshouhou, null);
                return view;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (adapter.getItem(position).getType() == 1) {
                    addPicture();
                } else {
                    LogUtil.LogShitou("PingJiaActivity--onItemClick", "预览");
                    LocalMedia media = adapter.getItem(position).getLocalMedia();
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    List<LocalMedia> localMediaList = new ArrayList<>();
                    for (int i = 0; i < adapter.getAllData().size(); i++) {
                        if (adapter.getItem(i).getType() == 0) {
                            localMediaList.add(adapter.getItem(i).getLocalMedia());
                        }
                    }
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(ShenQingSHActivity.this).externalPicturePreview(position, localMediaList);
                            break;
                        default:
                            break;
                    }
                }
            }
        });
    }

    /**
     * 添加照片
     */
    private void addPicture() {
        List<LocalMedia> localMediaList = new ArrayList<>();
        for (int i = 0; i < adapter.getAllData().size(); i++) {
            if (adapter.getItem(i).getType() == 0) {
                localMediaList.add(adapter.getItem(i).getLocalMedia());
            }
        }
        PictureSelector.create(this)
                /*全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()*/
                .openGallery(PictureMimeType.ofImage())
                /* 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE*/
                .selectionMode(PictureConfig.MULTIPLE)
                /*最大选择数量*/
                .maxSelectNum(selectMax)
                /*是否显示拍照按钮 true or false*/
                .isCamera(true)
                /*拍照保存图片格式后缀,默认jpeg*/
                .imageFormat(PictureMimeType.PNG)
                /*glide 加载图片大小 0~1之间 如设置 .glideOverride()无效*/
                .sizeMultiplier(0.5f)
                /*是否压缩 true or false*/
                .compress(true)
                /*裁剪压缩质量 默认90 int*/
                .cropCompressQuality(100)
                /*小于100kb的图片不压缩*/
                .minimumCompressSize(100)
                /*同步true或异步false 压缩 默认同步*/
                .synOrAsy(true)
                /*裁剪是否可旋转图片 true or false*/
                .rotateEnabled(true)
                /*裁剪是否可放大缩小图片 true or false*/
                .scaleEnabled(true)
                /*传入已选择的照片*/
                .selectionMedia(localMediaList)
                /*结果回调onActivityResult code*/
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        adapter.clear();
        adapter.add(new Picture(1, new LocalMedia()));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> localMediaList = PictureSelector.obtainMultipleResult(data);
                    adapter.clear();
                    for (int i = 0; i < localMediaList.size(); i++) {
                        adapter.add(new Picture(0, localMediaList.get(i)));
                    }
                    if (localMediaList.size() < selectMax) {
                        adapter.add(new Picture(1, new LocalMedia()));
                    }
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }
}
