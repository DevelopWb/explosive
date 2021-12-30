package com.juntai.wisdom.explorsive.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  出库
 * @CreateDate: 2021-12-30 15:39
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-12-30 15:39
 */
public class OutHouseBean {


    /**
     * mobile : 18669505929
     * token : 4J5WRE5T2-JKBW77WP0XVJ74D5MQQZ1-DDMBNCXK-0
     * id : 2
     * deliveryAddress : 青海省刚察县
     * deliveryLongitude : 118.515156184
     * deliveryLatitude : 35.6416515
     * deliveryUser : [{"userId":2,"username":"王进喜"},{"userId":7,"username":"杰克马"}]
     * explosiveUsageNumber : [{"usageId":1,"startNumber":10001,"endNumber":10007},{"usageId":1,"startNumber":10011,"endNumber":10016},{"usageId":2,"startNumber":20011,"endNumber":20016}]
     */

    private String mobile;
    private String token;
    private int id;
    private String deliveryAddress;
    private String deliveryLongitude;
    private String deliveryLatitude;
    private List<DeliveryListBean.DataBean> deliveryUser;
    private List<ExplosiveUsageNumberBean> explosiveUsageNumber;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryLongitude() {
        return deliveryLongitude;
    }

    public void setDeliveryLongitude(String deliveryLongitude) {
        this.deliveryLongitude = deliveryLongitude;
    }

    public String getDeliveryLatitude() {
        return deliveryLatitude;
    }

    public void setDeliveryLatitude(String deliveryLatitude) {
        this.deliveryLatitude = deliveryLatitude;
    }

    public List<DeliveryListBean.DataBean> getDeliveryUser() {
        if (deliveryUser == null) {
            return new ArrayList<>();
        }
        return deliveryUser;
    }

    public void setDeliveryUser(List<DeliveryListBean.DataBean> deliveryUser) {
        this.deliveryUser = deliveryUser;
    }

    public List<ExplosiveUsageNumberBean> getExplosiveUsageNumber() {
        return explosiveUsageNumber;
    }

    public void setExplosiveUsageNumber(List<ExplosiveUsageNumberBean> explosiveUsageNumber) {
        this.explosiveUsageNumber = explosiveUsageNumber;
    }

}
