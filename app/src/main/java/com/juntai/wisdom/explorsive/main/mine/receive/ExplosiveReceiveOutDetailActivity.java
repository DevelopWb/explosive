package com.juntai.wisdom.explorsive.main.mine.receive;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.utils.DialogUtil;
import com.juntai.disabled.basecomponent.utils.GsonTools;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.AppHttpPath;
import com.juntai.wisdom.explorsive.bean.BaseAdapterDataBean;
import com.juntai.wisdom.explorsive.bean.OutHouseBean;
import com.juntai.wisdom.explorsive.bean.ReceiveOrderDetailBean;
import com.juntai.wisdom.explorsive.utils.HawkProperty;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;
import com.orhanobut.hawk.Hawk;

import okhttp3.RequestBody;

/**
 * @aouther tobato
 * @description 描述  出库单的详情
 * @date 2021-12-29 14:49
 */
public class ExplosiveReceiveOutDetailActivity extends BaseReceiveDetailActivity {


    @Override
    protected String getTitleName() {
        return "民爆出库单";
    }

    @Override
    protected void initAdapterData(ReceiveOrderDetailBean.DataBean dataBean) {
        adapter.setCanSelect(false);
        adapter.setCanAddIssue(false);
        adapter.setNewData(mPresenter.getRecieveApplyOutData(dataBean));

    }
}
