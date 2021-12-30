package com.juntai.wisdom.explorsive.main.mine.receive;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.bean.ReceiveOrderDetailBean;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;

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
        if (5 == UserInfoManager.getDepartmentType()) {
            adapter.setCanSelect(true);
            adapter.setCanAddIssue(true);
            adapter.setNewData(mPresenter.getRecieveApplyOutData(dataBean));
            if (2 != dataBean.getIsVoid()) {
                //没有作废
                adapter.addFooterView(getFootView());
            }
        } else {
            adapter.setCanSelect(false);
            adapter.setCanAddIssue(false);
            adapter.setNewData(mPresenter.getRecieveApplyData(dataBean, true));

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
                // TODO: 2021-12-29 提交出库
                break;
            case R.id.save_draft_tv:
                // TODO: 2021-12-29 保存草稿
                break;
            default:
                break;
        }
    }
}
