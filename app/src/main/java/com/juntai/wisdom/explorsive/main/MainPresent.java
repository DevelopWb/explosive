package com.juntai.wisdom.explorsive.main;


import android.text.TextUtils;

import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.AppNetModule;
import com.juntai.wisdom.explorsive.base.BaseAppPresent;
import com.juntai.wisdom.explorsive.base.TextKeyValueAdapter;
import com.juntai.wisdom.explorsive.bean.BaseNormalRecyclerviewBean;
import com.juntai.wisdom.explorsive.bean.DeliveryListBean;
import com.juntai.wisdom.explorsive.bean.ExplosiveUsageBean;
import com.juntai.wisdom.explorsive.bean.ExplosiveUsageNumberBean;
import com.juntai.wisdom.explorsive.bean.FaceCheckBean;
import com.juntai.wisdom.explorsive.bean.FaceCheckResponseBean;
import com.juntai.wisdom.explorsive.bean.FragmentPicBean;
import com.juntai.wisdom.explorsive.bean.IdNameBean;
import com.juntai.wisdom.explorsive.bean.ImportantTagBean;
import com.juntai.wisdom.explorsive.bean.ItemSignBean;
import com.juntai.wisdom.explorsive.bean.LocationBean;
import com.juntai.wisdom.explorsive.bean.MineReceiverBean;
import com.juntai.wisdom.explorsive.bean.MultipleItem;
import com.juntai.wisdom.explorsive.bean.MyMenuBean;
import com.juntai.wisdom.explorsive.bean.RadioBean;
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
import retrofit2.http.Url;

/**
 * Describe:
 * Create by zhangzhenlong
 * 2020/3/7
 * email:954101549@qq.com
 */
