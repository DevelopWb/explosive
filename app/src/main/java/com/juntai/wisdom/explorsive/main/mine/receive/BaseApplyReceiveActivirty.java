package com.juntai.wisdom.explorsive.main.mine.receive;

import android.content.Intent;
import android.view.View;

import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.base.BaseAppActivity;
import com.juntai.wisdom.explorsive.main.MainContactInterface;
import com.juntai.wisdom.explorsive.main.MainPresent;

/**
 * @aouther tobato
 * @description 描述  民爆领取(向民爆局)申请
 * @date 2021-12-20 14:15
 */
public abstract class BaseApplyReceiveActivirty extends BaseAppActivity<MainPresent> implements MainContactInterface {


    @Override
    protected MainPresent createPresenter() {
        return new MainPresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.apply_receive_activity;
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
