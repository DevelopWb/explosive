package com.juntai.wisdom.explorsive.bean;

import com.contrarywind.interfaces.IPickerViewData;
import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-12-23 15:23
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-12-23 15:23
 */
public class ExplosiveTypeBean extends BaseResult {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements IPickerViewData {
        /**
         * id : 1
         * name : 煤矿许用瞬发电雷管
         * unit : 发
         */

        private int id;
        private String name;
        private String unit;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        @Override
        public String getPickerViewText() {
            return name;
        }
    }
}
