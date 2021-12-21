package com.juntai.wisdom.explorsive.main.mine.receive;

import android.view.View;

import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.base.BaseAppActivity;
import com.juntai.wisdom.explorsive.main.MainPresent;

/**
 * @aouther tobato
 * @description 描述  民爆领取(向民爆局)申请
 * @date 2021-12-20 14:15
 */
public class ApplyReceiveActivirty extends BaseAppActivity<MainPresent> implements IView {

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
        setTitleName("民爆领取申请");
        getTitleRightTv().setText("新增");
        getTitleRightTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2021-12-20 新增 民爆申请 
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void onSuccess(String tag, Object o) {

    }
}
