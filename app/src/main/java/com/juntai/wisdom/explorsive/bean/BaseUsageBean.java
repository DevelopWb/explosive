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
   private  List<UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean>  usageReturnBeans;
    private boolean isDetail;
    private int  isReturn;

    public BaseUsageBean(List<UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean> usageReturnBeans, boolean isDetail,int isReturn) {
        this.usageReturnBeans = usageReturnBeans;
        this.isDetail = isDetail;
        this.isReturn = isReturn;
    }

    public int getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(int isReturn) {
        this.isReturn = isReturn;
    }

    public BaseUsageBean(List<ExplosiveUsageNumberBean> usageNumberBeanList, boolean isDetail, boolean aaa) {
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

    public List<UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean> getUsageReturnBeans() {
        if (usageReturnBeans == null) {
            return new ArrayList<>();
        }
        return usageReturnBeans;
    }

    public void setUsageReturnBeans(List<UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean> usageReturnBeans) {
        this.usageReturnBeans = usageReturnBeans;
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
