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
    protected void commitLogic(BaseAdapterDataBean baseAdapterDataBean) {
        //部门类型（1矿场；2派出所；3治安大队；4县公安局；5民爆仓库）
        ReceiveOrderDetailBean.DataBean orderDetailBean = baseAdapterDataBean.getReceiveOrderBean();
        switch (UserInfoManager.getDepartmentType()) {
            case 2:
                mPresenter.policeApprove(getBaseBuilder().add("username", UserInfoManager.getUserName())
                        .add("id", String.valueOf(baseId))
                        .add("policeDepartmentId", String.valueOf(UserInfoManager.getDepartmentId()))
                        .add("policeSign", orderDetailBean.getPoliceSign())
                        .add("policeDepartmentSeal", orderDetailBean.getPoliceDepartmentSeal())
                        .add("isVoid", String.valueOf(orderDetailBean.getPoliceVoid()))
                        .add("reason", orderDetailBean.getPoliceRemarks())
                        .build(), AppHttpPath.POLICE_APPROVE
                );
                break;
            case 3:
                mPresenter.brigadeApprove(getBaseBuilder().add("username", UserInfoManager.getUserName())
                        .add("id", String.valueOf(baseId))
                        .add("brigadeDepartmentId", String.valueOf(UserInfoManager.getDepartmentId()))
                        .add("brigadeSign", orderDetailBean.getBrigadeSign())
                        .add("brigadeDepartmentSeal", orderDetailBean.getBrigadeDepartmentSeal())
                        .add("isVoid", String.valueOf(orderDetailBean.getBrigadeVoid()))
                        .add("reason", orderDetailBean.getBrigadeRemarks())
                        .build(), AppHttpPath.POLICE_APPROVE
                );
                break;
            case 4:
                mPresenter.leaderApprove(getBaseBuilder().add("username", UserInfoManager.getUserName())
                        .add("id", String.valueOf(baseId))
                        .add("leaderDepartmentId", String.valueOf(UserInfoManager.getDepartmentId()))
                        .add("leaderSign", orderDetailBean.getLeaderSign())
                        .add("leaderDepartmentSeal", orderDetailBean.getLeaderDepartmentSeal())
                        .add("isVoid", String.valueOf(orderDetailBean.getLeaderVoid()))
                        .add("reason", orderDetailBean.getLeaderRemarks())
                        .build(), AppHttpPath.POLICE_APPROVE
                );
                break;
            default:
                break;
        }

    }



    @Override
    protected void initAdapterData(ReceiveOrderDetailBean.DataBean dataBean) {
        // 1派出所审核；2治安大队审核；3局领导审核；4出库；5配送；6完成；7作废
        //             账户性质   1矿场；2派出所；3治安大队；4县公安局；5民爆仓库
        int orderStatus = dataBean.getStat() + 1;
        if (orderStatus == UserInfoManager.getDepartmentType()) {
            adapter.setNewData(mPresenter.getRecieveApplyCheckData(dataBean));
            if (2 != dataBean.getIsVoid()) {
                //没有作废
                adapter.addFooterView(getFootView());
            }
        } else {
            adapter.setNewData(mPresenter.getRecieveApplyData(dataBean, true));

        }
    }


}
