package com.vip.uyux.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vip.uyux.R;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.Bonus;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GlideApp;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;

import java.util.HashMap;

public class FenHongZXActivity extends ZjbBaseActivity implements View.OnClickListener {

    private View viewFenHong01;
    private View viewFenHong02;
    private View viewQuanXian01;
    private View viewQuanXian02;
    private ImageView imageImg;
    private TextView textName;
    private TextView textLv;
    private TextView textDate;
    private TextView textTotal_money;
    private TextView textY_money;
    private TextView textL_money;
    private TextView textZ_money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fen_hong_zx);
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
        viewFenHong01 = findViewById(R.id.viewFenHong01);
        viewFenHong02 = findViewById(R.id.viewFenHong02);
        viewQuanXian01 = findViewById(R.id.viewQuanXian01);
        viewQuanXian02 = findViewById(R.id.viewQuanXian02);
        imageImg = (ImageView) findViewById(R.id.imageImg);
        textName = (TextView) findViewById(R.id.textName);
        textLv = (TextView) findViewById(R.id.textLv);
        textDate = (TextView) findViewById(R.id.textDate);
        textTotal_money = (TextView) findViewById(R.id.textTotal_money);
        textY_money = (TextView) findViewById(R.id.textY_money);
        textL_money = (TextView) findViewById(R.id.textL_money);
        textZ_money = (TextView) findViewById(R.id.textZ_money);
    }

    @Override
    protected void initViews() {
        viewFenHong01.setVisibility(View.GONE);
        viewFenHong02.setVisibility(View.GONE);
        viewQuanXian01.setVisibility(View.VISIBLE);
        viewQuanXian02.setVisibility(View.VISIBLE);
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
        String url = Constant.HOST + Constant.Url.BONUS;
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
        ApiClient.post(FenHongZXActivity.this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("FenHongZXActivity--onSuccess", s + "");
                try {
                    Bonus bonus = GsonUtils.parseJSON(s, Bonus.class);
                    if (bonus.getStatus() == 1) {
                        GlideApp.with(FenHongZXActivity.this)
                                .load(bonus.getImg())
                                .centerCrop()
                                .dontAnimate()
                                .placeholder(R.mipmap.ic_empty)
                                .into(imageImg);
                        textName.setText(bonus.getName());
                        textLv.setText(bonus.getGrade_name());
                        textDate.setText(bonus.getCreate_time());
                        textTotal_money.setText(bonus.getProduct_bonus().getTotal_money());
                        textY_money.setText(bonus.getProduct_bonus().getY_money());
                        textL_money.setText(bonus.getDirect_bonus().getL_money());
                        textZ_money.setText(bonus.getDirect_bonus().getZ_money());
                        if (bonus.getProduct_bonus().getIs_up()==1){
                            viewFenHong01.setVisibility(View.VISIBLE);
                        }else {
                            viewFenHong01.setVisibility(View.GONE);
                        }
                        if (bonus.getDirect_bonus().getIs_up()==1){
                            viewFenHong02.setVisibility(View.VISIBLE);
                        }else {
                            viewFenHong01.setVisibility(View.GONE);
                        }
                    } else if (bonus.getStatus() == 3) {
                        MyDialog.showReLoginDialog(FenHongZXActivity.this);
                    } else {
                        Toast.makeText(FenHongZXActivity.this, bonus.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(FenHongZXActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(FenHongZXActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
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
}
