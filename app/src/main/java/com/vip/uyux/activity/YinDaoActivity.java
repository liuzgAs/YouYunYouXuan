package com.vip.uyux.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;
import com.vip.uyux.R;
import com.vip.uyux.base.ZjbBaseNotLeftActivity;
import com.vip.uyux.fragment.GuideFragment;


public class YinDaoActivity extends ZjbBaseNotLeftActivity {

    private ViewPager mMyPager;
    private PageIndicatorView mPageIndicatorView;
    private int[] imgs = new int[]{
            R.mipmap.welcome1,
            R.mipmap.welcome2,
            R.mipmap.welcome3,
            R.mipmap.welcome4,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yin_dao);
        mMyPager = (ViewPager) findViewById(R.id.myPager);
        mPageIndicatorView = (PageIndicatorView) findViewById(R.id.pageIndicatorView);
        int blue = ContextCompat.getColor(this,R.color.white);
        mPageIndicatorView.setSelectedColor(blue);
        mPageIndicatorView.setUnselectedColor(ContextCompat.getColor(this,R.color.gray_white));
        mPageIndicatorView.setAnimationType(AnimationType.WORM);
        mPageIndicatorView.setCount(imgs.length);
        mMyPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void findID() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void initData() {

    }

    /**
     * des： 引导adapter
     * author： ZhangJieBo
     * date： 2017/7/6 0006 下午 2:26
     */
    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            GuideFragment guideFragment = new GuideFragment();
            guideFragment.setImg(imgs[position], imgs.length - 1, position);
            return guideFragment;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }
    }
}
