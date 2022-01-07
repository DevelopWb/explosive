package com.juntai.wisdom.explorsive.base;


import com.juntai.disabled.basecomponent.base.BaseMvpFragment;
import com.juntai.disabled.basecomponent.mvp.IPresenter;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;

import okhttp3.FormBody;

/**
 * @aouther tobato
 * @description 描述  app的fragment的基类
 * @date 2020/7/18 16:43
 */
public abstract class BaseAppFragment<P extends IPresenter> extends BaseMvpFragment<P> {


    /**
     * 获取activity
     *
     * @return
     */
    public BaseAppActivity getBaseAppActivity() {
        return (BaseAppActivity) getActivity();
    }

    /**
     * 获取builder
     *
     * @return
     */
    public FormBody.Builder getBaseBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("token", UserInfoManager.getUserToken());
        builder.add("mobile", UserInfoManager.getMobile());
        builder.add("departmentId", String.valueOf(UserInfoManager.getDepartmentId()));
        builder.add("userId", String.valueOf(UserInfoManager.getUserId()));
        return builder;
    }

}
