package com.juntai.wisdom.explorsive.main.mine.use;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.base.BaseAppActivity;
import com.juntai.wisdom.explorsive.main.MainPresent;

/**
 * @aouther tobato
 * @description 描述 矿内使用申请
 * @date 2021-12-20 17:03
 */
public class ApplyUseActivity extends BaseAppActivity<MainPresent> implements IView {

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
        setTitleName("矿内使用申请");
        getTitleRightTv().setText("新增");
        getTitleRightTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2021-12-20 新增 矿内使用申请
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
