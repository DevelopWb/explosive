package com.juntai.wisdom.explorsive.main.mine.use;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.explorsive.AppHttpPath;
import com.juntai.wisdom.explorsive.bean.BaseAdapterDataBean;
import com.juntai.wisdom.explorsive.bean.ReceiveOrderDetailBean;
import com.juntai.wisdom.explorsive.bean.UseOrderDetailBean;
import com.juntai.wisdom.explorsive.main.BaseExplosiveActivity;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;

/**
 * @aouther tobato
 * @description 描述  民爆使用详情
 * @date 2021-12-27 11:24
 */
public class ExplosiveUseDetailActivity extends BaseExplosiveActivity {

    @Override
    protected String getTitleName() {
        return "民爆使用申请表";
    }


    @Override
    public void initView() {
        super.initView();
        mPresenter.getExplosiveUseDetail(getBaseBuilder().add("id", String.valueOf(baseId)).build(), AppHttpPath.USE_EXPLOSIVE_DETAIL);
        adapter.setDetail(true);
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
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        switch (tag) {
            case AppHttpPath.USE_EXPLOSIVE_DETAIL:
                UseOrderDetailBean orderDetailBean = (UseOrderDetailBean) o;
                if (orderDetailBean != null) {
                    UseOrderDetailBean.DataBean dataBean = orderDetailBean.getData();
                    if (dataBean != null) {
                        initAdapterData(dataBean,dataBean.getStat()+1);
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
    private void initAdapterData(UseOrderDetailBean.DataBean dataBean, int i) {
        if (i == UserInfoManager.getDepartmentType()) {
            adapter.setDetail(false);
            adapter.setCheck(true);
            adapter.setNewData(mPresenter.getUseApplyData(dataBean,true));
            if (2!=dataBean.getIsVoid()) {
                //没有作废
                adapter.addFooterView(getFootView());
            }

        } else {
            adapter.setDetail(true);
            adapter.setCheck(false);
            adapter.setNewData(mPresenter.getUseApplyData(dataBean, true));

        }
    }
}
