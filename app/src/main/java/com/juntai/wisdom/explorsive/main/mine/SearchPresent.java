package com.juntai.wisdom.explorsive.main.mine;


import android.content.Context;
import android.content.Intent;

import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.wisdom.explorsive.AppNetModule;
import com.juntai.wisdom.explorsive.bean.OrderListBean;
import com.juntai.wisdom.explorsive.main.BaseExplosiveActivity;
import com.juntai.wisdom.explorsive.main.MainActivity;
import com.juntai.wisdom.explorsive.main.mine.receive.ExplosiveReceiveApproveActivity;
import com.juntai.wisdom.explorsive.main.mine.receive.ExplosiveReceiveApproveDetailActivity;
import com.juntai.wisdom.explorsive.main.mine.receive.delivery.ExplosiveReceiveDeliveryDetailActivity;
import com.juntai.wisdom.explorsive.main.mine.receive.delivery.ExplosiveReceiveDeliveryActivity;
import com.juntai.wisdom.explorsive.main.mine.receive.manager.ExplosiveOutInMineActivity;
import com.juntai.wisdom.explorsive.main.mine.receive.manager.ExplosiveOutInMineDetailActivity;
import com.juntai.wisdom.explorsive.main.mine.receive.outHouse.ExplosiveReceiveOutDetailActivity;
import com.juntai.wisdom.explorsive.main.mine.receive.outHouse.ExplosiveReceiveOutActivity;
import com.juntai.wisdom.explorsive.main.mine.use.ExplosiveUseActivity;
import com.juntai.wisdom.explorsive.main.mine.use.ExplosiveUseApproveDetailActivity;
import com.juntai.wisdom.explorsive.main.mine.use.ExplosiveUseDetailActivity;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;

import okhttp3.RequestBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2020/7/9 15:01
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/9 15:01
 */
public class SearchPresent extends BasePresenter<IModel, IView> {


    @Override
    protected IModel createModel() {
        return null;
    }


    public void getReceiveOrderList(RequestBody requestBody, String tag) {
        AppNetModule
                .createrRetrofit()
                .getReceiveOrderList(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<OrderListBean>(getView()) {
                    @Override
                    public void onSuccess(OrderListBean o) {
                        getView().onSuccess(tag, o);
                    }

                    @Override
                    public void onError(String msg) {
                        getView().onError(tag, msg);
                    }
                });
    }

    public void getUseOrderList(RequestBody requestBody, String tag) {
        AppNetModule
                .createrRetrofit()
                .getUseOrderList(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<OrderListBean>(getView()) {
                    @Override
                    public void onSuccess(OrderListBean o) {
                        getView().onSuccess(tag, o);
                    }

                    @Override
                    public void onError(String msg) {
                        getView().onError(tag, msg);
                    }
                });
    }

    /**
     * 民爆申领订单列表的点击事件
     *
     * @param context
     */
    public void receiveApplyAdapterItemLogic(Context context, int stat,int applyId,int messageId) {
        //  订单详情  1派出所审核；2治安大队审核；3局领导审核；4出库；5配送；6完成；7作废
        // 部门类型（1矿场；2派出所；3治安大队；4县公安局；5民爆仓库）
        int departmentId = UserInfoManager.getDepartmentType();
        Intent intent = new Intent().putExtra(BaseExplosiveActivity.BASE_ID2, messageId)
                .putExtra(BaseExplosiveActivity.BASE_ID, applyId);
        switch (stat) {
            case 1:
            case 2:
            case 3:
                // : 2021-12-21 订单详情 派出所审核
                if (stat==departmentId-1) {
                    intent.setClass(context,ExplosiveReceiveApproveActivity.class);
                    context.startActivity(intent);
                }else {
                    intent.setClass(context,ExplosiveReceiveApproveDetailActivity.class);
                    context.startActivity(intent);
                }
                break;
            case 4:
                //  待出库状态
                if (5 == departmentId) {
                    //可操作
                    intent.setClass(context,ExplosiveReceiveOutActivity.class);
                    context.startActivity(intent);
                } else {
                    //只可查看   只能看到出库之前的状态
                    intent.setClass(context,ExplosiveReceiveApproveDetailActivity.class);
                    context.startActivity(intent);

                }
                break;
            case 5:
                //待配送环节
                if (5 == departmentId) {
                    //可操作
                    intent.setClass(context,ExplosiveReceiveDeliveryActivity.class);
                    context.startActivity(intent);
                } else {
                    intent.setClass(context,ExplosiveReceiveOutDetailActivity.class);
                    context.startActivity(intent);

                }
                break;
            case 6:
                intent.setClass(context,ExplosiveReceiveDeliveryDetailActivity.class);
                context.startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * 民爆使用订单列表的点击事件
     *
     * @param context
     */
    public void useApplyAdapterItemLogic(Context context, int stat,int applyId,int messageId) {
        //  订单详情  （1派出所审核；2仓库领用；3使用；4完成）
        // 部门类型（1矿场；2派出所；5民爆仓库）
        //权限
//        2 民爆申请人
//        3 矿场内爆炸物申请人
//        4 矿场内爆炸物仓库管理员
//        5 派出所审批人
//        6 治安大队审批人
//        7 县公安局审批人
//        8 民爆仓库管理员
//        9 民爆仓库配送员
        // : 202-12-21 订单详情 矿内使用派出所审核
        int departmentId = UserInfoManager.getDepartmentType();
        Intent intent = new Intent().putExtra(BaseExplosiveActivity.BASE_ID2, messageId)
                .putExtra(BaseExplosiveActivity.BASE_ID, applyId);
        switch (stat) {
            case 1:
                // : 2021-12-21 订单详情 矿内使用派出所审核
                intent.setClass(context,ExplosiveUseApproveDetailActivity.class);
                context.startActivity(intent);
                break;
            case 2:
                //仓库领用
                if (1 == departmentId && UserInfoManager.getWorkIds().contains(4) && MainActivity.isManager) {
                    intent.setClass(context,ExplosiveOutInMineActivity.class);
                    context.startActivity(intent);

                } else {
                    intent.setClass(context,ExplosiveUseApproveDetailActivity.class);
                    context.startActivity(intent);

                }
                break;
            case 3:
                //使用申请
                if (1 == departmentId && UserInfoManager.getWorkIds().contains(3) && MainActivity.isUseInMine) {
                    intent.setClass(context,ExplosiveUseActivity.class);
                    context.startActivity(intent);

                } else {
                    intent.setClass(context,ExplosiveOutInMineDetailActivity.class);
                    context.startActivity(intent);

                }
                break;
            case 4:
                intent.setClass(context,ExplosiveUseDetailActivity.class);
                context.startActivity(intent);

                break;
            default:
                break;
        }
    }
}
