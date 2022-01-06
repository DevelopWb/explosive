package com.juntai.wisdom.explorsive;

public class AppHttpPath {

//        public static final String BASE_ROOT = "http://192.168.124.118:8080/explosive-manage/";
    public static final String BASE_ROOT = "http://www.juntaikeji.com:20708/explosive-manage/";
    public static final String BASE = BASE_ROOT + "app";

//        public static final String BASE_IMAGE = "http://192.168.124.118:9600";
    public static final String BASE_IMAGE = "http://www.juntaikeji.com:20710";


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
    /**
     * 民爆领取订单详情
     */
    public static final String RECEIVE_EXPLOSIVE_DETAIL = BASE + "/getReceiveOrderInfo";


    /**
     * 派出所审批
     */
    public static final String POLICE_APPROVE = BASE + "/policeApprove";
    public static final String POLICE_APPROVE_OF_MINE = BASE + "/minePoliceApprove";
    public static final String BRIGADE_APPROVE = BASE + "/brigadeApprove";
    public static final String LEADER_APPROVE = BASE + "/leaderApprove";

    /**
     * 配送人员列表
     */
    public static final String GET_DELIVERY_LIST = BASE + "/getDistributionUserList";
    /**
     * 出库
     */
    public static final String OUT_HOUSE = BASE + "/exWarehouse";
    /**
     * 配送
     */
    public static final String DELIVERY = BASE + "/distribution";




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


    /**
     * 民爆使用订单详情
     */
    public static final String USE_EXPLOSIVE_DETAIL = BASE + "/getMineReceiveOrderInfo";

    /**
     * 出库
     */
    public static final String OUT_IN_MINE = BASE + "/mineGrantReceive";
    /**
     * 使用
     */
    public static final String USE_IN_MINE = BASE + "/mineUseExplosive";

    /*====================================================    个人中心   ==============================================================*/


    /**
     * 个人详情
     */
    public static final String GET_USER_INFO = BASE + "/getUserInfo";
    public static final String ABOUT_US = BASE + "/contactUs";
    public static final String MODIFY_PWD = BASE + "/updatePassword";


    /**
     * 人脸验证
     */
    public static final String FACE_CHECK = BASE + "/faceVerification";
}