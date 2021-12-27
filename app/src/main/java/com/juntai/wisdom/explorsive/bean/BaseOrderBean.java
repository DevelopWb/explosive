package com.juntai.wisdom.explorsive.bean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-12-27 8:47
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-12-27 8:47
 */
public class BaseOrderBean {

    private OrderListBean.DataBean  orderBean;

    private  IdNameBean  idNameBean;

    private String  divStr;

    public BaseOrderBean(OrderListBean.DataBean orderBean, IdNameBean idNameBean) {
        this.orderBean = orderBean;
        this.idNameBean = idNameBean;
    }

    public BaseOrderBean(OrderListBean.DataBean orderBean, String divStr) {
        this.orderBean = orderBean;
        this.divStr = divStr;
    }

    public OrderListBean.DataBean getOrderBean() {
        return orderBean;
    }

    public void setOrderBean(OrderListBean.DataBean orderBean) {
        this.orderBean = orderBean;
    }

    public IdNameBean getIdNameBean() {
        return idNameBean;
    }

    public void setIdNameBean(IdNameBean idNameBean) {
        this.idNameBean = idNameBean;
    }

    public String getDivStr() {
        return divStr == null ? "" : divStr;
    }

    public void setDivStr(String divStr) {
        this.divStr = divStr == null ? "" : divStr;
    }
}
