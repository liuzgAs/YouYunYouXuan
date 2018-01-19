package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;


/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class CarViewHolder extends BaseViewHolder<Integer> {

    private onDeleteListener onDeleteListener;
    private final TextView textTotalPrice;
    private final ImageView imageImg;
    private final TextView textName;
    private final TextView textNum;
    private final ImageView imageXuanZhong;
    private Integer data;

    public CarViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textName = $(R.id.textName);
        textTotalPrice = $(R.id.textTotalPrice);
        textNum = $(R.id.textNum);
        $(R.id.imageDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteListener.delete(getAdapterPosition());
            }
        });
        imageXuanZhong = $(R.id.imageXuanZhong);
//        imageXuanZhong.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (data.getSelect()) {
//                    data.setSelect(false);
//                    imageXuanZhong.setImageResource(R.mipmap.weixuanzhong);
//                } else {
//                    data.setSelect(true);
//                    imageXuanZhong.setImageResource(R.mipmap.xuanzhong);
//                }
//                Intent intent = new Intent();
//                intent.setAction(Constant.BroadcastCode.QUAN_XUAN);
//                getContext().sendBroadcast(intent);
//            }
//        });
//        $(R.id.textJian).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int number = data.getNumber();
//                if (number>1){
//                    number--;
//                }
//                editCar(number);
//            }
//        });
//        $(R.id.textJia).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int number = data.getNumber();
//                number++;
//                editCar(number);
//            }
//        });
    }


    @Override
    public void setData(Integer data) {
        super.setData(data);
        this.data = data;
//        if (data != null) {
//            Glide.with(getContext())
//                    .load(data.getGoods_thumb())
//                    .placeholder(R.mipmap.ic_empty)
//                    .into(imageImg);
//            textName.setText(data.getGoods_name());
//            SpannableString span = new SpannableString("¥" + data.getSum());
//            span.setSpan(new RelativeSizeSpan(0.65f), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            textTotalPrice.setText(span);
//            SpannableString span1 = new SpannableString("¥" + data.getPrice() + "+" + data.getPoint() + "蚁豆");
//            span1.setSpan(new RelativeSizeSpan(0.65f), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            textPriceAntPulse.setText(span1);
//            textNum.setText(data.getNumber() + "");
//            if (data.getSelect()) {
//                imageXuanZhong.setImageResource(R.mipmap.xuanzhong);
//            } else {
//                imageXuanZhong.setImageResource(R.mipmap.weixuanzhong);
//            }
//        }
    }

    public void setOnDeleteListener(onDeleteListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }

    public interface onDeleteListener {
        void delete(int position);
    }
}
