package com.juntai.wisdom.explorsive.bean;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  矿内使用出库  请求实体
 * @CreateDate: 2022-01-04 14:24
 * @UpdateUser: 更新者
 * @UpdateDate: 2022-01-04 14:24
 */
public class OutInMineRequest {


    /**
     * mobile : 18669505929
     * token : 4J5WRE5T2-JKBW77WP0XVJ74D5MQQZ1-DDMBNCXK-0
     * id : 2
     * grantUseAddress : 青海省刚察县
     * grantUseLongitude : 118.515156184
     * grantUseLatitude : 35.6416515
     * receiveSign : /2021-11-24/d33afd53e5564f45b18efb356766698f.png
     * receivePhoto : /2021-11-24/d33afd53e5564f45b18efb356766698f.png
     * safetySign : /2021-11-24/d33afd53e5564f45b18efb356766698f.png
     * safetyPhoto : /2021-11-24/d33afd53e5564f45b18efb356766698f.png
     * blasterSign : /2021-11-24/d33afd53e5564f45b18efb356766698f.png
     * blasterPhoto : /2021-11-24/d33afd53e5564f45b18efb356766698f.png
     * safekeepingSign : /2021-11-24/d33afd53e5564f45b18efb356766698f.png
     * safekeepingPhoto : /2021-11-24/d33afd53e5564f45b18efb356766698f.png
     * number : [{"usageId":3,"startNumber":10001,"endNumber":10007},{"usageId":3,"startNumber":10011,"endNumber":10016},{"usageId":4,"startNumber":20011,"endNumber":20016}]
     */

    private String mobile;
    private String token;
    private int id;
    private String grantUseAddress;
    private String grantUseLongitude;
    private String grantUseLatitude;
    private String receiveSign;
    private String receivePhoto;
    private String safetySign;
    private String safetyPhoto;
    private String blasterSign;
    private String blasterPhoto;
    private String safekeepingSign;
    private String safekeepingPhoto;
    private List<ExplosiveUsageNumberBean> number;

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

    public String getGrantUseAddress() {
        return grantUseAddress;
    }

    public void setGrantUseAddress(String grantUseAddress) {
        this.grantUseAddress = grantUseAddress;
    }

    public String getGrantUseLongitude() {
        return grantUseLongitude;
    }

    public void setGrantUseLongitude(String grantUseLongitude) {
        this.grantUseLongitude = grantUseLongitude;
    }

    public String getGrantUseLatitude() {
        return grantUseLatitude;
    }

    public void setGrantUseLatitude(String grantUseLatitude) {
        this.grantUseLatitude = grantUseLatitude;
    }

    public String getReceiveSign() {
        return receiveSign;
    }

    public void setReceiveSign(String receiveSign) {
        this.receiveSign = receiveSign;
    }

    public String getReceivePhoto() {
        return receivePhoto;
    }

    public void setReceivePhoto(String receivePhoto) {
        this.receivePhoto = receivePhoto;
    }

    public String getSafetySign() {
        return safetySign;
    }

    public void setSafetySign(String safetySign) {
        this.safetySign = safetySign;
    }

    public String getSafetyPhoto() {
        return safetyPhoto;
    }

    public void setSafetyPhoto(String safetyPhoto) {
        this.safetyPhoto = safetyPhoto;
    }

    public String getBlasterSign() {
        return blasterSign;
    }

    public void setBlasterSign(String blasterSign) {
        this.blasterSign = blasterSign;
    }

    public String getBlasterPhoto() {
        return blasterPhoto;
    }

    public void setBlasterPhoto(String blasterPhoto) {
        this.blasterPhoto = blasterPhoto;
    }

    public String getSafekeepingSign() {
        return safekeepingSign;
    }

    public void setSafekeepingSign(String safekeepingSign) {
        this.safekeepingSign = safekeepingSign;
    }

    public String getSafekeepingPhoto() {
        return safekeepingPhoto;
    }

    public void setSafekeepingPhoto(String safekeepingPhoto) {
        this.safekeepingPhoto = safekeepingPhoto;
    }

    public List<ExplosiveUsageNumberBean> getNumber() {
        return number;
    }

    public void setNumber(List<ExplosiveUsageNumberBean> number) {
        this.number = number;
    }

}
