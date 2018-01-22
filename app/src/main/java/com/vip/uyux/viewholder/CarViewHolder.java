package com.vip.uyux.viewholder;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.activity.MainActivity;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.customview.TwoBtnDialog;
import com.vip.uyux.model.CartIndex;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.model.SimpleInfo;
import com.vip.uyux.util.ACache;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;

import java.util.HashMap;


/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class CarViewHolder extends BaseViewHolder<CartIndex.CartBean> {

    private onDeleteListener onDeleteListener;
    private final TextView textTotalPrice;
    private final ImageView imageImg;
    private final TextView textName;
    private final ImageView imageXuanZhong;
    private CartIndex.CartBean data;
    private final TextView textDes;
    private final ImageView imageAdd;
    private final ImageView imageDel;
    public EditText editNum;
    private boolean isFrist = true;

    public CarViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textName = $(R.id.textName);
        textTotalPrice = $(R.id.textTotalPrice);
        $(R.id.imageDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteListener.delete(getAdapterPosition());
            }
        });
        imageXuanZhong = $(R.id.imageXuanZhong);
        textDes = $(R.id.textDes);
        imageAdd = $(R.id.imageAdd);
        imageDel = $(R.id.imageDel);
        editNum = $(R.id.editNum);
        imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editNum.getText().toString().trim())) {
                    editNum.setText("1");
                    editNum.setSelection(1);
                } else {
                    int goodsNum = Integer.parseInt(editNum.getText().toString().trim());
                    editNum.setText((goodsNum + 1) + "");
                    editNum.setSelection(((goodsNum + 1) + "").length());
                }
            }
        });
        imageDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editNum.getText().toString().trim())) {

                } else {
                    int goodsNum = Integer.parseInt(editNum.getText().toString().trim());
                    if (goodsNum > 1) {
                        editNum.setText((goodsNum - 1) + "");
                        editNum.setSelection(((goodsNum - 1) + "").length());
                    }
                }
            }
        });
        editNum.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if ((source.equals("0") && dest.toString().length() == 0)) {
                    return "1";
                }
                return null;
            }
        }});
        editNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable.toString())) {
                    editNum.setText("1");
                    editNum.setSelection(1);
                }
                data.setNum(Integer.parseInt(editNum.getText().toString().trim()));
                if (!isFrist) {
                    gengXinCarNum();
                }
            }
        });
        imageXuanZhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.isSelect()) {
                    data.setSelect(false);
                    imageXuanZhong.setImageResource(R.mipmap.weixuanzhong);
                } else {
                    data.setSelect(true);
                    imageXuanZhong.setImageResource(R.mipmap.xuanzhong);
                }
                Intent intent = new Intent();
                intent.setAction(Constant.BroadcastCode.QUAN_XUAN);
                getContext().sendBroadcast(intent);
            }
        });
        $(R.id.imageDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TwoBtnDialog twoBtnDialog = new TwoBtnDialog(getContext(), "确定要移除该商品吗？", "是", "否");
                twoBtnDialog.show();
                twoBtnDialog.setClicklistener(new TwoBtnDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm() {
                        shanChu();
                        twoBtnDialog.dismiss();
                    }

                    @Override
                    public void doCancel() {
                        twoBtnDialog.dismiss();
                    }
                });
            }
        });
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getDelOkObject() {
        String url = Constant.HOST + Constant.Url.CART_DELCART;
        HashMap<String, String> params = new HashMap<>();
        if (((MainActivity) getContext()).isLogin) {
            params.put("uid", ((MainActivity) getContext()).userInfo.getUid());
            params.put("tokenTime", ((MainActivity) getContext()).tokenTime);
        }
        params.put("id", String.valueOf(data.getId()));
        return new OkObject(params, url);
    }

    /**
     * 删除购物车
     */
    private void shanChu() {
        ((MainActivity) getContext()).showLoadingDialog();
        ApiClient.post(getContext(), getDelOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                ((MainActivity) getContext()).cancelLoadingDialog();
                LogUtil.LogShitou("CarViewHolder--onSuccess", s + "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus() == 1) {
                        Intent intent = new Intent();
                        intent.setAction(Constant.BroadcastCode.SHUA_XIN_CAR);
                        getContext().sendBroadcast(intent);
                    } else if (simpleInfo.getStatus() == 3) {
                        MyDialog.showReLoginDialog(getContext());
                    } else {
                        Toast.makeText(getContext(), simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                ((MainActivity) getContext()).cancelLoadingDialog();
                Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        ACache aCache = ACache.get(getContext(), Constant.Acache.LOCATION);
        String did = aCache.getAsString(Constant.Acache.DID);
        String url = Constant.HOST + Constant.Url.CART_UPDATECART;
        HashMap<String, String> params = new HashMap<>();
        if (((MainActivity) getContext()).isLogin) {
            params.put("uid", ((MainActivity) getContext()).userInfo.getUid());
            params.put("tokenTime", ((MainActivity) getContext()).tokenTime);
        }
        params.put("did", did);
        params.put("num", String.valueOf(data.getNum()));
        params.put("id", String.valueOf(data.getId()));
        return new OkObject(params, url);
    }

    /**
     * 更新购物车数量
     */
    private void gengXinCarNum() {
        ((MainActivity) getContext()).showLoadingDialog();
        ApiClient.post(getContext(), getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                ((MainActivity) getContext()).cancelLoadingDialog();
                LogUtil.LogShitou("CarViewHolder--onSuccess", s + "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus() == 1) {
                        Intent intent = new Intent();
                        intent.setAction(Constant.BroadcastCode.NUM_CHANGE);
                        getContext().sendBroadcast(intent);
                    } else if (simpleInfo.getStatus() == 3) {
                        MyDialog.showReLoginDialog(getContext());
                    } else {
                        Toast.makeText(getContext(), simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                ((MainActivity) getContext()).cancelLoadingDialog();
                Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void setData(CartIndex.CartBean data) {
        super.setData(data);
        this.data = data;
        if (data != null) {
            GlideApp.with(getContext())
                    .asBitmap()
                    .centerCrop()
                    .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(4, getContext())))
                    .load(data.getGoods_img())
                    .into(imageImg);
            textName.setText(data.getGoods_title());
            SpannableString span = new SpannableString("¥" + data.getGoods_price());
            span.setSpan(new RelativeSizeSpan(0.65f), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            textTotalPrice.setText(span);
            if (data.isSelect()) {
                imageXuanZhong.setImageResource(R.mipmap.xuanzhong);
            } else {
                imageXuanZhong.setImageResource(R.mipmap.weixuanzhong);
            }
            textDes.setText(data.getSpe_name());
            editNum.setText(String.valueOf(data.getNum()));
            editNum.setSelection(String.valueOf(data.getNum()).length());
            isFrist = false;
        }
    }

    public void setOnDeleteListener(onDeleteListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }

    public interface onDeleteListener {
        void delete(int position);
    }
}
