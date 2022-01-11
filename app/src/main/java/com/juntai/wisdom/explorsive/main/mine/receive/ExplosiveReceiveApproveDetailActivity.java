package com.juntai.wisdom.explorsive.main.mine.receive;

import com.juntai.wisdom.explorsive.AppHttpPath;
import com.juntai.wisdom.explorsive.bean.BaseAdapterDataBean;
import com.juntai.wisdom.explorsive.bean.ReceiveOrderDetailBean;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;

/**
 * @aouther tobato
 * @description 描述  民爆领取审核详情
 * @date 2021-12-26 16:20
 */
public class ExplosiveReceiveApproveDetailActivity extends BaseReceiveDetailActivity {

    @Override
    protected String getTitleName() {
        return "民爆领取申请表";
    }




    @Override
    protected void initAdapterData(ReceiveOrderDetailBean.DataBean dataBean) {
        // 1派出所审核；2治安大队审核；3局领导审核；4出库；5配送；6完成；7作废
        //             账户性质   1矿场；2派出所；3治安大队；4县公安局；5民爆仓库
        adapter.setNewData(mPresenter.getRecieveApplyApproveData(dataBean,true));

    }


}
