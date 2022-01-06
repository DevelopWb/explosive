package com.juntai.wisdom.explorsive.main.mine.receive.outHouse;

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
import com.juntai.wisdom.explorsive.main.mine.receive.BaseReceiveDetailActivity;
import com.juntai.wisdom.explorsive.utils.HawkProperty;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;
import com.orhanobut.hawk.Hawk;

import okhttp3.RequestBody;

/**
 * @aouther tobato
 * @description 描述  出库
 * @date 2021-12-29 14:49
 */
public class ExplosiveReceiveOutActivity extends BaseReceiveDetailActivity {


    @Override
    public void initData() {
        super.initData();
        ReceiveOrderDetailBean.DataBean savedBean = Hawk.get(HawkProperty.SAVE_OUT_ORDER+baseId);
        if (savedBean != null) {
            setAlertDialogHeightWidth(DialogUtil.getDialog(mContext).setMessage("您上次还有未提交的草稿,是否进入草稿？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapter.setNewData(mPresenter.getRecieveApplyOutData(savedBean,false));
                        }
                    }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).show(), -1, 0);
        }

    }

    @Override
    protected String getTitleName() {
        return "民爆出库单";
    }

    @Override
    protected void initAdapterData(ReceiveOrderDetailBean.DataBean dataBean) {
        Hawk.put(HawkProperty.CURRENT_SELECTED_EXPLOSIVE_TYPES, dataBean.getExplosiveUsage());
        adapter.setNewData(mPresenter.getRecieveApplyOutData(dataBean,false));
        if (2 != dataBean.getIsVoid()) {
            //没有作废
            adapter.addFooterView(getFootView());
        }
    }

    protected View getFootView() {
        View view = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.footview_save_commit, null);
        TextView mCommitBusinessTv = view.findViewById(R.id.commit_form_tv);
        mCommitBusinessTv.setText("出库提交");
        mCommitBusinessTv.setOnClickListener(this);
        TextView mSaveDraft = view.findViewById(R.id.save_draft_tv);
        mSaveDraft.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.commit_form_tv:
                // : 2021-12-29 提交出库
                BaseAdapterDataBean adapterData = getBaseAdapterData(false);
                if (adapterData != null) {
                    ReceiveOrderDetailBean.DataBean receiveBean = adapterData.getReceiveOrderBean();
                    OutHouseBean outHouseBean = new OutHouseBean();
                    outHouseBean.setDeliveryUser(receiveBean.getDeliveryUser());
                    outHouseBean.setExplosiveUsageNumber(receiveBean.getExplosiveUsageNumber());
                    outHouseBean.setMobile(UserInfoManager.getMobile());
                    outHouseBean.setToken(UserInfoManager.getUserToken());
                    outHouseBean.setId(baseId);
                    RequestBody body = GsonTools.getJsonRequest(outHouseBean);
                    mPresenter.outHouse(body, AppHttpPath.ADD_RECEIVE_EXPLOSIVE_APPLY);
                }


                break;
            case R.id.save_draft_tv:
                // : 2021-12-29 保存草稿
                BaseAdapterDataBean baseAdapterData = getBaseAdapterData(true);
                Hawk.put(HawkProperty.SAVE_OUT_ORDER+baseId, baseAdapterData.getReceiveOrderBean());
                ToastUtils.toast(mContext, "草稿保存成功");
                finish();

                break;
            default:
                break;
        }
    }
}
