package com.juntai.wisdom.explorsive.bean;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022-01-06 17:20
 * @UpdateUser: 更新者
 * @UpdateDate: 2022-01-06 17:20
 */
public class IdNameListBean extends BaseResult {

    private List<IdNameBean> data;

    public List<IdNameBean> getData() {
        return data;
    }

    public void setData(List<IdNameBean> data) {
        this.data = data;
    }

}
