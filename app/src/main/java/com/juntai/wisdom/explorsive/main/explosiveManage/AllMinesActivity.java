package com.juntai.wisdom.explorsive.main.explosiveManage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.wisdom.explorsive.base.BaseRecyclerviewActivity;
import com.juntai.wisdom.explorsive.main.MainContactInterface;
import com.juntai.wisdom.explorsive.main.MainPresent;

/**
 * @aouther tobato
 * @description 描述  所有的矿场
 * @date 2021-12-29 10:54
 */
public class AllMinesActivity extends BaseRecyclerviewActivity<MainPresent> implements MainContactInterface {

    @Override
    protected MainPresent createPresenter() {
        return null;
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void freshlayoutOnLoadMore() {

    }

    @Override
    protected void freshlayoutOnRefresh() {

    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return null;
    }
}
