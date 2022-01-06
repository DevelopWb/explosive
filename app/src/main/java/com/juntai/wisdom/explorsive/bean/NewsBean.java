package com.juntai.wisdom.explorsive.bean;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022-01-06 15:50
 * @UpdateUser: 更新者
 * @UpdateDate: 2022-01-06 15:50
 */
public class NewsBean extends BaseResult {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * number : 202112191154023
         * content : 已审核通过
         * isRead : 1
         */

        private Integer id;//消息id

        private Integer applyId;//订单id

        private String number;//订单编号

        private Integer type;//订单类型（1民爆领取；2矿内申请）
        private Integer stat;//申请状态（1派出所审核；2治安大队审核；3局领导审核；4出库；5配送；6完成；7作废）
        private String content;//内容

        private Integer isRead;//是否已读（1未读；2已读）

        public int getId() {
            return id;
        }

        public Integer getStat() {
            return stat;
        }

        public void setStat(Integer stat) {
            this.stat = stat;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getApplyId() {
            return applyId;
        }

        public void setApplyId(Integer applyId) {
            this.applyId = applyId;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public void setIsRead(Integer isRead) {
            this.isRead = isRead;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getIsRead() {
            return isRead;
        }

        public void setIsRead(int isRead) {
            this.isRead = isRead;
        }
    }
}
