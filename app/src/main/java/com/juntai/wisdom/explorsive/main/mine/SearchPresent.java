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
import com.juntai.wisdom.explorsive.main.mine.receive.ExplosiveReceiveApproveDetailActivity;
import com.juntai.wisdom.explorsive.main.mine.receive.ExplosiveReceiveDeliveryDetailActivity;
import com.juntai.wisdom.explorsive.main.mine.receive.ExplosiveReceiveOutDetailActivity;
import com.juntai.wisdom.explorsive.main.mine.use.ExplosiveUseApproveDetailActivity;

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
     * @param dataBean
     */
    public void receiveApplyAdapterItemLogic(Context context, OrderListBean.DataBean dataBean) {
        //  订单详情  1派出所审核；2治安大队审核；3局领导审核；4出库；5配送；6完成；7作废

        switch (dataBean.getStat()) {
            case 1:
            case 2:
            case 3:
                // : 2021-12-21 订单详情 派出所审核
                context.startActivity(new Intent(context, ExplosiveReceiveApproveDetailActivity.class).putExtra(BaseExplosiveActivity.BASE_ID, dataBean.getId()));
                break;
            case 4:
                //出库
                context.startActivity(new Intent(context, ExplosiveReceiveOutDetailActivity.class).putExtra(BaseExplosiveActivity.BASE_ID, dataBean.getId()));
                break;
            case 5:
                context.startActivity(new Intent(context, ExplosiveReceiveDeliveryDetailActivity.class).putExtra(BaseExplosiveActivity.BASE_ID, dataBean.getId()));
                break;
            default:
                break;
        }
    }

    /**
     * 民爆使用订单列表的点击事件
     *
     * @param context
     * @param dataBean
     */
    public void useApplyAdapterItemLogic(Context context, OrderListBean.DataBean dataBean) {
        //  订单详情  （1派出所审核；2仓库领用；3使用；4完成）
        // : 2021-12-21 订单详情 矿内使用派出所审核
        switch (dataBean.getStat()) {
            case 1:
                // : 2021-12-21 订单详情 矿内使用派出所审核
                context.startActivity(new Intent(context, ExplosiveUseApproveDetailActivity.class).putExtra(BaseExplosiveActivity.BASE_ID, dataBean.getId()));
                break;
            default:
                break;
        }
    }
}
