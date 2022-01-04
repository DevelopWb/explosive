package com.juntai.wisdom.explorsive.main.mine.receive.manager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.juntai.wisdom.explorsive.main.MainActivity;
import com.juntai.wisdom.explorsive.main.mine.use.BaseApplyUseActivity;

/**
 * @aouther tobato
 * @description 描述  矿内管理发放
 * @date 2022-01-03 15:07
 */
public class ExplosiveManageOfMineActivity extends BaseApplyUseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.isManager = true;
    }

    @Override
    protected String getTitleName() {
        return "矿场爆炸物仓库管理";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MainActivity.isManager = false;
    }
}
