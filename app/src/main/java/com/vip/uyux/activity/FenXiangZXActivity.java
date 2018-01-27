package com.vip.uyux.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.model.ShareIndex;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GlideApp;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;

import java.util.HashMap;

public class FenXiangZXActivity extends ZjbBaseActivity implements View.OnClickListener {

    private ImageView imageImg;
    private TextView textName;
    private TextView textDate;
    private TextView textGrade_name;
    private TextView textLiJiSJ;
    private TextView textKeTiXian;
    private TextView textBuKeTiXian;
    private TextView textYuJi;
    private TextView textFenXiaoYJ;
    private TextView textFengXiaoDD;
    private TextView textTuanDui;
    private TextView textKeHu;
    private String up_url;
    private Double yuji;
    private IWXAPI api;
    private ShareIndex.TeamShareBean teamShare;
    private ShareIndex.VipShareBean vipShare;
    private String college_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fen_xiang_zx);
        api = WXAPIFactory.createWXAPI(this, Constant.WXAPPID, true);
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
        imageImg = (ImageView) findViewById(R.id.imageImg);
        textName = (TextView) findViewById(R.id.textName);
        textDate = (TextView) findViewById(R.id.textDate);
        textGrade_name = (TextView) findViewById(R.id.textGrade_name);
        textLiJiSJ = (TextView) findViewById(R.id.textLiJiSJ);
        textKeTiXian = (TextView) findViewById(R.id.textKeTiXian);
        textBuKeTiXian = (TextView) findViewById(R.id.textBuKeTiXian);
        textYuJi = (TextView) findViewById(R.id.textYuJi);
        textFenXiaoYJ = (TextView) findViewById(R.id.textFenXiaoYJ);
        textFengXiaoDD = (TextView) findViewById(R.id.textFengXiaoDD);
        textTuanDui = (TextView) findViewById(R.id.textTuanDui);
        textKeHu = (TextView) findViewById(R.id.textKeHu);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void setListeners() {
        findViewById(R.id.viewFenHongZX).setOnClickListener(this);
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.viewBuKeTiXian).setOnClickListener(this);
        findViewById(R.id.viewWoDeKeHu).setOnClickListener(this);
        findViewById(R.id.viewWoDeTD).setOnClickListener(this);
        findViewById(R.id.viewKeTiXian).setOnClickListener(this);
        findViewById(R.id.textLiJiSJ).setOnClickListener(this);
        findViewById(R.id.viewFenXiaoYJ).setOnClickListener(this);
        findViewById(R.id.viewYouYunSXY).setOnClickListener(this);
        findViewById(R.id.viewYuJiYJ).setOnClickListener(this);
        findViewById(R.id.viewFenXiaoDD).setOnClickListener(this);
        findViewById(R.id.viewTuanDui).setOnClickListener(this);
        findViewById(R.id.viewVip).setOnClickListener(this);
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.SHARE_INDEX;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        return new OkObject(params, url);
    }

    @Override
    protected void initData() {
        showLoadingDialog();
        ApiClient.post(FenXiangZXActivity.this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("FenXiangZXActivity--onSuccess", s + "");
                try {
                    ShareIndex shareIndex = GsonUtils.parseJSON(s, ShareIndex.class);
                    if (shareIndex.getStatus() == 1) {
                        GlideApp.with(FenXiangZXActivity.this)
                                .load(shareIndex.getHeadimg())
                                .centerCrop()
                                .placeholder(R.mipmap.ic_empty)
                                .into(imageImg);
                        textName.setText(shareIndex.getNickname());
                        textDate.setText(shareIndex.getDes());
                        textGrade_name.setText(shareIndex.getGrade_name());
                        if (shareIndex.getIs_up() == 1) {
                            textLiJiSJ.setVisibility(View.VISIBLE);
                        } else {
                            textLiJiSJ.setVisibility(View.GONE);
                        }
                        up_url = shareIndex.getUp_url();
                        textKeTiXian.setText(String.valueOf(shareIndex.getMoney().get(0)));
                        textBuKeTiXian.setText(String.valueOf(shareIndex.getMoney().get(1)));
                        yuji = shareIndex.getMoney().get(2);
                        textYuJi.setText(String.valueOf(yuji));
                        double d1 = shareIndex.getNum().get(1);
                        double d2 = shareIndex.getNum().get(2);
                        double d3 = shareIndex.getNum().get(3);
                        textFenXiaoYJ.setText(String.valueOf(shareIndex.getNum().get(0)));
                        textFengXiaoDD.setText(String.valueOf((int) d1));
                        textTuanDui.setText(String.valueOf((int) d2));
                        textKeHu.setText(String.valueOf((int) d3));
                        teamShare = shareIndex.getTeamShare();
                        vipShare = shareIndex.getVipShare();
                        college_url = shareIndex.getCollege_url();
                    } else if (shareIndex.getStatus() == 3) {
                        MyDialog.showReLoginDialog(FenXiangZXActivity.this);
                    } else {
                        Toast.makeText(FenXiangZXActivity.this, shareIndex.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(FenXiangZXActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(FenXiangZXActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.viewVip:
                if (teamShare != null) {
                    MyDialog.share01(this, api, teamShare.getShareUrl(), teamShare.getShareImg(), teamShare.getShareTitle(), teamShare.getShareDes());
                }
                break;
            case R.id.viewTuanDui:
                if (vipShare != null) {
                    MyDialog.share01(this, api, vipShare.getShareUrl(), vipShare.getShareImg(), vipShare.getShareTitle(), vipShare.getShareDes());
                }
                break;
            case R.id.textLiJiSJ:
                intent.setClass(FenXiangZXActivity.this, WebActivity.class);
                intent.putExtra(Constant.IntentKey.TITLE, "立即升级");
                intent.putExtra(Constant.IntentKey.URL, up_url);
                startActivity(intent);
                break;
            case R.id.viewYouYunSXY:
                intent.setClass(FenXiangZXActivity.this, WebActivity.class);
                intent.putExtra(Constant.IntentKey.TITLE, "优云商学院");
                intent.putExtra(Constant.IntentKey.URL, college_url);
                startActivity(intent);
//                intent.setClass(this, ChangJianWenTiActivity.class);
//                intent.putExtra(Constant.IntentKey.TYPE, 2);
//                startActivity(intent);
                break;
            case R.id.viewFenXiaoDD:
                intent.setClass(this, FenXiaoDDActivity.class);
                intent.putExtra(Constant.IntentKey.VALUE, String.valueOf(yuji));
                startActivity(intent);
                break;
            case R.id.viewYuJiYJ:
                intent.setClass(this, FenXiaoDDActivity.class);
                intent.putExtra(Constant.IntentKey.VALUE, String.valueOf(yuji));
                startActivity(intent);
                break;
            case R.id.viewFenXiaoYJ:
                intent.setClass(this, BuKeTiXianActivity.class);
                intent.putExtra(Constant.IntentKey.TYPE, 3);
                startActivity(intent);
                break;
            case R.id.viewKeTiXian:
                intent.setClass(this, BuKeTiXianActivity.class);
                intent.putExtra(Constant.IntentKey.TYPE, 2);
                startActivity(intent);
                break;
            case R.id.viewWoDeTD:
                intent.setClass(this, WoDeTDActivity.class);
                startActivity(intent);
                break;
            case R.id.viewWoDeKeHu:
                intent.setClass(this, WoDeKeHuActivity.class);
                startActivity(intent);
                break;
            case R.id.viewBuKeTiXian:
                intent.setClass(this, BuKeTiXianActivity.class);
                intent.putExtra(Constant.IntentKey.TYPE, 1);
                startActivity(intent);
                break;
            case R.id.viewFenHongZX:
                intent.setClass(this, FenHongZXActivity.class);
                startActivity(intent);
                break;
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }
}
