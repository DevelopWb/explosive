package com.juntai.wisdom.explorsive.main.mine.use;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.explorsive.AppHttpPath;
import com.juntai.wisdom.explorsive.bean.BaseAdapterDataBean;
import com.juntai.wisdom.explorsive.bean.UseOrderDetailBean;
import com.juntai.wisdom.explorsive.main.BaseExplosiveActivity;
import com.juntai.wisdom.explorsive.utils.HawkProperty;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;
import com.orhanobut.hawk.Hawk;

import okhttp3.FormBody;

/**
 * @aouther tobato
 * @description 描述  民爆使用详情的基类
 * * @date 2021-12-27 11:24
 */
public abstract class BaseExplosiveUseDetailActivity extends BaseExplosiveActivity {



    @Override
    public void initView() {
        super.initView();
        FormBody.Builder builder = getBaseBuilder();
        builder .add("id", String.valueOf(baseId));
        if (baseId2>0) {
            builder .add("messageId", String.valueOf(baseId2));
        }
        mPresenter.getExplosiveUseDetail(builder.build(), AppHttpPath.USE_EXPLOSIVE_DETAIL);
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
                        initAdapterData(dataBean);
                    }
                }
                break;
            case AppHttpPath.POLICE_APPROVE:
                ToastUtils.toast(mContext, "提交成功");
                finish();
                break;
            case AppHttpPath.USE_IN_MINE:
                Hawk.delete(HawkProperty.SAVE_USE_ORDER_IN_MINE+baseId);
                ToastUtils.toast(mContext, "提交成功");
                finish();
                break;
            case AppHttpPath.OUT_IN_MINE:
                Hawk.delete(HawkProperty.SAVE_OUT_ORDER_IN_MINE+baseId);
                ToastUtils.toast(mContext, "提交成功");
                finish();
                break;
            default:
                break;
        }


    }

    protected abstract void initAdapterData(UseOrderDetailBean.DataBean dataBean);
}
