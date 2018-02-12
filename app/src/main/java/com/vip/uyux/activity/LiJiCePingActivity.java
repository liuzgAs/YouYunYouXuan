package com.vip.uyux.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
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
import com.vip.uyux.interfacepage.OnAddPictureListener;
import com.vip.uyux.model.CePingTiJiao;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.model.RespondAppimgadd;
import com.vip.uyux.model.SimpleInfo;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GlideApp;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.ImageWidthHeight;
import com.vip.uyux.util.ImgToBase64;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.viewholder.LiJICePingViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LiJiCePingActivity extends ZjbBaseActivity implements View.OnClickListener {

    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<LocalMedia> adapter;
    private TextView textViewRight;
    int type = 0;
    private ImageView imageHead;
    private View viewHeadBg;
    private ProgressDialog progressDialog;
    List<Integer> imgsList = new ArrayList<>();
    private String compressPathHead;
    private int imageHeadID;
    private int ogID;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_li_ji_ce_ping);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        ogID = intent.getIntExtra(Constant.IntentKey.OGID, 0);
        id = intent.getIntExtra(Constant.IntentKey.ID, 0);
    }

    @Override
    protected void findID() {
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        textViewRight = (TextView) findViewById(R.id.textViewRight);

    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("立即评测");
        textViewRight.setText("发布");
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
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<LocalMedia>(LiJiCePingActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_lijipingce;
                LiJICePingViewHolder liJICePingViewHolder = new LiJICePingViewHolder(parent, layout);
                liJICePingViewHolder.setOnAddPictureListener(new OnAddPictureListener() {
                    @Override
                    public void addPicture(int position) {
                        type = position;
                        addPictureSingle();
                    }
                });
                return liJICePingViewHolder;
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {


            private EditText editTitle;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(LiJiCePingActivity.this).inflate(R.layout.header_lijiceping, null);
                imageHead = (ImageView) view.findViewById(R.id.imageHead);
                viewHeadBg = view.findViewById(R.id.viewHeadBg);
                view.findViewById(R.id.viewHead).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        type = -1;
                        addPictureSingle();
                    }
                });
                editTitle = view.findViewById(R.id.editTitle);
                return view;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });

        adapter.addFooter(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(LiJiCePingActivity.this).inflate(R.layout.footer_lijiceping, null);
                view.findViewById(R.id.viewAdd).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        type = -2;
                        PictureSelector.create(LiJiCePingActivity.this)
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
                                /*结果回调onActivityResult code*/
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                    }
                });
                return view;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
    }

    private void addPictureSingle() {
        PictureSelector.create(LiJiCePingActivity.this)
        /*全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()*/
                .openGallery(PictureMimeType.ofImage())
        /* 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE*/
                .selectionMode(PictureConfig.SINGLE)
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
        /*结果回调onActivityResult code*/
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        textViewRight.setOnClickListener(this);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> localMediaList = PictureSelector.obtainMultipleResult(data);
                    if (type == -1) {
                        viewHeadBg.setVisibility(View.GONE);
                        LocalMedia localMedia = localMediaList.get(0);
                        LogUtil.LogShitou("LiJiCePingActivity--onActivityResult", "" + localMedia.getWidth());
                        LogUtil.LogShitou("LiJiCePingActivity--onActivityResult", "" + localMedia.getHeight());
                        compressPathHead = localMedia.getCompressPath();
                        ImageWidthHeight.WidthHeight imgWidthHeigth = ImageWidthHeight.getImgWidthHeigth(compressPathHead);
                        GlideApp.with(LiJiCePingActivity.this)
                                .load(compressPathHead)
                                .centerCrop()
                                .placeholder(R.mipmap.ic_empty)
                                .into(new SimpleTarget<Drawable>(imgWidthHeigth.getWidth(), imgWidthHeigth.getHeigth()) {
                                    @Override
                                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                        imageHead.setImageDrawable(resource);
                                    }
                                });
                    } else if (type == -2) {
                        adapter.addAll(localMediaList);
                    } else {
                        final LocalMedia localMedia = localMediaList.get(0);
                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                adapter.update(localMedia, type);
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                    break;
                default:
                    break;
            }
        }
    }

    int imgSum;
    boolean isBreak = false;
    int imgSumX = 0;
    int count = 0;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textViewRight:
                imgsList.clear();
                imgSum = 0;
                isBreak = false;
                imgSumX = 0;
                count = 0;
                imgSumX = adapter.getAllData().size() + 1;
                if (adapter.getAllData().size() > 1) {
                    for (int i = 0; i < adapter.getAllData().size(); i++) {
                        if (isBreak) {
                            break;
                        }
                        imgSum++;
                        upImg(adapter.getItem(i).getCompressPath(), 1);
                    }
                }
                imgSum++;
                upImg(compressPathHead, 2);
                progressDialog = new ProgressDialog(LiJiCePingActivity.this);
                progressDialog.setTitle("上传图片");
                progressDialog.setMessage("已上传0/" + imgSumX);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setMax(imgSumX);
                progressDialog.setCancelable(false);
                progressDialog.show();
                progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                            new AlertDialog.Builder(LiJiCePingActivity.this)
                                    .setTitle("是否取消上传")
                                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            isBreak = true;
                                            progressDialog.dismiss();
                                        }
                                    }).setNegativeButton("否", null)
                                    .create()
                                    .show();
                        }
                        return false;
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

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private String getOkObject() {
        List<CePingTiJiao.ImgBean> list = new ArrayList<>();
        for (int i = 0; i < imgsList.size(); i++) {
            CePingTiJiao.ImgBean imgBean = new CePingTiJiao.ImgBean(imgsList.get(i), "内容");
            list.add(imgBean);
        }
        CePingTiJiao cePingTiJiao = new CePingTiJiao(1,"android",userInfo.getUid(),tokenTime,id,ogID,"测一测",imageHeadID,list);
        return GsonUtils.parseObject(cePingTiJiao);
    }

    /**
     * 发布
     */
    private void faBu() {
        String url = Constant.HOST + Constant.Url.EVALUATION_ADDSUBMIT;
        showLoadingDialog();
        ApiClient.postJson(LiJiCePingActivity.this,url, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("LiJiCePingActivity--onSuccess", s + "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus() == 1) {
                    } else if (simpleInfo.getStatus() == 3) {
                        MyDialog.showReLoginDialog(LiJiCePingActivity.this);
                    } else {
                        Toast.makeText(LiJiCePingActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(LiJiCePingActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(LiJiCePingActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
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
    private void upImg(String path, final int typePic) {
        showLoadingDialog();
        ApiClient.post(LiJiCePingActivity.this, getTPOkObject(path), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("GeRenXXActivity--上传图片", s + "");
                try {
                    RespondAppimgadd respondAppimgadd = GsonUtils.parseJSON(s, RespondAppimgadd.class);
                    if (respondAppimgadd.getStatus() == 1) {
                        count++;
                        progressDialog.setProgress(count);
                        progressDialog.setMessage("已上传" + count + "/" + imgSumX);
                        switch (typePic) {
                            case 1:
                                imgsList.add(respondAppimgadd.getImgId());
                                break;
                            case 2:
                                imageHeadID=respondAppimgadd.getImgId();
                                break;
                            default:
                                break;
                        }
                        if (count == imgSum) {
                            progressDialog.dismiss();
                            faBu();
                            LogUtil.LogShitou("LiJiCePingActivity--imageHeadID", ""+imageHeadID);
                            LogUtil.LogShitou("LiJiCePingActivity--imgsList", ""+GsonUtils.parseObject(imgsList));
                        }
                    } else if (respondAppimgadd.getStatus() == 3) {
                        MyDialog.showReLoginDialog(LiJiCePingActivity.this);
                    } else {
                        Toast.makeText(LiJiCePingActivity.this, respondAppimgadd.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(LiJiCePingActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(LiJiCePingActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }
}
