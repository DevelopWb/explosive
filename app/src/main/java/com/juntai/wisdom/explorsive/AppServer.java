package com.juntai.wisdom.explorsive;


import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.wisdom.explorsive.bean.ReceiveOrderListBean;
import com.juntai.wisdom.explorsive.bean.UserBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
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
    Observable<UserBean> login(@Query("mobile") String account, @Query("password") String password, @Query("mobileName") String mobileName, @Query("regId") String regId);

    /**
     * 上传文件
     *
     * @return
     */
    @POST(AppHttpPath.UPLOAD_FILES)
    Observable<BaseResult> uploadFiles(@Body RequestBody requestBody);
    /**
     * account  手机号
     *
     * @return
     */
    @POST(AppHttpPath.GET_SMS_CODE)
    Observable<BaseResult> getSMSCode(@Query("account") String account);


    /*====================================================    个人中心   ==============================================================*/


    /**
     * account  手机号
     *
     * @return
     */
    @POST(AppHttpPath.GET_USER_INFO)
    Observable<UserBean> getUserInfo(@Body RequestBody requestBody);



    @POST(AppHttpPath.RECEIVE_ORDER_LIST)
    Observable<ReceiveOrderListBean> getReceiveOrderList(@Body RequestBody requestBody);

    @POST(AppHttpPath.USE_ORDER_LIST)
    Observable<ReceiveOrderListBean> getUseOrderList(@Body RequestBody requestBody);


}