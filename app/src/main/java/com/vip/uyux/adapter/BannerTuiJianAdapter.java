package com.vip.uyux.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vip.uyux.R;
import com.vip.uyux.util.GlideApp;

import java.util.List;

/**
 * Created by zhangjiebo on 2017/10/27 0027.
 *
 * @author ZhangJieBo
 */
public class BannerTuiJianAdapter extends PagerAdapter{

    private Context mContext;
    private  List<Integer> imgList;

    public BannerTuiJianAdapter(Context context, List<Integer> imgList) {
        this.mContext = context;
        this.imgList=imgList;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE/2;
    }

    @Override
    public View instantiateItem(ViewGroup container, final int position) {
        View inflate = LayoutInflater.from(container.getContext()).inflate(R.layout.item_index_viewpager, null);
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(mContext, WebActivity.class);
//                intent.putExtra(Constant.IntentKey.TITLE, imgList.get(position%imgList.size()).getTitle());
//                intent.putExtra(Constant.IntentKey.URL, imgList.get(position%imgList.size()).getShare_url());
//                mContext.startActivity(intent);
            }
        });
        ImageView imageImg = inflate.findViewById(R.id.imageImg);
        if (imgList!=null){
            if (imgList.size()>0){
                GlideApp.with(mContext)
                        .asBitmap()
                        .load(imgList.get(position%imgList.size()))
                        .placeholder(R.mipmap.ic_empty)
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

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
