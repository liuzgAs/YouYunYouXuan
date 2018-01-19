package com.vip.uyux.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vip.uyux.R;
import com.vip.uyux.util.GlideApp;

import java.util.List;

public class FenLeiAdapter extends BaseAdapter {
        private List<Integer> classDetailBean;
        private Context context;
        public FenLeiAdapter(Context context, List<Integer> classDetailBean) {
            this.context=context;
            this.classDetailBean = classDetailBean;
        }

        class ViewHolder {
            public TextView textName;
            public ImageView imageImg;
        }

        @Override
        public int getCount() {
            return classDetailBean.size();
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
                convertView = LayoutInflater.from(context).inflate(R.layout.item_grid_fenlei, null);
                holder.textName = (TextView) convertView.findViewById(R.id.textName);
                holder.imageImg = (ImageView) convertView.findViewById(R.id.imageImg);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textName.setText("坚果炒货");
            GlideApp.with(context)
                    .asBitmap()
                    .load(R.mipmap.shangpin)
                    .placeholder(R.mipmap.ic_empty)
                    .into(holder.imageImg);
            return convertView;
        }
    }