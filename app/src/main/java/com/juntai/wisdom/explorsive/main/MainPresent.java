package com.juntai.wisdom.explorsive.main;


import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.AppNetModule;
import com.juntai.wisdom.explorsive.base.BaseAppPresent;
import com.juntai.wisdom.explorsive.bean.MyMenuBean;
import com.juntai.wisdom.explorsive.bean.OrderListBean;
import com.juntai.wisdom.explorsive.bean.UserBean;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;
import com.mob.wrappers.PaySDKWrapper;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;

/**
 * Describe:
 * Create by zhangzhenlong
 * 2020/3/7
 * email:954101549@qq.com
 */
public class MainPresent extends BaseAppPresent<IModel, IView> {
    //矿场的
    public static final String MINE_REQUEST = "民爆领取申请";
    public static final String MINE_REQUEST_INSIDE = "矿内使用申请";
    public static final String MINE_MANAGER = "矿场管理发放";
    //负责审批的  监管单位
    public static final String APPROVE_RECEIVE = "民爆领取审批";
    public static final String APPROVE_USE = "矿场使用审批";
    //民爆局  物品管理单位
    public static final String EXPLOSIVE_MANAGE_OFFICE = "民爆管理发放";


    public static final String DOSAGE = "用量";

    @Override
    protected IModel createModel() {
        return null;
    }

    /**
     * 获取首页菜单
     * 2 民爆申请人 1
     * 3 矿场内爆炸物申请人 1
     * 4 矿场内爆炸物仓库管理员 1
     * 5 派出所审批人 2
     * 6 治安大队审批人 3
     * 7 县公安局审批人 4
     * 8 民爆仓库管理员 5
     * 9 民爆仓库配送员 5
     *
     * @return
     */
    protected List<MyMenuBean> getHomePageMenu() {
        List<MyMenuBean> menus = new ArrayList<>();
// TODO: 2021-12-20  这个地方的资源文件回头需要补充
        List<UserBean.DataBean.PostBean> postBeanList = UserInfoManager.getPostBeans();
        for (UserBean.DataBean.PostBean postBean : postBeanList) {
            switch (postBean.getId()) {
                case 2:
                    menus.add(new MyMenuBean(MINE_REQUEST, R.mipmap.ic_launcher));
                    break;
                case 3:
                    menus.add(new MyMenuBean(MINE_REQUEST_INSIDE, R.mipmap.ic_launcher));
                    break;
                case 4:
                    menus.add(new MyMenuBean(MINE_MANAGER, R.mipmap.ic_launcher));
                    break;
                case 5:
                case 6:
                case 7:
                    menus.add(new MyMenuBean(APPROVE_RECEIVE, R.mipmap.ic_launcher));
                    menus.add(new MyMenuBean(APPROVE_USE, R.mipmap.ic_launcher));
                    break;
                case 8:
                case 9:
                    menus.add(new MyMenuBean(EXPLOSIVE_MANAGE_OFFICE, R.mipmap.ic_launcher));
                    break;
                default:
                    break;
            }
        }
        menus.add(new MyMenuBean(DOSAGE, R.mipmap.ic_launcher));

        return menus;
    }





}
