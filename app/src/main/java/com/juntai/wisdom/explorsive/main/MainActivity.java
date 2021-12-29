package com.juntai.wisdom.explorsive.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.ActionConfig;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.bdmap.service.LocateAndUpload;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.MyApp;
import com.juntai.wisdom.explorsive.base.BaseAppActivity;
import com.juntai.wisdom.explorsive.bean.MyMenuBean;
import com.juntai.wisdom.explorsive.entrance.LoginActivity;
import com.juntai.wisdom.explorsive.faceCheck.FaceCheckActivity;
import com.juntai.wisdom.explorsive.main.explosiveManage.AllMinesActivity;
import com.juntai.wisdom.explorsive.main.explosiveManage.ExplosiveManageActivity;
import com.juntai.wisdom.explorsive.main.mine.receive.ApplyReceiveActivirty;
import com.juntai.wisdom.explorsive.main.mine.receive.ReceiveApproveActivity;
import com.juntai.wisdom.explorsive.main.mine.use.ApplyUseActivity;
import com.juntai.wisdom.explorsive.main.mine.use.UseApproveActivity;
import com.juntai.wisdom.explorsive.main.myCenter.MyCenterActivity;
import com.juntai.wisdom.explorsive.utils.UrlFormatUtil;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;

/**
 * @aouther tobato
 * @description 描述  首页
 * @date 2021-12-20 9:47
 */
public class MainActivity extends BaseAppActivity<MainPresent> implements MainContactInterface, View.OnClickListener {
    //
    CGBroadcastReceiver broadcastReceiver = new CGBroadcastReceiver();
    private ImageView mUserHeadIv;
    /**
     * unitName
     */
    private TextView mUnitNameTv;
    /**
     * userName
     */
    private TextView mUserNameTv;
    /**
     * 17568220000
     */
    private TextView mUserMobileTv;
    private RecyclerView mHomePageRv;
    private MainMenuAdapter menuAdapter;
    private TextView mUserWorkTv;


    @Override
    public int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        getToolbar().setVisibility(View.GONE);
        mBaseRootCol.setFitsSystemWindows(false);
        //注册广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ActionConfig.BROAD_LOGIN);
        intentFilter.addAction(ActionConfig.BROAD_CASE_DETAILS);
        registerReceiver(broadcastReceiver, intentFilter);
        mUserHeadIv = (ImageView) findViewById(R.id.user_head_iv);
        mUserHeadIv.setOnClickListener(this);
        mUnitNameTv = (TextView) findViewById(R.id.unit_name_tv);
        mUserNameTv = (TextView) findViewById(R.id.user_name_tv);
        mUserMobileTv = (TextView) findViewById(R.id.user_mobile_tv);
        mUserWorkTv = (TextView) findViewById(R.id.user_work_tv);
        initUserBaseInfo();
        mHomePageRv = (RecyclerView) findViewById(R.id.home_page_rv);
        menuAdapter = new MainMenuAdapter(R.layout.my_menu_item);
        GridLayoutManager manager = new GridLayoutManager(mContext, 2);
        mHomePageRv.setAdapter(menuAdapter);
        mHomePageRv.setLayoutManager(manager);
        menuAdapter.setNewData(mPresenter.getHomePageMenu());
        menuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyMenuBean menuBean = (MyMenuBean) adapter.getData().get(position);
                switch (menuBean.getName()) {
                    case MainPresent.MINE_REQUEST:
                        // : 2021-12-20  民爆领取申请
                        startActivity(new Intent(mContext, ApplyReceiveActivirty.class));
                        break;
                    case MainPresent.MINE_REQUEST_INSIDE:
                        // : 2021-12-20  矿内使用申请
                        startActivity(new Intent(mContext, ApplyUseActivity.class));

                        break;
                    case MainPresent.MINE_MANAGER:
                        // TODO: 2021-12-20  矿场管理发放
                        startActivity(new Intent(mContext, FaceCheckActivity.class));
                        break;
                    case MainPresent.APPROVE_RECEIVE:
                        // : 2021-12-20  民爆领取审批
                        startActivity(new Intent(mContext, ReceiveApproveActivity.class));
                        break;
                    case MainPresent.APPROVE_USE:
                        // : 2021-12-20  矿场使用审批
                        startActivity(new Intent(mContext, UseApproveActivity.class));
                        break;
                    case MainPresent.EXPLOSIVE_MANAGE_OFFICE:
                        // : 2021-12-20  民爆管理发放
                        startActivity(new Intent(mContext, ExplosiveManageActivity.class));
                        break;
                    case MainPresent.DOSAGE:
                        // TODO: 2021-12-20  用量
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 用户基本资料
     */
    private void initUserBaseInfo() {
        mUnitNameTv.setText(UserInfoManager.getDepartmentName());
        mUserNameTv.setText("姓名：" + UserInfoManager.getUserName());
        mUserMobileTv.setText("电话：" + UserInfoManager.getMobile());
        mUserWorkTv.setText("职务：" + UserInfoManager.geWorkName());
        ImageLoadUtil.loadCirImgWithCrash(mContext, UrlFormatUtil.getImageOriginalUrl(UserInfoManager.getHeadImage()), mUserHeadIv, R.mipmap.default_user_head_icon);
    }


    @Override
    public void initData() {
        update(false);
    }

    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
//            case AppHttpPath.GET_USER_INFO:
//
//                break;


            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_head_iv:
                //头像
                startActivityForResult(new Intent(mContext, MyCenterActivity.class), BASE_REQUEST_RESULT);

                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BASE_REQUEST_RESULT) {
            initUserBaseInfo();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 登录被顶
     */
    public class CGBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (ActionConfig.BROAD_LOGIN.equals(intent.getAction())) {
                //登录信息设置为空
                String error = intent.getStringExtra("error");
                ToastUtils.info(MyApp.app, error);
                //                SPTools.saveString(mContext, "login", "");
                startActivity(new Intent(mContext, LoginActivity.class));
                //重置界面
                //                EventManager.sendStringMsg(ActionConfig.UN_READ_MESSAG_TAG);
            }
        }
    }


    @Override
    public void onLocationReceived(BDLocation bdLocation) {

    }

    @Override
    public boolean requestLocation() {
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected MainPresent createPresenter() {
        return new MainPresent();
    }

    @Override
    protected void onDestroy() {
        Log.e("EEEEEEEEEE", " = MainActivity  onDestroy");
        stopService(new Intent(MainActivity.this, LocateAndUpload.class));
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(mContext)
                .setMessage("请选择退出方式")
                .setPositiveButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyApp.app.isFinish = true;
                        finish();
                    }
                })
                .setNegativeButton("挂起", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //模拟home键,发送广播
                        //sendBroadcast(new Intent().setAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
                        // .putExtra("reason","homekey"));
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        startActivity(intent);
                    }
                }).show();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


}
