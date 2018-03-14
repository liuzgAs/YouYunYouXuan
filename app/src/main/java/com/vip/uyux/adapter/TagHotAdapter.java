package com.vip.uyux.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vip.uyux.R;
import com.vip.uyux.customview.OnInitSelectedPosition;
import com.vip.uyux.model.GoodsSearch;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author HanHailong
 * @date 15/10/19
 */
public class TagHotAdapter extends BaseAdapter implements OnInitSelectedPosition {

    private final Context mContext;
    private final List<GoodsSearch.HotBean> mDataList;

    public TagHotAdapter(Context context) {
        this.mContext = context;
        mDataList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public GoodsSearch.HotBean getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_hot, null);

        TextView textView = (TextView) view.findViewById(R.id.textDes);
        String t = mDataList.get(position).getName();

//        if (t instanceof String) {
            textView.setText(t);
//        }
        return view;
    }

    public void onlyAddAll(List<GoodsSearch.HotBean> datas) {
        mDataList.addAll(datas);
        notifyDataSetChanged();
    }

    public void clearAndAddAll(List<GoodsSearch.HotBean> datas) {
        mDataList.clear();
        onlyAddAll(datas);
    }

    @Override
    public boolean isSelectedPosition(int position) {
        return false;
    }
}
