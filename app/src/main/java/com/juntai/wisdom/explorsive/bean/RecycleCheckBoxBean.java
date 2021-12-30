package com.juntai.wisdom.explorsive.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/1/28 10:57
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/1/28 10:57
 */
public class RecycleCheckBoxBean {

    private List<ItemCheckBoxBean> data;//recycleview的数据
    private String titleKey;
    private boolean isSigleSelect;//是否是单选
    public RecycleCheckBoxBean(List<ItemCheckBoxBean> data, String titleKey,
                               boolean isSigleSelect) {
        this.data = data;
        this.titleKey = titleKey;
        this.isSigleSelect = isSigleSelect;
    }


    public boolean isSigleSelect() {
        return isSigleSelect;
    }

    public void setSigleSelect(boolean sigleSelect) {
        isSigleSelect = sigleSelect;
    }

    public String getTitleKey() {
        return titleKey == null ? "" : titleKey;
    }

    public void setTitleKey(String titleKey) {
        this.titleKey = titleKey == null ? "" : titleKey;
    }


    public List<ItemCheckBoxBean> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<ItemCheckBoxBean> data) {
        this.data = data;
    }
}
