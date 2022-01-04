package com.juntai.wisdom.explorsive.main.mine.receive.manager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.juntai.wisdom.explorsive.bean.UseOrderDetailBean;
import com.juntai.wisdom.explorsive.main.mine.use.BaseExplosiveUseDetailActivity;
/**
 * @aouther tobato
 * @description 描述  出库详情
 * @date 2022-01-04 16:09
 */
public class ExplosiveOutInMineDetailActivity extends BaseExplosiveUseDetailActivity {


    @Override
    protected void initAdapterData(UseOrderDetailBean.DataBean dataBean) {
        adapter.setNewData(mPresenter.getUseApplyOutInMineData(dataBean,true));
    }

    @Override
    protected String getTitleName() {
        return "出库详情";
    }
}
