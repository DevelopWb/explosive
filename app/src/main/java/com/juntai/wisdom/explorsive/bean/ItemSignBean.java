package com.juntai.wisdom.explorsive.bean;

/**
 * @Author: tobato
 * @Description: 作用描述  签名
 * @CreateDate: 2021/1/28 16:39
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/1/28 16:39
 */
public class ItemSignBean {

    private String signTitle;
    private int signStatus;//0代表新增  1代表审批 2代表详情
    private String signPicPath;
    private String departmentSignPath;
    private String signTime;
    private  int isAgree;//是否同意  0是没有选择  1是同意 2是不同意
    private  String reason;//原因

    public ItemSignBean(String signTitle, int signStatus, String signPicPath, String departmentSignPath) {
        this.signTitle = signTitle;
        this.signStatus = signStatus;
        this.signPicPath = signPicPath;
        this.departmentSignPath = departmentSignPath;
    }

    public String getSignTitle() {
        return signTitle == null ? "" : signTitle;
    }

    public void setSignTitle(String signTitle) {
        this.signTitle = signTitle == null ? "" : signTitle;
    }

    public int getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(int isAgree) {
        this.isAgree = isAgree;
    }

    public String getSignPicPath() {
        return signPicPath == null ? "" : signPicPath;
    }

    public void setSignPicPath(String signPicPath) {
        this.signPicPath = signPicPath == null ? "" : signPicPath;
    }

    public int getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(int signStatus) {
        this.signStatus = signStatus;
    }


    public String getDepartmentSignPath() {
        return departmentSignPath == null ? "" : departmentSignPath;
    }

    public void setDepartmentSignPath(String departmentSignPath) {
        this.departmentSignPath = departmentSignPath == null ? "" : departmentSignPath;
    }


    public String getSignTime() {
        return signTime == null ? "" : signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime == null ? "" : signTime;
    }


    public String getReason() {
        return reason == null ? "" : reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? "" : reason;
    }
}
