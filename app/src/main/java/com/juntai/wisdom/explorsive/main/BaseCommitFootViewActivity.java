package com.juntai.wisdom.explorsive.main;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.bean.BaseAdapterDataBean;
import com.juntai.wisdom.explorsive.bean.LocationBean;
import com.juntai.wisdom.explorsive.bean.MultipleItem;

import java.util.List;

import okhttp3.MultipartBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/5/13 10:29
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/13 10:29
 */
public abstract class BaseCommitFootViewActivity extends BaseExplosiveActivity {

    private TextView mCommitBusinessTv;
    public String commitName;
    protected TextView mSaveDraft;

    public void setCommitName(String commitName) {
        this.commitName = commitName == null ? "" : commitName;
        if (mCommitBusinessTv != null) {
            mCommitBusinessTv.setText(commitName);
        }

    }



    @Override
    public void initView() {
        super.initView();
        adapter.setFooterView(getFootView());
    }





    @Override
    protected View getFootView() {
        View view = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.footview_save_commit, null);
        mCommitBusinessTv = view.findViewById(R.id.commit_form_tv);
        mCommitBusinessTv.setText(getCommitTextValue());
        commitName = getCommitTextValue();
        mCommitBusinessTv.setOnClickListener(this);
        mSaveDraft = view.findViewById(R.id.save_draft_tv);
        mSaveDraft.setOnClickListener(this);
        return view;
    }

    protected abstract String getCommitTextValue();

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.save_draft_tv:
                //保存草稿
                saveDraft();


                break;
            case R.id.commit_form_tv:
                BaseAdapterDataBean baseAdapterDataBean = getBaseAdapterData(false);
                if (baseAdapterDataBean == null) {
                    return;
                }
                commitRequest(baseAdapterDataBean);


                break;
            default:
                break;
        }
    }


    /**
     * 提交请求
     *
     */
    protected abstract void commitRequest(BaseAdapterDataBean baseAdapterDataBean);

    protected abstract void saveDraft();


}
