package com.juntai.wisdom.explorsive.main.mine.use;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.juntai.disabled.basecomponent.utils.DialogUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.explorsive.bean.ReceiveOrderDetailBean;
import com.juntai.wisdom.explorsive.main.BaseCommitFootViewActivity;
import com.juntai.wisdom.explorsive.utils.HawkProperty;
import com.orhanobut.hawk.Hawk;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  矿内使用申请
 * @date 2021-12-23 17:09
 */
public class AddUseApplyActivity extends BaseCommitFootViewActivity {

    @Override
    public void initData() {
        adapter.setNewData(mPresenter.getAddUseApplyData(null, false));
        ReceiveOrderDetailBean.DataBean savedBean = Hawk.get(HawkProperty.EXPLOSIVE_USE_APPLY);
        if (savedBean != null) {
            setAlertDialogHeightWidth(DialogUtil.getDialog(mContext).setMessage("您上次还有未提交的草稿,是否进入草稿？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapter.setNewData(mPresenter.getAddUseApplyData(savedBean, false));
                        }
                    }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startLocation();
                        }
                    }).show(), -1, 0);
        }

    }

    @Override
    public boolean requestLocation() {
        return true;
    }

    @Override
    protected String getCommitTextValue() {
        return "矿场使用提交";
    }

    @Override
    protected void commitRequest(MultipartBody.Builder builder) {

    }

    @Override
    protected void saveDraft() {
        if (getBaseAdapterData(true) != null) {
            Hawk.put(HawkProperty.EXPLOSIVE_USE_APPLY, getBaseAdapterData(true).getReceiveOrderBean());
            ToastUtils.toast(mContext, "草稿保存成功");
            finish();
        }
    }

    @Override
    protected String getTitleName() {
        return "民爆物品使用申请表";
    }
}