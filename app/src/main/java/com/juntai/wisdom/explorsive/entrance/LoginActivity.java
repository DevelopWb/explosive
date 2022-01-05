package com.juntai.wisdom.explorsive.entrance;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.base.BaseMvpActivity;
import com.juntai.disabled.basecomponent.bean.UnionidBean;
import com.juntai.disabled.basecomponent.utils.ActionConfig;
import com.juntai.disabled.basecomponent.utils.EventManager;
import com.juntai.disabled.basecomponent.utils.GsonTools;
import com.juntai.disabled.basecomponent.utils.HttpUtil;
import com.juntai.disabled.basecomponent.utils.LogUtil;
import com.juntai.disabled.basecomponent.utils.MD5;
import com.juntai.disabled.basecomponent.utils.PubUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.MyApp;
import com.juntai.wisdom.explorsive.base.BaseAppActivity;
import com.juntai.wisdom.explorsive.base.sendcode.SendCodeModel;
import com.juntai.wisdom.explorsive.bean.UserBean;
import com.juntai.disabled.basecomponent.utils.AppUtils;
import com.juntai.wisdom.explorsive.main.MainActivity;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;
import com.orhanobut.hawk.Hawk;
import com.siyee.oscvpush.util.RomUtil;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * @aouther tobato
 * @description 描述  登录
 * @date 2020/3/6 9:12
 */
public class LoginActivity extends BaseAppActivity<EntrancePresent> implements EntranceContract.IEntranceView,
        View.OnClickListener {
    public String otherHeadIcon = "";
    /**
     * 登录
     */
    private TextView mLoginTv, mConnectUsTv;
    /**
     * 账号
     */
    private EditText mPhoneEt;
    /**
     * 密码
     */
    private EditText mPassword;
    String account, password;

    private ImageView mCloseIv;


    @Override
    public int getLayoutView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public void initData() {
        mLoginTv = (TextView) findViewById(R.id.login_tv);
        mConnectUsTv = (TextView) findViewById(R.id.connect_us_tv);
        mLoginTv.setOnClickListener(this);
        mConnectUsTv.setOnClickListener(this);
        mPhoneEt = (EditText) findViewById(R.id.regist_phone_et);
        mPassword = (EditText) findViewById(R.id.password);
        getToolbar().setVisibility(View.GONE);
    }


    @Override
    protected EntrancePresent createPresenter() {
        return new EntrancePresent();
    }

    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            //登录成功
            case EntranceContract.LOGIN_TAG:
                UserBean loginBean = (UserBean) o;
                ToastUtils.success(mContext, "登录成功");
                MyApp.isReLoadWarn = true;
                UserInfoManager.saveUserBaseInfo(loginBean);
                startActivity(new Intent(mContext, MainActivity.class));
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.login_tv:
                // : 2021-12-15 对接登录接口
                account = mPhoneEt.getText().toString();
                password = mPassword.getText().toString();
                if (account.isEmpty()) {
                    ToastUtils.error(mContext, "账号不可为空");
                    return;
                }
                if (!SendCodeModel.isMobileNO(account)) {
                    ToastUtils.error(mContext, "手机号码格式不正确");
                    return;
                }
                if (password.isEmpty()) {
                    ToastUtils.error(mContext, "登录密码不能为空");
                    return;
                }
                mPresenter.login(account, MD5.md5(String.format("%s#%s", account, password)), RomUtil.getName(),MyApp.pushRegId,
                        EntranceContract.LOGIN_TAG);
                break;
            case R.id.close_iv:
                finish();
                break;
            case R.id.connect_us_tv:
                startActivity(new Intent(this, ConnectUsActivity.class));
                break;


        }
    }


    @Override
    public void initView() {
        mLoginTv = (TextView) findViewById(R.id.login_tv);
        mLoginTv.setOnClickListener(this);
        mCloseIv = (ImageView) findViewById(R.id.close_iv);
        mCloseIv.setOnClickListener(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
