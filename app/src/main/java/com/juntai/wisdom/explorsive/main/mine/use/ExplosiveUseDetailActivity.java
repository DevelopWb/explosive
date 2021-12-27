package com.juntai.wisdom.explorsive.main.mine.use;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.juntai.wisdom.explorsive.AppHttpPath;
import com.juntai.wisdom.explorsive.bean.ReceiveOrderDetailBean;
import com.juntai.wisdom.explorsive.main.BaseExplosiveActivity;
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
        mPresenter.getExplosiveReceiveDetail(getBaseBuilder().add("id", String.valueOf(baseId)).build(), AppHttpPath.RECEIVE_EXPLOSIVE_DETAIL);
        adapter.setDetail(true);
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        ReceiveOrderDetailBean orderDetailBean = (ReceiveOrderDetailBean) o;
        if (orderDetailBean != null) {
            ReceiveOrderDetailBean.DataBean dataBean = orderDetailBean.getData();
            if (dataBean != null) {
                adapter.setNewData(mPresenter.getAddRecieveApplyData(dataBean, true));
            }
        }

    }
}
