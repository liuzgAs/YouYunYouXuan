package com.vip.uyux.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.hedgehog.ratingbar.RatingBar;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.vip.uyux.R;
import com.vip.uyux.adapter.TagAdapter;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.customview.FlowTagLayout;
import com.vip.uyux.model.CommentAddbefore;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.model.Picture;
import com.vip.uyux.model.SimpleInfo;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.viewholder.PictureViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PingJiaActivity extends ZjbBaseActivity implements View.OnClickListener {

    private int id;
    private EasyRecyclerView recyclerView;
    public RecyclerArrayAdapter<Picture> adapter;
    private int selectMax = 9;
    private GridLayoutManager manager;
    private List<CommentAddbefore.TagBean> tagBeanList;
    private CommentAddbefore orderGotoeeva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping_jia);
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
        ((TextView) findViewById(R.id.textViewTitle)).setText("立即评价");
        initRecycler();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        manager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(manager);
        SpaceDecoration itemDecoration = new SpaceDecoration((int) DpUtils.convertDpToPixel(12f, PingJiaActivity.this));
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Picture>(PingJiaActivity.this) {
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

            private RatingBar ratingbar;
            private TextView textGoodName;
            private ImageView imageGood;
            private TagAdapter tagAdapter;
            private FlowTagLayout flowTagLayout;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(PingJiaActivity.this).inflate(R.layout.header_pingjia, null);
                flowTagLayout = view.findViewById(R.id.flowTagLayout);
                flowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
                imageGood = view.findViewById(R.id.imageGood);
                textGoodName = view.findViewById(R.id.textGoodName);
                ratingbar = view.findViewById(R.id.ratingbar);
                ratingbar.setStar(5);
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (orderGotoeeva != null) {
                    if (tagBeanList != null) {
                        tagAdapter = new TagAdapter(PingJiaActivity.this);
                        flowTagLayout.setAdapter(tagAdapter);
                        tagAdapter.clearAndAddAll(tagBeanList);
                        flowTagLayout.clearAllOption();
                    }
                    GlideApp.with(PingJiaActivity.this)
                            .asBitmap()
                            .centerCrop()
                            .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(8, PingJiaActivity.this)))
                            .load(orderGotoeeva.getImg())
                            .into(imageGood);
                    textGoodName.setText(orderGotoeeva.getGoods_name());
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
                            PictureSelector.create(PingJiaActivity.this).externalPicturePreview(position, localMediaList);
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
        String url = Constant.HOST + Constant.Url.COMMENT_ADDBEFORE;
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
        showLoadingDialog();
        ApiClient.post(PingJiaActivity.this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("PingJiaActivity--onSuccess", s + "");
                try {
                    orderGotoeeva = GsonUtils.parseJSON(s, CommentAddbefore.class);
                    if (orderGotoeeva.getStatus() == 1) {
                        tagBeanList = orderGotoeeva.getTag();
                        adapter.clear();
                        adapter.add(new Picture(1, new LocalMedia()));
                        adapter.notifyDataSetChanged();
                    } else if (orderGotoeeva.getStatus() == 3) {
                        MyDialog.showReLoginDialog(PingJiaActivity.this);
                    } else {
                        Toast.makeText(PingJiaActivity.this, orderGotoeeva.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(PingJiaActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(PingJiaActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
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

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getTJOkObject() {
        String url = Constant.HOST + Constant.Url.COMMENT_ADDSUBMIT;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        return new OkObject(params, url);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnTiJiao:
                showLoadingDialog();
                ApiClient.post(PingJiaActivity.this, getTJOkObject(), new ApiClient.CallBack() {
                    @Override
                    public void onSuccess(String s) {
                        cancelLoadingDialog();
                        LogUtil.LogShitou("PingJiaActivity--onSuccess", s + "");
                        try {
                            SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                            if (simpleInfo.getStatus() == 1) {
                            } else if (simpleInfo.getStatus() == 3) {
                                MyDialog.showReLoginDialog(PingJiaActivity.this);
                            } else {
                                Toast.makeText(PingJiaActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(PingJiaActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError() {
                        cancelLoadingDialog();
                        Toast.makeText(PingJiaActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }

}
