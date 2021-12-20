package com.juntai.wisdom.explorsive;


import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.wisdom.explorsive.bean.UserBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * responseBody里的数据只能调用(取出)一次，第二次为空。可赋值给新的变量使用
 */
public interface AppServer {
    /**
     * 登录
     *
     * @param account
     * @param password
     * @return
     */
    @POST(AppHttpPath.LOGIN)
    Observable<UserBean> login(@Query("mobile") String account, @Query("password") String password);


    /**
     * account  手机号
     *
     * @return
     */
    @POST(AppHttpPath.GET_SMS_CODE)
    Observable<BaseResult> getSMSCode(@Query("account") String account);
}