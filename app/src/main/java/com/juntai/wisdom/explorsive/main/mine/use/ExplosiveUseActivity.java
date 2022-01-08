package com.juntai.wisdom.explorsive.main.mine.use;

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
import com.juntai.wisdom.explorsive.bean.OutInMineRequest;
import com.juntai.wisdom.explorsive.bean.UseOrderDetailBean;
import com.juntai.wisdom.explorsive.utils.HawkProperty;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;
import com.orhanobut.hawk.Hawk;

import okhttp3.RequestBody;

/**
 * @aouther tobato
 * @description 描述  爆炸物使用
 * @date 2022-01-04 16:40
 */
public class ExplosiveUseActivity extends BaseExplosiveUseDetailActivity {

    @Override
    protected void initAdapterData(UseOrderDetailBean.DataBean dataBean) {
        adapter.setNewData(mPresenter.getApplyOfUseInMineData(dataBean,false));
        adapter.addFooterView(getFootView());
    }

    @Override
    protected String getTitleName() {
        return "爆炸物品矿内使用申请";
    }


    @Override
    public void initData() {
        super.initData();
        UseOrderDetailBean.DataBean savedBean = Hawk.get(HawkProperty.SAVE_USE_ORDER_IN_MINE+baseId);
        if (savedBean != null) {
            setAlertDialogHeightWidth(DialogUtil.getDialog(mContext).setMessage("您上次还有未提交的草稿,是否进入草稿？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapter.setNewData(mPresenter.getApplyOfUseInMineData(savedBean,false));
                        }
                    }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).show(), -1, 0);
        }

    }






    protected View getFootView() {
        View view = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.footview_save_commit, null);
        TextView mCommitBusinessTv = view.findViewById(R.id.commit_form_tv);
        mCommitBusinessTv.setText("提交");
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
                    UseOrderDetailBean.DataBean orderBean = adapterData.getUseOrderBean();
                    orderBean.setMobile(UserInfoManager.getMobile());
                    orderBean.setToken(UserInfoManager.getUserToken());
                    orderBean.setId(baseId);
                    RequestBody body = GsonTools.getJsonRequest(orderBean);
                    mPresenter.useInMine(body, AppHttpPath.USE_IN_MINE);
                }


                break;
            case R.id.save_draft_tv:
                // : 2021-12-29 保存草稿
                BaseAdapterDataBean baseAdapterData = getBaseAdapterData(true);
                Hawk.put(HawkProperty.SAVE_USE_ORDER_IN_MINE+baseId, baseAdapterData.getUseOrderBean());
                ToastUtils.toast(mContext, "草稿保存成功");
                finish();

                break;
            default:
                break;
        }
    }
}
