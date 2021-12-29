package com.juntai.wisdom.explorsive.main.mine.use;

import android.content.Intent;
import android.view.View;

import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.base.BaseAppActivity;
import com.juntai.wisdom.explorsive.main.MainContactInterface;
import com.juntai.wisdom.explorsive.main.MainPresent;

/**
 * @aouther tobato
 * @description 描述 矿内使用申请
 * @date 2021-12-20 17:03
 */
public abstract class BaseApplyUseActivity extends BaseAppActivity<MainPresent> implements MainContactInterface {


    @Override
    protected MainPresent createPresenter() {
        return new MainPresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_apply_use;
    }

    @Override
    public void initView() {
        setTitleName(getTitleName());
    }

    protected abstract String getTitleName();

    @Override
    public void initData() {

    }

    @Override
    public void onSuccess(String tag, Object o) {

    }
}
