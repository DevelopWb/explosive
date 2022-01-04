package com.juntai.wisdom.explorsive.bean;

/**
 * @Author: tobato
 * @Description: 作用描述  人脸识别
 * @CreateDate: 2022-01-03 17:27
 * @UpdateUser: 更新者
 * @UpdateDate: 2022-01-03 17:27
 */
public class FaceCheckBean {

    //人员id
    private int personId;

    private String personName;

    private String personHeadPic;


    private String personSignPic;

    /**
     * 是否验证成功
     */
    private boolean isCheckSuccess;

    public FaceCheckBean(int personId, String personName, String personHeadPic, String personSignPic, boolean isCheckSuccess) {
        this.personId = personId;
        this.personName = personName;
        this.personHeadPic = personHeadPic;
        this.personSignPic = personSignPic;
        this.isCheckSuccess = isCheckSuccess;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName == null ? "" : personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName == null ? "" : personName;
    }

    public String getPersonHeadPic() {
        return personHeadPic == null ? "" : personHeadPic;
    }

    public void setPersonHeadPic(String personHeadPic) {
        this.personHeadPic = personHeadPic == null ? "" : personHeadPic;
    }

    public String getPersonSignPic() {
        return personSignPic == null ? "" : personSignPic;
    }

    public void setPersonSignPic(String personSignPic) {
        this.personSignPic = personSignPic == null ? "" : personSignPic;
    }

    public boolean isCheckSuccess() {
        return isCheckSuccess;
    }

    public void setCheckSuccess(boolean checkSuccess) {
        isCheckSuccess = checkSuccess;
    }
}
