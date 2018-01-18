package com.vip.uyux.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.vip.uyux.R;
import com.vip.uyux.util.GlideApp;

import java.util.List;

public class IndexBannerImgHolderView implements Holder<Integer> {
    private ImageView imageView;
    private List<Integer> bannerBeanList;

    public IndexBannerImgHolderView(List<Integer> bannerBeanList) {
        this.bannerBeanList = bannerBeanList;
    }

    @Override
    public View createView(final Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    @Override
    public void UpdateUI(final Context context, final int position, Integer data) {
        GlideApp.with(context)
                .asBitmap()
                .load(data)
                .placeholder(R.mipmap.ic_empty)
                .into(imageView);
    }
}