package com.juntai.wisdom.explorsive.main;


import android.text.TextUtils;

import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.AppNetModule;
import com.juntai.wisdom.explorsive.base.BaseAppPresent;
import com.juntai.wisdom.explorsive.bean.AllDosageBean;
import com.juntai.wisdom.explorsive.bean.BaseNormalRecyclerviewBean;
import com.juntai.wisdom.explorsive.bean.BaseUsageBean;
import com.juntai.wisdom.explorsive.bean.DeliveryListBean;
import com.juntai.wisdom.explorsive.bean.ExplosiveUsageBean;
import com.juntai.wisdom.explorsive.bean.ExplosiveUsageNumberBean;
import com.juntai.wisdom.explorsive.bean.FaceCheckBean;
import com.juntai.wisdom.explorsive.bean.FaceCheckResponseBean;
import com.juntai.wisdom.explorsive.bean.FragmentPicBean;
import com.juntai.wisdom.explorsive.bean.HomePageMenuBean;
import com.juntai.wisdom.explorsive.bean.IdNameBean;
import com.juntai.wisdom.explorsive.bean.IdNameListBean;
import com.juntai.wisdom.explorsive.bean.ImportantTagBean;
import com.juntai.wisdom.explorsive.bean.ItemSignBean;
import com.juntai.wisdom.explorsive.bean.LocationBean;
import com.juntai.wisdom.explorsive.bean.MineReceiverBean;
import com.juntai.wisdom.explorsive.bean.MultipleItem;
import com.juntai.wisdom.explorsive.bean.NewsBean;
import com.juntai.wisdom.explorsive.bean.ReceiveOrderDetailBean;
import com.juntai.wisdom.explorsive.bean.TextKeyValueBean;
import com.juntai.wisdom.explorsive.bean.TimeBean;
import com.juntai.wisdom.explorsive.bean.UseOrderDetailBean;
import com.juntai.wisdom.explorsive.bean.UserBean;
import com.juntai.wisdom.explorsive.utils.UrlFormatUtil;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.RequestBody;

/**
 * Describe:
 * Create by zhangzhenlong
 * 2020/3/7
 * email:954101549@qq.com
 */
public class MainPresent extends BaseAppPresent<IModel, MainContactInterface> {
    //矿场的
    public static final String RECEIVE_APPLY_REQUEST = "民爆领取申请";
    public static final String USE_APPLY_INSIDE = "矿内使用申请";
    public static final String MINE_MANAGER = "矿场管理发放";
    //负责审批的  监管单位
    public static final String APPROVE_RECEIVE = "民爆领取审批";
    public static final String APPROVE_USE = "矿场使用审批";
    //民爆局  物品管理单位
    public static final String EXPLOSIVE_MANAGE_OFFICE = "民爆管理发放";


    public static final String DOSAGE = "用量";

    @Override
    protected IModel createModel() {
        return null;
    }


    public List<HomePageMenuBean> getMyCenterMenus() {
        List<HomePageMenuBean> menuBeanList = new ArrayList<>();

        menuBeanList.add(new HomePageMenuBean(HomePageMenuBean.MODIFY_MOBILE, "DIANHUAXIUGAI", R.mipmap.mycenter_blue_litter_bg, R.mipmap.mycenter_mobile_bg, R.color.menu_blue));
        menuBeanList.add(new HomePageMenuBean(HomePageMenuBean.MODIFY_PWD, "MIMAXIUGAI", R.mipmap.mycenter_yellow_lighter_bg, R.mipmap.mycenter_pwd_bg, R.color.menu_orange));
        menuBeanList.add(new HomePageMenuBean(HomePageMenuBean.UPDATE, "JIANCHAGENGXIN", R.mipmap.mycenter_green_lighter_bg, R.mipmap.mycenter_update_bg, R.color.menu_green));
        menuBeanList.add(new HomePageMenuBean(HomePageMenuBean.ABOUT_US, "GUANYUWOMEN", R.mipmap.mycenter_red_lighter_bg, R.mipmap.mycenter_us_bg, R.color.menu_red));
        return menuBeanList;
    }


