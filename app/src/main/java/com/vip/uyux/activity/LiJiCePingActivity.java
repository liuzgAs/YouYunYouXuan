package com.vip.uyux.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.customview.SingleBtnDialog;
import com.vip.uyux.interfacepage.OnAddPictureListener;
import com.vip.uyux.model.CePingTiJiao;
import com.vip.uyux.model.EvaluationAddbefore;
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
    private RecyclerArrayAdapter<EvaluationAddbefore.ImgsBean> adapter;
    private TextView textViewRight;
    int type = 0;
    private ProgressDialog progressDialog;
    private int ogID;
    private int id;
    private EvaluationAddbefore evaluationAddbefore;

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
        textViewRight.setVisibility(View.GONE);
        initRecycler();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<EvaluationAddbefore.ImgsBean>(LiJiCePingActivity.this) {
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

            private ImageView imageHead;
            private View viewHeadBg;
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
                editTitle.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        evaluationAddbefore.setTitle(editable.toString().trim());
                    }
                });
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (evaluationAddbefore!=null){
                    if (evaluationAddbefore.getImgBean() != null) {
                        viewHeadBg.setVisibility(View.GONE);
                        String compressPathHead = evaluationAddbefore.getImgBean().getCompressPath();
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
                    } else {
                        if (!TextUtils.isEmpty(evaluationAddbefore.getImg_url())) {
                            viewHeadBg.setVisibility(View.GONE);
                            GlideApp.with(LiJiCePingActivity.this)
                                    .load(evaluationAddbefore.getImg_url())
                                    .centerCrop()
                                    .placeholder(R.mipmap.ic_empty)
                                    .into(new SimpleTarget<Drawable>(evaluationAddbefore.getImg_w(), evaluationAddbefore.getImg_h()) {
                                        @Override
                                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                            imageHead.setImageDrawable(resource);
                                        }
                                    });
                        }
                    }
                    editTitle.setText(evaluationAddbefore.getTitle());
                }
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

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getInitOkObject() {
        String url = Constant.HOST + Constant.Url.EVALUATION_ADDBEFORE;
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
        ApiClient.post(this, getInitOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("", s);
                try {
                    evaluationAddbefore = GsonUtils.parseJSON(s, EvaluationAddbefore.class);
                    if (evaluationAddbefore.getStatus() == 1) {
                        textViewRight.setVisibility(View.VISIBLE);
                        adapter.clear();
                        List<EvaluationAddbefore.ImgsBean> imgsBeanList = evaluationAddbefore.getImgs();
                        adapter.addAll(imgsBeanList);
                    } else if (evaluationAddbefore.getStatus() == 3) {
                        MyDialog.showReLoginDialog(LiJiCePingActivity.this);
                    } else {
                        showError(evaluationAddbefore.getInfo());
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
                    View viewLoader = LayoutInflater.from(LiJiCePingActivity.this).inflate(R.layout.view_loaderror, null);
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
                    if (type == -1) {
                        LocalMedia localMedia = localMediaList.get(0);
                        evaluationAddbefore.setImgBean(localMedia);
                        adapter.notifyDataSetChanged();
                    } else if (type == -2) {
                        for (int i = 0; i < localMediaList.size(); i++) {
                            EvaluationAddbefore.ImgsBean imgsBean = new EvaluationAddbefore.ImgsBean(0, "", localMediaList.get(i));
                            adapter.add(imgsBean);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        final LocalMedia localMedia = localMediaList.get(0);
                        adapter.getItem(type).setImgBean(localMedia);
                        adapter.notifyDataSetChanged();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    int imgSum;
    boolean isBreak = false;
    int count = 0;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textViewRight:
                if (TextUtils.isEmpty(evaluationAddbefore.getImg()) && evaluationAddbefore.getImgBean() == null) {
                    Toast.makeText(LiJiCePingActivity.this, "请选择封面", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(evaluationAddbefore.getTitle())){
                    Toast.makeText(LiJiCePingActivity.this, "请输入标题", Toast.LENGTH_SHORT).show();
                    return;
                }
                imgSum = 0;
                isBreak = false;
                count = 0;
                if (adapter.getAllData().size() > 1) {
                    for (int i = 0; i < adapter.getAllData().size(); i++) {
                        if (isBreak) {
                            break;
                        }
                        if (adapter.getItem(i).getImgBean()!=null){
                            imgSum++;
                            upImg(adapter.getItem(i).getImgBean().getCompressPath(), 1,i);
                        }
                    }
                }
                if (evaluationAddbefore.getImgBean() != null) {
                    imgSum++;
                    upImg(evaluationAddbefore.getImgBean().getCompressPath(), 2,0);
                }
                if (imgSum>0){
                    progressDialog = new ProgressDialog(LiJiCePingActivity.this);
                    progressDialog.setTitle("上传图片");
                    progressDialog.setMessage("已上传0/" + imgSum);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.setMax(imgSum);
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
                }else {
                   faBu();
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
    private String getOkObject() {
        List<CePingTiJiao.ImgBean> list = new ArrayList<>();
        for (int i = 0; i < adapter.getAllData().size(); i++) {
            CePingTiJiao.ImgBean imgBean = new CePingTiJiao.ImgBean(adapter.getItem(i).getImg(), adapter.getItem(i).getContent());
            list.add(imgBean);
        }
        CePingTiJiao cePingTiJiao = new CePingTiJiao(1, "android", userInfo.getUid(), tokenTime, evaluationAddbefore.getId(), ogID, evaluationAddbefore.getTitle(), evaluationAddbefore.getImg(), list);
        return GsonUtils.parseObject(cePingTiJiao);
    }

    /**
     * 发布
     */
    private void faBu() {
        String url = Constant.HOST + Constant.Url.EVALUATION_ADDSUBMIT;
        showLoadingDialog();
        ApiClient.postJson(LiJiCePingActivity.this, url, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("LiJiCePingActivity--onSuccess", s + "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus() == 1) {
                        Intent intent1 = new Intent();
                        intent1.setAction(Constant.BroadcastCode.SHUA_XIN_CE_PING);
                        sendBroadcast(intent1);
                        final SingleBtnDialog singleBtnDialog = new SingleBtnDialog(LiJiCePingActivity.this, "发布成功", "确认");
                        singleBtnDialog.show();
                        singleBtnDialog.setClicklistener(new SingleBtnDialog.ClickListenerInterface() {
                            @Override
                            public void doWhat() {
                                singleBtnDialog.dismiss();
                                if (id==0){
                                    Intent intent = new Intent();
                                    intent.setClass(LiJiCePingActivity.this,WoDeCPActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    finish();
                                }
                            }
                        });
                        singleBtnDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                            @Override
                            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                                    dialog.dismiss();
                                    if (id==0){
                                        Intent intent = new Intent();
                                        intent.setClass(LiJiCePingActivity.this,WoDeCPActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else {
                                        finish();
                                    }
                                }
                                return false;
                            }
                        });
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
    private void upImg(String path, final int typePic, final int position) {
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
                        progressDialog.setMessage("已上传" + count + "/" + imgSum);
                        switch (typePic) {
                            case 1:
                                adapter.getItem(position).setImg(respondAppimgadd.getImgId());
                                break;
                            case 2:
                                evaluationAddbefore.setImg(String.valueOf(respondAppimgadd.getImgId()));
                                break;
                            default:
                                break;
                        }
                        if (count == imgSum) {
                            progressDialog.dismiss();
                            faBu();
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
