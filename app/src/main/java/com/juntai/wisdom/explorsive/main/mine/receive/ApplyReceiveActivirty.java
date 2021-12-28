package com.juntai.wisdom.explorsive.main.mine.receive;


import android.content.Intent;
import android.view.View;

/**
 * @aouther tobato
 * @description 描述  民爆领取(向民爆局)申请
 * @date 2021-12-20 14:15
 */
public class ApplyReceiveActivirty extends BaseApplyReceiveActivirty {
    @Override
    protected String getTitleName() {
        return "民爆领取申请";
    }

    @Override
    public void initView() {
        super.initView();
        getTitleRightTv().setText("新增");
        getTitleRightTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // : 2021-12-20 新增 民爆申请
                startActivityForResult(new Intent(mContext,AddReceiveApplyActivity.class),BASE_REQUEST_RESULT);
            }
        });
    }
}
