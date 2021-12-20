package com.juntai.wisdom.explorsive.bean;

import java.io.Serializable;

/**
 * Describe:个人中心菜单数据
 * 2020/3/7
 * email:954101549@qq.com
 */
public class MyMenuBean implements Serializable {

    public static final String HOME_MENU_ITEM1="民爆领取申请";
    public static final String HOME_MENU_ITEM2="民爆领取申请2";
    public static final String HOME_MENU_ITEM3="民爆领取申请3";
    public static final String HOME_MENU_ITEM4="民爆领取申请4";
    public static final String HOME_MENU_ITEM5="民爆领取申请5";
    private String name;
    private int imageId;

    public MyMenuBean(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
