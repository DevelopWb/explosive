package com.juntai.wisdom.explorsive.bean;


import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * 个人信息
 * Created by Ma
 * on 2019/4/27
 */
public class UserBean extends BaseResult {


    /**
     * data : {"userId":2,"username":"铁人王进喜","headPortrait":"/head_img/default.jpg","mobile":"18669505929","departmentId":2,"departmentName":"刚察县治安大队","post":[{"id":6,"name":"治安大队审批人"}],"token":"4J5WRE5T2-TJBWSBCLZRGFYM7TIBLX1-YSCJMCXK-2"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userId : 2
         * username : 铁人王进喜
         * headPortrait : /head_img/default.jpg
         * mobile : 18669505929
         * departmentId : 2
         * departmentType : 2
         * departmentName : 刚察县治安大队
         * post : [{"id":6,"name":"治安大队审批人"}]
         * token : 4J5WRE5T2-TJBWSBCLZRGFYM7TIBLX1-YSCJMCXK-2
         */

        private int userId;
        private String username;
        private String headPortrait;
        private String mobile;
        private int departmentId;
        private int departmentType;
        private String departmentName;
        private String departmentAddress;//部门地址
        private String sealUrl;//部门印章地址
        private String token;

        public int getDepartmentType() {
            return departmentType;
        }

        public void setDepartmentType(int departmentType) {
            this.departmentType = departmentType;
        }

        /**
         * 2 民爆申请人
         * 3 矿场内爆炸物申请人
         * 4 矿场内爆炸物仓库管理员
         * 5 派出所审批人
         * 6 治安大队审批人
         * 7 县公安局审批人
         * 8 民爆仓库管理员
         * 9 民爆仓库配送员
         */


        private List<PostBean> post;

        public String getSealUrl() {
            return sealUrl == null ? "" : sealUrl;
        }

        public void setSealUrl(String sealUrl) {
            this.sealUrl = sealUrl == null ? "" : sealUrl;
        }

        public String getDepartmentAddress() {
            return departmentAddress == null ? "" : departmentAddress;
        }

        public void setDepartmentAddress(String departmentAddress) {
            this.departmentAddress = departmentAddress == null ? "" : departmentAddress;
        }

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

        public String getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(int departmentId) {
            this.departmentId = departmentId;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public List<PostBean> getPost() {
            return post;
        }

        public void setPost(List<PostBean> post) {
            this.post = post;
        }

        public static class PostBean {
            /**
             * id : 6
             * name : 治安大队审批人
             */

            private int id;
            private String name;

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
        }
    }
}
