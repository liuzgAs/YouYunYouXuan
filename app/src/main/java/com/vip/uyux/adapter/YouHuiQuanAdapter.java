package com.vip.uyux.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vip.uyux.R;
import com.vip.uyux.model.OrderConfirmbefore;

import java.util.List;

public class YouHuiQuanAdapter extends BaseAdapter {
    private Context context;
    List<OrderConfirmbefore.CouponBean> couponBeanList;

    public YouHuiQuanAdapter(Context context, List<OrderConfirmbefore.CouponBean> couponBeanList) {
        this.context=context;
        this.couponBeanList=couponBeanList;
    }

    class ViewHolder {
        public TextView textName;
        public TextView textMoney;
    }

    @Override
    public int getCount() {
        return couponBeanList.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_youhuiquan_qrdd, null);
            holder.textName = (TextView) convertView.findViewById(R.id.textName);
            holder.textMoney = (TextView) convertView.findViewById(R.id.textMoney);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textName.setText(couponBeanList.get(position).getName());
        holder.textMoney.setText("¥"+couponBeanList.get(position).getMoney());
        return convertView;
    }
}