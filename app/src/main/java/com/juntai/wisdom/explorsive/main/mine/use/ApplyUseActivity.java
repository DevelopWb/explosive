package com.juntai.wisdom.explorsive.main.mine.use;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.base.BaseAppActivity;
import com.juntai.wisdom.explorsive.main.MainActivity;
import com.juntai.wisdom.explorsive.main.MainContactInterface;
import com.juntai.wisdom.explorsive.main.MainPresent;
import com.juntai.wisdom.explorsive.main.mine.receive.AddReceiveApplyActivity;
import com.juntai.wisdom.explorsive.main.mine.receive.ApplyReceiveFragment;

/**
 * @aouther tobato
 * @description 描述 矿内使用申请
 * @date 2021-12-20 17:03
 */
public class ApplyUseActivity extends BaseApplyUseActivity {


    @Override
    protected MainPresent createPresenter() {
        return new MainPresent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.isUseInMine = true;
    }

    @Override
    public void initView() {
        super.initView();
        getTitleRightTv().setText("新增");
        getTitleRightTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // : 2021-12-20 新增 矿内使用申请
                startActivityForResult(new Intent(mContext, AddUseApplyActivity.class),BASE_REQUEST_RESULT);

            }
        });
    }

    @Override
    protected String getTitleName() {
        return "矿场爆炸物使用申请";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MainActivity.isUseInMine = false;

    }
}
