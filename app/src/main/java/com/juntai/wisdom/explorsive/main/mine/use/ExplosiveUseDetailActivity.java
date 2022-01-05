package com.juntai.wisdom.explorsive.main.mine.use;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.juntai.wisdom.explorsive.bean.UseOrderDetailBean;

/**
 * @aouther tobato
 * @description 描述   使用完成
 * @date 2022-01-05 17:02
 */
public class ExplosiveUseDetailActivity extends BaseExplosiveUseDetailActivity {

    @Override
    protected void initAdapterData(UseOrderDetailBean.DataBean dataBean) {
        adapter.setNewData(mPresenter.getApplyOfUseInMineData(dataBean,true));
    }

    @Override
    protected String getTitleName() {
        return "爆炸物品使用申请表";
    }
}
