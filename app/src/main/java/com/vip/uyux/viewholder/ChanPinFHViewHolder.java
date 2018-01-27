package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.activity.ChanPinFenHongActivity;
import com.vip.uyux.customview.BoXingTu;
import com.vip.uyux.model.BonusGetprobonus;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;
import com.vip.uyux.util.LogUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class ChanPinFHViewHolder extends BaseViewHolder<BonusGetprobonus.GoodsListBean> {

    private final View viewShangPin;
    private final View viewBoXingTu;
    BonusGetprobonus.GoodsListBean data;
    private final ImageView imageImg;
    private final TextView textTitle;
    private final TextView textDes;
    private final TextView textPrice;
    private final TextView textTotal_money;
    private final TextView textDes1;
    private final TextView textDes2;
    private final TextView textV1;
    private final TextView textV2;
    private final BoXingTu boXingTu;

    public ChanPinFHViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        viewShangPin = $(R.id.viewShangPin);
        viewBoXingTu = $(R.id.viewBoXingTu);
        viewBoXingTu.setVisibility(View.GONE);
        viewShangPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (data.isZhanKai()) {
                    viewBoXingTu.setVisibility(View.GONE);
                    data.setZhanKai(false);
                } else {
                    viewBoXingTu.setVisibility(View.VISIBLE);
                    data.setZhanKai(true);
                }
                ((ChanPinFenHongActivity) getContext()).adapter.notifyDataSetChanged();
            }
        });
        imageImg = $(R.id.imageImg);
        textTitle = $(R.id.textTitle);
        textDes = $(R.id.textDes);
        textPrice = $(R.id.textPrice);
        textTotal_money = $(R.id.textTotal_money);
        textDes1 = $(R.id.textDes1);
        textDes2 = $(R.id.textDes2);
        textV1 = $(R.id.textV1);
        textV2 = $(R.id.textV2);
        boXingTu = $(R.id.boXingTu);
        boXingTu.setOnSelectListener(new BoXingTu.OnSelectListener() {
            @Override
            public void select(int position) {
                LogUtil.LogShitou("ChanPinFHViewHolder--select", ""+position);
                List<BonusGetprobonus.GoodsListBean.ProfitBean> profitBeanList = data.getProfit();
                textV1.setText("销量：" + profitBeanList.get(position).getV1());
                textV2.setText("收益：" + profitBeanList.get(position).getV2());
            }
        });
    }

    @Override
    public void setData(BonusGetprobonus.GoodsListBean data) {
        super.setData(data);
        this.data = data;
        GlideApp.with(getContext())
                .asBitmap()
                .centerCrop()
                .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(4, getContext())))
                .load(data.getImg())
                .into(imageImg);
        textTitle.setText(data.getGoods_title());
        textDes.setText(data.getGoods_rule());
        textPrice.setText("¥" + data.getPrice());
        textTotal_money.setText("累计分红：" + data.getTotal_money());
        textDes1.setText(data.getDes1());
        textDes2.setText(data.getDes2());
        if (data.isZhanKai()) {
            viewBoXingTu.setVisibility(View.VISIBLE);
            List<BonusGetprobonus.GoodsListBean.ProfitBean> profitBeanList = data.getProfit();
            textV1.setText("销量：" + profitBeanList.get(3).getV1());
            textV2.setText("收益：" + profitBeanList.get(3).getV2());
            String[] strings = new String[7];
            float[] floats01 = new float[7];
            float[] floats02 = new float[7];
            for (int i = 0; i < profitBeanList.size(); i++) {
                strings[i] = profitBeanList.get(i).getTitle();
                floats01[i] = (float)profitBeanList.get(i).getV1()/(float)data.getMax();
                floats02[i] = (float)profitBeanList.get(i).getV2()/(float)data.getMax();
            }
            boXingTu.setTextArr(strings);
            boXingTu.setValueArr01(floats01);
            boXingTu.setValueArr02(floats02);
        } else {
            viewBoXingTu.setVisibility(View.GONE);
        }
    }

}
