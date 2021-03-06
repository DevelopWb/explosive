package com.juntai.wisdom.explorsive.main;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.utils.ActionConfig;
import com.juntai.disabled.basecomponent.utils.ActivityManagerTool;
import com.juntai.disabled.basecomponent.utils.AppUtils;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.AppHttpPath;
import com.juntai.wisdom.explorsive.MyApp;
import com.juntai.wisdom.explorsive.base.BaseAppActivity;
import com.juntai.wisdom.explorsive.bean.HomePageMenuBean;
import com.juntai.wisdom.explorsive.bean.UserBean;
import com.juntai.wisdom.explorsive.entrance.LoginActivity;
import com.juntai.wisdom.explorsive.main.explosiveManage.ExplosiveManageActivity;
import com.juntai.wisdom.explorsive.main.mine.dosage.AllDosageActivity;
import com.juntai.wisdom.explorsive.main.mine.dosage.AllMinesActivity;
import com.juntai.wisdom.explorsive.main.mine.receive.ApplyReceiveActivirty;
import com.juntai.wisdom.explorsive.main.mine.receive.ReceiveApproveActivity;
import com.juntai.wisdom.explorsive.main.mine.receive.manager.ExplosiveManageOfMineActivity;
import com.juntai.wisdom.explorsive.main.mine.use.ApplyUseActivity;
import com.juntai.wisdom.explorsive.main.mine.use.UseApproveActivity;
import com.juntai.wisdom.explorsive.main.myCenter.MyCenterActivity;
import com.juntai.wisdom.explorsive.main.myCenter.NewsActivity;
import com.juntai.wisdom.explorsive.utils.UrlFormatUtil;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;
import com.orhanobut.hawk.Hawk;

/**
 * @aouther tobato
 * @description ??????  ??????
 * @date 2021-12-20 9:47
 */
public class MainActivity extends BaseAppActivity<MainPresent> implements MainContactInterface, View.OnClickListener {
    //????????????
    public static boolean isManager = false;
    //????????????
    public static boolean isUseInMine = false;
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
    private HomeMenuAdapter menuAdapter;
    private TextView mUserWorkTv;


    @Override
    public int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        initToolbarAndStatusBar(false);
        mImmersionBar.reset().statusBarDarkFont(false).init();
        //????????????
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
        menuAdapter = new HomeMenuAdapter(R.layout.home_menu_item);
        LinearLayoutManager manager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        mHomePageRv.setAdapter(menuAdapter);
        mHomePageRv.setLayoutManager(manager);
        menuAdapter.setNewData(mPresenter.getHomePageMenu());
        menuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomePageMenuBean menuBean = (HomePageMenuBean) adapter.getData().get(position);
                switch (menuBean.getMenuName()) {
                    case MainPresent.MENU_NEWS:
                        // : 2021-12-20  ????????????
                        startActivity(new Intent(mContext, NewsActivity.class));
                        break;
                    case MainPresent.RECEIVE_APPLY_REQUEST:
                        // : 2021-12-20  ??????????????????
                        startActivity(new Intent(mContext, ApplyReceiveActivirty.class));
                        break;
                    case MainPresent.USE_APPLY_INSIDE:
                        // : 2021-12-20  ??????????????????
                        startActivity(new Intent(mContext, ApplyUseActivity.class));

                        break;
                    case MainPresent.MINE_MANAGER:
                        // : 2021-12-20  ??????????????????
                        startActivity(new Intent(mContext, ExplosiveManageOfMineActivity.class));
                        break;
                    case MainPresent.APPROVE_RECEIVE:
                        // : 2021-12-20  ??????????????????
                        startActivity(new Intent(mContext, ReceiveApproveActivity.class));
                        break;
                    case MainPresent.APPROVE_USE:
                        // : 2021-12-20  ??????????????????
                        startActivity(new Intent(mContext, UseApproveActivity.class));
                        break;
                    case MainPresent.EXPLOSIVE_MANAGE_OFFICE:
                        // : 2021-12-20  ??????????????????
                        startActivity(new Intent(mContext, ExplosiveManageActivity.class));
                        break;
                    case MainPresent.DOSAGE:
                        // : 2021-12-20  ??????
                        if (1 == UserInfoManager.getDepartmentType()) {
                            startActivity(new Intent(mContext, AllDosageActivity.class).putExtra(BASE_ID, UserInfoManager.getDepartmentId()));
                        } else {
                            startActivity(new Intent(mContext, AllMinesActivity.class));

                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * ??????????????????
     */
    private void initUserBaseInfo() {
        mUnitNameTv.setText(UserInfoManager.getDepartmentName());
        mUserNameTv.setText(UserInfoManager.getUserName());
        mUserMobileTv.setText(UserInfoManager.getMobile());
        mUserWorkTv.setText(UserInfoManager.geWorkName());
        ImageLoadUtil.loadCirImgWithCrash(mContext, UrlFormatUtil.getImageOriginalUrl(UserInfoManager.getHeadImage()), mUserHeadIv, R.mipmap.default_user_head_icon);
    }


    @Override
    public void initData() {
        update(false);
    }

    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case AppHttpPath.GET_UNREAD_COUNT:
                // : 2022-01-10 ??????????????????
                BaseResult result = (BaseResult) o;
                if (result != null) {
                    String count = result.getMessage();
                    // : 2022-01-07 ?????????
                    int countInt = Integer.parseInt(count);
                    HomePageMenuBean menuBean = (HomePageMenuBean) menuAdapter.getData().get(0);
                    menuBean.setUnreadCount(countInt);
                    menuAdapter.notifyItemChanged(0);
                }
                break;

            case AppHttpPath.GET_USER_INFO:
                UserBean loginBean = (UserBean) o;
                Hawk.put(AppUtils.SP_KEY_USER, loginBean);
                menuAdapter.setNewData(mPresenter.getHomePageMenu());
                initUserBaseInfo();
                mPresenter.getUnreadCount(getBaseBuilder().build(), AppHttpPath.GET_UNREAD_COUNT);
                break;


            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_head_iv:
                //??????
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
     * ????????????
     */
    public class CGBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (ActionConfig.BROAD_LOGIN.equals(intent.getAction())) {
                //????????????????????????
                ToastUtils.info(MyApp.app, "???????????????????????????");
                UserInfoManager.clearUserBaseInfo();
                ActivityManagerTool.getInstance().finishApp();
                startActivity(new Intent(mContext, LoginActivity.class));
            }
        }
    }


    @Override
    public void onLocationReceived(BDLocation bdLocation) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getUserInfo(getBaseBuilder().build(), AppHttpPath.GET_USER_INFO);
    }


    @Override
    protected MainPresent createPresenter() {
        return new MainPresent();
    }

    @Override
    protected void onDestroy() {
        Log.e("EEEEEEEEEE", " = MainActivity  onDestroy");
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(mContext)
                .setMessage("?????????????????????")
                .setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyApp.app.isFinish = true;
                        finish();
                    }
                })
                .setNegativeButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //??????home???,????????????
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
