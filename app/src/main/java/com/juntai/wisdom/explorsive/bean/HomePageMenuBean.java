package com.juntai.wisdom.explorsive.bean;

import android.text.TextUtils;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/18 15:33
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/18 15:33
 */
public class HomePageMenuBean {
    public static final String  MODIFY_MOBILE = "联系电话修改";
    public static final String  MODIFY_PWD= "密码修改";
    public static final String  UPDATE = "检查更新";
    public static final String  ABOUT_US = "关于我们";


    private  String menuName;
    private  String menuEnName;
    private int menuBgId;
    private int menuIconId;

    public HomePageMenuBean(String menuName, String menuEnName, int menuBgId, int menuIconId) {
        this.menuName = menuName;
        this.menuEnName = menuEnName;
        this.menuBgId = menuBgId;
        this.menuIconId = menuIconId;
    }

    public int getMenuIconId() {
        return menuIconId;
    }

    public void setMenuIconId(int menuIconId) {
        this.menuIconId = menuIconId;
    }

    public String getMenuEnName() {
        return menuEnName == null ? "" : menuEnName;
    }

    public void setMenuEnName(String menuEnName) {
        this.menuEnName = menuEnName == null ? "" : menuEnName;
    }

    public String getMenuName() {
        return TextUtils.isEmpty(menuName) ? "暂无" : menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? "" : menuName;
    }

    public int getMenuBgId() {
        return menuBgId;
    }

    public void setMenuBgId(int menuBgId) {
        this.menuBgId = menuBgId;
    }
}
