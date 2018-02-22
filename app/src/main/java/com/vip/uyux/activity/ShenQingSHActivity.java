package com.vip.uyux.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.vip.uyux.R;
import com.vip.uyux.adapter.TagSHAdapter;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.customview.FlowTagLayout;
import com.vip.uyux.customview.OnTagSelectListener;
import com.vip.uyux.model.AftersAddbefore;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.model.Picture;
import com.vip.uyux.model.RespondAppimgadd;
import com.vip.uyux.model.ShouHouTiJiao;
import com.vip.uyux.model.SimpleInfo;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.ImgToBase64;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.viewholder.PictureViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShenQingSHActivity extends ZjbBaseActivity implements View.OnClickListener {

    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Picture> adapter;
    private int selectMax = 9;
    private int id;
    private AftersAddbefore aftersAddbefore;
    List<Integer> imgsList = new ArrayList<>();
    private EditText editDes;

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
        Intent intent = getIntent();
        id = intent.getIntExtra(Constant.IntentKey.ID, 0);
    }

    @Override
    protected void findID() {
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("申请售后");
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

            @Override
            public int getViewType(int position) {
                return getItem(position).getType();
            }
        });
        manager.setSpanSizeLookup(adapter.obtainGridSpanSizeLookUp(4));
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {

            private TextView textLength;
            private TextView textGoods_name;
            private TextView textGoods_price;
            private TextView textSpe_name;
            private ImageView imageImg;
            private TagSHAdapter tagSHAdapter;
            private FlowTagLayout flowTagLayout;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(ShenQingSHActivity.this).inflate(R.layout.header_shenqingshouhou, null);
                flowTagLayout = view.findViewById(R.id.flowTagLayout);
                flowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE_FALSE);
                tagSHAdapter = new TagSHAdapter(ShenQingSHActivity.this);
                flowTagLayout.setAdapter(tagSHAdapter);
                imageImg = view.findViewById(R.id.imageImg);
                textGoods_name = view.findViewById(R.id.textGoods_name);
                textSpe_name = view.findViewById(R.id.textSpe_name);
                textGoods_price = view.findViewById(R.id.textGoods_price);
                editDes = view.findViewById(R.id.editDes);
                textLength = view.findViewById(R.id.textLength);
                flowTagLayout.setOnTagSelectListener(new OnTagSelectListener() {
                    @Override
                    public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                        for (int i = 0; i < aftersAddbefore.getTag().size(); i++) {
                            aftersAddbefore.getTag().get(i).setSelect(false);
                        }
                        for (int i = 0; i < selectedList.size(); i++) {
                            aftersAddbefore.getTag().get(selectedList.get(i)).setSelect(true);
                        }
                    }
                });
                editDes.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        textLength.setText(editable.toString().length() + "/400");
                    }
                });
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (aftersAddbefore != null) {
                    List<AftersAddbefore.TagBean> tagBeanList = aftersAddbefore.getTag();
                    tagSHAdapter.clearAndAddAll(tagBeanList);
                    for (int i = 0; i < tagBeanList.size(); i++) {
                        if (tagBeanList.get(i).isSelect()) {
                            flowTagLayout.setSelect(i);
                        }
                    }
                    textSpe_name.setText(aftersAddbefore.getSpe_name());
                    GlideApp.with(ShenQingSHActivity.this)
                            .asBitmap()
                            .centerCrop()
                            .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(10, ShenQingSHActivity.this)))
                            .load(aftersAddbefore.getImg())
                            .into(imageImg);
                    textGoods_price.setText("¥" + aftersAddbefore.getGoods_price());
                    textGoods_name.setText(aftersAddbefore.getGoods_name());
                    editDes.setHint(aftersAddbefore.getDes());

                }
            }
        });
        adapter.addFooter(new RecyclerArrayAdapter.ItemView() {
            private TextView textDes2;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(ShenQingSHActivity.this).inflate(R.layout.foot_shenqingshouhou, null);
                textDes2 = view.findViewById(R.id.textDes2);
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (aftersAddbefore != null) {
                    textDes2.setText(aftersAddbefore.getDes2());
                }
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
        findViewById(R.id.btnTiJiao).setOnClickListener(this);
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.AFTERS_ADDBEFORE;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("id", String.valueOf(id));
        return new OkObject(params, url);
    }

    @Override
    protected void initData() {

        ApiClient.post(this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("", s);
                try {
                    aftersAddbefore = GsonUtils.parseJSON(s, AftersAddbefore.class);
                    if (aftersAddbefore.getStatus() == 1) {
                        adapter.clear();
                        adapter.add(new Picture(1, new LocalMedia()));
                        adapter.notifyDataSetChanged();
                    } else if (aftersAddbefore.getStatus() == 3) {
                        MyDialog.showReLoginDialog(ShenQingSHActivity.this);
                    } else {
                        showError(aftersAddbefore.getInfo());
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
                    View viewLoader = LayoutInflater.from(ShenQingSHActivity.this).inflate(R.layout.view_loaderror, null);
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

    int imgSum = 0;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnTiJiao:
                imgsList.clear();
                imgSum = 0;
                if (adapter.getAllData().size() > 1) {
                    for (int i = 0; i < adapter.getAllData().size(); i++) {
                        if (adapter.getItem(i).getType() == 0) {
                            imgSum++;
                            upImg(adapter.getItem(i).getLocalMedia().getCompressPath());
                        }
                    }
                } else {
                    tiJiao();
                }
                break;
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getTPOkObject(String path) {
        String url = Constant.WEB_HOST + Constant.Url.RESPOND_APPIMGADD;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("code", "headimg");
        params.put("img", ImgToBase64.toBase64(path));
        params.put("type", "png");
        return new OkObject(params, url);
    }

    /**
     * 上传图片
     */
    private void upImg(String path) {
        showLoadingDialog();
        ApiClient.post(ShenQingSHActivity.this, getTPOkObject(path), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("GeRenXXActivity--上传图片", s + "");
                try {
                    RespondAppimgadd respondAppimgadd = GsonUtils.parseJSON(s, RespondAppimgadd.class);
                    if (respondAppimgadd.getStatus() == 1) {
                        imgsList.add(respondAppimgadd.getImgId());
                        if (imgsList.size() == imgSum) {
                            tiJiao();
                        }
                    } else if (respondAppimgadd.getStatus() == 3) {
                        MyDialog.showReLoginDialog(ShenQingSHActivity.this);
                    } else {
                        Toast.makeText(ShenQingSHActivity.this, respondAppimgadd.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(ShenQingSHActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(ShenQingSHActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void tiJiao() {
        List<AftersAddbefore.TagBean> tag = aftersAddbefore.getTag();
        int tagId = -1;
        for (int i = 0; i < tag.size(); i++) {
            if (tag.get(i).isSelect()) {
                tagId = tag.get(i).getId();
                break;
            }
        }
        if (tagId == -1) {
            Toast.makeText(ShenQingSHActivity.this, "请选择服务类型", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(editDes.getText().toString().trim())) {
            Toast.makeText(ShenQingSHActivity.this, "请输入问题描述", Toast.LENGTH_SHORT).show();
            return;
        }
        ShouHouTiJiao shouHouTiJiao = new ShouHouTiJiao(1, "android", userInfo.getUid(), tokenTime, id, tagId, imgsList, editDes.getText().toString().trim());
        showLoadingDialog();
        String url = Constant.HOST + Constant.Url.AFTERS_ADDSUBMIT;
        ApiClient.postJson(ShenQingSHActivity.this, url, GsonUtils.parseObject(shouHouTiJiao), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ShenQingSHActivity--onSuccess", s + "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus() == 1) {
                        Intent intent = new Intent();
                        intent.setAction(Constant.BroadcastCode.SHUA_XIN_SHOW_HOU);
                        sendBroadcast(intent);
                        MyDialog.dialogFinish(ShenQingSHActivity.this, simpleInfo.getInfo());
                    } else if (simpleInfo.getStatus() == 3) {
                        MyDialog.showReLoginDialog(ShenQingSHActivity.this);
                    } else {
                        Toast.makeText(ShenQingSHActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(ShenQingSHActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(ShenQingSHActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