    /**
     * 获取首页菜单
     * 2 民爆申请人 1
     * 3 矿场内爆炸物申请人 1
     * 4 矿场内爆炸物仓库管理员 1
     * 5 派出所审批人 2
     * 6 治安大队审批人 3
     * 7 县公安局审批人 4
     * 8 民爆仓库管理员 5
     * 9 民爆仓库配送员 5
     *
     * @return
     */
    protected List<HomePageMenuBean> getHomePageMenu() {
        List<HomePageMenuBean> menus = new ArrayList<>();
        List<UserBean.DataBean.PostBean> postBeanList = UserInfoManager.getPostBeans();
        for (UserBean.DataBean.PostBean postBean : postBeanList) {
            if (8 == postBean.getId() || 9 == postBean.getId()) {
                menus.add(new HomePageMenuBean(EXPLOSIVE_MANAGE_OFFICE, "LINGQUSHENQING", R.mipmap.mycenter_blue_bg, R.mipmap.receive_apply_icon, R.color.white));
                break;
            }
            switch (postBean.getId()) {
                case 2:
                    menus.add(new HomePageMenuBean(RECEIVE_APPLY_REQUEST, "LINGQUSHENQING", R.mipmap.mycenter_blue_bg, R.mipmap.receive_apply_icon, R.color.white));
                    break;
                case 3:
                    menus.add(new HomePageMenuBean(USE_APPLY_INSIDE, "SHIYONGSHENQING", R.mipmap.mycenter_blue_bg, R.mipmap.receive_apply_icon, R.color.white));
                    break;
                case 4:
                    menus.add(new HomePageMenuBean(MINE_MANAGER, "GUANLIFAFANG", R.mipmap.mycenter_blue_bg, R.mipmap.menu_manage, R.color.white));
                    break;
                case 5:
                    menus.add(new HomePageMenuBean(APPROVE_RECEIVE, "LINGQUSHENPI", R.mipmap.mycenter_blue_bg, R.mipmap.receive_apply_icon, R.color.white));
                    menus.add(new HomePageMenuBean(APPROVE_USE, "SHIYONGSHENPI", R.mipmap.mycenter_green_bg, R.mipmap.menu_use_apply, R.color.white));
                    break;
                case 6:
                case 7:
                    menus.add(new HomePageMenuBean(APPROVE_RECEIVE, "LINGQUSHENPI", R.mipmap.mycenter_blue_bg, R.mipmap.receive_apply_icon, R.color.white));
                    break;
                default:
                    break;
            }
        }
        menus.add(new HomePageMenuBean(DOSAGE, "YONGLIANG", R.mipmap.menu_bg_blue_litter, R.mipmap.menu_dosage_icon, R.color.white));

        return menus;
    }


