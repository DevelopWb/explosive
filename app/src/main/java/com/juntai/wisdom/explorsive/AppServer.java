package com.juntai.wisdom.explorsive;


import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.wisdom.explorsive.bean.AboutUsBean;
import com.juntai.wisdom.explorsive.bean.AllDosageBean;
import com.juntai.wisdom.explorsive.bean.DeliveryListBean;
import com.juntai.wisdom.explorsive.bean.ExplosiveTypeBean;
import com.juntai.wisdom.explorsive.bean.FaceCheckResponseBean;
import com.juntai.wisdom.explorsive.bean.IdNameBean;
import com.juntai.wisdom.explorsive.bean.IdNameListBean;
import com.juntai.wisdom.explorsive.bean.MineReceiverBean;
import com.juntai.wisdom.explorsive.bean.NewsBean;
import com.juntai.wisdom.explorsive.bean.OrderListBean;
import com.juntai.wisdom.explorsive.bean.ReceiveOrderDetailBean;
import com.juntai.wisdom.explorsive.bean.UseOrderDetailBean;
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
    @POST(AppHttpPath.ABOUT_US)
    Observable<AboutUsBean> aboutUs(@Body RequestBody requestBody);
    @POST(AppHttpPath.MODIFY_PWD)
    Observable<BaseResult> modifyPwd(@Body RequestBody requestBody);

    @POST(AppHttpPath.NEWS_LIST)
    Observable<NewsBean> getNewsList(@Body RequestBody requestBody);
    @POST(AppHttpPath.GET_UNREAD_COUNT)
    Observable<BaseResult> getUnreadCount(@Body RequestBody requestBody);
    @POST(AppHttpPath.MODIFY_HEAD)
    Observable<BaseResult> modifyHead(@Body RequestBody requestBody);
    @POST(AppHttpPath.LOGOUT)
    Observable<BaseResult> logout(@Body RequestBody requestBody);

    @POST(AppHttpPath.FACE_CHECK)
    Observable<FaceCheckResponseBean> startFaceCheck(@Body RequestBody requestBody);


    @POST(AppHttpPath.RECEIVE_ORDER_LIST)
    Observable<OrderListBean> getReceiveOrderList(@Body RequestBody requestBody);

    @POST(AppHttpPath.USE_ORDER_LIST)
    Observable<OrderListBean> getUseOrderList(@Body RequestBody requestBody);

    @POST(AppHttpPath.GET_EXPLOSIVE_TYPES)
    Observable<ExplosiveTypeBean> getExplosiveTypes(@Body RequestBody requestBody);

    @POST(AppHttpPath.ADD_RECEIVE_EXPLOSIVE_APPLY)
    Observable<BaseResult> addExplosiveReceiveApply(@Body RequestBody requestBody);

    @POST(AppHttpPath.RECEIVE_EXPLOSIVE_DETAIL)
    Observable<ReceiveOrderDetailBean> getExplosiveReceiveDetail(@Body RequestBody requestBody);

    @POST(AppHttpPath.POLICE_APPROVE)
    Observable<BaseResult> policeApprove(@Body RequestBody requestBody);

    @POST(AppHttpPath.POLICE_APPROVE_OF_MINE)
    Observable<BaseResult> policeApproveOfMine(@Body RequestBody requestBody);

    @POST(AppHttpPath.BRIGADE_APPROVE)
    Observable<BaseResult> brigadeApprove(@Body RequestBody requestBody);

    @POST(AppHttpPath.LEADER_APPROVE)
    Observable<BaseResult> leaderApprove(@Body RequestBody requestBody);

    @POST(AppHttpPath.GET_DELIVERY_LIST)
    Observable<DeliveryListBean> getDeliveryList(@Body RequestBody requestBody);

    @POST(AppHttpPath.OUT_HOUSE)
    Observable<BaseResult> outHouse(@Body RequestBody requestBody);

    @POST(AppHttpPath.OUT_IN_MINE)
    Observable<BaseResult> outInMine(@Body RequestBody requestBody);

    @POST(AppHttpPath.USE_IN_MINE)
    Observable<BaseResult> useInMine(@Body RequestBody requestBody);
    @POST(AppHttpPath.GET_ALL_MINE)
    Observable<IdNameListBean> getAllMine(@Body RequestBody requestBody);

    @POST(AppHttpPath.DELIVERY)
    Observable<BaseResult> delivery(@Body RequestBody requestBody);

    @POST(AppHttpPath.USE_EXPLOSIVE_DETAIL)
    Observable<UseOrderDetailBean> getExplosiveUseDetail(@Body RequestBody requestBody);


    @POST(AppHttpPath.ADD_USE_EXPLOSIVE_APPLY)
    Observable<BaseResult> addExplosiveUseApply(@Body RequestBody requestBody);

    @POST(AppHttpPath.GET_RECEIVER_OF_MINE)
    Observable<MineReceiverBean> getReceiverOfMine(@Body RequestBody requestBody);



    @POST(AppHttpPath.STOCK_OF_MINE)
    Observable<AllDosageBean> getStockOfMine(@Body RequestBody requestBody);

    @POST(AppHttpPath.USE_STATISTICS)
    Observable<AllDosageBean> getUseStatistics(@Body RequestBody requestBody);

    @POST(AppHttpPath.RECEIVE_STATISTICS)
    Observable<AllDosageBean> getReceiveStatistics(@Body RequestBody requestBody);


}