package com.vip.uyux.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vip.uyux.R;
import com.vip.uyux.model.GoodsInfo;

import java.util.List;

public class MyChuXiaoAdapter extends BaseAdapter {
    private Context context;
    List<GoodsInfo.DataBean.PromotionsafterBean> promotionsAfter;

    public MyChuXiaoAdapter(Context context,List<GoodsInfo.DataBean.PromotionsafterBean> promotionsAfter) {
        this.context=context;
        this.promotionsAfter=promotionsAfter;
    }

    class ViewHolder {
        public TextView title;
    }

    @Override
    public int getCount() {
        return promotionsAfter.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_chuxiao, null);
            holder.title = (TextView) convertView.findViewById(R.id.textTitle);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(promotionsAfter.get(position).getTitle());
        return convertView;
    }
}