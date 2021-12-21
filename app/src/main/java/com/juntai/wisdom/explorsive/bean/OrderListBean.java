package com.juntai.wisdom.explorsive.bean;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  订单列表
 * @CreateDate: 2021-12-20 15:15
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-12-20 15:15
 */
public class OrderListBean  extends BaseResult {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * applyNumber : 202112191154023
         * applyTime : 2021-12-19 13:52:11
         * applyUsername : 悔创阿里杰克马
         * stat : 1
         * isVoid : 1
         */

        private int id;
        private String applyNumber;
        private String applyTime;
        private String applyUsername;
        private int stat;
        private int isVoid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getApplyNumber() {
            return applyNumber;
        }

        public void setApplyNumber(String applyNumber) {
            this.applyNumber = applyNumber;
        }

        public String getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(String applyTime) {
            this.applyTime = applyTime;
        }

        public String getApplyUsername() {
            return applyUsername;
        }

        public void setApplyUsername(String applyUsername) {
            this.applyUsername = applyUsername;
        }

        public int getStat() {
            return stat;
        }

        public void setStat(int stat) {
            this.stat = stat;
        }

        public int getIsVoid() {
            return isVoid;
        }

        public void setIsVoid(int isVoid) {
            this.isVoid = isVoid;
        }
    }
}
