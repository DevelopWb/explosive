package com.juntai.wisdom.explorsive.main;

import com.juntai.disabled.basecomponent.mvp.IView;


/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-12-22 15:43
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-12-22 15:43
 */
public interface MainContactInterface extends IView {

    String REMARK = "备注";
    String IS_RETURN = "是否退库";
    String APPLICATION = "用途";
    String USE_LOCATION = "使用地点";
    String UPLOAD_SIGN = "上传签名";
    String SIGN_TITLE_UNIT = "申请单位盖章签字";
    String SIGN_TITLE_POLICE = "派出所盖章签字";
    String SIGN_TITLE_BRIGADE = "治安大队盖章签字";
    String SIGN_TITLE_LEADER = "局领导盖章签字";

    String APPLY_NO = "申请编号:";
    String APPLY_USER = "申请人:";
    String APPLY_USER_MOBILE = "联系电话:";
    String APPLY_USER_UNIT = "申请单位:";
    String APPLY_USER_UNIT_ADDR = "单位地址:";
    String APPLY_TIME = "申请时间:";
    String PLAN_USE_START_TIME = "预计使用开始时间:";
    String PLAN_USE_END_TIME = "预计使用结束时间:";
    String DELIVERY = "配送人员:";
    String DELIVERY_TIME = "配送时间:";
    String DELIVERY_ADDR = "配送地点:";
    String OUT_IN_MINE_TIME = "发放时间:";
    String OUT_IN_MINE_ADDR = "发放地点:";
    String ARRIVERE_PHOTO = "单据接收方签字后拍照";
    String USE_RECORD_PHOTO = "使用单据拍照";
    String RECEIVER = "领取人:";
    String SAFER = "安全员:";
    String BLASTER = "爆破员:";
    String MANAGER = "保管员:";

    String ARRIVERE_SIGN = "接收矿场签字确认";

    String  TAB_TITLES = "tabtitles";
    String TAB_ID = "tabIs";
    String USE_DOSAGE = "矿场内爆炸物使用统计";
    String RECEIVE_DOSAGE = "民爆爆炸物领取统计";

}
