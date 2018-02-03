package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.model.Picture;
import com.vip.uyux.util.GlideApp;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class PictureViewHolder extends BaseViewHolder<Picture> {

    int viewType;
    private final View ll_del;
    private final ImageView fiv;
    public interface OnRemoveListener{
        void remove(int position);
    }

    private OnRemoveListener onRemoveListener;

    public void setOnRemoveListener(OnRemoveListener onRemoveListener) {
        this.onRemoveListener = onRemoveListener;
    }

    public PictureViewHolder(ViewGroup parent, @LayoutRes int res, int viewType) {
        super(parent, res);
        this.viewType=viewType;
        ll_del = $(R.id.ll_del);
        fiv = $(R.id.fiv);
        if (viewType==1){
            ll_del.setVisibility(View.GONE);
        }else {
            ll_del.setVisibility(View.VISIBLE);
            ll_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRemoveListener.remove(getDataPosition());
                }
            });
        }
    }

    @Override
    public void setData(Picture data) {
        super.setData(data);
        if (data.getType()==0){
            GlideApp.with(getContext())
                    .load(data.getLocalMedia().getCompressPath())
                    .centerCrop()
                    .placeholder(R.mipmap.ic_empty)
                    .into(fiv);
        }
    }

}
