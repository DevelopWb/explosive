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
                                if (2 == UserInfoManager.getDepartmentType()) {
                                    //派出所账户
                                    adapter.setDetail(false);
                                    adapter.setCheck(true);
                                    adapter.setNewData(mPresenter.getRecieveApplyCheckData(dataBean));
                                    adapter.addFooterView(getFootView());
                                } else {
                                    adapter.setDetail(true);
                                    adapter.setCheck(false);
                                    adapter.setNewData(mPresenter.getRecieveApplyData(dataBean, true));

                                }
                                break;
                            case 2:
                                if (3 == UserInfoManager.getDepartmentType()) {
                                    //治安大队账户
                                    adapter.setDetail(false);
                                    adapter.setCheck(true);
                                    adapter.setNewData(mPresenter.getRecieveApplyCheckData(dataBean));
                                    adapter.addFooterView(getFootView());
                                } else {
                                    adapter.setDetail(true);
                                    adapter.setCheck(false);
                                    adapter.setNewData(mPresenter.getRecieveApplyData(dataBean, true));

                                }

                                break;
                            default:
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
}
