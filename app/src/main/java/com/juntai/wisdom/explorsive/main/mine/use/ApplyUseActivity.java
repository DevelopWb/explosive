package com.juntai.wisdom.explorsive.main.mine.use;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.base.BaseAppActivity;
import com.juntai.wisdom.explorsive.main.MainContactInterface;
import com.juntai.wisdom.explorsive.main.MainPresent;
import com.juntai.wisdom.explorsive.main.mine.receive.AddReceiveApplyActivity;

/**
 * @aouther tobato
 * @description 描述 矿内使用申请
 * @date 2021-12-20 17:03
 */
public class ApplyUseActivity extends BaseAppActivity<MainPresent> implements MainContactInterface {

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
                // : 2021-12-20 新增 矿内使用申请
                startActivityForResult(new Intent(mContext, AddUseApplyActivity.class),BASE_REQUEST_RESULT);

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
