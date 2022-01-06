package com.juntai.wisdom.explorsive.main.myCenter;

import android.os.Bundle;
import android.widget.TextView;

import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.AppHttpPath;
import com.juntai.wisdom.explorsive.base.BaseAppActivity;
import com.juntai.wisdom.explorsive.bean.AboutUsBean;
import com.juntai.wisdom.explorsive.main.MainContactInterface;
import com.juntai.wisdom.explorsive.main.MainPresent;

import java.util.List;

import okhttp3.FormBody;

/**
 * @aouther tobato
 * @description 描述  关于我们
 * @date 2022-01-06 8:56
 */
public class AboutUsActivity extends BaseAppActivity<MainPresent> implements MainContactInterface {
    public static String TITLE_NAME = "titlename";
    /**
     * 此系统为刚察县公安局民用爆炸物管理系统
     */
    private TextView mUnitNameTv;
    /**
     * 姓名：userName
     */
    private TextView mUserNameTv;
    /**
     * 电话：17568220000
     */
    private TextView mUserMobileTv;
    /**
     * 职务：userName
     */
    private TextView mUserTelTv;

    @Override
    protected MainPresent createPresenter() {
        return new MainPresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_about_us;
    }

    @Override
    public void initView() {
        if (getIntent() != null) {
            String title = getIntent().getStringExtra(TITLE_NAME);
            setTitleName(title);
        }
        mUnitNameTv = (TextView) findViewById(R.id.unit_name_tv);
        mUserNameTv = (TextView) findViewById(R.id.user_name_tv);
        mUserMobileTv = (TextView) findViewById(R.id.user_mobile_tv);
        mUserTelTv = (TextView) findViewById(R.id.user_tel_tv);
    }

    @Override
    public void initData() {
        mPresenter.aboutUs(new FormBody.Builder().build(), AppHttpPath.ABOUT_US);
    }


    @Override
    public void onSuccess(String tag, Object o) {
        AboutUsBean aboutUsBean = (AboutUsBean) o;
        if (aboutUsBean != null) {
            List<AboutUsBean.DataBean> dataBeans = aboutUsBean.getData();
            if (dataBeans != null) {
                for (AboutUsBean.DataBean dataBean : dataBeans) {
                    if (2 == dataBean.getId()) {
                        mUserNameTv.setText("联系人："+dataBean.getValue());
                    }
                    if (3 == dataBean.getId()) {
                        mUserMobileTv.setText("联系电话："+dataBean.getValue());
                    }
                    if (4 == dataBean.getId()) {
                        mUserTelTv.setText("座机："+dataBean.getValue());
                    }
                }
            }

        }
    }

}
