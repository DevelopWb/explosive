package com.juntai.wisdom.explorsive.main.mine.dosage;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.SparseArray;

import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.base.BaseTabViewPageActivity;
import com.juntai.wisdom.explorsive.main.MainContactInterface;
import com.juntai.wisdom.explorsive.main.MainPresent;
/**
 * @aouther tobato
 * @description 描述  用量
 * @date 2022-01-07 10:00
 */
public class AllDosageActivity extends BaseTabViewPageActivity<MainPresent> implements MainContactInterface {

    @Override
    protected MainPresent createPresenter() {
        return new MainPresent();
    }



    @Override
    protected int getTabMode() {
        return TabLayout.MODE_SCROLLABLE;
    }

    @Override
    protected int getTabHeadLayout() {
        return R.layout.all_dosage_item;
    }

    @Override
    protected int getTabFootLayout() {
        return 0;
    }

    @Override
    protected String gettitleName() {
        return "用量";
    }


    @Override
    protected SparseArray<Fragment> getFragments() {
        SparseArray<Fragment> arrays = new SparseArray<>();

        arrays.append(0, DosageFragment.newInstance(MainContactInterface.USE_DOSAGE));
        arrays.append(1, DosageFragment.newInstance(MainContactInterface.RECEIVE_DOSAGE));

        return arrays;
    }

    @Override
    protected String[] getTabTitles() {
        return new String[]{MainContactInterface.USE_DOSAGE,
                MainContactInterface.RECEIVE_DOSAGE};
    }


    @Override
    public void onSuccess(String tag, Object o) {

    }

    @Override
    public void initData() {

    }
}
