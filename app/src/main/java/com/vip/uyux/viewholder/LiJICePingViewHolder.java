package com.vip.uyux.viewholder;

import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.interfacepage.OnAddPictureListener;
import com.vip.uyux.model.EvaluationAddbefore;
import com.vip.uyux.util.GlideApp;
import com.vip.uyux.util.ImageWidthHeight;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class LiJICePingViewHolder extends BaseViewHolder<EvaluationAddbefore.ImgsBean> {

    private final ImageView imageImg;
    private OnAddPictureListener onAddPictureListener;
    private final EditText editContent;
    EvaluationAddbefore.ImgsBean data;
    private final ImageView imageDelete;

    public LiJICePingViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        imageDelete = $(R.id.imageDelete);
        editContent = $(R.id.editContent);
        imageImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddPictureListener.addPicture(getDataPosition());
            }
        });
        editContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                data.setContent(editable.toString().trim());
            }
        });
        imageDelete.setVisibility(View.GONE);
        imageImg.setVisibility(View.GONE);
        imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.setImgBean(null);
                data.setImg(0);
                data.setImg_url("");
                imageDelete.setVisibility(View.GONE);
                imageImg.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void setData(EvaluationAddbefore.ImgsBean data) {
        super.setData(data);
        this.data=data;
        if (data.getImgBean() != null) {
            imageDelete.setVisibility(View.VISIBLE);
            imageImg.setVisibility(View.VISIBLE);
            ImageWidthHeight.WidthHeight imgWidthHeigth = ImageWidthHeight.getImgWidthHeigth(data.getImgBean().getCompressPath());
            GlideApp.with(getContext())
                    .load(data.getImgBean().getCompressPath())
                    .centerCrop()
                    .placeholder(R.mipmap.ic_empty)
                    .into(new SimpleTarget<Drawable>(imgWidthHeigth.getWidth(), imgWidthHeigth.getHeigth()) {
                        @Override
                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                            imageImg.setImageDrawable(resource);
                        }
                    });
        } else {
            if (!TextUtils.isEmpty(data.getImg_url())) {
                imageDelete.setVisibility(View.VISIBLE);
                imageImg.setVisibility(View.VISIBLE);
                GlideApp.with(getContext())
                        .load(data.getImg_url())
                        .centerCrop()
                        .placeholder(R.mipmap.ic_empty)
                        .into(new SimpleTarget<Drawable>(data.getImg_w(), data.getImg_h()) {
                            @Override
                            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                imageImg.setImageDrawable(resource);
                            }
                        });
            }
        }
        editContent.setText(data.getContent());
    }

    public void setOnAddPictureListener(OnAddPictureListener onAddPictureListener) {
        this.onAddPictureListener = onAddPictureListener;
    }
}
