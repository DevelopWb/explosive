package com.juntai.wisdom.explorsive.bean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-12-21 11:38
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-12-21 11:38
 */
public class IdNameBean {

    private  int id;
    private  int  currentStatus;
    private String  name;

    public IdNameBean(int id, int currentStatus, String name) {
        this.id = id;
        this.currentStatus = currentStatus;
        this.name = name;
    }

    public int getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(int currentStatus) {
        this.currentStatus = currentStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }
}
