package com.juntai.wisdom.explorsive.main.mine.receive.delivery;

import com.juntai.wisdom.explorsive.bean.ReceiveOrderDetailBean;
import com.juntai.wisdom.explorsive.main.mine.receive.BaseReceiveDetailActivity;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;

/**
 * @aouther tobato
 * @description 描述   配送单详情
 * @date 2021-12-29 15:11
 */
public class ExplosiveReceiveDeliveryDetailActivity extends BaseReceiveDetailActivity {

    @Override
    protected void initAdapterData(ReceiveOrderDetailBean.DataBean dataBean) {
        // TODO: 2021-12-29 配送相关的逻辑  这个地方需要改
        adapter.setNewData(mPresenter.getRecieveApplyDeliveryData(dataBean,true));
    }

    @Override
    protected String getTitleName() {
        return "申请单详情";
    }
}
