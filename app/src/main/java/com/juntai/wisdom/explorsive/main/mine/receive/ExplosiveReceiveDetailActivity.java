package com.juntai.wisdom.explorsive.main.mine.receive;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.explorsive.AppHttpPath;
import com.juntai.wisdom.explorsive.bean.BaseAdapterDataBean;
import com.juntai.wisdom.explorsive.bean.ReceiveOrderDetailBean;
import com.juntai.wisdom.explorsive.main.BaseExplosiveActivity;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  民爆领取详情
 * @date 2021-12-26 16:20
 */
public class ExplosiveReceiveDetailActivity extends BaseExplosiveActivity {

    @Override
    protected String getTitleName() {
        return "民爆领取申请表";
    }


    @Override
    public void initView() {
        super.initView();
        mPresenter.getExplosiveReceiveDetail(getBaseBuilder().add("id", String.valueOf(baseId)).build(), AppHttpPath.RECEIVE_EXPLOSIVE_DETAIL);

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
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        switch (tag) {
            case AppHttpPath.RECEIVE_EXPLOSIVE_DETAIL:
                ReceiveOrderDetailBean orderDetailBean = (ReceiveOrderDetailBean) o;
                if (orderDetailBean != null) {
                    ReceiveOrderDetailBean.DataBean dataBean = orderDetailBean.getData();
                    if (dataBean != null) {
                        int orderStatus = dataBean.getStat();
//
//             账户性质   1矿场；2派出所；3治安大队；4县公安局；5民爆仓库
                        // TODO: 2021-12-27 这个地方的逻辑回头还需要再确认
                        switch (orderStatus) {
                            // 1派出所审核；2治安大队审核；3局领导审核；4出库；5配送；6完成；7作废
                            case 1:
                            case 2:
                            case 3:
                                initAdapterData(dataBean, orderStatus+1);
                                break;
                            default:
                                initAdapterData(dataBean, orderStatus+1);
                                break;
                        }


                    }
                }
                break;
            case AppHttpPath.POLICE_APPROVE:
                ToastUtils.toast(mContext, "提交成功");
                finish();
                break;
            default:
                break;
        }


    }

    private void initAdapterData(ReceiveOrderDetailBean.DataBean dataBean, int i) {
        if (i == UserInfoManager.getDepartmentType()) {
            adapter.setDetail(false);
            adapter.setCheck(true);
            adapter.setNewData(mPresenter.getRecieveApplyCheckData(dataBean));
            if (2!=dataBean.getIsVoid()) {
                //没有作废
                adapter.addFooterView(getFootView());
            }
        } else {
            adapter.setDetail(true);
            adapter.setCheck(false);
            adapter.setNewData(mPresenter.getRecieveApplyData(dataBean, true));

        }
    }
}
