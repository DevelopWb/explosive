package com.juntai.wisdom.explorsive;

public class AppHttpPath {

    //            public static final String BASE = "http://192.168.124.115:8080/disabledPersonsFederation/u/app";
    public static final String BASE = "http://192.168.124.118:8080/explosive-manage/app";

    public static final String BASE_IMAGE = "http://192.168.124.118:9600";
    /**
     * 登录
     */
    public static final String LOGIN = BASE + "/login";


    /**
     * 检查更新
     */
//    public static final String APP_UPDATE = BASE + "/detectionAppVersions.shtml";
    public static final String APP_UPDATE = BASE + "";

    /**
     * 获取短信验证码
     */
    public static final String GET_SMS_CODE = BASE + "getSMSCode.shtml";
}