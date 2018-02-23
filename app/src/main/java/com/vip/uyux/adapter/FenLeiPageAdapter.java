package com.vip.uyux.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vip.uyux.fragment.FenLeiRigthFragment;
import com.vip.uyux.model.IndexCate;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/2/23/023.
 *
 * @author ZhangJieBo
 */

public class FenLeiPageAdapter extends FragmentPagerAdapter {
    private List<IndexCate.DataBean> dataBeanList;

    public FenLeiPageAdapter(FragmentManager fm, List<IndexCate.DataBean> dataBeanList) {
        super(fm);
        this.dataBeanList=dataBeanList;
    }

    public FenLeiPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        IndexCate.DataBean dataBean = dataBeanList.get(position);
        return new FenLeiRigthFragment(dataBean);
    }

    @Override
    public int getCount() {
        return dataBeanList.size();
    }
}
