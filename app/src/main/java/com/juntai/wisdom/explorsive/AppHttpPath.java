package com.juntai.wisdom.explorsive;

public class AppHttpPath {

    //            public static final String BASE = "http://192.168.124.115:8080/disabledPersonsFederation/u/app";
    public static final String BASE_ROOT = "http://192.168.124.118:8080/explosive-manage/";
    public static final String BASE = BASE_ROOT + "app";

    public static final String BASE_IMAGE = "http://192.168.124.118:9600";


    /**
     * 上传图片或视频
     */
    public static final String UPLOAD_FILES = BASE_ROOT + "uploadFile/upload";
    /**
     * 登录
     */
    public static final String LOGIN = BASE + "/login";


    /**
     * 检查更新
     */
    // TODO: 2021-12-21 这个最后再打开
//    public static final String APP_UPDATE = BASE + "/detectionAppVersions";
    public static final String APP_UPDATE = BASE + "";

    /**
     * 获取短信验证码
     */
    public static final String GET_SMS_CODE = BASE + "getSMSCode.shtml";




    /*====================================================    民爆领取相关   ==============================================================*/

    /**
     * 民爆领取订单列表
     */
    public static final String RECEIVE_ORDER_LIST = BASE + "/getReceiveOrderList";
    /**
     * 爆炸物种类
     */
    public static final String GET_EXPLOSIVE_TYPES = BASE + "/getExplosiveTypeList";
    /**
     * 新增民爆领取申请
     */
    public static final String ADD_RECEIVE_EXPLOSIVE_APPLY = BASE + "/addOrder";




    /*====================================================    民爆使用相关   ==============================================================*/

    /**
     * 民爆使用订单列表
     */
    public static final String USE_ORDER_LIST = BASE + "/getMineReceiveOrderList";


    public static final String ADD_USE_EXPLOSIVE_APPLY = BASE + "/addMineOrder";
    /**
     * 矿内领取人员
     */
    public static final String GET_RECEIVER_OF_MINE = BASE + "/getMineUserTypeList";




    /*====================================================    个人中心   ==============================================================*/


    /**
     * 个人详情
     */
    public static final String GET_USER_INFO = BASE + "/getUserInfo";
}