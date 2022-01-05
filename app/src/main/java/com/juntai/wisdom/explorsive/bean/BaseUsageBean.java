package com.juntai.wisdom.explorsive.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022-01-05 8:47
 * @UpdateUser: 更新者
 * @UpdateDate: 2022-01-05 8:47
 */
public class BaseUsageBean {

    private List<ExplosiveUsageNumberBean>  usageNumberBeanList;
    private List<ExplosiveUsageBean>  usageBeanList;
    private boolean isDetail;

    public BaseUsageBean(List<ExplosiveUsageNumberBean> usageNumberBeanList, boolean isDetail,boolean aaa) {
        this.usageNumberBeanList = usageNumberBeanList;
        this.isDetail = isDetail;
    }

    public BaseUsageBean(List<ExplosiveUsageBean> usageBeanList, boolean isDetail) {
        this.usageBeanList = usageBeanList;
        this.isDetail = isDetail;
    }

    public List<ExplosiveUsageNumberBean> getUsageNumberBeanList() {
        if (usageNumberBeanList == null) {
            return new ArrayList<>();
        }
        return usageNumberBeanList;
    }

    public void setUsageNumberBeanList(List<ExplosiveUsageNumberBean> usageNumberBeanList) {
        this.usageNumberBeanList = usageNumberBeanList;
    }

    public List<ExplosiveUsageBean> getUsageBeanList() {
        if (usageBeanList == null) {
            return new ArrayList<>();
        }
        return usageBeanList;
    }

    public void setUsageBeanList(List<ExplosiveUsageBean> usageBeanList) {
        this.usageBeanList = usageBeanList;
    }

    public boolean isDetail() {
        return isDetail;
    }

    public void setDetail(boolean detail) {
        isDetail = detail;
    }
}
