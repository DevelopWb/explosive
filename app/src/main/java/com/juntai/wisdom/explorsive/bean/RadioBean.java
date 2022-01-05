package com.juntai.wisdom.explorsive.bean;

/**
 * @Author: tobato
 * @Description: 作用描述 radiobutton
 * @CreateDate: 2021/1/16 15:22
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/1/16 15:22
 */
public class RadioBean {

    private String key;
    private int  defaultSelectedIndex ;
    private String[] values;
    private boolean isDetail;

    public RadioBean(String key, int defaultSelectedIndex, String[] values, boolean isDetail) {
        this.key = key;
        this.defaultSelectedIndex = defaultSelectedIndex;
        this.values = values;
        this.isDetail = isDetail;
    }

    public boolean isDetail() {
        return isDetail;
    }

    public void setDetail(boolean detail) {
        isDetail = detail;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public String getKey() {
        return key == null ? "" : key;
    }

    public void setKey(String key) {
        this.key = key == null ? "" : key;
    }

    public int getDefaultSelectedIndex() {
        return defaultSelectedIndex;
    }

    public void setDefaultSelectedIndex(int defaultSelectedIndex) {
        this.defaultSelectedIndex = defaultSelectedIndex;
    }
}
