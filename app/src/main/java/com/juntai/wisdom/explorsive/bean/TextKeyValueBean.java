package com.juntai.wisdom.explorsive.bean;

import android.text.TextUtils;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/21 9:35
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/21 9:35
 */
public class TextKeyValueBean {

    private  String key;
    private  String value;
    private String hint;
    private int type;//0代表高度固定的edittext  1代表高度不固定的edittext
    private boolean isImportant;//是否必填
    private boolean valueGravityToRight;//value靠右

    public TextKeyValueBean(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public TextKeyValueBean(String key, String value, String hint, int type, boolean isImportant) {
        this.key = key;
        this.value = value;
        this.hint = hint;
        this.type = type;
        this.isImportant = isImportant;
    }
    public boolean isValueGravityToRight() {
        return valueGravityToRight;
    }

    public void setValueGravityToRight(boolean valueGravityToRight) {
        this.valueGravityToRight = valueGravityToRight;
    }

    public String getHint() {
        return TextUtils.isEmpty(hint) ? "暂无" : hint;
    }

    public void setHint(String hint) {
        this.hint = hint == null ? "" : hint;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public String getKey() {
        return TextUtils.isEmpty(key) ? "暂无" : key;
    }

    public void setKey(String key) {
        this.key = key == null ? "" : key;
    }

    public String getValue() {
        return TextUtils.isEmpty(value) ? "暂无" : value;
    }

    public void setValue(String value) {
        this.value = value == null ? "" : value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
