package com.juntai.wisdom.explorsive.base;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;

import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.base.customview.CustomViewPager;
import com.juntai.wisdom.explorsive.base.customview.MainPagerAdapter;


/**
 * @aouther tobato
 * @description 描述  tab+viewpage
 * @date 2021/6/1 15:52
 */
public abstract class BaseTabViewPageActivity<P extends BasePresenter> extends BaseAppActivity<P> {

    private TabLayout mTabTb;
    private CustomViewPager mViewpageVp;
    private FrameLayout mTabHeadFl;
    private FrameLayout mTabFootFl;


    @Override
    public int getLayoutView() {
        return R.layout.base_tab_page_layout;
    }

    @Override
    public void initView() {
        setTitleName(gettitleName());
        mTabTb = (TabLayout) findViewById(R.id.tab_tb);
        mTabTb.setTabMode(getTabMode());
        mViewpageVp = (CustomViewPager) findViewById(R.id.viewpage_vp);
        mTabHeadFl = (FrameLayout) findViewById(R.id.tab_head_fl);
        mTabFootFl = (FrameLayout) findViewById(R.id.tab_foot_fl);
        if (getTabHeadLayout()>0) {
            mTabHeadFl.setVisibility(View.VISIBLE);
            mTabHeadFl.addView(View.inflate(this, getTabHeadLayout(), null));
        }
        if (getTabFootLayout()>0) {
            mTabFootFl.setVisibility(View.VISIBLE);
            mTabFootFl.addView(View.inflate(this, getTabFootLayout(), null));
        }
        initTab();
    }

    protected abstract int getTabMode();
    protected abstract int getTabHeadLayout();
    protected abstract int getTabFootLayout();

    protected abstract String gettitleName();



    private void initTab() {
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), getApplicationContext(),
                getTabTitles(),
                getFragments());
        mViewpageVp.setAdapter(adapter);
        mViewpageVp.setOffscreenPageLimit(getTabTitles().length);
        /*viewpager切换监听，包含滑动点击两种*/
        mViewpageVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mTabTb.setupWithViewPager(mViewpageVp);
        /**
         * 添加自定义tab布局
         * */
        for (int i = 0; i < mTabTb.getTabCount(); i++) {
            TabLayout.Tab tab = mTabTb.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(adapter.getTabView(i));
            }
        }
        /*viewpager切换默认第一个*/
        mViewpageVp.setCurrentItem(0);
    }

    protected abstract SparseArray<Fragment> getFragments();

    protected abstract String[] getTabTitles();

}
