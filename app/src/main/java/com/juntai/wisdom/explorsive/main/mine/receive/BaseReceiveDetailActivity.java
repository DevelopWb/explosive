package com.juntai.wisdom.explorsive.main.mine.receive;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.explorsive.AppHttpPath;
import com.juntai.wisdom.explorsive.bean.BaseAdapterDataBean;
import com.juntai.wisdom.explorsive.bean.ReceiveOrderDetailBean;
import com.juntai.wisdom.explorsive.main.BaseCommitFootViewActivity;
import com.juntai.wisdom.explorsive.main.BaseExplosiveActivity;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;

/**
 * @aouther tobato
 * @description 描述  民爆领取详情的基类
 * @date 2021-12-26 16:20
 */
public  abstract class BaseReceiveDetailActivity extends BaseExplosiveActivity {



    @Override
    public void initView() {
        super.initView();
        mPresenter.getExplosiveReceiveDetail(getBaseBuilder().add("id", String.valueOf(baseId)).build(), AppHttpPath.RECEIVE_EXPLOSIVE_DETAIL);

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
                        initAdapterData(dataBean);
                    }
                }
                break;
            case AppHttpPath.POLICE_APPROVE:
            case AppHttpPath.DELIVERY:
            case AppHttpPath.ADD_RECEIVE_EXPLOSIVE_APPLY:
                ToastUtils.toast(mContext, "提交成功");
                finish();
                break;
            default:
                break;
        }


    }

    protected abstract void initAdapterData(ReceiveOrderDetailBean.DataBean dataBean);

}