public class MainPresent extends BaseAppPresent<IModel, MainContactInterface> {
    //矿场的
    public static final String MINE_REQUEST = "民爆领取申请";
    public static final String MINE_REQUEST_INSIDE = "矿内使用申请";
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
    protected List<MyMenuBean> getHomePageMenu() {
        List<MyMenuBean> menus = new ArrayList<>();
// TODO: 2021-12-20  这个地方的资源文件回头需要补充
        List<UserBean.DataBean.PostBean> postBeanList = UserInfoManager.getPostBeans();
        for (UserBean.DataBean.PostBean postBean : postBeanList) {
            if (8 == postBean.getId() || 9 == postBean.getId()) {
                menus.add(new MyMenuBean(EXPLOSIVE_MANAGE_OFFICE, R.mipmap.ic_launcher));
                break;
            }
            switch (postBean.getId()) {
                case 2:
                    menus.add(new MyMenuBean(MINE_REQUEST, R.mipmap.ic_launcher));
                    break;
                case 3:
                    menus.add(new MyMenuBean(MINE_REQUEST_INSIDE, R.mipmap.ic_launcher));
                    break;
                case 4:
                    menus.add(new MyMenuBean(MINE_MANAGER, R.mipmap.ic_launcher));
                    break;
                case 5:
                    menus.add(new MyMenuBean(APPROVE_RECEIVE, R.mipmap.ic_launcher));
                    menus.add(new MyMenuBean(APPROVE_USE, R.mipmap.ic_launcher));
                    break;
                case 6:
                case 7:
                    menus.add(new MyMenuBean(APPROVE_RECEIVE, R.mipmap.ic_launcher));
                    break;
                default:
                    break;
            }
        }
        menus.add(new MyMenuBean(DOSAGE, R.mipmap.ic_launcher));

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
            arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT_TIME, new TimeBean(MainContactInterface.PLAN_USE_START_TIME, bean == null ? null : bean.getEstimateStartUseTime(), "请选择预计使用开始时间")));
            arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT_TIME, new TimeBean(MainContactInterface.PLAN_USE_END_TIME, bean == null ? null : bean.getEstimateEndUseTime(), "请选择预计使用结束时间")));
        }
        arrays.add(new MultipleItem(MultipleItem.ITEM_LOCATION, new LocationBean(MainContactInterface.USE_LOCATION, bean == null ? null :
                bean.getEstimateUseAddress()
                , bean == null ? null : bean.getEstimateUseLatitude(), bean == null ? null : bean.getEstimateUseLongitude())));
        initTextType(arrays, MultipleItem.ITEM_EDIT, MainContactInterface.APPLICATION, bean == null ? "" :
                bean.getRemarks(), false, 1);
        arrays.add(new MultipleItem(MultipleItem.ITEM_APPLY_DOSAGE, bean == null ? getExplosiveDosage() : bean.getExplosiveUsage()));
        if (showReceiver) {
            arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, "领取人"));

            arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT,
                    new TextKeyValueBean(MainContactInterface.SAFER, bean == null ? "" :
                            String.valueOf(bean.getSafetyName()), String.format("%s%s", "请选择",
                            MainContactInterface.SAFER), 0, true)));
            arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT,
                    new TextKeyValueBean(MainContactInterface.BLASTER, bean == null ? "" :
                            String.valueOf(bean.getBlasterName()), String.format("%s%s", "请选择",
                            MainContactInterface.BLASTER), 0, true)));
            arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT,
                    new TextKeyValueBean(MainContactInterface.MANAGER, bean == null ? "" :
                            String.valueOf(bean.getSafekeepingName()), String.format("%s%s", "请选择",
                            MainContactInterface.MANAGER), 0, true)));
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
                new TextKeyValueBean(MainContactInterface.OUT_IN_MINE_TIME, isDetail ? bean.getGrantTime() : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))));
        arrays.add(new MultipleItem(MultipleItem.ITEM_LOCATION, new LocationBean(MainContactInterface.OUT_IN_MINE_ADDR, !isDetail ? null :
                bean.getGrantUseAddress()
                , !isDetail ? null : bean.getGrantUseLatitude(), !isDetail ? null : bean.getGrantUseLongitude())));

        arrays.add(new MultipleItem(MultipleItem.ITEM_FACE_CHECK, new FaceCheckBean(bean.getReceiveId(), MainContactInterface.RECEIVER + bean.getApplyUsername()
                , bean.getReceivePhoto(), bean.getReceiveSign(), !TextUtils.isEmpty(bean.getReceivePhoto())
        )));
        arrays.add(new MultipleItem(MultipleItem.ITEM_FACE_CHECK, new FaceCheckBean(bean.getSafetyId(), MainContactInterface.SAFER + bean.getSafetyName()
                , bean.getSafetyPhoto(), bean.getSafetySign(), !TextUtils.isEmpty(bean.getSafetyPhoto())
        )));
        arrays.add(new MultipleItem(MultipleItem.ITEM_FACE_CHECK, new FaceCheckBean(bean.getBlasterId(), MainContactInterface.BLASTER + bean.getBlasterName()
                , bean.getBlasterPhoto(), bean.getBlasterSign(), !TextUtils.isEmpty(bean.getBlasterPhoto())
        )));
        arrays.add(new MultipleItem(MultipleItem.ITEM_FACE_CHECK, new FaceCheckBean(bean.getSafekeepingId(), MainContactInterface.MANAGER + bean.getSafekeepingName()
                , bean.getSafekeepingPhoto(), bean.getSafekeepingSign(), !TextUtils.isEmpty(bean.getSafekeepingPhoto())
        )));
        arrays.add(new MultipleItem(MultipleItem.ITEM_ISSUE_NO, bean.getExplosiveUsageNumber().isEmpty() ? getExplosiveDosageNumbers() : bean.getExplosiveUsageNumber()));

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
                        String.valueOf(bean.getSafetyName()), String.format("%s%s", "请选择",
                        MainContactInterface.SAFER), 0, true)));
        arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT,
                new TextKeyValueBean(MainContactInterface.BLASTER, bean == null ? "" :
                        String.valueOf(bean.getBlasterName()), String.format("%s%s", "请选择",
                        MainContactInterface.BLASTER), 0, true)));
        arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT,
                new TextKeyValueBean(MainContactInterface.MANAGER, bean == null ? "" :
                        String.valueOf(bean.getSafekeepingName()), String.format("%s%s", "请选择",
                        MainContactInterface.MANAGER), 0, true)));
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, MainContactInterface.USE_RECORD_PHOTO));
        List<String> pics = new ArrayList<>();
        pics.add(UrlFormatUtil.getImageOriginalUrl(bean.getUseBillUrl()));
        arrays.add(new MultipleItem(MultipleItem.ITEM_FRAGMENT,
                new FragmentPicBean(MainContactInterface.USE_RECORD_PHOTO, 0, pics)));
        initTextType(arrays, MultipleItem.ITEM_EDIT, MainContactInterface.REMARK, bean == null ? "" :
                bean.getUseRemarks(), false, 1);
        arrays.add(new MultipleItem(MultipleItem.ITEM_RADIO, new RadioBean(MainContactInterface.IS_RETURN, bean.getIsReturn(),
                new String[]{"无需退库", "需要退库"})));
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
                , bean == null ? null : bean.getUseLatitude(), bean == null ? null : bean.getUseLongitude())));
        initTextType(arrays, MultipleItem.ITEM_EDIT, MainContactInterface.APPLICATION, bean == null ? "" :
                bean.getRemarks(), false, 1);
        arrays.add(new MultipleItem(MultipleItem.ITEM_APPLY_DOSAGE, bean == null ? getExplosiveDosage() : bean.getExplosiveUsage()));
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
    public List<MultipleItem> getRecieveApplyCheckData(ReceiveOrderDetailBean.DataBean bean) {
        List<MultipleItem> arrays = new ArrayList<>();

        arrays.add(new MultipleItem(MultipleItem.ITEM_NORMAL_RECYCLEVIEW, new BaseNormalRecyclerviewBean(
                MultipleItem.BASE_RECYCLERVIEW_TYPE_TEXT_VALUE, 1,
                getApplyerData(bean, true))));
        arrays.add(new MultipleItem(MultipleItem.ITEM_LOCATION, new LocationBean(MainContactInterface.USE_LOCATION, bean == null ? null :
                bean.getUseAddress()
                , bean == null ? null : bean.getUseLatitude(), bean == null ? null : bean.getUseLongitude())));
        initTextType(arrays, MultipleItem.ITEM_EDIT, MainContactInterface.APPLICATION, bean == null ? "" :
                bean.getRemarks(), false, 1);
        arrays.add(new MultipleItem(MultipleItem.ITEM_APPLY_DOSAGE, bean == null ? getExplosiveDosage() : bean.getExplosiveUsage()));
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
    public List<MultipleItem> getRecieveApplyOutData(ReceiveOrderDetailBean.DataBean bean) {
        List<MultipleItem> arrays = getRecieveApplyCheckData(bean);
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, "出库单详情"));
        arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT,
                new TextKeyValueBean(MainContactInterface.DELIVERY, bean == null ? "" :
                        String.valueOf(getDeliverys(bean.getDeliveryUser())), String.format("%s%s", "请选择",
                        MainContactInterface.DELIVERY), 0, true, bean.getDeliveryUser())));
        arrays.add(new MultipleItem(MultipleItem.ITEM_ISSUE_NO, bean.getExplosiveUsageNumber().isEmpty() ? getExplosiveDosageNumbers() : bean.getExplosiveUsageNumber()));
        return arrays;
    }

    /**
     * 民爆领取出库
     *
     * @return
     */
    public List<MultipleItem> getRecieveApplyDeliveryData(ReceiveOrderDetailBean.DataBean bean, boolean isDetail) {
        List<MultipleItem> arrays = getRecieveApplyOutData(bean);
        arrays.add(new MultipleItem(MultipleItem.ITEM_TEXT,
                new TextKeyValueBean(MainContactInterface.DELIVERY_TIME, isDetail ? bean.getArriveTime() : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))));
        arrays.add(new MultipleItem(MultipleItem.ITEM_LOCATION, new LocationBean(MainContactInterface.DELIVERY_ADDR, !isDetail ? null :
                bean.getArriveAddress()
                , !isDetail ? null : bean.getArriveLatitude(), !isDetail ? null : bean.getArriveLongitude())));
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_SMALL, new ImportantTagBean
                (MainContactInterface.ARRIVERE_PHOTO, false)));
        List<String> pics = new ArrayList<>();
        pics.add(UrlFormatUtil.getImageOriginalUrl(bean.getArrivePicture()));
        arrays.add(new MultipleItem(MultipleItem.ITEM_FRAGMENT,
                new FragmentPicBean(MainContactInterface.ARRIVERE_PHOTO, 0, pics)));
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
    private List<ExplosiveUsageBean> getExplosiveDosage() {
        List<ExplosiveUsageBean> arrays = new ArrayList<>();
        arrays.add(new ExplosiveUsageBean("请选择爆炸物种类", 0, "零", "个"));
        return arrays;
    }

    /**
     * 获取默认的数据
     *
     * @return
     */
    private List<ExplosiveUsageNumberBean> getExplosiveDosageNumbers() {
        List<ExplosiveUsageNumberBean> arrays = new ArrayList<>();
        arrays.add(new ExplosiveUsageNumberBean(0, "请选择爆炸物种类", "0", "0"));
        return arrays;
    }

    /**
     * 申请人信息
     *
     * @param dataBean
     * @return
     */

    public List<TextKeyValueBean> getApplyerData(ReceiveOrderDetailBean.DataBean dataBean, boolean isDetail) {
        List<TextKeyValueBean> arrays = new ArrayList<>();
        arrays.add(new TextKeyValueBean(MainContactInterface.APPLY_NO, !isDetail ? new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) : dataBean.getApplyNumber()));
        arrays.add(new TextKeyValueBean(MainContactInterface.APPLY_USER, !isDetail ? UserInfoManager.getUserName() : dataBean.getApplyUsername()));
        arrays.add(new TextKeyValueBean(MainContactInterface.APPLY_USER_MOBILE, !isDetail ? UserInfoManager.getMobile() : dataBean.getApplyPhone()));
        arrays.add(new TextKeyValueBean(MainContactInterface.APPLY_USER_UNIT, !isDetail ? UserInfoManager.getDepartmentName() : dataBean.getApplyDepartmentName()));
        arrays.add(new TextKeyValueBean(MainContactInterface.APPLY_USER_UNIT_ADDR, !isDetail ? UserInfoManager.getDepartmentAddr() : dataBean.getApplyDepartmentAddress()));
        arrays.add(new TextKeyValueBean(MainContactInterface.APPLY_TIME, !isDetail ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) : dataBean.getApplyTime()));
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
        arrays.add(new TextKeyValueBean(MainContactInterface.APPLY_NO, !isDetail ? new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) : dataBean.getApplyNumber()));
        arrays.add(new TextKeyValueBean(MainContactInterface.APPLY_USER, dataBean == null ? UserInfoManager.getUserName() : dataBean.getApplyUsername()));
        arrays.add(new TextKeyValueBean(MainContactInterface.APPLY_USER_MOBILE, dataBean == null ? UserInfoManager.getMobile() : dataBean.getApplyPhone()));
        arrays.add(new TextKeyValueBean(MainContactInterface.APPLY_USER_UNIT, dataBean == null ? UserInfoManager.getDepartmentName() : dataBean.getApplyDepartmentName()));
        arrays.add(new TextKeyValueBean(MainContactInterface.APPLY_USER_UNIT_ADDR, dataBean == null ? UserInfoManager.getDepartmentAddr() : dataBean.getApplyDepartmentAddress()));
        arrays.add(new TextKeyValueBean(MainContactInterface.APPLY_TIME, !isDetail ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) : dataBean.getApplyTime()));
        if (isDetail) {
            arrays.add(new TextKeyValueBean(MainContactInterface.PLAN_USE_START_TIME, dataBean.getEstimateStartUseTime()));
            arrays.add(new TextKeyValueBean(MainContactInterface.PLAN_USE_END_TIME, dataBean.getEstimateEndUseTime()));
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
                              boolean isImportant, int editHeightType) {
        switch (layoutType) {
            case MultipleItem.ITEM_SELECT:
                arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_SMALL, new ImportantTagBean
                        (typeName, isImportant)));
                arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT,
                        new TextKeyValueBean(typeName, value, String.format("%s%s", "请选择",
                                typeName), 0, isImportant)));
                break;
            case MultipleItem.ITEM_EDIT:
                arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_SMALL, new ImportantTagBean(typeName,
                        isImportant)));
                arrays.add(new MultipleItem(MultipleItem.ITEM_EDIT,
                        new TextKeyValueBean(typeName, value,
                                String.format("%s%s", "请输入", typeName), editHeightType, isImportant)));

                break;
            case MultipleItem.ITEM_EDIT2:
                arrays.add(new MultipleItem(MultipleItem.ITEM_EDIT2,
                        new TextKeyValueBean(typeName, value,
                                String.format("%s%s", "请输入", typeName), editHeightType, isImportant)));
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
