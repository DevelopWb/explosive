package com.juntai.wisdom.explorsive.bean;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/21 9:35
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/21 9:35
 */
public class TextKeyValueBean {

    private String key;
    private String value;
    private String hint;
    private int id;
    private int type;//0代表高度固定的edittext  1代表高度不固定的edittext
    private boolean isImportant;//是否必填
    private boolean valueGravityToRight;//value靠右
    //选择配送人员的时候使用
    private List<DeliveryListBean.DataBean> deliveryBean;


    private boolean isDetail;

    public TextKeyValueBean(String key, String value,boolean isDetail) {
        this.key = key;
        this.value = value;
        this.isDetail = isDetail;

    }

    public List<DeliveryListBean.DataBean> getDeliveryBean() {
        if (deliveryBean == null) {
            return new ArrayList<>();
        }
        return deliveryBean;
    }

    public void setDeliveryBean(List<DeliveryListBean.DataBean> deliveryBean) {
        this.deliveryBean = deliveryBean;
    }

    public TextKeyValueBean(String key, String value, String hint, int type, boolean isImportant,boolean isDetail) {
        this.key = key;
        this.value = value;
        this.hint = hint;
        this.type = type;
        this.isImportant = isImportant;
        this.isDetail = isDetail;

    }
    public TextKeyValueBean(String key, String value, String hint, int type, boolean isImportant,List<DeliveryListBean.DataBean> deliveryBean,boolean isDetail) {
        this.key = key;
        this.value = value;
        this.hint = hint;
        this.type = type;
        this.isImportant = isImportant;
        this.deliveryBean = deliveryBean;
        this.isDetail = isDetail;
    }

    public boolean isDetail() {
        return isDetail;
    }

    public void setDetail(boolean detail) {
        isDetail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return TextUtils.isEmpty(value) ? "" : value;
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