    /**
     * 民爆使用申请
     * showReceiver  是否展示领取人
     *
     * @return
     */
    public List<MultipleItem> getUseApplyData(UseOrderDetailBean.DataBean bean, boolean isDetail, boolean showReceiver) {
        List<MultipleItem> arrays = new ArrayList<>();

        arrays.add(new MultipleItem(MultipleItem.ITEM_NORMAL_RECYCLEVIEW, new BaseNormalRecyclerviewBean(
                MultipleItem.BASE_RECYCLERVIEW_TYPE_TEXT_VALUE, 1,
                getApplyerDataInUse(bean, isDetail))));
        if (!isDetail) {
            arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT_TIME, new TimeBean(MainContactInterface.PLAN_USE_START_TIME, bean == null ? null : bean.getEstimateStartUseTime(), "请选择预计使用开始时间", isDetail)));
            arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT_TIME, new TimeBean(MainContactInterface.PLAN_USE_END_TIME, bean == null ? null : bean.getEstimateEndUseTime(), "请选择预计使用结束时间", isDetail)));
        }
        arrays.add(new MultipleItem(MultipleItem.ITEM_LOCATION, new LocationBean(MainContactInterface.USE_LOCATION, bean == null ? null :
                bean.getEstimateUseAddress()
                , bean == null ? null : bean.getEstimateUseLatitude(), bean == null ? null : bean.getEstimateUseLongitude(), isDetail)));
        if (!isDetail) {
            initTextType(arrays, MultipleItem.ITEM_EDIT, MainContactInterface.APPLICATION, bean == null ? "" :
                    bean.getRemarks(), false, 1, isDetail);
        }

        arrays.add(new MultipleItem(MultipleItem.ITEM_APPLY_DOSAGE, bean == null ? getExplosiveDosage(isDetail) : new BaseUsageBean(bean.getExplosiveUsage(), isDetail)));
        if (showReceiver) {
            arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, "领取人"));

            arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT,
                    new TextKeyValueBean(MainContactInterface.SAFER, bean == null ? "" :
                            String.valueOf(bean.getSafetyName()), String.format("%s%s", "请选择",
                            MainContactInterface.SAFER), 0, true, isDetail)));
            arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT,
                    new TextKeyValueBean(MainContactInterface.BLASTER, bean == null ? "" :
                            String.valueOf(bean.getBlasterName()), String.format("%s%s", "请选择",
                            MainContactInterface.BLASTER), 0, true, isDetail)));
            arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT,
                    new TextKeyValueBean(MainContactInterface.MANAGER, bean == null ? "" :
                            String.valueOf(bean.getSafekeepingName()), String.format("%s%s", "请选择",
                            MainContactInterface.MANAGER), 0, true, isDetail)));
        }

        if (isDetail) {
            arrays.add(new MultipleItem(MultipleItem.ITEM_SIGN, new ItemSignBean(MainContactInterface.SIGN_TITLE_UNIT, 2, bean.getApplySign(), bean.getApplyDepartmentSeal())));
            if (TextUtils.isEmpty(bean.getPoliceSign())) {
                arrays.add(new MultipleItem(MultipleItem.ITEM_SIGN, new ItemSignBean(MainContactInterface.SIGN_TITLE_POLICE, 2 == UserInfoManager.getDepartmentType() ? 1 : 3, null, UserInfoManager.getDepartmentSign(), bean.getPoliceRemarks())));
            } else {
                arrays.add(new MultipleItem(MultipleItem.ITEM_SIGN, new ItemSignBean(MainContactInterface.SIGN_TITLE_POLICE, 2, bean.getPoliceSign(), bean.getPoliceDepartmentSeal(), bean.getPoliceRemarks())));
            }

        } else {
            arrays.add(new MultipleItem(MultipleItem.ITEM_SIGN, new ItemSignBean(MainContactInterface.SIGN_TITLE_UNIT, bean == null ? 0 : bean.getSignStatus(), bean == null ? null : bean.getApplySign(), bean == null ? UserInfoManager.getDepartmentSign() : bean.getApplyDepartmentSeal())));

        }
        return arrays;
    }

    /**
     * 矿内出库
     *
     * @return
     */
    public List<MultipleItem> getUseApplyOutInMineData(UseOrderDetailBean.DataBean bean, boolean isDetail) {
        List<MultipleItem> arrays = getUseApplyData(bean, true, false);
        arrays.add(new MultipleItem(MultipleItem.ITEM_TEXT,
                new TextKeyValueBean(MainContactInterface.OUT_IN_MINE_TIME, isDetail ? bean.getGrantTime() : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), isDetail)));
        arrays.add(new MultipleItem(MultipleItem.ITEM_LOCATION, new LocationBean(MainContactInterface.OUT_IN_MINE_ADDR, !isDetail ? null :
                bean.getGrantUseAddress()
                , !isDetail ? null : bean.getGrantUseLatitude(), !isDetail ? null : bean.getGrantUseLongitude(), isDetail)));

        arrays.add(new MultipleItem(MultipleItem.ITEM_FACE_CHECK, new FaceCheckBean(bean.getReceiveId(), MainContactInterface.RECEIVER + bean.getApplyUsername()
                , bean.getReceivePhoto(), bean.getReceiveSign(), !TextUtils.isEmpty(bean.getReceivePhoto()), isDetail
        )));
        arrays.add(new MultipleItem(MultipleItem.ITEM_FACE_CHECK, new FaceCheckBean(bean.getSafetyId(), MainContactInterface.SAFER + bean.getSafetyName()
                , bean.getSafetyPhoto(), bean.getSafetySign(), !TextUtils.isEmpty(bean.getSafetyPhoto()), isDetail
        )));
        arrays.add(new MultipleItem(MultipleItem.ITEM_FACE_CHECK, new FaceCheckBean(bean.getBlasterId(), MainContactInterface.BLASTER + bean.getBlasterName()
                , bean.getBlasterPhoto(), bean.getBlasterSign(), !TextUtils.isEmpty(bean.getBlasterPhoto()), isDetail
        )));
        arrays.add(new MultipleItem(MultipleItem.ITEM_FACE_CHECK, new FaceCheckBean(bean.getSafekeepingId(), MainContactInterface.MANAGER + bean.getSafekeepingName()
                , bean.getSafekeepingPhoto(), bean.getSafekeepingSign(), !TextUtils.isEmpty(bean.getSafekeepingPhoto()), isDetail
        )));
        arrays.add(new MultipleItem(MultipleItem.ITEM_ISSUE_NO, bean.getExplosiveUsageNumber().isEmpty() ? getExplosiveDosageNumbers(isDetail) : new BaseUsageBean(bean.getExplosiveUsageNumber(), isDetail, isDetail)));

        return arrays;
    }


    /**
     * 矿内使用
     *
     * @param bean
     * @param isDetail
     * @return
     */
    public List<MultipleItem> getApplyOfUseInMineData(UseOrderDetailBean.DataBean bean, boolean isDetail) {
        List<MultipleItem> arrays = getUseApplyOutInMineData(bean, true);
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, "使用人"));

        arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT,
                new TextKeyValueBean(MainContactInterface.SAFER, bean == null ? "" :
                        String.valueOf(bean.getUseSafetyName()), String.format("%s%s", "请选择",
                        MainContactInterface.SAFER), 0, true, isDetail)));
        arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT,
                new TextKeyValueBean(MainContactInterface.BLASTER, bean == null ? "" :
                        String.valueOf(bean.getUseBlasterName()), String.format("%s%s", "请选择",
                        MainContactInterface.BLASTER), 0, true, isDetail)));
        arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT,
                new TextKeyValueBean(MainContactInterface.MANAGER, bean == null ? "" :
                        String.valueOf(bean.getUseSafekeepingName()), String.format("%s%s", "请选择",
                        MainContactInterface.MANAGER), 0, true, isDetail)));
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, MainContactInterface.USE_RECORD_PHOTO));
        List<String> pics = new ArrayList<>();
        pics.add(TextUtils.isEmpty(bean.getUseBillUrl()) ? "-1" : bean.getUseBillUrl());
        arrays.add(new MultipleItem(MultipleItem.ITEM_FRAGMENT,
                new FragmentPicBean(MainContactInterface.USE_RECORD_PHOTO, 0, pics, isDetail)));
        initTextType(arrays, MultipleItem.ITEM_EDIT, MainContactInterface.REMARK, bean == null ? "" :
                bean.getUseRemarks(), false, 1, isDetail);
        arrays.add(new MultipleItem(MultipleItem.ITEM_RETURN_DOSAGE, bean == null || bean.getExplosiveUsageReturn().isEmpty() ? getExplosiveReturnDosage(isDetail) : new BaseUsageBean(bean.getExplosiveUsageReturn(), isDetail, bean.getIsReturn())));

        return arrays;
    }


    /**
     * 民爆领取申请
     *
     * @return
     */
    public List<MultipleItem> getRecieveApplyData(ReceiveOrderDetailBean.DataBean bean, boolean isDetail) {
        List<MultipleItem> arrays = new ArrayList<>();

        arrays.add(new MultipleItem(MultipleItem.ITEM_NORMAL_RECYCLEVIEW, new BaseNormalRecyclerviewBean(
                MultipleItem.BASE_RECYCLERVIEW_TYPE_TEXT_VALUE, 1,
                getApplyerData(bean, isDetail))));
        arrays.add(new MultipleItem(MultipleItem.ITEM_LOCATION, new LocationBean(MainContactInterface.USE_LOCATION, bean == null ? null :
                bean.getUseAddress()
                , bean == null ? null : bean.getUseLatitude(), bean == null ? null : bean.getUseLongitude(), isDetail)));
        if (!isDetail) {
            initTextType(arrays, MultipleItem.ITEM_EDIT, MainContactInterface.APPLICATION, bean == null ? "" :
                    bean.getRemarks(), false, 1, isDetail);
        }
        arrays.add(new MultipleItem(MultipleItem.ITEM_APPLY_DOSAGE, bean == null ? getExplosiveDosage(isDetail) : new BaseUsageBean(bean.getExplosiveUsage(), isDetail)));
        if (isDetail) {
            arrays.add(new MultipleItem(MultipleItem.ITEM_SIGN, new ItemSignBean(MainContactInterface.SIGN_TITLE_UNIT, 2, bean.getApplySign(), bean.getApplyDepartmentSeal())));
            if (TextUtils.isEmpty(bean.getPoliceSign())) {
                arrays.add(new MultipleItem(MultipleItem.ITEM_SIGN, new ItemSignBean(MainContactInterface.SIGN_TITLE_POLICE, 3, null, UserInfoManager.getDepartmentSign(), bean.getPoliceRemarks())));
            } else {
                arrays.add(new MultipleItem(MultipleItem.ITEM_SIGN, new ItemSignBean(MainContactInterface.SIGN_TITLE_POLICE, 2, bean.getPoliceSign(), bean.getPoliceDepartmentSeal(), bean.getPoliceRemarks())));
            }
            if (TextUtils.isEmpty(bean.getBrigadeSign())) {
                arrays.add(new MultipleItem(MultipleItem.ITEM_SIGN, new ItemSignBean(MainContactInterface.SIGN_TITLE_BRIGADE, 3, null, UserInfoManager.getDepartmentSign(), bean.getBrigadeRemarks())));
            } else {
                arrays.add(new MultipleItem(MultipleItem.ITEM_SIGN, new ItemSignBean(MainContactInterface.SIGN_TITLE_BRIGADE, 2, bean.getBrigadeSign(), bean.getBrigadeDepartmentSeal(), bean.getBrigadeRemarks())));
            }
            if (TextUtils.isEmpty(bean.getLeaderSign())) {
                arrays.add(new MultipleItem(MultipleItem.ITEM_SIGN, new ItemSignBean(MainContactInterface.SIGN_TITLE_LEADER, 3, null, UserInfoManager.getDepartmentSign(), bean.getLeaderRemarks())));
            } else {
                arrays.add(new MultipleItem(MultipleItem.ITEM_SIGN, new ItemSignBean(MainContactInterface.SIGN_TITLE_LEADER, 2, bean.getLeaderSign(), bean.getLeaderDepartmentSeal(), bean.getLeaderRemarks())));
            }

        } else {
            arrays.add(new MultipleItem(MultipleItem.ITEM_SIGN, new ItemSignBean(MainContactInterface.SIGN_TITLE_UNIT, bean == null ? 0 : bean.getSignStatus(), bean == null ? null : bean.getApplySign(), bean == null ? UserInfoManager.getDepartmentSign() : bean.getApplyDepartmentSeal())));

        }


        return arrays;
    }

    /**
     * 民爆领取申请审查
     *
     * @return
     */
    public List<MultipleItem> getRecieveApplyCheckData(ReceiveOrderDetailBean.DataBean bean, boolean isDetail) {
        List<MultipleItem> arrays = new ArrayList<>();

        arrays.add(new MultipleItem(MultipleItem.ITEM_NORMAL_RECYCLEVIEW, new BaseNormalRecyclerviewBean(
                MultipleItem.BASE_RECYCLERVIEW_TYPE_TEXT_VALUE, 1,
                getApplyerData(bean, true))));
        arrays.add(new MultipleItem(MultipleItem.ITEM_LOCATION, new LocationBean(MainContactInterface.USE_LOCATION, bean == null ? null :
                bean.getUseAddress()
                , bean == null ? null : bean.getUseLatitude(), bean == null ? null : bean.getUseLongitude(), isDetail)));
        if (!isDetail) {
            initTextType(arrays, MultipleItem.ITEM_EDIT, MainContactInterface.APPLICATION, bean == null ? "" :
                    bean.getRemarks(), false, 1, isDetail);
        }

        arrays.add(new MultipleItem(MultipleItem.ITEM_APPLY_DOSAGE, bean == null ? getExplosiveDosage(isDetail) : new BaseUsageBean(bean.getExplosiveUsage(), isDetail)));
        arrays.add(new MultipleItem(MultipleItem.ITEM_SIGN, new ItemSignBean(MainContactInterface.SIGN_TITLE_UNIT, 2, bean.getApplySign(), bean.getApplyDepartmentSeal())));
        if (TextUtils.isEmpty(bean.getPoliceSign())) {
            arrays.add(new MultipleItem(MultipleItem.ITEM_SIGN, new ItemSignBean(MainContactInterface.SIGN_TITLE_POLICE, 2 == UserInfoManager.getDepartmentType() ? 1 : 3, null, UserInfoManager.getDepartmentSign(), bean.getPoliceRemarks())));
        } else {
            arrays.add(new MultipleItem(MultipleItem.ITEM_SIGN, new ItemSignBean(MainContactInterface.SIGN_TITLE_POLICE, 2, bean.getPoliceSign(), bean.getPoliceDepartmentSeal(), bean.getPoliceRemarks())));
        }
        if (TextUtils.isEmpty(bean.getBrigadeSign())) {
            arrays.add(new MultipleItem(MultipleItem.ITEM_SIGN, new ItemSignBean(MainContactInterface.SIGN_TITLE_BRIGADE, 3 == UserInfoManager.getDepartmentType() ? 1 : 3, null, UserInfoManager.getDepartmentSign(), bean.getBrigadeRemarks())));
        } else {
            arrays.add(new MultipleItem(MultipleItem.ITEM_SIGN, new ItemSignBean(MainContactInterface.SIGN_TITLE_BRIGADE, 2, bean.getBrigadeSign(), bean.getBrigadeDepartmentSeal(), bean.getBrigadeRemarks())));
        }
        if (TextUtils.isEmpty(bean.getLeaderSign())) {
            arrays.add(new MultipleItem(MultipleItem.ITEM_SIGN, new ItemSignBean(MainContactInterface.SIGN_TITLE_LEADER, 4 == UserInfoManager.getDepartmentType() ? 1 : 3, null, UserInfoManager.getDepartmentSign(), bean.getLeaderRemarks())));
        } else {
            arrays.add(new MultipleItem(MultipleItem.ITEM_SIGN, new ItemSignBean(MainContactInterface.SIGN_TITLE_LEADER, 2, bean.getLeaderSign(), bean.getLeaderDepartmentSeal(), bean.getLeaderRemarks())));
        }

        return arrays;
    }

    /**
     * 民爆领取出库
     *
     * @return
     */
    public List<MultipleItem> getRecieveApplyOutData(ReceiveOrderDetailBean.DataBean bean, boolean isDetail) {
        List<MultipleItem> arrays = getRecieveApplyCheckData(bean, true);
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, "出库单详情"));
        arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT,
                new TextKeyValueBean(MainContactInterface.DELIVERY, bean == null ? "" :
                        String.valueOf(getDeliverys(bean.getDeliveryUser())), String.format("%s%s", "请选择",
                        MainContactInterface.DELIVERY), 0, true, bean.getDeliveryUser(), isDetail)));
        arrays.add(new MultipleItem(MultipleItem.ITEM_ISSUE_NO, bean.getExplosiveUsageNumber().isEmpty() ? getExplosiveDosageNumbers(isDetail) : new BaseUsageBean(bean.getExplosiveUsageNumber(), isDetail, isDetail)));
        return arrays;
    }

    /**
     * 民爆领取出库
     *
     * @return
     */
    public List<MultipleItem> getRecieveApplyDeliveryData(ReceiveOrderDetailBean.DataBean bean, boolean isDetail) {
        List<MultipleItem> arrays = getRecieveApplyOutData(bean, true);
        arrays.add(new MultipleItem(MultipleItem.ITEM_TEXT,
                new TextKeyValueBean(MainContactInterface.DELIVERY_TIME, isDetail ? bean.getArriveTime() : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), isDetail)));
        arrays.add(new MultipleItem(MultipleItem.ITEM_LOCATION, new LocationBean(MainContactInterface.DELIVERY_ADDR, !isDetail ? null :
                bean.getArriveAddress()
                , !isDetail ? null : bean.getArriveLatitude(), !isDetail ? null : bean.getArriveLongitude(), isDetail)));
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_SMALL, new ImportantTagBean
                (MainContactInterface.ARRIVERE_PHOTO, false)));
        List<String> pics = new ArrayList<>();
        pics.add(TextUtils.isEmpty(bean.getArrivePicture()) ? "-1" : bean.getArrivePicture());
        arrays.add(new MultipleItem(MultipleItem.ITEM_FRAGMENT,
                new FragmentPicBean(MainContactInterface.ARRIVERE_PHOTO, 0, pics, isDetail)));
        arrays.add(new MultipleItem(MultipleItem.ITEM_SIGN, new ItemSignBean(MainContactInterface.ARRIVERE_SIGN, isDetail ? 2 : 0, bean.getArriveSign(), null)));

        return arrays;
    }

    /**
     * 获取配送人员
     *
     * @param deliveryUser
     * @return
     */
    private String getDeliverys(List<DeliveryListBean.DataBean> deliveryUser) {
        StringBuilder sb = new StringBuilder(deliveryUser.size());
        for (DeliveryListBean.DataBean deliveryUserBean : deliveryUser) {
            sb.append(deliveryUserBean.getUsername());
        }
        return sb.toString();
    }


    /**
     * 获取默认的数据
     *
     * @return
     */
    private BaseUsageBean getExplosiveDosage(boolean isDetail) {
        List<ExplosiveUsageBean> arrays = new ArrayList<>();
        arrays.add(new ExplosiveUsageBean("请选择爆炸物种类", 0, "零", "个"));
        return new BaseUsageBean(arrays, isDetail);
    }

    /**
     * 获取默认的数据
     *
     * @return
     */
    private BaseUsageBean getExplosiveReturnDosage(boolean isDetail) {
        List<UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean> arrays = new ArrayList<>();
        arrays.add(new UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean(0, "请选择爆炸物种类", 0, "零", "个", null));
        return new BaseUsageBean(arrays, isDetail, 0);
    }

    /**
     * 获取默认的数据
     *
     * @return
     */
    private BaseUsageBean getExplosiveDosageNumbers(boolean isDetail) {
        List<ExplosiveUsageNumberBean> arrays = new ArrayList<>();
        arrays.add(new ExplosiveUsageNumberBean(0, "请选择爆炸物种类", "0", "0"));
        return new BaseUsageBean(arrays, isDetail, false);
    }

    /**
     * 申请人信息
     *
     * @param dataBean
     * @return
     */

    public List<TextKeyValueBean> getApplyerData(ReceiveOrderDetailBean.DataBean dataBean, boolean isDetail) {
        List<TextKeyValueBean> arrays = new ArrayList<>();
        arrays.add(new TextKeyValueBean(MainContactInterface.APPLY_NO, !isDetail ? new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) : dataBean.getApplyNumber(), isDetail));
        arrays.add(new TextKeyValueBean(MainContactInterface.APPLY_USER, !isDetail ? UserInfoManager.getUserName() : dataBean.getApplyUsername(), isDetail));
        arrays.add(new TextKeyValueBean(MainContactInterface.APPLY_USER_MOBILE, !isDetail ? UserInfoManager.getMobile() : dataBean.getApplyPhone(), isDetail));
        arrays.add(new TextKeyValueBean(MainContactInterface.APPLY_USER_UNIT, !isDetail ? UserInfoManager.getDepartmentName() : dataBean.getApplyDepartmentName(), isDetail));
        arrays.add(new TextKeyValueBean(MainContactInterface.APPLY_USER_UNIT_ADDR, !isDetail ? UserInfoManager.getDepartmentAddr() : dataBean.getApplyDepartmentAddress(), isDetail));
        arrays.add(new TextKeyValueBean(MainContactInterface.APPLY_TIME, !isDetail ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) : dataBean.getApplyTime(), isDetail));
        if (isDetail) {
            arrays.add(new TextKeyValueBean(MainContactInterface.APPLICATION, dataBean.getRemarks(), isDetail));

        }

        return arrays;
    }

    /**
     * 申请人信息
     *
     * @param dataBean
     * @return
     */

    public List<TextKeyValueBean> getApplyerDataInUse(UseOrderDetailBean.DataBean dataBean, boolean isDetail) {
        List<TextKeyValueBean> arrays = new ArrayList<>();
        arrays.add(new TextKeyValueBean(MainContactInterface.APPLY_NO, !isDetail ? new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) : dataBean.getApplyNumber(), isDetail));
        arrays.add(new TextKeyValueBean(MainContactInterface.APPLY_USER, dataBean == null ? UserInfoManager.getUserName() : dataBean.getApplyUsername(), isDetail));
        arrays.add(new TextKeyValueBean(MainContactInterface.APPLY_USER_MOBILE, dataBean == null ? UserInfoManager.getMobile() : dataBean.getApplyPhone(), isDetail));
        arrays.add(new TextKeyValueBean(MainContactInterface.APPLY_USER_UNIT, dataBean == null ? UserInfoManager.getDepartmentName() : dataBean.getApplyDepartmentName(), isDetail));
        arrays.add(new TextKeyValueBean(MainContactInterface.APPLY_USER_UNIT_ADDR, dataBean == null ? UserInfoManager.getDepartmentAddr() : dataBean.getApplyDepartmentAddress(), isDetail));
        arrays.add(new TextKeyValueBean(MainContactInterface.APPLY_TIME, !isDetail ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) : dataBean.getApplyTime(), isDetail));
        if (isDetail) {
            arrays.add(new TextKeyValueBean(MainContactInterface.PLAN_USE_START_TIME, dataBean.getEstimateStartUseTime(), isDetail));
            arrays.add(new TextKeyValueBean(MainContactInterface.PLAN_USE_END_TIME, dataBean.getEstimateEndUseTime(), isDetail));
            arrays.add(new TextKeyValueBean(MainContactInterface.APPLICATION, dataBean.getRemarks(), isDetail));
        }
        return arrays;
    }

    /**
     * initTextType
     *
     * @param arrays
     * @param typeName
     * @param editHeightType 0代表高度固定 1代表不固定
     */
    private void initTextType(List<MultipleItem> arrays, int layoutType, String typeName, String value,
                              boolean isImportant, int editHeightType, boolean isDetail) {
        switch (layoutType) {
            case MultipleItem.ITEM_SELECT:
                arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_SMALL, new ImportantTagBean
                        (typeName, isImportant)));
                arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT,
                        new TextKeyValueBean(typeName, value, String.format("%s%s", "请选择",
                                typeName), 0, isImportant, isDetail)));
                break;
            case MultipleItem.ITEM_EDIT:
                arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_SMALL, new ImportantTagBean(typeName,
                        isImportant)));
                arrays.add(new MultipleItem(MultipleItem.ITEM_EDIT,
                        new TextKeyValueBean(typeName, value,
                                String.format("%s%s", "请输入", typeName), editHeightType, isImportant, isDetail)));

                break;
            case MultipleItem.ITEM_EDIT2:
                arrays.add(new MultipleItem(MultipleItem.ITEM_EDIT2,
                        new TextKeyValueBean(typeName, value,
                                String.format("%s%s", "请输入", typeName), editHeightType, isImportant, isDetail)));
                break;
            default:
                break;
        }

    }


    public void addExplosiveApply(RequestBody body, String tag) {
        AppNetModule.createrRetrofit()
                .addExplosiveReceiveApply(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

    public void modifyPwd(RequestBody body, String tag) {
        AppNetModule.createrRetrofit()
                .modifyPwd(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

    public void getNewsList(RequestBody body, String tag) {
        AppNetModule.createrRetrofit()
                .getNewsList(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<NewsBean>(getView()) {
                    @Override
                    public void onSuccess(NewsBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

    public void getExplosiveReceiveDetail(RequestBody body, String tag) {
        AppNetModule.createrRetrofit()
                .getExplosiveReceiveDetail(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<ReceiveOrderDetailBean>(getView()) {
                    @Override
                    public void onSuccess(ReceiveOrderDetailBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

    public void policeApprove(RequestBody body, String tag) {
        AppNetModule.createrRetrofit()
                .policeApprove(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

    public void policeApproveOfMine(RequestBody body, String tag) {
        AppNetModule.createrRetrofit()
                .policeApproveOfMine(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

    public void brigadeApprove(RequestBody body, String tag) {
        AppNetModule.createrRetrofit()
                .brigadeApprove(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

    public void leaderApprove(RequestBody body, String tag) {
        AppNetModule.createrRetrofit()
                .leaderApprove(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

    public void getDeliveryList(RequestBody body, String tag) {
        AppNetModule.createrRetrofit()
                .getDeliveryList(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<DeliveryListBean>(getView()) {
                    @Override
                    public void onSuccess(DeliveryListBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

    public void outHouse(RequestBody body, String tag) {
        AppNetModule.createrRetrofit()
                .outHouse(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

    public void outInMine(RequestBody body, String tag) {
        AppNetModule.createrRetrofit()
                .outInMine(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

    public void useInMine(RequestBody body, String tag) {
        AppNetModule.createrRetrofit()
                .useInMine(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

    public void getAllMine(RequestBody body, String tag) {
        AppNetModule.createrRetrofit()
                .getAllMine(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<IdNameListBean>(getView()) {
                    @Override
                    public void onSuccess(IdNameListBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

    public void delivery(RequestBody body, String tag) {
        AppNetModule.createrRetrofit()
                .delivery(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

    public void getExplosiveUseDetail(RequestBody body, String tag) {
        AppNetModule.createrRetrofit()
                .getExplosiveUseDetail(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<UseOrderDetailBean>(getView()) {
                    @Override
                    public void onSuccess(UseOrderDetailBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

    public void addExplosiveUseApply(RequestBody body, String tag) {
        AppNetModule.createrRetrofit()
                .addExplosiveUseApply(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

    public void getReceiverOfMine(RequestBody body, String tag) {
        AppNetModule.createrRetrofit()
                .getReceiverOfMine(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<MineReceiverBean>(getView()) {
                    @Override
                    public void onSuccess(MineReceiverBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

    public void getStockOfMine(RequestBody body, String tag) {
        AppNetModule.createrRetrofit()
                .getStockOfMine(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<AllDosageBean>(getView()) {
                    @Override
                    public void onSuccess(AllDosageBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

    public void getUseStatistics(RequestBody body, String tag) {
        AppNetModule.createrRetrofit()
                .getUseStatistics(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<AllDosageBean>(getView()) {
                    @Override
                    public void onSuccess(AllDosageBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

    public void getReceiveStatistics(RequestBody body, String tag) {
        AppNetModule.createrRetrofit()
                .getReceiveStatistics(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<AllDosageBean>(getView()) {
                    @Override
                    public void onSuccess(AllDosageBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

    public void startFaceCheck(RequestBody body, String tag) {
        AppNetModule.createrRetrofit()
                .startFaceCheck(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<FaceCheckResponseBean>(getView()) {
                    @Override
                    public void onSuccess(FaceCheckResponseBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

}
