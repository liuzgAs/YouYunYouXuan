package com.vip.uyux.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.interfacepage.OnNotifyItemChangeListener;
import com.vip.uyux.interfacepage.OnPictureListener;
import com.vip.uyux.model.BonusSuperioritybefore;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.model.Picture;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.viewholder.TuiJianShangPinViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TuiJianSPActivity extends ZjbBaseActivity implements View.OnClickListener {

    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<BonusSuperioritybefore> adapter;
    private BonusSuperioritybefore bonusSuperioritybefore;
    private int type;

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
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("推荐商品");
        initRecycler();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.line_width), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<BonusSuperioritybefore>(TuiJianSPActivity.this) {

            private TuiJianShangPinViewHolder tuiJianShangPinViewHolder;

            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.view_tuijianshangpin;
                tuiJianShangPinViewHolder = new TuiJianShangPinViewHolder(parent, layout);
                tuiJianShangPinViewHolder.setOnPictureListener(new OnPictureListener() {
                    @Override
                    public void addPicture(List<LocalMedia> localMediaList, int type) {
                        TuiJianSPActivity.this.addPicture(localMediaList, type);
                    }

                    @Override
                    public void showPicture(List<LocalMedia> localMediaList, int position) {
                        TuiJianSPActivity.this.showPicture(localMediaList, position);
                    }
                });
                tuiJianShangPinViewHolder.setOnNotifyItemChangeListener(new OnNotifyItemChangeListener() {
                    @Override
                    public void notify(int position) {
                        adapter.notifyItemChanged(0);
                    }
                });
                return tuiJianShangPinViewHolder;
            }
        });
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
                        List<Picture> pictureList = new ArrayList<>();
                        pictureList.add(new Picture(1, new LocalMedia()));
                        bonusSuperioritybefore.setPicZhuTu(pictureList);
                        adapter.clear();
                        adapter.add(bonusSuperioritybefore);
                        adapter.notifyDataSetChanged();
                    } else if (bonusSuperioritybefore.getStatus() == 3) {
                        MyDialog.showReLoginDialog(TuiJianSPActivity.this);
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
                    View viewLoader = LayoutInflater.from(TuiJianSPActivity.this).inflate(R.layout.view_loaderror, null);
                    TextView textMsg = viewLoader.findViewById(R.id.textMsg);
                    textMsg.setText(msg);
                    viewLoader.findViewById(R.id.buttonReLoad).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            recyclerView.showProgress();
                            initData();
                        }
                    });
                    recyclerView.setErrorView(viewLoader);
                    recyclerView.showError();
                } catch (Exception e) {
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
                    List<Picture> picZhuTu = adapter.getItem(0).getPicZhuTu();
                    picZhuTu.clear();
                    for (int i = 0; i < localMediaList.size(); i++) {
                        picZhuTu.add(new Picture(0, localMediaList.get(i)));
                    }
                    picZhuTu.add(new Picture(1, new LocalMedia()));
                    adapter.notifyItemChanged(0);
                    break;
                default:
                    break;
            }
        }
    }

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
