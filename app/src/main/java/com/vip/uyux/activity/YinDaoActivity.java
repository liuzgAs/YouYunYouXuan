package com.vip.uyux.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.luoxudong.app.threadpool.ThreadPoolHelp;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;
import com.vip.uyux.R;
import com.vip.uyux.base.ZjbBaseNotLeftActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.fragment.GuideFragment;
import com.vip.uyux.util.ACache;
import com.vip.uyux.util.LogUtil;


public class YinDaoActivity extends ZjbBaseNotLeftActivity {

    private ViewPager mMyPager;
    private PageIndicatorView mPageIndicatorView;
    private int[] imgs = new int[]{
            R.mipmap.welcome11,
            R.mipmap.welcome12,
            R.mipmap.welcome13,
            R.mipmap.welcome14,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yin_dao);
        mMyPager = (ViewPager) findViewById(R.id.myPager);
        mPageIndicatorView = (PageIndicatorView) findViewById(R.id.pageIndicatorView);
        int blue = ContextCompat.getColor(this, R.color.basic_color);
        mPageIndicatorView.setSelectedColor(blue);
        mPageIndicatorView.setUnselectedColor(ContextCompat.getColor(this, R.color.gray_white));
        mPageIndicatorView.setAnimationType(AnimationType.WORM);
        mPageIndicatorView.setCount(imgs.length);
        mMyPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        init();
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
        mMyPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                LogUtil.LogShitou("YinDaoActivity--onPageSelected", "" + position);
                if (position == imgs.length - 1) {
                    ThreadPoolHelp.Builder
                            .cached()
                            .builder()
                            .execute(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1000);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                ACache aCache = ACache.get(YinDaoActivity.this, Constant.Acache.FRIST);
                                                aCache.put(Constant.Acache.FRIST, "0");
                                                Intent intent = new Intent(YinDaoActivity.this, MainActivity.class);
                                                startActivity(intent);
//                mContext.overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                                                finish();
                                                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                            }
                                        });
                                    } catch (Exception e) {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                ACache aCache = ACache.get(YinDaoActivity.this, Constant.Acache.FRIST);
                                                aCache.put(Constant.Acache.FRIST, "0");
                                                Intent intent = new Intent(YinDaoActivity.this, MainActivity.class);
                                                startActivity(intent);
//                mContext.overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                                                finish();
                                                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                            }
                                        });
                                    }

                                }
                            });

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
            return new GuideFragment(imgs[position], imgs.length - 1, position);
        }

        @Override
        public int getCount() {
            return imgs.length;
        }
    }
}
