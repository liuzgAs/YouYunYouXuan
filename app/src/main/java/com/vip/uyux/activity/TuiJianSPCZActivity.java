package com.vip.uyux.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.customview.EditDialog;
import com.vip.uyux.model.BonusSuperioritybefore;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.model.Picture;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.viewholder.PictureViewHolder;
import com.vip.uyux.viewholder.ZiZhiZMViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TuiJianSPCZActivity extends ZjbBaseActivity implements View.OnClickListener {


    private BonusSuperioritybefore bonusSuperioritybefore;
    private  TextView textHuoYuanXingZhi;
    private  EasyRecyclerView recyclerViewHuoYuan;
    private RecyclerArrayAdapter<BonusSuperioritybefore.DataBean> adapterHuoYuanXingZhi;
    private EasyRecyclerView recyclerViewZhuTu;
    private RecyclerArrayAdapter<Picture> adapterZhuTu;
    private EasyRecyclerView recyclerViewWenJian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tui_jian_sp);
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
        textHuoYuanXingZhi = (TextView) findViewById(R.id.textHuoYuanXingZhi);
        recyclerViewZhuTu = (EasyRecyclerView) findViewById(R.id.recyclerViewZhuTu);
        recyclerViewHuoYuan = (EasyRecyclerView) findViewById(R.id.recyclerViewHuoYuan);
        recyclerViewWenJian = (EasyRecyclerView) findViewById(R.id.recyclerViewWenJian);

    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("推荐商品");
        findViewById(R.id.viewHuoYuanXZ).setOnClickListener(this);
        initHuoYuanYouShiRecycler();
        initZhuTuRecycler();
    }

    /**
     * 初始化recyclerview
     */
    private void initHuoYuanYouShiRecycler() {
        recyclerViewHuoYuan.setLayoutManager(new LinearLayoutManager(this));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.line_width), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerViewHuoYuan.addItemDecoration(itemDecoration);
        recyclerViewHuoYuan.setRefreshingColorResources(R.color.basic_color);
        recyclerViewHuoYuan.setAdapterWithProgress(adapterHuoYuanXingZhi = new RecyclerArrayAdapter<BonusSuperioritybefore.DataBean>(TuiJianSPCZActivity.this) {
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
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        recyclerViewZhuTu.setLayoutManager(manager);
        SpaceDecoration itemDecoration = new SpaceDecoration((int) DpUtils.convertDpToPixel(12f, this));
        recyclerViewZhuTu.addItemDecoration(itemDecoration);
        recyclerViewZhuTu.setRefreshingColorResources(R.color.basic_color);
        recyclerViewZhuTu.setItemAnimator(new DefaultItemAnimator());
        recyclerViewZhuTu.setAdapterWithProgress(adapterZhuTu = new RecyclerArrayAdapter<Picture>(TuiJianSPCZActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.gv_filter_image;
                PictureViewHolder pictureViewHolder = new PictureViewHolder(parent, layout, viewType);
                pictureViewHolder.setOnRemoveListener(new PictureViewHolder.OnRemoveListener() {
                    @Override
                    public void remove(int position) {
                        adapterZhuTu.remove(position);
                        adapterZhuTu.notifyDataSetChanged();
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
                    addPicture(localMediaList,1);
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
                            showPicture(localMediaList,position);
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


    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.BONUS_SUPERIORITYBEFORE;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        return new OkObject(params, url);
    }

    @Override
    protected void initData() {
        ApiClient.post(this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("", s);
                try {
                    bonusSuperioritybefore = GsonUtils.parseJSON(s, BonusSuperioritybefore.class);
                    if (bonusSuperioritybefore.getStatus() == 1) {
                        List<BonusSuperioritybefore.DataBean> dataBeanList = bonusSuperioritybefore.getData();
                        adapterHuoYuanXingZhi.clear();
                        adapterHuoYuanXingZhi.addAll(dataBeanList);
                    } else if (bonusSuperioritybefore.getStatus() == 3) {
                        MyDialog.showReLoginDialog(TuiJianSPCZActivity.this);
                    } else {
                        showError(bonusSuperioritybefore.getInfo());
                    }
                } catch (Exception e) {
                    showError("数据出错");
                }
            }

            @Override
            public void onError() {
                showError("网络出错");
            }

            /**
             * 错误显示
             * @param msg
             */
            private void showError(String msg) {
                try {
                    View viewLoader = LayoutInflater.from(TuiJianSPCZActivity.this).inflate(R.layout.view_loaderror, null);
                    TextView textMsg = viewLoader.findViewById(R.id.textMsg);
                    textMsg.setText(msg);
                    viewLoader.findViewById(R.id.buttonReLoad).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            initData();
                        }
                    });
                } catch (Exception e) {
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.viewHuoYuanXZ:
                List<BonusSuperioritybefore.NatureBean> natureBeanList = bonusSuperioritybefore.getNature();
                final String[] strings = new String[natureBeanList.size()+1];
                for (int i = 0; i < natureBeanList.size(); i++) {
                    strings[i]=natureBeanList.get(i).getName();
                }
                strings[strings.length-1] = "其他";
                new AlertDialog.Builder(this)
                        .setItems(strings, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, final int i) {
                                if (i<strings.length-1){
                                    textHuoYuanXingZhi.setText(strings[i]);
                                }else {
                                    final EditDialog editDialog = new EditDialog(TuiJianSPCZActivity.this, "货源性质", "请输入货源性质", "确认", "取消");
                                    editDialog.setClicklistener(new EditDialog.ClickListenerInterface() {
                                        @Override
                                        public void doConfirm(String intro) {
                                            editDialog.dismiss();
                                            textHuoYuanXingZhi.setText(intro);
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
                break;
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> localMediaList = PictureSelector.obtainMultipleResult(data);
                    List<Picture> picZhuTu = new ArrayList<>();
                    picZhuTu.clear();
                    for (int i = 0; i < localMediaList.size(); i++) {
                        picZhuTu.add(new Picture(0, localMediaList.get(i)));
                    }
                    picZhuTu.add(new Picture(1, new LocalMedia()));
                    switch (type) {
                        case 1:
                            adapterZhuTu.clear();
                            adapterZhuTu.addAll(picZhuTu);
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    int type;

    /**
     * 添加照片
     */
    private void addPicture(List<LocalMedia> localMediaList, int type) {
        this.type = type;
        PictureSelector.create(this)
                /*全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()*/
                .openGallery(PictureMimeType.ofImage())
                /* 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE*/
                .selectionMode(PictureConfig.MULTIPLE)
                .maxSelectNum(50)
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

    private void showPicture(List<LocalMedia> localMediaList, int position) {
        PictureSelector.create(this).externalPicturePreview(position, localMediaList);
    }
}
