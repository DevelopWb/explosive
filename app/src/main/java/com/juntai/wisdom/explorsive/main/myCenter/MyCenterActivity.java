package com.juntai.wisdom.explorsive.main.myCenter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.wisdom.explorsive.base.BaseAppPresent;
import com.juntai.wisdom.explorsive.base.BaseRecyclerviewActivity;
import com.juntai.wisdom.explorsive.entrance.LoginActivity;
import com.juntai.wisdom.explorsive.main.MainContactInterface;
import com.juntai.wisdom.explorsive.main.MainPresent;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;

/**
 * @aouther tobato
 * @description 描述 个人中心
 * @date 2021-12-20 10:07
 */
public class MyCenterActivity extends BaseRecyclerviewActivity<MainPresent> implements MainContactInterface {

    @Override
    protected MainPresent createPresenter() {
        return new MainPresent();
    }

    @Override
    public void initView() {
        super.initView();
        getTitleRightTv().setText("退出账户");
        getTitleRightTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfoManager.clearUserBaseInfo();
                startActivity(new Intent(mContext, LoginActivity.class));
            }
        });
    }

    @Override
    public void initData() {
// TODO: 2021-12-20  个人中心所有的逻辑
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
