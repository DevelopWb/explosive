package com.juntai.wisdom.explorsive.main.mine.use;

import android.content.DialogInterface;

import com.juntai.disabled.basecomponent.utils.DialogUtil;
import com.juntai.disabled.basecomponent.utils.GsonTools;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.explorsive.AppHttpPath;
import com.juntai.wisdom.explorsive.bean.BaseAdapterDataBean;
import com.juntai.wisdom.explorsive.bean.UseOrderDetailBean;
import com.juntai.wisdom.explorsive.main.BaseCommitFootViewActivity;
import com.juntai.wisdom.explorsive.main.MainContactInterface;
import com.juntai.wisdom.explorsive.utils.HawkProperty;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;
import com.orhanobut.hawk.Hawk;

import okhttp3.RequestBody;

/**
 * @aouther tobato
 * @description 描述  矿内使用申请
 * @date 2021-12-23 17:09
 */
public class AddUseApplyActivity extends BaseCommitFootViewActivity {

    @Override
    public void initData() {
        adapter.setNewData(mPresenter.getUseApplyAddData(null,false,true));
        UseOrderDetailBean.DataBean savedBean = Hawk.get(HawkProperty.SAVE_EXPLOSIVE_USE_APPLY);
        if (savedBean != null) {
            setAlertDialogHeightWidth(DialogUtil.getDialog(mContext).setMessage("您上次还有未提交的草稿,是否进入草稿？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapter.setNewData(mPresenter.getUseApplyAddData(savedBean,false,true));
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
        LOCATE_KEY = MainContactInterface.USE_LOCATION;
        return true;
    }

    @Override
    protected String getCommitTextValue() {
        return "提交";
    }

    @Override
    protected void commitRequest(BaseAdapterDataBean baseAdapterDataBean) {

        UseOrderDetailBean.DataBean  useBean = baseAdapterDataBean.getUseOrderBean();
        useBean.setApplyUserId(UserInfoManager.getUserId());
        useBean.setApplyDepartmentId(UserInfoManager.getDepartmentId());
        useBean.setMobile(UserInfoManager.getMobile());
        useBean.setApplyIdNumber(UserInfoManager.getIDCard());
        useBean.setToken(UserInfoManager.getUserToken());
        String route= GsonTools.createGsonString(useBean);//通过Gson将Bean转化为Json字符串形式
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
        mPresenter.addExplosiveUseApply(body, AppHttpPath.ADD_USE_EXPLOSIVE_APPLY);

    }

    @Override
    protected void saveDraft() {
        BaseAdapterDataBean dataBean = getBaseAdapterData(true);
        if (dataBean!= null) {
            Hawk.put(HawkProperty.SAVE_EXPLOSIVE_USE_APPLY, dataBean.getUseOrderBean());
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
                Hawk.delete(HawkProperty.SAVE_EXPLOSIVE_USE_APPLY);
                ToastUtils.toast(mContext,"申请成功");
                setResult(BASE_REQUEST_RESULT);
                finish();
                break;
            default:
                break;
        }
    }
}