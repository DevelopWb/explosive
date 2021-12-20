package com.juntai.wisdom.explorsive.entrance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.juntai.disabled.federation.R;
import com.juntai.wisdom.explorsive.base.BaseAppActivity;

/**
 * @aouther tobato
 * @description 描述  联系我们
 * @date 2021-12-19 16:39
 */
public class ConnectUsActivity extends BaseAppActivity<EntrancePresent> implements EntranceContract.IEntranceView {

    @Override
    protected EntrancePresent createPresenter() {
        return null;
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_connect_us;
    }

    @Override
    public void initView() {
        setTitleName("刚察县公安局");
    }

    @Override
    public void initData() {

    }


    @Override
    public void onSuccess(String tag, Object o) {

    }
}
