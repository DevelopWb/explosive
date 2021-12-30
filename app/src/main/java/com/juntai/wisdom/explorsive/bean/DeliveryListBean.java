package com.juntai.wisdom.explorsive.bean;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述   配送人员列表
 * @CreateDate: 2021-12-30 8:33
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-12-30 8:33
 */
public class DeliveryListBean extends BaseResult {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        public DataBean(int userId, String username) {
            this.userId = userId;
            this.username = username;
        }

        /**
         * userId : 2
         * username : 铁人王进喜
         */

        private int userId;
        private String username;


        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
