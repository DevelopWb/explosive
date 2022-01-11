package com.juntai.wisdom.explorsive.bean;

/**
 * Describe:本地位置缓存
 * Create by zhangzhenlong
 * 2020-10-24
 * email:954101549@qq.com
 */
public class LocationBean {
    private Long id;
    private String key;//地址key
    private String address;//地址
    private String latitude;
    private String longitude;
    private  boolean isDetail;
    //是否可以选择地址
    private boolean canSelectAddr;

    public LocationBean(String key, String address, String latitude, String longitude, boolean isDetail, boolean canSelectAddr) {
        this.key = key;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isDetail = isDetail;
        this.canSelectAddr = canSelectAddr;
    }

    public boolean isCanSelectAddr() {
        return canSelectAddr;
    }

    public void setCanSelectAddr(boolean canSelectAddr) {
        this.canSelectAddr = canSelectAddr;
    }

    public boolean isDetail() {
        return isDetail;
    }

    public void setDetail(boolean detail) {
        isDetail = detail;
    }

    public String getKey() {
        return key == null ? "" : key;
    }

    public void setKey(String key) {
        this.key = key == null ? "" : key;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address == null ? "" : address;
    }

    public void setAddress(String address) {
        this.address = address == null ? "" : address;
    }


    public String getLatitude() {
        return latitude == null ? "" : latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? "" : latitude;
    }

    public String getLongitude() {
        return longitude == null ? "" : longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? "" : longitude;
    }
}
