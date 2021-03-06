package com.vip.uyux.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.luoxudong.app.threadpool.ThreadPoolHelp;
import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseNotLeftActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.IndexStartad;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.util.ACache;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.util.VersionUtils;

import java.util.HashMap;

/**
 * web网页
 * app_i主页
 * app_goods_info 商品详情页（配item_id）
 * app_user_msg 用户消息页
 * app_goods_pcate 商品列表页（配item_id  当pcate传）
 * app_goods_cate 商品列表页（配item_id  当cate传）
 */
public class HuanYingActivity extends ZjbBaseNotLeftActivity {
    private static final int LOCATION = 1991;
    private String isFirst = "1";
    private long currentTimeMillis;
    private int GPS_REQUEST_CODE = 10;
    private ImageView imageImg;
    private TextView textDaoJiShi;
    private boolean isBreak = true;
    private int daoJiShi =3;

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.INDEX_STARTAD;
        HashMap<String, String> params = new HashMap<>();
        params.put("isFirst", isFirst);
        params.put("deviceId", PushServiceFactory.getCloudPushService().getDeviceId());
        params.put("version", VersionUtils.getCurrVersionName(this));
        params.put("intro", "model=" + android.os.Build.MODEL + "sdk=" + android.os.Build.VERSION.SDK);
        params.put("mid", "");
        params.put("lng", "");
        params.put("lat", "");
        return new OkObject(params, url);
    }

    private void getData() {
        ApiClient.post(HuanYingActivity.this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("HuanYingActivity--onSuccess", "");
                try {
                    final IndexStartad indexStartad = GsonUtils.parseJSON(s, IndexStartad.class);
                    if ((System.currentTimeMillis() - currentTimeMillis) < 1000) {

                        if (indexStartad.getStatus() == 1) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1000);
                                        go(indexStartad);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();
                        } else {
                            MyDialog.dialogFinish(HuanYingActivity.this, indexStartad.getInfo());
                        }
                    } else {
                        go(indexStartad);
                    }
                } catch (Exception e) {
                    MyDialog.dialogFinish(HuanYingActivity.this, "数据出错");
                }

            }

            private void go(final IndexStartad indexStartad) {
                ACache aCache = ACache.get(HuanYingActivity.this, Constant.Acache.LOCATION);
                aCache.put(Constant.Acache.DID, indexStartad.getDid() + "");
                if (TextUtils.equals(isFirst, "1")) {
                    Intent intent = new Intent(HuanYingActivity.this, YinDaoActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    if (indexStartad.getAdvs().size() > 0) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                RequestOptions options = new RequestOptions();
                                options.centerCrop()
                                        .placeholder(R.mipmap.qidong1)
                                        .error(R.mipmap.qidong1);
                                Glide.with(HuanYingActivity.this)
                                        .load(indexStartad.getAdvs().get(0).getImg())
                                        .apply(options)
                                        .listener(new RequestListener<Drawable>() {
                                            @Override
                                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                                return false;
                                            }

                                            @Override
                                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                                textDaoJiShi.setVisibility(View.VISIBLE);
                                                textDaoJiShi.setText(daoJiShi+"s");
                                                textDaoJiShi.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        isBreak = false;
                                                        toMainActivity();
                                                    }
                                                });
                                                imageImg.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        if (!TextUtils.isEmpty(indexStartad.getAdvs().get(0).getCode())){
                                                            isBreak = false;
                                                            Intent intent = new Intent();
                                                            intent.setClass(HuanYingActivity.this, MainActivity.class);
                                                            intent.putExtra(Constant.IntentKey.BEAN,indexStartad.getAdvs().get(0));
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                    }
                                                });
                                                ThreadPoolHelp.Builder
                                                        .cached()
                                                        .builder()
                                                        .execute(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                try {
                                                                    while(isBreak){
                                                                        Thread.sleep(1000);
                                                                        daoJiShi--;
                                                                        runOnUiThread(new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                textDaoJiShi.setText(daoJiShi+"s");
                                                                            }
                                                                        });
                                                                        if (daoJiShi==0){
                                                                            isBreak=false;
                                                                            toMainActivity();
                                                                        }
                                                                    }
                                                                } catch (InterruptedException e) {
                                                                    e.printStackTrace();
                                                                }
                                                            }
                                                        });
                                                return false;
                                            }
                                        })
                                        .transition(new DrawableTransitionOptions().crossFade(1000))
                                        .into(imageImg);
                            }
                        });
                    } else {
                        toMainActivity();
                    }
                }
            }

            @Override
            public void onError() {
                try {
                    MyDialog.dialogFinish(HuanYingActivity.this, "请求失败");
                } catch (Exception e) {
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huan_ying);
        init();
    }


    @Override
    protected void initSP() {
        ACache aCache = ACache.get(HuanYingActivity.this, Constant.Acache.FRIST);
        String frist = aCache.getAsString(Constant.Acache.FRIST);
        if (frist != null) {
            isFirst = frist;
        }
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void findID() {
        imageImg = findViewById(R.id.imageImg);
        textDaoJiShi = findViewById(R.id.textDaoJiShi);
    }

    @Override
    protected void initViews() {
        textDaoJiShi.setVisibility(View.GONE);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void initData() {
        currentTimeMillis = System.currentTimeMillis();
        getData();
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void toMainActivity() {
        Intent intent = new Intent();
//        if (isLogin) {
        intent.setClass(HuanYingActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
//        } else {
//            intent.setClass(HuanYingActivity.this, DengLuActivity.class);
//            startActivity(intent);
//            finish();
//        }
    }

}
