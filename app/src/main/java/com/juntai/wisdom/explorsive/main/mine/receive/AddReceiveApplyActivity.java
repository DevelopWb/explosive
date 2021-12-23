package com.juntai.wisdom.explorsive.main.mine.receive;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.juntai.disabled.basecomponent.utils.DialogUtil;
import com.juntai.wisdom.explorsive.main.BaseCommitFootViewActivity;
import com.juntai.wisdom.explorsive.utils.HawkProperty;
import com.orhanobut.hawk.Hawk;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述   新增民爆申请
 * @date 2021-12-22 15:10
 */
public class AddReceiveApplyActivity extends BaseCommitFootViewActivity {

    @Override
    public void initData() {
        adapter.setNewData(mPresenter.getAddRecieveApplyData(null));
//        WorkerDetailBean.DataBean  savedWorkerBean = Hawk.get(HawkProperty.ADD_WORKER_KEY+unitId);
//        if (savedWorkerBean != null) {
//            setAlertDialogHeightWidth( DialogUtil.getDialog(mContext).setMessage("您上次还有未提交的草稿,是否进入草稿？")
//                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            adapter.setNewData(mPresenter.getWorkerData(savedWorkerBean,false));
//                        }
//                    }).setNegativeButton("否", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            startLocation();
//                        }
//                    }).show(),-1,0);
//        }

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
    protected void commitRequest(MultipartBody.Builder builder) {

    }

    @Override
    protected void saveDraft() {

    }

    @Override
    protected String getTitleName() {
        return "民爆物品领取申请表";
    }
}
