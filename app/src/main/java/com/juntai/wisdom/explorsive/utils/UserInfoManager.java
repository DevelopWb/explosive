package com.juntai.wisdom.explorsive.utils;

import com.juntai.disabled.basecomponent.utils.AppUtils;
import com.juntai.wisdom.explorsive.bean.UserBean;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  用户信息管理类
 * @CreateDate: 2020/12/19 14:04
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/12/19 14:04
 */
public class UserInfoManager {


    /**
     * 获取用户信息
     *
     * @return
     */
    public static UserBean getUser() {
        return Hawk.get(AppUtils.SP_KEY_USER);
    }
    public static List<UserBean.DataBean.PostBean> getPostBeans() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getPost() : new ArrayList<>();
    }

    /**
     * 判定用户是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        if (getUser() == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取usertoken
     *
     * @return
     */
    public static String getUserToken() {
        return Hawk.get(AppUtils.SP_KEY_TOKEN);
    }

    /**
     * getMobile
     *
     * @return
     */
    public static String getMobile() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getMobile() : "";

    }

    /**
     * getDepartmentName
     *
     * @return
     */
    public static String getDepartmentName() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getDepartmentName() : "";

    }
    /**
     * getDepartmentName
     *
     * @return
     */
    public static String getDepartmentAddr() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getDepartmentAddress() : "";

    }
    public static String getDepartmentSign() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getSealUrl() : "";

    }

    /**
     * @return
     */
    public static String getUserName() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getUsername() : "";

    }
    /**
     * @return
     */
    public static String getHeadImage() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getHeadPortrait() : "";

    }

    /**
     * @return
     */
    public static String geWorkName() {
        if (getUser() != null && getUser().getData() != null) {
            StringBuilder sb = new StringBuilder();
            List<UserBean.DataBean.PostBean> postBeanList = getUser().getData().getPost();
            if (postBeanList != null && !postBeanList.isEmpty()) {
                for (int i = 0; i < postBeanList.size(); i++) {
                    UserBean.DataBean.PostBean postBean = postBeanList.get(i);
                    if (i==0) {
                        sb.append(postBean.getName() + "\n");
                    }else {
                        if (i==postBeanList.size()-1) {
                            sb.append("\u3000\u3000\u3000"+postBean.getName());
                        }else {
                            sb.append("\u3000\u3000\u3000"+postBean.getName()+"\n");
                        }
                    }
                }
                return sb.toString();
            }
        }
        return "";
    }


    public static  List<Integer>  getWorkIds(){
        List<Integer> arrays = new ArrayList<>();
        if (getUser() != null && getUser().getData() != null) {
            List<UserBean.DataBean.PostBean> postBeanList = getUser().getData().getPost();
            if (postBeanList != null && !postBeanList.isEmpty()) {
                for (int i = 0; i < postBeanList.size(); i++) {
                    UserBean.DataBean.PostBean postBean = postBeanList.get(i);
                    arrays.add(postBean.getId());
                }
            }
        }
        return arrays;
    }
    /**
     * 获取getUserId
     *
     * @return
     */
    public static int getUserId() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getUserId() : -1;
    }
    /**
     * 身份证号
     *
     * @return
     */
    public static String getIDCard() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getIdNumber() : "";
    }
    public static int getDepartmentId() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getDepartmentId() : -1;
    }

    /**
     * private Integer departmentType;//部门类型（1矿场；2派出所；3治安大队；4县公安局；5民爆仓库）
     * @return
     */
    public static int getDepartmentType() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getDepartmentType() : -1;
    }


}
