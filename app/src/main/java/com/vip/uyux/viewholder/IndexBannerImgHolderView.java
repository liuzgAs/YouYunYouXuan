package com.vip.uyux.viewholder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.vip.uyux.R;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.AdvsBean;
import com.vip.uyux.util.GlideApp;

public class IndexBannerImgHolderView implements Holder<AdvsBean> {
    private ImageView imageView;
    AdvsBean data;

    public IndexBannerImgHolderView() {
    }

    @Override
    public View createView(final Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(Constant.IntentKey.BEAN,data);
                intent.setAction(Constant.BroadcastCode.ADV);
                context.sendBroadcast(intent);
            }
        });
        return imageView;
    }

    @Override
    public void UpdateUI(final Context context, final int position, AdvsBean data) {
        this.data=data;
        GlideApp.with(context)
                .asBitmap()
                .load(data.getImg())
                .placeholder(R.mipmap.ic_empty)
                .into(imageView);
    }
}