package com.vip.uyux.viewholder;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.activity.ChanPinFenHongActivity;
import com.vip.uyux.activity.ChangJianWenTiActivity;
import com.vip.uyux.activity.WebActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.interfacepage.OnCallPhoneListener;
import com.vip.uyux.model.Bonus;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class FenHongZXViewHolder extends BaseViewHolder<Bonus> implements View.OnClickListener {
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
    private ImageView imageRight01;
    private ImageView imageRight02;
    Bonus data;
    private final TextView textTitle01;
    private final TextView textTitle02;
    private final TextView textDes0001;
    private final TextView textDes0002;
    private final TextView textDes0101;
    private final TextView textDes0102;
    private OnCallPhoneListener onCallPhoneListener;

    public FenHongZXViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        viewFenHong01 = $(R.id.viewFenHong01);
        viewFenHong02 = $(R.id.viewFenHong02);
        viewQuanXian01 = $(R.id.viewQuanXian01);
        viewQuanXian02 = $(R.id.viewQuanXian02);
        imageImg = (ImageView) $(R.id.imageImg);
        textName = (TextView) $(R.id.textName);
        textLv = (TextView) $(R.id.textLv);
        textDate = (TextView) $(R.id.textDate);
        textTotal_money = (TextView) $(R.id.textTotal_money);
        textY_money = (TextView) $(R.id.textY_money);
        textL_money = (TextView) $(R.id.textL_money);
        textZ_money = (TextView) $(R.id.textZ_money);
        imageRight01 = (ImageView) $(R.id.imageRight01);
        imageRight02 = (ImageView) $(R.id.imageRight02);
        textTitle01 = $(R.id.textTitle01);
        textTitle02 = $(R.id.textTitle02);
        textDes0001 = $(R.id.textDes0001);
        textDes0002 = $(R.id.textDes0002);
        textDes0101 = $(R.id.textDes0101);
        textDes0102 = $(R.id.textDes0102);
        $(R.id.viewChanPinFH).setOnClickListener(this);
        $(R.id.viewZhiTuiFH).setOnClickListener(this);
        $(R.id.textChangJianWT).setOnClickListener(this);
        $(R.id.textTel).setOnClickListener(this);
    }

    @Override
    public void setData(Bonus data) {
        super.setData(data);
        this.data = data;
        GlideApp.with(getContext())
                .load(data.getImg())
                .centerCrop()
                .dontAnimate()
                .placeholder(R.mipmap.ic_empty)
                .into(imageImg);
        textName.setText(data.getName());
        textLv.setText(data.getGrade_name());
        textDate.setText(data.getCreate_time());
        textTotal_money.setText(data.getProduct_bonus().getNum1());
        textY_money.setText(data.getProduct_bonus().getNum2());
        textL_money.setText(data.getDirect_bonus().getNum1());
        textZ_money.setText(data.getDirect_bonus().getNum2());
        textTitle01.setText(data.getProduct_bonus().getTitle());
        textTitle02.setText(data.getDirect_bonus().getTitle());
        textDes0001.setText(data.getProduct_bonus().getDes1());
        textDes0002.setText(data.getProduct_bonus().getDes2());
        textDes0101.setText(data.getDirect_bonus().getDes1());
        textDes0102.setText(data.getDirect_bonus().getDes2());
        if (data.getProduct_bonus().getIs_up() == 1) {
            viewFenHong01.setVisibility(View.GONE);
            imageRight01.setVisibility(View.GONE);
            viewQuanXian01.setVisibility(View.VISIBLE);
        } else {
            viewFenHong01.setVisibility(View.VISIBLE);
            imageRight01.setVisibility(View.VISIBLE);
            viewQuanXian01.setVisibility(View.GONE);
        }
        if (data.getDirect_bonus().getIs_up() == 1) {
            viewFenHong01.setVisibility(View.GONE);
            imageRight02.setVisibility(View.GONE);
            viewQuanXian02.setVisibility(View.VISIBLE);
        } else {
            viewFenHong02.setVisibility(View.VISIBLE);
            imageRight02.setVisibility(View.VISIBLE);
            viewQuanXian02.setVisibility(View.GONE);
        }
    }

    public void setOnCallPhoneListener(OnCallPhoneListener onCallPhoneListener) {
        this.onCallPhoneListener = onCallPhoneListener;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.textTel:
                onCallPhoneListener.callPhone(data.getTel());
                break;
            case R.id.textChangJianWT:
                intent.setClass(getContext(), ChangJianWenTiActivity.class);
                intent.putExtra(Constant.IntentKey.TYPE,1);
                getContext().startActivity(intent);
                break;
            case R.id.viewZhiTuiFH:
                if (data.getDirect_bonus().getIs_up()==1){
                    intent.setClass(getContext(), WebActivity.class);
                    intent.putExtra(Constant.IntentKey.TITLE, data.getUrl_title());
                    intent.putExtra(Constant.IntentKey.URL, data.getUp_url());
                    getContext().startActivity(intent);
                }else {

                }
                break;
            case R.id.viewChanPinFH:
                if (data.getProduct_bonus().getIs_up()==1){
                    intent.setClass(getContext(), WebActivity.class);
                    intent.putExtra(Constant.IntentKey.TITLE, data.getUrl_title());
                    intent.putExtra(Constant.IntentKey.URL, data.getUp_url());
                    getContext().startActivity(intent);
                }else {
                    intent.setClass(getContext(), ChanPinFenHongActivity.class);
                    getContext().startActivity(intent);
                }
                break;
            default:
                break;
        }
    }
}
