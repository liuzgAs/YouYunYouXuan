package com.vip.uyux.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vip.uyux.R;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.AdvsBean;
import com.vip.uyux.util.GlideApp;

import java.util.List;

/**
 * Created by zhangjiebo on 2017/10/27 0027.
 *
 * @author ZhangJieBo
 */
public class BannerAdapter extends PagerAdapter{

    private Context mContext;
    private  List<AdvsBean> imgList;

    public BannerAdapter(Context context, List<AdvsBean> imgList) {
        this.mContext = context;
        this.imgList=imgList;
    }

    @Override
    public int getCount() {
        return imgList.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, final int position) {
        View inflate = LayoutInflater.from(container.getContext()).inflate(R.layout.item_index_viewpager, null);
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(Constant.IntentKey.BEAN,imgList.get(position));
                intent.setAction(Constant.BroadcastCode.ADV);
                mContext.sendBroadcast(intent);
            }
        });
        ImageView imageImg = inflate.findViewById(R.id.imageImg);
        if (imgList!=null){
            if (imgList.size()>0){
                GlideApp.with(mContext)
                        .load(imgList.get(position).getImg())
                        .centerCrop()
                        .dontAnimate()
                        .placeholder(R.mipmap.ic_empty_h)
                        .into(imageImg);
            }
        }
        container.addView(inflate, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        return inflate;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
