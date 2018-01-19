package com.vip.uyux.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.vip.uyux.R;
import com.vip.uyux.adapter.FenLeiAdapter;
import com.vip.uyux.customview.WrapHeightGridView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class AllViewHolder extends BaseViewHolder<List<Integer>> {


    private final WrapHeightGridView gridview;
    private final TextView textName;

    public AllViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        gridview = $(R.id.gridview);
        textName = $(R.id.textName);
    }

    @Override
    public void setData(List<Integer> data) {
        super.setData(data);
        if (getDataPosition()==0){
            textName.setText("常用分类");
        }else {
            textName.setText("专场推荐");
        }
        gridview.setAdapter(new FenLeiAdapter(getContext(), data));
    }
}
