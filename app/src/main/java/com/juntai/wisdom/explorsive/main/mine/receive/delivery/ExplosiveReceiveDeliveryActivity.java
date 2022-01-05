package com.juntai.wisdom.explorsive.main.mine.receive.delivery;

import com.juntai.wisdom.explorsive.AppHttpPath;
import com.juntai.wisdom.explorsive.bean.BaseAdapterDataBean;
import com.juntai.wisdom.explorsive.bean.ReceiveOrderDetailBean;
import com.juntai.wisdom.explorsive.main.mine.receive.BaseReceiveDetailActivity;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;

/**
 * @aouther tobato
 * @description 描述   配送操作
 * @date 2021-12-29 15:11
 */
public class ExplosiveReceiveDeliveryActivity extends BaseReceiveDetailActivity {

    @Override
    protected void initAdapterData(ReceiveOrderDetailBean.DataBean dataBean) {
        // : 2021-12-29 配送相关的逻辑  这个地方需要改
        adapter.setNewData(mPresenter.getRecieveApplyDeliveryData(dataBean,false));
        adapter.addFooterView(getFootView());
    }


    @Override
    protected void commitLogic(BaseAdapterDataBean baseAdapterDataBean) {
        ReceiveOrderDetailBean.DataBean orderDetailBean = baseAdapterDataBean.getReceiveOrderBean();
        mPresenter.delivery(baseAdapterDataBean.getBuilder()
                .addFormDataPart("id", String.valueOf(baseId))
                .addFormDataPart("arriveAddress",orderDetailBean.getArriveAddress())
                .addFormDataPart("arriveLongitude",orderDetailBean.getArriveLongitude())
                .addFormDataPart("arriveLatitude",orderDetailBean.getArriveLatitude())
                .addFormDataPart("arriveSign",orderDetailBean.getArriveSign())
                .build(), AppHttpPath.DELIVERY
        );
    }

    @Override
    protected String getTitleName() {
        return "配送单";
    }
}
