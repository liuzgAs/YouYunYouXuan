package com.vip.uyux.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vip.uyux.fragment.ChanPinXqTuPianFragment;
import com.vip.uyux.model.ImgsBean;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/2/23/023.
 *
 * @author ZhangJieBo
 */

public class ChanPinXQTP extends FragmentPagerAdapter {

    List<ImgsBean> imgsBeanList;
    List<ImgsBean> imgsBeanList1;

    public ChanPinXQTP(FragmentManager fm, List<ImgsBean> imgsBeanList, List<ImgsBean> imgsBeanList1) {
        super(fm);
        this.imgsBeanList = imgsBeanList;
        this.imgsBeanList1 = imgsBeanList1;
    }

    public ChanPinXQTP(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ChanPinXqTuPianFragment(imgsBeanList);
            case 1:
                return new ChanPinXqTuPianFragment(imgsBeanList1);
            default:
                return new ChanPinXqTuPianFragment();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
