package com.vip.uyux.viewholder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.vip.uyux.R;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.AdvsBean;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;

public class HaiTaoBannerImgHolderView implements Holder<AdvsBean> {
    private ImageView imageView;
    AdvsBean data;

    public HaiTaoBannerImgHolderView() {
    }

    @Override
    public View createView(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_index_viewpager, null);
        imageView = view.findViewById(R.id.imageImg);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(Constant.IntentKey.BEAN, data);
                intent.setAction(Constant.BroadcastCode.ADV);
                context.sendBroadcast(intent);
            }
        });
        return view;
    }

    @Override
    public void UpdateUI(final Context context, final int position, AdvsBean data) {
        this.data = data;
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(12, context)))
                .load(data.getImg())
                .into(imageView);
    }
}