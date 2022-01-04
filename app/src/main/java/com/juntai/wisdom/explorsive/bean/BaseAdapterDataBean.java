package com.juntai.wisdom.explorsive.bean;


import okhttp3.MultipartBody;

/**
 * @Author: tobato
 * @Description: 作用描述  适配器中的数据
 * @CreateDate: 2021/5/11 11:12
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/11 11:12
 */
public class BaseAdapterDataBean {

    private  MultipartBody.Builder  builder;

    private  OutInMineRequest  outInMineRequest;
   private ReceiveOrderDetailBean.DataBean  receiveOrderBean;
    private UseOrderDetailBean.DataBean  useOrderBean;

    public UseOrderDetailBean.DataBean getUseOrderBean() {
        return useOrderBean;
    }

    public OutInMineRequest getOutInMineRequest() {
        return outInMineRequest;
    }

    public void setOutInMineRequest(OutInMineRequest outInMineRequest) {
        this.outInMineRequest = outInMineRequest;
    }

    public void setUseOrderBean(UseOrderDetailBean.DataBean useOrderBean) {
        this.useOrderBean = useOrderBean;
    }

    public ReceiveOrderDetailBean.DataBean getReceiveOrderBean() {
        return receiveOrderBean;
    }

    public void setReceiveOrderBean(ReceiveOrderDetailBean.DataBean receiveOrderBean) {
        this.receiveOrderBean = receiveOrderBean;
    }

    public MultipartBody.Builder getBuilder() {
        return builder;
    }

    public void setBuilder(MultipartBody.Builder builder) {
        this.builder = builder;
    }
}
