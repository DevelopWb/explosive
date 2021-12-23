package com.juntai.wisdom.explorsive.main;


import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.AppNetModule;
import com.juntai.wisdom.explorsive.base.BaseAppPresent;
import com.juntai.wisdom.explorsive.base.TextKeyValueAdapter;
import com.juntai.wisdom.explorsive.bean.BaseNormalRecyclerviewBean;
import com.juntai.wisdom.explorsive.bean.ExplosiveTypeBean;
import com.juntai.wisdom.explorsive.bean.ImportantTagBean;
import com.juntai.wisdom.explorsive.bean.LocationBean;
import com.juntai.wisdom.explorsive.bean.MultipleItem;
import com.juntai.wisdom.explorsive.bean.MyMenuBean;
import com.juntai.wisdom.explorsive.bean.ReceiveOrderDetailBean;
import com.juntai.wisdom.explorsive.bean.ReceiveOrderListBean;
import com.juntai.wisdom.explorsive.bean.TextKeyValueBean;
import com.juntai.wisdom.explorsive.bean.UserBean;
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
                case 6:
                case 7:
                    menus.add(new MyMenuBean(APPROVE_RECEIVE, R.mipmap.ic_launcher));
                    menus.add(new MyMenuBean(APPROVE_USE, R.mipmap.ic_launcher));
                    break;
                case 8:
                case 9:
                    menus.add(new MyMenuBean(EXPLOSIVE_MANAGE_OFFICE, R.mipmap.ic_launcher));
                    break;
                default:
                    break;
            }
        }
        menus.add(new MyMenuBean(DOSAGE, R.mipmap.ic_launcher));

        return menus;
    }


    /**
     * 添加  民爆领取申请
     *
     * @return
     */
    public List<MultipleItem> getAddUseApplyData(ReceiveOrderDetailBean.DataBean bean,boolean isDetail) {
        List<MultipleItem> arrays = new ArrayList<>();

        arrays.add(new MultipleItem(MultipleItem.ITEM_NORMAL_RECYCLEVIEW, new BaseNormalRecyclerviewBean(
                MultipleItem.BASE_RECYCLERVIEW_TYPE_TEXT_VALUE,
                getApplyerData(bean,isDetail), new TextKeyValueAdapter(R.layout.text_key_value_item))));
        arrays.add(new MultipleItem(MultipleItem.ITEM_LOCATION, new LocationBean(MainContactInterface.USE_LOCATION, bean == null ? null :
                bean.getUseAddress()
                , bean == null ? null : bean.getUseLatitude(), bean == null ? null : bean.getUseLongitude())));
        initTextType(arrays, MultipleItem.ITEM_EDIT, MainContactInterface.APPLICATION, bean == null ? "" :
                bean.getRemarks(), false, 1);
        arrays.add(new MultipleItem(MultipleItem.ITEM_APPLY_DOSAGE, bean == null ? getExplosiveDosage() : bean.getExplosiveUsage()));
        return arrays;
    }
    /**
     * 添加  民爆领取申请
     *
     * @return
     */
    public List<MultipleItem> getAddRecieveApplyData(ReceiveOrderDetailBean.DataBean bean,boolean isDetail) {
        List<MultipleItem> arrays = new ArrayList<>();

        arrays.add(new MultipleItem(MultipleItem.ITEM_NORMAL_RECYCLEVIEW, new BaseNormalRecyclerviewBean(
                MultipleItem.BASE_RECYCLERVIEW_TYPE_TEXT_VALUE,
                getApplyerData(bean,isDetail), new TextKeyValueAdapter(R.layout.text_key_value_item))));
        arrays.add(new MultipleItem(MultipleItem.ITEM_LOCATION, new LocationBean(MainContactInterface.USE_LOCATION, bean == null ? null :
                bean.getUseAddress()
                , bean == null ? null : bean.getUseLatitude(), bean == null ? null : bean.getUseLongitude())));
        initTextType(arrays, MultipleItem.ITEM_EDIT, MainContactInterface.APPLICATION, bean == null ? "" :
                bean.getRemarks(), false, 1);
        arrays.add(new MultipleItem(MultipleItem.ITEM_APPLY_DOSAGE, bean == null ? getExplosiveDosage() : bean.getExplosiveUsage()));
        return arrays;
    }

    /**
     * 获取默认的数据
     *
     * @return
     */
    private List<ReceiveOrderDetailBean.DataBean.ExplosiveUsageBean> getExplosiveDosage() {
        List<ReceiveOrderDetailBean.DataBean.ExplosiveUsageBean> arrays = new ArrayList<>();
        arrays.add(new ReceiveOrderDetailBean.DataBean.ExplosiveUsageBean("请选择爆炸物种类", 0, "零", "个"));
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
        arrays.add(new TextKeyValueBean("申请编号:", !isDetail ? new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) : dataBean.getApplyNumber()));
        arrays.add(new TextKeyValueBean("申请人:", !isDetail ? UserInfoManager.getUserName() : dataBean.getApplyUsername()));
        arrays.add(new TextKeyValueBean("联系电话:", !isDetail ? UserInfoManager.getMobile() : dataBean.getApplyPhone()));
        arrays.add(new TextKeyValueBean("申请单位:", !isDetail ? UserInfoManager.getDepartmentName() : dataBean.getApplyDepartmentName()));
        arrays.add(new TextKeyValueBean("单位地址:", !isDetail ? UserInfoManager.getDepartmentAddr() : dataBean.getApplyDepartmentAddress()));
        arrays.add(new TextKeyValueBean("申请时间:", !isDetail ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) : dataBean.getApplyTime()));
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


}
