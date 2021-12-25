package com.juntai.wisdom.explorsive.bean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-12-25 16:56
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-12-25 16:56
 */
public class TimeBean {

    private String  timeKey;
    private String  timeValue;
    private String  hint;

    public TimeBean(String timeKey, String timeValue, String hint) {
        this.timeKey = timeKey;
        this.timeValue = timeValue;
        this.hint = hint;
    }

    public String getTimeKey() {
        return timeKey == null ? "" : timeKey;
    }

    public void setTimeKey(String timeKey) {
        this.timeKey = timeKey == null ? "" : timeKey;
    }

    public String getTimeValue() {
        return timeValue == null ? "" : timeValue;
    }

    public void setTimeValue(String timeValue) {
        this.timeValue = timeValue == null ? "" : timeValue;
    }

    public String getHint() {
        return hint == null ? "" : hint;
    }

    public void setHint(String hint) {
        this.hint = hint == null ? "" : hint;
    }
}
