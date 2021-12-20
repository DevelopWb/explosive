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
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.utils.ActionConfig;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.bdmap.service.LocateAndUpload;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.explorsive.MyApp;
import com.juntai.wisdom.explorsive.base.BaseAppActivity;
import com.juntai.wisdom.explorsive.bean.MyMenuBean;
import com.juntai.wisdom.explorsive.entrance.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseAppActivity {
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
        mUnitNameTv = (TextView) findViewById(R.id.unit_name_tv);
        mUserNameTv = (TextView) findViewById(R.id.user_name_tv);
        mUserMobileTv = (TextView) findViewById(R.id.user_mobile_tv);
        mHomePageRv = (RecyclerView) findViewById(R.id.home_page_rv);
        menuAdapter = new MainMenuAdapter(R.layout.my_menu_item);
        GridLayoutManager manager = new GridLayoutManager(mContext, 2);
        mHomePageRv.setAdapter(menuAdapter);
        mHomePageRv.setLayoutManager(manager);
        menuAdapter.setNewData(getMenus());
    }

    private List<MyMenuBean> getMenus() {
        List<MyMenuBean>  arrays = new ArrayList<>();
        arrays.add(new MyMenuBean(MyMenuBean.HOME_MENU_ITEM1,R.mipmap.app_icon));
        arrays.add(new MyMenuBean(MyMenuBean.HOME_MENU_ITEM2,R.mipmap.app_icon));
        arrays.add(new MyMenuBean(MyMenuBean.HOME_MENU_ITEM3,R.mipmap.app_icon));
        return arrays;
    }

    @Override
    public void initData() {
        update(false);
    }

    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            default:
                break;
        }
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
    protected BasePresenter createPresenter() {
        return null;
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
