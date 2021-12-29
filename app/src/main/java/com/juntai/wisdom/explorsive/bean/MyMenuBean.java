package com.juntai.wisdom.explorsive.bean;

import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 * Describe:个人中心菜单数据
 * 2020/3/7
 * email:954101549@qq.com
 */
public class MyMenuBean  {

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
