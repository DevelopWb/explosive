package com.juntai.wisdom.explorsive;


import android.app.Activity;
import android.text.TextUtils;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.juntai.disabled.basecomponent.app.BaseApplication;
import com.juntai.disabled.basecomponent.utils.FileCacheUtils;
import com.juntai.disabled.video.ModuleVideo_Init;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;
import com.mob.MobSDK;
import com.orhanobut.hawk.Hawk;
import com.siyee.oscvpush.PushConstants;
import com.siyee.oscvpush.base.PushAdapter;
import com.siyee.oscvpush.huawei.HWPushRegister;
import com.siyee.oscvpush.mi.MiPushRegister;
import com.siyee.oscvpush.model.Message;
import com.siyee.oscvpush.model.Token;
import com.siyee.oscvpush.oppo.OppoPushRegister;
import com.siyee.oscvpush.util.RomUtil;
import com.siyee.oscvpush.vivo.VivoPushRegister;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @aouther Ma
 * @date 2019/3/12
 */
public class MyApp extends BaseApplication {
    public static MyApp app;
    public boolean isFinish = false;
    public LatLng myLocation;
    public BDLocation bdLocation;
    public static long lastClickTime;//上次点击按钮时间
    public static int timeLimit = 1000;
    public static String pushRegId = "";


    /**
     * 推送注册回调
     */
    private PushAdapter adapter = new PushAdapter() {

        @Override
        public void onRegister(int resCode, Token regId) {
            if (resCode == PushConstants.SUCCESS_CODE && regId != null) {
                pushRegId = regId.getRegId();
            }
        }

        /**
         * APP不在线的时候 收到推送的消息
         * @param msg
         */
        @Override
        public void onMessage(Message msg) {
        }

        @Override
        public void onMessageClicked(Message msg) {
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Hawk.init(this).build();
        MobSDK.init(this);
        //Video模块初始化
        ModuleVideo_Init.init();
        //百度地图初始化
        SDKInitializer.initialize(this);
        //        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);

        //创建压缩图片存放目录
        FileCacheUtils.creatFile(FileCacheUtils.getAppImagePath());
//        initBugly();
        initPushRegist();
    }

    public void initPushRegist() {
        if (!UserInfoManager.isLogin()) {
            if (RomUtil.isEmui()) {
                //华为
                HWPushRegister.getInstance(this).register();
            } else if (RomUtil.isVivo()) {
                VivoPushRegister.getInstance(this).register();
            } else if (RomUtil.isOppo()) {
                OppoPushRegister.getInstance(this).register("a8aaa44a557b420f921aa4079ec1774b", "34eecd930b2849edbc5162305fee687e");
            } else {
                //小米
                MiPushRegister.getInstance(this).register("2882303761520089591", "5432008920591");
            }
        }
        if (RomUtil.isEmui()) {
            //华为
            HWPushRegister.getInstance(this).setPushCallback(adapter);
        } else if (RomUtil.isVivo()) {
            VivoPushRegister.getInstance(this).setPushCallback(adapter);
        } else if (RomUtil.isOppo()) {
            OppoPushRegister.getInstance(this).setPushCallback(adapter);
        } else {
            //小米
            MiPushRegister.getInstance(this).setPushCallback(adapter);
        }
    }

    /**
     * 获取当前定位
     *
     * @return
     */
    public LatLng getMyLocation() {
        if (myLocation == null) {
            myLocation = new LatLng(0, 0);
        }
        return myLocation;
    }

    public void setMyLocation(LatLng myLocation) {
        this.myLocation = myLocation;
    }


    public BDLocation getBdLocation() {
        return bdLocation;
    }

    public void setBdLocation(BDLocation bdLocation) {
        this.bdLocation = bdLocation;
    }

    @Override
    public void appBackground(boolean isBackground, Activity activity) {
        if (isBackground && !isFinish) {
            //            NitoficationTool.sendNotif(activity,
            //                    11,
            //                    "挂起",
            //                    BaseAppUtils.getAppName() + "服务正在运行",
            //                    R.mipmap.app_icon,
            //                    true,
            //                    new Intent(activity, MainActivity.class));
        } else {
            //变为前台
            //            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            //            manager.cancelAll();
        }
    }


    /**
     * 防止暴力点击
     */
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < timeLimit) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

}
