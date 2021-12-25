package com.juntai.wisdom.explorsive.main.mine.use;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.juntai.disabled.basecomponent.utils.DialogUtil;
import com.juntai.disabled.basecomponent.utils.GsonTools;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.explorsive.AppHttpPath;
import com.juntai.wisdom.explorsive.bean.BaseAdapterDataBean;
import com.juntai.wisdom.explorsive.bean.UseOrderDetailBean;
import com.juntai.wisdom.explorsive.main.BaseCommitFootViewActivity;
import com.juntai.wisdom.explorsive.utils.HawkProperty;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;
import com.orhanobut.hawk.Hawk;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @aouther tobato
 * @description 描述  矿内使用申请
 * @date 2021-12-23 17:09
 */
public class AddUseApplyActivity extends BaseCommitFootViewActivity {

    @Override
    public void initData() {
        adapter.setNewData(mPresenter.getAddUseApplyData(null, false));
        UseOrderDetailBean.DataBean savedBean = Hawk.get(HawkProperty.EXPLOSIVE_USE_APPLY);
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
    protected void commitRequest(BaseAdapterDataBean baseAdapterDataBean) {

        UseOrderDetailBean.DataBean  receiveBean = baseAdapterDataBean.getUseOrderBean();
        receiveBean.setApplyUserId(UserInfoManager.getUserId());
        receiveBean.setApplyDepartmentId(UserInfoManager.getDepartmentId());
        receiveBean.setMobile(UserInfoManager.getMobile());
        receiveBean.setToken(UserInfoManager.getUserToken());
        String route= GsonTools.createGsonString(receiveBean);//通过Gson将Bean转化为Json字符串形式
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
        mPresenter.addExplosiveUseApply(body, AppHttpPath.ADD_USE_EXPLOSIVE_APPLY);

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
    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        switch (tag) {
            case AppHttpPath.ADD_USE_EXPLOSIVE_APPLY:
                ToastUtils.toast(mContext,"申请成功");
                setResult(BASE_REQUEST_RESULT);
                finish();
                break;
            default:
                break;
        }
    }
}