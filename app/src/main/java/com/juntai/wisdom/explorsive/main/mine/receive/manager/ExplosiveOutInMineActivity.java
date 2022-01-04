package com.juntai.wisdom.explorsive.main.mine.receive.manager;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.juntai.wisdom.explorsive.bean.OutInMineRequest;
import com.juntai.wisdom.explorsive.bean.UseOrderDetailBean;
import com.juntai.wisdom.explorsive.bean.UseOrderDetailBean;
import com.juntai.wisdom.explorsive.main.BaseCommitFootViewActivity;
import com.juntai.wisdom.explorsive.main.BaseExplosiveActivity;
import com.juntai.wisdom.explorsive.main.mine.receive.BaseReceiveDetailActivity;
import com.juntai.wisdom.explorsive.main.mine.use.BaseExplosiveUseDetailActivity;
import com.juntai.wisdom.explorsive.utils.HawkProperty;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;
import com.orhanobut.hawk.Hawk;

import okhttp3.RequestBody;

/**
 * @aouther tobato
 * @description 描述  爆炸物矿内出库
 * @date 2022-01-03 15:23
 */
public class ExplosiveOutInMineActivity extends BaseExplosiveUseDetailActivity {
    @Override
    public void initData() {
        super.initData();
        UseOrderDetailBean.DataBean savedBean = Hawk.get(HawkProperty.SAVE_OUT_ORDER_IN_MINE);
        if (savedBean != null) {
            setAlertDialogHeightWidth(DialogUtil.getDialog(mContext).setMessage("您上次还有未提交的草稿,是否进入草稿？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapter.setNewData(mPresenter.getUseApplyOutInMineData(savedBean,false));
                        }
                    }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).show(), -1, 0);
        }

    }

    @Override
    public boolean requestLocation() {
        return true;
    }

    @Override
    protected String getTitleName() {
        return "爆炸物品矿场出库";
    }


    @Override
    protected void initAdapterData(UseOrderDetailBean.DataBean dataBean) {
        Hawk.put(HawkProperty.CURRENT_SELECTED_EXPLOSIVE_TYPES, dataBean.getExplosiveUsage());
        adapter.setCanAddIssue(true);
        adapter.setNewData(mPresenter.getUseApplyOutInMineData(dataBean,false));
        adapter.addFooterView(getFootView());
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
                    OutInMineRequest outInMineRequest = adapterData.getOutInMineRequest();
                    outInMineRequest.setMobile(UserInfoManager.getMobile());
                    outInMineRequest.setToken(UserInfoManager.getUserToken());
                    outInMineRequest.setId(baseId);
                    RequestBody body = GsonTools.getJsonRequest(outInMineRequest);
                    mPresenter.outInMine(body, AppHttpPath.OUT_IN_MINE);
                }


                break;
            case R.id.save_draft_tv:
                // : 2021-12-29 保存草稿
                BaseAdapterDataBean baseAdapterData = getBaseAdapterData(true);
                Hawk.put(HawkProperty.SAVE_OUT_ORDER_IN_MINE, baseAdapterData.getUseOrderBean());
                ToastUtils.toast(mContext, "草稿保存成功");
                finish();

                break;
            default:
                break;
        }
    }
}


