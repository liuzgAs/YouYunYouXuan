package com.vip.uyux.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vip.uyux.R;
import com.vip.uyux.customview.OnInitSelectedPosition;
import com.vip.uyux.model.CustomerIntegragoodsinfo;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author HanHailong
 * @date 15/10/19
 */
public class TagAdapter02 extends BaseAdapter implements OnInitSelectedPosition {

    private final Context mContext;
    private final List<CustomerIntegragoodsinfo.SkuCateBean> mDataList;

    public TagAdapter02(Context context) {
        this.mContext = context;
        mDataList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public CustomerIntegragoodsinfo.SkuCateBean getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_guige01, null);

        TextView textView = (TextView) view.findViewById(R.id.textDes);
        String t = mDataList.get(position).getName();

//        if (t instanceof String) {
            textView.setText(t);
//        }
        return view;
    }

    public void onlyAddAll(List<CustomerIntegragoodsinfo.SkuCateBean> datas) {
        mDataList.addAll(datas);
        notifyDataSetChanged();
    }

    public void clearAndAddAll(List<CustomerIntegragoodsinfo.SkuCateBean> datas) {
        mDataList.clear();
        onlyAddAll(datas);
    }

    @Override
    public boolean isSelectedPosition(int position) {
        return false;
    }
}
