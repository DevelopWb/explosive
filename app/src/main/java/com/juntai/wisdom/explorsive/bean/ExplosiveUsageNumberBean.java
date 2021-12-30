package com.juntai.wisdom.explorsive.bean;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-12-25 17:13
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-12-25 17:13
 */
public class ExplosiveUsageNumberBean {
    private Integer usageId;
    private String typeName;
    private String startNumber;
    private String endNumber;

    public ExplosiveUsageNumberBean(Integer usageId, String typeName, String startNumber, String endNumber) {
        this.usageId = usageId;
        this.typeName = typeName;
        this.startNumber = startNumber;
        this.endNumber = endNumber;
    }

    public Integer getUsageId() {
        return usageId;
    }

    public void setUsageId(Integer usageId) {
        this.usageId = usageId;
    }

    public String getTypeName() {
        return typeName == null ? "" : typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? "" : typeName;
    }

    public String getStartNumber() {
        return startNumber == null ? "" : startNumber;
    }

    public void setStartNumber(String startNumber) {
        this.startNumber = startNumber == null ? "" : startNumber;
    }

    public String getEndNumber() {
        return endNumber == null ? "" : endNumber;
    }

    public void setEndNumber(String endNumber) {
        this.endNumber = endNumber == null ? "" : endNumber;
    }
}
