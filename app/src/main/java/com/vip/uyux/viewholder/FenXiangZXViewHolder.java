package com.vip.uyux.viewholder;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.activity.BuKeTiXianActivity;
import com.vip.uyux.activity.FenHongZXActivity;
import com.vip.uyux.activity.FenXiaoDDActivity;
import com.vip.uyux.activity.WebActivity;
import com.vip.uyux.activity.WoDeKeHuActivity;
import com.vip.uyux.activity.WoDeTDActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.ShareBean;
import com.vip.uyux.model.ShareIndex;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class FenXiangZXViewHolder extends BaseViewHolder<ShareIndex> implements View.OnClickListener {
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
    private String yuji;
    private ShareBean teamShare;
    private ShareBean vipShare;
    private String college_url;
    private String fenxiao;
    private View lineBuKeTX;
    private View viewBuKeTiXian;
    private OnShareListener onShareListener;

    public FenXiangZXViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = (ImageView) $(R.id.imageImg);
        textName = (TextView) $(R.id.textName);
        textDate = (TextView) $(R.id.textDate);
        textGrade_name = (TextView) $(R.id.textGrade_name);
        textLiJiSJ = (TextView) $(R.id.textLiJiSJ);
        textKeTiXian = (TextView) $(R.id.textKeTiXian);
        textBuKeTiXian = (TextView) $(R.id.textBuKeTiXian);
        textYuJi = (TextView) $(R.id.textYuJi);
        textFenXiaoYJ = (TextView) $(R.id.textFenXiaoYJ);
        textFengXiaoDD = (TextView) $(R.id.textFengXiaoDD);
        textTuanDui = (TextView) $(R.id.textTuanDui);
        textKeHu = (TextView) $(R.id.textKeHu);
        lineBuKeTX = $(R.id.lineBuKeTX);
        viewBuKeTiXian = $(R.id.viewBuKeTiXian);
        $(R.id.viewFenHongZX).setOnClickListener(this);
        viewBuKeTiXian.setOnClickListener(this);
        $(R.id.viewWoDeKeHu).setOnClickListener(this);
        $(R.id.viewWoDeTD).setOnClickListener(this);
        $(R.id.viewKeTiXian).setOnClickListener(this);
        textLiJiSJ.setOnClickListener(this);
        $(R.id.viewFenXiaoYJ).setOnClickListener(this);
        $(R.id.viewYouYunSXY).setOnClickListener(this);
        $(R.id.viewYuJiYJ).setOnClickListener(this);
        $(R.id.viewFenXiaoDD).setOnClickListener(this);
        $(R.id.viewTuanDui).setOnClickListener(this);
        $(R.id.viewVip).setOnClickListener(this);
    }

    @Override
    public void setData(ShareIndex data) {
        super.setData(data);
        GlideApp.with(getContext())
                .load(data.getHeadimg())
                .centerCrop()
                .placeholder(R.mipmap.ic_empty)
                .into(imageImg);
        textName.setText(data.getNickname());
        textDate.setText(data.getDes());
        textGrade_name.setText(data.getGrade_name());
        if (data.getIs_up() == 1) {
            textLiJiSJ.setVisibility(View.VISIBLE);
            lineBuKeTX.setVisibility(View.VISIBLE);
            viewBuKeTiXian.setVisibility(View.VISIBLE);
        } else {
            textLiJiSJ.setVisibility(View.GONE);
            lineBuKeTX.setVisibility(View.GONE);
            viewBuKeTiXian.setVisibility(View.GONE);
        }
        up_url = data.getUp_url();
        textKeTiXian.setText(data.getMoney().get(0));
        textBuKeTiXian.setText(data.getMoney().get(1));
        yuji = data.getMoney().get(2);
        textYuJi.setText(String.valueOf(yuji));
        fenxiao = data.getNum().get(1);
        String d2 = data.getNum().get(2);
        String d3 = data.getNum().get(3);
        textFenXiaoYJ.setText(String.valueOf(data.getNum().get(0)));
        textFengXiaoDD.setText(fenxiao);
        textTuanDui.setText(d2);
        textKeHu.setText(d3);
        teamShare = data.getTeamShare();
        vipShare = data.getVipShare();
        college_url = data.getCollege_url();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.viewVip:
                if (teamShare != null) {
                    onShareListener.share(teamShare);
                }
                break;
            case R.id.viewTuanDui:
                if (vipShare != null) {
                    onShareListener.share(vipShare);
                }
                break;
            case R.id.textLiJiSJ:
                intent.setClass(getContext(), WebActivity.class);
                intent.putExtra(Constant.IntentKey.TITLE, "立即升级");
                intent.putExtra(Constant.IntentKey.URL, up_url);
                getContext().startActivity(intent);
                break;
            case R.id.viewYouYunSXY:
                intent.setClass(getContext(), WebActivity.class);
                intent.putExtra(Constant.IntentKey.TITLE, "优云商学院");
                intent.putExtra(Constant.IntentKey.URL, college_url);
                getContext().startActivity(intent);
                break;
            case R.id.viewFenXiaoDD:
                intent.setClass(getContext(), FenXiaoDDActivity.class);
                intent.putExtra(Constant.IntentKey.TYPE, 2);
                getContext().startActivity(intent);
                break;
            case R.id.viewYuJiYJ:
                intent.setClass(getContext(), FenXiaoDDActivity.class);
                intent.putExtra(Constant.IntentKey.TYPE, 1);
                getContext().startActivity(intent);
                break;
            case R.id.viewFenXiaoYJ:
                intent.setClass(getContext(), BuKeTiXianActivity.class);
                intent.putExtra(Constant.IntentKey.TYPE, 3);
                getContext().startActivity(intent);
                break;
            case R.id.viewKeTiXian:
                intent.setClass(getContext(), BuKeTiXianActivity.class);
                intent.putExtra(Constant.IntentKey.TYPE, 2);
                getContext().startActivity(intent);
                break;
            case R.id.viewWoDeTD:
                intent.setClass(getContext(), WoDeTDActivity.class);
                getContext().startActivity(intent);
                break;
            case R.id.viewWoDeKeHu:
                intent.setClass(getContext(), WoDeKeHuActivity.class);
                getContext().startActivity(intent);
                break;
            case R.id.viewBuKeTiXian:
                intent.setClass(getContext(), BuKeTiXianActivity.class);
                intent.putExtra(Constant.IntentKey.TYPE, 1);
                getContext().startActivity(intent);
                break;
            case R.id.viewFenHongZX:
                intent.setClass(getContext(), FenHongZXActivity.class);
                getContext().startActivity(intent);
                break;
            default:
                break;
        }
    }

    public void setOnShareListener(OnShareListener onShareListener) {
        this.onShareListener = onShareListener;
    }

    public interface OnShareListener{
        void share(ShareBean shareBean);
    }
}
