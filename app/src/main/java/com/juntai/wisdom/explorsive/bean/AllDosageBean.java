package com.juntai.wisdom.explorsive.bean;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022-01-07 11:47
 * @UpdateUser: 更新者
 * @UpdateDate: 2022-01-07 11:47
 */
public class AllDosageBean extends BaseResult {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * typeName : 煤矿许用毫秒延期电雷管
         * quantity : 2000
         * unit : 个
         */

        private String typeName;
        private int quantity;
        private String unit;

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
}
