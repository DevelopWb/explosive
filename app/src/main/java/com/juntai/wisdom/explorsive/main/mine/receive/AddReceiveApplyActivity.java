package com.juntai.wisdom.explorsive.main.mine.receive;

import android.content.DialogInterface;

import com.juntai.disabled.basecomponent.utils.DialogUtil;
import com.juntai.disabled.basecomponent.utils.GsonTools;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.explorsive.AppHttpPath;
import com.juntai.wisdom.explorsive.bean.BaseAdapterDataBean;
import com.juntai.wisdom.explorsive.bean.ReceiveOrderDetailBean;
import com.juntai.wisdom.explorsive.main.BaseCommitFootViewActivity;
import com.juntai.wisdom.explorsive.utils.HawkProperty;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;
import com.orhanobut.hawk.Hawk;

import okhttp3.RequestBody;

/**
 * @aouther tobato
 * @description 描述   新增民爆申请
 * @date 2021-12-22 15:10
 */
public class AddReceiveApplyActivity extends BaseCommitFootViewActivity {

    @Override
    public void initData() {
        adapter.setNewData(mPresenter.getRecieveApplyData(null, false));
        ReceiveOrderDetailBean.DataBean savedBean = Hawk.get(HawkProperty.EXPLOSIVE_RECEIVE_APPLY);
        if (savedBean != null) {
            setAlertDialogHeightWidth(DialogUtil.getDialog(mContext).setMessage("您上次还有未提交的草稿,是否进入草稿？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapter.setNewData(mPresenter.getRecieveApplyData(savedBean, false));
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
        return "提交申请";
    }

    @Override
    protected void commitRequest(BaseAdapterDataBean baseAdapterDataBean) {
        ReceiveOrderDetailBean.DataBean  receiveBean = baseAdapterDataBean.getReceiveOrderBean();
        receiveBean.setApplyUserId(UserInfoManager.getUserId());
        receiveBean.setApplyDepartmentId(UserInfoManager.getDepartmentId());
        receiveBean.setMobile(UserInfoManager.getMobile());
        receiveBean.setToken(UserInfoManager.getUserToken());
        String route= GsonTools.createGsonString(receiveBean);//通过Gson将Bean转化为Json字符串形式
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
        mPresenter.addExplosiveApply(body, AppHttpPath.ADD_RECEIVE_EXPLOSIVE_APPLY);

    }

    @Override
    protected void saveDraft() {
        BaseAdapterDataBean dataBean = getBaseAdapterData(true);
        if (dataBean!= null) {
            Hawk.put(HawkProperty.EXPLOSIVE_RECEIVE_APPLY, dataBean.getReceiveOrderBean());
            ToastUtils.toast(mContext, "草稿保存成功");
            finish();
        }
    }

    @Override
    protected String getTitleName() {
        return "民爆物品领取申请表";
    }


    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        switch (tag) {
            case AppHttpPath.ADD_RECEIVE_EXPLOSIVE_APPLY:
                ToastUtils.toast(mContext,"申请成功");
                setResult(BASE_REQUEST_RESULT);
                finish();
                break;
            default:
                break;
        }
    }
}
