package com.juntai.wisdom.explorsive.main.mine;


import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.wisdom.explorsive.AppNetModule;
import com.juntai.wisdom.explorsive.bean.ReceiveOrderListBean;

import okhttp3.RequestBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2020/7/9 15:01
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/9 15:01
 */
public class SearchPresent extends BasePresenter<IModel, IView>  {



    @Override
    protected IModel createModel() {
        return null;
    }




    public void getReceiveOrderList(RequestBody requestBody, String tag) {
        AppNetModule
                .createrRetrofit()
                .getReceiveOrderList(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<ReceiveOrderListBean>(getView()) {
                    @Override
                    public void onSuccess(ReceiveOrderListBean o) {
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
                .subscribe(new BaseObserver<ReceiveOrderListBean>(getView()) {
                    @Override
                    public void onSuccess(ReceiveOrderListBean o) {
                        getView().onSuccess(tag, o);
                    }

                    @Override
                    public void onError(String msg) {
                        getView().onError(tag, msg);
                    }
                });
    }
}
