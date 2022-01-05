package com.juntai.wisdom.explorsive.main.mine.use;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.explorsive.AppHttpPath;
import com.juntai.wisdom.explorsive.bean.BaseAdapterDataBean;
import com.juntai.wisdom.explorsive.bean.UseOrderDetailBean;
import com.juntai.wisdom.explorsive.main.BaseExplosiveActivity;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;

/**
 * @aouther tobato
 * @description 描述  民爆使用详情
 * @date 2021-12-27 11:24
 */
public class ExplosiveUseApproveDetailActivity extends BaseExplosiveUseDetailActivity {

    @Override
    protected String getTitleName() {
        return "民爆使用申请表";
    }


    @Override
    protected void commitLogic(BaseAdapterDataBean baseAdapterDataBean) {
        //部门类型（1矿场；2派出所；3治安大队；4县公安局；5民爆仓库）
        UseOrderDetailBean.DataBean orderDetailBean = baseAdapterDataBean.getUseOrderBean();
        switch (UserInfoManager.getDepartmentType()) {
            case 2:
                mPresenter.policeApproveOfMine(getBaseBuilder().add("username", UserInfoManager.getUserName())
                        .add("id", String.valueOf(baseId))
                        .add("policeDepartmentId", String.valueOf(UserInfoManager.getDepartmentId()))
                        .add("policeSign", orderDetailBean.getPoliceSign())
                        .add("policeDepartmentSeal", orderDetailBean.getPoliceDepartmentSeal())
                        .add("isVoid", String.valueOf(orderDetailBean.getPoliceVoid()))
                        .add("reason", orderDetailBean.getPoliceRemarks())
                        .build(), AppHttpPath.POLICE_APPROVE
                );
                break;
            default:
                break;
        }

    }


    @Override
    protected void initAdapterData(UseOrderDetailBean.DataBean dataBean) {
        // 1派出所审核；2治安大队审核；3局领导审核；4出库；5配送；6完成；7作废
        //             账户性质   1矿场；2派出所；3治安大队；4县公安局；5民爆仓库
        int orderStatus = dataBean.getStat() + 1;
        if (orderStatus == UserInfoManager.getDepartmentType()) {
            adapter.setNewData(mPresenter.getUseApplyData(dataBean,true,true));
            if (2!=dataBean.getIsVoid()) {
                //没有作废
                adapter.addFooterView(getFootView());
            }
        } else {
            adapter.setNewData(mPresenter.getUseApplyData(dataBean, true,true));

        }
    }

}
